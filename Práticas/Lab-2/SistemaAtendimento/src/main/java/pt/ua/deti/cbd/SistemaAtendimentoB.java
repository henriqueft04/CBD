package pt.ua.deti.cbd;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class SistemaAtendimentoB {

    int limit;
    long timespan;

    public SistemaAtendimentoB(int limit, long timespan) {
        this.limit = limit;
        this.timespan = timespan * 1000; // Convert seconds to milliseconds
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

    public void listUsers(MongoCollection<Document> users) {
        try {
            List<Document> userList = users.find().into(new ArrayList<>());
            if (userList.isEmpty()) {
                System.out.println("No users found");
            } else {
                for (Document user : userList) {
                    System.out.println("User: " + user.getString("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error listing users");
        }
    }

    public void listProducts(MongoCollection<Document> products) {
        try {
            List<Document> productList = products.find().into(new ArrayList<>());
            if (productList.isEmpty()) {
                System.out.println("No products found");
            } else {
                for (Document product : productList) {
                    System.out.println("Product: " + product.getString("name") + ", Price: " + product.getDouble("price") + "€");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error listing products");
        }
    }

    public void buyProduct(String userName, String productName, int quantity,
                           MongoCollection<Document> users, MongoCollection<Document> products) {
        try {
            Document user = users.find(eq("name", userName)).first();
            Document product = products.find(eq("name", productName)).first();

            if (user == null || product == null) {
                System.out.println("User or product not found");
                return;
            }

            long currentTime = System.currentTimeMillis();

            List<Document> userProducts = user.getList("products", Document.class, new ArrayList<>());
            Iterator<Document> iterator = userProducts.iterator();
            while (iterator.hasNext()) {
                Document userProduct = iterator.next();
                long productTimestamp = userProduct.getLong("timestamp");
                if (currentTime - productTimestamp > timespan) {
                    iterator.remove();
                }
            }

            int totalQuantity = 0;
            for (Document userProduct : userProducts) {
                totalQuantity += userProduct.getInteger("quantity");
            }

            if (totalQuantity + quantity > limit) {
                System.out.println("Cannot purchase products: Limit of " + limit + " units per timespan exceeded");
                return;
            }

            Document purchasedProduct = new Document("productId", product.getObjectId("_id"))
                    .append("name", product.getString("name"))
                    .append("price", product.getDouble("price"))
                    .append("quantity", quantity)
                    .append("timestamp", currentTime);

            userProducts.add(purchasedProduct);

            users.updateOne(eq("name", userName), new Document("$set", new Document("products", userProducts)));

            System.out.println("Product " + productName + " (Quantity: " + quantity + ") purchased by " + userName);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error buying product");
        }
    }

    public void listUserProducts(String userName, MongoCollection<Document> users) {
        try {
            long currentTime = System.currentTimeMillis();
            Document user = users.find(eq("name", userName)).first();
            if (user == null) {
                System.out.println("User not found");
                return;
            }

            List<Document> userProducts = user.getList("products", Document.class, new ArrayList<>());
            Iterator<Document> iterator = userProducts.iterator();
            while (iterator.hasNext()) {
                Document userProduct = iterator.next();
                long productTimestamp = userProduct.getLong("timestamp");
                if (currentTime - productTimestamp > timespan) {
                    iterator.remove();
                }
            }

            users.updateOne(eq("name", userName), new Document("$set", new Document("products", userProducts)));

            if (userProducts.isEmpty()) {
                System.out.println("User has no products");
            } else {
                for (Document userProduct : userProducts) {
                    String productName = userProduct.getString("name");
                    double productPrice = userProduct.getDouble("price");
                    int quantity = userProduct.getInteger("quantity");
                    long timeLeft = (timespan - (currentTime - userProduct.getLong("timestamp"))) / 1000;
                    System.out.println("Product: " + productName + ", Quantity: " + quantity + ", Price per unit: "
                            + productPrice + "€, Time left: " + timeLeft + " seconds");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error listing user products");
        }
    }

    public void removeProductFromUser(String userName, String productName, MongoCollection<Document> users) {
        try {
            Document user = users.find(eq("name", userName)).first();
            if (user == null) {
                System.out.println("User not found");
                return;
            }
            List<Document> userProducts = user.getList("products", Document.class, new ArrayList<>());
            boolean found = false;

            Iterator<Document> iterator = userProducts.iterator();
            while (iterator.hasNext()) {
                Document userProduct = iterator.next();
                if (userProduct.getString("name").equals(productName)) {
                    iterator.remove();
                    found = true;
                }
            }

            if (found) {
                users.updateOne(eq("name", userName), new Document("$set", new Document("products", userProducts)));
                System.out.println("Product " + productName + " removed from user " + userName);
            } else {
                System.out.println("Product not found in user's list");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error removing product from user");
        }
    }

    public void removeUser(String userName, MongoCollection<Document> users) {
        try {
            users.deleteOne(eq("name", userName));
            System.out.println("User " + userName + " removed");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error removing user");
        }
    }

    public void removeProduct(String productName, MongoCollection<Document> products) {
        try {
            products.deleteOne(eq("name", productName));
            System.out.println("Product " + productName + " removed");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error removing product");
        }
    }

    public void removeUserProducts(String userName, MongoCollection<Document> users) {
        try {
            users.updateOne(eq("name", userName), new Document("$set", new Document("products", new ArrayList<>())));
            System.out.println("All products removed from user " + userName);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error removing user products");
        }
    }

}
