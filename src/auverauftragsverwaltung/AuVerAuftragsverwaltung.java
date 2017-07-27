/*------------------------------------------------------------------------------
* Klasse: AuVerAuftragsverwaltung.
*-------------------------------------------------------------------------------
* Zweck:
* - Main-Klasse zur Ausführung der Software.
*-------------------------------------------------------------------------------
* Historie:
* 2017-06-08 HEN Angelegt.
* 2017-06-13 GET StartGui eingebunden.
* 2017-06-26 GET Checkstyleprüfung.
* 2017-07-27 BER Kommentarlayout angepasst.
*-------------------------------------------------------------------------------
*/

package auverauftragsverwaltung;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * @author Jakob
 */
public class AuVerAuftragsverwaltung extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));

        Scene scene = new Scene(root);
        stage.setMaximized(true);
        stage.setTitle("AuVer - Auftragsverwaltung");
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
