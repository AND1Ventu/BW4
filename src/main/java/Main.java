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
//          Ticket ticket = new Ticket();
//          ticket.setDataVendita(LocalDateTime.of(2024,06,01,0,0));
//          ticket.setTipologia(Ticket.Tipologia.ABBONAMENTO);
//          ticket.setValidita(Ticket.Validita.MONTH);
//          ticket.setDataAttivazione(LocalDateTime.of(2024,06,01,0,0));
//          ticket.setRivenditoreAutorizzato(distributoreDAO.getDistributoreById(64L));

//          ticketDAO.aggiungiTicket(ticket);


            //utente
//            Utente utente = new Utente();
//            utente.setNome("Mario");
//            utente.setCognome("Rossi");
//            utente.setTicket(ticketDAO.getById(55L));
//
//            utenteDAO.aggiungiUtente(utente);

            //percorso
//            Percorso percorso = new Percorso();
//            percorso.setNomePercorso("aeroporto-stazione");
//
//            percorsoDAO.aggiungiPercorso(percorso);

            //tratta
//            Tratta tratta = new Tratta();
//            tratta.setDataOraInizioTratta(LocalDateTime.of(2023,12,01,06,30));
//            tratta.setDataOraFineTratta(LocalDateTime.of(2023,12,01,8,30));
//            tratta.setPercorso(percorsoDAO.getPercorsoById(33L));
//
//            trattaDAO.aggiungiTratta(tratta);

            //mezzo
//            Mezzo mezzo = new Mezzo();
//            mezzo.setCapienza(55);
//            mezzo.setInServizio(true);
//            mezzo.setTipologia(Mezzo.Tipologia.AUTOBUS);
//            mezzo.setPercorso(percorsoDAO.getPercorsoById(33L));

//            mezzoDAO.aggiungiMezzo(mezzo);

            //creazione ticket collegato ad un mezzo

            Mezzo risultato = mezzoDAO.getMezzoById(34L);
            List<Mezzo> listaMezzi = new ArrayList<>();

            listaMezzi.add(risultato);


//        Ticket ticket = ticketDAO.getById(59L);
//        ticket.setDataVendita(LocalDateTime.of(2024,06,01,0,0));
//        ticket.setTipologia(Ticket.Tipologia.ABBONAMENTO);
//        ticket.setValidita(Ticket.Validita.MONTH);
//        ticket.setDataAttivazione(LocalDateTime.of(2024,06,01,0,0));
//        ticket.setRivenditoreAutorizzato(distributoreDAO.getDistributoreById(64L));
//        ticket.setMezzi(listaMezzi);
//
//        ticketDAO.aggiungiTicket(ticket);

            //manutenzione
//            Manutenzione manutenzione = new Manutenzione();
//            manutenzione.setDataInizio(LocalDateTime.of(2024,01,8,0,0));
//            manutenzione.setDataFine(LocalDateTime.of(2024,01,10,0,0));
//            manutenzione.setMezzo(mezzoDAO.getMezzoById(35L));
//
//            manutenzioneDAO.aggiungiManutenzione(manutenzione);


            //mezzo in manutenzione
//            Mezzo mezzo = new Mezzo();
//            mezzo.setCapienza(55);
//            Mezzo mezzo = mezzoDAO.getMezzoById(35L);
//            mezzo.setInServizio(false);
//            mezzo.setTipologia(Mezzo.Tipologia.AUTOBUS);
//            mezzo.setPercorso(percorsoDAO.getPercorsoById(33L));
//            mezzoDAO.aggiungiMezzo(mezzo);


        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                ticketDAO.rollbackTransaction(em);
            }
            e.printStackTrace();
        } finally {
//            ticketDAO.closeEntityManager(em);
        }

        //numero biglietti venduti da un distributore in un range temporale
        System.out.println(distributoreDAO.getTicketPuntoEmissioneById(63L,LocalDateTime.of(2024,01,01,0,0),LocalDateTime.of(2024,02,28,0,0)));

        //verifica validità abbonamento
        ticketDAO.ricercaTicketPerId(59L);

        //numri biglietti vidimati su un mezzo
        System.out.println(mezzoDAO.numeroBigliettiMezzo(34L));

        //numero biglietti vidimati in un periodo di tempo
        System.out.println(ticketDAO.attivatiPerDate(LocalDateTime.of(2024,01,01,0,0),LocalDateTime.of(2024,12,31,0,0)));

        //tenere traccia dello stato di servizio del mezzo
        System.out.println(mezzoDAO.statoManutenzione(35L));

        //

    }


}

