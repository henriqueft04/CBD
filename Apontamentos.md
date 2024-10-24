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