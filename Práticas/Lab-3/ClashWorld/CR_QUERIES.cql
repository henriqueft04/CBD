use clashworld;

CREATE OR REPLACE FUNCTION count_clan_members(members set<int>)
    RETURNS NULL ON NULL INPUT
    RETURNS int
    LANGUAGE java
    AS 'return members == null ? 0 : members.size();';

CREATE OR REPLACE FUNCTION count_user_friends(friends set<int>)
    RETURNS NULL ON NULL INPUT
    RETURNS int
    LANGUAGE java
    AS 'return friends == null ? 0 : friends.size();';


SELECT count_clan_members(members) FROM clans WHERE clan_id = 101;
SELECT count_user_friends(friends) FROM users WHERE user_id = 4;

CREATE OR REPLACE FUNCTION sum_trophies(state int, trophies int)
    RETURNS NULL ON NULL INPUT
    RETURNS int
    LANGUAGE java
    AS 'return state + trophies;';

CREATE OR REPLACE AGGREGATE total_clan_trophies(int)
    SFUNC sum_trophies
    STYPE int
    INITCOND 0;

// Soma dos trofeus de todos os membros de um clã
SELECT total_clan_trophies(trophies) AS total_trophies FROM users WHERE clan_id = 101 ALLOW FILTERING ;

// adiconar um novo membro a um cla
UPDATE clans SET members = members + {6} WHERE clan_id = 101;

// remover um amigo
UPDATE users SET friends = friends - {3} WHERE user_id = 1;

// update a uma carta
UPDATE cards SET cost = 6 WHERE card_id = 2001;

// adicionar um novo amigo
UPDATE users SET friends = friends + {6} WHERE user_id = 1;

// alterar o nome de um clã
UPDATE clans SET name = 'Clã do Carlos Costa' WHERE clan_id = 102;

// remover um membro de um clã
UPDATE clans SET members = members - {5} WHERE clan_id = 101;

// limpar um deck
UPDATE users SET decks[2] = [] WHERE user_id = 1;

// elimiar um user
DELETE FROM users WHERE user_id = 19;

// remover uma carta
DELETE FROM cards WHERE card_id = 2050;

// remover uma batalha do historico
DELETE FROM battles WHERE battle_id = uuid();

// 1. 10 jogadores com mais trofeus
SELECT * FROM users_by_trophies LIMIT 10;

// 2. todas as cartas lendárias
SELECT * FROM cards WHERE rarity = 'Legendary';

// 3. todos os clãs ordenados por trofeus
SELECT * FROM clans_by_trophies;

// 4. clã com mais trofeus
SELECT * FROM clans_by_trophies LIMIT 1;

// 5. Todas as batalhas que eu ganhei
SELECT battle_id, player1, player2, result FROM battles WHERE winner = 4 ;

// 6. Todas as batalhas que eu perdi
SELECT battle_id, player1, player2, result FROM battles WHERE loser = 4 ;

// 7. Todos os membros do meu clã
SELECT * FROM users WHERE clan_id = 101;

//  8. Os meus decks
SELECT decks FROM users WHERE user_id = 4;

// 9. tods os jogadores com mais de 3500 trofeus
SELECT username, trophies FROM users_by_trophies WHERE trophies > 3500 ALLOW FILTERING;

// 10. cartas com custo maior que 5
SELECT * FROM cards WHERE cost > 5 ALLOW FILTERING;


