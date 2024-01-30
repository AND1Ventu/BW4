package entities;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @ManyToOne
//    @JoinColumn(name = "id_ticket")
    private Long idTicket;

    @ManyToOne
    @JoinColumn(name = "id_distributore")
    private RivenditoreAutorizzato rivenditoreAutorizzato;


    @ManyToOne
    @JoinColumn(name = "n_tessera")
    private List<Long> idsTicket;


    public enum Tipologia {
        SINGOLO, ABBONAMENTO
    }

    @Enumerated(EnumType.STRING)
    private Tipologia tipologia;

    @Column(name = "data_vendita")
    private LocalDateTime dataVendita;

    @Column(name = "data_attivazione")
    private LocalDateTime dataAttivazione;



    public enum Validita {
        HOUR, WEEK, MONTH
    }

    @Enumerated(EnumType.STRING)
    private Validita validita;

    @ManyToMany
    @JoinTable(
            name = "ticket_mezzo",
            joinColumns = @JoinColumn(name = "id_ticket"),
            inverseJoinColumns = @JoinColumn(name = "id_mezzo")
    )
    private List<Mezzo> mezzi;

    public Ticket() {
    }

    public Ticket(RivenditoreAutorizzato rivenditoreAutorizzato, Tipologia tipologia, LocalDateTime dataVendita, LocalDateTime dataAttivazione, Validita validita, List<Mezzo> mezzoAttivazione) {
        this.rivenditoreAutorizzato = rivenditoreAutorizzato;
        this.tipologia = tipologia;
        this.dataVendita = dataVendita;
        this.dataAttivazione = dataAttivazione;
        this.validita = validita;
        this.mezzoAttivazione = mezzoAttivazione;
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }

    public RivenditoreAutorizzato getRivenditoreAutorizzato() {
        return rivenditoreAutorizzato;
    }

    public void setRivenditoreAutorizzato(RivenditoreAutorizzato rivenditoreAutorizzato) {
        this.rivenditoreAutorizzato = rivenditoreAutorizzato;
    }

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }

    public LocalDateTime getDataVendita() {
        return dataVendita;
    }

    public void setDataVendita(LocalDateTime dataVendita) {
        this.dataVendita = dataVendita;
    }

    public LocalDateTime getDataAttivazione() {
        return dataAttivazione;
    }

    public void setDataAttivazione(LocalDateTime dataAttivazione) {
        this.dataAttivazione = dataAttivazione;
    }

    public Validita getValidita() {
        return validita;
    }

    public void setValidita(Validita validita) {
        this.validita = validita;
    }

    public Mezzo getMezzoAttivazione() {
        return (Mezzo) mezzoAttivazione;
    }

    public void setMezzoAttivazione(Mezzo mezzoAttivazione) {
        this.mezzoAttivazione = (List<Mezzo>) mezzoAttivazione;
    }
}
