package pt.ua.deti.cbd;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.Scanner;

public class MainB {

    private static final String CONNECTION_STRING = "mongodb://127.0.0.1:27017";
    private static final String DATABASE_NAME = "sistema_atendimento2"; // Replace with your database name

    public static void main(String[] args) {
        // Define the maximum number of units per user and the timespan in seconds
        int limit = 30; // Maximum units per user
        long timeslot = 60 * 60; // Timespan in seconds (e.g., 60 minutes)

        SistemaAtendimentoB sistema = new SistemaAtendimentoB(limit, timeslot);

        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> usersCollection = database.getCollection("users");
            MongoCollection<Document> productsCollection = database.getCollection("products");

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("===== Sistema de Atendimento =====");
                System.out.println("1. Add User");
                System.out.println("2. Add Product");
                System.out.println("3. List Users");
                System.out.println("4. List Products");
                System.out.println("5. Buy Product");
                System.out.println("6. List User's Products");
                System.out.println("7. Remove Product from User");
                System.out.println("8. Remove User");
                System.out.println("9. Remove Product");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        sistema.addUser(usersCollection);
                        break;
                    case 2:
                        sistema.addProduct(productsCollection);
                        break;
                    case 3:
                        sistema.listUsers(usersCollection);
                        break;
                    case 4:
                        sistema.listProducts(productsCollection);
                        break;
                    case 5:
                        System.out.print("Enter user name: ");
                        String userName = scanner.nextLine();
                        System.out.print("Enter product name: ");
                        String productName = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        sistema.buyProduct(userName, productName, quantity, usersCollection, productsCollection);
                        break;
                    case 6:
                        System.out.print("Enter user name: ");
                        userName = scanner.nextLine();
                        sistema.listUserProducts(userName, usersCollection);
                        break;
                    case 7:
                        System.out.print("Enter user name: ");
                        userName = scanner.nextLine();
                        System.out.print("Enter product name: ");
                        productName = scanner.nextLine();
                        sistema.removeProductFromUser(userName, productName, usersCollection);
                        break;
                    case 8:
                        System.out.print("Enter user name: ");
                        userName = scanner.nextLine();
                        sistema.removeUser(userName, usersCollection);
                        break;
                    case 9:
                        System.out.print("Enter product name: ");
                        productName = scanner.nextLine();
                        sistema.removeProduct(productName, productsCollection);
                        break;
                    case 0:
                        System.out.println("Exiting the system...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

                System.out.println();

            } while (choice != 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
