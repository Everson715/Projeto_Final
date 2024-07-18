package ingresso;

import java.util.Scanner;
import java.io.Serializable;

public class Ingresso implements Serializable {

    // Atributos
    private static final long serialVersionUID = 1L;
    private final double valor;

    // Construtor
    public Ingresso(double valor) {
        this.valor = valor;
    }

    // Método que solicita ao usuário quantos ingressos ele deseja ter
    public int solicitarQuantidadeIngressos(Scanner scanner) {
        while (true) {
            System.out.println("Quantos ingressos você deseja comprar?");
            String entrada = scanner.nextLine();

            try {
                int quantidade = Integer.parseInt(entrada);
                if (quantidade > 0) {
                    return quantidade;
                } else {
                    System.out.println("Erro: a quantidade de ingressos deve ser maior que zero. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: o valor digitado não é um número válido. Tente novamente.");
            }
        }
    }

    // Método que pergunta ao usuário qual tipo de ingresso ele deseja comprar
    public double solicitarTipoIngressos(int quantidade, Scanner scanner) {
        double valorTotal = 0.0;

        for (int i = 1; i <= quantidade; i++) {
            while (true) {
                System.out.println("Digite o tipo do ingresso " + i + " (1 para Inteira, 2 para Meia):");
                String tipo = scanner.nextLine();

                if (tipo.equals("1")) {
                    System.out.println("Ingresso Inteira selecionado.");
                    valorTotal += valor;
                    break;
                } else if (tipo.equals("2")) {
                    System.out.println("Ingresso Meia selecionado.");
                    valorTotal += valor / 2;
                    break;
                } else {
                    System.out.println("Erro: Tipo de ingresso inválido. Tente novamente.");
                }
            }
        }

        return valorTotal;
    }

    // Método que calcula o valor final baseado na quantidade de ingressos que o usuário digitou
    public double calcularValorFinal(int quantidade) {
        return valor * quantidade;
    }
}
