package Prova01;

import java.util.ArrayList;

public class Pedido {
    String nomeCliente;
    ArrayList<Produto> itens;

    public Pedido(String nomeCliente) {
        this.nomeCliente = nomeCliente;
        this.itens = new ArrayList<>();
    }

    void adicionarItem(Produto produto) {
        itens.add(produto);
    }

    double calcularTotal() {
        double total = 0;
        for (Produto produto : itens) {
            total += produto.preco;

        }
        return total;
    }

    void exibirPedido() {
        System.out.println("Pedido de " + nomeCliente);
        for (Produto produto : itens) {
            produto.exibir();
        }
        System.out.println("Total do Pedido: R$" + calcularTotal());
    }

}