package entities;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;

    @ManyToOne
    @JoinColumn(name = "idDistributore")
    private RivenditoreAutorizzato rivenditoreAutorizzato;

    private String tipologia;

    private LocalDateTime dataVendita;

    private LocalDateTime dataAttivazione;

    private String validita;

    @ManyToOne
    @JoinColumn(name = "idMezzoAttivazione")
    private Mezzo mezzoAttivazione;
}
