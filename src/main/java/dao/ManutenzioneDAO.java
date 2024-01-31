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

public class ManutenzioneDAO {

    private EntityManagerFactory emf;

    public ManutenzioneDAO() {
        this.emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
    }

    public void aggiungiManutenzione(Manutenzione manutenzione) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            beginTransaction(transaction);
            em.persist(manutenzione);
            commitTransaction(transaction, em);
        } catch (Exception e) {
            rollbackTransaction(transaction, em);
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

    public void beginTransaction(EntityTransaction transaction) {
        if (!transaction.isActive()) {
            transaction.begin();
        }
    }

    public void commitTransaction(EntityManager em) {
        em.getTransaction().commit();
        closeEntityManager(em);
    }

    public void rollbackTransaction(EntityManager em) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        closeEntityManager(em);
    }

    public void closeEntityManager(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}


