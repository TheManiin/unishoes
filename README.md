# 👟 UniShoes — Loja de Calçados Online


**Ano:** 2025  
**Curso:** [Seu curso aqui]  
**Turma:** [Turma / Período]  


---


## 🎯 Visão Geral


O **UniShoes** é uma aplicação web completa desenvolvida com **Java 17 + Spring Boot + Thymeleaf + MySQL**, que simula o gerenciamento de uma loja de calçados.


O sistema permite **cadastro, edição, listagem e exclusão de produtos e categorias**, com upload de imagem, autenticação de usuários e controle de acesso por perfil (`USER` e `ADMIN`).  
Inclui também **API REST documentada via Swagger UI**.


---


## 👥 Integrantes


| Nome completo | RA |
|----------------|----|
| Felipe Ferreira Gregio | [seu RA aqui] |
| [Demais integrantes, se houver] | [RA] |


---


## ⚙️ Tecnologias Utilizadas


- **Java 17**
- **Spring Boot 3.5.6**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **Thymeleaf**
- **MySQL**
- **Springdoc OpenAPI (Swagger UI)**
- **Bootstrap 5**
- **jQuery + Inputmask**
- **Hibernate ORM**


---


## 🧩 Estrutura e Funcionalidades


### 🔐 Segurança e Login
- Login via formulário (`/login`)
- Controle de acesso:
  - `/app/**` → requer login
  - `/admin/**` → apenas ADMIN
- Logout disponível no menu superior


### 🧾 Entidades
- **Usuário (`Usuario`)**
- **Papel (`Papel`)**
- **Categoria (`Categoria`)**
- **Produto (`Produto`)**
  - Campos: `nome`, `descricao`, `preco`, `categoria`, `imagemUrl`


### 📂 Relacionamentos
- `Categoria` 1️⃣—N️⃣ `Produto`
- `Usuario` N️⃣—N️⃣ `Papel`


### 🧠 Camadas
- **Controller (View e API)**
- **Service**
- **Repository**
- **Model**


---


## 🧠 Funcionalidades principais


| Funcionalidade | Descrição |
|----------------|------------|
| 🧍‍♂️ Login e controle de acesso | Usuário e administrador com permissões distintas |
| 📦 CRUD de produtos | Criar, listar, editar e excluir produtos |
| 🗂️ CRUD de categorias | Gerenciar categorias com aviso de vínculo |
| 🖼️ Upload de imagem | Upload e exibição de imagens no card do produto |
| 💬 Validação visual | Alertas e mensagens com Bootstrap |
| 📜 Documentação | Swagger UI acessível em `/swagger-ui/index.html#/` |


---


## ⚙️ Como Executar o Projeto Localmente


### 📌 Pré-requisitos


- **JDK 17+**
- **Maven 3.8+**
- **MySQL Server** rodando localmente (porta padrão ‘3306’/`3307`)
- **Git** instalado


---


### 🗂️ Passos para executar


1. **Clonar o repositório**
   ```bash
   git clone https://github.com/<seu-usuario>/unishoes.git
   cd unishoes


2. Configurar o banco de dados

Crie um schema no MySQL chamado unishoes:

CREATE DATABASE unishoes CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
   * Edite o arquivo src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/unishoes?useSSL=false&serverTimezone=UTC
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha


spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
   * Executar o projeto

 mvn spring-boot:run
   3.    4. Acessar no navegador

      * Sistema Web: http://localhost:8080/app/produtos

      * Login: http://localhost:8080/login

      * Swagger UI: http://localhost:8080/swagger-ui/index.html#/

________________


👤 Usuários de Teste
Perfil
	Email
	Senha
	Acesso
	ADMIN
	admin@unishoes.com
	123456
	CRUD completo e painel admin
	USER
	user@unishoes.com
	123456
	Consulta de produtos
	(Se preferir, cadastre novos usuários via SQL ou API Swagger.)
________________


🌐 API REST (Swagger)
A API está documentada com Springdoc OpenAPI.
🔍 Acesso:
         * http://localhost:8080/swagger-ui/index.html#/

Endpoints principais:
Método
	Endpoint
	Descrição
	GET
	/api/produtos
	Lista produtos (paginação/filtro)
	POST
	/api/produtos
	Cria novo produto
	PUT
	/api/produtos/{id}
	Atualiza produto
	DELETE
	/api/produtos/{id}
	Remove produto
	GET
	/api/categorias
	Lista categorias
	POST
	/api/categorias
	Cria categoria
	________________


💡 Como rodar testes e pipeline
Para rodar os testes automatizados:
mvn test


Para configurar o GitHub Actions, crie o arquivo .github/workflows/maven.yml com o conteúdo:
name: Java CI with Maven


on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]


jobs:
  build:
    runs-on: ubuntu-latest


    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
    - name: Build with Maven
      run: mvn -B package --file pom.xml


### 🧱 Script SQL para criação do banco e dados iniciais


Caso queira criar tudo manualmente (estrutura + dados iniciais), execute o seguinte script no seu MySQL antes de rodar o sistema.


Você também pode salvar esse conteúdo em um arquivo chamado `database.sql` na raiz do projeto e executá-lo via terminal:


```bash
mysql -u seu_usuario -p < database.sql

-- ==========================================
-- BANCO DE DADOS UNISHOES
-- ==========================================
CREATE DATABASE IF NOT EXISTS unishoes CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE unishoes;


-- ==========================================
-- TABELAS BÁSICAS
-- ==========================================


CREATE TABLE IF NOT EXISTS papel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    role VARCHAR(50)
);


CREATE TABLE IF NOT EXISTS usuario_papel (
    usuario_id BIGINT,
    papel_id BIGINT,
    PRIMARY KEY (usuario_id, papel_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (papel_id) REFERENCES papel(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS categorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL,
    imagem_url VARCHAR(255),
    categoria_id BIGINT,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id) ON DELETE SET NULL
);


-- ==========================================
-- DADOS INICIAIS
-- ==========================================
INSERT INTO papel (nome) VALUES ('ROLE_ADMIN'), ('ROLE_USER')
ON DUPLICATE KEY UPDATE nome = VALUES(nome);


-- Usuário administrador
INSERT INTO usuario (nome, email, senha, role)
VALUES ('Administrador', 'admin@unishoes.com', '$2a$10$84IUs9lQoQ1mHb4iDiuvxOS5IzfMPQzAl.p7gCxn79CKYVZo3k0ly', 'ADMIN')
ON DUPLICATE KEY UPDATE email = email;


-- Usuário comum
INSERT INTO usuario (nome, email, senha, role)
VALUES ('Usuário', 'user@unishoes.com', '$2a$10$84IUs9lQoQ1mHb4iDiuvxOS5IzfMPQzAl.p7gCxn79CKYVZo3k0ly', 'USER')
ON DUPLICATE KEY UPDATE email = email;


-- Vinculação de papéis
INSERT IGNORE INTO usuario_papel (usuario_id, papel_id)
SELECT u.id, p.id FROM usuario u, papel p
WHERE (u.email = 'admin@unishoes.com' AND p.nome = 'ROLE_ADMIN')
   OR (u.email = 'user@unishoes.com' AND p.nome = 'ROLE_USER');


-- Categoria padrão
INSERT INTO categorias (nome) VALUES ('Casual'), ('Esportivo'), ('Social')
ON DUPLICATE KEY UPDATE nome = VALUES(nome);


-- Produto exemplo
INSERT INTO produtos (nome, descricao, preco, imagem_url, categoria_id)
VALUES ('Tênis Exemplo', 'Modelo padrão para testes', 199.90, '/uploads/exemplo.jpg',
        (SELECT id FROM categorias WHERE nome = 'Casual' LIMIT 1));


💡 Observações
            * As senhas estão criptografadas com BCrypt (senha original: 123456);

            * O sistema faz hibernate.ddl-auto=update, então o banco será atualizado automaticamente se houver ajustes de estrutura;

            * O script acima serve apenas para inicializar com dados e permissões básicas.

________________


📦 Estrutura de diretórios sugerida
Depois disso, a raiz do projeto ficará assim:
unishoes/
│
├── src/
│   ├── main/
│   │   ├── java/com/unishoes/...
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│
├── uploads/             # imagens enviadas
├── database.sql         # <-- novo arquivo para inicialização do banco
├── pom.xml
└── README.md


________________


🚀 Sobre subir no GitHub com o script incluído
Depois que você criar o database.sql, rode:
git add database.sql README.md
git commit -m "Adiciona script SQL de criação e dados iniciais do UniShoes"
git push


Assim o repositório ficará autoexplicativo e reproduzível:
qualquer pessoa poderá clonar, rodar o script, executar mvn spring-boot:run, e ver o sistema completo com login e dados prontos.




________________


📦 Repositório e Links
               * GitHub: https://github.com/<seu-usuario>/unishoes

               * YouTube: [link do vídeo de demonstração]

               * Swagger: http://localhost:8080/swagger-ui/index.html#/

________________


🧠 Autores
Projeto desenvolvido por Felipe Ferreira Gregio e equipe, como parte da disciplina de Desenvolvimento Web com Spring Boot.


---


## 🧩 Onde incluir o Swagger (caso precise deixar explícito no código)


No seu `pom.xml`, já deve existir algo como:


```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.6.0</version>
</dependency>


E, opcionalmente, você pode criar uma classe de configuração SwaggerConfig (não obrigatória, mas deixa mais formal):
package com.unishoes.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI unishoesOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("UniShoes API")
                        .description("Documentação dos endpoints da aplicação UniShoes")
                        .version("1.0.0"));
    }
}


________________


🚀 Como subir no GitHub da melhor forma
Verifica se o .git já existe

 git status
                  1. Se não existir:

git init
git remote add origin https://github.com/<seu-usuario>/unishoes.git
                  * Adiciona todos os arquivos e o README

 git add .
                  2. Commit das alterações

 git commit -m "Adiciona README completo e configurações finais"
                  3. Envia para o GitHub

 git branch -M main
                  4. git push -u origin main
