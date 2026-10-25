import java.util.ArrayList;
import java.util.List;

public class GerenciadorProduto {

    private final List<Produto> produtos = new ArrayList<>();

    public void cadastrarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }
        produtos.add(produto);
    }

    public List<Produto> getProdutos() {
        return new ArrayList<>(produtos);
    }

    public boolean possuiProdutos() {
        return !produtos.isEmpty();
    }

    public double calcularValorTotalEstoque() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.calcularValorEstoque();
        }
        return total;
    }
}