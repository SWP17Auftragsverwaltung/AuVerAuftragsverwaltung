

package auverauftragsverwaltung;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jakob
 */
public class ViewArtikelverwaltung extends Stage{
    
    Stage stage;
    
    public ViewArtikelverwaltung() throws IOException{
        
        stage = this;
        Parent root = FXMLLoader.load(getClass().getResource("Artikelverwaltung.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }
    
}
