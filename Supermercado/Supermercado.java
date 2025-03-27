package Supermercado;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

class Produto {
    String nome;
    double preco;
    double peso;
    int quantidade;
    int codigoProduto;

    public Produto(String nome, double preco, double peso, int quantidade, int codigoProduto) {
        this.nome = nome;
        this.preco = preco;
        this.peso = peso;
        this.quantidade = quantidade;
        this.codigoProduto = codigoProduto;
    }

    public double calcularTotal() {
        return preco * quantidade;
    }

    public double calcularPesoTotal() {
        return peso * quantidade;
    }
}

class Carrinho {
    List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        for (Produto p : produtos) {
            if (p.nome.equals(produto.nome)) {
                p.quantidade += produto.quantidade;
                return;
            }
        }
        produtos.add(produto);
    }

    public void exibirCarrinho() {
        if (produtos.size() == 0) {
            System.out.println("\nSeu carrinho está vazio.");
        } else {
            System.out.println("\nProdutos no carrinho:");
            for (Produto p : produtos) {
                System.out.printf("%d - %s - %d Unidade(s) - R$ %.2f\n", p.codigoProduto, p.nome, p.quantidade, p.calcularTotal());
            }
            System.out.printf("Subtotal: R$ %.2f\n", calcularSubtotal());
        }
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (Produto p : produtos) {
            subtotal += p.calcularTotal();
        }
        return subtotal;
    }

    public boolean removerProdutoPorCodigo(int codigo) {
        for (Produto p : produtos) {
            if (p.codigoProduto == codigo) {
                produtos.remove(p);
                return true;
            }
        }
        return false;
    }
}

class Supermercado {
    private static Scanner scanner = new Scanner(System.in);
    private static String supermercadoEscolhido;
    private static String nomeCliente;
    private static String cpfOuCnpjCliente;
    private static String dataCadastro;
    private static String horaCadastro;
    private static int codigoCliente;
    private static int codigoSupermercado;
    private static final String[][] informacoesSupermercado = {
            {"Bahamas", "17.745.613/0001-50", "Supermercado Bahamas S/A", "Av. Barão do Rio Branco, 700 - Manoel Honório, Juiz de Fora - MG", "36045-120", "123456"},
            {"BH", "04.641.376/0256-35", "Supermercados BH Comércio de Alimentos S/A", "Av. Antônio Simão Firjam, 260 - Distrito Industrial, Juiz de Fora - MG", "36092-000", "654321"},
            {"Bretas", "24.444.127/0016-44", "CENCOSUD BRASIL COMERCIAL S.A", "Av. Barão do Rio Branco, 1973 - Centro, Juiz de Fora - MG", "36013-020", "987654"}
    };

    public static void main(String[] args) {
        Carrinho carrinho = new Carrinho();

        supermercadoEscolhido = escolherSupermercado();
        if (supermercadoEscolhido == null) {
            return;
        }

        while (true) {
            int opcaoSecao = escolherSecao(supermercadoEscolhido);
            if (opcaoSecao == -1) break;

            switch (opcaoSecao) {
                case 1:
                    adicionarLaticinios(carrinho);
                    break;
                case 2:
                    adicionarHortifruti(carrinho);
                    break;
                case 3:
                    adicionarMateriaisLimpeza(carrinho);
                    break;
                case 4:
                    adicionarHigiene(carrinho);
                    break;
                case 5:
                    carrinho.exibirCarrinho();
                    removerProduto(carrinho);
                    break;
                case 6:
                    carrinho.exibirCarrinho();
                    break;
                case 7:
                    finalizarCompra(carrinho);
                    return;
            }
        }
    }

    private static String escolherSupermercado() {
        System.out.println("Escolha um Supermercado:");
        System.out.println("1 - Bahamas");
        System.out.println("2 - BH");
        System.out.println("3 - Bretas");
        System.out.println("4 - Não comprar nada");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1:
                return "Bahamas";
            case 2:
                return "BH";
            case 3:
                return "Bretas";
            case 4:
                System.out.println("Você escolheu não comprar nada. Até mais!");
                return null;
            default:
                System.out.println("Opção Inválida.");
                return escolherSupermercado();
        }
    }

    private static int escolherSecao(String supermercado) {
        System.out.println("\nVocê está no Supermercado " + supermercado);
        System.out.println("Escolha a seção:");
        System.out.println("1 - Seção de Laticínios");
        System.out.println("2 - Seção de Hortifruti");
        System.out.println("3 - Seção de Materiais de Limpeza");
        System.out.println("4 - Seção de Higiene");
        System.out.println("5 - Remover produto");
        System.out.println("6 - Ver Carrinho");
        System.out.println("7 - Finalizar Compra");
        System.out.println("8 - Sair");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha == 8) {
            System.out.println("\nVocê tem certeza que deseja sair?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            int sair = scanner.nextInt();
            if (sair == 1) return -1;
            else return 0;
        }

        return escolha;
    }

    private static void adicionarLaticinios(Carrinho carrinho) {
        System.out.println("\nEscolha o produto de Laticínios:");
        System.out.println("1 - Queijo Mussarela 1 Kg - R$ 25,00");
        System.out.println("2 - Presunto 1 Kg - R$ 18,00");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        Produto produto;
        switch (escolha) {
            case 1:
                produto = new Produto("Queijo Mussarela 1 Kg", 25.00, 1.0, 1, 101);
                carrinho.adicionarProduto(produto);
                break;
            case 2:
                produto = new Produto("Presunto 1 Kg", 18.00, 1.0, 1, 102);
                carrinho.adicionarProduto(produto);
                break;
            default:
                System.out.println("Opção Inválida");
        }
    }

    private static void adicionarHortifruti(Carrinho carrinho) {
        System.out.println("\nEscolha o produto de Hortifruti:");
        System.out.println("1 - Maçã 1 Kg - R$ 6,00");
        System.out.println("2 - Banana 1 Kg - R$ 4,50");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        Produto produto;
        switch (escolha) {
            case 1:
                produto = new Produto("Maçã 1 Kg", 6.00, 1.0, 1, 201);
                carrinho.adicionarProduto(produto);
                break;
            case 2:
                produto = new Produto("Banana 1 Kg", 4.50, 1.0, 1, 202);
                carrinho.adicionarProduto(produto);
                break;
            default:
                System.out.println("Opção Inválida");
        }
    }

    private static void adicionarMateriaisLimpeza(Carrinho carrinho) {
        System.out.println("\nEscolha o produto de Materiais de Limpeza:");
        System.out.println("1 - Sabão em Pó 1 Kg - R$ 8,00");
        System.out.println("2 - Desinfetante 500 ml - R$ 4,50");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        Produto produto;
        switch (escolha) {
            case 1:
                produto = new Produto("Sabão em Pó 1 Kg", 8.00, 1.0, 1, 301);
                carrinho.adicionarProduto(produto);
                break;
            case 2:
                produto = new Produto("Desinfetante 500 ml", 4.50, 0.5, 1, 302);
                carrinho.adicionarProduto(produto);
                break;
            default:
                System.out.println("Opção Inválida");
        }
    }

    private static void adicionarHigiene(Carrinho carrinho) {
        System.out.println("\nEscolha o produto de Higiene:");
        System.out.println("1 - Shampoo 250 ml - R$ 12,00");
        System.out.println("2 - Sabonete 100g - R$ 2,50");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        Produto produto;
        switch (escolha) {
            case 1:
                produto = new Produto("Shampoo 250 ml", 12.00, 0.25, 1, 401);
                carrinho.adicionarProduto(produto);
                break;
            case 2:
                produto = new Produto("Sabonete 100g", 2.50, 0.1, 1, 402);
                carrinho.adicionarProduto(produto);
                break;
            default:
                System.out.println("Opção Inválida");
        }
    }

    private static void removerProduto(Carrinho carrinho) {
        System.out.println("\nDigite o código do produto que deseja remover:");
        int codigoProduto = scanner.nextInt();
        scanner.nextLine();

        boolean removido = carrinho.removerProdutoPorCodigo(codigoProduto);
        if (removido) {
            System.out.println("Produto removido com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void finalizarCompra(Carrinho carrinho) {
        System.out.println("\nEscolha o tipo de pagamento:");
        System.out.println("1 - Cartão de Crédito");
        System.out.println("2 - Dinheiro");

        int pagamento = scanner.nextInt();
        scanner.nextLine();

        if (pagamento == 1) {
            System.out.println("Pagamento via Cartão de Crédito.");
        } else if (pagamento == 2) {
            System.out.println("Pagamento via Dinheiro.");
        } else {
            System.out.println("Opção Inválida");
            return;
        }

        System.out.print("Digite o nome completo do cliente: ");
        nomeCliente = scanner.nextLine();

        while (nomeCliente.isEmpty() || nomeCliente.contains(".")) {
            System.out.println("Nome inválido! O nome não pode estar vazio ou conter pontos.");
            System.out.print("Digite o nome completo do cliente: ");
            nomeCliente = scanner.nextLine();
        }

        System.out.println("Digite CPF ou CNPJ (Somente números): ");
        cpfOuCnpjCliente = scanner.nextLine();

        while (!validarCpfOuCnpj(cpfOuCnpjCliente)) {
            System.out.println("Opção inválida! Por favor, digite um CPF (11 dígitos) ou CNPJ (14 dígitos) válido.");
            cpfOuCnpjCliente = scanner.nextLine();
        }

        Random rand = new Random();
        codigoCliente = rand.nextInt(90000) + 10000;

        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
        dataCadastro = sdfData.format(new Date());
        horaCadastro = sdfHora.format(new Date());

        System.out.printf("\nCliente cadastrado! Data do cadastro: %s, Hora do cadastro: %s\n", dataCadastro, horaCadastro);

        exibirCupomFiscal(carrinho);

        System.exit(0);
    }

    private static boolean validarCpfOuCnpj(String cpfOuCnpj) {
        return (cpfOuCnpj.length() == 11 && cpfOuCnpj.matches("\\d+")) || (cpfOuCnpj.length() == 14 && cpfOuCnpj.matches("\\d+"));
    }

    private static void exibirCupomFiscal(Carrinho carrinho) {
        System.out.println("\n---------- CUPOM FISCAL ----------");
        System.out.println("Supermercado: " + supermercadoEscolhido);
        System.out.println("CNPJ: " + obterCnpjSupermercado(supermercadoEscolhido));
        System.out.println("Razão Social: " + obterRazaoSocialSupermercado(supermercadoEscolhido));
        System.out.println("Endereço: " + obterEnderecoSupermercado(supermercadoEscolhido));
        System.out.println("CEP: " + obterCepSupermercado(supermercadoEscolhido));
        System.out.println("Código Supermercado: " + obterCodigoSupermercado(supermercadoEscolhido));
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("CPF/CNPJ do Cliente: " + cpfOuCnpjCliente);
        System.out.println("Código Cliente: " + codigoCliente);
        System.out.println("Data do Cadastro: " + dataCadastro);
        System.out.println("Hora do Cadastro: " + horaCadastro);
        carrinho.exibirCarrinho();
    }

    private static String obterCnpjSupermercado(String supermercado) {
        for (String[] info : informacoesSupermercado) {
            if (info[0].equalsIgnoreCase(supermercado)) {
                return info[1];
            }
        }
        return "CNPJ não encontrado";
    }

    private static String obterRazaoSocialSupermercado(String supermercado) {
        for (String[] info : informacoesSupermercado) {
            if (info[0].equalsIgnoreCase(supermercado)) {
                return info[2];
            }
        }
        return "Razão Social não encontrada";
    }

    private static String obterEnderecoSupermercado(String supermercado) {
        for (String[] info : informacoesSupermercado) {
            if (info[0].equalsIgnoreCase(supermercado)) {
                return info[3];
            }
        }
        return "Endereço não encontrado";
    }

    private static String obterCepSupermercado(String supermercado) {
        for (String[] info : informacoesSupermercado) {
            if (info[0].equalsIgnoreCase(supermercado)) {
                return info[4];
            }
        }
        return "CEP não encontrado";
    }

    private static String obterCodigoSupermercado(String supermercado) {
        for (String[] info : informacoesSupermercado) {
            if (info[0].equalsIgnoreCase(supermercado)) {
                return info[5];
            }
        }
        return "Código não encontrado";
    }
}
