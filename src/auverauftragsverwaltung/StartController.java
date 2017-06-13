/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auverauftragsverwaltung;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller-Klasse der StartGUI
 *
 * @author Jakob
 */
public class StartController implements Initializable {

    @FXML
    private void oeffneArtikelverwaltung(ActionEvent event) throws IOException{
        ViewArtikelverwaltung viewArtikelverwaltung = new ViewArtikelverwaltung();
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
}
