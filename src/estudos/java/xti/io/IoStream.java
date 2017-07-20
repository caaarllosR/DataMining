package estudos.java.xti.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class IoStream {

		public ArrayList<Path> getPaths(Path directoryPath){
			
			ArrayList<Path> paths = new ArrayList<>();
	
			try(DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)){
				for(Path p : stream){		
					paths.add(p); 					
				}
			}catch(IOException | DirectoryIteratorException e){
				e.printStackTrace();
			}
			return paths;
		}
		
		public String getText(Path path){
			
			StringBuilder text = new StringBuilder();

			try(BufferedReader br = Files.newBufferedReader(path)){
			    while(br.ready()){
				text.append(br.readLine());
				}
			}catch(IOException e){
				e.printStackTrace();
			}
										
			return text.toString();						
		}		
		
		
		public String writer(Path path, Charset utf8, String text){
			
			try(BufferedWriter wr = Files.newBufferedWriter(path, utf8)){
			    wr.write(text);
			}catch(IOException e){
				e.printStackTrace();
			}
										
			return text.toString();
		}
	
		
		
	public static void main(String[] args) throws IOException {
		
		

		ArrayList<Path> paths = new ArrayList<>();
		
		Path arffPath = Paths.get("D:/Dropbox/uerj/Java/Estudos/docsTest2/teste.arff");

		Path docsPath = Paths.get("D:/Dropbox/uerj/Java/Estudos/docsTest");
		StringBuilder text = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		IoStream ioStream = new IoStream();
		
		paths = ioStream.getPaths(docsPath);
		Charset utf8 = StandardCharsets.UTF_8;
		OpenOption options = StandardOpenOption.APPEND;
		
		Files.deleteIfExists(arffPath);
		Files.createFile(arffPath);
		
		text.append(ioStream.getText(paths.get(2)));

		try(BufferedWriter w = Files.newBufferedWriter(arffPath, utf8, options))	{
			w.write(text.toString());
		} catch (IOException e){
			e.printStackTrace();
		}
		
		text.delete(0, text.length());
		text.append("\n")
			.append(ioStream.getText(paths.get(2)));

		try(BufferedWriter w = Files.newBufferedWriter(arffPath, utf8, options))	{
			w.write(text.toString());
		} catch (IOException e){
			e.printStackTrace();
		}
		
		bw.write(text.toString());
		bw.flush();
	}

}
