import filme.Filme;
import usuarios.ADM;
import usuarios.Cliente;
import usuarios.MovieInputHandler;
import usuarios.MovieInputHandlerImpl; // Importando a implementação concreta
import database.DatabaseOperations;
import database.BancoDeDados; // Certifique-se de que há uma implementação concreta para BancoDeDados

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Implementação concreta do BancoDeDados
        DatabaseOperations databaseOperations = new BancoDeDados() {
            @Override
            public void addMovie(String nome, int classe, int duracao, String genero, List<String> horario) {
                try {
                    super.addMovie(nome, classe, duracao, genero, horario);
                    System.out.println("Filme adicionado com sucesso.");
                } catch (Exception e) {
                    System.out.println("Erro ao adicionar filme: " + e.getMessage());
                }
            }

            @Override
            public Optional<Filme> getMovie(int id) {
                try {
                    return super.getMovie(id);
                } catch (Exception e) {
                    System.out.println("Erro ao buscar filme: " + e.getMessage());
                    return Optional.empty();
                }
            }

            @Override
            public void updateMovie(int id, String nome, int classe, int duracao, String genero, List<String> horario) {
                try {
                    super.updateMovie(id, nome, classe, duracao, genero, horario);
                    System.out.println("Filme atualizado com sucesso.");
                } catch (Exception e) {
                    System.out.println("Erro ao atualizar filme: " + e.getMessage());
                }
            }

            @Override
            public void deleteMovie(int id) {
                try {
                    super.deleteMovie(id);
                    System.out.println("Filme excluído com sucesso.");
                } catch (Exception e) {
                    System.out.println("Erro ao excluir filme: " + e.getMessage());
                }
            }

            @Override
            public void listMovies() {
                try {
                    super.listMovies();
                } catch (Exception e) {
                    System.out.println("Erro ao listar filmes: " + e.getMessage());
                }
            }

            @Override
            public void listMoviesWithShowtimes() {
                try {
                    super.listMoviesWithShowtimes();
                } catch (Exception e) {
                    System.out.println("Erro ao listar filmes com horários: " + e.getMessage());
                }
            }

            @Override
            public int getMoviesCount() {
                try {
                    return super.getMoviesCount();
                } catch (Exception e) {
                    System.out.println("Erro ao contar filmes: " + e.getMessage());
                    return 0;
                }
            }
        };

        // Implementação do MovieInputHandler
        MovieInputHandler inputHandler = new MovieInputHandlerImpl(scanner); // Supondo que MovieInputHandlerImpl é a implementação concreta

        boolean continuar = true;

        while (continuar) {
            // Obter a largura do console
            int larguraConsole = 220; // Ajuste conforme necessário

            // Texto a ser centralizado
            String arteAscii =
                    "\n"+
                    "█████╗ ██████╗ ███████╗ ██████╗ ██╗     ██╗   ██╗████████╗███████╗" + "\n" +
                            "██╔══██╗██╔══██╗██╔════╝██╔═══██╗██║     ██║   ██║╚══██╔══╝██╔════╝" + "\n" +
                            "███████║██████╔╝███████╗██║   ██║██║     ██║   ██║   ██║   █████╗" + "\n" +
                            "██╔══██║██╔══██╗╚════██║██║   ██║██║     ██║   ██║   ██║   ██╔══╝" + "\n" +
                            "██║  ██║██████╔╝███████║╚██████╔╝███████╗╚██████╔╝   ██║   ███████╗" + "\n" +
                            "╚═╝  ╚═╝╚═════╝ ╚══════╝ ╚═════╝ ╚══════╝ ╚═════╝    ╚═╝   ╚══════╝" + "\n" +
                            " ██████╗██╗███╗   ██╗███████╗███╗   ███╗ █████╗" + "\n" +
                            "██╔════╝██║████╗  ██║██╔════╝████╗ ████║██╔══██╗"+ "\n" +
                            "██║     ██║██╔██╗ ██║█████╗  ██╔████╔██║███████║"+ "\n" +
                            "██║     ██║██║╚██╗██║██╔══╝  ██║╚██╔╝██║██╔══██║"+ "\n" +
                            "╚██████╗██║██║ ╚████║███████╗██║ ╚═╝ ██║██║  ██║"+ "\n" +
                            "╚═════╝╚═╝╚═╝  ╚═══╝╚══════╝╚═╝     ╚═╝╚═╝  ╚═╝";

            // Centralizar e imprimir o texto
            imprimirCentralizado(arteAscii, larguraConsole);
            System.out.println(); // Espaço entre a arte e o menu

            // Texto do menu
            String menuTexto =
                    "Escolha o tipo de acesso:" + "\n" + "1. ADM" + "\n" +
                            "2. Usuário" + "\n" +
                            "3. Sair";

            // Centralizar e imprimir o menu
            imprimirCentralizado(menuTexto, larguraConsole);

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
                                adm.addMovie(); // Chamando método para adicionar filme
                                break;
                            case 2:
                                adm.updateMovie(); // Chamando método para atualizar filme
                                break;
                            case 3:
                                adm.deleteMovie(); // Chamando método para excluir filme
                                break;
                            case 4:
                                adm.listMovies(); // Chamando método para listar filmes
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
                    Cliente usuario = new Cliente(scanner, databaseOperations); // Passa o Scanner e DatabaseOperations para a instância Cliente
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

    private static void imprimirCentralizado(String texto, int larguraConsole) {
        String[] linhas = texto.split("\n");
        for (String linha : linhas) {
            int espacosEsquerda = (larguraConsole - linha.length()) / 2;
            String espacos = " ".repeat(Math.max(0, espacosEsquerda));
            System.out.println(espacos + linha);
        }
    }
}
