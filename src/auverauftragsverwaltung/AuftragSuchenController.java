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
    private TextField tfAuftragsID;

    @FXML
    private TextField tfKundenname;

    @FXML
    private TextField tfLieferantenname;

    @FXML
    private TextField tfArtikel;

    @FXML
    private TextField tfSteuersatz;

    @FXML
    private TextField tfMahnstufe;

    @FXML
    private TextField tfDatum;
    
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
           
        begrenzeTextFeldEingabe(tfAuftragsID, 6);
        begrenzeTextFeldEingabe(tfKundenname, 41);
        begrenzeTextFeldEingabe(tfLieferantenname, 41);
        begrenzeTextFeldEingabe(tfArtikel, 250);
        begrenzeTextFeldEingabe(tfSteuersatz, 3);
        begrenzeTextFeldEingabe(tfMahnstufe, 1);
        
        
        
    }

    private void begrenzeTextFeldEingabe(TextField tf, int zahl) {

        tf.setTextFormatter(new TextFormatter<>(change
                -> change.getControlNewText().length() <= zahl ? change : null));
    }

}
