package entities;

@Entity
public class Mezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMezzo;

    private String tipologia;

    private boolean manutenzione;

    private int capienza;

    private boolean inServizio;

    @ManyToOne
    @JoinColumn(name = "idManutenzioneFk")
    private Manutenzione manutenzioneFk;

    @ManyToOne
    @JoinColumn(name = "idPercorsoFk")
    private Percorso percorsoFk;
}
