/**----------------------------------------------------------
* FXML Controller für die Klasse: AufträgeSuchen
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
public class AuftragSuchenController implements Initializable {

     @FXML
    private Button closeAS;
   
   @FXML
    public void CloseAuftragSuchen(ActionEvent event) {
    Stage stage = (Stage) closeAS.getScene().getWindow();
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
