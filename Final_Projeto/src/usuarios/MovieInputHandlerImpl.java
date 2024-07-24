package usuarios;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MovieInputHandlerImpl implements MovieInputHandler {
    private Scanner scanner;

    public MovieInputHandlerImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getNome() {
        System.out.print("Digite o nome do filme: ");
        return scanner.nextLine();
    }

    @Override
    public int getClasse() {
        while (true) {
            System.out.print("Digite a classe do filme (ex.: 12, 14, 16): ");
            try {
                int classe = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
                return classe;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro válido.");
                scanner.nextLine(); // Limpa a entrada inválida
            }
        }
    }

    @Override
    public int getDuracao() {
        while (true) {
            System.out.print("Digite a duração do filme em minutos: ");
            try {
                int duracao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
                return duracao;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro válido.");
                scanner.nextLine(); // Limpa a entrada inválida
            }
        }
    }

    @Override
    public String getGenero() {
        String genero = "";
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print("Digite o gênero do filme: ");
            genero = scanner.nextLine().trim(); // Remove espaços em branco

            // Verifica se a entrada não está vazia
            if (!genero.isEmpty()) {
                entradaValida = true; // Entrada válida, sai do loop
            } else {
                System.out.println("Entrada inválida. O gênero não pode estar vazio.");
            }
        }

        return genero;
    }



    @Override
    public List<String> getHorarios() {
        List<String> horarios = new ArrayList<>();
        System.out.println("Digite os horários disponíveis (Ex: 10:00). Para finalizar, digite '1':");

        while (true) {
            System.out.print("Horário: ");
            String horario = scanner.nextLine().trim(); // Remove espaços em branco antes e depois da entrada

            if ("1".equalsIgnoreCase(horario)) {
                break;
            }

            // Verifica se a entrada é um horário válido (no formato HH:MM)
            if (isValidTimeFormat(horario)) {
                horarios.add(horario);
            } else {
                System.out.println("Erro: Horário inválido. Por favor, digite um horário no formato HH:MM.");
            }
        }

        return horarios;
    }

    // Método auxiliar para validar o formato do horário
    private boolean isValidTimeFormat(String time) {
        // Expressão regular para verificar o formato HH:MM
        String timePattern = "^([01]\\d|2[0-3]):([0-5]\\d)$";
        return time.matches(timePattern);
    }


    @Override
    public int getId() {
        while (true) {
            System.out.print("Digite o ID do filme: ");
            try {
                int id = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer
                return id;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro válido.");
                scanner.nextLine(); // Limpa a entrada inválida
            }
        }
    }

    @Override
    public String getHorarioSelecionado() {
        System.out.print("Digite o horário selecionado: ");
        scanner.nextLine(); // Limpar o buffer
        return scanner.nextLine();
    }

    @Override
    public void clearInput() {
        // Implementar se necessário, ou deixar vazio
    }
}
