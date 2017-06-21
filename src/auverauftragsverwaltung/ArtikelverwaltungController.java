package auverauftragsverwaltung;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class.
 *
 * @author Mudimbi
 */
public class ArtikelverwaltungController implements Initializable {

    /**
     *
     */
    @FXML
    private Button closeArW;

    /**
     *
     * @param event ActionEvent.
     */
    @FXML
    public void closeArtikelverwaltung(ActionEvent event) {
        Stage stage = (Stage) closeArW.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialisiert die Controller-Klasse.
     * @param url URL zur initialisierung.
     * @param rb Resourcen die geladen werden sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

}
