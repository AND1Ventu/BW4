package entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "rivenditore_autorizzato")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class RivenditoreAutorizzato {

    @Id
    @Column(name = "id_distributore")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDistributore;


    @OneToMany(mappedBy = "rivenditoreAutorizzato", cascade = CascadeType.PERSIST)
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
