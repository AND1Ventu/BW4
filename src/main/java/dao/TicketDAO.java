package dao;

import entities.DistributoreAutorizzato;
import entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

public class TicketDAO {

    private EntityManagerFactory emf;

    public TicketDAO() {
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
//        closeEntityManager(em);
    }

    public void rollbackTransaction(EntityManager em) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        closeEntityManager(em);
    }

    public void aggiungiTicket(Ticket ticket) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            beginTransaction(transaction);
            em.persist(ticket);
//            em.refresh(ticket);
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

    public void rimuoviTicket(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            beginTransaction(transaction);
            Ticket ticket = em.find(Ticket.class, id);
            if (ticket != null) {
                em.remove(ticket);
            }
            commitTransaction(em);
        } catch (Exception e) {
            rollbackTransaction(em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

    public void ricercaTicketPerId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Ticket result = em.find(Ticket.class, id);

            if (result != null && Ticket.Tipologia.ABBONAMENTO.equals(result.getTipologia())) {
                LocalDateTime dataAttivazione = result.getDataAttivazione();
                Ticket.Validita validita = result.getValidita();

                if (dataAttivazione == null) {
                    System.out.println("Il biglietto è ancora da attivare");
                } else if ((validita == Ticket.Validita.WEEK) && result.getDataAttivazione().plusDays(7).isBefore(LocalDateTime.now())) {
                    System.out.println("Il biglietto settimanale è ancora valido e scade il " + dataAttivazione.plusDays(7));
                } else if ((validita == Ticket.Validita.MONTH) && result.getDataAttivazione().plusDays(30).isBefore(LocalDateTime.now())) {
                    System.out.println("Il biglietto mensile è ancora valido e scade il " + dataAttivazione.plusDays(30));
                } else {
                    System.out.println("Il biglietto è scaduto");
                }
            }
        } finally {
            closeEntityManager(em);
        }
    }

    public Long attivatiPerDate(LocalDateTime data_inizio, LocalDateTime data_fine) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();


        try {
            beginTransaction(transaction);
            String jpql = "SELECT COUNT(t) FROM Ticket t WHERE t.dataAttivazione BETWEEN :dataInizio AND :dataFine";

            return em.createQuery(jpql, Long.class)
                    .setParameter("dataInizio", data_inizio)
                    .setParameter("dataFine", data_fine)
                    .getSingleResult();
        } catch (Exception e) {
            rollbackTransaction(em);
            throw e;
        } finally {
            closeEntityManager(em);
        }
    }

    public Ticket getById(Long id){
        EntityManager em = emf.createEntityManager();
        Ticket ticket = em.find(Ticket.class, id);
        em.close();
        return ticket;
    }
}
