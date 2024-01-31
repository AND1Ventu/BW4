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

    @OneToMany(mappedBy = "percorso")
    private List<Tratta> tratte;

    @OneToMany(mappedBy = "percorso")
    private List<Mezzo> mezzi;

    public Percorso() {
    }

    public Percorso(String nomePercorso, List<Tratta> tratte) {
        this.nomePercorso = nomePercorso;
        this.tratte = tratte;
    }
}
