package org.example;

import redis.clients.jedis.Jedis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class PopularAutocomplete {
    public static final String USERS_KEY = "users"; // Key set for users' name

    public static void main(String[] args) throws FileNotFoundException {
        Jedis jedis = new Jedis();
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (true) {
            System.out.print("Search for ('Enter' for quit): ");
            input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Ended search");
                break;
            }

            // Open file for reading names
            Scanner fileReader = new Scanner(new FileReader("nomes-pt-2021.csv "));

            // Clear previous search results from Redis
            jedis.del(USERS_KEY);

            while (fileReader.hasNextLine()) {
                String name = fileReader.nextLine();

                if (name.toLowerCase().contains(input.toLowerCase())) {
                    jedis.sadd(USERS_KEY, name);
                }
            }

            fileReader.close();

            if (jedis.smembers(USERS_KEY).isEmpty()) {
                System.out.println("No matches found");
            } else {
                System.out.println("Matches found:");
                jedis.smembers(USERS_KEY).forEach(System.out::println);
            }
        }

        jedis.close();
    }
}
