# StanfordCoreNLP

O objetivo deste projeto é desenvolver um sistema de análise de sentimento, que visa identificar o sentimento que usuários de sites, fóruns ou redes sociais apresentam a respeito de um certo produto através da análise de seus comentários escritos. 

Neste projeto os comentários serão analisados e classificados em dois tipos: positivos e negativos. 

Será utilizada uma base de dado avaliações de filmes.
Link: http://edirlei.3dgb.com.br/aulas/ia_2014_2/movie_review_dataset.zip

Serão utilizados 3 modelos diferentes para a formulação do conjunto de treinamento:
-bag-of-words 
-bag-of-words + TF-IDF
-modelo semântico utilizando o Stanford Parser*

Serão utilizados 4 algoritmos diferentes de aprendizado supervisionado:
- Árvores de Decisão
- K-Nearest Neighbor (KNN)
- Support Vector Machine (SVM)
- Rede Neural (usando backpropagation)

O objetivo final é encontrar a melhor combinação de base de treinamento e algoritmo de aprendizado, tendo como referência a melhor taxa de reconhecimento.


Enunciado completo do problema:
http://edirlei.3dgb.com.br/aulas/ia_2016_2/Trabalho2.pdf


*Para utilização das classes que implementam o modelo semântico é necessário adicionar a biblioteca "Stanford coreNLP".
Link: https://stanfordnlp.github.io/CoreNLP/download.html
Manual de dependências:
https://nlp.stanford.edu/software/dependencies_manual.pdf
Versão de testes online (70 palavras ou menos):
http://nlp.stanford.edu:8080/parser/index.jsp
