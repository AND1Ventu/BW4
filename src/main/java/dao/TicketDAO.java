package dao;

import entities.Ticket;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class TicketDAO extends BaseDAO{


    public TicketDAO() {
        super("trasporto_pubblico");
    }

    public void aggiungiTicket(Ticket ticket) {
        EntityManager em = emf.createEntityManager();

        try {
            beginTransaction(em);
            em.merge(ticket);
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

        try {
            beginTransaction(em);
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
                } else if ((validita == Ticket.Validita.WEEK) && dataAttivazione.plusDays(7).isAfter(LocalDateTime.now())) {
                    System.out.println("Il biglietto settimanale è ancora valido e scade il " + dataAttivazione.plusDays(7));
                } else if ((validita == Ticket.Validita.MONTH) && dataAttivazione.plusDays(30).isAfter(LocalDateTime.now())) {
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


        try {
            beginTransaction(em);
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

    public static List<Ticket> getAllTickets() {
        EntityManager em = emf.createEntityManager();

        try {
            String jpql = "SELECT t FROM Ticket t";
            TypedQuery<Ticket> query = em.createQuery(jpql, Ticket.class);
            return query.getResultList();
        } finally {
            closeEntityManager(em);
        }
    }
}
