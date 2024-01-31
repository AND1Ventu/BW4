

import dao.DistributoreDAO;
import dao.TicketDAO;
import entities.DistributoreAutorizzato;
import entities.Ticket;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblico");

        try {
            DistributoreAutorizzato d1 = new DistributoreAutorizzato();
            Ticket t1 = new Ticket();


            DistributoreDAO distributoreDAO = new DistributoreDAO();
            distributoreDAO.saveDistributore(d1);

            t1.setRivenditoreAutorizzato(d1);

            TicketDAO ticketDAO = new TicketDAO();
            ticketDAO.aggiungiTicket(t1);

            System.out.println("Entities saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error occurred while saving entities.");
        } finally {
            emf.close();
        }
    }
}