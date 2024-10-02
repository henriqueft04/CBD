package pt.deti.ua.cbd;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class App {

    protected final Jedis jedis;
    private String name;
    private int maxTime;
    private int maxProd;

    public App(int maxTime, int maxProd) {
        this.jedis = new Jedis();
        this.maxTime = maxTime;
        this.maxProd = maxProd;
    }

    public void setMaxProd(int maxProd) {
        this.maxProd = maxProd;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void order(String name, String product) {

        Long time = System.currentTimeMillis() / 1000;

        if (canOrder(name, time)){
            jedis.zadd(name, time, product);
            System.out.println("User " + name + " ordered " + product + " at " + time);
        }

    }

    public boolean canOrder(String name, Long time) {
        // If the user has not ordered the maximum number of products in the timespan allowed
        // nao esquecer por mensagens de erro
        if (jedis.zcount(name, 0, time) < maxProd) {
            // If the user has not ordered the maximum number of products in the timespan allowed
            if (jedis.zcount(name, time - maxTime, time) < maxProd) {
                return true;
            } else {
                System.out.println("User " + name + " has ordered the maximum number of products in the last " + maxTime + " seconds");
                return false;
            }
        } else {
            System.out.println("User " + name + " has ordered the maximum number of products");
            return false;
        }
    }

    public List<String> getOrders(String name) {
        // get users orders in the last maxTime seconds
        long time = System.currentTimeMillis() / 1000;
        // zrangeByScore retorna uma List<String>, portanto, devemos usar List em vez de Set
        return new ArrayList<>(jedis.zrangeByScore(name, time - maxTime, time));
    }

}