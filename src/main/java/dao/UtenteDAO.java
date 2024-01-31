package dao;

import entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

public class UtenteDAO {

    private EntityManagerFactory emf;

    public UtenteDAO() {
        this.emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
    }

    public void aggiungiUtente(Utente utente) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            beginTransaction(transaction);
            em.persist(utente);
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

    public void rimuoviUtente(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            beginTransaction(transaction);
            Utente utente = em.find(Utente.class, id);
            if (utente != null) {
                em.remove(utente);
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
