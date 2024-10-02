# CBD-12: Documentação das Estruturas de Dados e Chaves

## Estruturas de Dados Redis (usadas via Jedis)

### 1. Set (Conjunto)
- **Redis**: Estrutura de dados que armazena coleções de elementos únicos. Útil para armazenar grupos de itens distintos.
- **Jedis**: Utiliza métodos como `sadd()` para adicionar elementos e `smembers()` para listar os itens do conjunto.
- **Uso no código**: Armazena nomes de utilizadores e seguidores de cada utilizador.

### 2. List (Lista)
- **Redis**: Estrutura de lista que permite adicionar e remover elementos no início ou fim da lista. Funciona como uma fila 
- **Jedis**: Métodos como `lpush()` adicionam elementos ao início da lista, e `lrange()` permite recuperar os elementos da lista.
- **Uso no código**: Armazena as mensagens enviadas por cada utilizador, sendo que as mais recentes estão no início da lista.

