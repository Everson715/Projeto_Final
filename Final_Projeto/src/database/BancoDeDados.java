package database;

import filme.Filme;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementação concreta da interface DatabaseOperations para gerenciamento de filmes.
 */
public class BancoDeDados implements DatabaseOperations {
    private List<Filme> movies = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void addMovie(String nome, int classe, int duracao, String genero, List<String> horario) {
        Filme movie = new Filme(nextId++, nome, classe, duracao, genero, horario);
        movies.add(movie);
        System.out.println("Filme adicionado com sucesso: " + movie);
    }

    @Override
    public Optional<Filme> getMovie(int id) {
        return movies.stream().filter(movie -> movie.getId() == id).findFirst();
    }

    @Override
    public void updateMovie(int id, String nome, int classe, int duracao, String genero, List<String> horario) {
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

    @Override
    public void deleteMovie(int id) {
        Optional<Filme> optionalMovie = getMovie(id);
        if (optionalMovie.isPresent()) {
            movies.remove(optionalMovie.get());
            System.out.println("Filme excluído com sucesso");
        } else {
            System.out.println("Filme não encontrado com id: " + id);
        }
    }

    @Override
    public void listMovies() {
        if (movies.isEmpty()) {
            System.out.println("Nenhum filme encontrado.");
        } else {
            movies.forEach(System.out::println);
        }
    }

    @Override
    public void listMoviesWithShowtimes() {
        if (movies.isEmpty()) {
            System.out.println("Nenhum filme encontrado.");
        } else {
            for (Filme movie : movies) {
                System.out.println("ID: " + movie.getId() + "\n" +
                        "Nome: " + movie.getNome() + "\n" +
                        "Horários: " + String.join(", ", movie.getHorario()));
            }
        }
    }

    @Override
    public int getMoviesCount() {
        return movies.size(); // Retorna o número total de filmes
    }
}
