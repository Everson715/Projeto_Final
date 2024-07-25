package database;

import filme.Filme;

import java.util.List;
import java.util.Optional;

public abstract class Database implements DatabaseOperations { /*Classe abstrata*/
    private List<Filme> filmes; // Presume que você tenha uma lista de filmes

    // Implementação de outros métodos...

    @Override
    public void listMoviesWithShowtimes() {
        for (Filme filme : filmes) {
            System.out.println("Filme: " + filme.getNome());
            System.out.println("Horários: " + filme.getHorario());
            System.out.println("Classificação Indicativa: " + filme.getClasse() + " anos");
            System.out.println("----------------------------------------");
        }
    }
}
