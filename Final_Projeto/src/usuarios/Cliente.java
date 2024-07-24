package usuarios;

import database.DatabaseOperations;
import filme.Filme;
import tratarDados.TesteDeConfirmacao;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Cliente {
    private Scanner scanner;
    private Filme filmeSelecionado;
    private String horarioSelecionado;
    private int salaSelecionada; // Armazenar o ID do filme como sala
    private DatabaseOperations databaseOperations; // Instância para operações de banco de dados

    public Cliente(Scanner scanner, DatabaseOperations databaseOperations) {
        this.scanner = scanner; // Inicialização correta do scanner
        this.databaseOperations = databaseOperations; // Inicializa a instância de DatabaseOperations
    }

    // Método para mostrar filmes em cartaz e iniciar a compra
    public void mostrarFilmesEIniciarCompra() {
        try {
            databaseOperations.listMoviesWithShowtimes(); // Lista filmes e horários disponíveis
        } catch (Exception e) {
            System.out.println("Erro ao listar filmes com horários: " + e.getMessage());
            return; // Interrompe a execução se houver erro ao listar filmes
        }

        // Seleciona o filme e o horário
        selectMovieAndShowtime();

        // Prossegue com a compra
        iniciarCompra();
    }

    // Método para selecionar o filme e o horário
    private void selectMovieAndShowtime() {
        System.out.print("Digite o id do filme a ser selecionado: ");
        try {
            salaSelecionada = scanner.nextInt(); // Armazena o ID do filme como sala
            scanner.nextLine(); // Consumir a nova linha
        } catch (InputMismatchException e) {
            System.out.println("Erro: ID do filme inválido. Por favor, digite um número.");
            scanner.nextLine(); // Limpar o buffer
            selectMovieAndShowtime(); // Permite ao usuário tentar novamente
            return;
        }

        Optional<Filme> movieOpt;
        try {
            movieOpt = databaseOperations.getMovie(salaSelecionada);
        } catch (Exception e) {
            System.out.println("Erro ao buscar filme: " + e.getMessage());
            return; // Interrompe a execução se houver erro ao buscar filme
        }

        if (movieOpt.isPresent()) {
            Filme movie = movieOpt.get();
            filmeSelecionado = movie; // Armazena o filme selecionado
            System.out.println("Filme selecionado: " + movie.getNome());
            System.out.println("Horários disponíveis: " + movie.getHorario());
            System.out.println("Gênero do Filme: " + movie.getGenero());

            System.out.print("Digite o horário a ser selecionado (ex.: 10:00): ");
            String horario = scanner.nextLine();
            if (movie.getHorario().contains(horario)) {
                horarioSelecionado = horario; // Armazena o horário selecionado
                System.out.println("Você selecionou o filme " + movie.getNome() + " às " + horario);
            } else {
                System.out.println("Horário selecionado inválido.");
                selectMovieAndShowtime(); // Permite ao usuário tentar novamente
            }
        } else {
            System.out.println("Filme não encontrado com id: " + salaSelecionada);
        }
    }

    // Método para iniciar a compra
    private void iniciarCompra() {
        TesteDeConfirmacao teste = new TesteDeConfirmacao(scanner); // Passar o scanner para TesteDeConfirmacao
        boolean compraConcluida;
        try {
            compraConcluida = teste.processarCompra();
        } catch (Exception e) {
            System.out.println("Erro ao processar a compra: " + e.getMessage());
            return; // Interrompe a execução se houver erro ao processar a compra
        }

        if (compraConcluida) {
            // Exibe as informações da compra
            exibirResumoCompra();
        }
    }

    // Método para exibir o resumo da compra
    private void exibirResumoCompra() {
        if (filmeSelecionado != null && horarioSelecionado != null) {
            System.out.println("Resumo da Compra:");
            System.out.println("Filme: " + filmeSelecionado.getNome());
            System.out.println("Horário: " + horarioSelecionado);
            System.out.println("Sala: " + salaSelecionada); // Exibe o ID do filme como sala
        } else {
            System.out.println("Resumo da compra não disponível.");
        }
    }
}
