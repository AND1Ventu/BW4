package entities;

@Entity
public class Ticket {

    @Id
    @Column(name = "id_ticket")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Long idTicket;

    @ManyToOne
    @JoinColumn(name = "id_distributore")
    private RivenditoreAutorizzato rivenditoreAutorizzato;


    public enum Tipologia {
        SINGOLO, ABBONAMENTO
    }

    @Enumerated(EnumType.STRING)
    private Tipologia tipologia;

    @Column(name = "data_vendita")
    private LocalDateTime dataVendita;

    @Column(name = "data_attivazione")
    private LocalDateTime dataAttivazione;



    public enum Validita {
        HOUR, WEEK, MONTH
    }

    @Enumerated(EnumType.STRING)
    private Validita validita;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzoAttivazione;
}
