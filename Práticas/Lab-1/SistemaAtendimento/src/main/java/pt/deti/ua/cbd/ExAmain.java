package pt.deti.ua.cbd;

public class ExAmain {

    public static void main(String[] args) throws InterruptedException {

        // Definir limite de 5 produtos e janela temporal de 10 segundos
        int maxProd = 5;
        int maxTime = 10;

        // Instanciando o sistema de atendimento com os limites definidos
        App atendimento = new App(maxTime, maxProd);

        // Simulação de pedidos por diferentes utilizadores
        String[] users = {"Carlos", "Costa" };
        String[] products = new String[7];  // Gerar 7 produtos para exceder o limite de 5
        for (int i = 0; i < products.length; i++) {
            products[i] = "Produto" + (i + 1);
        }

        // Simular pedidos dos utilizadores
        for (String user : users) {
            atendimento.setName(user);

            // Antes de cada usuário iniciar os pedidos, remover os dados antigos
            atendimento.jedis.del(user); // Limpar histórico de pedidos do Redis

            // Cada utilizador tenta fazer 7 pedidos (2 a mais do que o limite de 5)
            for (int i = 0; i < products.length; i++) {
                System.out.println("Tentando pedido: " + products[i] + " para " + user);
                atendimento.order(user, products[i]);

                // Esperar 1 segundo entre pedidos para simular um intervalo de tempo
                Thread.sleep(1000);
            }

            // Verificar os pedidos feitos pelo utilizador nas últimas 10 segundos
            System.out.println("Pedidos de " + user + " nas últimas " + maxTime + " segundos:");
            atendimento.getOrders(user).forEach(order -> System.out.println(order));
        }

        // Finalizando a simulação
        System.out.println("Simulação de pedidos concluída.");
    }
}
