package Prova01;



public class Main {
    public static void main(String[] args) {
       
        Lanchonete lanchonete = new Lanchonete();

        
        lanchonete.listaCardapio();

        
        System.out.println("\nBuscando o produto 'Refrigerante'...");
        lanchonete.buscarProduto("Refrigerante");

        
        System.out.println("\nFazendo pedidos...");
        lanchonete.fazerPedido(101, 1);  // Pedido do Hambúrguer
        lanchonete.fazerPedido(102, 2);  // Pedido do Refrigerante
        lanchonete.fazerPedido(103, 3);  // Pedido da Batata Frita

        
        System.out.println("\nAtendendo o primeiro pedido...");
        lanchonete.atenderProximo();  

        System.out.println("\nAtendendo o próximo pedido...");
        lanchonete.atenderProximo();  

        
        lanchonete.verUltimoAtendido();  

        
        System.out.println("\nTentando atender o próximo pedido, mas a fila pode estar vazia...");
        lanchonete.atenderProximo(); 
        lanchonete.atenderProximo();  
    }
}


