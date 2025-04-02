# Canoa Caiçara - Sistema Interno

Este é um projeto em desenvolvimento para a Canoa Caiçara, com o objetivo de gerenciar todo o backoffice da empresa.

# Como rodar o projeto
1. Certifique-se de ter Java 17 e Maven instalados em sua máquina (o banco de dados está hospedado online).
2. Clone o repositório do projeto
3. Gere o .jar do projeto com o comando "./mvnw clean package"
4. Pronto, rode o projeto

# Arquitetura do Projeto

O projeto foi estruturado para ser escalável e, ao mesmo tempo, simples de entender e manter. Ele é organizado em domínios (por exemplo: User), e dentro de cada domínio temos as seguintes camadas:

##  Controller
- Responsável por expor os endpoints da API.

- Valida os dados recebidos, chama o Service e retorna a resposta adequada

## Service
- Contém as regras de negócio da aplicação.

- Processa os dados e, quando necessário, aciona o Repository para interagir com o banco de dados.

## Repository
- Responsável pela comunicação com o banco de dados.

- Executa operações como consultas, inserções e atualizações.

## Common e Security
- **Common**: Contém funcionalidades e configurações compartilhadas entre diferentes partes do sistema.

- **Security**: Responsável pela segurança da aplicação, incluindo a implementação do JWT para autenticação.