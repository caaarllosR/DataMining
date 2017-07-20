package estudos.java.ia.ml;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
//import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.semgraph.*;
import edu.stanford.nlp.util.*;
import estudos.java.xti.io.IoStream;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PrintParse {

	public String dependencies (String docText){
		
		StringBuilder dependencyParse = new StringBuilder();
 
	    Annotation text = 
	    		new Annotation(docText);
	    Properties props = new Properties();
	    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	    pipeline.annotate(text);
	    SemanticGraph graph = null;
	    
	    for (CoreMap sentence : text.get(CoreAnnotations.SentencesAnnotation.class)) {
	      //Tree constituencyParse = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
	      //System.out.println(constituencyParse);
	      graph =
	          sentence.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class);
	      dependencyParse.append(graph.toList());
	    }
		return dependencyParse.toString();
	}
	
  public static void main(String[] args) throws IOException{
	  
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	ArrayList<Path> paths = new ArrayList<>();
	StringBuilder docText = new StringBuilder();
	
	IoStream ioStream = new IoStream();
	PrintParse parse = new PrintParse();
	
	Path docsPath = Paths.get("D:/Developer/GitHub/StanfordCorenpl/java/Estudos/docsTest");
	paths = ioStream.getPaths(docsPath);
	docText.append(ioStream.getText(paths.get(2)));

	bw.write(parse.dependencies(ioStream.getText(paths.get(2))));
	bw.flush();
    
  }

}