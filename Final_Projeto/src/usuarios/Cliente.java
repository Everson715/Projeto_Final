package usuarios;

import database.DatabaseOperations;
import filme.Filme;
import tratarDados.TesteDeConfirmacao;
import ingresso.Ingresso;
import pagamento.Cartao;
import pagamento.Pix;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Cliente {
    private Scanner scanner;
    private Filme filmeSelecionado;
    private String horarioSelecionado;
    private int salaSelecionada; // Armazenar o ID do filme como sala
    private DatabaseOperations databaseOperations; // Instância para operações de banco de dados

    public Cliente(Scanner scanner, DatabaseOperations databaseOperations) {
        this.scanner = scanner; // Inicialização correta do scanner
        this.databaseOperations = databaseOperations; // Inicializa a instância de DatabaseOperations
    }

    // Método para mostrar filmes em cartaz e iniciar a compra
    public void mostrarFilmesEIniciarCompra() {
        try {
            databaseOperations.listMoviesWithShowtimes(); // Lista filmes e horários disponíveis
        } catch (Exception e) {
            System.out.println("Erro ao listar filmes com horários: " + e.getMessage());
            return; // Interrompe a execução se houver erro ao listar filmes
        }

        // Seleciona o filme e o horário
        selectMovieAndShowtime();

        // Prossegue com a compra
        iniciarCompra();
    }

    // Método para selecionar o filme e o horário
    private void selectMovieAndShowtime() {
        System.out.print("Digite a sala do filme a ser selecionado: ");
        try {
            salaSelecionada = scanner.nextInt(); // Armazena o ID do filme como sala
            scanner.nextLine(); // Consumir a nova linha
        } catch (InputMismatchException e) {
            System.out.println("Erro: ID do filme inválido. Por favor, digite um número.");
            scanner.nextLine(); // Limpar o buffer
            selectMovieAndShowtime(); // Permite ao usuário tentar novamente
            return;
        }

        Optional<Filme> movieOpt;
        try {
            movieOpt = databaseOperations.getMovie(salaSelecionada);
        } catch (Exception e) {
            System.out.println("Erro ao buscar filme: " + e.getMessage());
            return; // Interrompe a execução se houver erro ao buscar filme
        }

        if (movieOpt.isPresent()) {
            Filme movie = movieOpt.get();
            filmeSelecionado = movie; // Armazena o filme selecionado
            System.out.println("Filme selecionado: " + movie.getNome().toUpperCase());
            System.out.println("Horários disponíveis: " + movie.getHorario());
            System.out.println("Gênero do Filme: " + movie.getGenero().toUpperCase());
            System.out.println("Classificação Indicativa: " + movie.getClasse() + " anos");

            System.out.print("Digite o horário a ser selecionado (ex.: 10:00): ");
            String horario = scanner.nextLine();
            if (movie.getHorario().contains(horario)) {
                horarioSelecionado = horario; // Armazena o horário selecionado
                System.out.println("Você selecionou o filme " + movie.getNome() + " às " + horario);
            } else {
                System.out.println("Horário selecionado inválido.");
                selectMovieAndShowtime(); // Permite ao usuário tentar novamente
            }
        } else {
            System.out.println("Filme não encontrado com id: " + salaSelecionada);
        }
    }

    // Método para iniciar a compra
    private void iniciarCompra() {
        // Solicita o número de ingressos
        Ingresso ingresso = new Ingresso(35.0); // Valor do ingresso
        int numeroIngressos = ingresso.solicitarQuantidadeIngressos(scanner);
        if (numeroIngressos <= 0) {
            System.out.println("Número de ingressos inválido.");
            return;
        }

        // Solicita os números dos assentos
        List<Integer> assentosEscolhidos = obterAssentosEscolhidos(numeroIngressos);

        if (assentosEscolhidos.isEmpty()) {
            System.out.println("Nenhum assento foi escolhido.");
            return;
        }

        // Solicita o tipo de ingresso
        double valorTotal = ingresso.solicitarTipoIngressos(numeroIngressos, scanner);

        // Exibe as informações da compra
        exibirResumoCompra(assentosEscolhidos, valorTotal);

        // Processa o pagamento
        processarPagamento(valorTotal);
    }

    // Solicita os números dos assentos e valida
    private List<Integer> obterAssentosEscolhidos(int numeroIngressos) {
        List<Integer> assentosEscolhidos = new ArrayList<>();
        for (int i = 1; i <= numeroIngressos; i++) {
            int assento = -1;
            while (assento <= 0 || assento > 49 || assentosEscolhidos.contains(assento)) {
                System.out.print("Digite o número do assento " + i + " (1-49): ");
                try {
                    assento = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha
                    if (assento <= 0 || assento > 49) {
                        System.out.println("Número do assento deve estar entre 1 e 49.");
                    } else if (assentosEscolhidos.contains(assento)) {
                        System.out.println("Assento já escolhido. Escolha outro.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                    scanner.nextLine(); // Limpar o buffer
                }
            }
            assentosEscolhidos.add(assento);
        }
        return assentosEscolhidos;
    }

    // Método para exibir o resumo da compra
    private void exibirResumoCompra(List<Integer> assentosEscolhidos, double valorTotal) {
        if (filmeSelecionado != null && horarioSelecionado != null) {
            System.out.println("Resumo da Compra:");
            System.out.println("Filme: " + filmeSelecionado.getNome());
            System.out.println("Horário: " + horarioSelecionado);
            System.out.println("Sala: " + salaSelecionada);
            System.out.println("Assentos escolhidos: " + assentosEscolhidos);
            System.out.println("Gênero: " + filmeSelecionado.getGenero());
            System.out.println("Classificação Indicativa: " + filmeSelecionado.getClasse() + " anos");
            System.out.println("Valor Total: R$ " + valorTotal);
        } else {
            System.out.println("Resumo da compra não disponível.");
        }
    }

    // Método para processar o pagamento
    private void processarPagamento(double valorTotal) {
        TesteDeConfirmacao testeDeConfirmacao = new TesteDeConfirmacao(scanner);

        // Solicita o nome do comprador
        System.out.print("Digite o nome do comprador: ");
        String nomeComprador = scanner.nextLine();

        // Solicita o CPF do comprador
        String cpf = testeDeConfirmacao.receberCpf();

        while (true) {
            System.out.println("Escolha o método de pagamento (1 para PIX, 2 para Cartão): ");
            String escolha = scanner.nextLine();

            if (escolha.equals("1") || escolha.equalsIgnoreCase("Pix")) {
                // Processa pagamento via PIX
                String chavePixEstabelecimento = "u123y8ur891iu21h-9u1h931";
                Pix pagamentoPix = new Pix(chavePixEstabelecimento, valorTotal, cpf, nomeComprador);
                System.out.println(pagamentoPix);
                break; // Saída do loop
            } else if (escolha.equals("2") || escolha.equalsIgnoreCase("Cartão")) {
                // Processa pagamento via Cartão
                Cartao cartao = new Cartao();
                cartao.capturarDados(scanner);
                cartao.identificarBandeira();
                cartao.capturarCvc(scanner);
                cartao.capturarTipoDePagamento(scanner);
                cartao.setValor(valorTotal); // Define o valor final no cartão
                System.out.println(cartao);
                break; // Saída do loop
            } else {
                System.out.println("Escolha inválida. Por favor, tente novamente.");
            }
        }

        // Pergunta se o usuário deseja realizar outra compra
        while (true) {
            System.out.println("Deseja realizar outra compra? (1 para Sim, 2 para Não)");
            String resposta = scanner.nextLine();

            if (resposta.equals("1") || resposta.equalsIgnoreCase("Sim")) {
                mostrarFilmesEIniciarCompra(); // Reinicia o processo de compra
                break;
            } else if (resposta.equals("2") || resposta.equalsIgnoreCase("Não")) {
                System.out.println("Obrigado pela compra. Até logo!");
                break;
            } else {
                System.out.println("Resposta inválida. Digite 1 para Sim ou 2 para Não.");
            }
        }
    }
}
