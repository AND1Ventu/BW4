import dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

import entities.*;

public class MainProva {

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
        RivenditoreDAO rivenditoreDAO = new RivenditoreDAO();

        try {
            // Inserimento dei dati nel database
            inserisciDati(em, distributoreDAO, ticketDAO, utenteDAO, mezzoDAO, manutenzioneDAO, percorsoDAO, trattaDAO,rivenditoreDAO);

            // Esecuzione delle query
            eseguiQuery(em, distributoreDAO, ticketDAO, mezzoDAO, percorsoDAO, trattaDAO);

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                ticketDAO.rollbackTransaction(em);
            }
            e.printStackTrace();
        } finally {
            //ticketDAO.closeEntityManager(em);
        }
    }

    public static void inserisciDati(EntityManager em, DistributoreDAO distributoreDAO, TicketDAO ticketDAO, UtenteDAO utenteDAO, MezzoDAO mezzoDAO, ManutenzioneDAO manutenzioneDAO, PercorsoDAO percorsoDAO, TrattaDAO trattaDAO, RivenditoreDAO rivenditoreDAO) {
        // Inserire qui il codice per l'inserimento dei dati nel database
        DistributoreAutorizzato distributore = new DistributoreAutorizzato();
        distributore.setAttivo(true);
        distributoreDAO.saveDistributore(distributore);

        //Inserimento del Rivenditore_Autorizzato
        RivenditoreAutorizzato rivenditoreAutorizzato = new RivenditoreAutorizzato();
        rivenditoreDAO.saveDistributore(rivenditoreAutorizzato);

        // Inserimento di un ticket
        Ticket ticket = new Ticket();
        ticket.setDataVendita(LocalDateTime.of(2024,06,01,0,0));
        ticket.setTipologia(Ticket.Tipologia.ABBONAMENTO);
        ticket.setValidita(Ticket.Validita.MONTH);
        ticket.setDataAttivazione(LocalDateTime.of(2024,06,01,0,0));
        ticket.setRivenditoreAutorizzato(distributoreDAO.getDistributoreById(14L));
        ticketDAO.aggiungiTicket(ticket);

        // Inserimento di un utente
        Utente utente = new Utente();
        utente.setNome("Mario");
        utente.setCognome("Rossi");
        utente.setTicket(ticketDAO.getById(11L));
        utenteDAO.aggiungiUtente(utente);

        // Inserimento di un percorso
        Percorso percorso = new Percorso();
        percorso.setNomePercorso("aeroporto-stazione");
        percorsoDAO.aggiungiPercorso(percorso);

        // Inserimento di una tratta
        Tratta tratta = new Tratta();
        tratta.setDataOraInizioTratta(LocalDateTime.of(2023,12,01,06,30));
        tratta.setDataOraFineTratta(LocalDateTime.of(2023,12,01,8,30));
        tratta.setPercorso(percorsoDAO.getPercorsoById(10L));
        trattaDAO.aggiungiTratta(tratta);

        // Inserimento di un mezzo
        Mezzo mezzo = new Mezzo();
        mezzo.setCapienza(55);
        mezzo.setInServizio(true);
        mezzo.setTipologia(Mezzo.Tipologia.AUTOBUS);
        mezzo.setPercorso(percorsoDAO.getPercorsoById(10L));
        mezzoDAO.aggiungiMezzo(mezzo);

        // Inserimento di una manutenzione
        Manutenzione manutenzione = new Manutenzione();
        manutenzione.setDataInizio(LocalDateTime.of(2024,01,8,0,0));
        manutenzione.setDataFine(LocalDateTime.of(2024,01,10,0,0));
        manutenzione.setMezzo(mezzoDAO.getMezzoById(10L));
        manutenzioneDAO.aggiungiManutenzione(manutenzione);

        // Aggiornamento dello stato del mezzo in manutenzione
        Mezzo mezzoInManutenzione = mezzoDAO.getMezzoById(10L);
        if(mezzoInManutenzione != null){
            mezzoInManutenzione.setInServizio(false);
            mezzoDAO.aggiungiMezzo(mezzoInManutenzione);
        } else {
            System.out.println("Il mezzo in manutezione non è stato trovato nel database");
        }

    }

    public static void eseguiQuery(EntityManager em, DistributoreDAO distributoreDAO, TicketDAO ticketDAO, MezzoDAO mezzoDAO, PercorsoDAO percorsoDAO, TrattaDAO trattaDAO) {
        try {
            // Numero biglietti venduti da un distributore in un range temporale
            System.out.println("Biglietti venduti da un distributore in un range temporale");
            System.out.println(distributoreDAO.getTicketPuntoEmissioneById(18L, LocalDateTime.of(2024,01,01,0,0), LocalDateTime.of(2024,02,28,0,0)));

            // Verifica validità abbonamento
            System.out.println("Verifica validità abbonamento");
            ticketDAO.ricercaTicketPerId(13L);

            // Numero biglietti vidimati su un mezzo
            System.out.println("Numeri biglietti vidimati su un mezzo");
            System.out.println(mezzoDAO.numeroBigliettiMezzo(12L));

            // Numero biglietti vidimati in un periodo di tempo
            System.out.println("Numero biglietti vidimati in un periodo di tempo");
            System.out.println(ticketDAO.attivatiPerDate(LocalDateTime.of(2024,01,01,0,0),LocalDateTime.of(2024,12,31,0,0)));

            // Tenere traccia dello stato di servizio del mezzo
            System.out.println("Tenere traccia dello stato di servizio del mezzo");
            System.out.println(mezzoDAO.statoManutenzione(12L));

            // Numero volte tratta-percorso da un mezzo
            System.out.println("Numero volte tratta-percorso da un mezzo");
            System.out.println(trattaDAO.numeroDiVoltePercorso(12L,12L));

            // Tempo effettivo di ogni tratta
            System.out.println("Tempo effettivo di ogni tratta");
            System.out.println(trattaDAO.getTempoPercorrenzaTratta(12L).toMinutes() + " minuti");

            // Tempo medio percorrenza percorso
            System.out.println("Tempo medio percorrenza percorso");
            System.out.println(percorsoDAO.calcolaTempoMedioPercorso());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
