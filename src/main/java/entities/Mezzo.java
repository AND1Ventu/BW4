package entities;

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
}
