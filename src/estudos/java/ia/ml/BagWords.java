package estudos.java.ia.ml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import estudos.java.io.IoStream;

public class BagWords {
	
	private LinkedHashSet<String> bagCollection = new LinkedHashSet<String>(); // armazena as palavras ao bag of words da colecao de documentos
	private LinkedHashSet<String> bagCollectionAux = new LinkedHashSet<String>(); // armazena as palavras ao bag of words da colecao de documentos
	private LinkedHashSet<String> bagDoc = new LinkedHashSet<String>(); //armazena as palavras ao bag of words do documento
	private LinkedHashMap<String, Integer> tf = new LinkedHashMap<String, Integer>(); // conta a quantidade de vezes em que uma palavra da bagCollection aparece em toda colecao de documentos
	private LinkedHashMap<String, Integer> df = new LinkedHashMap<String, Integer>(); // conta a quantidade de vezes em que uma palavra da bagCollection aparece por documento
	private LinkedHashMap<String, Integer> NWord = new LinkedHashMap<String, Integer>(); // conta quantos documentos na cole��o tem uma certa palavra da bagCollection
	
	public  void setContent(Path docsPath){ //adiciona as palavras na bag e atualiza o LinkedHashMap "tf"
		
		int i = 0;
		int j = 0;
		
		IoStream ioStream = new IoStream();
		ArrayList<Path> paths = new ArrayList<>();
		paths = ioStream.getPaths(docsPath);
		
		ArrayList<String> words = new ArrayList<>();		
		
		for(Path p: paths){

			Collections.addAll(words, ioStream.getText(p).toLowerCase().split("[\\W]+"));	

			for(String s: words){

				if(!(bagCollection.contains(s))) {
					bagCollection.add(s);
					bagDoc.add(s);	
					tf.put(s,1);	
					NWord.put(s,1);	

				} else if(!(bagDoc.contains(s))) {
					bagDoc.add(s);	
					i = tf.get(s);
					j = NWord.get(s);
					tf.put(s, i+1);
					NWord.put(s, j+1);	

				} else	{
					i = tf.get(s);
					tf.put(s, i+1);
				}
			}
			
			words.removeAll(words);
			bagDoc.removeAll(bagDoc);
		}

	}
	
	public void getDfCount(Path docPath){// faz o calculo do DF

		int i = 0;
		IoStream ioStream = new IoStream();
		
		ArrayList<String> words = new ArrayList<>();		

		Collections.addAll(words, ioStream.getText(docPath).toLowerCase().split("[\\W]+"));
				
		for(String s: bagCollection){
			df.put(s,0);														
		}
		
		for(String s: words){
			if(df.containsKey(s)){
				i = df.get(s);
				df.put(s, i+1);
			}
		}
	
	}
	
	public  LinkedHashMap<String,Integer> getTfCount(){
		return tf;
	}
	
	public  LinkedHashSet<String> getContent(){
		return bagCollection;
	}
	
	public void StopWordsTf(){ // remove palavras da bag mediante uma condi��o, em breve utilizara o calculo do TF para condi��o
			
		bagCollectionAux.addAll(bagCollection);
		
		for(String s: bagCollectionAux){
			if ((NWord.get(s)<3000)){
				bagCollection.remove(s);
				tf.remove(s);
				NWord.remove(s);
			}
			else if ((NWord.get(s)>10000)){
				bagCollection.remove(s);
				tf.remove(s);
				NWord.remove(s);
			}
		}			
	}
	
	public static void main(String[] args) throws IOException {

		File dp = new File("dataSetReduzido/neg");
		Path negPath = Paths.get(dp.getAbsolutePath());
		
		dp = new File("dataSetReduzido/pos");
		Path posPath = Paths.get(dp.getAbsolutePath());
		
		dp = new File("resultTest/teste.arff");
		Path arffPath = Paths.get(dp.getAbsolutePath());

		ArrayList<Path> paths = new ArrayList<>();

		StringBuilder text = new StringBuilder();
		
		IoStream ioStream = new IoStream();
		Charset utf8 = StandardCharsets.UTF_8;
		OpenOption options = StandardOpenOption.APPEND;
		
		Files.deleteIfExists(arffPath);
		Files.createFile(arffPath);
		
		BagWords bagWords = new BagWords();
		Runtime rt = Runtime.getRuntime();
		
		rt.runFinalization();
		rt.gc();
		
		System.out.println("etapa 1 de 5 - Bag of Words");
		bagWords.setContent(negPath);

		System.out.println("etapa 2 de 5 - Bag of Words");
		bagWords.setContent(posPath);
		
		System.out.println("etapa 3 de 5 - Stop Words");
		bagWords.StopWordsTf();
		
		System.out.println("Bag of Words size: "+ bagWords.bagCollection.size());

		text.append("@relation avaliacao\n\n");	
		

		System.out.println("criando arquivo arff");

		for(String s : bagWords.bagCollection)	{
				text.append("@attribute ").append(s).append("	NUMERIC\n");
		}		
		
		text.append("@attribute class        {negativo, positivo}\n\n")			
			.append("@data\n");
		
		try	(BufferedWriter w = Files.newBufferedWriter(arffPath, utf8, options)) {
			w.write(text.toString());
		}catch (IOException e){
			e.printStackTrace();
		}
		
		text.delete(0,text.length());
		
		rt.runFinalization();
		rt.gc();
		
		
		paths = ioStream.getPaths(negPath);

		System.out.println("etapa 4 de 5");

		for(Path p : paths)	{

			bagWords.getDfCount(p);

			for(String s : bagWords.bagCollection)	{
					text.append(bagWords.df.get(s))
						.append(",");
			}					
			text.append("negativo\n");						
		}try	(BufferedWriter w = Files.newBufferedWriter(arffPath, utf8, options)) {
			w.write(text.toString());
		}catch (IOException e){
			e.printStackTrace();
		}
		
		text.delete(0,text.length());
		
		rt.runFinalization();
		rt.gc();

			
			
		paths = ioStream.getPaths(posPath);

		System.out.println("etapa 5 de 5");

		for(Path p : paths)	{
			
			bagWords.getDfCount(p);

			for(String s : bagWords.bagCollection)	{
					text.append(bagWords.df.get(s))
						.append(",");
			}
			text.append("positivo\n");
		}try	(BufferedWriter w = Files.newBufferedWriter(arffPath, utf8, options)) {
			w.write(text.toString());
		}catch (IOException e){
			e.printStackTrace();
		}
		
		text.delete(0,text.length());
		
		rt.runFinalization();
		rt.gc();
			
									
		System.out.println("Arquivo arff gerado");

	}
}
