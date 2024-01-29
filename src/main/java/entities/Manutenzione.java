package entities;

@Entity
public class Manutenzione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idManutenzione;

    private LocalDateTime dataInizio;

    private LocalDateTime dataFine;

    @OneToMany(mappedBy = "manutenzioneFk")
    private List<Mezzo> mezzi;
}
