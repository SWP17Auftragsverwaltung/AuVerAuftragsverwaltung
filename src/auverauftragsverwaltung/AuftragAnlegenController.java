/*------------------------------------------------------------------------------
* Klasse: AuftragAnlegenController.
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
import javafx.stage.Stage;

/**
 *
 * @author Mudimbi
 */
public class AuftragAnlegenController implements Initializable {

    /**
     * Abbrechen-Button in der "Auftrag anlegen" - View.
     */
    @FXML
    private Button closeAAn;

    /**
     * Methode zum Abbrechen der "Auftrag anlegen" - View.
     *
     * @param event ActionEvent zur Prüfung ob der "Auftrag suchen" - Button
     * getätigt wurde.
     */
    @FXML
    public void closeAuftraegeAnlegen(ActionEvent event) {
        Stage stage = (Stage) closeAAn.getScene().getWindow();
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
