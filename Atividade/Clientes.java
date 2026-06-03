package Atividade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Clientes {
    private static final ArrayList<Clientes> CLIENTES = new ArrayList<>();

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;

    public Clientes(String nome, String cpf) {
        this(nome, cpf, "", "", "");
    }

    public Clientes(String nome, String cpf, String telefone, String email, String endereco) {
        this.nome = nome == null ? "" : nome.trim();
        this.cpf = cpf == null ? "" : cpf.trim();
        this.telefone = telefone == null ? "" : telefone.trim();
        this.email = email == null ? "" : email.trim();
        this.endereco = endereco == null ? "" : endereco.trim();
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public String getEndereco() { return endereco; }

    // Setters
    public void setNome(String nome) {
        this.nome = nome == null ? "" : nome.trim();
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone == null ? "" : telefone.trim();
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email.trim();
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco == null ? "" : endereco.trim();
    }

    public static boolean cadastrar(Clientes cliente) {
        if (cliente == null || cliente.cpf == null || cliente.cpf.trim().isEmpty()) {
            return false;
        }
        if (buscarPorCPF(cliente.cpf) != null) {
            return false;
        }
        CLIENTES.add(cliente);
        return true;
    }

    public static Clientes cadastrar(String nome, String cpf) {
        Clientes c = new Clientes(nome, cpf);
        return cadastrar(c) ? c : null;
    }

    public static List<Clientes> listar() {
        return Collections.unmodifiableList(new ArrayList<>(CLIENTES));
    }

    public static Clientes buscarPorCPF(String cpf) {
        if (cpf == null)
            return null;
        String termo = cpf.trim();
        for (Clientes c : CLIENTES) {
            if (c.cpf.equalsIgnoreCase(termo)) {
                return c;
            }
        }
        return null;
    }

    public static List<Clientes> buscarPorNome(String nome) {
        ArrayList<Clientes> encontrados = new ArrayList<>();
        if (nome == null)
            return encontrados;
        String termo = nome.trim().toLowerCase();
        for (Clientes c : CLIENTES) {
            if (c.nome.toLowerCase().contains(termo)) {
                encontrados.add(c);
            }
        }
        return encontrados;
    }

    public static List<Clientes> buscar(String termo) {
        ArrayList<Clientes> encontrados = new ArrayList<>();
        if (termo == null)
            return encontrados;
        String busca = termo.trim().toLowerCase();
        for (Clientes c : CLIENTES) {
            if (c.matches(busca)) {
                encontrados.add(c);
            }
        }
        return encontrados;
    }

    public static boolean remover(String cpf) {
        Clientes c = buscarPorCPF(cpf);
        if (c == null)
            return false;
        return CLIENTES.remove(c);
    }

    public static void limpar() {
        CLIENTES.clear();
    }

    public boolean matches(String termo) {
        if (termo == null)
            return false;
        String busca = termo.trim().toLowerCase();
        return cpf.equalsIgnoreCase(busca)
                || nome.toLowerCase().contains(busca)
                || telefone.toLowerCase().contains(busca)
                || email.toLowerCase().contains(busca)
                || endereco.toLowerCase().contains(busca);
    }

    public void exibir() {
        System.out.printf("%s - %s%n", cpf, nome);
    }
}
