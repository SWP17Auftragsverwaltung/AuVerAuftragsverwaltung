/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auverauftragsverwaltung;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jakob
 */
public class AuVerAuftragsverwaltung extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.out.println("Apfelsaft!");
    }
    
    public void FDD(){
        // Diese Ausgabe wurde nicht von Mudimbi implementiert
        System.out.println("Apfelsaft von Mudi!");
        System.out.println("Fischers Fritze fischt frische Fische!");
        System.out.println("Blaukraut bleibt blaukraut!");
        // Neuer Tag, neuer Versuch
    }
 
    public void He(){
        System.out.println("stirb");
    }
    
    public void HDF() {
            System.out.println("Test");
    }
    
}
