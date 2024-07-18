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
            System.out.println((i + 1) + ". " + CriarFilme.filmesCriados.get(i).getNome() + " (Sala: " + CriarFilme.filmesCriados.get(i).getSala() + ")");
        }

        System.out.println("Digite o número do filme que deseja excluir:");
        int opcao = scanner.nextInt();

        if (opcao < 1 || opcao > CriarFilme.filmesCriados.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        CriarFilme filmeParaExcluir = CriarFilme.filmesCriados.get(opcao - 1);
        CriarFilme.removerFilme(filmeParaExcluir);

        System.out.println("Filme " + filmeParaExcluir.getNome() + " excluído com sucesso! Sala " + filmeParaExcluir.getSala() + " liberada.");
    }
}

