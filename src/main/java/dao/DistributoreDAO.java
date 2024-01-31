package dao;

import entities.DistributoreAutorizzato;
import entities.Mezzo;
import entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class DistributoreDAO {

    private EntityManagerFactory emf;

    public DistributoreDAO() {
        this.emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
    }

    public void closeEntityManagerFactory() {
        emf.close();
    }

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

    public void aggiungiDistributore(DistributoreAutorizzato distributore) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            beginTransaction(transaction);
            em.persist(distributore);
            commitTransaction(transaction, em);
        } catch (Exception e) {
            rollbackTransaction(transaction, em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

    public void rimuoviDistributore(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            beginTransaction(em);
            DistributoreAutorizzato distributore = em.find(DistributoreAutorizzato.class, id);
            if (mezzo != null) {
                em.remove(distributore);
            }
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

    public void saveDistributore(DistributoreAutorizzato distributore) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            beginTransaction(transaction);
            em.merge(distributore);
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

    public DistributoreAutorizzato getDistributoreById(Long id) {
        EntityManager em = emf.createEntityManager();
        DistributoreAutorizzato distributore = em.find(DistributoreAutorizzato.class, id);
        em.close();
        return distributore;
    }

    public List<DistributoreAutorizzato> getAllDistributori() {
        EntityManager em = emf.createEntityManager();
        List<DistributoreAutorizzato> distributori = em.createQuery("SELECT d FROM DistributoreAutorizzato d", DistributoreAutorizzato.class)
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
