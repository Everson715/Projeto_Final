package usuarios;

import database.BancoDeDados;
import filme.Filme;

import java.util.List;
import java.util.Scanner;

public class ADM {
    private Scanner scanner;

    public ADM(Scanner scanner) {
        this.scanner = scanner; // Correção na inicialização do scanner
    }

    public void addMovie() {
        System.out.print("Digite o nome do filme: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a classificação do filme: ");
        int classe = scanner.nextInt();
        System.out.print("Digite a duração do filme (em minutos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Digite o gênero do filme: ");
        String genero = scanner.nextLine();
        System.out.print("Digite os horários (separados por vírgula, ex.: 10:00,13:00,16:00): ");
        String horarioInput = scanner.nextLine();
        List<String> horario = List.of(horarioInput.split(","));
        BancoDeDados.addMovie(nome, classe, duracao, genero, horario);
    }

    public void getMovie() {
        System.out.print("Digite o id do filme: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        BancoDeDados.getMovie(id).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Filme não encontrado com id: " + id)
        );
    }

    public void updateMovie() {
        System.out.print("Digite o id do filme a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Digite o novo nome do filme: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a nova classificação do filme: ");
        int classe = scanner.nextInt();
        System.out.print("Digite a nova duração do filme (em minutos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        System.out.print("Digite o novo gênero do filme: ");
        String genero = scanner.nextLine();
        System.out.print("Digite os novos horários (separados por vírgula, ex.: 10:00,13:00,16:00): ");
        String horarioInput = scanner.nextLine();
        List<String> horario = List.of(horarioInput.split(","));
        BancoDeDados.updateMovie(id, nome, classe, duracao, genero, horario);
    }

    public void deleteMovie() {
        System.out.print("Digite o id do filme a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        BancoDeDados.deleteMovie(id);
    }

    public void listMovies() {
        BancoDeDados.listMovies();
    }

    public void selectMovieAndShowtime() {
        BancoDeDados.listMoviesWithShowtimes();
        System.out.print("Digite o id do filme a ser selecionado: ");
        int movieId = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        var movieOpt = BancoDeDados.getMovie(movieId);
        if (movieOpt.isPresent()) {
            Filme movie = movieOpt.get();
            System.out.println("Filme selecionado: " + movie.getNome());
            System.out.println("Horários disponíveis: " + movie.getHorario());
            System.out.print("Digite o horário a ser selecionado (ex.: 10:00): ");
            String horario = scanner.nextLine();
            if (movie.getHorario().contains(horario)) {
                System.out.println("Você selecionou o filme " + movie.getNome() + " às " + horario);
            } else {
                System.out.println("Horário selecionado inválido.");
            }
        } else {
            System.out.println("Filme não encontrado com id: " + movieId);
        }
    }
}
