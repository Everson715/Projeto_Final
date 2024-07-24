import filme.Filme;
import usuarios.ADM;
import usuarios.Cliente;
import usuarios.MovieInputHandler;
import database.DatabaseOperations;
import database.BancoDeDados;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Implementação concreta do BancoDeDados
        DatabaseOperations databaseOperations = new BancoDeDados() {
            @Override
            public void addMovie(String nome, int classe, int duracao, String genero, List<String> horario) {
                super.addMovie(nome, classe, duracao, genero, horario);
            }

            @Override
            public Optional<Filme> getMovie(int id) {
                return super.getMovie(id);
            }

            @Override
            public void updateMovie(int id, String nome, int classe, int duracao, String genero, List<String> horario) {
                super.updateMovie(id, nome, classe, duracao, genero, horario);
            }

            @Override
            public void deleteMovie(int id) {
                super.deleteMovie(id);
            }

            @Override
            public void listMovies() {
                super.listMovies();
            }

            @Override
            public void listMoviesWithShowtimes() {
                super.listMoviesWithShowtimes();
            }

            @Override
            public int getMoviesCount() {
                return super.getMoviesCount();
            }
        };

        // Implementação do MovieInputHandler
        MovieInputHandler inputHandler = new MovieInputHandlerImpl(scanner); // Supondo que MovieInputHandlerImpl é a implementação concreta

        boolean continuar = true;

        while (continuar) {
            System.out.println("Escolha o tipo de acesso:");
            System.out.println("1. ADM");
            System.out.println("2. Usuário");
            System.out.println("3. Sair");

            int opcao = -1; // Inicializa com um valor inválido

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha deixada pelo nextInt()
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); // Limpa a entrada inválida
                continue; // Retorna ao início do loop
            }

            switch (opcao) {
                case 1:
                    ADM adm = new ADM(scanner, databaseOperations); // Passa o Scanner e DatabaseOperations para a instância ADM
                    boolean adminMenu = true;
                    while (adminMenu) {
                        System.out.println("Menu ADM:");
                        System.out.println("1. Adicionar filme");
                        System.out.println("2. Atualizar filme");
                        System.out.println("3. Excluir filme");
                        System.out.println("4. Listar filmes");
                        System.out.println("5. Voltar ao menu principal");

                        int adminOpcao = -1;

                        try {
                            adminOpcao = scanner.nextInt();
                            scanner.nextLine(); // Consumir a nova linha deixada pelo nextInt()
                        } catch (InputMismatchException e) {
                            System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                            scanner.nextLine(); // Limpa a entrada inválida
                            continue; // Retorna ao início do loop
                        }

                        switch (adminOpcao) {
                            case 1:
                                adm.addMovie();
                                break;
                            case 2:
                                adm.updateMovie();
                                break;
                            case 3:
                                adm.deleteMovie();
                                break;
                            case 4:
                                adm.listMovies();
                                break;
                            case 5:
                                adminMenu = false; // Voltar ao menu principal
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }
                    }
                    break;
                case 2:
                    Cliente usuario = new Cliente(scanner);
                    usuario.mostrarFilmesEIniciarCompra(); // Mostra filmes e inicia compra
                    break;
                case 3:
                    continuar = false; // Encerra o loop e sai do programa
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close(); // Fechando o Scanner ao final do uso
    }
}
