
package auverauftragsverwaltung;

import Datenbank.AuftragskonditionsDAO;
import Klassen.Zahlungskonditionen;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    @FXML
    private TextField tfAuftragsart;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            gibKonditionen();
        
        } catch (SQLException ex) {
            Logger.getLogger(AuftragskonditionenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuftragskonditionenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
    /**
     * Schließt die Zahlungskonditionen.
     * @param event Event
     */
    @FXML
    private void closeAuftragskonditionen(ActionEvent event) {

        Stage stage = (Stage) closeZK.getScene().getWindow();
        stage.close();
    }
    
    
    /**
     * Gibt die Zahlungskonditionen zu einem bestimmten Auftrag wieder.
     * @throws SQLException SQLFehler
     * @throws java.io.IOException IOFehler
     */
    public void gibKonditionen() throws SQLException, IOException {
        AuftragskonditionsDAO akd = new AuftragskonditionsDAO();
        Zahlungskonditionen zk;
        String auftragskopfID;   
        
        FXMLLoader fxmlLoader 
            = new FXMLLoader(getClass().getResource("Auftraege.fxml"));
           
        AuftraegeController controller 
            = fxmlLoader.<AuftraegeController>getController();
        
        
        auftragskopfID = controller.gibAuftragskopfID();
        
        zk = akd.gibKonditionZuAuftrag(auftragskopfID); 
        
        this.tfLieferzeitSOFORT.setText(zk.getLieferzeitSOFORT());
        this.tfMahnzeit1.setText(zk.getMahnzeit1());
        this.tfMahnzeit2.setText(zk.getMahnzeit2());
        this.tfMahnzeit3.setText(zk.getMahnzeit3());
        this.tfSkonto1.setText(zk.getSkonto1());
        this.tfSkonto2.setText(zk.getSkonto2());
        this.tfSperrzeitWUNSCH.setText(zk.getSperrzeitWUNSCH());
        this.tfZahlungskonditionsID.setText(zk.getZahlungskonditionenID());
        this.tfAuftragsart.setText(zk.getAuftragsart());
           
    }
    
}
