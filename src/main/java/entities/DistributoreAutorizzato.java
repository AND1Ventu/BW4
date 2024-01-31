package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "distributori_autorizzati")
public class DistributoreAutorizzato extends RivenditoreAutorizzato {

    @Column(name = "attivo")
    private boolean attivo;

    public DistributoreAutorizzato() {
    }

    public DistributoreAutorizzato(Long idDistributore, List<Ticket> ticketDistribuiti, boolean attivo) {
        super(idDistributore, ticketDistribuiti);
        this.attivo = attivo;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }
}
