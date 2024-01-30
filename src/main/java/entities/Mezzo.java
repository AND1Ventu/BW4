package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mezzo")
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

    @OneToMany(mappedBy = "idMezzo")
    private List<Manutenzione> manutenzioneFk;

    @ManyToOne
    @JoinColumn(name = "id_percorso")
    private Percorso percorsoFk;

    public Mezzo() {
    }

    public Mezzo(Tipologia tipologia, boolean manutenzione, int capienza, boolean inServizio, List<Manutenzione> manutenzioneFk, Percorso percorsoFk) {
        this.tipologia = tipologia;
        this.manutenzione = manutenzione;
        this.capienza = capienza;
        this.inServizio = inServizio;
        this.manutenzioneFk = manutenzioneFk;
        this.percorsoFk = percorsoFk;
    }
}
