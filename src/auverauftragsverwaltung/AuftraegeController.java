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
import Datenbank.SucheDAO;
import Klassen.Artikel;
import Klassen.Auftragskopf;
import Klassen.Auftragsposition;
import Klassen.Geschaeftspartner;
import Klassen.Meldung;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TimeZone;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Mudimbi
 */
public class AuftraegeController implements Initializable {

    /**
     * Pane, um die Eingabe in der Aufträgemaske zu deaktivieren.
     */
    @FXML
    private AnchorPane paneAuftraege;
    
    /**
     * Pane, in dem die Auftragsköpfe angezeigt werden.
     */
    @FXML
    private TitledPane auftragskopfTP;
    
    /**
     * Textfield für die AuftragskopfID in der Auftragskopfmaske.
     */
    @FXML
    private TextField tfAuftragskopf;
    
    /**
     * Textfield für den Auftragstext in der Auftragskopfmaske.
     */
    @FXML
    private TextArea tfText;
    
    /**
     * Textfield für die GeschäftspartnerID in der Auftragskopfmaske.
     */
    @FXML
    private TextField tfPartnerID;
    
    /**
     * Combobox für den Auftragsstatus in der Auftragskopfmaske.
     */
    @FXML
    private ComboBox<String> cbAuftragsstatus = new ComboBox<>();
    
    /**
     * Combobox für die Auftragsart in der Auftragskopfmaske.
     */
    @FXML
    private ComboBox<String> cbAuftragsart = new ComboBox<>();
    
    /**
     * Textfield für den Auftragswert in der Auftragskopfmaske.
     */
    @FXML
    private TextField tfAuftragswert;
    
    /**
     * Button in der Auftragskopfmaske, um die Auftragspositionen zu einem
     * ausgewählten Auftragskopf anzuzeigen.
     */
    @FXML
    private Button btAuftragspositionen;
    
    /**
     * Button in der Auftragskopfmaske, um einen neuen Auftrag anzulegen.
     */
    @FXML
    private Button btAnlegen;
    
    /**
     * Button in der Auftragskopfmaske, um einen Auftrag zu bearbeiten.
     */
    @FXML
    private Button btAendern;
    
    /**
     * Button in der Auftragskopfmaske, um einen Auftrag zu löschen.
     */
    @FXML
    private Button btLoeschen;
    
    /**
     * Button in der Auftragskopfmaske, um eine Aktion abzubrechen.
     */
    @FXML
    private Button closeAA;
    
    /**
     * Textfield für das Erfassungsdatum in der Auftragskopfmaske. 
     */
    @FXML
    private TextField tfErfDatum;
    
    /**
     * Textfield für das Lieferdatum in der Auftragskopfmaske.
     */
    @FXML
    private TextField tfLieferdatum;
    
    /**
     * Textfield für das Abschlussdatum in der Auftragskopfmaske.
     */
    @FXML
    private TextField tfAbschlussdatum;
    
    /**
     * Button in der Auftragskopfmaske, um eine Aktion abzubrechen.
     */
    @FXML
    private Button btAbbrechen;
    
    /**
     * Button in der Auftragskopfmaske, um einen Auftragskopf hinzuzufügen.
     */
    @FXML
    private Button btHinzufuegen;
    
    /**
     * Button in der Auftragskopfmaske, um die Änderungen eines Auftragskopfs 
     * zu speichern.
     */
    @FXML
    private Button btSpeichern;
    
    /**
     * Hauptpane im Auftragskopf.
     */
    @FXML
    private Pane pane;
    
    /**
     * TableView für die Anzeige aller Auftragsköpfe.
     */
    @FXML
    private TableView tvAuftragskopf = new TableView<Auftragskopf>();
    
    /**
     * Tabellenspalte für den Auftragskopf in der Auftragskopftabelle.
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragsID;
    
    /**
     * Tabellenspalte für den Auftragstext in der Auftragskopftabelle.
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragsText;
    
    /**
     * Tabellenspalte für die GeschäftspartnerID in der Auftragskopftabelle.
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcgeschaeftspartnerID;
    
    /**
     * Tabellenspalte für das Erfassungsdatum in der Auftragskopftabelle.
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcErfDatum;
    
    /**
     * Tabellenspalte für das Lieferdatum in der Auftragskopftabelle.
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcLieferDatum;
    
    /**
     * Tabellenspalte für die Auftragsart in der Auftragskopftabelle.
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragsart;
    
    /**
     * Tabellenspalte für den Auftragswert in der Auftragskopftabelle.
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragswert;
    
    /**
     * Tabellenspalte für den Auftragsstatus in der Auftragskopftabelle.
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcStatus;
    
    /**
     * Tabellenspalte für das Abschlussdatum in der Auftragskopftabelle.
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAbschDatum;
       
    /**
     * Pane für die Anzeige der Geschäftspartner.
     */
    @FXML
    private TitledPane paneGP;
    
    /**
     * TableView für die Anzeige aller Geschäftspartner.
     */
    @FXML
    private TableView tvGPAuswahl = new TableView<Geschaeftspartner>();
    
    /**
     * Tabellenspalte für die GeschäftspartnerID in der GPTabelle.
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> tcGpIDGPWahl;
    
    /**
     * Tabellenspalte für den GEschäftspartner Typ in der GPTabelle.
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> tcTypGPWahl;
    
    /**
     * Tabellenspalte für die Geschäftspartner Anschrift in der GPTabelle.
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> tcAnschriftIDGPWahl;
    
    /**
     * Tabellenspalte für die Geschäftspartner Lieferadresse in der GPTabelle.
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> tcLieferIDGPWahl;
    
    /**
     * Tabellenspalte für das GeschäftspartnerLimit in der GPTabelle.
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> tcKreditlimitGPWahl;
      
    /**
     * Pane für die Auftragspositionen.
     */
    @FXML
    private AnchorPane paneAuftragspositionen;
    
    /**
     * Pane für Artikelauswahl.
     */
    @FXML
    private TitledPane paneArtikelauswahl;
    
    /**
     * TableView für die Anzeige aller Artikel.
     */
    @FXML
    private TableView tvArtikelauswahl = new TableView<Artikel>();
    
    /**
     * Tabellenspalte für die ArtikelID in der Artikelauswahl.
     */
    @FXML
    private TableColumn<Artikel, String> tcArtikelIDArtWahl;
    
    /**
     * Tabellenspalte für den Artikeltext in der Artikeltabelle.
     */
    @FXML
    private TableColumn<Artikel, String> tcArtikeltextArtWahl;
    
    /**
     * Tabellenspalte für den Bestelltext in der Artikelauswahl.
     */
    @FXML
    private TableColumn<Artikel, String> tcBestelltextArtWahl;
    
    /**
     * Tabellenspalte für die ArtikelID in der Artikelauswahl.
     */
    @FXML
    private TableColumn<Artikel, String> tcEinzelwertArtArtWahl;
    
    /**
     * Tabellenspalte für den Bestellwert in der Artikelauswahl.
     */
    @FXML
    private TableColumn<Artikel, String> tcBestellwertArtWahl;
    
    /**
     * Tabellenspalte für die Mehrwertsteuer in der Artikelauswahl.
     */
    @FXML
    private TableColumn<Artikel, String> tcMwStArtWahl;
    
    /**
     * Tabellenspalte für den Freien Bestand in der Artikelauswahl.
     */
    @FXML
    private TableColumn<Artikel, String> tcBestandFreiArtWahl;
      
    /**
     * Pane für die Auftragspositionen.
     */
    @FXML
    private TitledPane paneAuftragsposition;
    
    /**
     * TableView für die Anzeige aller Auftragspositionen.
     */
    @FXML
    private TableView tvAuftragsposition = new TableView<Auftragsposition>();
    
    /**
     * Tabellenspalte für die AuftragskopfID in der Positionsverwaltung.
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcAuftragskopfIDAufPos;
    
    /**
     * Tabellenspalte für die Positionsnummer in der Positionsverwaltung.
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcPositionsNrAufPos;
    
    /**
     * Tabellenspalte für die ArtikelID in der Positionsverwaltung.
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcMaterialNrAufPos;
    
    /**
     * Tabellenspalte für die Menge in der Positionsverwaltung.
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcMengeAufPos;
    
    /**
     * Tabellenspalte für den Einzelwert in der Positionsverwaltung.
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcEinzelwertAufPos;
    
    /**
     * Pane für die Auftragspositionen.
     */
    @FXML
    private TitledPane auftragspositionTP;
    
    /**
     * Pane für die AuftragsPositionDatensätze.
     */
    @FXML
    private Pane paneAPD;
    
    /**
     * Button, um eine neue Auftragsposition in den AuftragsPositionDatensätzen
     * anzulegen.
     */
    @FXML
    private Button btAnlegenAPD;
    
    /**
     * Button, um eine Auftragsposition in den AuftragsPositionDatensätzen zu
     * löschen.
     */
    @FXML
    private Button btLoeschenAPD;
    
    /**
     * Button, um eine Auftragsposition in den AuftragsPositionDatensätzen zu
     * bearbeiten.
     */
    @FXML
    private Button btBearbeitenAPD;
    
    /**
     * Button, um eine Aktion in den AuftragsPositionDatensätzen abzubrechen.
     */
    @FXML
    private Button btAbbrechenAPD; 
    
    /**
     * Textfield für die Positionsnummer in den AuftragsPositionDatensätzen.
     */
    @FXML
    private TextField tfPositionsNrAPD;
    
    /**
     * Textfield für die ArtikelID in den AuftragsPositionDatensätzen.
     */
    @FXML
    private TextField tfMaterialNrAPD;
    
    /**
     * Textfield für die Menge in den AuftragsPositionDatensätzen.
     */
    @FXML
    private TextField tfMengeAPD;
    
    /**
     * Textfield für den Einzelwert in den AuftragsPositionDatensätzen.
     */
    @FXML
    private TextField tfEinzelwertAPD;
    
    /**
     * Button, um die AuftragsPositionDatensätzen zu schließen.
     */
    @FXML
    private Button btCloseAPD;
    
    /**
     * Button, um eine Position in den AuftragsPositionDatensätzen anzulegen.
     */
    @FXML
    private Button btHinzufuegenAPD;
    
    /**
     * Pane für den Auftragskopf in den Postionen.
     */
    @FXML
    private TitledPane auftragskopfTPPOS;
    
    /**
     * Textfield für das Erfassungsdatum in den Auftragspositionen.
     */
    @FXML
    private TextField tfErfDatumPOS;
    
    /**
     * Textfield für den Auftragswert in den Auftragspositionen.
     */
    @FXML
    private TextField tfAuftragswertPOS;
    
    /**
     * Textfield für die PartnerID in den Auftragspositionen.
     */
    @FXML
    private TextField tfPartnerIDPOS;
    
    /**
     * Textarea für den Auftragstext in den Auftragspositionen.
     */
    @FXML
    private TextArea tfTextPOS;
    
    /**
     * Textfield für die AuftragskopfID in den Auftragspositionen.
     */
    @FXML
    private TextField tfAuftragskopfIDPOS;
    
    /**
     * Textfield für das Abschlussdatum in den Auftragspositionen.
     */
    @FXML
    private TextField tfAbschlussdatumPOS;
    
    /**
     * Textfield für das Lieferdatum in den Auftragspositionen.
     */
    @FXML
    private TextField tfLieferdatumPOS;
    
    /**
     * Pane für die Aufträgemaske.
     */
    @FXML
    private TitledPane auftraegeTP;
    
    /**
     *Button, um Änderungen an einer Position in den AuftragsPositionDatensätzen
     * zu speichern.
     */
    @FXML
    private Button btSpeichernAPD;
    
    /**
     * Pane, zum Deaktivieren der Eingabefelder beim Status "Freigegeben".
     */
    @FXML
    private Pane paneAuftragskopfStatus;
   
    /**
     * ObseravleList für die Kombobox "Status".
     */
    private ObservableList<String> allOptions 
        = FXCollections.observableArrayList(
        "Erfasst", "Freigegeben", "Abgeschlossen");
    
    /**
     * TextField für den MwSt in den AuftragsPositionDatensätzen.
     */
    @FXML
    private TextField tfMwStAPD;
    
    /**
     * Button für die Suche von Aufträgen.
     */
    @FXML
    private Button btAuftragSuchen;
    
    /**
     * ComboBox für die Auswahl des Suchkriteriums in den Aufträgen.
     */
    @FXML
    private ComboBox<String> cbSuchfeldAuftraege = new ComboBox<>();
    
    /**
     * TextField für den Suchbegriff in den Aufträgen.
     */
    @FXML
    private TextField tfSuchbegriff;
    
    /**
     * Button, um nur Lieferanten in der TableView "Aufträge" anzuzeigen.
     */
    @FXML
    private Button btLieferanten;
    
    /**
     * Button, um nur Kunden in der TableView "Aufträge" anzuzeigen.
     */
    @FXML
    private Button btKunden;
    
    /**
     * Buttom, um die Auftragssuche zurückzusetzen.
     */
    @FXML
    private Button btSucheZuruecksetzen;
    
    
    
    /**
     * Mehtode die das öffnen der Suchmaske für Aufträge, durch den Button
     * "Auftrag suchen" ermöglicht.
     * @param event ActionEvent zur Prüfung ob der "Auftrag suchen" -
     *              Button getätigt wurde.
     */
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

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 30.08.17    GET     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
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
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 30.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
        
    /**
     * Methode zum Abbrechen der Auftragspositionsanzeige.
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen" 
     *              abfängt.
     */
    @FXML
    public void closeAuftraegspositionAnzeigen(ActionEvent event) {
        this.paneAuftraege.setVisible(true);
        this.paneAuftragspositionen.setVisible(false);
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 30.08.17    GET     Methode erstellt.
    /*------------------------------------------------------------------------*/
    /**
     * Begrenzte Feldeingabe.
     *
     * @param tf Teftfeld
     * @param zahl Zahl
     */
    private void begrenzeTextFeldEingabe(TextField tf, int zahl) {
        tf.setTextFormatter(new TextFormatter<>(change
            -> {
            return change.getControlNewText().length() <= zahl ? change : null;
        }));
    }
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 30.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Initialisiert die Controller-Klasse.
     * @param url URL zur initialisierung.
     * @param rb Resourcen die geladen werden sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Menge auf 3 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfMengeAPD, 3);

        this.paneGP.setVisible(false);
        this.paneAuftraege.setVisible(true);
        this.btAuftragspositionen.setDisable(true);
        this.paneAPD.setDisable(true);
        this.pane.setVisible(true);
        this.btAendern.setVisible(true);
        this.btAendern.setDisable(true);
        this.btSpeichern.setVisible(false);
        this.btAnlegen.setDisable(false);
        this.btLoeschen.setDisable(true);
        this.btAuftragspositionen.setDisable(true);
        this.btAbbrechen.setDisable(true);
        
        try {   
            setTableContent();
            
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText("Keine Adressen gefunden!");
            alert.showAndWait();
        }
        
        //Artikel Tabelle
        tcArtikelIDArtWahl.setCellValueFactory(
                new PropertyValueFactory<>("artikelID"));
        tcArtikeltextArtWahl.setCellValueFactory(
                new PropertyValueFactory<>("artikeltext"));
        tcBestelltextArtWahl.setCellValueFactory(
                new PropertyValueFactory<>("bestelltext"));
        tcEinzelwertArtArtWahl.setCellValueFactory(
                new PropertyValueFactory<>("einzelwert"));
        tcBestellwertArtWahl.setCellValueFactory(
                new PropertyValueFactory<>("bestellwert"));
        tcMwStArtWahl.setCellValueFactory(
                new PropertyValueFactory<>("steuer"));
        tcBestandFreiArtWahl.setCellValueFactory(
                new PropertyValueFactory<>("bestandsmengeFrei"));      
        
        //Auftrag Tabelle bei Auftragspositionen oben
        tcAuftragskopfIDAufPos.setCellValueFactory(
                new PropertyValueFactory<>("auftragskopfID"));
        tcPositionsNrAufPos.setCellValueFactory(
                new PropertyValueFactory<>("positionsnummer"));
        tcMaterialNrAufPos.setCellValueFactory(
                new PropertyValueFactory<>("artikelID"));
        tcMengeAufPos.setCellValueFactory(
                new PropertyValueFactory<>("menge"));        
        tcEinzelwertAufPos.setCellValueFactory(
                new PropertyValueFactory<>("einzelwert"));
                     
        //Geschäftspartner Auswahltabelle
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
                   
        //Auftragskopf Tabelle
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
                "Erfasst", 
                "Freigegeben", 
                "Abgeschlossen");    
        cbAuftragsart.getItems().addAll(
                "Barauftrag",
                "Sofortauftrag", 
                "Terminauftrag",
                "Bestellauftrag");
        
        cbSuchfeldAuftraege.getItems().addAll(
                "AuftragskopfID",
                "GeschäftspartnerID",
                "Auftragstext",
                "Erfassungsdatum",
                "Lieferdatum",
                "Abschlussdatum",
                "Status",
                "Auftragsart",
                "Auftragswert");
    }
    
  
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Aktualisiert die TableView Auftragskopf mit aktuellem Inhalt.
     * @throws java.sql.SQLException SQLFehler.
    */
    public void refreshAuftragskopfTable() throws SQLException {
        tvAuftragskopf.getItems().clear();
        setTableContent();
    } 

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 04.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Aktualisiert die TableView Auftragspositionen mit aktuellem Inhalt.
     * @throws java.sql.SQLException SQLFehler
    */
    public void refreshAuftragspositionTable() throws SQLException {
        tvAuftragsposition.getItems().clear();
        setTableContentPositionen();
    }     
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Löscht alle Eingaben in den Textfeldern der Auftragskopfmaske.
     */
    public void clearAuftragskopfTextFields() {
        tfAuftragskopf.clear();
        tfText.clear();
        tfPartnerID.clear();
        tfErfDatum.clear();
        tfAuftragswert.clear();
        tfLieferdatum.clear();
        cbAuftragsart.valueProperty().set(null);
        cbAuftragsstatus.valueProperty().set(null);
        tfAbschlussdatum.clear();
        cbAuftragsstatus.valueProperty().set(null);
        cbAuftragsart.valueProperty().set(null);
    }    
    
  
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 02.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Prüft, ob das eingegebene Datum auf ein Wochenende fällt.
     * @return Geprüftes Datum.
     */
    public String gibDatum() {
        GregorianCalendar cal = new GregorianCalendar(); 
        cal.setTimeZone(TimeZone.getTimeZone("CET"));
        cal.getTime();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM); 
        String datum = "";
        
        if (cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY
            || cal.get(GregorianCalendar.DAY_OF_WEEK) 
            == GregorianCalendar.SUNDAY) {   
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText(
                    "Achtung: Das heutige Datum fällt auf ein Wochenende!!!");
            alert.showAndWait();
            datum = df.format(cal.getTime());

        } else {
            datum = df.format(cal.getTime());
            
        }
        return datum;
    }
 
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 02.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Prüft, ob das eingegebene Datum auf einen Feiertrag fällt.
     * @param cal Zu prüfendes Datum.
     * @return Geprüftes Datum.
     */
    public boolean istFeiertag(GregorianCalendar cal) {
        boolean istFeiertag;

        int jahr = cal.get(GregorianCalendar.YEAR);
        int monat = cal.get(GregorianCalendar.MONTH);
        int tag = cal.get(GregorianCalendar.DATE);
        
        Calendar kalender = GregorianCalendar.getInstance();
        kalender.clear();
        kalender.setTimeZone(TimeZone.getTimeZone("CET"));
        kalender.set(jahr, monat, tag);
        
        kalender.getTime();
        
        HolidayManager manager 
            = HolidayManager.getInstance(HolidayCalendar.GERMANY);
    
        istFeiertag = manager.isHoliday(kalender);   
        
        return istFeiertag;
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
        this.tfErfDatum.setText(gibDatum());
        this.cbAuftragsstatus.setItems(komboBoxFilter(0, 1));
        this.cbAuftragsstatus.setValue("Erfasst");
        
        this.pane.setVisible(false);
        this.auftragskopfTP.setText("Auftragskopf (Anlegemodus)");    
        this.btAnlegen.setVisible(false);
        this.btHinzufuegen.setVisible(true);   
        this.btAendern.setDisable(true);
        this.btLoeschen.setDisable(true);
        this.btAbbrechen.setDisable(false);

        AuftragskopfDAO akd = new AuftragskopfDAO();
        tfAuftragskopf.setText(akd.generiereID());
        
        this.auftraegeTP.setVisible(false);
        this.paneGP.setVisible(true);
        
        setTableContentGP();
    }    
    


    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 12.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht anhand eines Suchkriteriums und Suchbegriffs nach einem Auftrag.
     * @throws java.sql.SQLException SQLFehler
     */
    @FXML
    public void auftragSuchen() throws SQLException {
        SucheDAO sd = new SucheDAO();
        ArrayList gefundeneAuftraege;

        String suchkriterium = cbSuchfeldAuftraege.getValue();
        String suchbegriff = tfSuchbegriff.getText();

        gefundeneAuftraege = sd.auftragskopfSuche(suchkriterium, suchbegriff);
        zeigeGefundeneAuftraege(gefundeneAuftraege);
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 11.08.17    HEN     Methode erstellt.
    /* 12.08.17    HEN     ObservableArrayList hinzugefügt
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein AdressDAO Objekt und gibt eine Adress ArrayList an eine
     * OberservableList, die dann an die TableView übergeben wird.
     * @throws java.sql.SQLException SQLFehler
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
     * @throws java.sql.SQLException SQLFehler
    */
    @FXML
    public void setTableContentKunden() throws SQLException {
        AuftragskopfDAO akd = new AuftragskopfDAO();
        ObservableList<Auftragskopf> auftragskopf
            = FXCollections.observableArrayList(
                    akd.gibAlleGeschaeftspartnerKunde());
        tvAuftragskopf.setItems(auftragskopf);
        this.auftraegeTP.setText("Lieferaufträge");
    } 
    

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 11.08.17    HEN     Methode erstellt.
    /* 12.08.17    HEN     ObservableArrayList hinzugefügt
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein AdressDAO Objekt und gibt eine Adress ArrayList an eine
     * OberservableList, die dann an die TableView übergeben wird.
     * @throws java.sql.SQLException SQLFehler
    */
    @FXML
    public void setTableContentLieferanten() throws SQLException {
        AuftragskopfDAO akp = new AuftragskopfDAO();
        ObservableList<Auftragskopf> auftragskopf
            = FXCollections.observableArrayList(
                    akp.gibAlleGeschaeftspartnerLieferant());
        tvAuftragskopf.setItems(auftragskopf);
        this.auftraegeTP.setText("Bestellaufträge");
    }    
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 11.08.17    HEN     Methode erstellt.
    /* 12.08.17    HEN     ObservableArrayList hinzugefügt
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein GeschäftspartnerDAO Objekt und gibt eine Geschäftspartner 
     * ArrayList an eine OberservableList, die dann an die TableView übergeben 
     * wird.
     * @throws java.sql.SQLException SQLFehler
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
     * @throws java.sql.SQLException SQLFehler
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
     * Erstellt ein ArtikelDAO Objekt und gibt eine Artikel ArrayList an eine 
     * OberservableList, die dann an die TableView übergeben wird.
     * @throws java.sql.SQLException SQLFehler
     */
    public void setTableContentArtikel() throws SQLException {
        ArtikelDAO ad = new ArtikelDAO();
        ObservableList<Artikel> artikel
            = FXCollections.observableArrayList(ad.gibAlleArtikelOhneLKZ());
        tvArtikelauswahl.setItems(artikel);
    }      
    

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 13.09.17    HEN     Methode erstellt.
    /* 13.09.17    HEN     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Setzt die Suche zurück.
     * @throws java.sql.SQLException SQLFehler
     */
    @FXML
    public void setzeSucheZurueck() throws SQLException {
        this.tfSuchbegriff.setText("");
        this.cbSuchfeldAuftraege.setValue(null);
        this.auftraegeTP.setText("Aufträge");
        setTableContent();
    }
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Adressen mit aktivem LKZ und stellt sie in der Tabelle
     * dar.
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
     * "Löscht" einen markierten Auftragskopf,in dem das LKZ auf J gesetzt wird.
     * Aktualisiert anschließend die TableView.
     * @throws java.sql.SQLException SQLFehler
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
    /* 04.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * "Löscht" eine markierte Auftragsposition, in dem das LKZ auf J gesetzt 
     * wird. Aktualisiert anschließend die TableView.
     * @throws java.sql.SQLException SQLFEhler
     */
    @FXML
    public void auftragspositionLoeschen() throws SQLException {
        Object auftragsposition = 
                tvAuftragsposition.getSelectionModel().getSelectedItem();
        Auftragsposition ap = (Auftragsposition) auftragsposition;
        
        if (!tfPositionsNrAPD.getText().isEmpty()) {
            Meldung meldung = new Meldung();
            meldung.loeschenAbfragen();

            if (meldung.antwort()) {
                AuftragspositionDAO apd = new AuftragspositionDAO();
                String steuer = tfMwStAPD.getText();    
              
                double einzelwert = Double.parseDouble(ap.getEinzelwert());
                int menge = Integer.parseInt(ap.getMenge());
                int steuerInt = Integer.parseInt(steuer);
                double steuersatz = einzelwert + (einzelwert * steuerInt / 100);
                double ergebnis = -(steuersatz * menge);
                
                apd.berechneAuftragswert(ergebnis, ap.getAuftragskopfID());
                String auftragswert 
                    = apd.gibAuftragswert(ap.getAuftragskopfID());
                tfAuftragswertPOS.setText(auftragswert);
                
                apd.setzeAuftragsposLKZ(ap);

                refreshAuftragspositionTable();
                refreshAuftragskopfTable();
                
            } else {
                meldung.schließeFenster();
                clearAuftragsPosTextFields();
            }
        }
    }    
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    GET     Methode erstellt.
    /* 22.08.17    BER     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Füllt das untere GeschäftspartnerID Feld mit einer ID, die in der 
     * Tabelle ausgewählt wurde.
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
     * Zeigt die Werte eines ausgewählten Auftrags im unteren Bereich an.
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
            switch (a.getStatus()) {
                case "E":
                    this.cbAuftragsstatus.setValue("Erfasst");
                    break;
                case "F":
                    this.cbAuftragsstatus.setValue("Freigegeben");
                    break;
                case "A":
                    this.cbAuftragsstatus.setValue("Abgeschlossen");
                    break;
                default:
                    break;
            }
            this.cbAuftragsart.setValue(a.getAuftragsart());
            this.tfAuftragswert.setText(a.getAuftragswert());          
        }   
        
        if (a.getStatus().equals("A")) {
            this.btAendern.setDisable(true);
            this.btLoeschen.setDisable(true);
            this.btAuftragspositionen.setDisable(false);        
        
        } else {
            this.btAendern.setDisable(false);
            this.btLoeschen.setDisable(false);
            this.btAuftragspositionen.setDisable(false);      
        }
    }    
    
 
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt die Werte eines ausgewählten Artikel im unteren Bereich an.
     */
    @FXML
    public void zeigeWerteTvArtikel() {
        Object artikel 
                = tvArtikelauswahl.getSelectionModel().getSelectedItem();
        Artikel a = (Artikel) artikel;

        if (a != null) {
            this.tfEinzelwertAPD.setText(a.getEinzelwert());
            this.tfMaterialNrAPD.setText(a.getArtikelID());
            this.tfMwStAPD.setText(a.getSteuer());
            this.tfMengeAPD.requestFocus();
        }
    }      
    

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 12.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Methode bekommt eine ArrayList mit den gefundenen Aufträgen übergeben und
     * aktualisiert damit die TableView.
     * @param auftraege Übergebene Aufträge.
     * @throws java.sql.SQLException SQLFehler 
     */
    public void zeigeGefundeneAuftraege(ArrayList auftraege) 
        throws SQLException {
        refreshAuftragskopfTable();
        ObservableList<Auftragskopf> auftragskopfAusgabe
                = FXCollections.observableArrayList(auftraege);
        tvAuftragskopf.setItems(auftragskopfAusgabe);
    }    
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt die Werte einer ausgewählten Position im unteren Bereich an.
     * @throws java.sql.SQLException SQLFehler
     */
    @FXML
    public void zeigeWerteTvPositionen() throws SQLException {
        Object auftragsposition 
                = tvAuftragsposition.getSelectionModel().getSelectedItem();
        Auftragsposition ap = (Auftragsposition) auftragsposition;
        ArtikelDAO ard = new ArtikelDAO();
        
        if (ap != null) {       
            this.tfPositionsNrAPD.setText(ap.getPositionsnummer());
            this.tfMengeAPD.setText(ap.getMenge());
            this.tfEinzelwertAPD.setText(ap.getEinzelwert());
            this.tfMaterialNrAPD.setText(ap.getArtikelID());
            this.tfMwStAPD.setText(ard.gibArtikelSteuer(ap.getArtikelID()));
            this.btAbbrechenAPD.setDisable(true);
                        
            if (this.cbAuftragsstatus.getValue().equals("Abgeschlossen")) { 
                this.btAnlegenAPD.setDisable(true);
                this.btBearbeitenAPD.setDisable(true);
                this.btLoeschenAPD.setDisable(true);
            
            } else {            
                this.btAnlegenAPD.setDisable(false);
                this.btBearbeitenAPD.setDisable(false);
                this.btLoeschenAPD.setDisable(false);
            }
        }
    }   
    

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 03.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Füllt das untere Datumsfeld mit dem Datum, das im DatePicker ausgewählt
     * wurde.
     */
    @FXML
    public void datumAendern() {
        Meldung meldung = new Meldung();
        String date = meldung.dialogDatepicker();

        if (meldung.antwort()) {
            this.tfErfDatum.setText(date);
        }
        
    } 
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 26.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues.
     * Auftragskopf Objekt,welches dann über die DAO in die DB geschrieben wird.
     * Fragt den Benutzer, ob das eingegeben Datum gesetzt oder geändert werden
     * soll, falls es auf einen Feiertag oder Wochenende fällt.
     * Es wird zusätzlich eine neue Auftragsposition zu dem neu erstellen 
     * Auftrag angelegt.
     * Aktualisiert anschließend die Auftragskopftabelle.
     * @throws java.sql.SQLException SQLFehler
     * @throws java.text.ParseException ParseException
     */
    @FXML
    public void auftragHinzufuegen() throws SQLException, ParseException { 
        GregorianCalendar cal = new GregorianCalendar();
        String erfassungsDatum = "";
        boolean ergebnis = false;
        
        while (!ergebnis) {
            StringTokenizer st 
                = new StringTokenizer(this.tfErfDatum.getText(), ".", false);
        
            //Datum in cal Objekt packen.
            while (st.hasMoreTokens()) {
                String tag = st.nextToken();
                String monat = st.nextToken();
                String jahr =  st.nextToken();
                
                int year = Integer.parseInt(jahr);
                int month = Integer.parseInt(monat);
                int day = Integer.parseInt(tag);
                month = month - 1;
                
                cal.clear();
                cal.setTimeZone(TimeZone.getTimeZone("CET"));
                cal.set(year, month, day);
                cal.getTime();
            }
        
            //Datum auf Wochenende prüfen
            if (cal.get(GregorianCalendar.DAY_OF_WEEK) 
                == GregorianCalendar.SATURDAY
                || cal.get(GregorianCalendar.DAY_OF_WEEK) 
                == GregorianCalendar.SUNDAY) {
                
                Meldung meldung = new Meldung();
                meldung.dialogDatumWochenende();
            
                //Benutzer entscheiden lasen, ob Auftrag anlegen oder nicht.
                if (meldung.antwort()) {
                    erfassungsDatum = this.tfErfDatum.getText();
                    ergebnis = true;

                } else {
                    datumAendern();                   
                    ergebnis = false;
                } 
        
            //Datum auf Feiertag prüfen
            } else if (istFeiertag(cal)) {        
                Meldung meldung = new Meldung();
                meldung.dialogDatumFeiertag();
            
                //Benutzer entscheiden lasen, ob Auftrag anlegen oder nicht.
                if (meldung.antwort()) {
                    erfassungsDatum = this.tfErfDatum.getText();
                    ergebnis = true;

                } else {
                    datumAendern();                   
                    ergebnis = false;
                } 
                
            } else {
                erfassungsDatum = this.tfErfDatum.getText();
                ergebnis = true;
            }
        }
           
        String auftragskopfID = tfAuftragskopf.getText();
        String geschaeftspartnerID = tfPartnerID.getText();
        String auftragsText = tfText.getText();
        String lieferDatum = this.tfLieferdatum.getText();
        String abschlussDatum = this.tfAbschlussdatum.getText();  
        String status = "";
        switch (cbAuftragsstatus.getValue()) {
            case "Erfasst":
                status = "E";
                break;
            case "Freigegeben":
                status = "F";
                break;
            case "Abgeschlossen":
                status = "A";
                break;
            default:
                break;
        }
        String auftragsArt = cbAuftragsart.getValue();
        String auftragsWert = "0";
        String lkz = "N";
        
        Auftragskopf auftragskopf = new Auftragskopf(auftragskopfID, 
                geschaeftspartnerID, auftragsText, erfassungsDatum, lieferDatum,
                abschlussDatum, status, auftragsArt, auftragsWert, lkz);

        AuftragskopfDAO akd = new AuftragskopfDAO();
        akd.fuegeAuftragHinzu(auftragskopf);
        
        clearAuftragskopfTextFields();
             
        //Buttons setzen
        this.pane.setVisible(true);      
        this.auftragskopfTP.setText("Auftragskopf");
        this.btAnlegen.setVisible(true);
        this.btHinzufuegen.setVisible(false);
        this.btAendern.setDisable(false);
        this.btLoeschen.setDisable(false);
        this.paneGP.setVisible(false);
        this.auftraegeTP.setVisible(true);
        this.btAbbrechen.setDisable(true);
        
        //Auftragskopftabelle aktualisieren
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
                    //Buttons aktivieren / deaktiviern
                    this.pane.setDisable(false);
                    this.btAnlegen.setVisible(true);
                    this.auftragskopfTP.setText("Auftragskopf");
                    this.btAendern.setDisable(true);
                    this.btSpeichern.setVisible(false);
                    this.btSpeichern.setDisable(false);
                    this.btLoeschen.setDisable(true);
                    this.btHinzufuegen.setVisible(false);
                    this.tvAuftragskopf.setMouseTransparent(false);
                    this.auftraegeTP.setVisible(true);
                    this.btAbbrechen.setDisable(true);     
                    this.paneGP.setVisible(false);
                    
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
     * Löscht alle Eingaben der Textfeldern in der Auftragspositionsmaske.
     */   
    public void clearAuftragsPosTextFields() {
        tfPositionsNrAPD.clear();
        tfMaterialNrAPD.clear();
        tfMengeAPD.clear();
        tfEinzelwertAPD.clear();
        tfMwStAPD.clear();
    }        
    
    
 
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues 
     * Auftragsposition Objekt welches dann über die DAO in die DB geschrieben 
     * wird.
     * @throws java.sql.SQLException SQLFehler
     */
    @FXML
    public void auftragspositionAnlegen() throws SQLException {   
        this.paneAuftragsposition.setVisible(false);
        this.paneArtikelauswahl.setVisible(true);
        this.btAnlegenAPD.setVisible(false);
        this.btHinzufuegenAPD.setVisible(true);
        this.btAbbrechenAPD.setDisable(true);
        this.btLoeschenAPD.setDisable(true);
        
        AuftragspositionDAO apd = new AuftragspositionDAO();
        String auftragskopfID = tfAuftragskopfIDPOS.getText();
        String positionsnummer = apd.generiereID(auftragskopfID);
        tfPositionsNrAPD.setText(positionsnummer);       
        tfMengeAPD.clear();
        
        setTableContentArtikel();
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
    @FXML
    public void auftragspositionHinzufuegen() throws SQLException {
        AuftragspositionDAO apd = new AuftragspositionDAO();
  
        String auftragskopfID = tfAuftragskopfIDPOS.getText();
        String positionsnummer = tfPositionsNrAPD.getText();
        String artikelID = tfMaterialNrAPD.getText();
        String menge = tfMengeAPD.getText();
        String einzelwert = tfEinzelwertAPD.getText();
        String lkz = "N";
        
        Auftragsposition auftragsposition = new Auftragsposition(auftragskopfID,
            positionsnummer, artikelID, menge, einzelwert, lkz);
    
        apd.fuegeAuftragspositionHinzu(auftragsposition);
        
        berechneAuftragswert(auftragskopfID);
        tfAuftragswertPOS.setText(apd.gibAuftragswert(auftragskopfID));

        refreshAuftragspositionTable();
        refreshAuftragskopfTable();
        clearAuftragsPosTextFields();
        
        this.paneArtikelauswahl.setVisible(false);
        this.paneAuftragsposition.setVisible(true);
        this.btHinzufuegenAPD.setVisible(false);
        this.btAnlegenAPD.setVisible(true);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
        
    /**
     * Berechnet den Auftragswert anhand der angegebenen Menge und füllt
     * das obere Auftragswert Feld.
     * @param auftragskopfID Auftragskopf dessen Wert berechnet werden soll.
     * @throws java.sql.SQLException SQLFehler
     */
    public void berechneAuftragswert(String auftragskopfID) 
            throws SQLException {
        String mengeAPD = tfMengeAPD.getText();
        String steuerAPD = tfMwStAPD.getText();
        int menge =  Integer.parseInt(mengeAPD);
        int steuer = Integer.parseInt(steuerAPD);
        
        String einzelWertAPD = tfEinzelwertAPD.getText();
        double einzelwert = Double.parseDouble(einzelWertAPD);
        double berechneteSteuer = einzelwert + (einzelwert * steuer / 100);
  
        double rechnung = menge * berechneteSteuer;
        rechnung = rechnung * 100;
        rechnung = Math.round(rechnung);
        rechnung = rechnung / 100;        
        
        AuftragspositionDAO apd = new AuftragspositionDAO();
        apd.berechneAuftragswert(rechnung, auftragskopfID);          
    }

    

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt alle Auftragspositionen zu einem ausgewählten AUftragskopf an.
     * @throws java.sql.SQLException SQLFehler
     */
    @FXML
    public void zeigeAuftragspositionenZuAuftrag() throws SQLException {
        //Daten aus dem Auftragskopf im oberen Abteil anzeigen.
        tfErfDatumPOS.setText(tfErfDatum.getText());    
        tfAuftragswertPOS.setText(tfAuftragswert.getText());  
        tfPartnerIDPOS.setText(tfPartnerID.getText());
        tfTextPOS.setText(tfText.getText());
        tfAuftragskopfIDPOS.setText(tfAuftragskopf.getText());
        tfAbschlussdatumPOS.setText(tfAbschlussdatum.getText());
        tfLieferdatumPOS.setText(tfLieferdatum.getText());
        setTableContentPositionen();
        
        if (this.cbAuftragsstatus.getValue().equals("Abgeschlossen")) {
            this.paneAuftraege.setVisible(false);
            this.paneAuftragspositionen.setVisible(true);
            this.paneArtikelauswahl.setVisible(false);
            this.paneAuftragsposition.setVisible(true);
            
            this.btAnlegenAPD.setDisable(true);
            this.btBearbeitenAPD.setDisable(true);
            this.btLoeschenAPD.setDisable(true);
            this.btAbbrechenAPD.setDisable(true);
            
        } else {
            this.paneAuftraege.setVisible(false);
            this.paneAuftragspositionen.setVisible(true);
            this.paneArtikelauswahl.setVisible(false);
            this.paneAuftragsposition.setVisible(true);
            
            this.btAnlegenAPD.setDisable(false);
            this.btBearbeitenAPD.setDisable(true);
            this.btLoeschenAPD.setDisable(true);
            this.btAbbrechenAPD.setDisable(true);
        }
    }      
    

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 16.08.17    GET     Methode erstellt.
    /* 22.08.17    HEN     Adressdatenpane geändert. Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Lässt das Bearbeiten einer ausgewählten Adresse zu.
     */
    @FXML
    public void bearbeitePosition() {
        //Buttons aktivieren / deaktivieren
        this.paneAPD.setDisable(true);
        this.btBearbeitenAPD.setVisible(false);
        this.btSpeichernAPD.setVisible(true);
        this.btAnlegenAPD.setDisable(true);
        this.btLoeschenAPD.setDisable(true);
        this.tfMengeAPD.requestFocus();
        tvAuftragsposition.setMouseTransparent(true);
    }
    


    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 06.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Lässt das Bearbeiten einer ausgewählten Adresse zu.
     * @param start Filter Start
     * @param end Filter Ende
     * @return Gefilterte Items für die Kombobox
     */   
    private ObservableList<String> komboBoxFilter(int start, int end) {
        final ObservableList<String> anzuzeigendeItems 
            = FXCollections.<String>observableArrayList();
    
        anzuzeigendeItems.addAll(allOptions.subList(start, end));
        return anzuzeigendeItems;
    }
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 06.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Lässt das Bearbeiten einer ausgewählten Adresse zu.
     */
    @FXML
    public void bearbeiteAuftragskopf() {
        //Buttons aktivieren / deaktivieren
        this.pane.setVisible(false);
        this.btAendern.setVisible(false);
        this.btSpeichern.setVisible(true);
        this.btAnlegen.setDisable(true);
        this.btLoeschen.setDisable(true);
        this.btAuftragspositionen.setDisable(true);
        this.btAbbrechen.setDisable(false);
        tvAuftragskopf.setMouseTransparent(true);

        if (this.cbAuftragsstatus.getValue().equals("Erfasst")) {
            this.cbAuftragsstatus.setItems(komboBoxFilter(0, 2));
    
        } else if (this.cbAuftragsstatus.getValue().equals("Freigegeben")) {
            this.cbAuftragsstatus.setItems(komboBoxFilter(0, 3));
        }  
    }    

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 16.08.17    GET     Methode erstellt.
    /* 22.08.17    HEN     Exceptions eingefügt. Getestet & Freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Speichert die gemachten Änderungen an einer Auftragsposition in die 
     * Datenbank und aktualisiert die TableView mit den neuen Werten.
     * @throws java.sql.SQLException SQLFehler.
     */
    @FXML
    public void speichereAenderungPosition() throws SQLException {
        AuftragspositionDAO apd = new AuftragspositionDAO();
        
        String auftragsID = tfAuftragskopfIDPOS.getText();
        String positionsnummer = tfPositionsNrAPD.getText();
        String artikelID = tfMaterialNrAPD.getText();
        String menge = tfMengeAPD.getText();
        String alteMenge = apd.gibPositionsMenge(positionsnummer);
        String einzelwert = tfEinzelwertAPD.getText();
        String lkz = "N";
            
        Auftragsposition auftragsposition = new Auftragsposition(auftragsID, 
            positionsnummer, artikelID, alteMenge, einzelwert, lkz);

        //Erstellte Auftragsposition zur DB hinzufügen.       
        apd.aendereAuftragsposition(auftragsposition);
        
        //Alte Menge der gewählten Position holen und mit der neu eingegebenen
        //verrechnen. 
        int alteMengeInt = Integer.parseInt(alteMenge);
        int mengeInt = Integer.parseInt(menge);
        int x = alteMengeInt - mengeInt;
           
        if (x == 0) {
   
        } else if (menge.equals("0")) {
            //Menge neu berechnen.
            double einzelwertDouble = Double.parseDouble(einzelwert);    
            double neuerWert = alteMengeInt * einzelwertDouble;  
            apd.berechneAuftragswert(-neuerWert, auftragsID);  
            apd.setzeAuftragsposLKZ(auftragsposition);
        
        } else {       
            //Menge neu berechnen.
            String steuerAPD = tfMwStAPD.getText();
            int steuerAPDInt = Integer.parseInt(steuerAPD);     
            
            double einzelwertDouble = Double.parseDouble(einzelwert);
            double berechneteSteuer = einzelwertDouble * steuerAPDInt / 100;
            double steuerwert = einzelwertDouble + berechneteSteuer;
            
            double neuerWert = x * steuerwert * (-1);
            
            auftragsposition.setMenge(menge);
            apd.aendereAuftragsposition(auftragsposition);
            
            apd.berechneAuftragswert(neuerWert, auftragsID);   
        }
         
        refreshAuftragspositionTable();
        refreshAuftragskopfTable();
        clearAuftragsPosTextFields();
        tfAuftragswertPOS.setText(apd.gibAuftragswert(auftragsID));

        //Buttons und Textfelder aktivieren / deaktivieren.
        this.paneAPD.setDisable(false);
        this.btBearbeitenAPD.setVisible(true);
        this.btBearbeitenAPD.setDisable(true);
        this.btSpeichernAPD.setVisible(false);
        this.btAnlegenAPD.setDisable(false);
        this.btLoeschenAPD.setDisable(true); 
        tvAuftragsposition.setMouseTransparent(false);
    }
    

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 06.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Speichert die gemachten Änderungen in die Datenbank und aktualisiert die
     * TableView mit den neuen Werten.
     * @throws java.sql.SQLException SQLFehler
     */
    @FXML
    public void speichereAenderungAuftragskopf() throws SQLException { 
        AuftragskopfDAO akd = new AuftragskopfDAO();
        String auftragskopfID = tfAuftragskopf.getText();
        String auftragstext = tfText.getText();
        String partnerID = tfPartnerID.getText();
        String erfassungsdatum = tfErfDatum.getText();
        String lieferdatum = tfLieferdatum.getText();
        String abschlussdatum = tfAbschlussdatum.getText();
        String auftragswert = tfAuftragswert.getText();
        String statusNeu = cbAuftragsstatus.getValue();
        String statusAlt = akd.gibAuftragsstatus(auftragskopfID);
        switch (statusNeu) {
            case "Erfasst":
                statusNeu = "E";
                break;
            case "Freigegeben":
                statusNeu = "F";
                break;
            case "Abgeschlossen":
                statusNeu = "A";
                break;
            default:
                break;
        }
        String art = cbAuftragsart.getValue();
        String lkz = "N";
        boolean istVerfuegbar; 
        boolean hatKredit;
        String rechnung;   
        
        //Falls Status von E nach F gewechselt wird, wird geprüft, ob der freie
        //Bestand ausreicht. Falls JA: wird FREI und RES berechnet.
        if (statusAlt.equals("E") && statusNeu.equals("F")) {
            istVerfuegbar = bestandVerfuegbar(auftragskopfID);
            hatKredit = kreditVerfuegbar(auftragskopfID, partnerID);
            
            if (istVerfuegbar && hatKredit) {
                rechnung = "addition";
                berechneMengeFreiRes(auftragskopfID, rechnung);
                berechneKreditlimit(auftragswert, partnerID, rechnung);
            
            } else {
                //Buttons aktivieren / deaktivieren
                this.pane.setVisible(true);
                this.btAendern.setVisible(true);
                this.btAendern.setDisable(true);
                this.btSpeichern.setVisible(false);
                this.btAnlegen.setDisable(false);
                this.btAbbrechen.setDisable(true);
                tvAuftragskopf.setMouseTransparent(false);  
                tvAuftragskopf.getSelectionModel().select(-1);
                clearAuftragskopfTextFields();
                refreshAuftragskopfTable();
                
                return;
            }
          
        //Falls Status von F zurück nach E gewechselt wird, werden die Mengen
        //von FREI und RES wieder zurückgerechnet.
        } else if (statusAlt.equals("F") && statusNeu.equals("E")) {
            rechnung = "subtraktion";
            berechneMengeFreiRes(auftragskopfID, rechnung);
            berechneKreditlimit(auftragswert, partnerID, rechnung);
          
        //Falls Status von F nach A gewechsetl wird, werden die Mengen RES
        //und VER berechnet.
        } else if (statusAlt.equals("F") && statusNeu.equals("A")) {
            berechneMengeResVer(auftragskopfID);
            abschlussdatum = gibDatum();
        }
        
        Auftragskopf auftrag = new Auftragskopf(auftragskopfID, partnerID, 
            auftragstext, erfassungsdatum, lieferdatum, abschlussdatum, 
            statusNeu, art, auftragswert, lkz);

        akd.aendereAuftragskopf(auftrag);
        
        //Buttons aktivieren / deaktivieren
        this.pane.setVisible(true);
        this.btAendern.setVisible(true);
        this.btAendern.setDisable(true);
        this.btSpeichern.setVisible(false);
        this.btAnlegen.setDisable(false);
        this.btAbbrechen.setDisable(true);
        tvAuftragskopf.setMouseTransparent(false);
        
        tvAuftragskopf.getSelectionModel().select(-1);
        clearAuftragskopfTextFields();
        refreshAuftragskopfTable();
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 06.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Berechnet die Bestandsmenge FREI und RESERVIERT.
     * @param auftragskopfID Auftrag, mit dessen Positionen die Bestände 
     * berechnet werden.
     * @param rechnung Rechnung die durchgeführt wird
     * @throws java.sql.SQLException SQLFehler
     */
    public void berechneMengeFreiRes(String auftragskopfID, String rechnung) 
            throws SQLException {
        AuftragspositionDAO apd = new AuftragspositionDAO();
        ArtikelDAO artd = new ArtikelDAO();
        
        ArrayList<Auftragsposition> auftragspositionen;
        auftragspositionen 
            = apd.gibAuftragspositionenZuAuftrag(auftragskopfID);
        
        String artikelID;
        String mengePosition;
        String mengeFreiAlt;
        String mengeFreiNeu;
        String mengeResAlt;
        String mengeResNeu;       
        
        //Additionsrechnung für die Mengen FREI und RES.
        if (rechnung.equals("addition")) {
            for (int i = 0; i < auftragspositionen.size(); i++) {
                //ArtikelID und Menge des Artikels der Positionen holen
                artikelID = auftragspositionen.get(i).getArtikelID();
                mengePosition = auftragspositionen.get(i).getMenge();
            
                //Menge FREI und RESERVIERT zu der Position aus DB holen
                mengeFreiAlt = artd.gibMengeFrei(artikelID);
                mengeResAlt = artd.gibMengeReserviert(artikelID);
              
                //Menge der Position mit alter Menge FREI in DB verrechnen
                int mengePositionInt =  Integer.parseInt(mengePosition);
                int mengeFreiAltInt = Integer.parseInt(mengeFreiAlt);
                int mengeFreiNeuInt = mengeFreiAltInt - mengePositionInt;
                mengeFreiNeu = String.valueOf(mengeFreiNeuInt);
            
                //Menge der Position mit alter Menge RES verrechnen
                int mengeResAltInt = Integer.parseInt(mengeResAlt);
                int mengeResNeuInt = mengeResAltInt + mengePositionInt;
                mengeResNeu = String.valueOf(mengeResNeuInt);
            
                artd.setzeMengeFreiRes(artikelID, mengeFreiNeu, mengeResNeu);
            }
        
        //Subtraktionsrechnung für die Mengen FREI und RES.
        } else if (rechnung.equals("subtraktion")) {
            for (int i = 0; i < auftragspositionen.size(); i++) {
                //ArtikelID und Menge des Artikels der Positionen holen
                artikelID = auftragspositionen.get(i).getArtikelID();
                mengePosition = auftragspositionen.get(i).getMenge();
            
                //Menge FREI und RESERVIERT zu der Position aus DB holen
                mengeFreiAlt = artd.gibMengeFrei(artikelID);
                mengeResAlt = artd.gibMengeReserviert(artikelID);
              
                //Menge der Position mit alter Menge FREI in DB verrechnen
                int mengePositionInt =  Integer.parseInt(mengePosition);
                int mengeFreiAltInt = Integer.parseInt(mengeFreiAlt);
                int mengeFreiNeuInt = mengeFreiAltInt + mengePositionInt;
                mengeFreiNeu = String.valueOf(mengeFreiNeuInt);
            
                //Menge der Position mit alter Menge RES verrechnen
                int mengeResAltInt = Integer.parseInt(mengeResAlt);
                int mengeResNeuInt = mengeResAltInt - mengePositionInt;
                mengeResNeu = String.valueOf(mengeResNeuInt);
            
                artd.setzeMengeFreiRes(artikelID, mengeFreiNeu, mengeResNeu);
            }
        }
    }

    

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 07.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Berechnet die Bestandsmenge RESERVIERT und VERKAUFT.
     * @param auftragskopfID Auftrag, mit dessen Positionen die Bestände 
     * berechnet werden.
     * @throws java.sql.SQLException SQLFehler
     */
    public void berechneMengeResVer(String auftragskopfID) 
            throws SQLException {
        AuftragspositionDAO apd = new AuftragspositionDAO();
        ArtikelDAO artd = new ArtikelDAO();
        
        ArrayList<Auftragsposition> auftragspositionen;
        auftragspositionen 
            = apd.gibAuftragspositionenZuAuftrag(auftragskopfID);
        
        String artikelID;
        String mengePosition;
        String mengeVerkauftAlt;
        String mengeVerkauftNeu;
        String mengeResAlt;
        String mengeResNeu;       
        
        for (int i = 0; i < auftragspositionen.size(); i++) {
            //ArtikelID und Menge des Artikels der Positionen holen
            artikelID = auftragspositionen.get(i).getArtikelID();
            mengePosition = auftragspositionen.get(i).getMenge();
            
            //Menge FREI und RESERVIERT zu der Position aus DB holen
            mengeResAlt = artd.gibMengeReserviert(artikelID);
            mengeVerkauftAlt = artd.gibMengeVerkauft(artikelID);
            
            //Menge der Position mit alter Menge VERKAUFT in DB verrechnen
            int mengePositionInt =  Integer.parseInt(mengePosition);
            int mengeVerkauftAltInt = Integer.parseInt(mengeVerkauftAlt);
            int mengeVerkauftNeuInt = mengeVerkauftAltInt + mengePositionInt;
            mengeVerkauftNeu = String.valueOf(mengeVerkauftNeuInt);
            
            //Menge der Position mit alter Menge RES verrechnen
            int mengeResAltInt = Integer.parseInt(mengeResAlt);
            int mengeResNeuInt = mengeResAltInt - mengePositionInt;
            mengeResNeu = String.valueOf(mengeResNeuInt);
            
            artd.setzeMengeResVer(artikelID, mengeVerkauftNeu, mengeResNeu);   
        }
    }
    

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 13.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Berechnet die Bestandsmenge ZULAUF anhand der Positionen eines Auftrags.
     * @param auftragskopfID Auftrag, mit dessen Positionen die Bestände 
     * berechnet werden.
     * @throws java.sql.SQLException SQLFehler
     */
    public void berechneMengeZulauf(String auftragskopfID) 
            throws SQLException {
        AuftragspositionDAO apd = new AuftragspositionDAO();
        ArtikelDAO artd = new ArtikelDAO();
        
        ArrayList<Auftragsposition> auftragspositionen;
        auftragspositionen 
            = apd.gibAuftragspositionenZuAuftrag(auftragskopfID);
        
        String artikelID;
        String mengeZulaufPos;
        String mengeZulaufAlt;
        String mengeZulaufNeu;       
        
        for (int i = 0; i < auftragspositionen.size(); i++) {
            //ArtikelID und Menge des Artikels der Positionen holen
            artikelID = auftragspositionen.get(i).getArtikelID();
            mengeZulaufPos = auftragspositionen.get(i).getMenge();
            mengeZulaufAlt = artd.gibMengeZulauf(artikelID);
         
            //Menge der Position mit alter Menge VERKAUFT in DB verrechnen
            int mengeZulaufPosInt =  Integer.parseInt(mengeZulaufPos);
            int mengeZulaufAltInt = Integer.parseInt(mengeZulaufAlt);
            int mengeZulaufNeuInt = mengeZulaufPosInt + mengeZulaufAltInt;
            mengeZulaufNeu = String.valueOf(mengeZulaufNeuInt);
             
            artd.setzeMengeZulauf(artikelID, mengeZulaufNeu);
        }
    }      
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 13.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Berechnet die Bestandsmenge RESERVIERT und VERKAUFT.
     * @param auftragskopfID Auftrag, mit dessen Positionen die Bestände 
     * berechnet werden.
     * @throws java.sql.SQLException SQLFehler
     */
    public void berechneMengeZulaufFrei(String auftragskopfID) 
            throws SQLException {
        AuftragspositionDAO apd = new AuftragspositionDAO();
        ArtikelDAO artd = new ArtikelDAO();
        
        ArrayList<Auftragsposition> auftragspositionen;
        auftragspositionen 
            = apd.gibAuftragspositionenZuAuftrag(auftragskopfID);
        
        String artikelID;
        String mengePosition;
        String mengeZulaufAlt;
        String mengeZulaufNeu;
        String mengeFreiAlt;
        String mengeFreiNeu;       
        
        for (int i = 0; i < auftragspositionen.size(); i++) {
            //ArtikelID und Menge des Artikels der Positionen holen
            artikelID = auftragspositionen.get(i).getArtikelID();
            mengePosition = auftragspositionen.get(i).getMenge();
            
            //Menge FREI und RESERVIERT zu der Position aus DB holen
            mengeZulaufAlt = artd.gibMengeZulauf(artikelID);
            mengeFreiAlt = artd.gibMengeFrei(artikelID);
            
            //Menge der Position mit alter Menge VERKAUFT in DB verrechnen
            int mengePositionInt =  Integer.parseInt(mengePosition);
            int mengeZulaufAltInt = Integer.parseInt(mengeZulaufAlt);
            int mengeZulaufNeuInt = mengeZulaufAltInt - mengePositionInt;
            mengeZulaufNeu = String.valueOf(mengeZulaufNeuInt);
            
            //Menge der Position mit alter Menge RES verrechnen
            int mengeFreiAltInt = Integer.parseInt(mengeFreiAlt);
            int mengeFreiNeuInt = mengeFreiAltInt + mengePositionInt;
            mengeFreiNeu = String.valueOf(mengeFreiNeuInt);
            
            artd.setzeMengeZulaufFrei(artikelID, mengeFreiNeu, mengeZulaufNeu);
        }
    }    
    
    

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 13.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Berechnet das Kreditlimit eines ausgewählten Geschäftsparnters.
     * @param auftragswert Auftragswert der berechnet wird.
     * @param gpID Geschäftspartner dessen Kreditlimit berechnet wird
     * @param rechnung Gibt an ob Addition oder Subtraktion
     * @throws java.sql.SQLException SQLFehler
     */
    public void berechneKreditlimit(String auftragswert, String gpID, 
        String rechnung) 
        throws SQLException {
        GeschaeftspartnerDAO gpd = new GeschaeftspartnerDAO();
        
        String kreditlimit; 
        String kreditlimitNeu;    
        kreditlimit = gpd.gibKreditlimit(gpID);
        
        //Additionsrechnung für das Kreditlimit.
        if (rechnung.equals("addition")) {         
            //Altes Kreditlimit mit neuem in DB verrechnen
            double kreditlimitDouble =  Double.parseDouble(kreditlimit);
            double auftragswertDouble = Double.parseDouble(auftragswert);
            kreditlimitDouble = kreditlimitDouble - auftragswertDouble;
            
            kreditlimitDouble = kreditlimitDouble * 100;
            kreditlimitDouble = Math.round(kreditlimitDouble);
            kreditlimitDouble = kreditlimitDouble / 100;  
            
            kreditlimitNeu = String.valueOf(kreditlimitDouble);

            gpd.setzeKreditlmit(kreditlimitNeu, gpID);

        //Subtraktionsrechnung für die Mengen FREI und RES.
        } else if (rechnung.equals("subtraktion")) {
            //Altes Kreditlimit mit neuem in DB verrechnen
            double kreditlimitDouble =  Double.parseDouble(kreditlimit);
            double auftragswertDouble = Double.parseDouble(auftragswert);
            kreditlimitDouble = kreditlimitDouble + auftragswertDouble;
            
            kreditlimitDouble = kreditlimitDouble * 100;
            kreditlimitDouble = Math.round(kreditlimitDouble);
            kreditlimitDouble = kreditlimitDouble / 100;   
            
            kreditlimitNeu = String.valueOf(kreditlimitDouble);
           
            gpd.setzeKreditlmit(kreditlimitNeu, gpID);
        }
        
    }   
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 06.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Prüft, ob der FREIE Bestand für die Menge der einzelnen Positionen
     * ausreicht.
     * @param auftragskopfID Auftrag, mit dessen Positionen die Bestände 
     * geprüft werden.
     * @return True, falls Bestand ausreicht, False, wenn nicht
     * @throws java.sql.SQLException SQLFehler
     */
    public boolean bestandVerfuegbar(String auftragskopfID) 
            throws SQLException {
        boolean bestandVerfuegbar = true;
        AuftragspositionDAO apd = new AuftragspositionDAO();
        ArtikelDAO artd = new ArtikelDAO();
        
        ArrayList<Auftragsposition> auftragspositionen;
        auftragspositionen 
            = apd.gibAuftragspositionenZuAuftrag(auftragskopfID);
        
        String artikelID;
        String mengePosition;
        String mengeFreiAlt;
        
        for (int i = 0; i < auftragspositionen.size() 
            && bestandVerfuegbar; i++) {
            //ArtikelID und Menge des Artikels der Positionen holen
            artikelID = auftragspositionen.get(i).getArtikelID();
            mengePosition = auftragspositionen.get(i).getMenge();
            //Menge FREI zu der Position aus DB holen
            mengeFreiAlt = artd.gibMengeFrei(artikelID);
            int mengeFreiAltInt = Integer.parseInt(mengeFreiAlt);
            int mengePositionInt =  Integer.parseInt(mengePosition);
            
            if (mengeFreiAltInt - mengePositionInt < 0) {
                bestandVerfuegbar = false;
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Information zum Bestand");
                alert.setHeaderText("Der Bestand des Artikels " + artikelID 
                    + " ist zu niedrig!" + "\n\n" + mengePosition + " gewählt\n" 
                    + mengeFreiAlt + " verfügbar");
                alert.showAndWait();
            }      
        }
        
        return bestandVerfuegbar;
    }       


    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 13.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Prüft, ob das Kreditlmit des Kunden ausreicht.
     * @param auftragskopfID Auftrag, mit dessen Positionen das Kreditlimit
     * geprüft wird.
     * @param gpID GeschäftspartnerID
     * @return True, falls Kredit ausreicht, False, wenn nicht
     * @throws java.sql.SQLException SQLFehler
     */
    public boolean kreditVerfuegbar(String auftragskopfID, String gpID) 
            throws SQLException {
        boolean kreditVerfuegbar = true;
        GeschaeftspartnerDAO gpd = new GeschaeftspartnerDAO();
        AuftragskopfDAO akd = new AuftragskopfDAO();   

        String auftragswert;
        String kreditlimit;
        
        auftragswert = akd.gibAuftragswert(auftragskopfID);
        kreditlimit = gpd.gibKreditlimit(gpID);

        //Wert und Limit nach INT / Double casten
        double auftragswertDouble = Double.parseDouble(auftragswert);
        double kreditlimitInt = Double.parseDouble(kreditlimit);

        if (kreditlimitInt > auftragswertDouble) {
            kreditVerfuegbar = true;  
          
        } else {
            kreditVerfuegbar = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information zum Bestand");
            alert.setHeaderText("Das Kreditlimit vom Geschäftspartner" + gpID 
                + " reicht nicht aus!");
            alert.showAndWait();
        }      
         
        return kreditVerfuegbar;
    } 
    
    
}