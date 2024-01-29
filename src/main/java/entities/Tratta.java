package entities;

@Entity
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTratta;

    private int tempoEffettivo;

    private LocalDateTime dataOraInizioTratta;

    private LocalDateTime dataOraFineTratta;

    @ManyToOne
    @JoinColumn(name = "idPercorsoFk")
    private Percorso percorsoFk;
}
