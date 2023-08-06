package classes.classesDePiso;

import classes.classesDeVagas.VagaEletrico;
import classes.classesDeVagas.VagaGrande;
import classes.classesDeVagas.VagaMotocicleta;
import classes.classesDeVagas.VagaPequena;
import classesAbstratas.Vaga;

public class PainelDeExibicao {

    private int pequenas = 0;
    private int grandes = 0;
    private int motocicletas = 0;
    private int eletricos = 0;

    public PainelDeExibicao(PisoDeEstacionamento piso) {

        for (Vaga vaga : piso.getListaDeVagas()) {
            if (vaga.getOcupada() == false) {
                if (vaga instanceof VagaPequena) {
                    pequenas += 1;
                } else if (vaga instanceof VagaGrande) {
                    grandes += 1;
                } else if (vaga instanceof VagaMotocicleta) {
                    motocicletas += 1;
                } else if (vaga instanceof VagaEletrico) {
                    eletricos += 1;
                }
            }
        }

        System.out.printf("-------- PAINEL DE VAGAS DO PISO " + piso.getPisoId() + "-------------");
        System.out.printf("\n|\t Pequenas: " + pequenas);
        System.out.printf("\n|\tGrandes: " + grandes);
        System.out.printf("\n|\tMotocicleta: " + motocicletas);
        System.out.printf("\n|\tEletrico: " + eletricos);
        System.out.printf("\n---------------------------------\n\n");

    }

}
