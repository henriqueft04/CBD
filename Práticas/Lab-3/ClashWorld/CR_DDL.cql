CREATE KEYSPACE IF NOT EXISTS ClashWorld WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

USE ClashWorld;

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
    user_id int PRIMARY KEY,
    username text,
    clan_id int,
    trophies int,
    decks frozen<map<int, list<int>>>,
    friends set<int>,
);

DROP TABLE IF EXISTS users_by_clan_id;
CREATE INDEX IF NOT EXISTS users_by_clan_id ON users (clan_id);


DROP TABLE IF EXISTS users_by_username;
CREATE INDEX users_by_username ON users (username);

DROP TABLE IF EXISTS users_by_trophies;
CREATE TABLE IF NOT EXISTS users_by_trophies (
    trophies int,
    user_id int,
    username text,
    clan_id int,
    decks frozen<map<int, list<int>>>,
    friends set<int>,
    PRIMARY KEY (trophies, user_id)  -- trophies is the partition key
) WITH CLUSTERING ORDER BY (user_id ASC);

DROP TABLE IF EXISTS cards;
CREATE TABLE IF NOT EXISTS cards (
    card_id int PRIMARY KEY,
    name text,
    rarity text,
    cost int,
    mega_evolutions set<text> // Set of mega evolution names
);

DROP INDEX IF EXISTS cards_mega_evolutions_index;
CREATE INDEX IF NOT EXISTS cards_mega_evolutions_index ON cards (mega_evolutions);


DROP TABLE IF EXISTS cards_by_name;
CREATE INDEX IF NOT EXISTS cards_by_name ON cards (name);

DROP TABLE IF EXISTS cards_by_cost;
CREATE INDEX IF NOT EXISTS cards_by_cost ON cards (cost);

DROP TABLE IF EXISTS cards_by_rarity;
CREATE INDEX cards_by_rarity ON cards (rarity);

DROP TABLE IF EXISTS clans;
CREATE TABLE IF NOT EXISTS clans (
    clan_id int PRIMARY KEY,
    name text,
    trophies int,
    members set<int>
);

DROP TABLE IF EXISTS clans_by_name;
CREATE INDEX IF NOT EXISTS clans_by_name ON clans (name);

DROP TABLE IF EXISTS clans_by_trophies;
CREATE TABLE IF NOT EXISTS clans_by_trophies (
    trophies int,
    clan_id int,
    name text,
    members set<int>,
    PRIMARY KEY (trophies, clan_id)
) WITH CLUSTERING ORDER BY (clan_id ASC);

DROP TABLE IF EXISTS battles;
CREATE TABLE IF NOT EXISTS battles(
    battle_id UUID PRIMARY KEY,
    player1 int,
    player2 int,
    winner int,
    loser int,
    result text,
    timestamp timestamp,
    deck1 list<int>,
    deck2 list<int>
);

DROP INDEX IF EXISTS battles_by_loser;
CREATE INDEX IF NOT EXISTS battles_by_loser ON battles (loser);
DROP INDEX IF EXISTS battles_by_loser;
CREATE INDEX IF NOT EXISTS battles_by_winner ON battles (winner);

