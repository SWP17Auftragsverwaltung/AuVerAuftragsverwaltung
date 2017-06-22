/**----------------------------------------------------------
* FXML Controller für die Klasse: Aufträge anlegen
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
public class AuftragAnlegenController implements Initializable {

     @FXML
    private Button closeAAn;
   
   @FXML
    public void CloseAuftraegeAnlegen(ActionEvent event) {
    Stage stage = (Stage) closeAAn.getScene().getWindow();
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
