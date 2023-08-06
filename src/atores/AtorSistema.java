package atores;

import java.util.Scanner;

import classes.classesDePiso.PainelDeExibicao;
import classes.classesDePiso.PisoDeEstacionamento;
import classes.classesPagamento.PorCartao;
import classesAbstratas.Vaga;
import estacionamento.Estacionamento;
import tipos.NumeroDeCartao;
import tipos.Ticket;
import tipos.Valor;

public class AtorSistema {
    Estacionamento estacionamento;
    private static AtorSistema instancia;

    public AtorSistema() {
        this.estacionamento = Estacionamento.getInstance();

    }

    public static AtorSistema getInstance() {
        if (instancia == null) {
            instancia = new AtorSistema();
        }
        return instancia;
    }

    public void exibirPainel(PisoDeEstacionamento piso) {
        new PainelDeExibicao(piso);
    }

    public void ocuparVaga(PisoDeEstacionamento piso, Vaga vaga) {

        for (Vaga vagaElem : piso.getListaDeVagas()) {

            if (vagaElem == vaga && vagaElem.getOcupada() == false) {
                vagaElem.setOcupada(true);

            }
        }

    }

    public void desocuparVaga(PisoDeEstacionamento piso, Vaga vaga) {

        for (Vaga vagaElem : piso.getListaDeVagas()) {
            if (vagaElem == vaga) {
                vagaElem.setOcupada(false);
            }
        }

    }

    public void pagarPorCartao(Ticket ticket, Valor valor) {

        Scanner scanner = new Scanner(System.in);

        System.out.printf("\nDigite o número do cartão: ");
        int numeroDoCartaoInt = scanner.nextInt();

        NumeroDeCartao numeroDoCartao = new NumeroDeCartao(numeroDoCartaoInt);

        PorCartao cartao = new PorCartao(ticket, numeroDoCartao, valor);

        boolean resultadoPagamento = cartao.pagar();

        if (resultadoPagamento) {
            System.out.printf("\nPagamento realizado com sucesso!");
            desocuparVaga(ticket.getPiso(), ticket.getVaga());
        } else {
            System.out.printf("\nErro ao realizar pagamento. Digite novamente o número do cartão:\n");
            pagarPorCartao(ticket, valor);

        }

    }

}
