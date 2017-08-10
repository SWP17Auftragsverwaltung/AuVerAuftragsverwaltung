/*------------------------------------------------------------------------------
* Klasse: GeschaeftspartnerverwaltungController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 2017-06-15 SAM Angelegt.
* 2017-06-26 GET Checkstyleprüfung sowie Fehlerkorrektur.
* 2017-07-27 BER Kommentarlayout angepasst.
*-------------------------------------------------------------------------------
 */
package auverauftragsverwaltung;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

/**
 *
 * @author Mudimbi
 */
public class GeschaeftspartnerverwaltungController implements Initializable {

    /**
     * Hier wird die Geschätspartner Scene verwaltet. Der Zugriff auf die
     * Datenbank etc wird hier implementiert
     */
    @FXML
    private Button closeGP;
 
    @FXML
    private TextField tf_lieferID;
    @FXML
    private TextField tf_anschriftID;
    @FXML
    private TextField tf_kreditlimit;

    /**
     * Methode zum Schließen der Geschäftspartnerverwaltung durch den Button
     * Abbrechen.
     *
     * @param event ActionEvent zur Prüfung ob der Abbrechen-Button getätigt
     * wurde
     */
    @FXML
    public void closeGeschaeftspartner(ActionEvent event) {
        Stage stage = (Stage) closeGP.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialisiert die Controller-Klasse.
     *
     * @param url Zu initialisierende URL.
     * @param rb Verwendete Ressourcen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        begrenzeTextFeldEingabe(tf_anschriftID, 6);
        begrenzeTextFeldEingabe(tf_lieferID, 6);
        begrenzeTextFeldEingabe(tf_kreditlimit, 6);
    }

    private void begrenzeTextFeldEingabe(TextField tf, int zahl) {

        tf.setTextFormatter(new TextFormatter<>(change
                -> change.getControlNewText().length() <= zahl ? change : null));
    }

}
