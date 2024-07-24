package filme;

import java.util.List;
import java.util.regex.Pattern;

public class Filme {
    private int id;
    private String nome;
    private int classe; // Age rating
    private int duracao; // Duration in minutes
    private String genero;
    private List<String> horario;

    // Expressão regular para validar o formato do horário (hh:mm)
    private static final Pattern HORARIO_PATTERN = Pattern.compile("^(?:[01]\\d|2[0-3]):[0-5]\\d$");

    public Filme(int id, String nome, int classe, int duracao, String genero, List<String> horario) {
        setId(id);
        setNome(nome);
        setClasse(classe);
        setDuracao(duracao);
        setGenero(genero);
        setHorario(horario);
    }

    // Getters and Setters with validation
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do filme deve ser um número positivo.");
        }
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do filme não pode ser vazio.");
        }
        this.nome = nome;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        if (classe < 0 || classe > 18) {
            throw new IllegalArgumentException("Classificação indicativa deve estar entre 0 e 18.");
        }
        this.classe = classe;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        if (duracao <= 0) {
            throw new IllegalArgumentException("Duração do filme deve ser um número positivo.");
        }
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("Gênero do filme não pode ser vazio.");
        }
        this.genero = genero;
    }

    public List<String> getHorario() {
        return horario;
    }

    public void setHorario(List<String> horario) {
        if (horario == null || horario.isEmpty()) {
            throw new IllegalArgumentException("A lista de horários não pode ser vazia.");
        }
        for (String h : horario) {
            if (!isHorarioValido(h)) {
                throw new IllegalArgumentException("Horário inválido: " + h);
            }
        }
        this.horario = horario;
    }

    // Valida se o horário está no formato correto
    private boolean isHorarioValido(String horario) {
        return HORARIO_PATTERN.matcher(horario).matches();
    }

    @Override
    public String toString() {
        return "Filme:" + "\n" +
                "ID: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "Classe indicativa: " + classe + "\n" +
                "Duração: " + duracao + " min" + "\n" +
                "Gênero: " + genero + "\n" +
                "Horário de exibição: " + horario ;
    }
}
