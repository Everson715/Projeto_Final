import usuarios.ADM;
import usuarios.Cliente;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
                    ADM adm = new ADM(scanner); // Passa o Scanner para a instância ADM
                    boolean adminMenu = true;
                    while (adminMenu) {
                        System.out.println("Menu ADM:");
                        System.out.println("1. Adicionar filme");
                        System.out.println("2. Atualizar filme");
                        System.out.println("3. Excluir filme");
                        System.out.println("4. Listar filmes");
                        System.out.println("5. Selecionar filme e horário");
                        System.out.println("6. Voltar ao menu principal");

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
                                adm.selectMovieAndShowtime();
                                break;
                            case 6:
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
