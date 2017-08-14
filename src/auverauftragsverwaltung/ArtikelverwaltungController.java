/*------------------------------------------------------------------------------
* Klasse: ArtikelverwaltungController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 2017-06-14 SAM Angelegt.
* 2017-06-26 GET Checkstyleprüfung.
*                Fehler bei Start der GUI behoben.
* 2017-07-27 BER Javadoc angepasst.
* 2017-08-14 BER Angepasst an neue DB.
*-------------------------------------------------------------------------------
 */
package auverauftragsverwaltung;


import Datenbank.ArtikelDAO;
import Klassen.Artikel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Mudimbi
 */
public class ArtikelverwaltungController implements Initializable {

    @FXML
    private TextArea artikelbeschreibung;
    @FXML
    private TextField einzelwert;
    @FXML
    private TextArea bestellbeschreibung;
    @FXML
    private TextField bestellwert;
    @FXML
    private TableView artikelTV = new TableView<Artikel>();

        
        
    @FXML
    private TableColumn<Artikel, String> ArtikelID;
    @FXML
    private TableColumn<Artikel, String> Artikeltext;
    @FXML
    private TableColumn<Artikel, String> Bestelltext;
    @FXML
    private TableColumn<Artikel, String> AnschriftID;
    @FXML
    private TableColumn<Artikel, String> Einzelwert;
    @FXML
    private TableColumn<Artikel, String> Bestellwert;
    @FXML
    private TableColumn<Artikel, String> Steuer;
    @FXML
    private TableColumn<Artikel, String> BestandsmengeFrei;
    @FXML
    private TableColumn<Artikel, String> BestandsmengeReserviert;
    @FXML
    private TableColumn<Artikel, String> BestandsmengeZulauf;
    @FXML
    private TableColumn<Artikel, String> BestandsmengeVerkauft;
    /**
     * Abbrechen-Button der Artikelverwaltung.
     */
    @FXML
    private Button closeArW;

    /**
     * Methode zum Abbrechen der Artikelverwaltung.
     *
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen"
     * abfängt.
     */
    @FXML
    public void closeArtikelverwaltung(ActionEvent event) {
        Stage stage = (Stage) closeArW.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialisiert die Controller-Klasse der Artikelverwaltung.
     *
     * @param url URL zur initialisierung.
     * @param rb Resourcen die geladen werden sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //  Artikelbeschreibung auf 250 Zeichen begrenzt
        begrenzeTextAreaEingabe(artikelbeschreibung, 250);

        // Bestellbeschreibung auf 250 Zeichen begrenzt
        begrenzeTextAreaEingabe(bestellbeschreibung, 250);

        // Einzelwert auf 6 Zeichen begrenzt      
        begrenzeTextFeldEingabe(einzelwert, 6);

        // Bestellwert auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(bestellwert, 6);
       
        
//        ArtikelID.setCellValueFactory(new PropertyValueFactory<>("artikelID"));
//        Artikeltext.setCellValueFactory(new PropertyValueFactory<>("artikeltext"));
//        Bestelltext.setCellValueFactory(new PropertyValueFactory<>("bestelltext"));
//        Einzelwert.setCellValueFactory(new PropertyValueFactory<>("einzelwert"));
//        Bestellwert.setCellValueFactory(new PropertyValueFactory<>("bestellwert"));
//        Steuer.setCellValueFactory(new PropertyValueFactory<>("steuer"));
//        BestandsmengeFrei.setCellValueFactory(new PropertyValueFactory<>("bestandsmengeFrei"));
//        BestandsmengeReserviert.setCellValueFactory(new PropertyValueFactory<>("bestandsmengeReserviert"));
//        BestandsmengeZulauf.setCellValueFactory(new PropertyValueFactory<>("bestandsmengeZulauf"));
//        BestandsmengeVerkauft.setCellValueFactory(new PropertyValueFactory<>("bestandsmengeVerkauft"));
        
        
    }

    private void begrenzeTextFeldEingabe(TextField tf, int zahl) {

        tf.setTextFormatter(new TextFormatter<>(change
                -> change.getControlNewText().length() <= zahl ? change : null));
    }

    private void begrenzeTextAreaEingabe(TextArea ta, int zahl) {
        // Zeilenumbruch im TextArea Feld
        ta.setWrapText(true);
        ta.setTextFormatter(new TextFormatter<>(change
                -> change.getControlNewText().length() <= zahl ? change : null));
    }

    
        /**
     * Erstellt ein ArtikelDAO Objekt und gibt eine Artikel ArrayList an eine
     * OberservableList, die dann an die TableView übergeben wird.
     */
    @FXML
    public void setTableContent() {    
        ArtikelDAO ar = new ArtikelDAO();     
        ObservableList<Artikel> artikel 
                = FXCollections.observableArrayList(ar.gibAlleArtikel());
        artikelTV.setItems(artikel);
    } 
}
