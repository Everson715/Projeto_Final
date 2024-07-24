package usuarios;

import database.BancoDeDados;
import filme.Filme;
import tratarDados.TesteDeConfirmacao;

import java.util.Optional;
import java.util.Scanner;

public class Cliente {
    private Scanner scanner;
    private Filme filmeSelecionado;
    private String horarioSelecionado;
    private int quantidadeIngressos;

    public Cliente(Scanner scanner) {
        this.scanner = scanner; // Inicialização correta do scanner
    }

    // Método para mostrar filmes em cartaz e iniciar a compra
    public void mostrarFilmesEIniciarCompra() {
        BancoDeDados.listMoviesWithShowtimes(); // Lista filmes e horários disponíveis

        // Seleciona o filme e o horário
        selectMovieAndShowtime();

        // Prossegue com a compra
        iniciarCompra();
    }

    // Método para selecionar o filme e o horário
    private void selectMovieAndShowtime() {
        System.out.print("Digite o id do filme a ser selecionado: ");
        int movieId = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Optional<Filme> movieOpt = BancoDeDados.getMovie(movieId);
        if (movieOpt.isPresent()) {
            Filme movie = movieOpt.get();
            filmeSelecionado = movie; // Armazena o filme selecionado
            System.out.println("Filme selecionado: " + movie.getNome());
            System.out.println("Horários disponíveis: " + movie.getHorario());

            System.out.print("Digite o horário a ser selecionado (ex.: 10:00): ");
            String horario = scanner.nextLine();
            if (movie.getHorario().contains(horario)) {
                horarioSelecionado = horario; // Armazena o horário selecionado
                System.out.println("Você selecionou o filme " + movie.getNome() + " às " + horario);
                // Captura a quantidade de ingressos
                System.out.print("Digite a quantidade de ingressos: ");
                quantidadeIngressos = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha
            } else {
                System.out.println("Horário selecionado inválido.");
                selectMovieAndShowtime(); // Permite ao usuário tentar novamente
            }
        } else {
            System.out.println("Filme não encontrado com id: " + movieId);
        }
    }

    // Método para iniciar a compra
    private void iniciarCompra() {
        TesteDeConfirmacao teste = new TesteDeConfirmacao(scanner); // Passar o scanner para TesteDeConfirmacao
        boolean compraConcluida = teste.processarCompra();

        if (compraConcluida) {
            // Exibe as informações da compra
            exibirResumoCompra();
            // Pergunta se deseja realizar nova compra
            perguntarNovaCompra();
        }
    }

    // Método para exibir o resumo da compra
    private void exibirResumoCompra() {
        if (filmeSelecionado != null && horarioSelecionado != null) {
            System.out.println("Resumo da Compra:");
            System.out.println("Filme: " + filmeSelecionado.getNome());
            System.out.println("Horário: " + horarioSelecionado);
            System.out.println("Quantidade de Ingressos: " + quantidadeIngressos);
        }
    }

    // Método para perguntar se o cliente deseja realizar uma nova compra
    private void perguntarNovaCompra() {
        while (true) {
            System.out.println("Deseja realizar outra compra? (1 para Sim, 2 para Não)");
            String resposta = scanner.nextLine();

            if (resposta.equals("1") || resposta.equalsIgnoreCase("Sim")) {
                mostrarFilmesEIniciarCompra(); // Inicia o processo de nova compra
                break;
            } else if (resposta.equals("2") || resposta.equalsIgnoreCase("Não")) {
                System.out.println("Processo encerrado.");
                break;
            } else {
                System.out.println("Resposta inválida. Digite 1 para Sim ou 2 para Não.");
            }
        }
    }
}
