# Desafio: Avaliação de Candidato a Desenvolvedor Java

## Introdução

Este documento descreve a implementação de uma funcionalidade para o sistema de avaliação de pagamentos de vendedores. A
funcionalidade recebe um objeto contendo o código do vendedor e uma lista de pagamentos realizados, validando a
existência do vendedor e das cobranças na base de dados. O sistema também categoriza os pagamentos como parciais, totais
ou excedentes e processa as informações enviando-as para filas do SQS (Simple Queue Service).

## Objetivos

- Validar dados de vendedor e pagamentos.
- Enviar dados para filas SQS com base na categoria de pagamento.
- Retornar o status do pagamento processado.

## Requisitos Funcionais

1. **Receber Objeto**:
    - O sistema deve receber um objeto que contenha o código do vendedor e uma lista de pagamentos.

2. **Validar Existência do Vendedor**:
    - Verificar se o vendedor informado existe na base de dados. Se não existir, retornar uma mensagem de erro.

3. **Validar Existência do Código da Cobrança**:
    - Para cada pagamento na lista, o sistema deve verificar se o código da cobrança existe na base de dados. Caso
      contrário, retornar uma mensagem de erro.

4. **Validar Valor do Pagamento**:
    - Comparar o valor do pagamento recebido com o valor original cobrado, determinando se o pagamento é parcial, total
      ou excedente.

5. **Enviar Objeto para Fila SQS**:
    - Enviar o objeto para uma fila SQS distinta com base na situação do pagamento.

6. **Preencher Status de Pagamento**:
    - Após o processamento, o sistema deve preencher o status de pagamento no objeto recebido, indicando se foi parcial,
      total ou excedente.

## Requisitos Não Funcionais

1. **Testes Unitários**:
    - O sistema deve ser testável por meio de testes unitários para garantir a funcionalidade e a qualidade do código.

2. **Tratamento de Resposta e Status Code**:
    - O sistema deve retornar um código de status 200 em caso de sucesso e códigos de status 4XX em caso de erro.

## Como Executar

Para executar o projeto, siga os passos abaixo:

1. Clone este repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <NOME_DO_DIRETORIO>

## Configuração

## Variáveis de Ambiente

Para que o sistema funcione corretamente, é necessário configurar as seguintes variáveis de ambiente com os tokens
enviados por email:

### Linux e macOS

Para definir as variáveis de ambiente no terminal, você pode usar os seguintes comandos:

```bash
export AWS_ACCESS_KEY=TOKEN
export AWS_SECRET_KEY=TOKEN
```

### Docker

1. Construa e inicie os containers Docker (incluindo a aplicação):
    ```sh
    docker-compose up --build
    ```
