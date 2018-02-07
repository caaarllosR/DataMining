//Código criado utilizando a arvore de decisão J48 do weka
//para mais detalhes sobre o código acessar o seguinte link:
//https://docs.google.com/document/d/1p4vzEB75HRwzSQJeRzb2u-IAB8m6G7Q9nGy5s4-scwU/edit?usp=sharing

package estudos.java.ia.dt;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WekaClassifier {
	
	File dp = new File("dtArff/dados.arff");
	private Path path = Paths.get(dp.getAbsolutePath());
	private Charset utf8 = StandardCharsets.UTF_8;
	Object[] info = new Object[5];
	private double i = 2;
	private double p = Double.NaN;
	
	private double classify(Object[] i) throws Exception {

		p = Double.NaN;
		p = this.posicao(i);
		return p;
	}

	private double posicao(Object[] i) {
		p = Double.NaN;
		if (i[0] == null) {
			p = 1;
		} else if (i[0].equals("fimEstante")) {
			p = 1;
		} else if (i[0].equals("cd_espec")) {
			p = this.preco(i);
		} else if (i[0].equals("EstantPadrao")) {
			p = this.nivel_olho(i);
		}
		return p;
	}

	private double preco(Object[] i) {
		p = Double.NaN;
		if (i[2] == null) {
			p = 1;
		} else if (new Double(i[2].toString()) <= 80.0) {
			p = 1;
		} else if (new Double(i[2].toString()) > 80.0) {
			p = 0;
		}
		return p;
	}

	private double nivel_olho(Object[] i) {
		p = Double.NaN;
		if (i[3] == null) {
			p = 0;
		} else if (i[3].equals("FALSE")) {
			p = 0;
		} else if (i[3].equals("TRUE")) {
			p = 1;
		}
		return p;
	}

	private void testaDados() throws Exception { 
		//Os resultados respondem aos dados passados no arquivo arff em questão para cada linha linha de dados em @data

		info = new Object[5];
		i = 2;
		try (BufferedReader r = Files.newBufferedReader(path, utf8)) {
			String line = null;

			while (i != 0.0 & i != 1.0) {
				line = r.readLine();
				info = line.split(",");
				i = classify(info);
			}

			while ((line) != null) {
				info = line.split(",");
				System.out.format("Cliente comprou o CD? %d\n", (int) classify(info));
				line = r.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		new WekaClassifier().testaDados();

	}
}
