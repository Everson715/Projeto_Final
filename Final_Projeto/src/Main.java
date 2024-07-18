import usuarios.ADM;
import usuarios.Cliente;
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
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    ADM adm = new ADM();
                    adm.gerenciarFilmes();
                    break;
                case 2:
                    Cliente usuario = new Cliente();
                    usuario.iniciarCompra();
                    break;
                case 3:
                    continuar = false;
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
