/**----------------------------------------------------------
* FXML Controller für die Klasse: AufträgeAnzeigen
* 
* Datum     Name    Was
* 15.06.17  Sam     angelegt
*----------------------------------------------------------*/
package auverauftragsverwaltung;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mudimbi
 */
public class AuftraegeAnzeigenController implements Initializable {

    
    @FXML
    void AuftragSuchen(ActionEvent event){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AuftragSuchen.fxml"));
       Scene scene = new Scene(loader.load(), 755, 500);
        Stage stage = new Stage();
        stage.setTitle("Auftrag suchen");
        stage.setScene(scene);
        stage.show();
    } catch(IOException e){
        System.out.println("Can't load the AuftragSuchen!");
    }
    }
    
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
