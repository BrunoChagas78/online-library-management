# Online Library Management

Sistema web simples para gerenciamento de livros, desenvolvido na disciplina **Prática Profissional em ADS**.

Esta versão corresponde à **Iteração 2** do projeto, identificada pela tag **`v2`** no repositório Git.

---

## Requisitos para executar

- Java 17 ou superior  
- Maven 3.8 ou superior  
- PostgreSQL instalado e em execução

---

## Configuração do banco de dados

1. Crie o banco de dados no PostgreSQL:

   ```sql
   CREATE DATABASE online_library;
(Opcional) Crie um usuário e conceda acesso:

sql
Copiar código
CREATE USER library_user WITH ENCRYPTED PASSWORD 'library_pass';
GRANT ALL PRIVILEGES ON DATABASE online_library TO library_user;
Ajuste o arquivo src/main/resources/application.properties com os dados do seu ambiente. Exemplo:

properties
Copiar código
spring.datasource.url=jdbc:postgresql://localhost:5432/online_library
spring.datasource.username=library_user
spring.datasource.password=library_pass

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Como executar a aplicação
Clone o repositório:

bash
Copiar código
git clone https://github.com/BrunoChagas78/online-library-management.git
cd online-library-management
(Opcional) Vá para a versão da Iteração 2:

bash
Copiar código
git checkout v2
Execute a aplicação com Maven:

bash
Copiar código
mvn spring-boot:run
Acesse no navegador:

text
Copiar código
http://localhost:8080/livros
A aplicação deve exibir a página de livros, permitindo listar, cadastrar, editar e excluir registros.

makefile
Copiar código
::contentReference[oaicite:0]{index=0}