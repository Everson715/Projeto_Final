package usuarios;

import filme.CriarFilme;
import filme.ExcluirFilme;
import java.util.InputMismatchException;
import java.util.List;
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

            int opcao = -1; // Inicializa com um valor inválido

            try {
                if (scanner.hasNextInt()) {
                    opcao = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha deixada pelo nextInt()
                } else {
                    System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                    scanner.next(); // Limpa a entrada inválida
                    continue; // Retorna ao início do loop
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                scanner.next(); // Limpa a entrada inválida
                continue; // Retorna ao início do loop
            }

            switch (opcao) {
                case 1:
                    CriarFilme filme = CriarFilme.criarNovoFilme();
                    System.out.println(filme);
                    break;
                case 2:
                    List<CriarFilme> filmes = CriarFilme.filmesCriados;
                    if (filmes.isEmpty()) {
                        System.out.println("Nenhum filme para excluir.");
                    } else {
                        ExcluirFilme.excluirFilme(); // Verifica se o método existe em ExcluirFilme
                    }
                    break;
                case 3:
                    continuar = false; // Encerra o loop
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        // scanner.close(); // Não feche o Scanner aqui para não fechar a entrada padrão do sistema
    }
}
