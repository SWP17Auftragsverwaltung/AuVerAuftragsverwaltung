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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    void oeffneGeschaeftspartner(ActionEvent event){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Geschaeftspartnerverwaltung.fxml"));
        //Parent root1 =  (Parent) loader.load();
       Scene scene = new Scene(loader.load(), 800, 600);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Business Partners");
        //stage.setScene(new Scene(root1));
        stage.setScene(scene);
        stage.show();
    } catch(IOException e){
        System.out.println("Can't load the fckn window!");
    }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
}
