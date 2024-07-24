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
        DatabaseOperations databaseOperations = new BancoDeDados(); // Supõe-se que BancoDeDados é uma classe concreta

        // Implementação do MovieInputHandler
        MovieInputHandler inputHandler = new MovieInputHandlerImpl(scanner); // Supondo que MovieInputHandlerImpl é a implementação concreta

        boolean continuar = true;

        while (continuar) {
            // Obter a largura do console
            int larguraConsole = 220; // Ajuste conforme necessário

            // Texto a ser centralizado
            String arteAscii =
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
                    "Escolha o tipo de acesso:" + "\n" +
                            "1. ADM" + "\n" +
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
                    usuario.mostrarFilmesEIniciarCompra(); // Interage com o cliente
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
