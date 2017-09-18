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
* 15.08.2017    BER     alleMitLKZ(), alleOhneLKZ()
* 16.08.2017    BER     artikelAnlegen() erstellt.
* 17.08.2017    BER     artikelHinzufuegen(), artikelLoeschen() erstellt.
* 17.08.2017    GET     zeigeWerteAn() erstellt.
* 18.08.2017    BER     bearbeiteArtikel(), speichereAenderung() erstellt.
* 19.08.2017    HEN     artikelSuchen() erstellt.
* 19.08.2017    BER     artikelSuchen() erweitert.
* 19.08 2017    SEZ     aktionAbbrechen(), setzeSucheZurueck() erstellt.
*-------------------------------------------------------------------------------
 */
package auverauftragsverwaltung;

import Klassen.Meldung;
import Datenbank.ArtikelDAO;
import Datenbank.SucheDAO;
import Klassen.Artikel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
     * Button um eine Aktion abzubrechen.
     */
    @FXML
    private Button btAbbrechen;
    
    /**
     * Button um zurückzugehen.
     */
    @FXML
    private Button btZurueckSetzen;


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
            this.btBearbeiten.setDisable(true);
            this.btLoeschen.setDisable(true);
            this.btAbbrechen.setDisable(true);

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText("Keine Adressen gefunden!");
            alert.showAndWait();
        }

        //Bestandfelder deaktivieren
        this.tfBestandReserviert.setEditable(false);
        this.tfBestandZulauf.setEditable(false);
        this.tfBestandVerkauft.setEditable(false);
        
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
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    SAM     Methode erstellt.
    /* 16.08.17    SAM     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
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
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 16.08.17    GET     Methode erstellt.
    /* 17.08.17    GET     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
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
                = FXCollections.observableArrayList(ar.gibAlleArtikelOhneLKZ());
        tvArtikel.setItems(artikel);
    }
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /* 16.08.17    HEN     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/

    /**
     * Methode bekommt eine ArrayList mit den gefundenen Artikel übergeben und
     * aktualisiert damit die TableView.
     *
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
     *
     * @throws java.sql.SQLException SQL Exception
     */
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
     */
    public void clearTextFields() {

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
     *
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
    /* 16.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt die unteren Eingabefelder für das Anlegen einer neuer Adresse frei.
     *
     * @throws java.sql.SQLException SQLException
     */
    @FXML
    public void artikelAnlegen() throws SQLException {
        tvArtikel.setMouseTransparent(true);
        clearTextFields();

        //Buttons aktivieren / deaktivieren
        this.pane.setDisable(true);
        this.btAnlegen.setVisible(false);
        this.btHinzufuegen.setVisible(true);
        this.artikeldatensatzPane.setText("Artikeldatensatz (Anlegemodus)");
        this.btBearbeiten.setDisable(true);
        this.btLoeschen.setDisable(true);
        this.btAbbrechen.setDisable(false);
              
        this.tfBestandFrei.setText("0");
        this.tfBestandReserviert.setText("0");
        this.tfBestandZulauf.setText("0");
        this.tfBestandVerkauft.setText("0");

        ArtikelDAO ar = new ArtikelDAO();
        tfMaterialNr.setText(ar.generiereID());
    }

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues Artikel
     * Objekt, welches dann über die DAO in die DB geschrieben wird.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void artikelHinzufuegen() throws SQLException {
            
        if (validateFields()) {   
            if (validateEinzelwert() && validateBestellwert()
                && validateBestandFREI() && validateBestandRESERVIERT()
                && validateBestandZULAUF() && validateBestandVERKAUFT()) {
                 
            String artikelID = tfMaterialNr.getText();
                String einzelwert = tfEinzelwert.getText().replace(',', '.');
                String artikeltext = tfArtikelbeschreibung.getText();
                String bestellwert = tfBestellwert.getText().replace(',', '.');
                String bestelltext = tfBestellbeschreibung.getText();
                String steuer = cbMwstsatz.getValue();
                String bestandsmengeFrei = tfBestandFrei.getText();
                String bestandsmengeReserviert = tfBestandReserviert.getText();
                String bestandsmengeZulauf = tfBestandZulauf.getText();
                String bestandsmengeVerkauft = tfBestandVerkauft.getText();
                String lkz = "N";             
                Artikel artikel = new Artikel(artikelID, artikeltext, 
                        bestelltext, einzelwert, bestellwert, steuer,
                        bestandsmengeFrei, bestandsmengeReserviert, 
                        bestandsmengeZulauf, bestandsmengeVerkauft, lkz);

                ArtikelDAO ar = new ArtikelDAO();
                ar.fuegeArtikelHinzu(artikel);

                clearTextFields();
                refreshTable();

                //Buttons aktivieren / deaktivieren
                this.pane.setDisable(false);
                this.btAnlegen.setVisible(true);
                this.btHinzufuegen.setVisible(false);
                this.artikeldatensatzPane.setText("Artikeldatensatz");
                this.btBearbeiten.setDisable(true);
                this.btLoeschen.setDisable(true);            
                tvArtikel.setMouseTransparent(false);
                this.btAbbrechen.setDisable(true);              
            }
        }
    }

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * "Löscht" einen markierten Artikel, in dem das LKZ auf J gesetzt wird.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void artikelLoeschen() throws SQLException {

        Object artikel = tvArtikel.getSelectionModel().getSelectedItem();
        Artikel b = (Artikel) artikel;

        if (!this.tfMaterialNr.getText().isEmpty()) {
            Meldung meldung = new Meldung();
            meldung.loeschenAbfragen();

            if (meldung.antwort()) {
                ArtikelDAO ar = new ArtikelDAO();
                ar.setzeLKZ(b);
                refreshTable();
            
            } else {
                meldung.schließeFenster();
            }
            clearTextFields();
            this.btBearbeiten.setDisable(true);
            this.btLoeschen.setDisable(true);
            this.btAbbrechen.setDisable(true);
            this.btZurueckSetzen.requestFocus();
        }
    }

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 18.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Lässt das Bearbeiten einer ausgewählten Adresse zu.
     */
    @FXML
    public void bearbeiteArtikel() {
        // Textfeldbereich wird aktiviert
        this.pane.setDisable(true);
        this.btBearbeiten.setVisible(false);
        this.btSpeichern.setVisible(true);
        this.artikeldatensatzPane.setText(
                "Artikeldatensatz (Bearbeitungsmodus)");
        this.btAnlegen.setDisable(true);
        this.btLoeschen.setDisable(true);
        this.btAbbrechen.setDisable(false);
    }

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 18.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Speichert die gemachten Änderungen in die Datenbank und aktualisiert die
     * View mit den neuen Werten.
     *
     * @throws java.sql.SQLException SQLException.
     */
    @FXML
    public void speichereAenderung() throws SQLException {
        
        if (validateFields()) {
            
            if (validateEinzelwert() && validateBestellwert()
                     && validateBestandFREI() && validateBestandRESERVIERT()
                     && validateBestandZULAUF() && validateBestandVERKAUFT()) {
            
                String artikelID = tfMaterialNr.getText();
                String einzelwert = tfEinzelwert.getText().replace(',', '.');
                String artikeltext = tfArtikelbeschreibung.getText();
                String bestellwert = tfBestellwert.getText().replace(',', '.');
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
                this.btBearbeiten.setVisible(true);
                this.btSpeichern.setVisible(false);
                this.artikeldatensatzPane.setText("Artikeldatensatz");
                this.btAnlegen.setDisable(false);            
                this.btBearbeiten.setDisable(true);
                this.btLoeschen.setDisable(true);
                this.btAbbrechen.setDisable(true);         
            }      
        }
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
            
            this.btBearbeiten.setDisable(false);
            this.btLoeschen.setDisable(false);
            this.btAbbrechen.setDisable(true);
        }
    }

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    HEN     Methode erstellt.
    /* 19.08.17    BER     An SucheDAO angepasst.
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt die Werte einer ausgewählten Adresse im unteren Bereich an.
     *
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
    /* 19.08.17    SEZ     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Setzt die Suche zurück.
     *
     * @throws java.sql.SQLException SQLException
     */
    @FXML
    public void setzeSucheZurueck() throws SQLException {
        this.tfSuchbegriff.setText("");
        this.cbSuchfeld.setValue("Bitte wählen...");
        setTableContent();
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    SEZ     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     *  Methode für Funktionalität des Abbrechen-Buttons. 
     * Bricht im Bearbeitungsmodus und im Anlegemodus die jeweilige Aktion falls
     * gewünscht ab.
     */
    @FXML
    public void aktionAbbrechen() {
        if (!this.artikeldatensatzPane.getText().equalsIgnoreCase(""
                + "Artikeldatensatz")) {

            Meldung meldung = new Meldung();
            meldung.verwerfenFenster();

            if (meldung.antwort()) {
                // Textfeldbereich wird aktiviert
                this.pane.setDisable(false);
                this.btAnlegen.setVisible(true);
                this.artikeldatensatzPane.setText("Artikeldatensatz");
                this.btBearbeiten.setDisable(true);
                this.btBearbeiten.setVisible(true);
                this.btSpeichern.setVisible(false);
                this.btAnlegen.setDisable(false);
                this.btLoeschen.setDisable(true);
                this.btHinzufuegen.setVisible(false);
                this.tvArtikel.setMouseTransparent(false);             
                this.btAbbrechen.setDisable(true);

                clearTextFields();
            
            } else {
                meldung.schließeFenster();
            }
        }
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 08.09.2017    GET     Methode erstellt.
    /* 08.09.2017    GET     Getestet & freigegeben 
    /*------------------------------------------------------------------------*/
    
    /**
     * Prüft ob alle Pflichtfelder eingegeben sind und Korrekt sind.
     *
     * @return boolschen Wert ob die eingaben korrekt sind.
     */
    private boolean validateFields() {
        boolean istValidiert = true;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Fehlende Eingaben");

        if (this.tfEinzelwert.getText().isEmpty()) {

            alert.setContentText("Es ist kein Einzelwert eingetragen!");
            alert.showAndWait();

            istValidiert = false;

        } else if (this.tfBestellwert.getText().isEmpty()) {

            alert.setContentText("Es ist kein Bestellwert eingetragen!");
            alert.showAndWait();

            istValidiert = false;

        } else if (this.cbMwstsatz.getValue() == null) {

            alert.setContentText("Bitte den Mehrwertsteuersatz wählen!");
            alert.showAndWait();

            istValidiert = false;

        } else if (this.tfBestandFrei.getText().isEmpty()) {

            alert.setContentText("Bitte geben sie die Bestandsmenge FREI an!");
            alert.showAndWait();

            istValidiert = false;

        } else if (this.tfBestandReserviert.getText().isEmpty()) {

            alert.setContentText("Bitte geben sie die Bestandsmenge"
                    + " RESERVIERT an!");
            alert.showAndWait();

            istValidiert = false;

        } else if (this.tfBestandZulauf.getText().isEmpty()) {

            alert.setContentText("Bitte geben sie die Bestandsmenge "
                    + "ZULAUF an!");
            alert.showAndWait();

            istValidiert = false;

        } else if (this.tfBestandVerkauft.getText().isEmpty()) {

            alert.setContentText("Bitte geben sie die Bestandsmenge"
                    + " VERKAUFT an!");
            alert.showAndWait();

            istValidiert = false;

        } 
        return istValidiert;
    }
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 10.09.2017    GET     Methode erstellt.
    /* 10.09.2017    GET     Getestet & freigegeben 
    /*------------------------------------------------------------------------*/
    
    /**
     * Prüft ob der Einzelwert korrekt eingegeben wurde.
     *
     * @return boolschen Wert ob die eingaben korrekt sind.
     */
    private boolean validateEinzelwert() {
        
        boolean istValidiert = false;
        
        Pattern p = Pattern.compile("[0-9][0-9]*[,|.]?[0-9]*");
        Matcher m = p.matcher(this.tfEinzelwert.getText());

        if (m.find() && m.group().equals(this.tfEinzelwert.getText())) {
            
            istValidiert = true;
            
        } else {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fehlerhafte Einzelwert!");
            alert.setContentText("Der Einzelwert entspricht nicht dem Format "
                    + "(z.B.: 199.99)");
            alert.showAndWait();
        }
        
        
        return istValidiert;
        
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 10.09.2017    GET     Methode erstellt.
    /* 10.09.2017    GET     Getestet & freigegeben 
    /*------------------------------------------------------------------------*/
    
    /**
     * Prüft ob der Bestellwert korrekt eingegeben wurde.
     *
     * @return boolschen Wert ob die eingaben korrekt sind.
     */
    private boolean validateBestellwert() {
        
        boolean istValidiert = false;
        
        Pattern p = Pattern.compile("[0-9][0-9]*[,|.]?[0-9]*");
        Matcher m = p.matcher(this.tfBestellwert.getText());

        if (m.find() && m.group().equals(this.tfBestellwert.getText())) {
            
            istValidiert = true;
            
        } else {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fehlerhafte Bestellwert!");
            alert.setContentText("Der Bestellwert entspricht nicht dem Format "
                    + "(z.B.: 199.99)");
            alert.showAndWait();
        }
        
        
        return istValidiert;
        
    }
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 10.09.2017    GET     Methode erstellt.
    /* 10.09.2017    GET     Getestet & freigegeben 
    */
    /*------------------------------------------------------------------------*/
    
    /**
     * Prüft ob der freie Bestand korrekt eingegeben wurde.
     *
     * @return boolschen Wert ob die eingaben korrekt sind.
     */
    private boolean validateBestandFREI() {
        boolean istValidiert = false;
        
        
        Pattern p = Pattern.compile("[0]|[1-9][0-9]*");
        Matcher m = p.matcher(this.tfBestandFrei.getText());
        
        if (m.find() && m.group().equals(this.tfBestandFrei.getText())) {
            
            istValidiert = true;
            
        } else {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fehlerhafte Bestandsangabe!");
            alert.setContentText("Der Bestand FREI entspricht nicht dem Format "
                    + "(z.B.: 999)");
            alert.showAndWait();
        }
        
        return istValidiert;
    }   
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 10.09.2017    GET     Methode erstellt.
    /* 10.09.2017    GET     Getestet & freigegeben 
    /*------------------------------------------------------------------------*/
    
    /**
     * Prüft ob der reservierte Bestand korrekt eingegeben wurde.
     *
     * @return boolschen Wert ob die eingaben korrekt sind.
     */
    private boolean validateBestandRESERVIERT() {
        boolean istValidiert = false;
        
        
        Pattern p = Pattern.compile("[0]|[1-9][0-9]+");
        Matcher m =  p.matcher(this.tfBestandReserviert.getText());
        
        if (m.find() && m.group().equals(this.tfBestandReserviert.getText())) {
            
            istValidiert = true;
            
        } else {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fehlerhafte Bestandsangabe!");
            alert.setContentText("Der Bestand RESERVIERT entspricht nicht "
                    + "dem Format (z.B.: 999)");
            alert.showAndWait();
        }
        
        return istValidiert;
    }
    

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 10.09.2017    GET     Methode erstellt.
    /* 10.09.2017    GET     Getestet & freigegeben 
    /*------------------------------------------------------------------------*/
    
    /**
     * Prüft ob der im Zulauf befindende Bestand korrekt eingegeben wurde.
     *
     * @return boolschen Wert ob die eingaben korrekt sind.
     */    
    private boolean validateBestandZULAUF() {
        boolean istValidiert = false;
        
        
        Pattern p = Pattern.compile("[0]|[1-9][0-9]+");
        Matcher m =  p.matcher(this.tfBestandZulauf.getText());
        
        if (m.find() && m.group().equals(this.tfBestandZulauf.getText())) {
            
            istValidiert = true;
            
        } else {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fehlerhafte Bestandsangabe!");
            alert.setContentText("Der Bestand ZULAUF entspricht nicht "
                    + "dem Format (z.B.: 999)");
            alert.showAndWait();
        }
        
        return istValidiert;
    }    
    
    /*------------------------------------------------------------------------*/
    /* Datum         Name    Was
    /* 10.09.2017    GET     Methode erstellt.
    /* 10.09.2017    GET     Getestet & freigegeben 
    /*------------------------------------------------------------------------*/
    
    /**
     * Prüft ob der verkaufte Bestand korrekt eingegeben wurde.
     * @return boolschen Wert ob die eingaben korrekt sind.
     */
    private boolean validateBestandVERKAUFT() {
        boolean istValidiert = false;
        
        
        Pattern p = Pattern.compile("[0]|[1-9][0-9]+");
        Matcher m =  p.matcher(this.tfBestandVerkauft.getText());
        
        if (m.find() && m.group().equals(this.tfBestandVerkauft.getText())) {
            
            istValidiert = true;
            
        } else {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fehlerhafte Bestandsangabe!");
            alert.setContentText("Der Bestand VERKAUFT entspricht nicht "
                    + "dem Format (z.B.: 999)");
            alert.showAndWait();
        }
        
        return istValidiert;
    }

}
    
