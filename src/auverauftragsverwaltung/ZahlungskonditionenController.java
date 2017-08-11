/*------------------------------------------------------------------------------
* Klasse: ZahlungskonditionenController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 2017-06-15 SAM Angelegt.
* 2017-06-26 GET Checkstyleprüfung.
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
public class ZahlungskonditionenController implements Initializable {

    /**
     * Abbrechen-Button der Zahlungskonditionverwaltung.
     */
    @FXML
    private Button closeZK;
    /**
     * Textfeld der Lieferzeit bei einem Sofortauftrag
     */
    @FXML
    private TextField tf_lieferzeitSOFORT;
    /**
     * Textfeld der Sperrzeit bei einem Terminauftrag
     */
    @FXML
    private TextField tf_sperrzeitWUNSCH;
    /**
     * Mahnzeit 1 für die Zahlungskondition
     */
    @FXML
    private TextField tf_mahnzeit1;
    /**
     * Mahnzeit 2 für die Zahlungskondition
     */
    @FXML
    private TextField tf_mahnzeit2;
    /**
     * Mahnzeit 3 für die Zahlungskondition
     */
    @FXML
    private TextField tf_mahnzeit3;
    /**
     * Skontozeit 1
     */
    @FXML
    private TextField tf_skontozeit1;
    /**
     * Skonto 1
     */
    @FXML
    private TextField tf_skonto1;
    /**
     * Skontozeit 2
     */
    @FXML
    private TextField tf_skontozeit2;
    /**
     * Skonto 2
     */
    @FXML
    private TextField tf_skonto2;

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

        begrenzeTextFeldEingabe(tf_lieferzeitSOFORT, 4);
        begrenzeTextFeldEingabe(tf_sperrzeitWUNSCH, 4);
        begrenzeTextFeldEingabe(tf_mahnzeit1, 4);
        begrenzeTextFeldEingabe(tf_mahnzeit2, 4);
        begrenzeTextFeldEingabe(tf_mahnzeit3, 4);
        begrenzeTextFeldEingabe(tf_skontozeit1, 4);
        begrenzeTextFeldEingabe(tf_skonto1, 3);
        begrenzeTextFeldEingabe(tf_skontozeit2, 4);
        begrenzeTextFeldEingabe(tf_skonto2, 3);

    }

    private void begrenzeTextFeldEingabe(TextField tf, int zahl) {

        tf.setTextFormatter(new TextFormatter<>(change
                -> change.getControlNewText().length() <= zahl ? change : null));
    }

}
