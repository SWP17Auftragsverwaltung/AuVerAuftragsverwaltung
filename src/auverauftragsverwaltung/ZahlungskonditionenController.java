/**
 * ----------------------------------------------------------
 * FXML Controller für die Klasse: Zahlungskonditionen
 *
 * Datum     Name    Was
 * 15.06.17  Sam     angelegt
 * 26.06.17  Get     Checkstyleprüfung.
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
 * FXML Controller-Klasse : Zahlungskonditionverwaltung.
 *
 * @author Mudimbi
 */
public class ZahlungskonditionenController implements Initializable {

    /**
     * Abbrechen-Button der Zahlungskonditionverwaltung.
     */
    @FXML
    private Button closeZK;

    /**
     * Methode zum Abbrechen der Zahlungskonditionverwaltung.
     *
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen"
     * abfängt.
     */
    @FXML
    public void closeZahlungskondition(ActionEvent event) {
        Stage stage = (Stage) closeZK.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialisiert die Controller-Klasse.
     *
     * @param url URL zur initialisierung.
     * @param rb Resourcen die geladen werden sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
