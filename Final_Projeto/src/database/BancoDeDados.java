package database;

import filme.Filme;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BancoDeDados{
    private static List<Filme> movies = new ArrayList<>();
    private static int nextId = 1;

    public static void addMovie(String nome, int classe, int duracao, String genero, List<String> horario) {
        Filme movie = new Filme(nextId++, nome, classe, duracao, genero, horario);
        movies.add(movie);
        System.out.println("Movie added successfully: " + movie);
    }

    public static Optional<Filme> getMovie(int id) {
        return movies.stream().filter(movie -> movie.getId() == id).findFirst();
    }

    public static void updateMovie(int id, String nome, int classe, int duracao, String genero, List<String> horario) {
        Optional<Filme> optionalMovie = getMovie(id);
        if (optionalMovie.isPresent()) {
            Filme movie = optionalMovie.get();
            movie.setNome(nome);
            movie.setClasse(classe);
            movie.setDuracao(duracao);
            movie.setGenero(genero);
            movie.setHorario(horario);
            System.out.println("Filme atualizado com sucesso: " + movie);
        } else {
            System.out.println("Filme não encontrado com id: " + id);
        }
    }

    public static void deleteMovie(int id) {
        Optional<Filme> optionalMovie = getMovie(id);
        if (optionalMovie.isPresent()) {
            movies.remove(optionalMovie.get());
            System.out.println("Filme excluído com sucesso");
        } else {
            System.out.println("Filme não encontrado com id: " + id);
        }
    }

    public static void listMovies() {
        if (movies.isEmpty()) {
            System.out.println("Nenhum filme encontrado.");
        } else {
            movies.forEach(System.out::println);
        }
    }

    public static void listMoviesWithShowtimes() {
        if (movies.isEmpty()) {
            System.out.println("Nenhum filme encontrado.");
        } else {
            for (Filme movie : movies) {
                System.out.println("ID: " + movie.getId() + "\n" +
                        "Nome : " + movie.getNome() + "\n" +
                        "Horario : " + movie.getHorario());
            }
        }
    }
}
