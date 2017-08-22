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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private TextField tfMaterialNr;
    
    /**
     * Textfeld "Artikelbeschreibung".
     */
    @FXML
    private TextArea tfArtikelbeschreibung;
    
    /**
     * Textfeld "Bestellbeschreibung".
     */
    @FXML
    private TextArea tfBestellbeschreibung;
    
    /**
     * Textfeld "Bestand Frei".
     */
    @FXML
    private TextField tfBestandFrei;
    
    /**
     * Textfeld "Bestand Reserviert".
     */
    @FXML
    private TextField tfBestandReserviert;
    
    /**
     * Textfeld "Bestand Zulauf".
     */
    @FXML
    private TextField tfBestandZulauf;
    
    /**
     * Textfeld "Bestand Verkauf".
     */
    @FXML
    private TextField tfBestandVerkauft;
    
    /**
     * Textfeld "Suchbegriff".
     */
    @FXML
    private TextField tfSuchbegriff;
    
    /**
     * Textfeld "Einzelwert".
     */
    @FXML
    private TextField tfEinzelwert;
    
    /**
     * Textfeld "Bestellwert".
     */
    @FXML
    private TextField tfBestellwert;
    
    /**
     * Artikeltabelle.
     */
    @FXML
    private TableView tvArtikel = new TableView<>();

     /**
     * ComboBox "Suchfeld".
     */
    @FXML
    private ComboBox<String> cbSuchfeld = new ComboBox();
    
     /**
     * ComboBox "MwSt. Satz".
     */
    @FXML
    private ComboBox<String> cbMwstsatz = new ComboBox();
    
     /**
     * Tabellenspalte "MaterialNr".
     */
    @FXML
    private TableColumn<Artikel, String> tcMaterialNr;
    
     /**
     * Tabellenspalte "Artikelbeschreibung".
     */
    @FXML
    private TableColumn<Artikel, String> tcArtikelbeschreibung;
    
     /**
     * Tabellenspalte "Einzelwert".
     */
    @FXML
    private TableColumn<Artikel, String> tcEinzelwert;
    
     /**
     * Tabellenspalte "Bestellbeschreibung".
     */
    @FXML
    private TableColumn<Artikel, String> tcBestellbeschreibung;
    
     /**
     * Tabellenspalte "Bestellwert".
     */
    @FXML
    private TableColumn<Artikel, String> tcBestellwert;
    
     /**
     * Tabellenspalte "MwSt. Satz".
     */
    @FXML
    private TableColumn<Artikel, String> tcMwstsatz;
    
     /**
     * Tabellenspalte "Bestand Frei".
     */
    @FXML
    private TableColumn<Artikel, String> tcBestandFrei;
    
     /**
     * Tabellenspalte "Bestand Reserviert".
     */
    @FXML
    private TableColumn<Artikel, String> tcBestandReserviert;
    
     /**
     * Tabellenspalte "Bestand Zulauf".
     */
    @FXML
    private TableColumn<Artikel, String> tcBestandZulauf;
    
     /**
     * Tabellenspalte "Bestand Verkauft".
     */
    @FXML
    private TableColumn<Artikel, String> tcBestandVerkauft;
    
    /**
     * Tabellenspalte "Bestand Verkauft".
     */
    @FXML
    private Pane pane;
    
    /**
     * Button "Anlegen".
     */
    @FXML
    private Button btAnlegen;
    
    /**
     * Button "Speichern".
     */
    @FXML
    private Button btSpeichern;

    /**
     * Button "Hinzufügen".
     */
    @FXML
    private Button btHinzufuegen;

    /**
     * Button "Bearbeitent".
     */
    @FXML
    private Button btBearbeiten;
         
    /**
     * Button "Löschen".
     */
    @FXML
    private Button btLoeschen;
       
    /**
     * Tabellenspalte "Bestand Verkauft".
     */
    @FXML
    private TitledPane artikeldatensatzPane;
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText("Keine Adressen gefunden!");
            alert.showAndWait();
        }
       
        //  MaterialNr auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfMaterialNr, 6);
        
        //  Artikelbeschreibung auf 250 Zeichen begrenzt
        begrenzeTextAreaEingabe(tfArtikelbeschreibung, 250);

        // Bestellbeschreibung auf 250 Zeichen begrenzt
        begrenzeTextAreaEingabe(tfBestellbeschreibung, 250);

        // Einzelwert auf 6 Zeichen begrenzt      
        begrenzeTextFeldEingabe(tfEinzelwert, 6);

        // Bestellwert auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfBestellwert, 6);
        
        // Bestand Frei auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfBestandFrei, 6);
        
        // Bestand Reserviert auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfBestandReserviert, 6);
        
        // Bestand Zulauf auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfBestandZulauf, 6);
        
        // Bestand Verkauft auf 12 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfBestandVerkauft, 12);
       
        
        tcMaterialNr.setCellValueFactory(
                new PropertyValueFactory<>("artikelID"));
        tcEinzelwert.setCellValueFactory(
                new PropertyValueFactory<>("einzelwert"));
        tcArtikelbeschreibung.setCellValueFactory(
                new PropertyValueFactory<>("artikeltext"));
        tcBestellbeschreibung.setCellValueFactory(
                new PropertyValueFactory<>("bestelltext"));
        tcBestellwert.setCellValueFactory(
                new PropertyValueFactory<>("bestellwert"));
        tcMwstsatz.setCellValueFactory(
                new PropertyValueFactory<>("steuer"));
        tcBestandFrei.setCellValueFactory(
                new PropertyValueFactory<>("bestandsmengeFrei"));
        tcBestandReserviert.setCellValueFactory(
                new PropertyValueFactory<>("bestandsmengeReserviert"));
        tcBestandZulauf.setCellValueFactory(
                new PropertyValueFactory<>("bestandsmengeZulauf"));
        tcBestandVerkauft.setCellValueFactory(
                new PropertyValueFactory<>("bestandsmengeVerkauft"));     
        
        cbMwstsatz.getItems().addAll("0", "7", "19");
        
        cbSuchfeld.getItems().addAll(
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
                = FXCollections.observableArrayList(ar.gibAlleArtikelOhneLKZ());
        tvArtikel.setItems(artikel);
    } 
    
    
        /**
     * Methode bekommt eine ArrayList mit den gefundenen Adressen übergeben und
     * aktualisiert damit die TableView.
     * @param artikel Übergebene Adresse.
     * @throws java.sql.SQLException SQL Exception
    */
    public void zeigeGefundeneArtikel(ArrayList artikel) throws SQLException {
        refreshTable();
        ObservableList<Artikel> artikelAusgabe
            = FXCollections.observableArrayList(artikel);
        tvArtikel.setItems(artikelAusgabe);
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
        tvArtikel.getItems().clear();
        setTableContent();
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
        
        tfMaterialNr.clear();
        tfEinzelwert.clear();
        tfArtikelbeschreibung.clear();
        tfBestellwert.clear();
        tfBestellbeschreibung.clear();
        tfBestandFrei.clear();
        tfBestandReserviert.clear();
        tfBestandZulauf.clear();
        tfBestandVerkauft.clear();
        cbMwstsatz.valueProperty().set(null);
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
        tvArtikel.setItems(artikel);
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
        tvArtikel.setItems(artikel);
    }
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt die unteren Eingabefelder für das Anlegen einer neuer Adresse frei.
     * @throws java.sql.SQLException SQLException
    */    
    @FXML
    public void artikelAnlegen() throws SQLException {
        tvArtikel.setMouseTransparent(true);
        clearTextFields();   

        // Textfeldbereich wird aktiviert
        this.pane.setDisable(true);
        // Bearbeiten-Button wird ausgeblendet
        this.btAnlegen.setVisible(false);
        // Speichern-Button wird eingeblendet
        this.btHinzufuegen.setVisible(true);       
        // Der Anlegemodus des Adressdatensatzes wird aktiviert
        this.artikeldatensatzPane.setText(
                "Adressdatensatz (Anlegemodus)");       
        // Anlegen-Button wird deaktiviert
        this.btBearbeiten.setDisable(true);     
        // Löschen-Button wird deaktiviert
        this.btLoeschen.setDisable(true);
           
        ArtikelDAO ar = new ArtikelDAO();
        tfMaterialNr.setText(ar.generiereID());   
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
        String artikelID = tfMaterialNr.getText();
        String einzelwert = tfEinzelwert.getText();
        String artikeltext = tfArtikelbeschreibung.getText();
        String bestellwert = tfBestellwert.getText();
        String bestelltext = tfBestellbeschreibung.getText();
        String steuer = cbMwstsatz.getValue();
        String bestandsmengeFrei = tfBestandFrei.getText();
        String bestandsmengeReserviert = tfBestandReserviert.getText();
        String bestandsmengeZulauf = tfBestandZulauf.getText();
        String bestandsmengeVerkauft = tfBestandVerkauft.getText();
        String lkz = "N";
        Artikel artikel = new Artikel(artikelID, artikeltext, bestelltext,
                einzelwert, bestellwert, steuer, bestandsmengeFrei,
                bestandsmengeReserviert, bestandsmengeZulauf, 
                bestandsmengeVerkauft, lkz);
        
        ArtikelDAO ar = new ArtikelDAO();
        ar.fuegeArtikelHinzu(artikel);
        
        clearTextFields();
        refreshTable();
        
                // Textfeldbereich wird aktiviert
        this.pane.setDisable(false);
        // Bearbeiten-Button wird ausgeblendet
        this.btAnlegen.setVisible(true);
        // Speichern-Button wird eingeblendet
        this.btHinzufuegen.setVisible(false);   
        // Der Bearbeitungsmodus des Adressdatensatzes wird aktiviert
        this.artikeldatensatzPane.setText("Artikeldatensatz");    
        // Anlegen-Button wird deaktiviert
        this.btBearbeiten.setDisable(false);     
        // Löschen-Button wird deaktiviert
        this.btLoeschen.setDisable(false);
        tvArtikel.setMouseTransparent(false);
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

        Object artikel = tvArtikel.getSelectionModel().getSelectedItem();
        Artikel b = (Artikel) artikel;

        ArtikelDAO ar = new ArtikelDAO();
        ar.setzeLKZ(b);
        
        refreshTable();
    }

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Lässt das Bearbeiten einer ausgewählten Adresse zu.
    */      
    @FXML
    public void bearbeiteArtikel() {
        // Textfeldbereich wird aktiviert
        this.pane.setDisable(true);
        // Bearbeiten-Button wird ausgeblendet
        this.btBearbeiten.setVisible(false);
        // Speichern-Button wird eingeblendet
        this.btSpeichern.setVisible(true);
        // Der Bearbeitungsmodus des Adressdatensatzes wird aktiviert
        this.artikeldatensatzPane.setText(
                "Artikeldatensatz (Bearbeitungsmodus)");
        // Anlegen-Button wird deaktiviert
        this.btAnlegen.setDisable(true);
        // Löschen-Button wird deaktiviert
        this.btLoeschen.setDisable(true);
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Speichert die gemachten Änderungen in die Datenbank und aktualisiert
     * die View mit den neuen Werten.
     * @throws java.sql.SQLException SQLException.
    */      
    @FXML
    public void speichereAenderung() throws SQLException {  
        String artikelID = tfMaterialNr.getText();
        String einzelwert = tfEinzelwert.getText();
        String artikeltext = tfArtikelbeschreibung.getText();
        String bestellwert = tfBestellwert.getText();
        String bestelltext = tfBestellbeschreibung.getText();
        String steuer = cbMwstsatz.getValue();
        String bestandsmengeFrei = tfBestandFrei.getText();
        String bestandsmengeReserviert = tfBestandReserviert.getText();
        String bestandsmengeZulauf = tfBestandZulauf.getText();
        String bestandsmengeVerkauft = tfBestandVerkauft.getText();
        String lkz = "N";
        Artikel artikel = new Artikel(artikelID, artikeltext, bestelltext,
                einzelwert, bestellwert, steuer, bestandsmengeFrei,
                bestandsmengeReserviert, bestandsmengeZulauf, 
                bestandsmengeVerkauft, lkz);
       
        ArtikelDAO aDAO = new ArtikelDAO();
        aDAO.aendereArtikel(artikel);
        
        refreshTable();
        
        // Textfeldbereich wird deaktivieren
        this.pane.setDisable(false);
        // Bearbeiten-Button wird ausgeblendet
        this.btBearbeiten.setVisible(true);
        // Speichern-Button wird eingeblendet
        this.btSpeichern.setVisible(false);       
        // Der Bearbeitungsmodus des Adressdatensatzes wird aktiviert
        this.artikeldatensatzPane.setText("Artikeldatensatz");       
        // Anlegen-Button wird deaktiviert
        this.btAnlegen.setDisable(false);       
        // Löschen-Button wird deaktiviert
        this.btLoeschen.setDisable(false);
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    GET     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt die Werte einer ausgewählten Adresse im unteren Bereich an.
    */      
    @FXML
    public void zeigeWerteAn() {
        Object artikel = tvArtikel.getSelectionModel().getSelectedItem();
        Artikel b = (Artikel) artikel;
        
        if (b != null) {
            this.tfMaterialNr.setText(b.getArtikelID());
            this.cbMwstsatz.setValue(b.getSteuer());
            this.tfArtikelbeschreibung.setText(b.getArtikeltext());
            this.tfEinzelwert.setText(b.getEinzelwert());
            this.tfBestellbeschreibung.setText(b.getBestelltext());
            this.tfBestellwert.setText(b.getBestellwert());
            this.tfBestandFrei.setText(b.getBestandsmengeFrei());
            this.tfBestandReserviert.setText(b.getBestandsmengeReserviert());
            this.tfBestandZulauf.setText(b.getBestandsmengeZulauf());
            this.tfBestandVerkauft.setText(b.getBestandsmengeVerkauft());
        }
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
    public void artikelSuchen() throws SQLException {
        SucheDAO ar = new SucheDAO();
        ArrayList gefundenerArtikel;
        
        String suchkriterium = cbSuchfeld.getValue();
        String suchbegriff = tfSuchbegriff.getText();
        
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
        this.tfSuchbegriff.setText("");
        this.cbSuchfeld.setValue("Bitte wählen...");
        setTableContent();
    } 
   
}
