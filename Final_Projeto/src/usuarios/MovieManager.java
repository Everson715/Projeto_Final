package usuarios;

import filme.Filme;

import java.util.Optional;

public interface MovieManager {
    void addMovie();
    void updateMovie();
    void deleteMovie();
    void listMovies();
    void selectMovieAndShowtime();
    Optional<Filme> getMovie(int id);
}
