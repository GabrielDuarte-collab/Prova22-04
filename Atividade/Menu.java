package Atividade;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenuPrincipal() {
        System.out.println("\n========================================");
        System.out.println("     SISTEMA DE VENDAS - MENU PRINCIPAL");
        System.out.println("========================================");
        System.out.println("1. Gerenciar Produtos");
        System.out.println("2. Gerenciar Clientes");
        System.out.println("3. Realizar Venda");
        System.out.println("4. Consultar Vendas");
        System.out.println("0. Sair");
        System.out.println("========================================");
        System.out.print("Escolha uma opção: ");
    }

    public void exibirMenuProdutos() {
        System.out.println("\n--- GERENCIAMENTO DE PRODUTOS ---");
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Listar Produtos");
        System.out.println("3. Buscar Produto");
        System.out.println("4. Atualizar Estoque");
        System.out.println("5. Remover Produto");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
    }

    public void exibirMenuClientes() {
        System.out.println("\n--- GERENCIAMENTO DE CLIENTES ---");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Buscar Cliente");
        System.out.println("4. Remover Cliente");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
    }

    public void exibirMenuVendas() {
        System.out.println("\n--- GERENCIAMENTO DE VENDAS ---");
        System.out.println("1. Criar Nova Venda");
        System.out.println("2. Consultar Histórico de Vendas");
        System.out.println("3. Consultar Vendas por Cliente");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
    }

    // ========== PRODUTO ==========

    public void cadastrarProduto() {
        System.out.println("\n--- CADASTRO DE PRODUTO ---");
        System.out.print("Código do produto: ");
        String codigo = scanner.nextLine().trim();

        if (Produto.buscarPorCodigo(codigo) != null) {
            System.out.println("❌ Erro: Produto com este código já existe!");
            return;
        }

        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine().trim();

        System.out.print("Preço: R$ ");
        double preco;
        try {
            preco = Double.parseDouble(scanner.nextLine().trim());
            if (preco < 0) {
                System.out.println("❌ Erro: Preço não pode ser negativo!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: Preço inválido!");
            return;
        }

        System.out.print("Estoque inicial: ");
        int estoque;
        try {
            estoque = Integer.parseInt(scanner.nextLine().trim());
            if (estoque < 0) {
                System.out.println("❌ Erro: Estoque não pode ser negativo!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: Estoque inválido!");
            return;
        }

        if (Produto.cadastrar(codigo, nome, categoria, preco, estoque) != null) {
            System.out.println("✓ Produto cadastrado com sucesso!");
        } else {
            System.out.println("❌ Erro ao cadastrar produto!");
        }
    }

    public void listarProdutos() {
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        List<Produto> produtos = Produto.listar();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto p : produtos) {
            p.exibir();
        }
        System.out.println("Total: " + produtos.size() + " produto(s)");
    }

    public void buscarProduto() {
        System.out.println("\n--- BUSCA DE PRODUTO ---");
        System.out.print("Digite o termo de busca (código ou nome): ");
        String termo = scanner.nextLine().trim();

        List<Produto> encontrados = Produto.buscar(termo);

        if (encontrados.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
            return;
        }

        System.out.println("Produtos encontrados:");
        for (Produto p : encontrados) {
            p.exibir();
        }
    }

    public void atualizarEstoque() {
        System.out.println("\n--- ATUALIZAR ESTOQUE ---");
        System.out.print("Digite o código do produto: ");
        String codigo = scanner.nextLine().trim();

        Produto p = Produto.buscarPorCodigo(codigo);
        if (p == null) {
            System.out.println("❌ Produto não encontrado!");
            return;
        }

        System.out.println("Estoque atual: " + p.getEstoque());
        System.out.print("Nova quantidade: ");
        try {
            int novaQtd = Integer.parseInt(scanner.nextLine().trim());
            if (novaQtd < 0) {
                System.out.println("❌ Erro: Estoque não pode ser negativo!");
                return;
            }
            p.atualizarEstoque(novaQtd);
            System.out.println("✓ Estoque atualizado!");
        } catch (NumberFormatException e) {
            System.out.println("❌ Erro: Valor inválido!");
        }
    }

    public void removerProduto() {
        System.out.println("\n--- REMOVER PRODUTO ---");
        System.out.print("Digite o código do produto: ");
        String codigo = scanner.nextLine().trim();

        if (Produto.remover(codigo)) {
            System.out.println("✓ Produto removido com sucesso!");
        } else {
            System.out.println("❌ Produto não encontrado!");
        }
    }

    // ========== CLIENTE ==========

    public void cadastrarCliente() {
        System.out.println("\n--- CADASTRO DE CLIENTE ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();

        if (Clientes.buscarPorCPF(cpf) != null) {
            System.out.println("❌ Erro: Cliente com este CPF já existe!");
            return;
        }

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine().trim();

        Clientes cliente = new Clientes(nome, cpf, telefone, email, endereco);
        if (Clientes.cadastrar(cliente)) {
            System.out.println("✓ Cliente cadastrado com sucesso!");
        } else {
            System.out.println("❌ Erro ao cadastrar cliente!");
        }
    }

    public void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        List<Clientes> clientes = Clientes.listar();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Clientes c : clientes) {
            c.exibir();
        }
        System.out.println("Total: " + clientes.size() + " cliente(s)");
    }

    public void buscarCliente() {
        System.out.println("\n--- BUSCA DE CLIENTE ---");
        System.out.print("Digite o termo de busca (CPF ou nome): ");
        String termo = scanner.nextLine().trim();

        List<Clientes> encontrados = Clientes.buscar(termo);

        if (encontrados.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
            return;
        }

        System.out.println("Clientes encontrados:");
        for (Clientes c : encontrados) {
            c.exibir();
        }
    }

    public void removerCliente() {
        System.out.println("\n--- REMOVER CLIENTE ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine().trim();

        if (Clientes.remover(cpf)) {
            System.out.println("✓ Cliente removido com sucesso!");
        } else {
            System.out.println("❌ Cliente não encontrado!");
        }
    }

    // ========== VENDAS ==========

    public void criarVenda() {
        System.out.println("\n--- NOVA VENDA ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine().trim();

        Clientes cliente = Clientes.buscarPorCPF(cpf);
        if (cliente == null) {
            System.out.println("❌ Cliente não encontrado!");
            return;
        }

        Vendas.Venda venda = Vendas.iniciarVenda(cliente);
        System.out.println("Venda iniciada para: " + cliente.getNome());

        while (true) {
            System.out.println("\n1. Adicionar produto");
            System.out.println("2. Ver itens");
            System.out.println("3. Finalizar venda");
            System.out.println("0. Cancelar");
            System.out.print("Opção: ");

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    adicionarItemVenda(venda);
                    break;
                case "2":
                    venda.exibirResumo();
                    break;
                case "3":
                    venda.finalizar();
                    System.out.println("✓ Venda finalizada com sucesso!");
                    return;
                case "0":
                    System.out.println("Venda cancelada.");
                    return;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        }
    }

    private void adicionarItemVenda(Vendas.Venda venda) {
        System.out.print("Digite o código do produto: ");
        String codigo = scanner.nextLine().trim();

        Produto produto = Produto.buscarPorCodigo(codigo);
        if (produto == null) {
            System.out.println("❌ Produto não encontrado!");
            return;
        }

        System.out.println("Produto: " + produto.getNome());
        System.out.println("Preço: R$ " + String.format("%.2f", produto.getPreco()));
        System.out.println("Estoque disponível: " + produto.getEstoque());

        System.out.print("Quantidade: ");
        try {
            int qtd = Integer.parseInt(scanner.nextLine().trim());
            if (qtd <= 0) {
                System.out.println("❌ Quantidade deve ser maior que zero!");
                return;
            }
            if (venda.adicionarItem(produto, qtd)) {
                System.out.println("✓ Item adicionado!");
            } else {
                System.out.println("❌ Erro ao adicionar item (estoque insuficiente)");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Quantidade inválida!");
        }
    }

    public void consultarVendas() {
        System.out.println("\n--- HISTÓRICO DE VENDAS ---");
        List<Vendas.Venda> vendas = Vendas.listarVendas();

        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        for (Vendas.Venda v : vendas) {
            v.exibirResumo();
        }
    }

    public void consultarVendasCliente() {
        System.out.println("\n--- VENDAS POR CLIENTE ---");
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine().trim();

        List<Vendas.Venda> vendas = Vendas.historicoPorCliente(cpf);

        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda encontrada para este cliente.");
            return;
        }

        System.out.println("Vendas encontradas:");
        for (Vendas.Venda v : vendas) {
            v.exibirResumo();
        }
    }

    public String lerOpcao() {
        return scanner.nextLine().trim();
    }

    public void fechar() {
        scanner.close();
    }
}
