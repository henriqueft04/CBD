# Apontamentos Aulas Práticas CBD

## Redis Guide

+ Iniciar Server:
  + ```Redis-cli```
+ Aplicar inserção com file
  + cat ``` file.txt | redis-cli --pipe ```
+ Salvar histórico do Reds
  +  ```nano .rediscli_history ```

## MongoDB guide

+ Iniciar MongoDB
  + ```sudo systemctl start mongod```
+ Verificar que está a dar run corretamente
  + ```sudo systemctl status mongod```
+ Parar o Mongo
  + ```sudo systemctl stop mongod```
+ Dar Restart ao Mongo
  + ```sudo systemctl restart mongod```
+ Começar a usar MongoDB
  + ```mongosh```


### Cheat Sheet
Documento:  
-unidade fundamental de data
-essencialmente ao equivalente a uma row numa bd relacional
+ **Métodos**
  + find()
  + aggregate()
    + Usado para operações complexas, *grouping* & *counting*
    + Tem estados
    + `$match`
      + essenciamente o find
        `{$match: {gastronomia: "Portuguese", "address.coord.0": {$lt: -60}}}`, 
    + `$project`
      + é o que é
    + `$unwind`
      + deconstrói um campo em array em vários documentos
    + `$group` 
      + junta documentos e permite aplicar operações neles 
      +  `$sum` -> argumentos são um campo, que vai somar todos os valores desse campo, ou um valor constante, usado para somar o número de documentos no grupo
      +   `$lookup` (for joins)
  + distinct()
  + countDocuments(filtro)
  
+ **Funçoes**
  + .count()
  + .sort(filtro)
    + no sort podemos ter um sort por vários parâmetros
    + 1 , ordem ascendente
    + -1 , ordem descendente
  + .lenght()
+ **Filtros**
  + ```$eq```
  equals
  + ```$gt```
  greater than
  + ```$gte```
  greater than or equals
  + ```$in```
  encontra qualquer um dos items especificados no array
  sintax:
  ```{key:{$in: [array of values] } }```
  + ```$lt```
  less than
  + ```$lte```
  less than or equals
  + ```$ne```
  encontra os items que nao sao iguais aos específicados
  + ```$nin```
  é o oposto do in lmao
+ **Operadores Lógicos**
  + ```$and```
  faz ands lool
  syntax:
  ```{$and: [ {},{} ] }```
  + ```$nor```:
	all AOE expres­sions must fail
  + ```$not:Expr```
  Negate a SubDoc­ument (doesn't work with $regex)
  + $```or:AOE```
  OR operation between all AOE expres­sions


## Cassandra Guide

+ Pull the docker image. For the latest image, use:

  `docker pull cassandra:latest`

+ Start Cassandra with a docker run command:

  `docker network create cassandra `
  `docker run --rm -d --name cassandra --hostname cassandra --network cassandra cassandra`

  + The --name option will be the name of the Cassandra cluster created.


+ Start the CQL shell, cqlsh to interact with the Cassandra node created:

  `docker exec -it cass_cluster cqlsh`

+ Load the data into the Cassandra cluster:

  `docker exec -it cass_cluster cqlsh -f /path/to/your/cql/file.cql` <br></br>
  `docker run --rm --network cassandra \-v "/home/ric/Desktop/CBD/Práticas/Lab-3/data.cql:/scripts/data.cql" \cassandra cqlsh cassandra 9042 -f /scripts/data.cql -k store
`
+ Stop the Cassandra cluster:

  `docker stop cass_cluster`

+ Start the Cassandra cluster:

  `docker start cassandra_cbd`

+ Remove the Cassandra cluster:

  `docker rm cass_cluster`

+ Get the IP address of the Cassandra cluster:

  `sudo docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' cass_cluster`

### Cassandra (CQL) Cheat Sheet

#### Keyspace Operations
- **Create Keyspace**
  - Cria uma keyspace se não existir
  - **Sintaxe:**
    ```cql
    CREATE KEYSPACE IF NOT EXISTS keyspace_name WITH REPLICATION = 
    { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };
    ```
  - **Parâmetros:**
    - `class`: Estratégia de replicação (e.g., `SimpleStrategy`)
    - `replication_factor`: Número de réplicas

- **Use Keyspace**
  - Define a keyspace a ser usada nas operações subsequentes
  - **Sintaxe:**
    ```cql
    USE keyspace_name;
    ```

- **List All Keyspaces**
  - Lista todas as keyspaces existentes
  - **Sintaxe:**
    ```cql
    DESCRIBE KEYSPACES;
    ```

- **Drop Keyspace**
  - Remove uma keyspace existente
  - **Sintaxe:**
    ```cql
    DROP KEYSPACE keyspace_name;
    ```

---

#### Table Operations
- **Create Table**
  - Cria uma tabela dentro de uma keyspace
  - **Sintaxe:**
    ```cql
    CREATE TABLE IF NOT EXISTS keyspace_name.table_name (
        id UUID PRIMARY KEY,
        column1 TEXT,
        column2 INT
    );
    ```
  - **Componentes:**
    - `PRIMARY KEY`: Define a chave primária
    - `UUID`, `TEXT`, `INT`: Tipos de dados

- **List All Tables in Keyspace**
  - Lista todas as tabelas dentro da keyspace atual
  - **Sintaxe:**
    ```cql
    DESCRIBE TABLES;
    ```

- **Describe Table Structure**
  - Exibe a estrutura detalhada de uma tabela
  - **Sintaxe:**
    ```cql
    DESCRIBE TABLE keyspace_name.table_name;
    ```

- **Drop Table**
  - Remove uma tabela existente
  - **Sintaxe:**
    ```cql
    DROP TABLE keyspace_name.table_name;
    ```

---

#### Data Manipulation (CRUD Operations)
- **Insert Data**
  - Insere uma nova linha na tabela
  - **Sintaxe:**
    ```cql
    INSERT INTO keyspace_name.table_name (id, column1, column2)
    VALUES (uuid(), 'value1', 123);
    ```

- **Select Data**
  - **Select All Rows:**
    - Seleciona todas as linhas da tabela
    - **Sintaxe:**
      ```cql
      SELECT * FROM keyspace_name.table_name;
      ```

  - **Select Specific Columns:**
    - Seleciona colunas específicas
    - **Sintaxe:**
      ```cql
      SELECT column1, column2 FROM keyspace_name.table_name;
      ```

  - **Conditional Select:**
    - Seleciona linhas com condição
    - **Sintaxe:**
      ```cql
      SELECT * FROM keyspace_name.table_name WHERE column1 = 'value1';
      ```

- **Update Data**
  - Atualiza dados existentes
  - **Sintaxe:**
    ```cql
    UPDATE keyspace_name.table_name
    SET column2 = 456
    WHERE id = uuid_value;
    ```

- **Delete Data**
  - **Delete Specific Row:**
    - Remove uma linha específica
    - **Sintaxe:**
      ```cql
      DELETE FROM keyspace_name.table_name WHERE id = uuid_value;
      ```

  - **Delete Specific Column from Row:**
    - Remove um valor específico de uma coluna na linha
    - **Sintaxe:**
      ```cql
      DELETE column1 FROM keyspace_name.table_name WHERE id = uuid_value;
      ```

---

#### Functions and Data Types
- **Common Data Types**
  - `TEXT`: Dados de texto
  - `INT`: Inteiro
  - `UUID`: Identificador Único Universal
  - `TIMESTAMP`: Data e hora

- **Common Functions**
  - **Generate UUID:**
    - Gera um UUID único
    - **Sintaxe:**
      ```cql
      uuid()
      ```

  - **Current Timestamp:**
    - Obtém o timestamp atual
    - **Sintaxe:**
      ```cql
      toTimestamp(now())
      ```

---

#### Aggregation
- **Count Rows**
  - Conta o número de linhas na tabela
  - **Sintaxe:**
    ```cql
    SELECT COUNT(*) FROM keyspace_name.table_name;
    ```

---

#### Indexing
- **Create Index**
  - Cria um índice em uma coluna específica
  - **Sintaxe:**
    ```cql
    CREATE INDEX IF NOT EXISTS index_name ON keyspace_name.table_name (column_name);
    ```

- **Drop Index**
  - Remove um índice existente
  - **Sintaxe:**
    ```cql
    DROP INDEX IF EXISTS keyspace_name.index_name;
    ```

---

#### Advanced Commands
- **Filtering (With Caution)**
  - Usa `ALLOW FILTERING` para consultas que não utilizam índices
  - **Atenção:** Pode ser intensivo em recursos para grandes conjuntos de dados
  - **Sintaxe:**
    ```cql
    SELECT * FROM keyspace_name.table_name
    WHERE column_name = 'value' ALLOW FILTERING;
    ```

- **Batch Operations**
  - Executa múltiplas operações em lote
  - **Sintaxe:**
    ```cql
    BEGIN BATCH
      INSERT INTO keyspace_name.table_name (id, column1) VALUES (uuid(), 'value1');
      UPDATE keyspace_name.table_name SET column2 = 789 WHERE id = uuid_value;
    APPLY BATCH;
    ```

---

#### Key Points
- **Primary Key:**
  - Define o identificador único para as linhas
  - Pode ser composto por múltiplas colunas

- **Partition Key:**
  - Primeira parte da chave primária
  - Usada para distribuição de dados

- **Clustering Columns:**
  - Especificam a ordenação dentro de uma partição

---
