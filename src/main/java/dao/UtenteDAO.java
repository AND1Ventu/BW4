package dao;

import entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

public class UtenteDAO extends BaseDAO{


    public UtenteDAO() {
        super("trasporto_pubblico");
    }

    public void aggiungiUtente(Utente utente) {
        EntityManager em = emf.createEntityManager();

        try {
            beginTransaction(em);
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

        try {
            beginTransaction(em);
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

}
