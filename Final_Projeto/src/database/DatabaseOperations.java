package database;

import filme.Filme;

import java.util.List;
import java.util.Optional;

public interface DatabaseOperations {
    void addMovie(String nome, int classe, int duracao, String genero, List<String> horario);
    Optional<Filme> getMovie(int id);
    void updateMovie(int id, String nome, int classe, int duracao, String genero, List<String> horario);
    void deleteMovie(int id);
    void listMovies();
    void listMoviesWithShowtimes();
    int getMoviesCount();
}
