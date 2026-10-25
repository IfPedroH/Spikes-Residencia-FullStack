import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ProdutoPerecivel extends Produto {

    private final String dataValidade;

    public ProdutoPerecivel(String nome, double preco, int quantidade, String dataValidade) {
        super(nome, preco, quantidade);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(dataValidade, formato);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new IllegalArgumentException("Data de validade deve usar o formato dd/MM/aaaa.");
        }
        this.dataValidade = dataValidade;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    @Override
    public String toString() {
        return super.toString() + " | Validade: " + dataValidade;
    }
}
