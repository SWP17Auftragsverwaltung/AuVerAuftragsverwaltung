/*------------------------------------------------------------------------------
* Klasse: ArtikelverwaltungController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 2017-06-14 SAM Angelegt.
* 2017-06-26 GET Checkstyleprüfung.
*                Fehler bei Start der GUI behoben.
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

/**
 *
 * @author Mudimbi
 */
public class ArtikelverwaltungController implements Initializable {

    @FXML
    private TextArea artikelbeschreibung;
    @FXML
    private TextField einzelwert;
    @FXML
    private TextArea bestellbeschreibung;
    @FXML
    private TextField bestellwert;

    /**
     * Abbrechen-Button der Artikelverwaltung.
     */
    @FXML
    private Button closeArW;

    /**
     * Methode zum Abbrechen der Artikelverwaltung.
     *
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen"
     * abfängt.
     */
    @FXML
    public void closeArtikelverwaltung(ActionEvent event) {
        Stage stage = (Stage) closeArW.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialisiert die Controller-Klasse der Artikelverwaltung.
     *
     * @param url URL zur initialisierung.
     * @param rb Resourcen die geladen werden sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //  Artikelbeschreibung auf 250 Zeichen begrenzt
        begrenzeTextAreaEingabe(artikelbeschreibung, 250);

        // Bestellbeschreibung auf 250 Zeichen begrenzt
        begrenzeTextAreaEingabe(bestellbeschreibung, 250);

        // Einzelwert auf 6 Zeichen begrenzt      
        begrenzeTextFeldEingabe(einzelwert, 6);

        // Bestellwert auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(bestellwert, 6);
    }

    private void begrenzeTextFeldEingabe(TextField tf, int zahl) {

        tf.setTextFormatter(new TextFormatter<>(change
                -> change.getControlNewText().length() <= zahl ? change : null));
    }

    private void begrenzeTextAreaEingabe(TextArea ta, int zahl) {
        // Zeilenumbruch im TextArea Feld
        ta.setWrapText(true);
        ta.setTextFormatter(new TextFormatter<>(change
                -> change.getControlNewText().length() <= zahl ? change : null));
    }

}
