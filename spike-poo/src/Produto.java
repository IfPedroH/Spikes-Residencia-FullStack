import java.math.BigDecimal;

public class Produto {

    private final String nome;
    private double preco;
    private int quantidade;

    public Produto(String nome, double preco, int quantidade) {
        if (nome == null || !nome.matches("(?s).*\\p{L}.*")) {
            throw new IllegalArgumentException("O nome deve conter pelo menos uma letra.");
        }
        if (!precoValido(preco)) {
            throw new IllegalArgumentException("Preço deve ser não negativo e ter até duas casas decimais.");
        }
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa.");
        }
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setPreco(double preco) {
        if (!precoValido(preco)) {
            throw new IllegalArgumentException("Preço deve ser positivo, até duas casas decimais é aceitável.");
        }
        this.preco = preco;
    }

    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade adicionada deve ser positiva e inteira.");
        }
        this.quantidade = Math.addExact(this.quantidade, quantidade);
    }

    public double calcularValorEstoque() {
        return preco * quantidade;
    }

    private static boolean precoValido(double preco) {
        return Double.isFinite(preco) && preco >= 0
                && BigDecimal.valueOf(preco).stripTrailingZeros().scale() <= 2;
    }

    @Override
    public String toString() {
        return String.format("%s | Preço: R$ %.2f | Quantidade: %d", nome, preco, quantidade);
    }
}
