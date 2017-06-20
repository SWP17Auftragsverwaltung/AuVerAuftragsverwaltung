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
public class AdressverwaltungController implements Initializable {

    @FXML
    private Button closeAW;
   
   @FXML
    public void CloseAdressverwaltung(ActionEvent event) {
    Stage stage = (Stage) closeAW.getScene().getWindow();
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
