package dao;

import entities.Mezzo;
import entities.Manutenzione;
import entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class ManutenzioneDAO extends BaseDAO {

    public ManutenzioneDAO() {
        super("trasporto_pubblico");
    }

    public void aggiungiManutenzione(Manutenzione manutenzione) {
        EntityManager em = emf.createEntityManager();

        try {
            beginTransaction(em);
            em.persist(manutenzione);
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

    public void rimuoviManutenzione(Long id) {
        EntityManager em = emf.createEntityManager();

        try {
            beginTransaction(em);
            Manutenzione manutenzione = em.find(Manutenzione.class, id);
            if (manutenzione != null) {
                em.remove(manutenzione);
            }
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

}


