import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GerenciadorProduto gerenciador = new GerenciadorProduto();

        int opcao;

        do {
            System.out.println("\n===== GERENCIADOR DE PRODUTOS =====");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Ver valor total do estoque");
            System.out.println("0 - Sair");
            System.out.print("\nEscolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("\nOpção inválida. Digite um número.");
                scanner.nextLine();
                System.out.print("\nEscolha uma opção: ");
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("\nNome do produto: ");
                    String nome = scanner.nextLine().trim();
                    while (!nome.matches("(?s).*\\p{L}.*")) {
                        System.out.println("\nNome inválido. Digite um nome com pelo menos uma letra.");
                        System.out.print("\nNome do produto: ");
                        nome = scanner.nextLine().trim();
                    }

                    double preco = lerPreco(scanner);

                    int quantidade = lerQuantidade(scanner);

                    System.out.print("\nProduto perecível? (s/n): ");
                    String resposta = scanner.nextLine().trim();
                    while (!resposta.equalsIgnoreCase("s") && !resposta.equalsIgnoreCase("n")) {
                        System.out.println("\nResposta inválida. Digite s ou n.");
                        System.out.print("Produto perecível? (s/n): ");
                        resposta = scanner.nextLine().trim();
                    }

                    boolean perecivel = resposta.equalsIgnoreCase("s");
                    Produto produto;
                    if (perecivel) {
                        String dataValidade = lerDataValidade(scanner);
                        produto = new ProdutoPerecivel(nome, preco, quantidade, dataValidade);
                    } else {
                        produto = new Produto(nome, preco, quantidade);
                    }

                    gerenciador.cadastrarProduto(produto);
                    System.out.println("\nProduto cadastrado com sucesso.");
                    break;

                case 2:
                    if (!gerenciador.possuiProdutos()) {
                        System.out.println("\nNenhum produto cadastrado.");
                    } else {
                        for (Produto item : gerenciador.getProdutos()) {
                            System.out.println(item);
                        }
                    }
                    break;

                case 3:
                    double total = gerenciador.calcularValorTotalEstoque();

                    System.out.printf("\nValor total do estoque: R$ %.2f%n", total);
                    break;

                case 0:
                    System.out.println("\nPrograma encerrado.");
                    break;

                default:
                    System.out.println("\nOpção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static double lerPreco(Scanner scanner) {
        while (true) {
            System.out.print("\nPreço do produto: ");
            String entrada = scanner.nextLine().trim();
            try {
                if (entrada.matches("\\d+(?:[.,]\\d{1,2})?")) {
                    double preco = Double.parseDouble(entrada.replace(',', '.'));
                    if (Double.isFinite(preco)) {
                        return preco;
                    }
                }
            } catch (NumberFormatException ignored) {
                // A entrada será solicitada novamente.
            }
            System.out.println("\nPreço inválido. Digite um valor não negativo com até duas casas decimais.");
        }
    }

    private static String lerDataValidade(Scanner scanner) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        while (true) {
            System.out.print("\nData de validade (dd/MM/aaaa): ");
            String data = scanner.nextLine().trim();
            try {
                LocalDate.parse(data, formato);
                return data;
            } catch (DateTimeParseException ignored) {
                System.out.println("\nData inválida. Use dd/MM/aaaa, por exemplo 30/12/2001.");
            }
        }
    }

    private static int lerQuantidade(Scanner scanner) {
        while (true) {
            System.out.print("\nQuantidade em estoque: ");
            String entrada = scanner.nextLine();
            try {
                int quantidade = Integer.parseInt(entrada);
                if (quantidade >= 0) {
                    return quantidade;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Quantidade inválida. Digite um número inteiro não negativo.");
        }
    }
}
