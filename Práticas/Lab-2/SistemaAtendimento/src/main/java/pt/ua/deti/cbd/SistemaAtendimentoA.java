package pt.ua.deti.cbd;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class SistemaAtendimentoA {

    List<Document> users;
    int limit;
    List<Document> products;
    long timespan;

    public SistemaAtendimentoA(int limit, long timespan) {
        this.limit = limit;
        this.timespan = timespan * 1000;
        this.users = new ArrayList<>();
    }

    public List<Document> getUsers() {
        return users;
    }

    public void setUsers(List<Document> users) {
        this.users = users;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Document> getProducts() {
        return products;
    }

    public void setProducts(List<Document> products) {
        this.products = products;
    }

    public long getTimespan() {
        return timespan;
    }

    public void setTimespan(long timespan) {
        this.timespan = timespan;
    }

    public void addUser(MongoCollection<Document> users){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Insert user name: ");
            String name = scanner.nextLine();
            System.out.println("Phone number: ");
            String phone = scanner.nextLine();

            if(name.isEmpty() || phone.isEmpty()){
                System.out.println("Invalid input");
                return;
            }


            if (users.find(and(eq("name", name), eq("phone", phone))).first() != null) {
                System.out.println("User already exists");
                return;
            }

            Document user = new Document(
                    "_id", new ObjectId()).
                    append("name", name).
                    append("phone", phone).
                    append("products", new ArrayList<Document>()
            );

            users.insertOne(user);

            System.out.println("User " + name +" added");

        }catch (Exception e){
            System.out.println("Error adding user");
        }
    }

    public void addProduct(MongoCollection<Document> products){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Insert product name: ");
            String name = scanner.nextLine();
            System.out.println("Price: ");
            double price = scanner.nextDouble();

            if(name.isEmpty() || price <= 0){
                System.out.println("Invalid input");
                return;
            }

            if(products.find( new Document("name", name)).first() != null){
                System.out.println("Product already exists");
                return;
            }

            // get current time
            long currentTime = System.currentTimeMillis();

            Document product = new Document(
                    "_id", new ObjectId()).
                    append("name", name).
                    append("price", price).
                    append("timestamp", currentTime);

            products.insertOne(product);

            System.out.println("Product " + name +" added");

        }catch (Exception e){
            System.out.println("Error adding product");
        }
    }

    public void listUsers(MongoCollection<Document> users){
        try{
            for (Document user : users.find()) {
                System.out.println(user.toJson());
            }
        }catch (Exception e){
            System.out.println("Error listing users");
        }
    }

    public void listProducts(MongoCollection<Document> products){
        try{
            for (Document product : products.find()) {
                System.out.println(product.toJson());
            }
        }catch (Exception e){
            System.out.println("Error listing products");
        }
    }

    public void getUser(String nome, MongoCollection<Document> users){
        try{
            Document user = users.find(eq("name", nome)).first();
            if(user == null){
                System.out.println("User not found");
                return;
            }
            System.out.println(user.toJson());
        }catch (Exception e){
            System.out.println("Error getting user");
        }
    }

    public void getProduct(String nome, MongoCollection<Document> products){
        try{
            Document product = products.find(eq("name", nome)).first();
            if(product == null){
                System.out.println("Product not found");
                return;
            }
            System.out.println(product.toJson());
        }catch (Exception e){
            System.out.println("Error getting product");
        }
    }

    public void removeProduct(String productName, MongoCollection<Document> products){
        try{
            products.deleteOne(eq("name", productName));
            System.out.println("Product " + productName + " removed");
        }catch (Exception e){
            System.out.println("Error removing product");
        }
    }

    public void removeUser(String userName, MongoCollection<Document> users){
        try{
            users.deleteOne(eq("name", userName));
            System.out.println("User " + userName + " removed");
        }catch (Exception e){
            System.out.println("Error removing user");
        }
    }

    public void removeProductFromUser(String userName, String productName, MongoCollection<Document> users){
        try{
            Document user = users.find(eq("name", userName)).first();
            if(user == null){
                System.out.println("User not found");
                return;
            }
            List<Document> userProducts = user.getList("products", Document.class);
            for (Document userProduct : userProducts) {
                double productPrice = userProduct.getDouble("price");
                System.out.println("Product: " + userProduct.getString("name") + " Price: " + productPrice + "€");
            }
            userProducts.removeIf(product -> product.getString("name").equals(productName));
            users.updateOne(eq("name", userName), new Document("$set", new Document("products", userProducts)));
            System.out.println("Product " + productName + " removed from user " + userName);
        }catch (Exception e){
            System.out.println("Error removing product from user");
        }
    }

    public void buyProduct(String userName, String productName, MongoCollection<Document> users, MongoCollection<Document> products){
        try{
            Document user = users.find(eq("name", userName)).first();
            Document product = products.find(eq("name", productName)).first();

            if(user == null || product == null){
                System.out.println("User or product not found");
                return;
            }


            // check if the limit of products inside the timespan is reached
            // primeiro remover todos os produtos do carrinho que já passaram o tempo limite

            long currentTime = System.currentTimeMillis();
            for (Document userProduct : user.getList("products", Document.class)) {
                long productTimestamp = userProduct.getLong("timestamp");
                if(currentTime - productTimestamp > timespan){
                    user.getList("products", Document.class).remove(userProduct);
                }
            }

            if(user.getList("products", Document.class).size() >= limit){
                System.out.println("User reached the limit of products");
                return;
            }

            List<Document> userProducts = user.getList("products", Document.class);
            userProducts.add(product);
            users.updateOne(eq("name", userName), new Document("$set", new Document("products", userProducts)));

            System.out.println("Product " + productName + " bought by " + userName);

        }catch (Exception e){
            System.out.println("Error buying product");
        }
    }

    public void listUserProducts(String userName, MongoCollection<Document> users){

        // verificar o que já passou do tempo limite

        long currentTime = System.currentTimeMillis();
        Document user = users.find(eq("name", userName)).first();
        if(user == null){
            System.out.println("User not found");
            return;
        }

        for (Document userProduct : user.getList("products", Document.class)) {
            long productTimestamp = userProduct.getLong("timestamp");
            if(currentTime - productTimestamp > timespan){
                user.getList("products", Document.class).remove(userProduct);
            }
        }

        if (user.getList("products", Document.class).isEmpty()) {
            System.out.println("User has no products");
            return;
        }
        else {
            for (Document userProduct : user.getList("products", Document.class)) {
                String productName = userProduct.getString("name");
                double productPrice = userProduct.getDouble("price");
                System.out.println("Product: " + productName + " Price: " + productPrice + "€ | Time left: " + (timespan - (currentTime - userProduct.getLong("timestamp"))) / 1000 + " seconds");
            }
        }

    }
}
