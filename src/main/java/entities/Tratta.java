package entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "tratta")
public class Tratta {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_tratta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTratta;

    @Column(name = "data_ora_inizio")
    private LocalDateTime dataOraInizioTratta;

    @Column(name = "data_ora_fine")
    private LocalDateTime dataOraFineTratta;

    public Tratta() {
    }

    public Tratta(LocalDateTime dataOraInizioTratta, LocalDateTime dataOraFineTratta) {
        this.dataOraInizioTratta = dataOraInizioTratta;
        this.dataOraFineTratta = dataOraFineTratta;
    }
}
