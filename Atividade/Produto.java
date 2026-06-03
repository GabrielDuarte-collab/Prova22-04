package Atividade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Produto {
    private static final ArrayList<Produto> PRODUTOS = new ArrayList<>();

    private String codigo;
    private String nome;
    private String categoria;
    private double preco;
    private int estoque;

    public Produto(String codigo, String nome, String categoria, double preco) {
        this(codigo, nome, categoria, preco, 0);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome == null ? "" : nome.trim();
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria == null ? "" : categoria.trim();
    }

    public void setPreco(double preco) {
        this.preco = Math.max(0, preco);
    }

    public Produto(String codigo, String nome, String categoria, double preco, int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = Math.max(0, estoque);
    }

    public static boolean cadastrar(Produto produto) {
        if (produto == null || produto.codigo == null || produto.codigo.trim().isEmpty()) {
            return false;
        }
        if (buscarPorCodigo(produto.codigo) != null) {
            return false;
        }
        PRODUTOS.add(produto);
        return true;
    }

    public static Produto cadastrar(String codigo, String nome, String categoria, double preco, int estoque) {
        Produto produto = new Produto(codigo, nome, categoria, preco, estoque);
        return cadastrar(produto) ? produto : null;
    }

    public static List<Produto> listar() {
        return Collections.unmodifiableList(new ArrayList<>(PRODUTOS));
    }

    public static Produto buscarPorCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        String termo = codigo.trim();
        for (Produto produto : PRODUTOS) {
            if (produto.codigo.equalsIgnoreCase(termo)) {
                return produto;
            }
        }
        return null;
    }

    public static List<Produto> buscarPorNome(String nome) {
        ArrayList<Produto> encontrados = new ArrayList<>();
        if (nome == null) {
            return encontrados;
        }
        String termo = nome.trim().toLowerCase();
        for (Produto produto : PRODUTOS) {
            if (produto.nome.toLowerCase().contains(termo)) {
                encontrados.add(produto);
            }
        }
        return encontrados;
    }

    public static List<Produto> buscar(String termo) {
        ArrayList<Produto> encontrados = new ArrayList<>();
        if (termo == null) {
            return encontrados;
        }
        String busca = termo.trim().toLowerCase();
        for (Produto produto : PRODUTOS) {
            if (produto.matches(busca)) {
                encontrados.add(produto);
            }
        }
        return encontrados;
    }

    public static boolean remover(String codigo) {
        Produto produto = buscarPorCodigo(codigo);
        if (produto == null) {
            return false;
        }
        return PRODUTOS.remove(produto);
    }

    public static void limparEstoque() {
        PRODUTOS.clear();
    }

    public void atualizarEstoque(int novaQuantidade) {
        this.estoque = Math.max(0, novaQuantidade);
    }

    public void ajustarEstoque(int diferenca) {
        atualizarEstoque(this.estoque + diferenca);
    }

    public boolean matches(String termo) {
        if (termo == null) {
            return false;
        }
        String busca = termo.trim().toLowerCase();
        return codigo.equalsIgnoreCase(busca)
                || nome.toLowerCase().contains(busca)
                || categoria.toLowerCase().contains(busca)
                || String.valueOf(preco).equals(busca)
                || String.valueOf(estoque).equals(busca);
    }

    public void exibir() {
        System.out.printf("%s - %s (%s): R$%.2f - Estoque: %d%n",
                codigo, nome, categoria, preco, estoque);
    }
}
