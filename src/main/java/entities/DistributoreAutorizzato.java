package entities;

@Entity
public class DistributoreAutorizzato extends RivenditoreAutorizzato {

    private boolean attivo;

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }
}
