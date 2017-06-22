/**----------------------------------------------------------
* FXML Controller für die Klasse: Zahlungskonditionen
* 
* Datum     Name    Was
* 15.06.17  Sam     angelegt
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
 * FXML Controller class
 *
 * @author Mudimbi
 */
public class ZahlungskonditionenController implements Initializable {

     @FXML
    private Button closeZK;
   
   @FXML
    public void CloseZahlungskondition(ActionEvent event) {
    Stage stage = (Stage) closeZK.getScene().getWindow();
    stage.close();
}
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
