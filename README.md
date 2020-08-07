# Mineração de Dados - Análise Comentários em sites, fóruns ou redes sociais


O objetivo deste projeto é desenvolver um sistema capaz de analisar comentários* de usuários de sites, fóruns ou redes sociais a respeito de um certo produto e classifica-los em dois tipos: positivos e negativos. 
Para fim de comparação serão utilizados dois modelos de extração de características, o bag-of-words e o bag-of-words com TD-IF.
Além dos modelos estatísticos, será utilizado um modelo semântico de lingugem natural que pode ser encontrado em https://nlp.stanford.edu/ e a biblioteca Weka Lib para implementação da rede neural que tomará as decisões a cerca dos comentários.
O idioma dos cometários da base de dados será o inglês.

Para utilização das classes que implementam o modelo semântico é necessário adicionar a biblioteca "Stanford coreNLP 3.8.0". Recentemente foram feitos testes utilizando a versão 3.9.1, mas sem sucesso, as mudanças na biblioteca CoreNLP tornam o código atual deste projeto incompatível com a mesma, ficando assim decidido manter a utilização da versão 3.8.0, já que ela se mostrou estável e suficiente para as necessidade do projeto.
Java 8 SDK.


---------------------------------------------------------------------------------------------------------------------------------


# Data Mining - Analysis Comments on websites, forums or social networks


The objective of this project is to develop a system capable of analyzing comments* from users of websites, forums or social networks regarding a certain product and classifying them into two types: positive and negative. For comparison purposes, two feature extraction models will be used, the bag-of-words and the bag-of-words with TD-IF. In addition to the statistical models, a semantic model of natural language will be used that can be found at https://nlp.stanford.edu/ and the Weka Lib library for the implementation of the neural network that will make the decisions about the comments. The language of the database comments will be English.

To use the classes that implement the semantic model, it is necessary to add the library "Stanford coreNLP 3.8.0". Recently tests were made using version 3.9.1, but without success, the changes in the CoreNLP library make the current code of this project incompatible with it, so it was decided to keep using version 3.8.0, since it proved to be stable and enough for the needs of the project. Java 8 SDK.


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

