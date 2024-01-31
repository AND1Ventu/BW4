import dao.*;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("trasporto_pubblico").createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        DistributoreDAO distributoreDAO = new DistributoreDAO();
        TicketDAO ticketDAO = new TicketDAO();
        UtenteDAO utenteDAO = new UtenteDAO();
        MezzoDAO mezzoDAO = new MezzoDAO();
        ManutenzioneDAO manutenzioneDAO = new ManutenzioneDAO();
        PercorsoDAO percorsoDAO = new PercorsoDAO();
        TrattaDAO trattaDAO = new TrattaDAO();

        List<Ticket> ticketList = new ArrayList<>();

        try {
            DistributoreAutorizzato distributore = new DistributoreAutorizzato();
            distributore.setAttivo(true);

            distributoreDAO.saveDistributore(distributore);
        } catch (Exception e) {
            if (transaction.isActive()) {
                distributoreDAO.rollbackTransaction(transaction, em);
            }
            e.printStackTrace();
//        } finally {
//            distributoreDAO.closeEntityManagerFactory();
        }

//        try {
//            Utente utente1 = new Utente();
//            utente1.setNome("Mario");
//            utente1.setCognome("Rossi");
//
//            utenteDAO.aggiungiUtente(utente1);
//        } catch (Exception e) {
//            if (em.getTransaction().isActive()) {
//                ticketDAO.rollbackTransaction(em);
//            }
//            e.printStackTrace();
////        } finally {
////            utenteDAO.closeEntityManager(em);
//        }

        try {
            //ticket
            Ticket ticket = new Ticket();
            ticket.setDataVendita(LocalDateTime.now());
            ticket.setTipologia(Ticket.Tipologia.ABBONAMENTO);
            ticket.setValidita(Ticket.Validita.WEEK);
//            ticket.setRivenditoreAutorizzato(distributore);

            ticketDAO.aggiungiTicket(ticket);


            //rivenditore
            DistributoreAutorizzato distributore = new DistributoreAutorizzato();
            distributore.setAttivo(true);
            ticketList.add(ticket);
            distributore.setTicketDistribuiti(ticketList);

            distributoreDAO.saveDistributore(distributore);


            //utente
            Utente utente1 = new Utente();
            utente1.setNome("Mario");
            utente1.setCognome("Rossi");
            utente1.setTicket(ticket);

            utenteDAO.aggiungiUtente(utente1);


        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                ticketDAO.rollbackTransaction(em);
            }
            e.printStackTrace();
//        } finally {
//            ticketDAO.closeEntityManager(em);
        }

        try {
            Mezzo mezzo1 = new Mezzo();
            mezzo1.setCapienza(55);
            mezzo1.setInServizio(true);

            mezzoDAO.aggiungiMezzo(mezzo1);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                mezzoDAO.rollbackTransaction(em);
            }
            e.printStackTrace();
//        } finally {
//            mezzoDAO.closeEntityManager(em);
        }

        try {
            Manutenzione manutenzione1 = new Manutenzione();
            manutenzione1.setDataInizio(LocalDateTime.of(2023,12,01,0,0));
            manutenzione1.setDataFine(LocalDateTime.of(2023,12,30,0,0));

            manutenzioneDAO.aggiungiManutenzione(manutenzione1);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                manutenzioneDAO.rollbackTransaction(em);
            }
            e.printStackTrace();
//        } finally {
//            manutenzioneDAO.closeEntityManager(em);
        }

        try {
            Percorso percorso1 = new Percorso();
            percorso1.setNomePercorso("aeroporto-stazione");

            percorsoDAO.aggiungiPercorso(percorso1);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                percorsoDAO.rollbackTransaction(em);
            }
            e.printStackTrace();
//        } finally {
//            percorsoDAO.closeEntityManager(em);
        }

        try {
            Tratta tratta1 = new Tratta();
            tratta1.setDataOraInizioTratta(LocalDateTime.of(2023,12,01,06,30));
            tratta1.setDataOraFineTratta(LocalDateTime.of(2023,12,01,07,30));

            trattaDAO.aggiungiTratta(tratta1);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                trattaDAO.rollbackTransaction(em);
            }
            e.printStackTrace();
//        } finally {
//            trattaDAO.closeEntityManager(em);
        }


    }


}

