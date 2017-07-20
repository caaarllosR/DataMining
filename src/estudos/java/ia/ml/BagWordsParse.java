package estudos.java.ia.ml;

import java.io.BufferedWriter;
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

import estudos.java.xti.io.IoStream;
import estudos.java.xti.regex.Regex;

public class BagWordsParse {
	
	private LinkedHashSet<String> bagCollection = new LinkedHashSet<String>(); // armazena as palavras ao bag of words da colecao
	private LinkedHashSet<String> bagCollectionAux = new LinkedHashSet<String>(); // armazena as palavras ao bag of words da colecao
	private LinkedHashSet<String> bagDoc = new LinkedHashSet<String>(); //armazena as palavras ao bag of words do documento
	private LinkedHashMap<String, Integer> tf = new LinkedHashMap<String, Integer>(); // conta todas as vezes que a palavra aparece em toda colecao de documentos
	private LinkedHashMap<String, Integer> df = new LinkedHashMap<String, Integer>(); // conta a aparição da palavra por documento
	private LinkedHashMap<String, Integer> NWord = new LinkedHashMap<String, Integer>(); // conta quantos documentos na coleção tem uma certa palavra
	
	public  void setContent(Path docsPath){ //adiciona as palavras na bag e um contador de mesmo indice na cWord
		
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
	
	public void getDfCount(Path docPath){

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
	
	public void StopWordsTf(){ // remove palavras da bag mediante uma condição
		
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
	
	public void parse(Path path){
		
		ArrayList<String> words = new ArrayList<>();		
		IoStream ioStream = new IoStream();
		PrintParse parse = new PrintParse();
		Regex regex = new Regex();
						
		words = regex.negateWords(parse.dependencies(ioStream.getText(path)));         
   
		bagCollectionAux.addAll(bagCollection);
		
		for(String s: bagCollectionAux){
			for(String c: words){
				if((s.equals(c))){
					bagCollection.remove(s);
				}
			}
		}	
    
	}
	public static void main(String[] args) throws IOException {

/*		Path dataSetPath1 = Paths.get("D:/Dropbox/uerj/Java/Estudos/docsTest");
				
		Path docsPath = Paths.get("D:/Dropbox/uerj/Java/Estudos/docsTest");

		StringBuilder text = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		BagWords bagWords = new BagWords();
		bagWords.setContent(docsPath);

		text.append(bagWords.bagCollection)
			.append("\n")
			.append(bagWords.tf);
				
		bw.write(text.toString());
		bw.flush();*/
						
//		Path dataSetPath1 = Paths.get("D:/Dropbox/uerj/Java/Estudos/movie_review_dataset/part1/neg");
//		Path dataSetPath2 = Paths.get("D:/Dropbox/uerj/Java/Estudos/movie_review_dataset/part2/neg");
//		Path dataSetPath3 = Paths.get("D:/Dropbox/uerj/Java/Estudos/movie_review_dataset/part1/pos");
//		Path dataSetPath4 = Paths.get("D:/Dropbox/uerj/Java/Estudos/movie_review_dataset/part2/pos");
		
		Path dataSetPath1 = Paths.get("D:/Dropbox/uerj/Java/Estudos/docsTest");
		Path dataSetPath2 = Paths.get("D:/Dropbox/uerj/Java/Estudos/docsTest");
		Path dataSetPath3 = Paths.get("D:/Dropbox/uerj/Java/Estudos/docsTest");
		Path dataSetPath4 = Paths.get("D:/Dropbox/uerj/Java/Estudos/docsTest");
		
		Path arffPath = Paths.get("D:/Dropbox/uerj/Java/Estudos/docsTest2/teste.arff");

		ArrayList<Path> paths = new ArrayList<>();

		StringBuilder text = new StringBuilder();
		
		IoStream ioStream = new IoStream();
		Charset utf8 = StandardCharsets.UTF_8;
		OpenOption options = StandardOpenOption.APPEND;
		
		Files.deleteIfExists(arffPath);
		Files.createFile(arffPath);
		
		BagWordsParse bagWords = new BagWordsParse();
		Runtime rt = Runtime.getRuntime();
		
		rt.runFinalization();
		rt.gc();
		
		System.out.println("bag 1");
		bagWords.setContent(dataSetPath1);
		System.out.println("bag 2");
		bagWords.setContent(dataSetPath2);
		System.out.println("bag 3");
		bagWords.setContent(dataSetPath3);
		System.out.println("bag 4");
		bagWords.setContent(dataSetPath4);
		
		System.out.println("Stop Words");
//		bagWords.StopWordsTf();
		
		System.out.println("bagCollection size "+ bagWords.bagCollection.size());

		text.append("@relation avaliacao\n\n");	
		

		System.out.println("attributes");

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
		
		
		paths = ioStream.getPaths(dataSetPath1);

		System.out.println("for 1");

		for(Path p : paths)	{

			bagWords.parse(p);
			bagWords.getDfCount(p);
			

			for(String s : bagWords.bagCollection)	{
					text.append(bagWords.df.get(s))
						.append(",");
			}					
			text.append("negativo\n");						
		}
			try	(BufferedWriter w = Files.newBufferedWriter(arffPath, utf8, options)) {
				w.write(text.toString());
			}catch (IOException e){
				e.printStackTrace();
			}
			
			text.delete(0,text.length());
			
			rt.runFinalization();
			rt.gc();
			
				
			
/*		paths = ioStream.getPaths(dataSetPath2);
		
		System.out.println("for 2");

		for(Path p : paths)	{

			bagWords.getDfCount(p);

			for(String s : bagWords.bagCollection)	{
					text.append(bagWords.df.get(s))
						.append(",");
			}				 
			text.append("negativo\n");			
		}
			try	(BufferedWriter w = Files.newBufferedWriter(arffPath, utf8, options)) {
				w.write(text.toString());
			}catch (IOException e){
				e.printStackTrace();
			}
			
			text.delete(0,text.length());
			
			rt.runFinalization();
			rt.gc();
				
					
					
		paths = ioStream.getPaths(dataSetPath3);

		System.out.println("for 3");

		for(Path p : paths)	{
			
			bagWords.getDfCount(p);

			for(String s : bagWords.bagCollection)	{
					text.append(bagWords.df.get(s))
						.append(",");
			}
			text.append("positivo\n");
		}
			try	(BufferedWriter w = Files.newBufferedWriter(arffPath, utf8, options)) {
				w.write(text.toString());
			}catch (IOException e){
				e.printStackTrace();
			}
							
			text.delete(0,text.length());
			
			rt.runFinalization();
			rt.gc();							
					
		
			
		paths = ioStream.getPaths(dataSetPath4);

		System.out.println("for 4");

		for(Path p : paths)	{
			
			bagWords.getDfCount(p);

			for(String s : bagWords.bagCollection)	{
					text.append(bagWords.df.get(s))
						.append(",");
			}
			text.append("positivo\n");
		}
			try	(BufferedWriter w = Files.newBufferedWriter(arffPath, utf8, options)) {
				w.write(text.toString());
			}catch (IOException e){
				e.printStackTrace();
			}
			
			text.delete(0,text.length());
			
			rt.runFinalization();
			rt.gc();*/
			
									
		System.out.println("Arquivo gerado");

	}
}
