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

public class MezzoDAO {

    private EntityManagerFactory emf;

    public MezzoDAO() {
        this.emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
    }

    public void closeEntityManager(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
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

    public void aggiungiMezzo(Mezzo mezzo) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            beginTransaction(transaction);
            em.persist(mezzo);
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

    public void rimuoviMezzo(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            beginTransaction(transaction);
            Mezzo mezzo = em.find(Mezzo.class, id);
            if (mezzo != null) {
                em.remove(mezzo);
            }
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

    public Mezzo ricercaMezzoPerId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Mezzo.class, id);
        } finally {
            closeEntityManager(em);
        }
    }

    public List<Manutenzione> statoManutenzione(Long id) {

        EntityManager em = emf.createEntityManager();
        try {
            Mezzo mezzo = em.find(Mezzo.class, id);
            if (mezzo.isManutenzione()){
                String jpql = "SELECT m FROM Manutenzione WHERE m.mezzo.id = :id";
                return em.createQuery(jpql, Manutenzione.class)
                        .setParameter("id", id)
                        .getResultList();
            }
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public int numeroBigliettiMezzo(Long id){

        EntityManager em = emf.createEntityManager();
        try{
            Mezzo mezzo = em.find(Mezzo.class, id);
            if (mezzo != null){
                List<Ticket> tickets = mezzo.getTickets();
                return (int) tickets.size();
            }
            return 0;
        } finally {
            closeEntityManager(em);
        }
    }
}

