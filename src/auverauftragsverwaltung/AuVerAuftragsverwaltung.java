package auverauftragsverwaltung;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*----------------------------------------------------------*/
/* Datum Name Was */
/* 08.06.17 AHen angelegt */
/* 13.06.17 JGet StartGUI(Start.fxml) eingebunden */
/* 
/*----------------------------------------------------------*/

/**
 * AuVerAuftragsverwaltung 
 * @author Jakob
 */
public class AuVerAuftragsverwaltung extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));

        Scene scene = new Scene(root);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       
    }

}
