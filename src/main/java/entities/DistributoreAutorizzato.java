package entities;
import javax.persistence.*;
@Entity
public class DistributoreAutorizzato extends RivenditoreAutorizzato {

    private boolean attivo;

    public DistributoreAutorizzato() {
    }

    public DistributoreAutorizzato(boolean attivo) {
        this.attivo = attivo;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }
}
