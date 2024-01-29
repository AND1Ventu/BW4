package entities;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Mezzo {

    @Id
    @Column(name = "id_mezzo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMezzo;


    public enum Tipologia {
        TRAM, AUTOBUS
    }

    @Enumerated(EnumType.STRING)
    private Tipologia tipologia;

    private boolean manutenzione;

    private int capienza;

    @Column(name = "in_servizio")
    private boolean inServizio;

    @OneToMany
    @JoinColumn(name = "id_manutenzione")
    private List<Manutenzione> manutenzioneFk;

    @ManyToOne
    @JoinColumn(name = "id_percorso")
    private Percorso percorsoFk;

    public Mezzo() {
    }

    public Mezzo(Long idMezzo, Tipologia tipologia, boolean manutenzione, int capienza, boolean inServizio, List<Manutenzione> manutenzioneFk, Percorso percorsoFk) {
        this.idMezzo = idMezzo;
        this.tipologia = tipologia;
        this.manutenzione = manutenzione;
        this.capienza = capienza;
        this.inServizio = inServizio;
        this.manutenzioneFk = manutenzioneFk;
        this.percorsoFk = percorsoFk;
    }

    public Long getIdMezzo() {
        return idMezzo;
    }

    public void setIdMezzo(Long idMezzo) {
        this.idMezzo = idMezzo;
    }

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }

    public boolean isManutenzione() {
        return manutenzione;
    }

    public void setManutenzione(boolean manutenzione) {
        this.manutenzione = manutenzione;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public boolean isInServizio() {
        return inServizio;
    }

    public void setInServizio(boolean inServizio) {
        this.inServizio = inServizio;
    }

    public List<Manutenzione> getManutenzioneFk() {
        return manutenzioneFk;
    }

    public void setManutenzioneFk(List<Manutenzione> manutenzioneFk) {
        this.manutenzioneFk = manutenzioneFk;
    }

    public Percorso getPercorsoFk() {
        return percorsoFk;
    }

    public void setPercorsoFk(Percorso percorsoFk) {
        this.percorsoFk = percorsoFk;
    }
}
