package Atividade;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        boolean executando = true;

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  BEM-VINDO AO SISTEMA DE VENDAS 1.0   ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        // Adicionar alguns produtos de exemplo
        adicionarDadosExemplo();

        while (executando) {
            menu.exibirMenuPrincipal();
            String opcao = menu.lerOpcao();

            switch (opcao) {
                case "1":
                    menuProdutos(menu);
                    break;
                case "2":
                    menuClientes(menu);
                    break;
                case "3":
                    menu.criarVenda();
                    break;
                case "4":
                    menuVendas(menu);
                    break;
                case "0":
                    System.out.println("\n✓ Encerrando sistema...");
                    executando = false;
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        }

        menu.fechar();
    }

    private static void menuProdutos(Menu menu) {
        boolean voltar = false;

        while (!voltar) {
            menu.exibirMenuProdutos();
            String opcao = menu.lerOpcao();

            switch (opcao) {
                case "1":
                    menu.cadastrarProduto();
                    break;
                case "2":
                    menu.listarProdutos();
                    break;
                case "3":
                    menu.buscarProduto();
                    break;
                case "4":
                    menu.atualizarEstoque();
                    break;
                case "5":
                    menu.removerProduto();
                    break;
                case "0":
                    voltar = true;
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        }
    }

    private static void menuClientes(Menu menu) {
        boolean voltar = false;

        while (!voltar) {
            menu.exibirMenuClientes();
            String opcao = menu.lerOpcao();

            switch (opcao) {
                case "1":
                    menu.cadastrarCliente();
                    break;
                case "2":
                    menu.listarClientes();
                    break;
                case "3":
                    menu.buscarCliente();
                    break;
                case "4":
                    menu.removerCliente();
                    break;
                case "0":
                    voltar = true;
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        }
    }

    private static void menuVendas(Menu menu) {
        boolean voltar = false;

        while (!voltar) {
            menu.exibirMenuVendas();
            String opcao = menu.lerOpcao();

            switch (opcao) {
                case "1":
                    menu.criarVenda();
                    break;
                case "2":
                    menu.consultarVendas();
                    break;
                case "3":
                    menu.consultarVendasCliente();
                    break;
                case "0":
                    voltar = true;
                    break;
                default:
                    System.out.println("❌ Opção inválida!");
            }
        }
    }

    private static void adicionarDadosExemplo() {
        // Produtos de exemplo
        Produto.cadastrar("P001", "Notebook Dell", "Eletrônicos", 3500.00, 5);
        Produto.cadastrar("P002", "Mouse Logitech", "Acessórios", 85.50, 15);
        Produto.cadastrar("P003", "Teclado Mecânico", "Acessórios", 450.00, 8);
        Produto.cadastrar("P004", "Monitor LG 24\"", "Eletrônicos", 899.00, 3);
        Produto.cadastrar("P005", "Webcam HD", "Acessórios", 250.00, 10);

        // Clientes de exemplo
        Clientes.cadastrar(new Clientes("João Silva", "123.456.789-00", "(11) 99999-1111", "joao@email.com", "Rua A, 100"));
        Clientes.cadastrar(new Clientes("Maria Santos", "987.654.321-00", "(11) 98888-2222", "maria@email.com", "Rua B, 200"));
        Clientes.cadastrar(new Clientes("Pedro Oliveira", "456.789.123-00", "(11) 97777-3333", "pedro@email.com", "Rua C, 300"));

        System.out.println("✓ Dados de exemplo carregados!\n");
    }
}
