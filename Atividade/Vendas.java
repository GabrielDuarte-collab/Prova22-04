package Atividade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vendas {
    private static final ArrayList<Venda> VENDAS = new ArrayList<>();
    private static int COUNTER = 1;

    public static class Item {
        private final Produto produto;
        private final int quantidade;
        private final double precoUnitario;

        public Item(Produto produto, int quantidade) {
            this.produto = produto;
            this.quantidade = Math.max(0, quantidade);
            this.precoUnitario = produto == null ? 0.0 : produto.getPreco();
        }

        public Produto getProduto() {
            return produto;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public double getPrecoUnitario() {
            return precoUnitario;
        }

        public double subtotal() {
            return precoUnitario * quantidade;
        }

        public void exibir() {
            System.out.printf("  %s x%d - R$%.2f (subtotal: R$%.2f)%n",
                    produto == null ? "<sem produto>" : produto.getNome(),
                    quantidade, precoUnitario, subtotal());
        }
    }

    public static class Venda {
        private final int id;
        private final Clientes cliente;
        private final ArrayList<Item> itens = new ArrayList<>();

        private boolean finalizada = false;

        public Venda(Clientes cliente) {
            this.id = COUNTER++;
            this.cliente = cliente;
        }

        public int getId() {
            return id;
        }

        public Clientes getCliente() {
            return cliente;
        }

        public List<Item> getItens() {
            return Collections.unmodifiableList(itens);
        }

        public boolean isFinalizada() {
            return finalizada;
        }

        public boolean adicionarItem(Produto produto, int quantidade) {
            if (finalizada || produto == null || quantidade <= 0) {
                return false;
            }
            if (produto.getEstoque() < quantidade) {
                return false;
            }
            // reduzir estoque no momento da adição
            produto.ajustarEstoque(-quantidade);
            itens.add(new Item(produto, quantidade));
            return true;
        }

        public double calcularTotal() {
            double total = 0.0;
            for (Item i : itens) {
                total += i.subtotal();
            }
            return total;
        }

        public void finalizar() {
            if (!finalizada) {
                finalizada = true;
                VENDAS.add(this);
            }
        }

        public void exibirResumo() {
            System.out.printf("Venda #%d - Cliente: %s - Total: R$%.2f\n",
                    id,
                    cliente == null ? "<sem cliente>" : cliente.getCpf() + " - " + cliente.getNome(),
                    calcularTotal());
            for (Item i : itens) {
                i.exibir();
            }
        }
    }

    public static Venda iniciarVenda(Clientes cliente) {
        return new Venda(cliente);
    }

    public static List<Venda> listarVendas() {
        return Collections.unmodifiableList(new ArrayList<>(VENDAS));
    }

    public static List<Venda> historicoPorCliente(String cpf) {
        ArrayList<Venda> encontrados = new ArrayList<>();
        if (cpf == null)
            return encontrados;
        String termo = cpf.trim();
        for (Venda v : VENDAS) {
            if (v.getCliente() != null && v.getCliente().getCpf().equalsIgnoreCase(termo)) {
                encontrados.add(v);
            }
        }
        return encontrados;
    }

    public static List<Venda> historicoPorCliente(Clientes cliente) {
        if (cliente == null)
            return new ArrayList<>();
        return historicoPorCliente(cliente.getCpf());
    }

    public static void limparVendas() {
        VENDAS.clear();
        COUNTER = 1;
    }
}
