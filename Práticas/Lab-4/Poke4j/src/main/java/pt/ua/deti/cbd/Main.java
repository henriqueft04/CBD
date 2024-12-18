/*
    Entities:
        Pokemon:
            - ID
            - Name
            - Total
            - HP
            - Attack
            - Defense
            - Sp. Atk
            - Sp. Def
            - Speed
            - Generation
            - Legendary

        Type:
            - Name

        Generation:
            - Number

        Region:
            - Name

    Relationships:
        - (Pokemon)-[:HAS_TYPE]->(Type)
        - (Pokemon)-[:BELONGS_TO]->(Generation)
        - (Pokemon)-[:ORIGINATES_FROM]->(Region)
*/

package pt.ua.deti.cbd;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Query;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Main implements AutoCloseable {

    private final Driver driver;

    public Main(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    private void loadData(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                int id = Integer.parseInt(values[0].trim());
                String name = values[1].trim();
                String type1 = values[2].trim();
                String type2 = values[3].trim().isEmpty() ? null : values[3].trim();
                int total = Integer.parseInt(values[4].trim());
                int hp = Integer.parseInt(values[5].trim());
                int attack = Integer.parseInt(values[6].trim());
                int defense = Integer.parseInt(values[7].trim());
                int spAtk = Integer.parseInt(values[8].trim());
                int spDef = Integer.parseInt(values[9].trim());
                int speed = Integer.parseInt(values[10].trim());
                int generation = Integer.parseInt(values[11].trim());
                boolean legendary = Boolean.parseBoolean(values[12].trim());
                String region = "Kanto"; // Example region, modify as needed

                try (Session session = driver.session()) {
                    session.executeWrite(
                            tx -> {
                                tx.run(
                                        "CREATE (p:Pokemon {id: $id, name: $name, total: $total, hp: $hp, attack: $attack, defense: $defense, " +
                                                "spAtk: $spAtk, spDef: $spDef, speed: $speed, generation: $generation, legendary: $legendary})" +
                                                "MERGE (t1:Type {name: $type1})" +
                                                (type2 != null ? "MERGE (t2:Type {name: $type2})" : "") +
                                                "MERGE (g:Generation {number: $generation})" +
                                                "MERGE (r:Region {name: $region})" +
                                                "CREATE (p)-[:HAS_TYPE]->(t1)" +
                                                (type2 != null ? "CREATE (p)-[:HAS_TYPE]->(t2)" : "") +
                                                "CREATE (p)-[:BELONGS_TO]->(g)" +
                                                "CREATE (p)-[:ORIGINATES_FROM]->(r)",
                                        Values.parameters(
                                                "id", id,
                                                "name", name,
                                                "type1", type1,
                                                "type2", type2,
                                                "total", total,
                                                "hp", hp,
                                                "attack", attack,
                                                "defense", defense,
                                                "spAtk", spAtk,
                                                "spDef", spDef,
                                                "speed", speed,
                                                "generation", generation,
                                                "legendary", legendary,
                                                "region", region
                                        )
                                );
                                return null;
                            }
                    );
                }
            }
        }
    }

    @Override
    public void close() throws RuntimeException {
        driver.close();
    }

    public static void main(String[] args) throws IOException {
        String csvFile = "/home/ric/Desktop/CBD/Práticas/Lab-4/Poke4j/src/main/resources/Pokemon.csv";

        try (Main app = new Main("bolt://localhost:7687", "neo4j", "password")) { // Update credentials
            System.out.println("Loading Pokémon data into Neo4j...");
            app.loadData(csvFile);
            System.out.println("Data loaded successfully!");
        }
    }
}
