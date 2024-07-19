package validarDados;

import java.util.Scanner;

public class ValidadorDeDados {

    // Método para validar o nome do filme
    public static String validarNome() {
        Scanner scanner = new Scanner(System.in);
        String nome;
        while (true) {
            System.out.println("Digite o nome do filme: ");
            nome = scanner.nextLine();
            if (nome != null && !nome.trim().isEmpty()) {
                break;
            }
            System.out.println("Erro: O nome do filme não pode estar vazio. Tente novamente.");
        }
        return nome;
    }

    // Método para validar a classe indicativa
    public static int validarClasseIndicativa() {
        Scanner scanner = new Scanner(System.in);
        int classeIndicativa;
        while (true) {
            System.out.println("Digite a classe indicativa (entre 0 e 18): ");
            if (scanner.hasNextInt()) {
                classeIndicativa = scanner.nextInt();
                if (classeIndicativa >= 0 && classeIndicativa <= 18) {
                    scanner.nextLine(); // Consumir a nova linha
                    break;
                } else {
                    System.out.println("Erro: A classe indicativa deve estar entre 0 e 18. Tente novamente.");
                }
            } else {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                scanner.next(); // Limpar a entrada inválida
            }
        }
        return classeIndicativa;
    }

    // Método para validar a duração do filme
    public static int validarDuracao() {
        Scanner scanner = new Scanner(System.in);
        int duracao;
        while (true) {
            System.out.println("Digite a duração do filme em minutos (maior que 0): ");
            if (scanner.hasNextInt()) {
                duracao = scanner.nextInt();
                if (duracao > 0) {
                    scanner.nextLine(); // Consumir a nova linha
                    break;
                } else {
                    System.out.println("Erro: A duração deve ser maior que 0. Tente novamente.");
                }
            } else {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                scanner.next(); // Limpar a entrada inválida
            }
        }
        return duracao;
    }

    // Método para validar a sala
    public static int validarSala() {
        // Neste caso, a alocação da sala é feita internamente e não requer validação adicional
        return (int) (Math.random() * 4) + 1;
    }

    // Método para validar os horários
    public static String[] validarHorarios() {
        // Horários são fixos e não requerem validação adicional
        return new String[]{"10:00", "13:00", "16:00", "19:00"};
    }
}
