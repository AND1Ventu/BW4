package entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "tratta")
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "data_ora_inizio")
    private LocalDateTime dataOraInizioTratta;

    @Column(name = "data_ora_fine")
    private LocalDateTime dataOraFineTratta;

    @ManyToOne
    @JoinColumn(name = "id_tratta")
    private Percorso percorso;


    public Tratta() {
    }

    public Tratta(LocalDateTime dataOraInizioTratta, LocalDateTime dataOraFineTratta) {
        this.dataOraInizioTratta = dataOraInizioTratta;
        this.dataOraFineTratta = dataOraFineTratta;
    }
}
