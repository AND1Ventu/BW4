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

    public Long getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Long getnTessera() {
        return nTessera;
    }

    public void setnTessera(Long nTessera) {
        this.nTessera = nTessera;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
