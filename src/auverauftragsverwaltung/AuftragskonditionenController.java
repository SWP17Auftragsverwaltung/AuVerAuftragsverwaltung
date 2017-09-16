
package auverauftragsverwaltung;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jakob
 */
public class AuftragskonditionenController implements Initializable {

    @FXML
    private TitledPane zahlungskonditionendatensatzPane;
    @FXML
    private TextField tfZahlungskonditionsID;
    @FXML
    private ComboBox<?> cbAuftragsart;
    @FXML
    private TextField tfLieferzeitSOFORT;
    @FXML
    private TextField tfSperrzeitWUNSCH;
    @FXML
    private TextField tfMahnzeit1;
    @FXML
    private TextField tfMahnzeit2;
    @FXML
    private TextField tfMahnzeit3;
    @FXML
    private TextField tfSkontozeit1;
    @FXML
    private TextField tfSkonto1;
    @FXML
    private TextField tfSkontozeit2;
    @FXML
    private TextField tfSkonto2;
    @FXML
    private Pane pane;
    @FXML
    private Button closeZK;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void closeAuftragskonditionen(ActionEvent event) {

        Stage stage = (Stage) closeZK.getScene().getWindow();
        stage.close();
    }
    
}
