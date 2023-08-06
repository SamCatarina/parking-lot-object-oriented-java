package classes.classesPagamento;

import interfaces.Pagamento;
import tipos.Ticket;
import tipos.Valor;

public class PorDinheiro implements Pagamento {

    private Valor valor;
    private Ticket ticket;

    public PorDinheiro(Valor valor, Ticket ticket) {
        this.valor = valor;
        this.ticket = ticket;
    }

    public boolean pagar() {

        if (this.valor.getValor() != this.ticket.getValorDoTicket().getValor()) {
            return false;
        }

        return true;
    }

}
