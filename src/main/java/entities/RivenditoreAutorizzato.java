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
}
