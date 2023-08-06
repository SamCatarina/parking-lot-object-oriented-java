package classes.classesPagamento;

import java.util.Random;

import tipos.Valor;

public class TaxaDeEstacionamento {

    private float valorFloat;
    private Valor valor;

    public TaxaDeEstacionamento() {
        Random random = new Random();

        int horas = random.nextInt(1, 10);

        for (int i = 0; i < horas; i++) {
            if (i == 0) {
                this.valorFloat += 4.00;
            } else if (i == 1 || i == 2) {
                this.valorFloat += 3.50;
            } else {
                this.valorFloat += 2.50;
            }
        }
        this.valor = new Valor(valorFloat);

    }

    public Valor getValor() {
        return valor;
    }

}
