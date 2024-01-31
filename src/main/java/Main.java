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


        try {

            //rivenditore
//            DistributoreAutorizzato distributore = new DistributoreAutorizzato();
//            distributore.setAttivo(true);
//
//            distributoreDAO.saveDistributore(distributore);

            //ticket
//            Ticket ticket = new Ticket();
//            ticket.setDataVendita(LocalDateTime.now());
//            ticket.setTipologia(Ticket.Tipologia.ABBONAMENTO);
//            ticket.setValidita(Ticket.Validita.WEEK);
//            ticket.setRivenditoreAutorizzato(distributore);
//
//            ticketDAO.aggiungiTicket(ticket);


            //utente
            Utente utente = new Utente();
            utente.setNome("Mario");
            utente.setCognome("Rossi");
            utente.setTicket(ticketDAO.getById(55L));

            utenteDAO.aggiungiUtente(utente);

            //mezzo
//            Mezzo mezzo = new Mezzo();
//            mezzo.setCapienza(55);
//            mezzo.setInServizio(true);
//
//            mezzoDAO.aggiungiMezzo(mezzo);

            //manutenzione
//            Manutenzione manutenzione = new Manutenzione();
//            manutenzione.setDataInizio(LocalDateTime.of(2023,12,01,0,0));
//            manutenzione.setDataFine(LocalDateTime.of(2023,12,30,0,0));
//
//            manutenzioneDAO.aggiungiManutenzione(manutenzione);

            //percorso
//            Percorso percorso = new Percorso();
//            percorso.setNomePercorso("aeroporto-stazione");
//
//            percorsoDAO.aggiungiPercorso(percorso);

            //tratta
//            Tratta tratta = new Tratta();
//            tratta.setDataOraInizioTratta(LocalDateTime.of(2023,12,01,06,30));
//            tratta.setDataOraFineTratta(LocalDateTime.of(2023,12,01,07,30));
//
//            trattaDAO.aggiungiTratta(tratta);


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

