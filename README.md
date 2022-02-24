# GerenciadorDeTarefas

Informações para execução do projeto:

- Eclipse IDE 2021-12
- Tomcat 9.0.55: Deve ser adicionado ao eclipse acessando File > New > Other. Na tela que será exibida, selecionar a opção “Server” e clicar em “Next”, em seguida clicar em “Apache”, selecionar a opção “Tomcat v9.0 Server” e clicar em “Next”. Na tela que será exibida, clicar em “Download and Install”. Após o download e a instalação serem concluídos, clique em “Next”. Por fim, clique no nome do projeto, que estará na seção “Available”, clique em “Add >” e finalize o procedimento clicando em “Finish”.
- Todas as libs necessárias para a execução se encontram em “\GerenciadorDeTarefas\src\main\webapp\WEB-INF\lib”. Devem ser adicionadas ao Classpath do projeto, após o mesmo ser importado, clicando com o botão direito no projeto e acessando Build Path > Configure Build Path. Em Classpath, acessar a opção “Add External JARs”, identificar a pasta lib mencionada anteriormente, selecionar todos os arquivos e clicar em “Abrir”. Por fim, clicar em “Apply and Close”.
- PostgreSQL 14.2: O arquivo “hibernate.cfg.xml”, encontrado em “\GerenciadorDeTarefas\src\main\java\hibernate.cfg.xml”, deve ser alterado para os dados que foram configurados na instalação do PostgreSQL. Os dados de conexão com o banco a serem alterados são os seguintes:o  usuário, que se encontra no trecho <property name="hibernate.connection.username">postgres</property>, a senha, que se encontra no trecho <property name="hibernate.connection.password">admin</property>, e a url de conexão, que se encontra no trecho <property name="hibernate.connection.url">jdbc:postgresql://localhost:5433/gerenciadordetarefas</property>.

—---------------------------------------------------------------------------------------------------------------------

Script de criação do banco de dados:

--DROP DATABASE IF EXISTS gerenciadordetarefas;

CREATE DATABASE gerenciadordetarefas
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

—---------------------------------------------------------------------------------------------------------------------

Script de criação da tabela:

--DROP TABLE IF EXISTS public.tarefas;

CREATE TABLE IF NOT EXISTS public.tarefas
(
    id oid NOT NULL,
    titulo text COLLATE pg_catalog."default",
    descricao text COLLATE pg_catalog."default",
    responsavel text COLLATE pg_catalog."default",
    prioridade text COLLATE pg_catalog."default",
    deadline date,
    status text COLLATE pg_catalog."default",
    CONSTRAINT tarefas_pkey PRIMARY KEY (id)
)
