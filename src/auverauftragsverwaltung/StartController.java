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
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller-Klasse der StartGUI
 *
 * @author Jakob
 */
public class StartController implements Initializable {

    @FXML
    void oeffneArtikelverwaltung(ActionEvent event){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Artikelverwaltung.fxml"));
       Scene scene = new Scene(loader.load(), 1000, 600);
        Stage stage = new Stage();
        stage.setTitle("Artikelverwaltung");
        stage.setScene(scene);
        stage.show();
    } catch(IOException e){
        System.out.println("Can't load the Artikel!");
    }
    }
    
    @FXML
    void oeffneGeschaeftspartner(ActionEvent event){
        try {
            //Die Ressource wird bezogen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Geschaeftspartnerverwaltung.fxml"));
        // Die Ressource wird geladen und Abmessungen werden festgelegt
       Scene scene = new Scene(loader.load(), 1000, 600);
        Stage stage = new Stage();
        stage.setTitle("Geschäftspartnerverwaltung");
        stage.setScene(scene);
        stage.show();
    } catch(IOException e){
        System.out.println("Can't load the Businesspartner!");
    }
    }
    
//    @FXML
//    void oeffneAdressverwaltung(ActionEvent event){
//        try {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Adressverwaltung.fxml"));
//       Scene scene = new Scene(loader.load(), 800, 600);
//        Stage stage = new Stage();
//        stage.setTitle("Adressverwaltung");
//        stage.setScene(scene);
//        stage.show();
//    } catch(IOException e){
//        System.out.println("Can't load the Adress!");
//    }
//    }
    
//    @FXML
//    void oeffneZahlungskondition(ActionEvent event){
//        try {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Zahlungskonditionsverwaltung.fxml"));
//       Scene scene = new Scene(loader.load(), 800, 600);
//        Stage stage = new Stage();
//        stage.setTitle("Zahlungskonditionsverwaltung");
//        stage.setScene(scene);
//        stage.show();
//    } catch(IOException e){
//        System.out.println("Can't load the Conditions!");
//    }
//    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
}
