# UniShoes — Loja de Calçados Online

- Curso: Sistemas de Informação
- Ano: 2025
- Turma: Turma: 49 / Vila Prudente - Noturno

# Visão Geral

O UniShoes é uma aplicação web completa desenvolvida em Java 17 com Spring Boot, Thymeleaf e MySQL, voltada ao gerenciamento de uma loja de calçados. <br>
O sistema oferece funcionalidades de cadastro, edição, listagem, exclusão e busca de produtos e categorias, possuindo relacionamentos 1:N entre entidades. <br>
Inclui autenticação e controle de acesso por perfis (USER e ADMIN), além de um upload simples de imagem vinculado aos produtos. <br>
A camada de serviço utiliza DTOs e validação de dados, garantindo consistência no domínio. <br>
A aplicação também expõe uma API REST documentada com Swagger UI (/swagger-ui/index.html#/), permitindo fácil integração e testes dos endpoints. <br>
Com arquitetura em camadas, o projeto segue boas práticas de desenvolvimento e oferece uma interface web intuitiva para o usuário. <br>

# Integrantes

| Felipe Ferreira de Medeiros | 3025105234 | CRUD + Beckend <br>
| Igor Almeida Gimenez | 3024202417 | Frontend + API <br>
| Ricardo Souza Niemoi Dias | 3024106458 | Segurança + Swagger <br>

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.6
- Spring Web
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL
- Springdoc OpenAPI (Swagger UI)
- Bootstrap 5
- jQuery + Inputmask
- Hibernate ORM

# Estrutura e Funcionalidades

# Segurança e Login
- Login via formulário (`/login`)
- Controle de acesso:
- `/app/**` → requer login
- `/admin/**` → apenas ADMIN
- Logout disponível no menu superior

# Entidades
- Usuário (`Usuario`)
- Papel (`Papel`)
- Categoria (`Categoria`)
- Produto (`Produto`)
- Campos: `nome`, `descricao`, `preco`, `categoria`, `imagemUrl`

# Relacionamentos
- `Categoria` 1-N `Produto`
- `Usuario` N️—N️ `Papel`

# Camadas
- Controller (View e API)
- Service
- Repository
- Model

# Funcionalidades principais

| Login e controle de acesso | Usuário e administrador com permissões distintas | <br>
| CRUD de produtos | Criar, listar, editar e excluir produtos | <br>
| CRUD de categorias | Gerenciar categorias com aviso de vínculo | <br>
| Upload de imagem | Upload e exibição de imagens no card do produto | <br>
| Validação visual | Alertas e mensagens com Bootstrap | <br>
| Documentação | Swagger UI acessível em `/swagger-ui/index.html#/` | <br>


# Como Executar o Projeto Localmente

# Pré-requisitos

- JDK 17+
- Maven 3.8+
- MySQL Server rodando localmente (porta padrão '3306'/'3307') / (Utilizei a porta 3307, caso deseja utilizar outra, alterar o número da porta no arquivo: application.properties)
- Git instalado

---

# Passos para executar

1. Clonar o repositório <br>
git clone https://github.com/TheManiin/unishoes.git <br>

2. Configurar o banco de dados <br>

O projeto utiliza o MySQL 8.0+ como banco de dados principal. <br>
Um script SQL completo está incluído no repositório, permitindo restaurar toda a base com estrutura e dados iniciais (usuários, papéis, produtos e categorias).

Arquivo do banco 

O arquivo está localizado em: 

src/main/resources/db/unishoes.sql

Pré-requisitos

MySQL 8.0 ou superior

Porta configurada: 3307

Usuário: root

Senha: admin (caso não funcione, tente: Admin123.)

Caso sua instância utilize outra porta ou senha, ajuste no application.properties.

Importar o banco no Windows

Abra o Prompt de Comando (cmd) e execute:

"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -h 127.0.0.1 -P 3307 -u root -p < C:\workspace\unishoes\src\main\resources\db\unishoes.sql

Quando solicitado, digite a senha do MySQL (admin).

Se tudo ocorrer bem, o comando finalizará sem erros.

Verificando a importação

Abra o MySQL Workbench ou o terminal e execute:

SHOW DATABASES; <br>
USE marketplace_db; <br>
SHOW TABLES; <br>

Você deve ver as tabelas:

categorias <br>
papeis <br>
produtos <br>
usuario <br>
usuario_papel <br>

Configuração no application.properties

O arquivo deve conter:

spring.application.name=unishoes

spring.datasource.url=jdbc:mysql://localhost:3307/marketplace_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC <br>
spring.datasource.username=root <br>
spring.datasource.password=admin <br>

spring.jpa.hibernate.ddl-auto=update <br>
spring.jpa.show-sql=true <br>
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect <br>

Observações

Caso queira usar outra porta, altere o número 3307 no comando e no application.properties.

É possível abrir o projeto no MySQL Workbench e importar o arquivo .sql manualmente (via Server → Data Import).

O arquivo unishoes.sql já inclui os dados iniciais para login e testes.

3. Executar o projeto

mvn spring-boot:run

4. Acessar no navegador

Pagina de Apresentação (index): http://localhost:8080/ <br>
Sistema Web: http://localhost:8080/app/produtos <br>
Login: http://localhost:8080/login <br>
Swagger UI: http://localhost:8080/swagger-ui/index.html#/ <br>

# Usuários de Teste

# Login conta Admin no Sistema Unishoes

Nome: Admin Unishoes <br>
Email: admin@unishoes.com <br>
Senha: admin <br>

# Login conta usuário no Sistema Unishoes

Nome: Usuario Unishoes <br>
Email: usuarioteste@unishoes.com <br>
Senha: usuario1 <br>

# API REST (Swagger)

A API está documentada com Springdoc OpenAPI. <br>
http://localhost:8080/swagger-ui/index.html#/ <br>

Endpoints principais:
Método <br>
	Endpoint <br>
	Descrição <br>
	GET <br>
	/api/produtos <br>
	Lista produtos (paginação/filtro) <br>
	POST <br>
	/api/produtos <br>
	Cria novo produto <br>
	PUT <br>
	/api/produtos/{id} <br>
	Atualiza produto <br>
	DELETE <br>
	/api/produtos/{id} <br>
	Remove produto <br>
	GET <br>
	/api/categorias <br>
	Lista categorias <br>
	POST <br>
	/api/categorias <br>
	Cria categoria <br>


# Repositório e Links
Pagina de Apresentação (index): http://localhost:8080/ <br>
GitHub: https://github.com/TheManiin/unishoes <br>
YouTube: [link do vídeo de demonstração] <br>
Swagger: http://localhost:8080/swagger-ui/index.html#/ <br>


# Autores
Projeto desenvolvido por Felipe Ferreira de Medeiros, Igor Almeida Gimenez e Ricardo Souza Niemoi Dias, como parte da disciplina de Desenvolvimento Web com Spring Boot.
