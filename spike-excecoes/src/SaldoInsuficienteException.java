// Exceção personalizada: erro de regra de negócio (saldo insuficiente)
// extends Exception = é uma exceção "checked", o Java obriga a tratar
public class SaldoInsuficienteException extends Exception {

    // recebe a mensagem que vai aparecer para o usuário
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}