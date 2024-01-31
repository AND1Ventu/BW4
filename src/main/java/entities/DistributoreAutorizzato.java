package entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;


public class DistributoreAutorizzato extends RivenditoreAutorizzato {

    private boolean attivo;

    public DistributoreAutorizzato() {
    }

    public DistributoreAutorizzato(List<Ticket> ticketDistribuiti, boolean attivo) {
        super(ticketDistribuiti);
        this.attivo = attivo;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }
}
