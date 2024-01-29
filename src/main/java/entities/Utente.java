package entities;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Utente {

    @Id
    @Column(name = "id_utente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtente;

    private String nome;

    private String cognome;

    @Column(name = "n_tessera", unique = true)
    private Long nTessera;

    @OneToMany(mappedBy = "utente")
    private List<Ticket> tickets;

    public Utente() {
    }

    public Utente(Long idUtente, String nome, String cognome, Long nTessera, List<Ticket> tickets) {
        this.idUtente = idUtente;
        this.nome = nome;
        this.cognome = cognome;
        this.nTessera = nTessera;
        this.tickets = tickets;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
