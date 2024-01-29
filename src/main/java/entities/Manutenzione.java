package entities;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Manutenzione {

    @Id
    @Column(name = "id_manutenzione")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idManutenzione;

    @Column(name = "data_inizio")
    private LocalDateTime dataInizio;

    @Column(name = "data_fine")
    private LocalDateTime dataFine;

    @ManyToOne(mappedBy= "manutenzioneFk")
    private List<Mezzo> mezzi;
}
