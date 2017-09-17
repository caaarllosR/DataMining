# Mineração de Dados - Análise de Sentimentos


O objetivo deste projeto é desenvolver um sistema de análise de sentimento, que visa identificar o sentimento que usuários de sites, fóruns ou redes sociais apresentam a respeito de um certo produto através da análise de seus comentários escritos. 

Neste projeto os comentários* serão analisados e classificados em dois tipos: positivos e negativos. 

Serão utilizados 3 modelos diferentes para a formulação da base de treinamento:
- bag-of-words 
- bag-of-words + TF-IDF
- modelo semântico utilizando o Stanford Parser**

Serão utilizados 4 algoritmos diferentes de aprendizado supervisionado:
- Árvores de Decisão
- K-Nearest Neighbor (KNN)
- Support Vector Machine (SVM)
- Rede Neural (usando backpropagation)

O objetivo final é encontrar a melhor combinação entre base de treinamento e algoritmo de aprendizado, tendo como base para escolha da melhor opção a taxa de reconhecimento obtida.
O idioma dos cometários da base de dados será o inglês.


*Base de dados com comentários de avaliações de filmes.
Link: https://drive.google.com/file/d/0B53-nM6ehYq9WjU1ZEJZNkp3Umc/view?usp=sharing

**Será utilizada uma base de dado avaliações de filmes.
Link: http://edirlei.3dgb.com.br/aulas/ia_2014_2/movie_review_dataset.zipPara utilização das classes que implementam o modelo semântico é necessário adicionar a biblioteca "Stanford coreNLP".
- Biblioteca: https://stanfordnlp.github.io/CoreNLP/download.html
- Manual de dependências:
https://nlp.stanford.edu/software/dependencies_manual.pdf
- Versão de testes online (70 palavras ou menos):
http://nlp.stanford.edu:8080/parser/index.jsp
