package entities;

@Entity
public class Percorso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPercorso;

    private String nomePercorso;

    private int tempoMedio;

    @OneToMany(mappedBy = "percorsoFk")
    private List<Tratta> tratte;
}
