package entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Tratta {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_tratta")
    @Column(name = "id_tratta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTratta;

    @Column(name = "data_ora_inizio")
    private LocalDateTime dataOraInizioTratta;

    @Column(name = "data_ora_fine")
    private LocalDateTime dataOraFineTratta;

    public Tratta() {
    }

    public Tratta(Long idTratta, LocalDateTime dataOraInizioTratta, LocalDateTime dataOraFineTratta) {
        this.idTratta = idTratta;
        this.dataOraInizioTratta = dataOraInizioTratta;
        this.dataOraFineTratta = dataOraFineTratta;
    }

    public Long getIdTratta() {
        return idTratta;
    }

    public void setIdTratta(Long idTratta) {
        this.idTratta = idTratta;
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
}
