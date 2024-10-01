package org.example;

import redis.clients.jedis.Jedis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Autocomplete {
    public static final String USERS_KEY = "users"; // Key set for users' name

    public static void main(String[] args) throws FileNotFoundException {
        Jedis jedis = new Jedis();
        jedis.del(USERS_KEY);

        try {
            jedis.ping();
        } catch (Exception e) {
            System.out.println("Redis server is not running!");
            return;
        }

        try {
            Scanner fileReader = new Scanner(new FileReader("Práticas/Lab-1/Jedis_Guide/names.txt"));

            while (fileReader.hasNextLine()) {
                String name = fileReader.nextLine();
                jedis.zadd(USERS_KEY, 0, name);
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

                jedis.zrangeByLex(USERS_KEY, "[" + input, "[" + input + "u00ff").forEach(System.out::println);

            } while(true);

        }

        jedis.close();
    }
}
