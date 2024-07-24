package usuarios;

import database.DatabaseOperations;
import filme.Filme;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ADM implements MovieManager {
    private Scanner scanner;
    private final InputHandler inputHandler;
    private DatabaseOperations databaseOperations;

    public ADM(Scanner scanner, DatabaseOperations databaseOperations) {
        this.scanner = scanner;
        this.databaseOperations = databaseOperations;
        this.inputHandler = new MovieInputHandler(scanner);
    }

    @Override
    public void addMovie() {
        String nome = inputHandler.getNome();
        int classe = inputHandler.getClasse();
        int duracao = inputHandler.getDuracao();
        String genero = inputHandler.getGenero();
        List<String> horario = inputHandler.getHorarios();
        try {
            databaseOperations.addMovie(nome, classe, duracao, genero, horario);
            System.out.println("Filme adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar o filme: " + e.getMessage());
        }
    }

    @Override
    public void updateMovie() {
        int id = inputHandler.getId();
        String nome = inputHandler.getNome();
        int classe = inputHandler.getClasse();
        int duracao = inputHandler.getDuracao();
        String genero = inputHandler.getGenero();
        List<String> horario = inputHandler.getHorarios();
        try {
            databaseOperations.updateMovie(id, nome, classe, duracao, genero, horario);
            System.out.println("Filme atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o filme: " + e.getMessage());
        }
    }

    @Override
    public void deleteMovie() {
        boolean validInput = false;
        int id = -1;

        while (!validInput) {
            try {
                id = inputHandler.getId();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número inteiro para o ID.");
                inputHandler.clearInput(); // Assuming inputHandler has a method to clear invalid input
            }
        }

        try {
            databaseOperations.deleteMovie(id);
            System.out.println("Filme excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir o filme: " + e.getMessage());
        }
    }


    @Override
    public void listMovies() {
        try {
            // Tenta listar os filmes
            databaseOperations.listMovies();

        } catch (Exception e) {
            // Captura qualquer exceção e informa o erro
            System.out.println("Erro ao listar filmes: " + e.getMessage());
        }
        try {
            if (databaseOperations.getMoviesCount() == 0) { // Supondo que `getMoviesCount` retorne a quantidade de filmes
                System.out.println("Não há filmes no banco de dados.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar filmes no banco de dados: " + e.getMessage());
        }
        System.out.println("Voltando ao menu ADM...");
    }


    @Override
    public void selectMovieAndShowtime() {
        databaseOperations.listMoviesWithShowtimes();
        int movieId = inputHandler.getId();
        try {
            Optional<Filme> movieOpt = databaseOperations.getMovie(movieId);
            if (movieOpt.isPresent()) {
                Filme movie = movieOpt.get();
                System.out.println("Filme selecionado: " + movie.getNome());
                System.out.println("Horários disponíveis: " + movie.getHorario());
                String horario = inputHandler.getHorarioSelecionado();
                if (movie.getHorario().contains(horario)) {
                    System.out.println("Você selecionou o filme " + movie.getNome() + " às " + horario);
                } else {
                    System.out.println("Horário selecionado inválido.");
                }
            } else {
                System.out.println("Filme não encontrado com id: " + movieId);
            }
        } catch (Exception e) {
            System.out.println("Erro ao selecionar o filme e horário: " + e.getMessage());
        }
    }

    @Override
    public Optional<Filme> getMovie(int id) {
        try {
            return databaseOperations.getMovie(id);
        } catch (Exception e) {
            System.out.println("Erro ao buscar o filme: " + e.getMessage());
            return Optional.empty();
        }
    }
}
