package Prova01;

public class Main {
    public static void main(String[] args) {

        Lanchonete lanchonete = new Lanchonete();

        // Adicionando produtos
        lanchonete.adicionarAoCardapio(new Produto("101", "Hambúrguer", "Lanche", 15.0));
        lanchonete.adicionarAoCardapio(new Produto("102", "Refrigerante", "Bebida", 5.0));
        lanchonete.adicionarAoCardapio(new Produto("103", "Batata Frita", "Lanche", 10.0));

        // Listar cardápio
        lanchonete.listaCardapio();

        // Buscar produto
        System.out.println("\nBuscando o produto 'Refrigerante'...");
        lanchonete.buscarProduto("Refrigerante");

        // Fazer pedidos
        System.out.println("\nFazendo pedidos...");
        lanchonete.fazerPedido("101", "João");
        lanchonete.fazerPedido("102", "Maria");
        lanchonete.fazerPedido("103", "Carlos");

        // Atender pedidos
        System.out.println("\nAtendendo pedidos...");
        lanchonete.atenderProximo();
        lanchonete.atenderProximo();

        // Ver último atendido
        lanchonete.verUltimoAtendido();

        // Continuar atendimento
        System.out.println("\nAtendendo restantes...");
        lanchonete.atenderProximo();
        lanchonete.atenderProximo();
    }
}


