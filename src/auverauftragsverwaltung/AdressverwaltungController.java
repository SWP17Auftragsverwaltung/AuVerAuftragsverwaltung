/*------------------------------------------------------------------------------
* Klasse: AdressverwaltungController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 2017-06-14 SAM Angelegt.
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
public class AdressverwaltungController implements Initializable {

    /**
     *  Der Abbrechen-Button in der Adressverwaltung.
     */
    @FXML
    private Button closeAW;

     /**
     *  Methode zum Abbrechen der Adressverwaltung.
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen" 
     *              abfängt.
     */
    @FXML
    public void closeAdressverwaltung(ActionEvent event) {
        Stage stage = (Stage) closeAW.getScene().getWindow();
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
