package arquivo;

import pagamento.Cartao;
import pagamento.Pix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArquivoCompraCard {

    private String caminhoArquivo;

    public ArquivoCompraCard(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public void salvarCompra(Object compra) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            writer.write(compra.toString());
            writer.newLine();
            writer.write("====================================");
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar a compra: " + e.getMessage());
        }
    }

    public void lerCompras() {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ArquivoCompraCard arquivoCompra = new ArquivoCompraCard("compras.txt");

        // Criando objetos de exemplo (Cartao e Pix)
        Cartao cartao = new Cartao();
        cartao.setNomeTitular("João Silva");
        cartao.setNumeroCartao("1234567812345678");
        cartao.setDataValidade("12/25");
        cartao.setCVC("123");
        cartao.setValor(100.0);
        cartao.identificarBandeira();
        cartao.setTipoDePagamento("crédito");
        cartao.setParcelas(2);

        Pix pix = new Pix("chavePIXEstabelecimento", 50.0, "12345678901", "Maria Oliveira");

        // Chamando o método toString de Cartao e Pix e salvando no arquivo
        arquivoCompra.salvarCompra(cartao); // cartao.toString() será chamado implicitamente
        arquivoCompra.salvarCompra(pix); // pix.toString() será chamado implicitamente

        // Lendo compras do arquivo
        System.out.println("Compras salvas no arquivo:");
        arquivoCompra.lerCompras();
    }
}
