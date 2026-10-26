public class Main {

    public static void main(String[] args) {

        ContaBancaria conta = new ContaBancaria("Maria", 100.0);
        System.out.println("Conta de " + conta.getTitular() + " criada. Saldo inicial: R$ " + conta.getSaldo());

        // Teste 1: depósito válido
        System.out.println("\n--- Teste 1: depósito válido ---");
        try {
            System.out.println("Tentando depositar R$ 50.0");
            conta.depositar(50.0);
            System.out.println("Depósito realizado. Saldo atual: R$ " + conta.getSaldo());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            // finally: roda sempre, deu erro ou não
            System.out.println("Fim do teste 1.");
        }

        // Teste 2: saque válido
        System.out.println("\n--- Teste 2: saque válido ---");
        try {
            System.out.println("Tentando sacar R$ 30.0");
            conta.sacar(30.0);
            System.out.println("Saque realizado. Saldo atual: R$ " + conta.getSaldo());
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            System.out.println("Fim do teste 2.");
        }

        // Teste 3: saque maior que o saldo (exceção personalizada)
        System.out.println("\n--- Teste 3: saldo insuficiente ---");
        try {
            System.out.println("Tentando sacar R$ 500.0");
            conta.sacar(500.0);
            System.out.println("Saque realizado. Saldo atual: R$ " + conta.getSaldo());
        } catch (SaldoInsuficienteException e) {
            // o programa cai aqui em vez de encerrar
            System.out.println("Erro: " + e.getMessage());
        } finally {
            System.out.println("Fim do teste 3.");
        }

        // Teste 4: depósito com valor negativo
        System.out.println("\n--- Teste 4: valor inválido ---");
        try {
            System.out.println("Tentando depositar R$ -10.0");
            conta.depositar(-10.0);
            System.out.println("Depósito realizado. Saldo atual: R$ " + conta.getSaldo());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
            System.out.println("O saldo não mudou: R$ " + conta.getSaldo());
        } finally {
            System.out.println("Fim do teste 4.");
        }

        // se chegou aqui, o programa não encerrou por causa dos erros
        System.out.println("\nO programa continuou rodando. Saldo final: R$ " + conta.getSaldo());
    }
}