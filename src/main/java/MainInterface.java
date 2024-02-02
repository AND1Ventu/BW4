import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import dao.DistributoreDAO;
import dao.TicketDAO;
import dao.MezzoDAO;
import dao.TrattaDAO;
import dao.PercorsoDAO;
import entities.*;

import java.time.LocalDateTime;

public class MainInterface extends Application {

    private DistributoreDAO distributoreDAO = new DistributoreDAO();
    private TicketDAO ticketDAO = new TicketDAO();
    private MezzoDAO mezzoDAO = new MezzoDAO();
    private TrattaDAO trattaDAO = new TrattaDAO();
    private PercorsoDAO percorsoDAO = new PercorsoDAO();

    private Label resultLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Risultati Operazioni");

        // Create buttons for each operation
        Button btn1 = new Button("Biglietti venduti da un distributore");
        Button btn2 = new Button("Verifica validità abbonamento");
        Button btn3 = new Button("Numeri biglietti vidimati su un mezzo");
        Button btn4 = new Button("Numero biglietti vidimati in un periodo di tempo");
        Button btn5 = new Button("Tenere traccia dello stato di servizio del mezzo");
        Button btn6 = new Button("Numero volte tratta-percorso da un mezzo");
        Button btn7 = new Button("Tempo effettivo di ogni tratta");
        Button btn8 = new Button("Tempo medio percorrenza percorso");

        // Set actions for each button
        btn1.setOnAction(e -> showResult(1L));
        btn2.setOnAction(e -> showResult(2L));
        btn3.setOnAction(e -> showResult(3L));
        btn4.setOnAction(e -> showResult(4L));
        btn5.setOnAction(e -> showResult(5L));
        btn6.setOnAction(e -> showResult(6L));
        btn7.setOnAction(e -> showResult(7L));
        btn8.setOnAction(e -> showResult(8L));

        // Creazione di un layout VBox per organizzare i bottoni e le etichette
        VBox layout = new VBox(10);
        layout.getChildren().addAll(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, resultLabel);
        layout.setPadding(new Insets(10));

        // Creazione della scena
        Scene scene = new Scene(layout, 600, 400);

        // Impostazione della scena sullo stage
        primaryStage.setScene(scene);

        // Visualizzazione dello stage
        primaryStage.show();
    }

    // Method to show the result based on the button clicked
    private void showResult(long btnNumber) {
        switch ((int) btnNumber) {
            case 1:
                resultLabel.setText("Biglietti venduti da un distributore: " +
                        distributoreDAO.getTicketPuntoEmissioneById(1L, LocalDateTime.of(2024, 01, 01, 0, 0),
                                LocalDateTime.of(2024, 02, 28, 0, 0)));
                break;
            case 2:
                resultLabel.setText("Verifica validità abbonamento: ");
                ticketDAO.ricercaTicketPerId(1L);
                break;
            case 3:
                resultLabel.setText("Numeri biglietti vidimati su un mezzo: " +
                        mezzoDAO.numeroBigliettiMezzo(1L));
                break;
            case 4:
                resultLabel.setText("Numero biglietti vidimati in un periodo di tempo: " +
                        ticketDAO.attivatiPerDate(LocalDateTime.of(2024, 01, 01, 0, 0),
                                LocalDateTime.of(2024, 12, 31, 0, 0)));
                break;
            case 5:
                resultLabel.setText("Tenere traccia dello stato di servizio del mezzo: " +
                        mezzoDAO.statoManutenzione(1L));
                break;
            case 6:
                resultLabel.setText("Numero volte tratta-percorso da un mezzo: " +
                        trattaDAO.numeroDiVoltePercorso(2L, 1L));
                break;
            case 7:
                resultLabel.setText("Tempo effettivo di ogni tratta: " +
                        trattaDAO.getTempoPercorrenzaTratta(1L).toMinutes() + " minuti");
                break;
            case 8:
                resultLabel.setText("Tempo medio percorrenza percorso: " +
                        percorsoDAO.calcolaTempoMedioPercorso());
                break;
        }
    }
}
