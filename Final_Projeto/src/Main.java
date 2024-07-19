import usuarios.ADM;
import usuarios.Cliente;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Escolha o tipo de acesso:");
            System.out.println("1. ADM");
            System.out.println("2. Usuário");
            System.out.println("3. Sair");

            int opcao = -1; // Inicializa com um valor inválido

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha deixada pelo nextInt()
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); // Limpa a entrada inválida
                continue; // Retorna ao início do loop
            }

            switch (opcao) {
                case 1:
                    ADM adm = new ADM();
                    adm.gerenciarFilmes(); // Verifica se o método existe em ADM
                    break;
                case 2:
                    Cliente usuario = new Cliente();
                    boolean continuarComprando = usuario.iniciarCompra(); // Inicia o processo de compra
                    if (!continuarComprando) {
                        continuar = false; // Encerra o loop e sai do programa se a compra não for concluída
                    }
                    break;
                case 3:
                    continuar = false; // Encerra o loop e sai do programa
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close(); // Fechando o Scanner ao final do uso
    }
}
