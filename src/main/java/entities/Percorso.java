package entities;

import javax.persistence.*;
import java.util.List;

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

    public Percorso() {
    }

    public Percorso(Long idPercorso, String nomePercorso, List<Tratta> tratte) {
        this.idPercorso = idPercorso;
        this.nomePercorso = nomePercorso;
        this.tratte = tratte;
    }

    public Long getIdPercorso() {
        return idPercorso;
    }

    public void setIdPercorso(Long idPercorso) {
        this.idPercorso = idPercorso;
    }

    public String getNomePercorso() {
        return nomePercorso;
    }

    public void setNomePercorso(String nomePercorso) {
        this.nomePercorso = nomePercorso;
    }

    public List<Tratta> getTratte() {
        return tratte;
    }

    public void setTratte(List<Tratta> tratte) {
        this.tratte = tratte;
    }
}
