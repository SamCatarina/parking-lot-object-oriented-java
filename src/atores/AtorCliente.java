package atores;

import java.util.Locale;
import java.util.Scanner;

import classes.classesDePiso.PisoDeEstacionamento;
import classes.classesDePiso.PortalDeAtendimento;
import classesAbstratas.Vaga;
import classesAbstratas.Veiculo;
import estacionamento.Estacionamento;
import tela.TelaPrincipalUtil;
import tipos.Ticket;
import tipos.Valor;
import tipos.Pagamento;

public class AtorCliente {

    public Veiculo tipoDeVeiculo;
    public Ticket ticket;
    public String tipoDePagamento;
    public PisoDeEstacionamento piso;
    public Vaga vaga;
    public String nome;
    Estacionamento estacionamento;
    PortalDeAtendimento portal;
    int pisoId;

    public AtorCliente(String nome, PisoDeEstacionamento piso, Veiculo tipoDeVeiculo, Vaga vaga) {
        this.nome = nome;
        this.piso = piso;

        this.tipoDeVeiculo = tipoDeVeiculo;

        this.estacionamento = Estacionamento.getInstance();

        this.vaga = vaga;

    }

    public void gerarTicket() {

        Ticket ticket = new Ticket(this.piso, this.vaga);
        this.ticket = ticket;
        this.piso.getPortal().guardarTicket(ticket);
        this.piso.getPortal().atribuirVaga(this.piso, this.vaga);
        System.out.println("Bem-vindo! O seu ticket é: " + ticket.getTicketId());

        System.out.println("------ Olá, cliente! Essas são suas ações -------");
        System.out.println("| [ 1 ] Sair do estacionamento ");
        System.out.println("| [ 2 ] Voltar para tela principal ");

        System.out.println("-----------------------");
        System.out.printf("Digite o número: ");

        Scanner scanner = new Scanner(System.in);
        int opcaoAdm = scanner.nextInt();

        switch (opcaoAdm) {
            case 1:
                pagarTicket();
                break;
            default:
                TelaPrincipalUtil.telaPrincipal();
                break;
        }

    }

    public PisoDeEstacionamento getPiso() {
        return piso;
    }

    public String getNome() {
        return nome;
    }

    public void pagarTicket() {

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.printf("Digite o ticket: ");
        String ticketDigitado = scanner.next();

        Ticket ticket = null;

        for (Ticket ticketElem : estacionamento.getListaDeTickets()) {
            if (ticketElem.getTicketId().equals(ticketDigitado)) {
                ticket = ticketElem;
                break;
            }
        }

        if (ticket == null) {
            System.out.printf("\nTicket incorreto, digite novamente:");
            pagarTicket();

        } else {

            Pagamento tipoDePagamento = receberTipoPagamento();

            System.out.println("O valor do seu ticket é: " + ticket.getValorDoTicketFloat());

            while (tipoDePagamento.equals(null)) {
                tipoDePagamento = receberTipoPagamento();
            }

            System.out.printf("Digite o valor a pagar: ");
            float valorDigitado = scanner.nextFloat();

            Valor valor = new Valor(valorDigitado);
            this.piso.getPortal().processarPagamento(ticket, tipoDePagamento, valor);

        }

        // System.out.printf("->> "+this.ticket.getTicketId());

        TelaPrincipalUtil.telaPrincipal();

    }

    public Pagamento receberTipoPagamento() {

        Scanner scanner = new Scanner(System.in);

        System.out.printf("\nDigite a forma de pagamento \n[1] Cartão\n[2] Dinheiro \nOpção: ");
        int tipoDePagamentoInt = scanner.nextInt();

        if (tipoDePagamentoInt == 1) {
            return Pagamento.CARTAO;
        } else if (tipoDePagamentoInt == 2) {
            return Pagamento.DINHEIRO;
        } else {
            System.out.printf("\nERRO Tipo de pagamento inválido. Digite novamente...");

        }
        return null;
    }

    public void sairDoEstacionamento() {

        pagarTicket();
    }

}
