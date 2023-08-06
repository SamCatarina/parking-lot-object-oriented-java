package atores;

import java.util.Locale;
import java.util.Scanner;

import classes.classesDePiso.PisoDeEstacionamento;
import classes.classesPagamento.PorDinheiro;
import classesAbstratas.Ator;
import tipos.DadosDaConta;
import tipos.Ticket;
import tipos.Valor;

public class AtorAtendente extends Ator {
    public static AtorAtendente instancia;
    public PisoDeEstacionamento piso;
    public String login, senha;

    public AtorAtendente(DadosDaConta dadosDaConta, PisoDeEstacionamento piso) {

        super();
        this.login = dadosDaConta.getLogin();
        this.senha = dadosDaConta.getSenha();
        this.piso = piso;

    }

    public void receberDinheiro(Ticket ticket, Valor valor) {
        PorDinheiro dinheiro = new PorDinheiro(valor, ticket);
        boolean resultadoPagamento = dinheiro.pagar();

        if (!resultadoPagamento) {

            System.out.printf("O valor entregue não corresponde ao valor do ticket. Entregar novo valor: ");
            Scanner scanner = new Scanner(System.in);
            scanner.useLocale(Locale.US);

            float novoValorFloat = scanner.nextFloat();

            Valor novoValor = new Valor(novoValorFloat);
            receberDinheiro(ticket, novoValor);
        } else {
            System.out.printf("Pagamento feito!");
        }
    }

}
