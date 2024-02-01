package entities;

import javax.persistence.*;
import java.time.Duration;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDataOraInizioTratta() {
        return dataOraInizioTratta;
    }

    public void setDataOraInizioTratta(LocalDateTime dataOraInizioTratta) {
        this.dataOraInizioTratta = dataOraInizioTratta;
    }

    public LocalDateTime getDataOraFineTratta() {
        return dataOraFineTratta;
    }

    public void setDataOraFineTratta(LocalDateTime dataOraFineTratta) {
        this.dataOraFineTratta = dataOraFineTratta;
    }

    public Percorso getPercorso() {
        return percorso;
    }

    public void setPercorso(Percorso percorso) {
        this.percorso = percorso;
    }
}
