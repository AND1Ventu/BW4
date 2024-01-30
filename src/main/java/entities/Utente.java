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

    @OneToMany
    @JoinColumn(name = "id_ticket")
    private Ticket ticket;

    public Utente() {
    }

    public Utente(String nome, String cognome, Long nTessera, List<Ticket> tickets) {
        this.nome = nome;
        this.cognome = cognome;
        this.nTessera = nTessera;
        this.tickets = tickets;
    }
}
