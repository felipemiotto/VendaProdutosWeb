# VendaProdutosWeb
Este software tem a finalidade de efetuar vendas de produtos a clientes pelo navegador. Neste  pode-se efetuar vendas a clientes, cadastros de dados relacionados a venda e a localização. O sistema efetua CRUD dos seguintes dados: 
	1 – CEP;
	2 – Cidade; 
	3 – Cliente;
	4 – Documento;
	5 – Estado;
	6 – Grupo;
	7 – País;
	8 – Produto;
	9 – Venda.
A solução está pronta para ser executada no Heroku. Caso a opção seja executar em um computador pessoal, deve-se incluir um arquivo com o nome  “conecta.txt” na pasta bin do Apache,  este arquivo deve conter o texto “usuario,senha,basededados”, onde usuário é o usuário do banco de dados, senha é a senha do banco de dados e basededados é o nome do banco a ser usado.
As principais pastas e arquivos desenvolvidos/alterados, serão na sequencia exmplicados:

Na pasta raiz do projeto temos os arquivos pom e .travis.yml onde temos as dependencias e bibliotecas relacionadas ao código
e arquivos de configuração do Travis.io, respectivamente.

No caminho vendaprodutosweb/src/main/java temos as pastas: 
Banco, onde estão os scripts para criação do banco de dados e tabelas;
Crud, onde estão arquivos de conexão com o banco de dados, onde todos fazem extends da classe AbstractCrud. Temos também a classe EMNames onde faz-se a conexão com o banco de dados analisando se o software está alocado no Heroku ou em um computador, validando uma variável de banco de dados criada pelo Heroku.
Model, aqui estão as classes de modelo, estas possuem atributos, funções e mapeamento com as tabeças do banco de dados.
A pasta Utilitarios possui um arquivo usado para conversão de UUID e o Utils.java.
Na pasta Web temos os arquivos Managed Beans que fazem a ligação da interface html com o model e banco de dados.

No caminho vendaprodutosweb/src/main/webapp/operações temos os arquivos de html para a criação do layout padrão: cabec, content, footer, index e layout_operações.
Tem-se aqui também as pastas relacionadas as views de todos os CRUDs, dentro destas pastas existem arquivos responsáveis pela tela de cadastro, alterar, listar e excluir.

