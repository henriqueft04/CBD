package pt.ua.deti.cbd;

import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Updates.*;

public class MongoDriver {
    private static final String CONNECTION_STRING = "mongodb://127.0.0.1:27017";
    private static final String DATABASE_NAME = "local";
    private static final String COLLECTION_NAME = "restaurants";


    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);


            Restaurant testRestaurant = new Restaurant("100000", "Test Restaurant", "Test Location", "Test Gastronomy");

            insertRestaurant(testRestaurant, collection);
            updateRestaurant("100000", "Updated Restaurant", "Updated Location", "Updated Gastronomy", collection);
            findRestaurant("100000", collection);
            deleteRestaurant("100000", collection);

            exercise3(collection);
            exercise4(collection);
            exercise5(collection);

            System.out.println("\n Total of localidades: " + new MongoDriver().countLocalidades(collection));

            Map<String, Integer> result = new MongoDriver().countRestByLocalidade(collection);
            for (Map.Entry<String, Integer> entry : result.entrySet()) {
                System.out.println("Localidade: " + entry.getKey() + " -> Total: " + entry.getValue());
            }

            System.out.println("\n Restaurants with name closer to 'Park': ");

            List<String> result2 = new MongoDriver().getRestWithNameCloserTo("Park", collection);
            for (String name : result2) {
                System.out.println("Restaurant name: " + name);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void insertRestaurant(Restaurant restaurant, MongoCollection<Document> collection){
        Document document = new Document("id", restaurant.id)
                .append("name", restaurant.name)
                .append("location", restaurant.location)
                .append("gastronomy", restaurant.gastronomy);
        collection.insertOne(document);

        System.out.println("Inserted restaurant with id: " + restaurant.id + "\n");
    }

    public static void updateRestaurant(String id, String name, String location, String gastronomy, MongoCollection<Document> collection){
        collection.updateOne(eq("id", id), combine(set("name", name), set("location", location), set("gastronomy", gastronomy)));

        System.out.println("Updated restaurant with id: " + id + "\n");
    }

    public static void deleteRestaurant(String id, MongoCollection<Document> collection){
        collection.deleteOne(eq("id", id));

        System.out.println("Deleted restaurant with id: " + id + "\n");
    }

    public static void findRestaurant(String id, MongoCollection<Document> collection){
        Document restaurant = collection.find(eq("id", id)).first();
        assert restaurant != null;
        System.out.println("restaurant found: \n"+restaurant.toJson());
    }

    // recreate 5 exercises
    public static void exercise1(MongoCollection<Document> collection){
        // Exercise 1: Find all restaurants
        System.out.println("Exercise 1 -> 2.1: Find all restaurants");
        FindIterable<Document> restaurants = collection.find();
        for (Document restaurant : restaurants) {
            System.out.println(restaurant.toJson());
        }
    }

    public static void exercise2(MongoCollection<Document> collection) {
        // Exercise 2: find the argumens, restaurant_id, nome, localidade e gastronomia for all documents
        System.out.println("Exercise 2 -> 2.2: find the argumens, restaurant_id, nome, localidade e gastronomia for all documents");
        FindIterable<Document> restaurants = collection.find().projection(fields(include("id", "name", "location", "gastronomy")));
        for (Document restaurant : restaurants) {
            System.out.println(restaurant.toJson());
        }
    }

    public static void exercise3(MongoCollection<Document> collection) {
        // Exercise 3: find the total of restaurants located in the Bronx
        System.out.println("\n Exercise 3 -> 2.5: find the total of restaurants located in the Bronx");
        long count = collection.countDocuments(eq("localidade", "Bronx"));
        System.out.println("Total of restaurants located in the Bronx: " + count);
    }

    public static void exercise4(MongoCollection<Document> collection) {
        // Exercise 4: find all the restaurants with at least one score above 85
        System.out.println("\n Exercise 4 -> 2.6: find all the restaurants with at least one score above 85");
        FindIterable<Document> restaurants = collection.find(elemMatch("grades", gt("score", 85)));
        for (Document restaurant : restaurants) {
            System.out.println(restaurant.toJson());
        }
    }

    public static void exercise5(MongoCollection<Document> collection) {
        // Exercise 5: find the total number of avaliations for each week day
        System.out.println("\n Exercise 5 -> 2.21: find the total number of avaliations for each week day");

        List<Bson> pipeline = new ArrayList<>();
        pipeline.add(unwind("$grades"));

        pipeline.add(Aggregates.project(
                Projections.fields(
                        Projections.computed("dayOfWeek", new Document("$dayOfWeek", "$grades.date"))
                )
        ));

        pipeline.add(group("$dayOfWeek", sum("total", 1)));
        pipeline.add(sort(descending("_id")));

        AggregateIterable<Document> result = collection.aggregate(pipeline);
        for (Document document : result) {
            // case by id
            switch (document.getInteger("_id")) {
                case 1:
                    System.out.println("Sunday: " + document.getInteger("total"));
                    break;
                case 2:
                    System.out.println("Monday: " + document.getInteger("total"));
                    break;
                case 3:
                    System.out.println("Tuesday: " + document.getInteger("total"));
                    break;
                case 4:
                    System.out.println("Wednesday: " + document.getInteger("total"));
                    break;
                case 5:
                    System.out.println("Thursday: " + document.getInteger("total"));
                    break;
                case 6:
                    System.out.println("Friday: " + document.getInteger("total"));
                    break;
                case 7:
                    System.out.println("Saturday: " + document.getInteger("total"));
                    break;
            }
        }
    }

    public int countLocalidades(MongoCollection<Document> collection) {
        return (int) collection.distinct("localidade", String.class).into(new ArrayList<>()).size();
    }

    public Map<String, Integer> countRestByLocalidade( MongoCollection<Document> collection) {
        Map<String, Integer> result = new HashMap<>();
        List<Bson> pipeline = new ArrayList<>();

        pipeline.add(group("$localidade", sum("total", 1)));

        AggregateIterable<Document> documents = collection.aggregate(pipeline);

        for (Document document : documents) {
            result.put(document.getString("_id"), document.getInteger("total"));
        }
        return result;
    }

    public List<String> getRestWithNameCloserTo(String name, MongoCollection<Document> collection) {
        List<String> result = new ArrayList<>();

        Bson filter = regex("nome", ".*" + name + ".*");
        Bson projection = Projections.fields(Projections.include("nome"));
        FindIterable<Document> documents = collection.find(filter).projection(projection);
        for (Document document : documents) {
            result.add(document.getString("nome"));
        }

        return result;
    }

}

