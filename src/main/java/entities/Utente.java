package entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @Column(name = "id_utente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtente;

    private String nome;

    private String cognome;

    @Column(name = "n_tessera", unique = true)
    private Long nTessera;

    @ManyToOne
    @JoinColumn(name = "id_ticket")
    private Ticket ticket;

    public Utente() {
    }

    public Utente(Long idUtente, String nome, String cognome, Long nTessera, Ticket ticket) {
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.nTessera = nTessera;
        this.ticket = ticket;
    }
}
