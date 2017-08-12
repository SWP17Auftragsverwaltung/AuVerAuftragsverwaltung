/*------------------------------------------------------------------------------
* Klasse: AdressverwaltungController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 2017-06-14 SAM Angelegt.
* 2017-06-26 GET Checkstyleprüfung.
* 2017-07-27 BER Kommentarlayout angepasst.
*-------------------------------------------------------------------------------
 */
package auverauftragsverwaltung;

import Datenbank.AdresseDAO;
import Klassen.Adresse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Mudimbi
 */
public class AdressverwaltungController implements Initializable {

    /**
     * Der Abbrechen-Button in der Adressverwaltung.
     */
    @FXML
    private Button closeAW;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_vorname;
    @FXML
    private TextField tf_telefon;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_strasse;
    @FXML
    private TextField tf_hausNr;
    @FXML
    private TextField tf_plz;
    @FXML
    private TextField tf_ort;
    @FXML
    private TextField tf_staat;
    @FXML
    private TextField tf_datum;
    @FXML
    private TableView adresseTV = new TableView<Adresse>();
    


    @FXML
    private ComboBox<?> cb_suchfeld;
    @FXML
    private TextField tf_suchbegriff;
    @FXML
    private TextField tf_anschriftID;
    @FXML
    private SplitMenuButton cb_anrede;
    @FXML
    private TableColumn<Adresse, String> AnschriftID;
    @FXML
    private TableColumn<Adresse, String> Anrede;
    @FXML
    private TableColumn<Adresse, String> Name;
    @FXML
    private TableColumn<Adresse, String> Vorname;
    @FXML
    private TableColumn<Adresse, String> Straße;
    @FXML
    private TableColumn<Adresse, String> HausNr;
    @FXML
    private TableColumn<Adresse, String> PLZ;
    @FXML
    private TableColumn<Adresse, String> Ort;
    @FXML
    private TableColumn<Adresse, String> Staat;
    @FXML
    private TableColumn<Adresse, String> Tel;
    @FXML
    private TableColumn<Adresse, String> EMail;
    @FXML
    private TableColumn<Adresse, String> ErfDatum;
 

    /**
     * Methode zum Abbrechen der Adressverwaltung.
     *
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen"
     * abfängt.
     */
    @FXML
    public void closeAdressverwaltung(ActionEvent event) {
        Stage stage = (Stage) closeAW.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialisiert die Controller-Klasse.
     *
     * @param url URL zur initialisierung.
     * @param rb Resourcen die geladen werden sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Name auf 20 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_name, 20);
        
        // Vorname auf 20 Zeichen begrenzt   
        begrenzeTextFeldEingabe(tf_vorname, 20);
          
        // Telefon auf 20 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_telefon, 20);
        
        // E-Mail auf 30 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_email, 50);
        
        // Strasse auf 30 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_strasse, 30);
               
        // Hausnummer auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_hausNr, 6);
        
        // PLZ auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_plz, 6);    
        
        // Ort auf 30 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_ort, 30);   
        
        // Staat auf 30 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_staat, 30);
        
        // Datum auf 10 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_datum, 10);
          
        AnschriftID.setCellValueFactory(new PropertyValueFactory<>("adresseID"));            
        Anrede.setCellValueFactory(new PropertyValueFactory<>("anrede"));     
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));   
        Vorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));     
        Straße.setCellValueFactory(new PropertyValueFactory<>("strasse"));      
        HausNr.setCellValueFactory(new PropertyValueFactory<>("hausnummer"));            
        PLZ.setCellValueFactory(new PropertyValueFactory<>("plz"));     
        Ort.setCellValueFactory(new PropertyValueFactory<>("ort"));       
        Staat.setCellValueFactory(new PropertyValueFactory<>("staat"));            
        Tel.setCellValueFactory(new PropertyValueFactory<>("telefon"));             
        EMail.setCellValueFactory(new PropertyValueFactory<>("eMail"));       
        ErfDatum.setCellValueFactory(new PropertyValueFactory<>("erfassungsdatum"));
        
    }
    
    /**
     * Begrenzte Feldeingabe.
     * @param tf Teftfekd
     * @param zahl Zahl
     */
    private void begrenzeTextFeldEingabe(TextField tf, int zahl) {
        
        tf.setTextFormatter(new TextFormatter<>(change
            -> {
            return change.getControlNewText().length() <= zahl ? change : null;
        }));
        
    }
    
    /**
     * Erstellt ein AdressDAO Objekt und gibt eine Adress ArrayList an eine
     * OberservableList, die dann an die TableView übergeben wird.
     */
    @FXML
    public void setTableContent() {    
        AdresseDAO ad = new AdresseDAO();     
        ObservableList<Adresse> adressen 
                = FXCollections.observableArrayList(ad.gibAlleAdressen());
        adresseTV.setItems(adressen);
    } 
}
