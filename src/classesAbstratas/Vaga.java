package classesAbstratas;

import tipos.TipoDeVaga;

public abstract class Vaga {

    private boolean ocupada;
    private TipoDeVaga tipoDaVaga;

    public Vaga(TipoDeVaga tipoDeVaga) {
        this.tipoDaVaga = tipoDeVaga;
        this.ocupada = false;

    }

    public boolean getOcupada() {
        return ocupada;
    }

    public String getOcupadaString() {
        if (getOcupada()) {
            return "ocupada";
        }
        return "disponível";
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public TipoDeVaga getTipoDaVaga() {
        return tipoDaVaga;
    }

}
