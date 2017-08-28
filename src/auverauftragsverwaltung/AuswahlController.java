
package auverauftragsverwaltung;

import Datenbank.GeschaeftspartnerDAO;
import Klassen.Geschaeftspartner;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class.
 * @author Jakob
 */
public class AuswahlController implements Initializable {

    
    /**
     * 
     */
    private String gpID;
    
    /**
     * 
     */
    @FXML
    private Label lableAuswahl;
    
    /**
     * 
     */
    @FXML
    protected TableView auswahlTV = new TableView<Geschaeftspartner>();
    
    /**
     * 
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> ID;
    
    /**
     * 
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> spalte2;
    
    /**
     * 
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> spalte3;
    
    /**
     * 
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> spalte4;
    
    /**
     * 
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> spalte5;
    
    /**
     * 
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> spalte6;
    
    /**
     * 
     */
    @FXML
    protected Button btHinzufügen;
    
    /**
     * 
     */
    @FXML
    private Button btZurueck;
    

    /**
     * Initializes the controller class.
     * @param url URL
     * @param rb RB
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setTableContent();
        } catch (SQLException ex) {
            System.out.println("VOLL KACKE!");
        }
        
        ID.setCellValueFactory(
                new PropertyValueFactory<>("geschaeftspartnerID"));
        spalte2.setCellValueFactory(new PropertyValueFactory<>("typ"));
        spalte3.setCellValueFactory(new PropertyValueFactory<>("adresseID"));
        spalte4.setCellValueFactory(new PropertyValueFactory<>("lieferID"));
        spalte5.setCellValueFactory(
                new PropertyValueFactory<>("kreditlimit"));        
      
    }


    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein AdressDAO Objekt und gibt eine Adress ArrayList an eine
     * OberservableList, die dann an die TableView übergeben wird.
     *
     * @throws java.sql.SQLException SQL Exception
    */
    public void setTableContent() throws SQLException {
        GeschaeftspartnerDAO gpd = new GeschaeftspartnerDAO();

        ObservableList<Geschaeftspartner> geschaeftspartner
            = FXCollections.observableArrayList(
                    gpd.gibAlleGeschaeftspartnerOhneLKZ());
        
        auswahlTV.setItems(geschaeftspartner);
    }


    
    @FXML
    protected void gibGeschaeftspartnerID(ActionEvent event) {
        Object geschaeftspartner 
            = auswahlTV.getSelectionModel().getSelectedItem();
        Geschaeftspartner g = (Geschaeftspartner) geschaeftspartner;

        if (g != null) {
            this.gpID = g.getGeschaeftspartnerID();        
        }
        

        Stage stage = (Stage) btHinzufügen.getScene().getWindow();
        stage.close();
    }

  
    
    
    
}
