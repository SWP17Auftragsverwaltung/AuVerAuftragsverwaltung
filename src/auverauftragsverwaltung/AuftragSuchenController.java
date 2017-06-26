/*----------------------------------------------------------
* FXML Controller für die Klasse: AufträgeSuchen
* 
* Datum     Name    Was
* 15.06.17  Sam     angelegt
* 26.06.17  Get     Checkstyleprüfung.
*----------------------------------------------------------*/
package auverauftragsverwaltung;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller-Klasse : Auftragssuche.
 *
 * @author Mudimbi
 */
public class AuftragSuchenController implements Initializable {

    /**
     * Abbrechen-Button der Auftragssuchmaske.
     */
    @FXML
    private Button closeAS;

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
        // TODO
    }

}
