package pt.deti.ua.cbd;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class App {

    private Jedis jedis;
    private String name;
    private final String nameKey = "names";
    // keys para cada user
    private String messageKey;
    private String followersKey;

    public void keyGenerator(String name) {
        this.name = name;
        this.messageKey = name + ":messages";
        this.followersKey = name + ":followers";
    }

    public void addUsers(String name) {
        keyGenerator(name);
        jedis.sadd(nameKey, name);
        System.out.println("User " + name + " added");
    }

    public void followUser(String name, String user) {
        keyGenerator(name);
        jedis.sadd(followersKey, user);
        System.out.println(name + " is now following " + user);
    }

    public void sendMessage(String name, String message) {
        keyGenerator(name);

        jedis.lpush(messageKey, message);
        System.out.println(name + ": " + message);
        message = name + ": " + message;

        Set<String> followers = jedis.smembers(followersKey);
        for (String follower : followers) {

            String followerKey = follower + ":messages";
            jedis.lpush(followerKey, message);
        }
    }

    public void showMessages(String name) {
        keyGenerator(name);
        List<String> messages = jedis.lrange(messageKey, 0, -1);
        System.out.println("Messages for " + name);
        for (String message : messages) {
            System.out.println(message);
        }
    }

    public void showFollowers(String name) {
        keyGenerator(name);
        Set<String> followers = jedis.smembers(followersKey);
        System.out.println("Followers for " + name);
        for (String follower : followers) {
            System.out.println(follower);
        }
    }

    public void showUsers() {
        Set<String> users = jedis.smembers(nameKey);
        System.out.println("Users");
        for (String user : users) {
            System.out.println(user);
        }
    }

    public static void main(String[] args) {

        App app = new App();
        app.jedis = new Jedis("localhost", 6379);
        app.jedis.flushAll();

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            // Mostrar o menu de opções
            System.out.println("\n--- Menu ---");
            System.out.println("1. Adicionar Utilizador");
            System.out.println("2. Seguir Utilizador");
            System.out.println("3. Enviar Mensagem");
            System.out.println("4. Mostrar Mensagens");
            System.out.println("5. Mostrar Utilizadores");
            System.out.println("6. Mostrar Seguidores");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (option) {
                case 1:
                    // Adicionar utilizador
                    System.out.print("Insira o nome do novo utilizador: ");
                    String newUser = scanner.nextLine();
                    if (newUser.isEmpty()) {
                        System.out.println("Nome de utilizador inválido.");
                        break;
                    }
                    if (app.jedis.sismember(app.nameKey, newUser)) {
                        System.out.println("Utilizador já existe.");
                        break;
                    }
                    app.addUsers(newUser);
                    break;

                case 2:
                    // Seguir utilizador
                    System.out.print("Insira o nome do utilizador que vai seguir: ");
                    String follower = scanner.nextLine();
                    if (!app.jedis.sismember(app.nameKey, follower)) {
                        System.out.println("Utilizador não existe.");
                        break;
                    }
                    System.out.print("Insira o nome do utilizador a seguir: ");
                    String followee = scanner.nextLine();
                    if (!app.jedis.sismember(app.nameKey, followee)) {
                        System.out.println("Utilizador não existe.");
                        break;
                    }
                    app.followUser(follower, followee);
                    break;

                case 3:
                    // Enviar mensagem
                    System.out.print("Insira o nome do utilizador que vai enviar a mensagem: ");
                    String sender = scanner.nextLine();
                    if (!app.jedis.sismember(app.nameKey, sender)) {
                        System.out.println("Utilizador não existe.");
                        break;
                    }
                    System.out.print("Insira a mensagem: ");
                    String message = scanner.nextLine();
                    app.sendMessage(sender, message);
                    break;

                case 4:
                    // Mostrar mensagens
                    System.out.print("Insira o nome do utilizador para mostrar as mensagens: ");
                    String userToShow = scanner.nextLine();
                    if (!app.jedis.sismember(app.nameKey, userToShow)) {
                        System.out.println("Utilizador não existe.");
                        break;
                    }
                    app.showMessages(userToShow);
                    break;

                case 5:
                    // Mostrar utilizadores
                    app.showUsers();
                    break;

                case 6:
                    // Mostrar seguidores
                    System.out.print("Insira o nome do utilizador para mostrar os seguidores: ");
                    String userToFollowers = scanner.nextLine();
                    if (!app.jedis.sismember(app.nameKey, userToFollowers)) {
                        System.out.println("Utilizador não existe.");
                        break;
                    }
                    app.showFollowers(userToFollowers);
                    break;

                case 7:
                    // Sair
                    running = false;
                    System.out.println("A sair...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close(); // Fechar o scanner
        app.jedis.close(); // Fechar a conexão com o Redis
    }
}