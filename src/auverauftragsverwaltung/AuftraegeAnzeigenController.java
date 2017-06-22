/**----------------------------------------------------------
* FXML Controller für die Klasse: AufträgeAnzeigen
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
public class AuftraegeAnzeigenController implements Initializable {

    
    @FXML
    private Button closeAA;
   
   @FXML
    public void CloseAuftraegeAnzeigen(ActionEvent event) {
    Stage stage = (Stage) closeAA.getScene().getWindow();
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
