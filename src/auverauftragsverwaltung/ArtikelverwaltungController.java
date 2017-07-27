/**
 * ----------------------------------------------------------
 * FXML Controller für die Klasse: Artikelverwaltung
 *
 * Datum     Name    Was
 * 15.06.17  Sam     angelegt
 * 26.06.17  Get     fehler bei Start der GUI behoben.
 *                   Checkstyleprüfung.
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
 * FXML Controller class.
 *
 * @author Mudimbi
 */
public class ArtikelverwaltungController implements Initializable {

    /**
     *  Abbrechen-Button der Artikelverwaltung.
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

    }

}
