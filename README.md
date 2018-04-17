# Mineração de Dados - Análise Comentários em sites, fóruns ou redes sociais


O objetivo deste projeto é desenvolver um sistema capaz de analisar comentários* de usuários de sites, fóruns ou redes sociais a respeito de um certo produto e classifica-los em dois tipos: positivos e negativos. 
Os modelos estatísticos utilizados para a extração de características serão o bag-of-words e o bag-of-words com TD-IF, afim de serem comparados os resultados com cada modelo.
Além dos modelos estatísticos, será utilizado um modelo semântico utilizado o Stanford Parser, para comparação de resultados.
Serão separados atributos para gerar a base de treinamento e de validação utilizando cada um dos modelos de extração de características.
As bases de treinamento e de validação serão utilizados em algoritmos de aprendizado supervisionado, utilizando a biblioteca Weka Lib.
O idioma dos cometários da base de dados será o inglês.

Para utilização das classes que implementam o modelo semântico é necessário adicionar a biblioteca "Stanford coreNLP 3.8.0". Recentemente foram feitos testes utilizando a versão 3.9.1, mas sem sucesso, as mudanças na biblioteca CoreNLP tornam o código atual deste projeto incompatível com a mesma, ficando assim decidido manter a utilização da versão 3.8.0, já que ela se mostrou estável e suficiente para as necessidade do projeto.

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

