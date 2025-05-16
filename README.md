# Search4green API 🌱

## Sobre o Projeto

Bem-vindos ao **Search4green**, sua API RESTful em Java (Spring Boot) para gerenciar estabelecimentos sustentáveis com telhados verdes e paredes vivas! Aqui você pode:

* Cadastrar e atualizar parcialmente informações (nome, telefone, descrição, tipo)
* Excluir registros sem dor de cabeça
* Buscar por nome, tipo e cidade
* Associar e gerenciar **endereços** e **imagens** como sub-recursos

Este trabalho de faculdade tem o objetivo de criar um backend simples, didático e funcional para a comunidade verde. 💚

---

## 🛠 Tecnologias

* **Java 17+**
* **Spring Boot 3.x**
* **Spring Data JPA (Hibernate)**
* **MySQL**
* **Bean Validation** (Jakarta Validation)
* **Lombok**

---

## ⚙️ Pré-requisitos

Antes de rodar, certifique-se de ter:

* Java JDK 17 ou superior instalado
* MySQL Server rodando
* Maven (wrapper incluso no projeto)
* Variáveis de ambiente configuradas:

  * `DB_USER` (opcional, padrão: `root`)
  * `DB_PASSWORD` (senha do MySQL)

---

## 🏗️ Configuração

1. **Clone** o repositório:

   ```bash
   git clone <URL_DO_REPOSITÓRIO>
   cd search4green
   ```

2. **Defina** as variáveis de ambiente:

   * **Windows (PowerShell)**:

     ```powershell
     setx DB_USER "root"
     setx DB_PASSWORD "SuaSenha"
     ```
   * **Linux/macOS**:

     ```bash
     export DB_USER="root"
     export DB_PASSWORD="SuaSenha"
     ```

3. **Confira** `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/search4green_db
   spring.datasource.username=${DB_USER:root}
   spring.datasource.password=${DB_PASSWORD}
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

---

## ▶️ Como Executar

* **Via Maven Wrapper**:

  ```bash
  ./mvnw spring-boot:run
  ```
* **Ou** abra a classe `Search4greenApplication` na sua IDE e execute como Java Application.

A API estará disponível em `http://localhost:8080` — bora testar! 🎉

---

## 🚨 Endpoints Principais

### Estabelecimentos

| Método     | Rota                           | Descrição                                                             |
| ---------- | ------------------------------ | --------------------------------------------------------------------- |
| **POST**   | `/api/estabelecimentos`        | Cria um novo estabelecimento<br>⚠️ Validação obrigatória com `@Valid` |
| **PATCH**  | `/api/estabelecimentos/{id}`   | Atualiza apenas os campos informados                                  |
| **DELETE** | `/api/estabelecimentos/{id}`   | Remove um estabelecimento                                             |
| **GET**    | `/api/estabelecimentos/search` | Busca genérica por `nome`, `tipo` e/ou `cidade`                       |
| **GET**    | `/api/estabelecimentos/{id}`   | Recupera um estabelecimento por ID                                    |

### Sub-recursos

#### Endereço

| Método     | Rota                                  | Descrição                            |
| ---------- | ------------------------------------- | ------------------------------------ |
| **POST**   | `/api/estabelecimentos/{id}/endereco` | Adiciona um endereço (com validação) |
| **PATCH**  | `/api/estabelecimentos/{id}/endereco` | Atualiza parcialmente o endereço     |
| **DELETE** | `/api/estabelecimentos/{id}/endereco` | Remove o endereço associado          |

#### Imagens

| Método     | Rota                                         | Descrição                        |
| ---------- | -------------------------------------------- | -------------------------------- |
| **GET**    | `/api/estabelecimentos/{id}/imagens`         | Lista todas as imagens           |
| **POST**   | `/api/estabelecimentos/{id}/imagens`         | Adiciona uma nova imagem         |
| **DELETE** | `/api/estabelecimentos/{id}/imagens/{imgId}` | Remove imagem do estabelecimento |

---

## 📂 Estrutura de Pastas

```
src/
├── main/
│   ├── java/com/api/search4green/
│   │   ├── controller/      ← Endpoints REST
│   │   ├── service/         ← Lógica de negócio
│   │   ├── repository/      ← Interfaces JPA
│   │   ├── model/           ← Entidades (JPA + validações)
│   │   ├── exception/       ← Tratamento global de erros
│   │   └── Search4greenApplication.java ← Aplicação Spring Boot
│   └── resources/
│       └── application.properties ← Configuração de conexão
└── test/                    ← Testes automáticos (futuros)
```

---

## Possíveis Implementações Futuras

* Swagger/OpenAPI para documentação interativa
* Paginação e ordenação de resultados
* Testes automatizados (unitários e de integração)
* Segurança com Spring Security (Autenticação/Autorização)
* Dockerização (API + MySQL)<br>(`docker-compose.yml`)
* Front-end simples em React ou Angular

---

> Criado pelo time **Cipher Deffect** 👾 | Projeto da disciplina de UPX da Facens
