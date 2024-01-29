package entities;

import javax.persistence.*;

import javax.persistence.*;
import java.util.List;

@Entity
public class RivenditoreAutorizzato {

    @Id
    @Column(name = "id_distributore")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDistributore;


    @OneToMany(mappedBy = "rivenditore_autorizzato")
    private List<Ticket> ticketDistribuiti;

    public RivenditoreAutorizzato() {
    }

    public RivenditoreAutorizzato(Long idDistributore, List<Ticket> ticketDistribuiti) {
        this.idDistributore = idDistributore;
        this.ticketDistribuiti = ticketDistribuiti;
    }

    public Long getIdDistributore() {
        return idDistributore;
    }

    public void setIdDistributore(Long idDistributore) {
        this.idDistributore = idDistributore;
    }

    public List<Ticket> getTicketDistribuiti() {
        return ticketDistribuiti;
    }

    public void setTicketDistribuiti(List<Ticket> ticketDistribuiti) {
        this.ticketDistribuiti = ticketDistribuiti;
    }
}
