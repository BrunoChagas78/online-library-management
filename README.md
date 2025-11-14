1. Introdução

Este documento apresenta a especificação revisada e atualizada do sistema Online Library Management, desenvolvida na disciplina Prática Profissional em ADS.

O objetivo do sistema é permitir o gerenciamento simples de livros de uma biblioteca, possibilitando o cadastro, consulta, atualização e exclusão de registros por meio de uma interface web.

URL de acesso a aplicação online: https://online-library-management-iz1c.onrender.com/livros

2. Objetivos da Iteração 2

A Iteração 2 tem como objetivos principais:

Implementar a funcionalidade de gerenciamento de livros, contemplando:
Listagem paginada de livros;
Busca por título ou autor;
Cadastro de novos livros;
Edição de livros existentes;
Exclusão de livros.
Adequar o código-fonte ao design definido na fase de análise (casos de uso, modelo de classes, protótipos de tela).
Organizar o projeto em camadas (domínio, repositório, controlador, visão).
Configurar o repositório de controle de versão com tags e branches para acompanhar as iterações de construção.
3. Escopo da Iteração 2

No contexto do sistema como um todo, a Iteração 2 contempla o caso de uso:

UC01 – Gerenciar Livros
Inclui os seguintes fluxos:

UC01.1 – Listar livros
UC01.2 – Pesquisar livros por título/autor
UC01.3 – Cadastrar novo livro
UC01.4 – Editar livro existente
UC01.5 – Excluir livro
Funcionalidades como gerenciamento de usuários, empréstimos, reservas e relatórios ficam fora do escopo desta iteração.

4. Requisitos Funcionais

RF01 – Listar livros
O sistema deve exibir uma lista de livros cadastrados, com título, autor e status.

RF02 – Paginar resultados
A listagem de livros deve ser apresentada de forma paginada, permitindo navegação entre páginas.

RF03 – Pesquisar livros por título/autor
O sistema deve permitir a pesquisa de livros a partir de um campo de busca, filtrando por título e/ou autor.

RF04 – Cadastrar novo livro
O sistema deve disponibilizar um formulário para inclusão de um novo livro, contendo pelo menos título e autor.

RF05 – Editar livro
O sistema deve permitir a alteração dos dados de um livro já cadastrado.

RF06 – Excluir livro
O sistema deve possibilitar a exclusão de um livro selecionado na listagem.

RF07 – Mensagens de feedback
O sistema deve exibir mensagens de sucesso ou erro após as operações de cadastro, edição e exclusão.

5. Requisitos Não Funcionais

RNF01 – Tecnologia
O sistema deve ser implementado em Java, utilizando o framework Spring Boot e o mecanismo de templates Thymeleaf.

RNF02 – Banco de dados
Os dados devem ser persistidos em um banco relacional PostgreSQL (ou equivalente definido pelo grupo).

RNF03 – Usabilidade
A interface deve ser simples e intuitiva, com botões e rótulos claros (ex.: “Novo”, “Editar”, “Excluir”).

RNF04 – Manutenibilidade
O código deve ser estruturado em camadas e pacotes coerentes, facilitando a evolução do projeto em iterações futuras.

RNF05 – Deploy
A aplicação deve poder ser empacotada em um arquivo JAR e, opcionalmente, executada em um ambiente de container (Docker).

6. Modelo de Domínio (Resumo)

Entidade principal desta iteração:

Livro
id (identificador único)
titulo (texto obrigatório)
autor (texto obrigatório)
status (ex.: “ATIVO”)
O relacionamento com outras entidades (Usuário, Empréstimo, etc.) não é contemplado nesta iteração.

7. Visão de Arquitetura e Mapeamento Design → Código

A arquitetura adotada segue o padrão MVC simplificado:

Camada de domínio (Model/Entity)

Classe Livro (representa a entidade de domínio).
Camada de persistência (Repository)

Interface LivroRepository, responsável pelas operações de acesso ao banco de dados (extensão de JpaRepository).
Camada de negócios (Service) (se utilizada)

Classe LivroService, encapsulando regras de negócio relacionadas aos livros.
Camada de apresentação (Controller + View)

LivroController: gerencia as requisições HTTP relacionadas a /livros.
Templates Thymeleaf:
livros.html – listagem, busca e ações;
form-livro.html – formulário de cadastro/edição;
_layout.html – layout base reaproveitado.
Esse mapeamento garante a consistência entre o modelo conceitual produzido na fase de análise (casos de uso e diagramas de classes) e a implementação Java.

8. Interface de Usuário (Resumo)

As principais telas implementadas na Iteração 2 são:

Tela de Listagem de Livros

Campos: título, autor, status;
Ações: “Novo”, “Editar”, “Excluir”;
Campo de busca para filtro por título/autor;
Controles de paginação.
Tela de Cadastro/Edição de Livro

Campos: título (obrigatório), autor (obrigatório);
Botões: “Salvar” e “Voltar”;
Exibição de mensagens de erro de validação quando houver dados inválidos.
9. Controle de Versão

O projeto utiliza Git e GitHub como repositório remoto.

Branch principal: main.
Branch de desenvolvimento da Iteração 2: iteracao2, criado a partir de main para implementação das funcionalidades desta fase.
Tags:
v1 – código correspondente à Iteração 1;
v2 – código correspondente à Iteração 2.
