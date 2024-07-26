package tratarDados;

import pagamento.Cartao;
import pagamento.Pix;
import ingresso.Ingresso;

import java.util.Scanner;

public class TesteDeConfirmacao {

    // Atributos
    private Scanner scanner;

    // Construtor
    public TesteDeConfirmacao(Scanner scanner) {
        this.scanner = scanner;
    }

    // Método para verificar se o CPF contém apenas números
    public String receberCpf() {
        while (true) {
            System.out.println("Digite o CPF do comprador:");
            String cpf = scanner.nextLine();

            if (cpf.matches("\\d{11}")) { // Verifica se o CPF tem exatamente 11 dígitos
                return formatarCpf(cpf); // Formata o CPF antes de retornar
            } else {
                System.out.println("Erro: o CPF deve conter exatamente 11 números. Tente novamente.");
            }
        }
    }

    // Método para formatar o CPF no padrão ###.###.###-##
    public String formatarCpf(String cpf) {
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }

    // Método para verificar se a compra foi confirmada ou não
    public boolean confirmarCompra() {
        while (true) {
            System.out.println("Deseja confirmar a compra? (1 para Sim, 2 para Não)");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equals("1") || confirmacao.equalsIgnoreCase("Sim")) {
                System.out.println("Pagamento realizado com sucesso!");
                return true;
            } else if (confirmacao.equals("2") || confirmacao.equalsIgnoreCase("Não")) {
                System.out.println("Pagamento cancelado com sucesso!");
                return false;
            } else {
                System.out.println("Erro ao realizar a compra! Escolha novamente.");
            }
        }
    }

    public boolean processarCompra() {

        System.out.println("Iniciando Compra");

        boolean continuarComprando = true;

        while (continuarComprando) {
            System.out.print("Digite o nome do titular: ");
            String nomeComprador = scanner.nextLine();

            // Verificar se a variável nomeTitular possui apenas letras e espaços
            if (!nomeComprador.matches("[a-zA-Z ]+")) {
                System.out.println("Erro: O nome do titular deve conter apenas letras e espaços.");
                continue;
            }

            String cpf = receberCpf();
            Ingresso ingresso = new Ingresso(35.0); // valor do ingresso

            int quantidadeIngressos = ingresso.solicitarQuantidadeIngressos(scanner);
            double valorFinal = ingresso.solicitarTipoIngressos(quantidadeIngressos, scanner);

            if (confirmarCompra()) {
                while (true) {
                    System.out.println("Digite a escolha de pagamento (1 para PIX, 2 para Cartão): ");
                    String escolha = scanner.nextLine();

                    if (escolha.equals("1") || escolha.equalsIgnoreCase("Pix")) {
                        String chavePixEstabelecimento = "u123y8ur891iu21h-9u1h931";
                        System.out.println("Copie a chave PIX do estabelecimento: " + chavePixEstabelecimento);

                        // Simulação de confirmação de pagamento
                        Pix pagamento = new Pix(chavePixEstabelecimento, valorFinal, cpf, nomeComprador);
                        System.out.println(pagamento);

                        break; // Saída do loop
                    } else if (escolha.equals("2") || escolha.equalsIgnoreCase("Cartao") || escolha.equalsIgnoreCase("Cartão")) {
                        Cartao cartao = new Cartao();

                        cartao.capturarDados(scanner);
                        cartao.identificarBandeira();
                        cartao.capturarCvc(scanner);
                        cartao.capturarTipoDePagamento(scanner);
                        cartao.setValor(valorFinal); // Define o valor final no cartão

                        // Simulação de confirmação de pagamento
                        System.out.println(cartao);

                        break; // Saída do loop principal
                    } else {
                        System.out.println("Escolha inválida, por favor tente novamente.");
                    }
                }
            } else {
                System.out.println("A compra não foi confirmada. Processo encerrado.");
            }

            while (true) {
                System.out.println("Deseja realizar outra compra? (1 para Sim, 2 para Não)");
                String resposta = scanner.nextLine();

                if (resposta.equals("1") || resposta.equalsIgnoreCase("Sim")) {
                    continuarComprando = true;
                    break;
                } else if (resposta.equals("2") || resposta.equalsIgnoreCase("Não")) {
                    continuarComprando = false;
                    break;
                } else {
                    System.out.println("Resposta inválida. Digite 1 para Sim ou 2 para Não.");
                }
            }
        }

        System.out.println("Processo encerrado.");
        return !continuarComprando; // Retorna true se o usuário não quiser continuar comprando
    }
}
