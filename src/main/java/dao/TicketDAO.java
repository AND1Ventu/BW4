package dao;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class TicketDAO {

    private EntityManagerFactory emf;

    public TicketDAO() {
        this.emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
    }

    public void aggiungiTicket(Ticket ticket) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ticket);
        em.getTransaction().commit();
        em.close();
    }

    public void rimuoviTicket(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Ticket ticket = em.find(Ticket.class, id);
        if (ticket != null) {
            em.remove(ticket);
        }
        em.getTransaction().commit();
        em.close();
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
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public Long attivatiPerDate(LocalDateTime data_inizio, LocalDateTime data_fine) {
        EntityManager em = emf.createEntityManager();

        try{
            String jpql = "SELECT COUNT(t) FROM Ticket t WHERE t.dataAttivazione BETWEEN :dataInizio AND :dataFine";

            return em.createQuery(jpql, Long.class)
                    .setParameter("dataInizio", data_inizio)
                    .setParameter("dataFine", data_fine)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

}

