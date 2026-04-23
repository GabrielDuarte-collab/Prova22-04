package Prova01;

public class Produto {
    String codigo;
    String nome;
    String categoria;
    double preco;

    public Produto(String codigo, String nome, String categoria, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    void exibir() {
        System.out.println("codigo" + codigo +
                "nome" + nome + "categoria" + categoria + "R$" + preco);
    }

}