package usuarios;

import filme.CriarFilme;
import filme.ExcluirFilme;
import java.util.Scanner;

public class ADM {

    public void gerenciarFilmes() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Criar novo filme");
            System.out.println("2. Excluir um filme");
            System.out.println("3. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha deixada pelo nextInt()

            switch (opcao) {
                case 1:
                    CriarFilme filme = CriarFilme.criarNovoFilme();
                    System.out.println(filme);
                    break;
                case 2:
                    ExcluirFilme.excluirFilme();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}


