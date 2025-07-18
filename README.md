# 📖 SAGA - Sistema de Gerenciamento de Histórias

**SAGA** é um sistema Fullstack criado como parte de um projeto universitário com o objetivo de desenvolver um CRUD completo usando **Angular** no front-end e **Spring Boot** no back-end.

Nosso grupo optou por desenvolver algo criativo e divertido: uma plataforma onde os usuários podem adicionar suas próprias histórias ou qualquer outra criação narrativa, com capa(imagem), além de visualizar, editar e deletar as histórias salvas.

---

## 🎯 Objetivo

O desafio do projeto era criar um sistema funcional de cadastro com operações de:

- Criar (add)
- Visualizar (list)
- Editar (update)
- Deletar (delete)

Além disso, implementamos uma **validação de CPF**, e o **login** do usuário é feito utilizando **CPF e senha**.

---

## 🔧 Tecnologias Utilizadas

### Front-end
- Angular
- HTML / SCSS
- Angular Router

### Back-end
- Spring Boot
- Maven
- Java 17+
- H2 Database (em memória)
- JPA / Hibernate
- Validação de CPF

### Testes
- Postman (para testar as APIs)

---

## ✅ Funcionalidades

- 🔐 Login com CPF e senha
- 🧾 Cadastro de novas histórias com título, conteúdo e imagem de capa
- 📚 Listagem de todas as histórias cadastradas
- ✏️ Edição de histórias existentes
- ❌ Exclusão de histórias
- 🔍 Verificação de CPF válido no momento do cadastro
- 🎨 Interface divertida e intuitiva

---

## 🚀 Como Rodar o Projeto

### ⚙️ Pré-requisitos

- Node.js e npm instalados
- Java 17+ e Maven instalados

---

### 🔙 Back-end - Spring Boot

1. Pasta do backend:

```bash
cd Saga_back
```

2. Instale as dependências e rode a aplicação:

```bash
./mvnw spring-boot:run
```

> A aplicação será executada por padrão em:  
> `http://localhost:8080`
---

## 👤 Acesso ao Sistema

Para acessar o sistema, o usuário deve estar previamente cadastrado com CPF válido. Caso deseje, cadastre um novo usuário para teste.

---

## 💡 Sobre o Projeto

O **SAGA** foi idealizado para ser uma ferramenta leve, divertida e criativa. Ao invés de um CRUD tradicional de cadastro de clientes ou produtos, optamos por algo diferente: uma "biblioteca digital" onde o usuário pode gerenciar suas próprias histórias, como se fossem livros personalizados com título, conteúdo e capa.

A ideia é unir aprendizado técnico com criatividade e usabilidade.

---

## 👩‍💻 Desenvolvido por

Pearl Nyarko e equipe  
Projeto Universitário - Projeto Integrador com Angular + Spring Boot + H2

---
