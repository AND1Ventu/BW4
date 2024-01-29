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

    public Manutenzione() {
    }

    public Manutenzione(Long idManutenzione, LocalDateTime dataInizio, LocalDateTime dataFine, List<Mezzo> mezzi) {
        this.idManutenzione = idManutenzione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.mezzi = mezzi;
    }

    public Long getIdManutenzione() {
        return idManutenzione;
    }

    public void setIdManutenzione(Long idManutenzione) {
        this.idManutenzione = idManutenzione;
    }

    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public List<Mezzo> getMezzi() {
        return mezzi;
    }

    public void setMezzi(List<Mezzo> mezzi) {
        this.mezzi = mezzi;
    }
}
