package estudos.java.ia.ml;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
//import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.semgraph.*;
import edu.stanford.nlp.util.*;
import estudos.java.io.IoStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* REGEX:
 * 
 * MODIFICADORES
 * 
 * (?i)	ignora maiuscula e minuscula
 * (?x) comentarios
 * (?m) multilinhas
 * (?s) dottal
 * 
 * 
 * METACARACTERES
 * 
 * .  qualquer caracter
 * \d digitos		[0-9]
 * \D nao eh digito	[^0-9]
 * \s espacos		[ \t\n\x0B\f\f]
 * \S nao eh espaco	[^\s]
 * \w letra			[a-zA-Z_0-9]
 * \W nao eh letra	
 * 
 * 
 * QUANTIFICADORES
 * 
 * X{n}		X, exatamente n vezes
 * X{n,}	X, pelo menos n vezes
 * X{n,m}	X, pelo menos n vezes, mas não mais que m vezes
 * X?		X, 0 ou 1 vez
 * X*		X, 0 ou mais vezes
 * X+		X, 1 ou mais vezes
 * 
 * 
 * METACARACTER DE FRONTEIRA
 * 
 * ^	inicia
 * $	finaliza
 * |	ou 
 * 
 * 
 * AGRUPADORES
 * 
 * [...]			agrupamento
 * [a-z]			alcanse
 * [a-e][i-u]		uniao
 * [a-z&&[aeiou]]	intersecao
 * [^abc]			excecao
 * [a-z&&[^m-p]]	subtracao
 * \x				fuga literal
 * 
 * Vaariaveis
 * 
 * (variavel)					$numerp da posicao
 * (variavel1)(variavel2)		$1  $2
 */

public class PrintParse {

	
	public String dependencies (String docText){
		//dado um texto em String retorna um String com as reacoes de dependencia gramatical das palavras
		StringBuilder dependencyParse = new StringBuilder();
 
	    Annotation text = 
	    		new Annotation(docText);
	    Properties props = new Properties();
	    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	    pipeline.annotate(text);
	    SemanticGraph graph = null;
	    List<CoreMap> sentences = text.get(CoreAnnotations.SentencesAnnotation.class);
	    
	    for (CoreMap sentence : sentences) {
//	      Tree constituencyParse = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
//	      System.out.println(constituencyParse);
	      graph =
	          sentence.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class);
	      dependencyParse.append(graph.toList());
	    }
		return dependencyParse.toString();
	}
	
	
	public LinkedHashSet<String> negateWords(String docText) {
		//dado um texto em String retorna um String com as palavras que foram usadas no texto com sentido de negacao da mesma
		LinkedHashSet<String> negateWords = new LinkedHashSet<>();
		Matcher match;
		StringBuilder splitWord = new StringBuilder();
		StringBuilder wordsDep = new StringBuilder();
		Pattern pattern1 = Pattern.compile("(ccomp.*?\\-.*?-)|(acomp.*?\\-.*?-)|(amod.*?\\-.*?-)|(npadvmod.*?\\-)");
		Pattern pattern2 = Pattern.compile("(ccomp.*?\\,|\\-)|(acomp.*?\\,|\\-)|(amod.*?\\,|\\-)|(npadvmod\\(|\\-)|\\s");
		wordsDep.append(docText);
		
	    match = pattern1.matcher(wordsDep);
	    
	    while (match.find()){
	    	splitWord.append((match.group().replaceAll(pattern2.pattern(), "")).toLowerCase());
	    	negateWords.add(splitWord.toString());
	    	splitWord.delete(0,splitWord.length());
	    	wordsDep.delete(0, wordsDep.indexOf(match.group())+match.group().length()-1);
	        match = pattern1.matcher(wordsDep);
	    }
//		match = Pattern.compile("neg.*?\\-").matcher(wordsDep);
//		
//		while (match.find()){
//		splitWord.append("not")
//		  		 .append((match.group().replaceAll("(neg\\(|\\-)", "")).toLowerCase());
		return negateWords;
	}

  public static void main(String[] args) throws IOException{
	  
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	LinkedHashSet<String> negateWords = new LinkedHashSet<>();

	String docText = new String();
	String dependencies = new String();
	
	IoStream ioStream = new IoStream();
	PrintParse parse = new PrintParse();
	
	File dp = new File("docsTest/10_3.txt");
	Path docsPath = Paths.get(dp.getAbsolutePath());
	
	bw.write("\n\nP -1 \n\n");
	bw.flush(); 

	docText = ioStream.getText(docsPath);
	dependencies = parse.dependencies(docText);
	
	bw.write("\n\nP - 2 \n\n");
	bw.flush(); 

	negateWords = parse.negateWords(dependencies);         
	

	bw.write("\n\nP - 3 \n\n");
	bw.flush(); 

	bw.write("relacao de dependencia gramatical das palavras:\n\n");
	bw.flush();
	
	bw.write(dependencies);
	bw.flush();   
  
	bw.write("\n\nP - 4 \n\n");
	bw.flush(); 

	bw.write("\n\npalavras negadas:\n\n");
	bw.flush();
  
	for(String words : negateWords){
        bw.write(words);
        bw.write("\n");
		bw.flush(); 
	}  
  }
}