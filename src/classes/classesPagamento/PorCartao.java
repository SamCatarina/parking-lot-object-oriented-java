package classes.classesPagamento;

import interfaces.Pagamento;
import tipos.NumeroDeCartao;
import tipos.Ticket;
import tipos.Valor;

public class PorCartao implements Pagamento {

    private NumeroDeCartao numeroDoCartao;
    private Valor valor;
    private Ticket ticket;

    public PorCartao(Ticket ticket, NumeroDeCartao numeroDoCartao, Valor valor) {

        this.numeroDoCartao = numeroDoCartao;
        this.ticket = ticket;
        this.valor = valor;

    }

    public boolean pagar() {

        if ((this.ticket.getValorDoTicketFloat() != this.valor.getValor())) {

            return false;

        } else {

            String numeroDoCartaoStr = String.valueOf(this.numeroDoCartao.getNumeroCartao());

            if (numeroDoCartaoStr.length() < 8) {
                return false;
            }
        }

        return true;
    }

}
