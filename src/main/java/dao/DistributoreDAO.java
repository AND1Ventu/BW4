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

public class DistributoreDAO extends BaseDAO {

    public DistributoreDAO() {
        super("trasporto_pubblico");
    }


    public void aggiungiDistributore(DistributoreAutorizzato distributore) {
        EntityManager em = emf.createEntityManager();

        try {
            beginTransaction(em);
            em.persist(distributore);
            commitTransaction(em);

        } catch (Exception e) {
            rollbackTransaction( em);
            throw e;
        } finally {
            try {
                em.refresh(distributore);
            }
            finally {

            }

            closeEntityManager(em);
        }
    }

    public void rimuoviDistributore(Long id) {
        EntityManager em = emf.createEntityManager();

        try {
            beginTransaction(em);
            DistributoreAutorizzato distributore = em.find(DistributoreAutorizzato.class, id);
            if (distributore != null) {
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

        try {
            beginTransaction(em);
            em.persist(distributore);
//            em.refresh(distributore);
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    public DistributoreAutorizzato getDistributoreById(Long id) {
        EntityManager em = emf.createEntityManager();
        DistributoreAutorizzato distributore = em.find(DistributoreAutorizzato.class, id);
        em.close();
        return distributore;
    }

    public static List<DistributoreAutorizzato> getAllDistributori() {
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

}
