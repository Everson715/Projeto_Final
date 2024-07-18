package filme;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CriarFilme {

    //Atributos
    private final String nome;
    private final int classeIndicativa;
    private final int duracao;
    private final int sala;
    private String[] horarios = new String[4];

    public static List<CriarFilme> filmesCriados = new ArrayList<>(); // Lista estática para armazenar filmes criados

    // Construtores
    public CriarFilme(String nome, int classeIndicativa, int duracao, int sala, String[] horarios) {
        this.nome = nome;
        this.classeIndicativa = classeIndicativa;
        this.duracao = duracao;
        this.sala = sala;
        this.horarios = horarios;
        filmesCriados.add(this); // Adiciona o filme criado à lista
    }

    // Método para solicitar dados do filme ao usuário
    public static CriarFilme criarNovoFilme() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do filme: ");
        String nome = scanner.nextLine();

        System.out.println("Digite a classe Indicativa: ");
        int classeIndicativa = scanner.nextInt();

        System.out.println("Digite a duração do filme em minutos: ");
        int duracao = scanner.nextInt();

        int sala = alocarSala();
        String[] horarios = gerarHorarios();

        return new CriarFilme(nome, classeIndicativa, duracao, sala, horarios);
    }

    // Método para alocar uma sala (1 a 4)
    private static int alocarSala() {
        return (int) (Math.random() * 4) + 1;
    }

    // Método para gerar 4 horários de exibição
    private static String[] gerarHorarios() {
        String[] horarios = new String[4];
        horarios[0] = "10:00";
        horarios[1] = "13:00";
        horarios[2] = "16:00";
        horarios[3] = "19:00";
        return horarios;
    }

    // Método toString para exibir informações do filme
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome do Filme: ").append(nome).append("\n");
        sb.append("Classe Indicativa: ").append(classeIndicativa).append("\n");
        sb.append("Duração: ").append(duracao).append(" minutos\n");
        sb.append("Sala: ").append(sala).append("\n");
        sb.append("Horários de Exibição:\n");
        for (String horario : horarios) {
            sb.append(" - ").append(horario).append("\n");
        }
        return sb.toString();
    }

    // Getter para o nome do filme
    public String getNome() {
        return nome;
    }

    // Getter para a sala
    public int getSala() {
        return sala;
    }

    // Método para remover o filme da lista
    public static void removerFilme(CriarFilme filme) {
        filmesCriados.remove(filme);
    }
}
