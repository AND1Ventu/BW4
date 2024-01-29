package entities;

@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtente;

    private String nome;

    private String cognome;

    private String nTessera;

    @OneToMany(mappedBy = "utente")
    private List<Ticket> tickets;
}
