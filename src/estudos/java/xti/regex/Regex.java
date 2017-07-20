package estudos.java.xti.regex;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import estudos.java.ia.ml.PrintParse;
import estudos.java.xti.io.IoStream;


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
 * [a-e][i-u]		união
 * [a-z&&[aeiou]]	interseção
 * [^abc]			exceção
 * [a-z&&[^m-p]]	subtração
 * \x				fuga literal
 */

public class Regex{

	public ArrayList<String> negateWords(String docText) {
		
		ArrayList<String> negateWords = new ArrayList<>();
		Matcher match;
		StringBuilder splitWord = new StringBuilder();
		StringBuilder wordsDep = new StringBuilder();
		wordsDep.append(docText);
		
	    match = Pattern.compile("(ccomp.*?\\-.*?-)|(acomp.*?\\-.*?-)|(amod.*?\\-.*?-)|(npadvmod.*?\\-)").matcher(wordsDep);
	    
	    while (match.find()){
	    	splitWord.append((match.group().replaceAll("(ccomp.*?\\,|\\-)|(acomp.*?\\,|\\-)|(amod.*?\\,|\\-)|(npadvmod\\(|\\-)|\\s", "")).toLowerCase());
	    	negateWords.add(splitWord.toString());
	    	splitWord.delete(0,splitWord.length());
	    	wordsDep.delete(0, wordsDep.indexOf(match.group())+match.group().length()-1);
	        match = Pattern.compile("(ccomp.*?\\-.*?-)|(acomp.*?\\-.*?-)|(amod.*?\\-.*?-)|(npadvmod.*?\\-)").matcher(wordsDep);
	    }
	    
//   match = Pattern.compile("neg.*?\\-").matcher(wordsDep);
//	    
//	    while (match.find()){
//	    	splitWord.append("not")
//	    			 .append((match.group().replaceAll("(neg\\(|\\-)", "")).toLowerCase());
		return negateWords;
	}
	
    public static void main(String[] args) throws IOException  {
    	
		ArrayList<String> negateWords = new ArrayList<>();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		ArrayList<Path> paths = new ArrayList<>();
		
		IoStream ioStream = new IoStream();
		PrintParse parse = new PrintParse();
		Regex regex = new Regex();
				
		Path docsPath = Paths.get("D:/Developer/GitHub/Java/workspace/StanfordCorenpl/docsTest");
		paths = ioStream.getPaths(docsPath);
		
		negateWords = regex.negateWords(parse.dependencies(ioStream.getText(paths.get(2))));         
 
		for(String words : negateWords){
	        bw.write(words);
	        bw.write("\n");
			bw.flush(); 
		}               
    }
}
