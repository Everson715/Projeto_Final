package usuarios;

import tratarDados.TesteDeConfirmacao;
import java.io.*;

public class Cliente {

    public void iniciarCompra() {
        TesteDeConfirmacao teste = new TesteDeConfirmacao();
        teste.processarCompra();

        // Início da serialização.
        try (FileOutputStream fileOut = new FileOutputStream("testeDeConfirmacao.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(teste);
            System.out.println("Objeto TesteDeConfirmacao serializado com sucesso!");
        } catch (IOException i) {
            i.printStackTrace();
        }

        // Desserializar o objeto TesteDeConfirmacao
        TesteDeConfirmacao testeDesserializado = null;
        try (FileInputStream fileIn = new FileInputStream("testeDeConfirmacao.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            testeDesserializado = (TesteDeConfirmacao) in.readObject();
            System.out.println("Objeto TesteDeConfirmacao desserializado com sucesso!");
            // Reinicializa o scanner após a desserialização
            testeDesserializado.reinicializarScanner();
            testeDesserializado.processarCompra();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Classe TesteDeConfirmacao não encontrada");
            c.printStackTrace();
        }
    }
}


