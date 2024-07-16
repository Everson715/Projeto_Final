package pagamento;

import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cartao {

    // Atributos
    private String nomeTitular;
    private String numeroCartao;
    private String dataValidade;
    private String bandeira;
    private String tipoDePagamento;
    private String CVC;
    private int parcelas;
    private double valor; // Atributo para armazenar o valor final do ingresso
    private String idPagamento;

    // Método para capturar dados do cartão
    public void capturarDados(Scanner scanner) {
        while (true) {
            System.out.print("Digite o nome do titular: ");
            this.nomeTitular = scanner.nextLine();

            // Verificar se a variável nomeTitular possui apenas letras e espaços
            if (nomeTitular.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.println("Erro: O nome do titular deve conter apenas letras e espaços.");
            }
        }

        while (true) {
            System.out.print("Digite o número do cartão: ");
            this.numeroCartao = scanner.nextLine();

            // Verificação do número do cartão (usando regex simplificado)
            Pattern pattern = Pattern.compile("\\d{16}");
            Matcher matcher = pattern.matcher(numeroCartao);
            if (matcher.matches()) {
                break;
            } else {
                System.out.println("Número do cartão inválido. Deve conter 16 dígitos.");
            }
        }

        while (true) {
            System.out.print("Digite a data de validade (MM/AA): ");
            this.dataValidade = scanner.nextLine();

            // Verificação da data de validade (usando regex simplificado)
            Pattern pattern = Pattern.compile("(0[1-9]|1[0-2])/\\d{2}");
            Matcher matcher = pattern.matcher(dataValidade);
            if (matcher.matches()) {
                break;
            } else {
                System.out.println("Data de validade inválida. Use o formato MM/AA.");
            }
        }
    }

    // Método para identificar a bandeira do cartão
    public void identificarBandeira() {
        if (numeroCartao.startsWith("4")) {
            bandeira = "Visa";
        } else if (numeroCartao.matches("^5[1-5].*")) {
            bandeira = "MasterCard";
        } else if (numeroCartao.matches("^3[47].*")) {
            bandeira = "American Express";
        } else if (numeroCartao.matches("^(6|7|8|9).*")) {
            bandeira = "ELO";
        } else {
            bandeira = "Desconhecida";
        }
    }

    // Método para conferir o código CVC do cartão
    public void capturarCvc(Scanner scanner) {
        while (true) {
            System.out.print("Digite o código CVV do seu cartão: ");
            CVC = scanner.nextLine();
            if (CVC.length() == 3 && CVC.matches("\\d+")) {
                break;
            } else {
                System.out.println("Erro: Código CVV deve ter exatamente 3 dígitos numéricos.");
            }
        }
    }

    // Método para informar o tipo de pagamento com o cartão
    public void capturarTipoDePagamento(Scanner scanner) {
        while (true) {
            System.out.print("Digite o tipo de pagamento (Crédito/Débito): ");
            this.tipoDePagamento = scanner.nextLine().trim().toLowerCase();

            if (tipoDePagamento.equalsIgnoreCase("crédito") || tipoDePagamento.equalsIgnoreCase("credito")) {
                this.tipoDePagamento = "crédito"; // Normaliza para evitar problemas com acentuação
                perguntarParcelas(scanner); // Chama o método para perguntar sobre as parcelas se for crédito
                break;
            } else if (tipoDePagamento.equalsIgnoreCase("débito") || tipoDePagamento.equalsIgnoreCase("debito")) {
                this.tipoDePagamento = "débito"; // Normaliza para evitar problemas com acentuação
                this.parcelas = 1; // Define parcelas como 1 para débito
                break; // Não precisa perguntar sobre parcelas se for débito
            } else {
                System.out.println("Tipo de pagamento inválido. Por favor, digite Crédito ou Débito.");
            }
        }
        // Gerar ID do pagamento
        gerarIdPagamento();
    }

    // Método para perguntar ao usuário em quantas vezes ele deseja parcelar a compra
    public void perguntarParcelas(Scanner scanner) {
        if (!tipoDePagamento.equalsIgnoreCase("débito")) {
            while (true) {
                System.out.print("Digite em quantas vezes deseja parcelar a compra (até 3 vezes): ");
                String entrada = scanner.nextLine();

                try {
                    int numeroParcelas = Integer.parseInt(entrada);
                    if (numeroParcelas >= 1 && numeroParcelas <= 3) {
                        this.parcelas = numeroParcelas;
                        break;
                    } else {
                        System.out.println("Número de parcelas inválido. Digite um número entre 1 e 3.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Digite um número válido para as parcelas.");
                }
            }
        }
    }

    // Método para definir o valor do ingresso
    public void setValor(double valor) {
        this.valor = valor;
    }

    // Método para formatar o número do cartão
    private String formatarNumeroCartao() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numeroCartao.length(); i++) {
            if (i > 0 && i % 4 == 0) {
                sb.append(" ");
            }
            sb.append(numeroCartao.charAt(i));
        }
        return sb.toString();
    }

    // Método para gerar o ID do pagamento
    public void gerarIdPagamento() {
        this.idPagamento = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        double valorParcela = 0;
        if (parcelas > 0) {
            valorParcela = valor / parcelas;
        }
        return "Nome: " + nomeTitular.toUpperCase() + "\n" +
                "Número do Cartão: " + formatarNumeroCartao() + "\n" +
                "Data de Validade: " + dataValidade + "\n" +
                "Bandeira: " + bandeira + "\n" +
                "Código CVC: " + CVC + "\n" +
                "Tipo de Pagamento: " + tipoDePagamento.substring(0, 1).toUpperCase() + tipoDePagamento.substring(1) + "\n" +
                "Valor Total: R$ " + valor + "\n" +
                (parcelas > 0 ? "Valor da Parcela: R$ " + valorParcela + "\n" +
                        "Quantidade de Parcelas: " + parcelas + "\n" : "") +
                "Código da compra: " + idPagamento;
    }

    // Getters e Setters
    public double getValor() {
        return valor;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }

    public String getTipoDePagamento() {
        return tipoDePagamento;
    }

    public void setTipoDePagamento(String tipoDePagamento) {
        this.tipoDePagamento = tipoDePagamento;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getIdPagamento() {
        return idPagamento;
    }
}
