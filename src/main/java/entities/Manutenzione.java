package entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "manutenzione")
public class Manutenzione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "id_manutenzione")
    private Long idManutenzione;

    @Column(name = "data_inizio")
    private LocalDateTime dataInizio;

    @Column(name = "data_fine")
    private LocalDateTime dataFine;



    public Manutenzione() {
    }

    public Manutenzione(LocalDateTime dataInizio, LocalDateTime dataFine) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }
}
