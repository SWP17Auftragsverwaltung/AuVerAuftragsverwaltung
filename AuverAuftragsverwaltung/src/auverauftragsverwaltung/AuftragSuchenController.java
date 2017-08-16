/*------------------------------------------------------------------------------
* Klasse: AuftragSuchenController.
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
public class AuftragSuchenController implements Initializable {

    /**
     * Abbrechen-Button der Auftragssuchmaske.
     */
    @FXML
    private Button closeAS;

    @FXML
    private TextField auftragsID;

    @FXML
    private TextField kundenname;

    @FXML
    private TextField lieferantenname;

    @FXML
    private TextField artikel;

    @FXML
    private TextField steuersatz;

    @FXML
    private TextField mahnstufe;

    /**
     * Mehtode zum Abbrechen der Auftragssuche.
     *
     * @param event ActionEvent zur Prüfung ob der "Auftrag suchen" - Button
     * getätigt wurde.
     */
    @FXML
    public void closeAuftragSuchen(ActionEvent event) {
        Stage stage = (Stage) closeAS.getScene().getWindow();
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
           
        begrenzeTextFeldEingabe(auftragsID, 6);
        begrenzeTextFeldEingabe(kundenname, 41);
        begrenzeTextFeldEingabe(lieferantenname, 41);
        begrenzeTextFeldEingabe(artikel, 250);
        begrenzeTextFeldEingabe(steuersatz, 3);
        begrenzeTextFeldEingabe(mahnstufe, 1);
        
        
        
    }

    private void begrenzeTextFeldEingabe(TextField tf, int zahl) {

        tf.setTextFormatter(new TextFormatter<>(change
                -> change.getControlNewText().length() <= zahl ? change : null));
    }

}
