
# 🏍️ Gestão de Motos no Pátio – API REST

API REST desenvolvida durante a 1ª Sprint da disciplina **Java Advanced** (FIAP) com o objetivo de gerenciar o cadastro de **motos** e **vagas** em um pátio. A aplicação utiliza boas práticas de design REST, Spring Boot, JPA e banco de dados Oracle.

---

## 📚 Descrição do Projeto

O sistema permite:

- 📥 Cadastrar, atualizar, listar e remover motos
- 🅿️ Cadastrar, atualizar, listar e remover vagas
- 🔗 Relacionar uma moto a uma vaga
- 🧠 Impedir alocação de moto em vaga ocupada
- ♻️ Liberar a vaga automaticamente ao remover a moto
- 🔍 Buscar moto por placa e vaga por ID
- 📄 Paginar e ordenar os resultados via query params
- ✅ Validar campos com Bean Validation
- 🛡️ Tratar exceções de forma centralizada com mensagens amigáveis

---

## 👨‍💻 Integrantes

- Raphaela Oliveira Tatto – RM: *554983*
- Tiago Ribeiro Capela – RM: *558021*


---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Oracle Database
- Jakarta Bean Validation
- Lombok
- Postman (para testes)
- IntelliJ IDEA (IDE)

---

## 🚀 Como Executar o Projeto

### ✅ Pré-requisitos

- Java 17 instalado
- Oracle Database configurado e rodando
- IntelliJ IDEA ou VSCode com Maven
- Postman (opcional)

### 📦 Passos

1. **Clone o repositório:**

```bash
git clone https://github.com/seu-usuario/sprint-1-java.git
cd sprint-1-java
```

2. **Configure o banco de dados Oracle:**

Edite o arquivo `src/main/resources/application.properties` com as credenciais do seu banco:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

3. **Compile e rode a aplicação:**

- Pelo IntelliJ: vá até `MainApplication.java`, clique com o botão direito e selecione **Run**.
- Ou pelo terminal:

```bash
./mvnw spring-boot:run
```

4. **Testar os endpoints com Postman:**

Exemplos:
- `POST /motos` — cadastrar moto
- `GET /motos?page=0&size=10&sort=placa,desc` — listar motos com paginação e ordenação
- `DELETE /motos/{id}` — deletar moto
- `POST /vagas` — cadastrar vaga
- `GET /vagas` — listar vagas

---

## ✅ Requisitos Atendidos

- [x] Spring Web para criação da API
- [x] Spring Data JPA com Oracle
- [x] CRUD completo para motos e vagas
- [x] Relacionamento entre entidades
- [x] Bean Validation com mensagens claras
- [x] Paginação e ordenação com Spring Data
- [x] Filtros por parâmetros
- [x] Tratamento global de exceções com `@RestControllerAdvice`
- [x] Uso de DTOs para segurança e organização

---

## 📂 Organização do Projeto

```
src/
├── controller/
│   ├── MotoController.java
│   └── VagaController.java
├── dto/
│   ├── MotoDTO.java
│   ├── MotoCreateDTO.java
│   ├── VagaDTO.java
│   └── VagaCreateDTO.java
├── exception/
│   ├── BusinessException.java
│   └── GlobalExceptionHandler.java
├── model/
│   ├── Moto.java
│   ├── Vaga.java
│   ├── StatusMoto.java
│   └── StatusVaga.java
├── repository/
│   ├── MotoRepository.java
│   └── VagaRepository.java
├── service/
│   ├── MotoService.java
│   └── VagaService.java
└── MainApplication.java
```

---

## 📎 Observações

- Aplicação preparada para ambiente local.
- Pode ser facilmente adaptada para bancos como PostgreSQL ou MySQL.
- Os testes podem ser automatizados com JUnit e MockMvc futuramente.

---

## 📥 Download

- [📥 Baixar coleção de testes do Postman](https://github.com/raphatatto/sprint-1-java/blob/master/Sprint1-Mottu-Postman-Collection.json)

