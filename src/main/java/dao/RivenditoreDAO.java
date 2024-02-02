package dao;

import entities.RivenditoreAutorizzato;
import entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class RivenditoreDAO {

    private EntityManagerFactory emf;

    public RivenditoreDAO() {
        this.emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
    }

    public void closeEntityManagerFactory() {
        emf.close();
    }

    public void closeEntityManager(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
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

//    public void aggiungiDistributore(RivenditoreAutorizzato rivenditore) {
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction transaction = em.getTransaction();
//
//        try {
//            beginTransaction(transaction);
//            em.persist(rivenditore);
//            commitTransaction(transaction, em);
//        } catch (Exception e) {
//            rollbackTransaction(transaction, em);
//            throw e;
//        } finally {
//            closeEntityManager(em);
//        }
//    }

    public void aggiungiDistributore(RivenditoreAutorizzato rivenditore) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            beginTransaction(transaction);
            em.persist(rivenditore);
            commitTransaction(transaction, em);

        } catch (Exception e) {
            rollbackTransaction(transaction, em);
            throw e;
        } finally {
            try {
                em.refresh(rivenditore);
            }
            finally {

            }

            closeEntityManager(em);
        }
    }

    public void rimuoviDistributore(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            beginTransaction(transaction);
            RivenditoreAutorizzato rivenditore = em.find(RivenditoreAutorizzato.class, id);
            if (rivenditore != null) {
                em.remove(rivenditore);
            }
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

    public void saveDistributore(RivenditoreAutorizzato rivenditore) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            beginTransaction(transaction);
            em.persist(rivenditore);
//            em.refresh(rivenditore);
            commitTransaction(transaction, em);
        } catch (Exception e) {
            rollbackTransaction(transaction, em);
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    public void beginTransaction(EntityTransaction transaction) {
        if (!transaction.isActive()) {
            transaction.begin();
        }
    }

    public RivenditoreAutorizzato getDistributoreById(Long id) {
        EntityManager em = emf.createEntityManager();
        RivenditoreAutorizzato rivenditore = em.find(RivenditoreAutorizzato.class, id);
        em.close();
        return rivenditore;
    }

    public List<RivenditoreAutorizzato> getAllDistributori() {
        EntityManager em = emf.createEntityManager();
        List<RivenditoreAutorizzato> distributori = em.createQuery("SELECT d FROM RivenditoreAutorizzato d", RivenditoreAutorizzato.class)
                .getResultList();
        em.close();
        return distributori;
    }

    public int getTicketPuntoEmissioneById(Long id, LocalDateTime dataInizio, LocalDateTime dataFine) {
        EntityManager em = emf.createEntityManager();
        int conteggioTicket = 0;

        try {
            List<Ticket> tickets = em.createQuery("SELECT t FROM Ticket t WHERE t.rivenditoreAutorizzato.idDistributore = :id", Ticket.class)
                    .setParameter("id", id)
                    .getResultList();

            for (Ticket ticket : tickets) {
                LocalDateTime dataVendita = ticket.getDataVendita();

                if (dataVendita != null && dataVendita.isAfter(dataInizio) && dataVendita.isBefore(dataFine)) {
                    conteggioTicket++;
                }
            }
        } finally {
            em.close();
        }

        return conteggioTicket;
    }

    public void commitTransaction(EntityTransaction transaction, EntityManager em) {
        transaction.commit();
    }

    public void rollbackTransaction(EntityTransaction transaction, EntityManager em) {
        if (transaction.isActive()) {
            transaction.rollback();
        }
    }
}
