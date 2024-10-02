package pt.deti.ua.cbd;

import redis.clients.jedis.Jedis;

import java.util.List;

public class AppB {

    protected final Jedis jedis;
    private String name;
    private int maxTime;
    private int maxProd;  // Agora isso representa o número total de unidades permitidas.

    public AppB(int maxTime, int maxProd) {
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

    public void order(String name, String product, int quantity) {

        Long time = System.currentTimeMillis() / 1000;

        if (canOrder(name, quantity, time)) {
            // O valor armazenado no Redis será "produto:quantidade"
            String orderDetails = product + ":" + quantity;
            jedis.zadd(name, time, orderDetails);
            System.out.println("User " + name + " ordered " + quantity + " units of " + product + " at " + time);
        }

    }

    public boolean canOrder(String name, int quantity, Long time) {
        // Obter todos os pedidos do usuário dentro da janela de tempo
        List<String> orders = jedis.zrangeByScore(name, time - maxTime, time);

        // Contar o total de unidades pedidas na janela de tempo
        int totalUnits = 0;
        for (String order : orders) {
            String[] parts = order.split(":");
            int orderQuantity = Integer.parseInt(parts[1]);  // Pega a quantidade do pedido
            totalUnits += orderQuantity;
        }

        // Verificar se o número total de unidades já excede o limite
        if (totalUnits + quantity > maxProd) {
            System.out.println("User " + name + " has ordered the maximum number of product units in the last " + maxTime + " seconds.");
            return false;
        } else {
            return true;
        }
    }

    public List<String> getOrders(String name) {
        // get users orders in the last maxTime seconds
        long time = System.currentTimeMillis() / 1000;
        return jedis.zrangeByScore(name, time - maxTime, time);
    }
}
