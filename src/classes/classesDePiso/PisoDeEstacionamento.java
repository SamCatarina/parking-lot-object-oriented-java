package classes.classesDePiso;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import atores.AtorAtendente;
import classes.classesDeVagas.VagaEletrico;
import classes.classesDeVagas.VagaGrande;
import classes.classesDeVagas.VagaMotocicleta;
import classes.classesDeVagas.VagaPequena;
import classesAbstratas.Vaga;
import tipos.TipoDeVaga;

public class PisoDeEstacionamento {

    private String idPiso;
    private int qntVagas;
    private int qntVagasGrandes;
    private int qntVagasPequenas;
    private int qntVagasMotocicleta;
    private int qntVagasEletrico;
    private PainelDeExibicao painel;
    private PortalDeAtendimento portal;
    public AtorAtendente atendente;

    protected List<Vaga> listaDeVagas;

    public PisoDeEstacionamento(String idPiso) {

        listaDeVagas = new ArrayList<>();

        this.idPiso = idPiso;

    }

    public PortalDeAtendimento getPortal() {
        return this.portal;
    }

    public void setPortal(PortalDeAtendimento portal) {
        this.portal = portal;
    }

    public PainelDeExibicao getPainel() {
        return this.painel;
    }

    public void criarPortal(AtorAtendente atendente) {
        PortalDeAtendimento portal = new PortalDeAtendimento(atendente);
        setPortal(portal);

    }

    public boolean guardarVaga() {
        Vaga tipoDeVaga = null;
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Digite o tipo de vaga \n[1] pequena\n[2] grande\n[3] eletrico\n[4] motocicleta \nOpção: ");
        String tipoVaga = scanner.next();

        switch (tipoVaga) {
            case "1":
                tipoDeVaga = new VagaPequena(TipoDeVaga.PEQUENA);
                break;
            case "2":
                tipoDeVaga = new VagaGrande(TipoDeVaga.GRANDE);
                break;
            case "3":
                tipoDeVaga = new VagaEletrico(TipoDeVaga.ELETRICO);
                break;
            case "4":
                tipoDeVaga = new VagaMotocicleta(TipoDeVaga.MOTOCICLETA);
                break;

            default:

                return false;
        }
        setQntVagas(getQntVagas() + 1);
        listaDeVagas.add(tipoDeVaga);
        atualizarVagasDisponiveis(1, tipoDeVaga);
        return true;
    }

    public boolean removerVaga(Vaga tipoDaVaga) {

        if (listaDeVagas.contains(tipoDaVaga)) {
            listaDeVagas.remove(tipoDaVaga);
            atualizarVagasDisponiveis(-1, tipoDaVaga);
            return true;
        }
        System.out.println("[ERRO] Não há essa vaga");
        return false;
    }

    public void atualizarVagasDisponiveis(int acao, Vaga tipoDaVaga) {

        setQntVagas(getQntVagas() + acao);

        if (tipoDaVaga instanceof VagaPequena) {
            setQntVagasPequenas(getQntVagasPequenas() + acao);
        } else if (tipoDaVaga instanceof VagaGrande) {
            setQntVagasGrandes(getQntVagasGrandes() + acao);
        } else if (tipoDaVaga instanceof VagaEletrico) {
            setQntVagasEletrico(getQntVagasEletrico() + acao);
        } else if (tipoDaVaga instanceof VagaMotocicleta) {
            setQntVagasMotocicleta(getQntVagasMotocicleta() + acao);
        }

    }

    public String getPisoId() {
        return idPiso;
    }

    public void setQntVagas(int qntVagas) {
        this.qntVagas = qntVagas;
    }

    public void setQntVagasEletrico(int qntVagasEletrico) {
        this.qntVagasEletrico = qntVagasEletrico;

    }

    public void setQntVagasGrandes(int qntVagasGrandes) {
        this.qntVagasGrandes = qntVagasGrandes;

    }

    public void setQntVagasMotocicleta(int qntVagasMotocicleta) {
        this.qntVagasMotocicleta = qntVagasMotocicleta;

    }

    public void setQntVagasPequenas(int qntVagasPequenas) {
        this.qntVagasPequenas = qntVagasPequenas;

    }

    // getters

    public int getQntVagas() {
        return qntVagas;
    }

    public int getQntVagasEletrico() {
        return qntVagasEletrico;
    }

    public int getQntVagasGrandes() {
        return qntVagasGrandes;
    }

    public int getQntVagasMotocicleta() {
        return qntVagasMotocicleta;
    }

    public int getQntVagasPequenas() {
        return qntVagasPequenas;
    }

    public List<Vaga> getListaDeVagas() {
        return listaDeVagas;
    }

}
