
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import entities.*;
import dao.*;

public class MainGUI extends Application {

    private DistributoreDAO distributoreDAO;
    private TicketDAO ticketDAO;
    private UtenteDAO utenteDAO;
    private MezzoDAO mezzoDAO;
    private ManutenzioneDAO manutenzioneDAO;
    private PercorsoDAO percorsoDAO;
    private TrattaDAO trattaDAO;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initDAOs();  // Inizializza i DAO
        primaryStage.setTitle("Gestione Dati Trasporto Pubblico");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Scelta dell'entità
        Label entityLabel = new Label("Seleziona l'entità:");
        ChoiceBox<String> entityChoice = new ChoiceBox<>();
        entityChoice.getItems().addAll("Distributore", "Ticket", "Utente", "Mezzo", "Manutenzione", "Percorso", "Tratta");
        entityChoice.setValue("Distributore");

        // Bottone di creazione
        Button createButton = new Button("Crea");
        createButton.setOnAction(e -> {
            String selectedEntity = entityChoice.getValue();
            switch (selectedEntity) {
                case "Distributore":
                    inserisciDistributore();
                    break;
                case "Ticket":
                    inserisciTicket();
                    break;
                case "Utente":
                    inserisciUtente();
                    break;
                case "Mezzo":
                    inserisciMezzo();
                    break;
                case "Manutenzione":
                    inserisciManutenzione();
                    break;
                case "Percorso":
                    inserisciPercorso();
                    break;
                case "Tratta":
                    inserisciTratta();
                    break;
            }
        });

        // Aggiungi controlli al layout
        grid.add(entityLabel, 0, 0);
        grid.add(entityChoice, 1, 0);
        grid.add(createButton, 2, 0);

        Scene scene = new Scene(grid, 400, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initDAOs() {
        // Inizializza i DAO
        distributoreDAO = new DistributoreDAO();
        ticketDAO = new TicketDAO();
        utenteDAO = new UtenteDAO();
        mezzoDAO = new MezzoDAO();
        manutenzioneDAO = new ManutenzioneDAO();
        percorsoDAO = new PercorsoDAO();
        trattaDAO = new TrattaDAO();
    }

    private void inserisciDistributore() {
        // Implementa la logica per l'inserimento di un distributore
        DistributoreAutorizzato distributore = new DistributoreAutorizzato();
        distributore.setAttivo(true);
        distributoreDAO.aggiungiDistributore(distributore);
        System.out.println("Distributore inserito con successo.");
    }


    private void inserisciTicket() {
        // Implementa la logica per l'inserimento di un ticket
        Ticket ticket = new Ticket();
        ticket.setDataVendita(LocalDateTime.now());
        ticket.setTipologia(Ticket.Tipologia.ABBONAMENTO);
        ticket.setValidita(Ticket.Validita.MONTH);
        ticket.setRivenditoreAutorizzato(distributoreDAO.getAllDistributori().getFirst());
        ticketDAO.aggiungiTicket(ticket);
        System.out.println("Ticket inserito con successo.");
    }

    private void inserisciUtente() {
        // Implementa la logica per l'inserimento di un utente
        Utente utente = new Utente();
        utente.setNome("Andrea");
        utente.setCognome("Ventura");
        utente.setTicket(ticketDAO.getAllTickets().getFirst());
        utenteDAO.aggiungiUtente(utente);
        System.out.println("Utente inserito con successo.");
    }

    private void inserisciMezzo() {
        // Implementa la logica per l'inserimento di un mezzo
        Mezzo mezzo = new Mezzo();
        mezzo.setCapienza(50);
        mezzo.setInServizio(true);
        mezzo.setTipologia(Mezzo.Tipologia.AUTOBUS);
        mezzo.setPercorso(percorsoDAO.getAllPercorsi().getFirst());
        mezzoDAO.aggiungiMezzo(mezzo);
        System.out.println("Mezzo inserito con successo.");
    }

    private void inserisciManutenzione() {
        // Implementa la logica per l'inserimento di una manutenzione
        Mezzo mezzoInManutenzione = mezzoDAO.getAllMezzi().getFirst();
        Manutenzione manutenzione = new Manutenzione();
        manutenzione.setDataInizio(LocalDateTime.now());
        manutenzione.setDataFine(LocalDateTime.now().plusDays(2));
        manutenzione.setMezzo(mezzoInManutenzione);
        manutenzioneDAO.aggiungiManutenzione(manutenzione);
        System.out.println("Manutenzione inserita con successo.");
    }

    private void inserisciPercorso() {
        // Implementa la logica per l'inserimento di un percorso
        Percorso percorso = new Percorso();
        percorso.setNomePercorso("Aeroporto-Stazione");
        percorsoDAO.aggiungiPercorso(percorso);
        System.out.println("Percorso inserito con successo.");
    }

    private void inserisciTratta() {
        // Implementa la logica per l'inserimento di una tratta
        Tratta tratta = new Tratta();
        tratta.setDataOraInizioTratta(LocalDateTime.now());
        tratta.setDataOraFineTratta(LocalDateTime.now().plusHours(2));
        tratta.setPercorso(percorsoDAO.getAllPercorsi().get(0));
        trattaDAO.aggiungiTratta(tratta);
        System.out.println("Tratta inserita con successo.");
    }
}

