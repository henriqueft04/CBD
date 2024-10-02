package pt.deti.ua.cbd;

public class ExBmain {

    public static void main(String[] args) throws InterruptedException {

        // Definir limite de 30 unidades de produtos e janela temporal de 60 segundos
        int maxProd = 30;
        int maxTime = 60;

        // Instanciando o sistema de atendimento com os limites definidos
        AppB atendimento = new AppB(maxTime, maxProd);

        // Simulação de pedidos por diferentes utilizadores
        String[] users = {"Costa", "Carlos"};
        String[] products = new String[]{"Produto1", "Produto2", "Produto3", "Produto4", "Produto5"};

        // Cada utilizador vai tentar fazer 7 pedidos com quantidades variadas (alguns pedidos excederão o limite)
        int[] quantities = {5, 10, 3, 7, 8};  // As quantidades pedidas para cada produto

        for (String user : users) {
            atendimento.setName(user);

            // Antes de cada usuário iniciar os pedidos, remover os dados antigos
            atendimento.jedis.del(user); // Limpar histórico de pedidos do Redis

            // Cada utilizador tenta fazer pedidos de vários produtos
            for (int i = 0; i < products.length; i++) {
                System.out.println("Tentando pedir " + quantities[i] + " unidades de " + products[i] + " para " + user);
                atendimento.order(user, products[i], quantities[i]);

                // Esperar 1 segundo entre pedidos para simular um intervalo de tempo
                Thread.sleep(1000);
            }

            // Verificar os pedidos feitos pelo utilizador nas últimas 60 segundos
            System.out.println("Pedidos de " + user + " nas últimas " + maxTime + " segundos:");
            atendimento.getOrders(user).forEach(order -> System.out.println(order));
        }

        // Finalizando a simulação
        System.out.println("Simulação de pedidos concluída.");
    }
}
