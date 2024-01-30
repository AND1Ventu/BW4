package entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "rivenditore_autorizzato")
@DiscriminatorColumn(name = "tipo_rivenditore")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class RivenditoreAutorizzato {

    @Id
    @Column(name = "id_distributore")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDistributore;


    @OneToMany(mappedBy = "rivenditoreAutorizzato")
    private List<Ticket> ticketDistribuiti;

    public RivenditoreAutorizzato() {
    }

    public RivenditoreAutorizzato(List<Ticket> ticketDistribuiti) {
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
