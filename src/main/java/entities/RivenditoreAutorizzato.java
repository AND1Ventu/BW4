package entities;

import javax.persistence.*;

@Entity
public class RivenditoreAutorizzato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDistributore;

    private String tipologia;

    @OneToMany(mappedBy = "rivenditoreAutorizzato")
    private List<Ticket> ticketDistribuiti;
}
