import dao.DistributoreDAO;
import dao.TicketDAO;
import entities.DistributoreAutorizzato;
import entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("trasporto_pubblico").createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        DistributoreDAO distributoreDAO = new DistributoreDAO();
        TicketDAO ticketDAO = new TicketDAO();

        try {
            DistributoreAutorizzato distributore = new DistributoreAutorizzato();
            distributoreDAO.saveDistributore(distributore);
            distributoreDAO.commitTransaction(transaction, em);
        } catch (Exception e) {
            if (transaction.isActive()) {
                distributoreDAO.rollbackTransaction(transaction, em);
            }
            e.printStackTrace();
        } finally {
            distributoreDAO.closeEntityManagerFactory();
        }

        try {
            Ticket ticket = new Ticket();
            ticketDAO.aggiungiTicket(ticket);
            ticketDAO.commitTransaction(em);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                ticketDAO.rollbackTransaction(em);
            }
            e.printStackTrace();
        } finally {
            ticketDAO.closeEntityManager(em);
        }
    }
}

