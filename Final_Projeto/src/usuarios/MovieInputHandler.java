package usuarios;

import java.util.List;
public interface MovieInputHandler extends InputHandler {
    @Override
    String getNome();
    @Override
    int getClasse();
    @Override
    int getDuracao();
    @Override
    String getGenero();
    @Override
    List<String> getHorarios();
    @Override
    int getId();
    @Override
    String getHorarioSelecionado();
}
