package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.resps.Tuple;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PopularAutocomplete {
    public static final String USERS_KEY = "users"; // Key set for users' name

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        jedis.del(USERS_KEY);

        try {
            jedis.ping();
        } catch (Exception e) {
            System.out.println("Redis server is not running!");
            return;
        }

        // Leitura e inserção de dados no Redis
        try {
            Scanner fileReader = new Scanner(new FileReader("/home/henriqueft_04/CBD/Práticas/Lab-1/Ex 1.1-1.4/nomes-pt-2021.csv"));

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] data = line.split(";");
                if (data.length == 2) {
                    String nome = data[0].trim().toLowerCase();
                    double score = Double.parseDouble(data[1]);
                    jedis.zadd(USERS_KEY, score, nome);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        if (jedis.zcard(USERS_KEY) == 0) {
            System.out.println("No names found in the file read!");
        } else {
            do {
                System.out.print("Search for ('Enter' for quit): ");
                String input = scanner.nextLine().toLowerCase();
                System.out.println(input);

                if (input.isEmpty()) {
                    System.out.println("Ended search");
                    break;
                }

                List<String> nomes = new ArrayList<>(jedis.zrangeByScore(USERS_KEY, "-inf", "+inf"));
                List<Tuple> result = new ArrayList<>();
                for (String nome : nomes) {
                    if (nome.startsWith(input)) {
                        double score = jedis.zscore(USERS_KEY, nome);
                        result.add(new Tuple(nome, score));
                    }
                }
                result.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));

                for (Tuple tuple : result) {
                    System.out.println(tuple.getElement() + " - " + tuple.getScore());
                }
            } while (true);
        }

        jedis.close();
    }
}
