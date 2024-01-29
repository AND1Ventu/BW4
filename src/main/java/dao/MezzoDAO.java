package dao;

import entities.Manutenzione;
import entities.Mezzo;
import entities.Ticket;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class MezzoDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public MezzoDAO(){
        emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
        em = emf.createEntityManager();
    }

    public List<Ticket> getBigliettiVidimatiPerMezzoEPeriodo(Mezzo mezzo, LocalDateTime inizioPeriodo, LocalDateTime finePeriodo) {
        String jpql = "SELECT b FROM Biglietto b WHERE b.mezzo = :mezzo AND b.vidimato = true " +
                "AND b.timestampVidimazione BETWEEN :inizioPeriodo AND :finePeriodo";
        TypedQuery<Ticket> query = em.createQuery(jpql, Ticket.class);
        query.setParameter("mezzo", mezzo);
        query.setParameter("inizioPeriodo", inizioPeriodo);
        query.setParameter("finePeriodo", finePeriodo);
        return query.getResultList();
    }

    public List<Ticket> getBigliettiVidimatiPerMezzo(Mezzo mezzo) {
        String jpql = "SELECT b FROM Biglietto b WHERE b.mezzo = :mezzo AND b.vidimato = true";
        TypedQuery<Ticket> query = em.createQuery(jpql, Ticket.class);
        query.setParameter("mezzo", mezzo);
        return query.getResultList();
    }

    //STATI MEZZI

    public void aggiornaStatoMezzo(Long idMezzo){
        Mezzo mezzo = em.find(Mezzo.class,idMezzo);
        Manutenzione manutenzione = new Manutenzione();
        EntityTransaction et = em.getTransaction();
        if (mezzo == null){
            et.begin();
            if (mezzo.isInServizio() == true){
                mezzo.setInServizio(true);
            } else if (mezzo.isManutenzione()) {
                mezzo.setManutenzione(true);
                manutenzione.setDataInizio(LocalDateTime.now());
            }
            em.merge(mezzo);
            et.commit();

        }
    }

    public void fineStatoMezzo(Long IdMezzo) {
        Mezzo mezzo = em.find(Mezzo.class, IdMezzo);
        Manutenzione manutenzione = new Manutenzione();
        EntityTransaction et = em.getTransaction();
        if (mezzo != null) {
            et.begin();
            if (mezzo.isInServizio() == true) {
                mezzo.setInServizio(true);
            } else if (mezzo.isManutenzione()) {
                manutenzione.setDataFine(LocalDateTime.now());
            }
            em.merge(mezzo);
            et.commit();
        }
    }
}
