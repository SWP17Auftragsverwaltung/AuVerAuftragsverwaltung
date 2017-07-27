/**----------------------------------------------------------
* FXML Controller für die Klasse: Auftragsverwaltung
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
 * FXML Controller-Klasse : Adressverwaltung.
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
