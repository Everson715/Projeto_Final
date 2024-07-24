package usuarios;

import java.util.List;

public interface InputHandler {
    String getNome();
    int getClasse();
    int getDuracao();
    String getGenero();
    List<String> getHorarios();
    int getId();
    String getHorarioSelecionado();
    void clearInput();
}
