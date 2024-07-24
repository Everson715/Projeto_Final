package filme;

import java.util.List;

public class Filme {
    private int id;
    private String nome;
    private int classe; // Age rating
    private int duracao; // Duration in minutes
    private String genero;
    private List<String> horario;

    public Filme(int id, String nome, int classe, int duracao, String genero, List<String> horario) {
        this.id = id;
        this.nome = nome;
        this.classe = classe;
        this.duracao = duracao;
        this.genero = genero;
        this.horario = horario;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getHorario() {
        return horario;
    }

    public void setHorario(List<String> horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "ID: " + id +
                ", Nome: '" + nome + '\'' +
                ", Classe indicatica: " + classe +
                ", Duração: " + duracao +
                ", Genero: '" + genero + '\'' +
                ", Horario de exibição" + horario  +
                '}';
    }
}
