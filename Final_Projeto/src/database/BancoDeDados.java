package database;

import filme.Filme;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            System.out.println("Filme não encontrado com Sala: " + id);
        }
    }

    @Override
    public void deleteMovie(int id) {
        try {
            Optional<Filme> optionalMovie = getMovie(id);

            if (optionalMovie.isPresent()) {
                boolean removed = movies.remove(optionalMovie.get());

                if (removed) {
                    System.out.println(" ");
                } else {
                    System.out.println("Erro ao remover o filme. Por favor, tente novamente.");
                }
            } else {
                System.out.println("Filme não encontrado com Sala: " + id);
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar excluir o filme: " + e.getMessage());
            e.printStackTrace(); // Opcional: fornece detalhes adicionais sobre a exceção
        }
    }

    @Override
    public void listMovies() {
        try {
            if (movies.isEmpty()) {
                System.out.println("Nenhum filme encontrado.");
            } else {
                movies.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar listar os filmes: " + e.getMessage());
            e.printStackTrace(); // Opcional: fornece detalhes adicionais sobre a exceção
        }
    }

    @Override // Modificação para incluir classificação indicativa
    public void listMoviesWithShowtimes() {
        if (movies.isEmpty()) {
            System.out.println("Nenhum filme encontrado.");
        } else {
            for (Filme movie : movies) {
                System.out.println("ID: " + movie.getId() + "\n" +
                        "Nome: " + movie.getNome() + "\n" +
                        "Horários: " + String.join(", ", movie.getHorario()) + "\n" +
                        "Classificação Indicativa: " + movie.getClasse() + " anos\n");
            }
        }
    }

    @Override
    public int getMoviesCount() {
        return movies.size(); // Retorna o número total de filmes
    }
}
