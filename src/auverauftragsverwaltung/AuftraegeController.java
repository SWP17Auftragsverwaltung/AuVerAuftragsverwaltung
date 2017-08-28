/*------------------------------------------------------------------------------
* Klasse: AufträgeAnzeigenController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 2017-06-15 SAM Angelegt.
* 2017-06-26 GET Checkstyleprüfung.
* 2017-07-27 BER Kommentarlayout angepasst.
*-------------------------------------------------------------------------------
*/
package auverauftragsverwaltung;

import Datenbank.ArtikelDAO;
import Datenbank.AuftragskopfDAO;
import Datenbank.AuftragspositionDAO;
import Datenbank.GeschaeftspartnerDAO;
import Klassen.Artikel;
import Klassen.Auftragskopf;
import Klassen.Auftragsposition;
import Klassen.Geschaeftspartner;
import Klassen.Meldung;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Mudimbi
 */
public class AuftraegeController implements Initializable {

    @FXML
    private AnchorPane paneAuftraege;
    @FXML
    private TitledPane auftragskopfTP;
    @FXML
    private TextField tfAuftragskopf;
    @FXML
    private TextArea tfText;
    @FXML
    private TextField tfPartnerID;
    @FXML
    private ComboBox<String> cbAuftragsstatus = new ComboBox<>();
    @FXML
    private ComboBox<String> cbAuftragsart = new ComboBox<>();
    @FXML
    private TextField tfAuftragswert;
    @FXML
    private Button btAuftragspositionen;
    @FXML
    private Button btAnlegen;
    @FXML
    private Button btAendern;
    @FXML
    private Button btLoeschen;
    @FXML
    private Button closeAA;
    @FXML
    private TextField tfErfDatum;
    @FXML
    private TextField tfLieferdatum;
    @FXML
    private TextField tfAbschlussdatum;
    @FXML
    private Button btAbbrechen;
    @FXML
    private Button btHinzufuegen;
    @FXML
    private Button btSpeichern;
    @FXML
    private Pane pane;
    
    
    @FXML
    private TableView tvAuftragskopf = new TableView<Auftragskopf>();
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragsID;
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragsText;
    @FXML
    private TableColumn<Auftragskopf, String> tcgeschaeftspartnerID;
    @FXML
    private TableColumn<Auftragskopf, String> tcErfDatum;
    @FXML
    private TableColumn<Auftragskopf, String> tcLieferDatum;
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragsart;
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragswert;
    @FXML
    private TableColumn<Auftragskopf, String> tcStatus;
    @FXML
    private TableColumn<Auftragskopf, String> tcAbschDatum;
    
    
    @FXML
    private TitledPane paneGP;
    @FXML
    private TableView tvGPAuswahl = new TableView<Geschaeftspartner>();
    @FXML
    private TableColumn<Geschaeftspartner, String> tcGpIDGPWahl;
    @FXML
    private TableColumn<Geschaeftspartner, String> tcTypGPWahl;
    @FXML
    private TableColumn<Geschaeftspartner, String> tcAnschriftIDGPWahl;
    @FXML
    private TableColumn<Geschaeftspartner, String> tcLieferIDGPWahl;
    @FXML
    private TableColumn<Geschaeftspartner, String> tcKreditlimitGPWahl;
    
    
    @FXML
    private AnchorPane paneAuftragspositionen;
    @FXML
    private TitledPane paneArtikelauswahl;
    @FXML
    private TableView tvArtikelauswahl = new TableView<Artikel>();
    @FXML
    private TableColumn<Artikel, String> tcArtikelIDArtWahl;
    @FXML
    private TableColumn<Artikel, String> tcArtikeltextArtWahl;
    @FXML
    private TableColumn<Artikel, String> tcBestelltextArtWahl;
    @FXML
    private TableColumn<Artikel, String> tcEinzelwertArtArtWahl;
    @FXML
    private TableColumn<Artikel, String> tcBestellwertArtWahl;
    @FXML
    private TableColumn<Artikel, String> tcMwStArtWahl;
    @FXML
    private TableColumn<Artikel, String> tcBestandFreiArtWahl;
    
    
    @FXML
    private TitledPane paneAuftragsposition;
    @FXML
    private TableView tvAuftragsposition = new TableView<Auftragsposition>();
    @FXML
    private TableColumn<Auftragsposition, String> tcAuftragskopfIDAufPos;
    @FXML
    private TableColumn<Auftragsposition, String> tcPositionsNrAufPos;
    @FXML
    private TableColumn<Auftragsposition, String> tcMaterialNrAufPos;
    @FXML
    private TableColumn<Auftragsposition, String> tcMengeAufPos;
    @FXML
    private TableColumn<Auftragsposition, String> tcEinzelwertAufPos;
    
    
    @FXML
    private TitledPane auftragspositionTP;
    @FXML
    private Pane paneAPD;
    @FXML
    private Button btAnlegenAPD;
    @FXML
    private Button btLoeschenAPD;
    @FXML
    private Button btBearbeitenAPD;
    @FXML
    private Button btAbbrechenAPD;
    
    
    @FXML
    private TextField tfPositionsNrAPD;
    @FXML
    private TextField tfMaterialNrAPD;
    @FXML
    private TextField tfMengeAPD;
    @FXML
    private TextField tfEinzelwertAPD;
    
    
    @FXML
    private Button btCloseAPD;
    @FXML
    private Button btHinzufuegenAPD;
    @FXML
    private TitledPane auftragskopfTPPOS;
    @FXML
    private TextField tfErfDatumPOS;
    @FXML
    private TextField tfAuftragswertPOS;
    @FXML
    private TextField tfPartnerIDPOS;
    @FXML
    private TextArea tfTextPOS;
    @FXML
    private TextField tfAuftragskopfIDPOS;
    @FXML
    private TextField tfAbschlussdatumPOS;
    @FXML
    private TextField tfLieferdatumPOS;
    @FXML
    private TitledPane auftraegeTP;



    
    
    /**
     * 
     * Mehtode die das öffnen der Suchmaske für Aufträge, durch den Button
     * "Auftrag suchen" ermöglicht.
     * @param event ActionEvent zur Prüfung ob der "Auftrag suchen" -
     *              Button getätigt wurde.
     */
    @FXML
    public void sucheAuftrag(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "AuftragSuchen.fxml"));
            Scene scene = new Scene(loader.load(), 755, 500);
            Stage stage = new Stage();
            stage.setTitle("Auftrag suchen");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load the AuftragSuchen!");
        }
    }

    
    
    /**
     * Methode zum Abbrechen der Auftragsanzeige.
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen" 
     *              abfängt.
     */
    @FXML
    public void closeAuftraegeAnzeigen(ActionEvent event) {
        Stage stage = (Stage) closeAA.getScene().getWindow();
        stage.close();
    }

    
    
    /**
     * Initialisiert die Controller-Klasse.
     * @param url URL zur initialisierung.
     * @param rb Resourcen die geladen werden sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.paneGP.setVisible(false);
        this.paneAuftraege.setVisible(true);
        
        btAuftragspositionen.setDisable(true);
        
        try {   
            setTableContent();
            
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText("Keine Adressen gefunden!");
            alert.showAndWait();
        }
        
        
        tcAuftragskopfIDAufPos.setCellValueFactory(
                new PropertyValueFactory<>("auftragskopfID"));
        tcPositionsNrAufPos.setCellValueFactory(
                new PropertyValueFactory<>("positionsnummer"));
        tcMaterialNrAufPos.setCellValueFactory(
                new PropertyValueFactory<>("artikelID"));
        tcMengeAufPos.setCellValueFactory(
                new PropertyValueFactory<>("einzelwert"));        
        tcEinzelwertAufPos.setCellValueFactory(
                new PropertyValueFactory<>("menge"));
                
                
        tcGpIDGPWahl.setCellValueFactory(
                new PropertyValueFactory<>("geschaeftspartnerID"));
        tcTypGPWahl.setCellValueFactory(
                new PropertyValueFactory<>("typ"));
        tcAnschriftIDGPWahl.setCellValueFactory(
                new PropertyValueFactory<>("adresseID"));
        tcLieferIDGPWahl.setCellValueFactory(
                new PropertyValueFactory<>("lieferID"));
        tcKreditlimitGPWahl.setCellValueFactory(
                new PropertyValueFactory<>("kreditlimit"));
        
                
        tcAuftragsID.setCellValueFactory(
                new PropertyValueFactory<>("auftragskopfID"));
        tcAuftragsText.setCellValueFactory(
                new PropertyValueFactory<>("auftragstext"));
        tcgeschaeftspartnerID.setCellValueFactory(
                new PropertyValueFactory<>("geschaeftspartnerID"));
        tcErfDatum.setCellValueFactory(
                new PropertyValueFactory<>("erfassungsdatum"));
        tcLieferDatum.setCellValueFactory(
                new PropertyValueFactory<>("lieferdatum"));
        tcAuftragsart.setCellValueFactory(
                new PropertyValueFactory<>("auftragsart"));
        tcAuftragswert.setCellValueFactory(
                new PropertyValueFactory<>("auftragswert"));
        tcStatus.setCellValueFactory(
                new PropertyValueFactory<>("status"));
        tcAbschDatum.setCellValueFactory(
                new PropertyValueFactory<>("abschlussDatum"));
        
        cbAuftragsstatus.getItems().addAll(
                "E", 
                "F", 
                "A");
        
        cbAuftragsart.getItems().addAll(
                "Barauftrag",
                "Sofortauftrag", 
                "Terminauftrag",
                "Bestellauftrag");
    }
    
  
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Aktualisiert die TableView mit aktuellem Inhalt.
     * @throws java.sql.SQLException SQL Exception
    */
    public void refreshAuftragskopfTable() throws SQLException {
        tvAuftragskopf.getItems().clear();
        setTableContent();
    } 

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Löscht alle Eingaben in den Textfeldern.
     */
    public void clearAuftragskopfTextFields() {
        tfAuftragskopf.clear();
        tfText.clear();
        tfPartnerID.clear();
        tfErfDatum.clear();
        tfLieferdatum.clear();
        cbAuftragsart.valueProperty().set(null);
        tfAuftragswert.clear();
        cbAuftragsstatus.valueProperty().set(null);
        tfAbschlussdatum.clear();
        cbAuftragsstatus.valueProperty().set(null);
        cbAuftragsart.valueProperty().set(null);
    }    
    
  
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt die unteren Eingabefelder für das Anlegen einer neuer Adresse frei.
     * @throws java.sql.SQLException SQLException
     */
    @FXML
    public void auftragAnlegen() throws SQLException {
        
        clearAuftragskopfTextFields();
        
        // Sperre wird aufgehoben 
        this.pane.setVisible(false);
        
        this.auftragskopfTP.setText("Auftragskopf (Anlegemodus)");
       
        // Anlege-Button wird unsichtbar.
        this.btAnlegen.setVisible(false);
        
        // Hinzufügen-Button wird sichtbar.
        this.btHinzufuegen.setVisible(true);
        
        // Ändern Button wird deaktiviert.
        this.btAendern.setDisable(true);
             
        // Löschen Button wird deaktiviert
        this.btLoeschen.setDisable(true);

        AuftragskopfDAO akd = new AuftragskopfDAO();
        tfAuftragskopf.setText(akd.generiereID());
        
        this.auftraegeTP.setVisible(false);
        this.paneGP.setVisible(true);
    
        setTableContentGP();
    }    
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 11.08.17    HEN     Methode erstellt.
    /* 12.08.17    HEN     ObservableArrayList hinzugefügt
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein AdressDAO Objekt und gibt eine Adress ArrayList an eine
     * OberservableList, die dann an die TableView übergeben wird.
     *
     * @throws java.sql.SQLException SQL Exception
    */
    public void setTableContent() throws SQLException {
        AuftragskopfDAO ak = new AuftragskopfDAO();
        ObservableList<Auftragskopf> auftragskopf
            = FXCollections.observableArrayList(
                    ak.gibAlleAuftragskoepfeOhneLKZ());
        tvAuftragskopf.setItems(auftragskopf);
    }
 
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 11.08.17    HEN     Methode erstellt.
    /* 12.08.17    HEN     ObservableArrayList hinzugefügt
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein AdressDAO Objekt und gibt eine Adress ArrayList an eine
     * OberservableList, die dann an die TableView übergeben wird.
     * @throws java.sql.SQLException SQL Exception
    */
    public void setTableContentGP() throws SQLException {
        GeschaeftspartnerDAO gpd = new GeschaeftspartnerDAO();
        ObservableList<Geschaeftspartner> geschaeftspartner
            = FXCollections.observableArrayList(
                    gpd.gibAlleGeschaeftspartnerOhneLKZ());
        tvGPAuswahl.setItems(geschaeftspartner);
    }   

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein AuftragspositionDAO Objekt und gibt eine Auftragsposition 
     * ArrayList an eine OberservableList, die dann an die TableView 
     * übergeben wird.
     * @throws java.sql.SQLException SQL Exception
     */
    public void setTableContentPositionen() throws SQLException {
        AuftragspositionDAO apd = new AuftragspositionDAO();
        String auftragID = this.tfAuftragskopf.getText();
        
        ObservableList<Auftragsposition> auftragspositionen
            = FXCollections.observableArrayList(
                    apd.gibAuftragspositionenZuAuftrag(auftragID));
        tvAuftragsposition.setItems(auftragspositionen);
    }    

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein AuftragspositionDAO Objekt und gibt eine Auftragsposition 
     * ArrayList an eine OberservableList, die dann an die TableView 
     * übergeben wird.
     * @throws java.sql.SQLException SQL Exception
     */
    public void setTableContentArtikel() throws SQLException {
        ArtikelDAO ad = new ArtikelDAO();
        ObservableList<Artikel> artikel
            = FXCollections.observableArrayList(ad.gibAlleArtikelOhneLKZ());
        tvArtikelauswahl.setItems(artikel);
    }      
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Adressen mit aktivem LKZ und stellt sie in der Tabelle
     * dar.
     *
     * @throws java.sql.SQLException SQL Exception
    */
    public void alleMitLKZ() throws SQLException {
        AuftragskopfDAO ak = new AuftragskopfDAO();
        ObservableList<Auftragskopf> adressen
                = FXCollections.observableArrayList(
                        ak.gibAlleAuftragskoepfeMitLKZ());
        tvAuftragskopf.setItems(adressen);
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * "Löscht" eine markierte Adresse, in dem das LKZ auf J gesetzt wird.
     * Aktualisiert anschließend die TableView.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void auftragskopfLoeschen() throws SQLException {
        Object auftragskopf = 
                tvAuftragskopf.getSelectionModel().getSelectedItem();
        Auftragskopf a = (Auftragskopf) auftragskopf;
        
        if (!this.tfAuftragskopf.getText().isEmpty()) {
            Meldung meldung = new Meldung();
            meldung.loeschenAbfragen();

            if (meldung.antwort()) {
                AuftragskopfDAO ak = new AuftragskopfDAO();
                ak.setzeLKZ(a);

                refreshAuftragskopfTable();
            } else {
                meldung.schließeFenster();
                clearAuftragskopfTextFields();
            }
        }
    }
    

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    GET     Methode erstellt.
    /* 22.08.17    BER     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * 
     */
    @FXML
    public void waehleGeschaeftspartnerID() {
        Object geschaeftspartner 
            = tvGPAuswahl.getSelectionModel().getSelectedItem();
        Geschaeftspartner g = (Geschaeftspartner) geschaeftspartner;

        if (g != null) {
            this.tfPartnerID.setText(g.getGeschaeftspartnerID());        
        }
    }


    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt die Werte einer ausgewählten Adresse im unteren Bereich an.
     */
    @FXML
    public void zeigeWerteAn() {
        Object auftragskopf 
                = tvAuftragskopf.getSelectionModel().getSelectedItem();
        Auftragskopf a = (Auftragskopf) auftragskopf;

        if (a != null) {
            this.tfAuftragskopf.setText(a.getAuftragskopfID());
            this.tfPartnerID.setText(a.getGeschaeftspartnerID());
            this.tfText.setText(a.getAuftragstext());
            this.tfErfDatum.setText(a.getErfassungsdatum());
            this.tfLieferdatum.setText(a.getLieferdatum());
            this.tfAbschlussdatum.setText(a.getAbschlussDatum());
            this.cbAuftragsstatus.setValue(a.getStatus());
            this.cbAuftragsart.setValue(a.getAuftragsart());
            this.tfAuftragswert.setText(a.getAuftragswert());
        }
        btAuftragspositionen.setDisable(false);
    }    
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 26.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues.
     * Auftragskopf Objekt, welches dann über die DAO in die DB geschrieben wird
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void auftragHinzufuegen() throws SQLException {
        
        String auftragskopfID = tfAuftragskopf.getText();
        String geschaeftspartnerID = tfPartnerID.getText();
        String auftragsText = tfText.getText();
        String erfassungsDatum = this.tfErfDatum.getText();
        String lieferDatum = this.tfLieferdatum.getText();
        String abschlussDatum = this.tfAbschlussdatum.getText();
        String status = cbAuftragsstatus.getValue();
        String auftragsArt = cbAuftragsart.getValue();
        String auftragsWert = tfAuftragswert.getText();
        String lkz = "N";
        
        Auftragskopf auftragskopf = new Auftragskopf(auftragskopfID, 
                geschaeftspartnerID, auftragsText, erfassungsDatum, lieferDatum,
                abschlussDatum, status, auftragsArt, auftragsWert, lkz);

        AuftragskopfDAO akd = new AuftragskopfDAO();
        akd.fuegeAuftragHinzu(auftragskopf);
        
        clearAuftragskopfTextFields();
             
          // Sperre wird aufgehoben 
        this.pane.setVisible(true);
        
        this.auftragskopfTP.setText("Auftragskopf");
       
        // Anlege-Button wird unsichtbar.
        this.btAnlegen.setVisible(true);
        
        // Hinzufügen-Button wird sichtbar.
        this.btHinzufuegen.setVisible(false);
        
        // Ändern Button wird deaktiviert.
        this.btAendern.setDisable(false);

        // Löschen Button wird deaktiviert
        this.btLoeschen.setDisable(false);
        
        this.paneGP.setVisible(false);
        
        this.auftraegeTP.setVisible(true);
        refreshAuftragskopfTable();
    }
    
    
    
    /**
     * Lässt den Benutzer die Aktion abbrechen.
     */    
    @FXML
    public void aktionAbbrechen() {

        if (!this.auftragskopfTP.getText().equalsIgnoreCase(
                "Auftragskopf")) {
            Meldung meldung = new Meldung();
            meldung.verwerfenFenster();
            if (!(this.tfAuftragskopf.getText().isEmpty())) {

                if (meldung.antwort()) {
                    // Textfeldbereich wird aktiviert
                    this.pane.setDisable(false);
                    // Bearbeiten-Button wird ausgeblendet
                    this.btAnlegen.setVisible(true);
                    // Speichern-Button wird eingeblendet

                    //Der Bearbeitungsmodus des Adressdatensatzes wird aktiviert
                    this.auftragskopfTP.setText(
                            "Auftragskopf");

                    // Anlegen-Button wird deaktiviert
                    this.btAendern.setDisable(false);

                    this.btSpeichern.setVisible(false);

                    this.btSpeichern.setDisable(false);
                    // Löschen-Button wird deaktiviert
                    this.btLoeschen.setDisable(false);

                    this.btHinzufuegen.setVisible(false);

                    this.tvAuftragskopf.setMouseTransparent(false);

                    clearAuftragskopfTextFields();

                } else {

                    meldung.schließeFenster();

                }
            }
        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Löscht alle Eingaben in den Textfeldern.
     */   
    public void clearAuftragsPosTextFields() {
        tfPositionsNrAPD.clear();
        tfMaterialNrAPD.clear();
        tfMengeAPD.clear();
        tfEinzelwertAPD.clear();
    }        
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues 
     * Auftragsposition Objekt, welches dann über die DAO in die DB geschrieben 
     * wird.
     * @throws java.sql.SQLException SQL Exception
     */
    public void auftragspositionHinzufuegen() throws SQLException {
        String auftragskopfID = tcAuftragskopfIDAufPos.getText();
        String positionsnummer = tcPositionsNrAufPos.getText();
        String artikelID = tcMaterialNrAufPos.getText();
        String menge = tcMengeAufPos.getText();
        String einzelwert = tcEinzelwertAufPos.getText();
        String lkz = "N";
        
        Auftragsposition auftragsposition = new Auftragsposition(auftragskopfID,
            positionsnummer, artikelID, menge, einzelwert, lkz);

        AuftragspositionDAO apd = new AuftragspositionDAO();
        apd.fuegeAuftragspositionHinzu(auftragsposition);

        clearAuftragsPosTextFields();
//        refreshAuftragskopfTable();
    }    


    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues 
     * Auftragsposition Objekt, welches dann über die DAO in die DB geschrieben 
     * wird.
     * @throws java.sql.SQLException SQL Exception
     */
    public void leereAuftragspositionHinzufuegen() throws SQLException {
        AuftragskopfDAO akd = new AuftragskopfDAO();
        ArtikelDAO ad = new ArtikelDAO();
  
        String auftragskopfID = akd.gibLetztID();
        String positionsnummer = "";
        String artikelID = "000001";
        String menge = "";
        String einzelwert = "";
        String lkz = "N";
        
        Auftragsposition auftragsposition = new Auftragsposition(auftragskopfID,
            positionsnummer, artikelID, menge, einzelwert, lkz);

        AuftragspositionDAO apd = new AuftragspositionDAO();
        apd.fuegeAuftragspositionHinzu(auftragsposition);

        clearAuftragsPosTextFields();
//        refreshTable();
    }    
  
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt die Werte einer ausgewählten Adresse im unteren Bereich an.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void zeigeAuftragspositionenZuAuftrag() throws SQLException {
        AuftragspositionDAO apd = new AuftragspositionDAO();
        
        if (this.tfAuftragskopf.getText().isEmpty()) {
            System.out.println("NEIN");
        
        } else {                 
            tfErfDatumPOS.setText(tfErfDatum.getText());
    
            tfAuftragswertPOS.setText(tfAuftragswert.getText());
    
            tfPartnerIDPOS.setText(tfPartnerID.getText());
    
            tfTextPOS.setText(tfText.getText());
    
            tfAuftragskopfIDPOS.setText(tfAuftragskopf.getText());
    
            tfAbschlussdatumPOS.setText(tfAbschlussdatum.getText());
    
            tfLieferdatumPOS.setText(tfLieferdatum.getText());
            
            
            apd.gibAuftragspositionenZuAuftrag(tfAuftragskopf.getText());
            setTableContentPositionen();
            this.paneAuftraege.setVisible(false);
            this.paneAuftragspositionen.setVisible(true);
            this.paneArtikelauswahl.setVisible(false);
            this.paneAuftragsposition.setVisible(true);        
        }
    }      
    
    
    
}
