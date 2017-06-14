/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auverauftragsverwaltung;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
    private Button cancelButton;
   
   /*
   Mit dieser Methode schließt man ein offenes/geöffnetes Fenster
   mit dem "Abbrechen" Button
   */
   @FXML
    public void handleCloseButtonAction(ActionEvent event) {
    Stage stage = (Stage) cancelButton.getScene().getWindow();
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
