package filme;

import validarDados.ValidadorDeDados;
import java.util.ArrayList;
import java.util.List;

public class CriarFilme {

    // Atributos
    private final String nome;
    private final int classeIndicativa;
    private final int duracao;
    private final int sala;
    private final String[] horarios;

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
        String nome = ValidadorDeDados.validarNome();
        int classeIndicativa = ValidadorDeDados.validarClasseIndicativa();
        int duracao = ValidadorDeDados.validarDuracao();
        int sala = ValidadorDeDados.validarSala();
        String[] horarios = ValidadorDeDados.validarHorarios();

        return new CriarFilme(nome, classeIndicativa, duracao, sala, horarios);
    }

    // Método para gerar 4 horários de exibição
    private static String[] gerarHorarios() {
        return new String[]{"10:00", "13:00", "16:00", "19:00"};
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
