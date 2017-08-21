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
import Datenbank.SucheDAO;
import Klassen.Artikel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    /**
     * Abbrechen-Button der Artikelverwaltung.
     */
    @FXML
    private Button closeArW;
     /**
     * Textfeld "MaterialNr".
     */
    @FXML
    private TextField tf_materialNr;
    
    /**
     * Textfeld "Artikelbeschreibung".
     */
    @FXML
    private TextArea tf_artikelbeschreibung;
    
    /**
     * Textfeld "Bestellbeschreibung".
     */
    @FXML
    private TextArea tf_bestellbeschreibung;
    
    /**
     * Textfeld "Bestand Frei".
     */
    @FXML
    private TextField tf_bestandFrei;
    
    /**
     * Textfeld "Bestand Reserviert".
     */
    @FXML
    private TextField tf_bestandReserviert;
    
    /**
     * Textfeld "Bestand Zulauf".
     */
    @FXML
    private TextField tf_bestandZulauf;
    
    /**
     * Textfeld "Bestand Verkauf".
     */
    @FXML
    private TextField tf_bestandVerkauft;
    
    /**
     * Textfeld "Suchbegriff".
     */
    @FXML
    private TextField tf_suchbegriff;
    
    /**
     * Textfeld "Einzelwert".
     */
    @FXML
    private TextField tf_einzelwert;
    
    /**
     * Textfeld "Bestellwert".
     */
    @FXML
    private TextField tf_bestellwert;
    
    /**
     * Artikeltabelle.
     */
    @FXML
    private TableView tv_artikel = new TableView<>();

     /**
     * ComboBox "Suchfeld".
     */
    @FXML
    private ComboBox<String> cb_suchfeld = new ComboBox();
    
     /**
     * ComboBox "MwSt. Satz".
     */
    @FXML
    private ComboBox<String> cb_mwstsatz = new ComboBox();
    
     /**
     * Tabellenspalte "MaterialNr".
     */
    @FXML
    private TableColumn<Artikel, String> tc_materialNr;
    
     /**
     * Tabellenspalte "Artikelbeschreibung".
     */
    @FXML
    private TableColumn<Artikel, String> tc_artikelbeschreibung;
    
     /**
     * Tabellenspalte "Einzelwert".
     */
    @FXML
    private TableColumn<Artikel, String> tc_einzelwert;
    
     /**
     * Tabellenspalte "Bestellbeschreibung".
     */
    @FXML
    private TableColumn<Artikel, String> tc_bestellbeschreibung;
    
     /**
     * Tabellenspalte "Bestellwert".
     */
    @FXML
    private TableColumn<Artikel, String> tc_bestellwert;
    
     /**
     * Tabellenspalte "MwSt. Satz".
     */
    @FXML
    private TableColumn<Artikel, String> tc_mwstsatz;
    
     /**
     * Tabellenspalte "Bestand Frei".
     */
    @FXML
    private TableColumn<Artikel, String> tc_BestandFrei;
    
     /**
     * Tabellenspalte "Bestand Reserviert".
     */
    @FXML
    private TableColumn<Artikel, String> tc_BestandReserviert;
    
     /**
     * Tabellenspalte "Bestand Zulauf".
     */
    @FXML
    private TableColumn<Artikel, String> tc_BestandZulauf;
    
     /**
     * Tabellenspalte "Bestand Verkauft".
     */
    @FXML
    private TableColumn<Artikel, String> tc_BestandVerkauft;


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
        
        try {
            setTableContent();
        } catch (SQLException ex) {
            Logger.getLogger(AdressverwaltungController.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
       
        //  MaterialNr auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_materialNr, 6);
        
        //  Artikelbeschreibung auf 250 Zeichen begrenzt
        begrenzeTextAreaEingabe(tf_artikelbeschreibung, 250);

        // Bestellbeschreibung auf 250 Zeichen begrenzt
        begrenzeTextAreaEingabe(tf_bestellbeschreibung, 250);

        // Einzelwert auf 6 Zeichen begrenzt      
        begrenzeTextFeldEingabe(tf_einzelwert, 6);

        // Bestellwert auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_bestellwert, 6);
        
        // Bestand Frei auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_bestandFrei, 6);
        
        // Bestand Reserviert auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_bestandReserviert, 6);
        
        // Bestand Zulauf auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_bestandZulauf, 6);
        
        // Bestand Verkauft auf 12 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_bestandVerkauft, 12);
       
        
        tc_materialNr.setCellValueFactory(
                new PropertyValueFactory<>("artikelID"));
        tc_einzelwert.setCellValueFactory(
                new PropertyValueFactory<>("einzelwert"));
        tc_artikelbeschreibung.setCellValueFactory(
                new PropertyValueFactory<>("artikeltext"));
        tc_bestellbeschreibung.setCellValueFactory(
                new PropertyValueFactory<>("bestelltext"));
        tc_bestellwert.setCellValueFactory(
                new PropertyValueFactory<>("bestellwert"));
        tc_mwstsatz.setCellValueFactory(
                new PropertyValueFactory<>("steuer"));
        tc_BestandFrei.setCellValueFactory(
                new PropertyValueFactory<>("bestandsmengeFrei"));
        tc_BestandReserviert.setCellValueFactory(
                new PropertyValueFactory<>("bestandsmengeReserviert"));
        tc_BestandZulauf.setCellValueFactory(
                new PropertyValueFactory<>("bestandsmengeZulauf"));
        tc_BestandVerkauft.setCellValueFactory(
                new PropertyValueFactory<>("bestandsmengeVerkauft"));     
        
        cb_mwstsatz.getItems().addAll("0", "7", "19");
        
        cb_suchfeld.getItems().addAll(
                "MaterialNr",
                "Artikelbeschreibung",
                "Einzelwert",
                "Bestellbeschreibung",
                "Bestellwert",
                "MwSt. Satz",
                "Bestand Frei",
                "Bestand Reserviert",
                "Bestand Zulauf",
                "Bestand Verkauft");

 
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
    
    
    
        /**
     * Methode bekommt eine ArrayList mit den gefundenen Adressen übergeben und
     * aktualisiert damit die TableView.
     * @param adressen Übergebene Adresse.
     * @throws java.sql.SQLException SQL Exception
    */
    public void zeigeGefundeneArtikel(ArrayList artikel) throws SQLException {
        refreshTable();
        ObservableList<Artikel> adressenAusgabe
            = FXCollections.observableArrayList(artikel);
        tv_artikel.setItems(adressenAusgabe);
    } 
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Aktualisiert die TableView mit aktuellem Inhalt.
     * @throws java.sql.SQLException SQL Exception
    */
    @FXML
    public void refreshTable() throws SQLException {
        tv_artikel.getItems().clear();
        setTableContent();
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
    @FXML
    public void setTableContent() throws SQLException {    
        ArtikelDAO ar = new ArtikelDAO();     
        ObservableList<Artikel> artikel 
                = FXCollections.observableArrayList(ar.gibAlleArtikel());
        tv_artikel.setItems(artikel);
    } 
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Löscht alle Eingaben in den Textfeldern.
     * @throws java.sql.SQLException SQL Exception
    */
    @FXML
    public void clearTextFields() throws SQLException {
        
        tf_materialNr.clear();
        tf_einzelwert.clear();
        tf_artikelbeschreibung.clear();
        tf_bestellwert.clear();
        tf_bestellbeschreibung.clear();
        tf_bestandFrei.clear();
        tf_bestandReserviert.clear();
        tf_bestandZulauf.clear();
        tf_bestandVerkauft.clear();
        cb_mwstsatz.valueProperty().set(null);
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
     /**
     * Sucht nach allen Artikeln mit aktivem LKZ und stellt sie in der Tabelle
     * dar.
     * @throws java.sql.SQLException SQL Exception
     */ 
    public void alleMitLKZ() throws SQLException {    
        ArtikelDAO ar = new ArtikelDAO();     
        ObservableList<Artikel> artikel 
                = FXCollections.observableArrayList(
                        ar.gibAlleArtikelMitLKZ());
        tv_artikel.setItems(artikel);
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
     /**
     * Sucht nach allen Artikeln mit aktivem LKZ und stellt sie in der Tabelle
     * dar.
     * @throws java.sql.SQLException SQL Exception
     */ 
    public void alleOhneLKZ() throws SQLException {    
        ArtikelDAO ar = new ArtikelDAO();     
        ObservableList<Artikel> artikel 
                = FXCollections.observableArrayList(
                        ar.gibAlleArtikelOhneLKZ());
        tv_artikel.setItems(artikel);
    }
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues
     * Artikel Objekt, welches dann über die DAO in die DB geschrieben wird.
     * @throws java.sql.SQLException SQL Exception
     */
    public void artikelHinzufuegen() throws SQLException {
        String artikelID = tf_materialNr.getText();
        String einzelwert = tf_einzelwert.getText();
        String artikeltext = tf_artikelbeschreibung.getText();
        String bestellwert = tf_bestellwert.getText();
        String bestelltext = tf_bestellbeschreibung.getText();
        String steuer = cb_mwstsatz.getValue();
        String bestandsmengeFrei = tf_bestandFrei.getText();
        String bestandsmengeReserviert = tf_bestandReserviert.getText();
        String bestandsmengeZulauf = tf_bestandZulauf.getText();
        String bestandsmengeVerkauft = tf_bestandVerkauft.getText();
        String lkz = "N";
        Artikel artikel = new Artikel(artikelID, artikeltext, bestelltext,
                einzelwert, bestellwert, steuer, bestandsmengeFrei,
                bestandsmengeReserviert, bestandsmengeZulauf, 
                bestandsmengeVerkauft, lkz);
        
        ArtikelDAO ar = new ArtikelDAO();
        ar.fuegeArtikelHinzu(artikel);
        
        clearTextFields();
        refreshTable();
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * "Löscht" einen markierten Artikel, in dem das LKZ auf J gesetzt wird.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void artikelLoeschen() throws SQLException {

        Object artikel = tv_artikel.getSelectionModel().getSelectedItem();
        Artikel b = (Artikel) artikel;

        ArtikelDAO ar = new ArtikelDAO();
        ar.setzeLKZ(b);
    }
    
    @FXML
    public void artikelAendern() throws SQLException {
        
        Object artikel = tv_artikel.getSelectionModel().getSelectedItem();
        Artikel b = (Artikel) artikel;
        
        ArtikelDAO ar = new ArtikelDAO();
        ar.aendernArtikel(b);
    }
    
    
        /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    HEN     Methode erstellt.
    /* 18.08.17    BER     IF Fälle ergänzt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt die Werte einer ausgewählten Adresse im unteren Bereich an.
     * @throws java.sql.SQLException SQLException
    */        
    @FXML
    public void ArtikelSuchen() throws SQLException {
        SucheDAO ar = new SucheDAO();
        ArrayList gefundenerArtikel;
        
        String suchkriterium = cb_suchfeld.getValue();
        String suchbegriff = tf_suchbegriff.getText();
        
        gefundenerArtikel = ar.artikelSuche(suchkriterium, suchbegriff);
        
        zeigeGefundeneArtikel(gefundenerArtikel);
    } 
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Setzt die Suche zurück.
     * @throws java.sql.SQLException SQLException
    */        
    @FXML
    public void setzeSucheZurueck() throws SQLException {
        this.tf_suchbegriff.setText("");
        this.cb_suchfeld.setValue("Bitte wählen...");
        setTableContent();
    } 
   
}
