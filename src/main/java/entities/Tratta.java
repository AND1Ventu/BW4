package entities;

@Entity
public class Tratta {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_tratta")
    @Column(name = "id_tratta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTratta;

    @Column(name = "data_ora_inizio")
    private LocalDateTime dataOraInizioTratta;

    @Column(name = "data_ora_fine")
    private LocalDateTime dataOraFineTratta;

}
