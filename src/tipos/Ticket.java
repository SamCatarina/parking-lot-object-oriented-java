package tipos;

import java.util.Random;

import classes.classesDePiso.PisoDeEstacionamento;
import classes.classesPagamento.TaxaDeEstacionamento;
import classesAbstratas.Vaga;

public class Ticket {

    private String ticket_id;
    private Valor valorDoTicket;
    private PisoDeEstacionamento piso;
    private Vaga vaga;

    public Ticket(PisoDeEstacionamento piso, Vaga vaga) {

        this.piso = piso;
        String ticket_str = "";
        String pisoIdStr = piso.getPisoId();
        TaxaDeEstacionamento taxa = new TaxaDeEstacionamento();
        this.valorDoTicket = taxa.getValor();
        this.vaga = vaga;

        ticket_str += pisoIdStr;

        char[] letras = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

        Random randomNum = new Random();

        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                ticket_str += letras[randomNum.nextInt(26)];
            } else {
                ticket_str += randomNum.nextInt(10);
            }
        }

        this.ticket_id = ticket_str;

    }

    public String getTicketId() {
        return ticket_id;
    }

    public Valor getValorDoTicket() {
        return valorDoTicket;
    }

    public float getValorDoTicketFloat() {
        return valorDoTicket.getValor();
    }

    public Vaga getVaga() {
        return vaga;
    }

    public PisoDeEstacionamento getPiso() {
        return piso;
    }
}
