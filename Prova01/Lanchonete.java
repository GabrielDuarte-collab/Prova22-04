package Prova01;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Lanchonete {
    Queue<Pedido> filaDePedidos;
    Stack<Pedido> historico;
    HashMap<String, Produto> cardapio;

    public Lanchonete() {
        this.filaDePedidos = new LinkedList<>();
        this.historico = new Stack<>();
        this.cardapio = new HashMap<>();
    }

    void adicionarAoCardapio(Produto produto) {
        cardapio.put(produto.codigo, produto);
        System.out.println("Produto " + produto.nome + " adicionado ao cardápio!");
    }

    void buscarProduto(String nome) {
        boolean encontrado = false;

        for (Produto produto : cardapio.values()) {
            if (produto.nome.equalsIgnoreCase(nome)) {
                System.out.println("Produto encontrado:");
                produto.exibir();
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Produto não encontrado no cardápio.");
        }
    }

    void fazerPedido(String codigoProduto, String nomeCliente) {
        Produto produto = cardapio.get(codigoProduto);

        if (produto != null) {
            Pedido pedido = new Pedido(nomeCliente);
            pedido.adicionarItem(produto);
            filaDePedidos.add(pedido);

            System.out.println("Pedido de " + nomeCliente + " adicionado à fila.");
        } else {
            System.out.println("Produto não encontrado no cardápio.");
        }
    }

    void atenderProximo() {
        if (!filaDePedidos.isEmpty()) {
            Pedido pedidoAtendido = filaDePedidos.poll();

            System.out.println("\nAtendendo o pedido de " + pedidoAtendido.nomeCliente);
            pedidoAtendido.exibirPedido();

            historico.push(pedidoAtendido);
        } else {
            System.out.println("\nNão há pedidos na fila.");
        }
    }

    void verUltimoAtendido() {
        if (!historico.isEmpty()) {
            Pedido ultimoPedido = historico.peek();

            System.out.println("\nÚltimo Pedido Atendido:");
            ultimoPedido.exibirPedido();
        } else {
            System.out.println("\nNenhum pedido atendido ainda.");
        }
    }

    void listaCardapio() {
        System.out.println("\nCardápio de Produtos:");

        if (cardapio.isEmpty()) {
            System.out.println("Cardápio vazio.");
        } else {
            for (Produto produto : cardapio.values()) {
                produto.exibir();
            }
        }
    }
}