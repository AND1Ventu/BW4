package entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "percorso")
public class Percorso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPercorso;

    @Column(name = "nome_percorso")
    private String nomePercorso;

    @Column(name = "id_tratta")
    @OneToMany(mappedBy = "idTratta")
    private List<Tratta> tratte;

    @OneToMany(mappedBy = "idPercorso")
    private List<Mezzo> mezzi;

    public Percorso() {
    }

    public Percorso(String nomePercorso, List<Tratta> tratte) {
        this.nomePercorso = nomePercorso;
        this.tratte = tratte;
    }
}
