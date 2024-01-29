package entities;

@Entity
public class Percorso {

    @Id
    @Column(name = "id_percorso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPercorso;

    @Column(name = "nome_percorso")
    private String nomePercorso;

    @Column(name = "id_tratta")
    @OneToMany(mappedBy = "percorsoFk")
    private List<Tratta> tratte;
}
