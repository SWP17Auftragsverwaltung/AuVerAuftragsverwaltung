package auverauftragsverwaltung;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jakob
 */
public class AuftragskonditionenController implements Initializable {
    
    /**
     * Zurück-Button der Zahlungskonditionverwaltung.
     */
    @FXML
    private Button closeZK;
    
     /**
     * Dieses Textfeld dient zur Eingabe der Zahlungskonditions-ID.
     */
    @FXML
    private TextField tfZahlungskonditionsID;

    /**
     * Diese ComboBox dient zur Auswahl der Auftragsarten.
     */
    @FXML
    private ComboBox<String> cbAuftragsart = new ComboBox();

    /**
     * Dieses Textfeld dient zur Eingabe des Zeitraums fuer den Liefertermin bei
     * Sofortauftraegen.
     */
    @FXML
    private TextField tfLieferzeitSOFORT;

    /**
     * Dieses Textfeld dient zur Eingabe des Zeitraums nachdem ein
     * Wunschlieferdatum angelegt werden kann.
     */
    @FXML
    private TextField tfSperrzeitWUNSCH;

    /**
     * Dieses Textfeld dient zur Eingabe der Tage fuer die Mahnstufe 1.
     */
    @FXML
    private TextField tfMahnzeit1;

    /**
     * Dieses Textfeld dient zur Eingabe der Tage fuer die Mahnstufe 2.
     */
    @FXML
    private TextField tfMahnzeit2;

    /**
     * Dieses Textfeld dient zur Eingabe der Tage fuer die Mahnstufe 3.
     */
    @FXML
    private TextField tfMahnzeit3;

    /**
     * Dieses Textfeld dient zur Eingabe des Zeitraums für Skontoabzug1.
     */
    @FXML
    private TextField tfSkontozeit1;

    /**
     * Dieses Textfeld dient zur Eingabe des Prozentsatzes für Skontozeitraum 1.
     */
    @FXML
    private TextField tfSkonto1;

    /**
     * Dieses Textfeld dient zur Eingabe des Zeitraums für Skontoabzug2.
     */
    @FXML
    private TextField tfSkontozeit2;

    /**
     * Dieses Textfeld dient zur Eingabe des Prozentsatzes für Skontozeitraum 2.
     */
    @FXML
    private TextField tfSkonto2;
    /**
     * ÜberschriftPane für den Eingabebereich.
     */
    
    @FXML
    private TitledPane zahlungskonditionendatensatzPane;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        cbAuftragsart.getItems().addAll("Barauftrag",
//                "Sofortauftrag",
//                "Terminauftrag",
//                "Bestellauftrag");
//        
    } 
    
    /**
     * Durch betätigen der Schaltfläche "Abbrechen" soll das Fenster der
     * Zahlungskonditionsverwaltung geschlossen werden.
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen"
     * abfängt.
     */
    @FXML
    public void closeAuftragskonditionen(ActionEvent event) {
        Stage stage = (Stage) closeZK.getScene().getWindow();
        stage.close();
    }
    
    
}
