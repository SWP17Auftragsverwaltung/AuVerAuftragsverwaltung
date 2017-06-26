/**
 * ----------------------------------------------------------
 * FXML Controller für die Klasse: Geschäftspartnerverwaltung
 *
 * Datum     Name    Was
 * 15.06.17  Sam     angelegt
 * 26.06.17  Get     Styleprüfung mit Checkstyle sowie Fehlerkorrektur
 *----------------------------------------------------------
 */
package auverauftragsverwaltung;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller-Klasse: Geschäftspartnerverwaltung.
 *
 * @author Mudimbi
 */
public class GeschaeftspartnerverwaltungController implements Initializable {

    /**
    * Hier wird die Geschätspartner Scene verwaltet. Der Zugriff auf die
    * Datenbank etc wird hier 
    * implementiert
    */
    @FXML
    private Button closeGP;

    /**
     *  Methode zum Schließen der Geschäftspartnerverwaltung durch den
     * Button Abbrechen.
     * 
     * @param event ActionEvent zur Prüfung ob der Abbrechen-Button getätigt 
     *              wurde
     */
    @FXML
    public void closeGeschaeftspartner(ActionEvent event) {
        Stage stage = (Stage) closeGP.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialisiert die Controller-Klasse.
     * @param url Zu initialisierende URL.
     * @param rb  Verwendete Ressourcen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
