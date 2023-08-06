package classes.classesDePiso;

import atores.AtorAtendente;
import atores.AtorSistema;
import classesAbstratas.Vaga;
import estacionamento.Estacionamento;
import tela.TelaPrincipalUtil;
import tipos.Pagamento;
import tipos.Ticket;
import tipos.Valor;

public class PortalDeAtendimento {

    public AtorAtendente atendente;
    PisoDeEstacionamento piso;
    AtorSistema atorSistema;
    Estacionamento estacionamento;
    public static PortalDeAtendimento instancia;

    public PortalDeAtendimento(AtorAtendente atendente) {

        this.atorSistema = AtorSistema.getInstance();
        this.estacionamento = Estacionamento.getInstance();
        this.atendente = atendente;

    }

    public void atribuirVaga(PisoDeEstacionamento piso, Vaga vaga) {
        atorSistema.ocuparVaga(piso, vaga);
    }

    public void mostrarPainel(PisoDeEstacionamento piso) {
        atorSistema.exibirPainel(piso);
    }

    public void guardarTicket(Ticket ticket) {
        estacionamento.adicionarTicket(ticket);
    }

    public void destruirTicket(Ticket ticket) {
        estacionamento.removerTicket(ticket);
        TelaPrincipalUtil.telaPrincipal();
    }

    public void processarPagamento(Ticket ticket, Pagamento pagamento, Valor valor) {

        if (pagamento.equals(Pagamento.CARTAO)) {

            atorSistema.pagarPorCartao(ticket, valor);

        } else if (pagamento.equals(Pagamento.DINHEIRO)) {

            atendente.receberDinheiro(ticket, valor);

        }

        atorSistema.desocuparVaga(ticket.getPiso(), ticket.getVaga());

        destruirTicket(ticket);

    }

}
