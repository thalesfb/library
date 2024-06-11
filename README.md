## Projeto: Sistema de Gerenciamento de Biblioteca

### Descrição

Este projeto consiste em um sistema de gerenciamento de biblioteca desenvolvido em Java utilizando o framework Spring. O sistema permite gerenciar autores, livros e empréstimos, além de notificar usuários sobre a expiração de empréstimos através de um mecanismo de observador.

### Tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada para o desenvolvimento do backend.
- **Spring Boot**: Framework para simplificar a configuração e o desenvolvimento do aplicativo.
- **Spring Data JPA**: Abstração de persistência para facilitar o acesso ao banco de dados.
- **Hibernate**: Implementação do JPA utilizada para o mapeamento objeto-relacional.
- **MySQL**: Banco de dados relacional utilizado para armazenar os dados.
- **Spring Mail**: Módulo utilizado para envio de emails de notificação.
- **Maven**: Ferramenta de gerenciamento de dependências e construção do projeto.

### Estrutura do Projeto

- **config**: Contém classes de configuração do Spring.
- **controllers**: Contém classes de controle que lidam com as requisições HTTP.
- **entity**: Contém as classes de modelo (entidades) mapeadas para o banco de dados.
- **repositories**: Contém interfaces que estendem `JpaRepository` para acesso aos dados.
- **service**: Contém classes de serviço que implementam a lógica de negócio.
- **observer**: Implementa o padrão de projeto Observer para notificação de usuários.
- **factory**: Contém fábricas para criação de observadores.
- **utils**: Contém utilitários, incluindo envio de emails.

### Funcionalidades

- **Cadastro de Autores**: Permite o registro, busca e remoção de autores.
- **Cadastro de Livros**: Permite o registro, busca (por título e ISBN) e remoção de livros.
- **Gerenciamento de Empréstimos**: Permite o registro, busca e remoção de empréstimos.
- **Notificações**: Utiliza o padrão Observer para notificar usuários sobre a data de expiração dos empréstimos via email.

### Como Executar

1. **Clone o repositório**:
    ```bash
    git clone https://github.com/thalesfb/library.git
    ```

2. **Navegue até o diretório do projeto**:
    ```bash
    cd library
    ```

3. **Configure o banco de dados**:
    - Certifique-se de que o MySQL está instalado e rodando.
    - Crie um banco de dados chamado `library`.
    - Atualize o arquivo `application.properties` com suas credenciais do MySQL.

4. **Construa e execute o projeto**:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

### Contribuição

Sinta-se à vontade para contribuir com o projeto. Siga os passos abaixo para enviar suas contribuições:

1. **Fork o repositório**.
2. **Crie uma nova branch**:
    ```bash
    git checkout -b minha-feature
    ```
3. **Faça as modificações e commit**:
    ```bash
    git commit -m "Adiciona minha nova feature"
    ```
4. **Envie para sua branch**:
    ```bash
    git push origin minha-feature
    ```
5. **Abra um Pull Request** no GitHub.

### Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

### Contato

- **Autores**: Thales Ferreira Batista e Vanderlei Slongo
- **Email**: thales_fb2014@hotmail.com
- **LinkedIn**: [Thales Ferreira](https://www.linkedin.com/in/thalesfb96/)
