package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mezzo")
public class Mezzo {

    @Id
    @Column(name = "id_mezzo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMezzo;


    public enum Tipologia {
        TRAM, AUTOBUS
    }

    @Enumerated(EnumType.STRING)
    private Tipologia tipologia;

    private boolean manutenzione;

    private int capienza;

    @Column(name = "in_servizio")
    private boolean inServizio;

    @OneToMany(mappedBy = "mezzo")
    private List<Manutenzione> manutenzioni;

    @ManyToOne
    @JoinColumn(name = "id_percorso")
    private Percorso percorso;

    @ManyToMany(mappedBy = "mezzi")
    private List<Ticket> tickets;

    public Mezzo() {
    }

    public Mezzo(Long idMezzo, Tipologia tipologia, boolean manutenzione, int capienza, boolean inServizio, List<Manutenzione> manutenzioni, Percorso percorso, List<Ticket> tickets) {
        this.idMezzo = idMezzo;
        this.tipologia = tipologia;
        this.manutenzione = manutenzione;
        this.capienza = capienza;
        this.inServizio = inServizio;
        this.manutenzioni = manutenzioni;
        this.percorso = percorso;
        this.tickets = tickets;
    }

    public Long getIdMezzo() {
        return idMezzo;
    }

    public void setIdMezzo(Long idMezzo) {
        this.idMezzo = idMezzo;
    }

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }

    public boolean isManutenzione() {
        return manutenzione;
    }

    public void setManutenzione(boolean manutenzione) {
        this.manutenzione = manutenzione;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public boolean isInServizio() {
        return inServizio;
    }

    public void setInServizio(boolean inServizio) {
        this.inServizio = inServizio;
    }

    public List<Manutenzione> getManutenzioni() {
        return manutenzioni;
    }

    public void setManutenzioni(List<Manutenzione> manutenzioni) {
        this.manutenzioni = manutenzioni;
    }

    public Percorso getPercorso() {
        return percorso;
    }

    public void setPercorso(Percorso percorso) {
        this.percorso = percorso;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
