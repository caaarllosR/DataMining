# Mineração de Dados - Análise Comentários em sites, fóruns ou redes sociais


O objetivo deste projeto é desenvolver um sistema capaz de analisar comentários* de usuários de sites, fóruns ou redes sociais a respeito de um certo produto e classifica-los em dois tipos: positivos e negativos. 
Os modelos estatísticos utilizados para a extração de características serão o bag-of-words e o bag-of-words com TD-IF, afim de serem comparados os resultados com cada modelo.
Além dos modelos estatísticos, será utilizado um modelo semântico utilizado o Stanford Parser, para comparação de resultados.
Serão separados atributos para gerar a base de treinamento e de validação utilizando cada um dos modelos de extração de características.
As bases de treinamento e de validação serão utilizados em algoritmos de aprendizado supervisionado, utilizando a biblioteca Weka Lib.
O idioma dos cometários da base de dados será o inglês.

Para utilização das classes que implementam o modelo semântico é necessário adicionar a biblioteca "Stanford coreNLP 3.8.0". Recentemente foram feitos testes utilizando a versão 3.9.1, mas sem sucesso, as mudanças na biblioteca CoreNLP tornam o código atual deste projeto incompatível com a mesma, ficando assim decidido manter a utilização da versão 3.8.0, já que ela se mostrou estável e suficiente para as necessidade do projeto.
Java 8 SDK.


---------------------------------------------------------------------------------------------------------------------------------


# Data Mining - Analysis Comments on websites, forums or social networks


The goal of this project is to develop a system capable of analyzing comments * from users of websites, forums or social networks regarding a certain product and classifying them into two types: positive and negative.
The statistical models used for the extraction of characteristics will be the bag-of-words and the bag-of-words with TD-IF, in order to compare the results with each model.
In addition to the statistical models, a semantic model will be used, the Stanford Parser, for comparison of results.
Attributes will be separated to generate the training and validation base using each of the characteristic extraction models.
The training and validation bases will be used in supervised learning algorithms, using the Weka Lib library.
The language of the database comments will be English.

To use the classes that implement the semantic model it is necessary to add the "Stanford coreNLP 3.8.0" library. Recently tests were made using version 3.9.1, but without success, changes in the CoreNLP library make the current code of this project incompatible with it, so it was decided to maintain the use of version 3.8.0, since it was stable and sufficient for the needs of the project.
Java 8 SDK.


- *Base de dados com comentários de avaliações de filmes.
Link: https://drive.google.com/file/d/0B53-nM6ehYq9WjU1ZEJZNkp3Umc/view?usp=sharing
- Biblioteca Stanford coreNLP 3.8.0: 
https://stanfordnlp.github.io/CoreNLP/history.html
- Manual de dependências da biblioteca:
https://nlp.stanford.edu/software/dependencies_manual.pdf
- Versão de testes online da biblioteca (70 palavras ou menos):
http://nlp.stanford.edu:8080/parser/index.jsp
- Weka:
https://www.cs.waikato.ac.nz/ml/weka/

