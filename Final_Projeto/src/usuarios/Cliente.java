package usuarios;

import tratarDados.TesteDeConfirmacao;
import java.io.*;

public class Cliente {

    // Método para iniciar a compra
    public boolean iniciarCompra() {
        TesteDeConfirmacao teste = new TesteDeConfirmacao();
        boolean compraConcluida = teste.processarCompra();

        // Serializar o objeto
        serializarCompra(teste);

        // Desserializar e processar novamente (se necessário)
        desserializarEProcessarCompra();

        return compraConcluida;
    }

    // Método para serializar o objeto TesteDeConfirmacao
    private void serializarCompra(TesteDeConfirmacao teste) {
        try (FileOutputStream fileOut = new FileOutputStream("Compra.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(teste);
            System.out.println("Objeto Compra serializado com sucesso!");
        } catch (IOException i) {
            System.out.println("Erro ao serializar o objeto: " + i.getMessage());
            i.printStackTrace();
        }
    }

    // Método para desserializar e processar o objeto TesteDeConfirmacao
    private void desserializarEProcessarCompra() {
        TesteDeConfirmacao testeDesserializado = null;
        try (FileInputStream fileIn = new FileInputStream("Compra.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            testeDesserializado = (TesteDeConfirmacao) in.readObject();
            System.out.println("Objeto Compra desserializado com sucesso!");

            // Reinicializar o scanner após a desserialização
            testeDesserializado.reinicializarScanner();
            // Continuar o processamento se necessário
            testeDesserializado.processarCompra();
        } catch (IOException i) {
            System.out.println("Erro ao desserializar o objeto: " + i.getMessage());
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Classe TesteDeConfirmacao não encontrada");
            c.printStackTrace();
        }
    }
}