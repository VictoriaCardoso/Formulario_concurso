# 📋 Formulário de Aprovados em Concursos

Aplicação **full stack** desenvolvida como teste técnico para vaga de nível **júnior**, com o objetivo de cadastrar e listar pessoas aprovadas em concursos públicos.

O projeto possui um **backend em Java (Spring Boot)** integrado ao **Firebase Firestore** e um **frontend estático (HTML, CSS e JavaScript)** que consome a API REST.

---

## 🚀 Tecnologias Utilizadas

### Backend

* Java 21+
* Spring Boot 4
* Maven
* Firebase Admin SDK
* Firestore (Database)
* Docker (para deploy)

### Frontend

* HTML5
* CSS3
* JavaScript (Fetch API)


---

## ✨ Funcionalidades

* Cadastro de aprovados em concursos
* Campos:

  * Nome
  * E-mail
  * Telefone
  * Concursos aprovados
* Listagem de aprovados cadastrados
* Validação básica no backend
* Integração com Firebase Firestore

---

## 🔗 Endpoints da API

### 📌 Cadastrar aprovado

**POST** `/api/aprovados`

```json
{
  "nome": "Maria Silva",
  "email": "maria@email.com",
  "telefone": "(11) 99999-9999",
  "concursos": "INSS, Banco do Brasil"
}
```

**Resposta:**

```json
{
  "message": "Aprovado cadastrado com sucesso"
}
```

---

### 📌 Listar aprovados

**GET** `/api/aprovados`

**Resposta:**

```json
[
  {
    "nome": "Maria Silva",
    "email": "maria@email.com",
    "telefone": "(11) 99999-9999",
    "concursos": "INSS, Banco do Brasil"
  }
]
```

---

## ⚙️ Configuração do Firebase

O projeto utiliza o **Firebase Firestore** via **Firebase Admin SDK**.

Por segurança, as credenciais não ficam no código. Elas são carregadas por meio da variável de ambiente:

```
FIREBASE_CONFIG
```

Essa variável deve conter **todo o conteúdo do arquivo JSON da Service Account**.

---

## 🐳 Build e Execução Local

### Gerar o JAR

```bash
mvn clean package
```

### Executar localmente

```bash
mvn spring-boot:run
```

A aplicação estará disponível em:

```
http://localhost:8080
```

---

## 👩‍💻 Autora

**Victória**
Desenvolvedora | Programadora

---
