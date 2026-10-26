// Conta bancária simples, sem banco de dados
public class ContaBancaria {

    private String titular;
    private double saldo;

    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    // depositar: só aceita valor maior que zero
    public void depositar(double valor) {
        if (valor <= 0) {
            // throw: lança o erro quando o valor é inválido
            throw new IllegalArgumentException("O valor do depósito deve ser maior que zero.");
        }
        saldo = saldo + valor;
    }

    // sacar: valida o valor e também o saldo
    // throws: avisa que este método pode lançar a exceção personalizada
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
        }
        if (valor > saldo) {
            throw new SaldoInsuficienteException(
                    "Saldo insuficiente. Saldo atual: R$ " + saldo + ", saque pedido: R$ " + valor);
        }
        saldo = saldo - valor;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
}