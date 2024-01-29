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

    private String tipologia;

    @OneToMany(mappedBy = "rivenditoreAutorizzato")
    private List<Ticket> ticketDistribuiti;
}
