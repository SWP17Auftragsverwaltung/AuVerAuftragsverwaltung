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
public class GeschaeftspartnerverwaltungController implements Initializable {
    
    /*
    *
    Hier wird die Geschätspartner Scene verwaltet. Der Zugriff auf die Datenbank etc wird hier 
    implementiert
    */
   @FXML
    private Button closeGP;
   
   @FXML
    public void CloseGeschaeftspartner(ActionEvent event) {
    Stage stage = (Stage) closeGP.getScene().getWindow();
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
