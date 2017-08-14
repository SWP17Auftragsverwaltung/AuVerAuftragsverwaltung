/*------------------------------------------------------------------------------
* Klasse: ArtikelverwaltungController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 14.06.2017    SAM     Angelegt.
* 26.06.2017    GET     Checkstyleprüfung.
*                       Fehler bei Start der GUI behoben.
* 27.07.2017    BER     Javadoc angepasst.
* 14.08.2017    BER     Angepasst an neue DB.
* 14.08.2017    HEN     initialize() ergänzt, FXML TableColumns erstellt.
*-------------------------------------------------------------------------------
 */
package auverauftragsverwaltung;


import Datenbank.ArtikelDAO;
import Klassen.Artikel;
import java.net.URL;
import java.sql.SQLException;
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
    private TableView tv_artikel = new TableView<Artikel>();

        
    /**
     * Abbrechen-Button der Artikelverwaltung.
     */
    @FXML
    private Button closeArW;
    @FXML
    private ComboBox<?> cb_feldwahl;
    @FXML
    private TableColumn<Artikel, String> tc_materialNr;
    @FXML
    private TableColumn<Artikel, String> tc_artikelbeschreibung;
    @FXML
    private TableColumn<Artikel, String> tc_einzelwert;
    @FXML
    private TableColumn<Artikel, String> tc_bestellbeschreibung;
    @FXML
    private TableColumn<Artikel, String> tc_BestandFrei;
    @FXML
    private TableColumn<Artikel, String> tc_BestandReserviert;
    @FXML
    private TableColumn<Artikel, String> tc_BestandZulauf;
    @FXML
    private TableColumn<Artikel, String> tc_BestandVerkauft;
    @FXML
    private TextField materialNr;
    @FXML
    private TextField bestandFREI;
    @FXML
    private TextField bestandRESERVIERT;
    @FXML
    private TextField bestandZULAUF;
    @FXML
    private TextField bestandVERKAUFT;

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
       
        
        tc_materialNr.setCellValueFactory(
                new PropertyValueFactory<>("artikelID"));
        tc_artikelbeschreibung.setCellValueFactory(
                new PropertyValueFactory<>("artikeltext"));
        tc_bestellbeschreibung.setCellValueFactory(
                new PropertyValueFactory<>("bestelltext"));
        tc_einzelwert.setCellValueFactory(
                new PropertyValueFactory<>("einzelwert"));
        tc_BestandFrei.setCellValueFactory(
                new PropertyValueFactory<>("bestandsmengeFrei"));
        tc_BestandReserviert.setCellValueFactory(
                new PropertyValueFactory<>("bestandsmengeReserviert"));
        tc_BestandZulauf.setCellValueFactory(
                new PropertyValueFactory<>("bestandsmengeZulauf"));
        tc_BestandVerkauft.setCellValueFactory(
                new PropertyValueFactory<>("bestandsmengeVerkauft"));     
    }

    /**
     * 
     * @param tf Textfeld
     * @param zahl Länge des Feldes
     */
    private void begrenzeTextFeldEingabe(TextField tf, int zahl) {
        tf.setTextFormatter(new TextFormatter<>(change
            -> change.getControlNewText().length() <= zahl ? change : null));
    }

    /**
     * 
     * @param ta Textarea.
     * @param zahl Länge der Textarea
     */
    private void begrenzeTextAreaEingabe(TextArea ta, int zahl) {
        // Zeilenumbruch im TextArea Feld
        ta.setWrapText(true);
        ta.setTextFormatter(new TextFormatter<>(change
            -> change.getControlNewText().length() <= zahl ? change : null));
    }

 
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.08.17    HEN     ObservableArrayList hinzugefügt
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein ArtikelDAO Objekt und gibt eine Artikel ArrayList an eine
     * OberservableList, die dann an die TableView übergeben wird.
     * @throws java.sql.SQLException SQL Exception
     */
    public void setTableContent() throws SQLException {    
        ArtikelDAO ar = new ArtikelDAO();     
        ObservableList<Artikel> artikel 
                = FXCollections.observableArrayList(ar.gibAlleArtikel());
        tv_artikel.setItems(artikel);
    } 
}
