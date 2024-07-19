package filme;

import java.util.Scanner;

public class ExcluirFilme {

    public static void excluirFilme() {
        Scanner scanner = new Scanner(System.in);

        if (CriarFilme.filmesCriados.isEmpty()) {
            System.out.println("Nenhum filme disponível para exclusão.");
            return;
        }

        System.out.println("Filmes disponíveis:");
        for (int i = 0; i < CriarFilme.filmesCriados.size(); i++) {
            System.out.println((i + 1) + ". " + CriarFilme.filmesCriados.get(i).getNome());
        }

        System.out.println("Digite o número do filme que deseja excluir:");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha deixada pelo nextInt()

        if (opcao > 0 && opcao <= CriarFilme.filmesCriados.size()) {
            CriarFilme filmeParaExcluir = CriarFilme.filmesCriados.get(opcao - 1);
            CriarFilme.removerFilme(filmeParaExcluir);
            System.out.println("Filme '" + filmeParaExcluir.getNome() + "' excluído com sucesso.");
        } else {
            System.out.println("Opção inválida.");
        }
    }
}
