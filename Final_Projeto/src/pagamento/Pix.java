package pagamento;

import java.util.UUID;
import java.io.Serializable;
public class Pix implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String idTransacao;
    private final String chavePIXEstabelecimento;
    private final double valorCompra; // Modificado para double
    private final String cpfComprador;
    private final String nomeComprador;

    public Pix(String chavePIXEstabelecimento, double valorCompra, String cpfComprador, String nomeComprador) {
        this.idTransacao = UUID.randomUUID().toString();
        this.chavePIXEstabelecimento = chavePIXEstabelecimento;
        this.valorCompra = valorCompra; // Valor da compra é passado como parâmetro
        this.cpfComprador = cpfComprador;
        this.nomeComprador = nomeComprador;
    }

    @Override
    public String toString() {
        return "Dados da compra:" + "\n" + "\n" +
                "Nome do Comprador: " + nomeComprador.toUpperCase() + "\n" +
                "Código de validação: " + idTransacao + "\n" +
                "Chave PIX do estabelecimento: " + chavePIXEstabelecimento + "\n" +
                "CNPJ do estabelecimento: 00.779.721/0059-68" + "\n" +
                "Valor Final: R$" + valorCompra + "\n" +
                "CPF do comprador: " + cpfComprador + "\n";
    }
}
