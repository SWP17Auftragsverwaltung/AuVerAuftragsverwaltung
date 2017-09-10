/*------------------------------------------------------------------------------
* Klasse: GeschaeftspartnerverwaltungController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 15.06.2017    SAM     Klasse angelegt und closeGeschaeftspartner() erstellt.
* 26.06.2017    GET     begrenzeTextFeldEingabe() implemenitert.
* 27.07.2017    BER     Javadoc angepasst.
* 14.08.2017    HEN     setTabelContent() erstellt, TableColums 
*                       zeigeGefundeneAdressen() erstellt.
* 17.08.2017    SAM     initialize() erweitert.
* 19.08.2017    HEN     Methoden alleMitLKZ(), alleOhneLKZ()erstellt.
* 20.08.2017    HEN     geschaeftspartnerAnlegen(),geschaeftspartnerHinzufügen()
*                       clearTextField() refreshTable() erstellt.
* 21.08.2017    GET     bearbeiteGeschäftspartner(), speichereAenderung()
*                       implimentiert.
* 22.08.2017    GET     zeigeWerteAn() und aktionAbbrechen() implementiert,
*                       FXML-Datei Buttons angeordnet.
* 22.08.2017    BER     geschaeftspartnerLoeschen(), geschaeftspartnerSuchen()
*                       setzeSucheZurueck() erstellt.    
*-------------------------------------------------------------------------------
 */
package auverauftragsverwaltung;

import Datenbank.AdresseDAO;
import Klassen.Meldung;
import Datenbank.GeschaeftspartnerDAO;
import Datenbank.SucheDAO;
import Klassen.Adresse;
import Klassen.Geschaeftspartner;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller der Geschäftspartner die für die Darstellung der FXML Datei 
 * zuständig ist.
 * 
 * @author Mudimbi
 */
public class GeschaeftspartnerverwaltungController implements Initializable {

    /**
     * Der Zurück-Button der Geschäftspartnerverwaltung
     */
    @FXML
    private Button closeGP;
    
    /**
     * Textfeld "LieferID".
     */
    @FXML
    private TextField tfLieferID;
    
    /**
     * Textfeld "AnschriftID".
     */
    @FXML
    private TextField tfAnschriftID;
    
    /**
     * ComboBox "Suchfeld".
     */
    @FXML
    private ComboBox<String> cbSuchfeld = new ComboBox();

     /**
     * Textfeld "Suchbegriff".
     */
    @FXML
    private TextField tfSuchbegriff;
    
    /**
     * Kreditlimit des Geschäftspartners.
     */
    @FXML
    private TextField tfKreditlimit;
    
    /**
     * PartnerTyp des Geschäftspartners.
     */
    @FXML
    private ComboBox<String> cbPartnerTyp = new ComboBox();
    
    /**
     * ID des Geschäftspartners.
     */
    @FXML
    private TextField tfGeschaeftspartnerID;

    /**
     * Tabellenspalte "GeschäftspartnerID".
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> gpID;

    /**
     * Tabellenspalte "Typ".
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> gpTyp;

    /**
     * Tabellenspalte "AdresseID".
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> adressID;

    /**
     * Tabellenspalte "LieferID".
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> liefID;

    /**
     * Tabellenspalte "Kreditlimit".
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> kredLimit;
    
     /**
     * Pane die die Eingabe ausserhalb des Bearbeitungsmodus und Anelgemodus
     * sperrt.
     */
    @FXML
    private Pane pane;

    /**
     * Button "Hinzufügen".
     */
    @FXML
    private Button hinzufuegenBT;

    /**
     * Button "Anlegen".
     */
    @FXML
    private Button anlegenBT;

    /**
     * Button "Bearbeiten".
     */
    @FXML
    private Button bearbeitenBT;

    /**
     * Button "Speichern".
     */
    @FXML
    private Button speichernBT;

    /**
     * Button "Löschen".
     */
    @FXML
    private Button loeschenBT;

    /**
     * TitledPane "Datensatz".
     */
    @FXML
    private TitledPane datensatzTP;

    /**
     * Geschäftspartnertabelle.
     */
    @FXML
    private TableView gpTable = new TableView<Geschaeftspartner>();
    
    /**
     * TableView für die Anzeige der Adressen.
     */
    @FXML
    private TableView adresseTV = new TableView<Adresse>();

    /**
     * Tabellenspalte "AnschriftID".
     */
    @FXML
    private TableColumn<Adresse, String> tcAnschriftID;

    /**
     * Tabellenspalte "Anrede".
     */
    @FXML
    private TableColumn<Adresse, String> tcAnrede;

    /**
     * Tabellenspalte "Name".
     */
    @FXML
    private TableColumn<Adresse, String> tcName;

    /**
     * Tabellenspalte "Vorname".
     */
    @FXML
    private TableColumn<Adresse, String> tcVorname;

    /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Adresse, String> tcStraße;

    /**
     * Tabellenspalte "HausNr".
     */
    @FXML
    private TableColumn<Adresse, String> tcHausNr;

    /**
     * Tabellenspalte "PLZ".
     */
    @FXML
    private TableColumn<Adresse, String> tcPLZ;

    /**
     * Tabellenspalte "Ort".
     */
    @FXML
    private TableColumn<Adresse, String> tcOrt;

    /**
     * Tabellenspalte "Staat".
     */
    @FXML
    private TableColumn<Adresse, String> tcStaat;

    /**
     * Tabellenspalte "Tel".
     */
    @FXML
    private TableColumn<Adresse, String> tcTel;

    /**
     * Tabellenspalte "Email".
     */
    @FXML
    private TableColumn<Adresse, String> tcEMail;

    /**
     * Tabellenspalte "erfDatum".
     */
    @FXML
    private TableColumn<Adresse, String> tcErfDatum;

    /**
     * Tabellenspalte LKZ.
     */
    @FXML
    private TableColumn<Adresse, String> tcLKZ;
    
    @FXML
    private TitledPane paneAdresseWahl;

    
    
    
    
    
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    SAM     Methode erstellt.
    /* 16.08.17    SAM     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/

    /**
     * Methode zum Schließen der Geschäftspartnerverwaltung durch den Button
     * Abbrechen.
     *
     * @param event ActionEvent zur Prüfung ob der Abbrechen-Button getätigt
     * wurde
     */
    @FXML
    public void closeGeschaeftspartner(ActionEvent event) {
        Stage stage = (Stage) closeGP.getScene().getWindow();
        stage.close();
    }
    


/*------------------------------------------------------------------------*
     * Datum       Name    Was
     * 17.08.17    SAM     Methode erstellt.
     *------------------------------------------------------------------------*/    
    
    /**
     * Initialisiert die Controller-Klasse.
     *
     * @param url Zu initialisierende URL.
     * @param rb Verwendete Ressourcen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.paneAdresseWahl.setVisible(false);
        
        try {
            setTableContent();
        } catch (SQLException ex) {
            Logger.getLogger(AdressverwaltungController.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        //Die Geschäftspartner-ID enthält max. 6 Zeichen.        
        begrenzeTextFeldEingabe(tfGeschaeftspartnerID, 6);
        //Die Anschrift-ID enthält max. 6 Zeichen.
        begrenzeTextFeldEingabe(tfAnschriftID, 6);
        //Die Liefer-Id enthält max. 6 Zeichen.
        begrenzeTextFeldEingabe(tfLieferID, 6);
        //Das Kreditlimit enthält max. 6 Zeichen.
        begrenzeTextFeldEingabe(tfKreditlimit, 6);

        gpID.setCellValueFactory(
                new PropertyValueFactory<>("geschaeftspartnerID"));
        gpTyp.setCellValueFactory(new PropertyValueFactory<>("typ"));
        adressID.setCellValueFactory(new PropertyValueFactory<>("adresseID"));
        liefID.setCellValueFactory(new PropertyValueFactory<>("lieferID"));
        kredLimit.setCellValueFactory(
                new PropertyValueFactory<>("kreditlimit"));
        
        
        
        

        cbSuchfeld.getItems().addAll(
                "Geschaeftspartner-ID",
                "Geschäftspartner-Typ",
                "Anschrift-ID",
                "Liefer-ID",
                "Kreditlimit");

        cbPartnerTyp.getItems().addAll("K", "L");
        
        
        // Adresse TableView -> TableColums werden geladen.
        tcAnschriftID.setCellValueFactory(
                new PropertyValueFactory<>("adresseID"));

        tcAnrede.setCellValueFactory(new PropertyValueFactory<>("anrede"));

        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));

        tcVorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));

        tcStraße.setCellValueFactory(new PropertyValueFactory<>("strasse"));

        tcHausNr.setCellValueFactory(
                new PropertyValueFactory<>("hausnummer"));

        tcPLZ.setCellValueFactory(new PropertyValueFactory<>("plz"));

        tcOrt.setCellValueFactory(new PropertyValueFactory<>("ort"));

        tcStaat.setCellValueFactory(
                new PropertyValueFactory<>("staat"));

        tcTel.setCellValueFactory(
                new PropertyValueFactory<>("telefon"));

        tcEMail.setCellValueFactory(new PropertyValueFactory<>("Email"));

        tcErfDatum.setCellValueFactory(
                new PropertyValueFactory<>("erfassungsdatum"));

        tcLKZ.setCellValueFactory(
                new PropertyValueFactory<>("lkz"));
        
    }
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 26.07.17    GET     Methode erstellt.
    /* 27.07.17    GET     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/

    /**
     * Methode zum begrenzen der Anzahl der Zeichen, die in ein Textfeld
     * eingetragen werden können.
     *
     * @param tf Textfeld welches begrenzt wird.
     * @param zahl Anzahl Zeichen.
     */
    private void begrenzeTextFeldEingabe(TextField tf, int zahl) {

        tf.setTextFormatter(new TextFormatter<>(change
            -> change.getControlNewText().length() <= zahl
                        ? change : null));
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.08.17    HEN     Methode erstellt.
    /* 19.08.17    HEN     Methoden "alleOhneLKZ" & "alleMitLKZ" hinzugefügt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein GeschäftspartnerDAO Objekt, gibt eine GP ArrayList an eine
     * OberservableList, die dann an die TableView übergeben wird.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    public void setTableContent() throws SQLException {
        GeschaeftspartnerDAO gp = new GeschaeftspartnerDAO();
        ObservableList<Geschaeftspartner> geschaeftspartner
                = FXCollections.observableArrayList(
                        gp.gibAlleGeschaeftspartnerOhneLKZ());
        gpTable.setItems(geschaeftspartner);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    CEL     Methode erstellt.
    /* 19.08.17    HEN     Methode überarbeitet und getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Geschätspartnern mit aktivem LKZ und stellt sie in der
     * Tabelle dar.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    public void alleOhneLKZ() throws SQLException {
        GeschaeftspartnerDAO gp = new GeschaeftspartnerDAO();
        ObservableList<Geschaeftspartner> geschaeftspartner
                = FXCollections.observableArrayList(
                        gp.gibAlleGeschaeftspartnerOhneLKZ());
        gpTable.setItems(geschaeftspartner);
    }

    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    HEN     Methode erstellt und fertiggestellt. 
    /*                     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Geschätspartnern mit aktivem LKZ und stellt sie in der
     * Tabelle dar.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    public void alleMitLKZ() throws SQLException {
        GeschaeftspartnerDAO gp = new GeschaeftspartnerDAO();
        ObservableList<Geschaeftspartner> geschaeftspartner
                = FXCollections.observableArrayList(
                        gp.gibAlleGeschaeftspartnerMitLKZ());
        gpTable.setItems(geschaeftspartner);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 20.08.17    HEN     Methode erstellt und fertiggestellt. 
    /*                     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/

    /**
     * Ermöglicht das Eingeben des Datensatzes für das Hinzufügen eines
     * Geschäftspartners.
     *
     * @throws java.sql.SQLException SQLException
     */
    public void geschaeftspartnerAnlegen() throws SQLException {

        gpTable.setMouseTransparent(true);
        clearTextFields();

        this.pane.setDisable(true);

        this.anlegenBT.setVisible(false);

        this.hinzufuegenBT.setVisible(true);

        this.datensatzTP.setText("Geschäftspartnerdatensatz (Anlegemodus)");

        this.bearbeitenBT.setDisable(true);

        this.loeschenBT.setDisable(true);

        GeschaeftspartnerDAO gpDAO = new GeschaeftspartnerDAO();
        this.tfGeschaeftspartnerID.setText(gpDAO.generiereID());
        
        this.paneAdresseWahl.setVisible(true);
        this.gpTable.setVisible(false);
        
        setTableContentAdresse();
        
        
        
        
        
      
        

    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 20.08.17    HEN     Methode erstellt und fertiggestellt. 
    /*                     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/

    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues
     * Geschätspartner Objekt, welches dann über die DAO in die DB geschrieben
     * wird.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    public void geschaeftspartnerHinzufuegen() throws SQLException {
        
        if (validateFields()) {
            
            String geschaeftspartnerID = tfGeschaeftspartnerID.getText();
            String typ = cbPartnerTyp.getValue();
            String adresseID = tfAnschriftID.getText();
            String lieferID = tfLieferID.getText();
            String kreditlimit = tfKreditlimit.getText();
            String lkz = "N";
            Geschaeftspartner geschaeftspartner = new Geschaeftspartner(
                    geschaeftspartnerID, typ, adresseID, lieferID,
                    kreditlimit, lkz);

            GeschaeftspartnerDAO gpDAO = new GeschaeftspartnerDAO();
            gpDAO.fuegeGeschaeftspartnerHinzu(geschaeftspartner);

            clearTextFields();
            refreshTable();

            // Sperre für Bearbeitung wird deaktiviert.
            this.pane.setDisable(false);
            // Anlegen Button wird Sichtbar gemacht.
            this.anlegenBT.setVisible(true);
            // Hinzufügen -Button wird eingeblendet
            this.hinzufuegenBT.setVisible(false);
            // Der Anlegemodus wird dektiviert
            this.datensatzTP.setText("Geschäftspartnerdatensatz");
            // Anlegen-Button wird aktiviert
            this.bearbeitenBT.setDisable(false);
            // Löschen-Button wird aktiviert
            this.loeschenBT.setDisable(false);

            gpTable.setMouseTransparent(false);
            this.paneAdresseWahl.setVisible(false);
            this.gpTable.setVisible(true);
        }
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 20.08.17    HEN     Methode erstellt und fertiggestellt. 
    /*                     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/

    /**
     * Löscht alle Eingaben in den Textfeldern.
     */
    public void clearTextFields() {

        this.tfGeschaeftspartnerID.clear();
        this.cbPartnerTyp.setValue("Bitte wählen...");
        this.tfAnschriftID.clear();
        this.tfLieferID.clear();
        this.tfKreditlimit.clear();

    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 20.08.17    HEN     Methode erstellt und fertiggestellt. 
    /*                     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/

    /**
     * Aktualisiert die TableView mit aktuellem Inhalt.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    public void refreshTable() throws SQLException {
        this.gpTable.getItems().clear();
        setTableContent();
    }

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 18.08.17    CEL     Methode erstellt.
    /* 22.08.17    BER     Methode überarbeitet - getestet & freigegeben
    /*------------------------------------------------------------------------*/
    
    /**
     * "Löscht" einen markierten Geschäftspartner, in dem das LKZ auf J gesetzt
     * wird.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void geschaeftspartnerLoeschen() throws SQLException {

        Object geschaeftspartner = 
                gpTable.getSelectionModel().getSelectedItem();
        Geschaeftspartner g = (Geschaeftspartner) geschaeftspartner;

        if (!this.tfGeschaeftspartnerID.getText().isEmpty()) {
            Meldung meldung = new Meldung();
            meldung.loeschenAbfragen();

            if (meldung.antwort()) {
                GeschaeftspartnerDAO gp = new GeschaeftspartnerDAO();
                gp.setzeLKZ(g);

                refreshTable();
            } else {
                meldung.schließeFenster();
                clearTextFields();
            }
        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 21.08.17    GET     Methode erstellt.
    /* 22.08.17    HEN     Adressdatenpane geändert. Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Lässt das Bearbeiten eines ausgewählten Geschäftspartners zu.
     * @throws java.sql.SQLException SQLException
     */
    @FXML
    public void bearbeiteGeschaeftspartner() throws SQLException {

        if (!this.tfGeschaeftspartnerID.getText().isEmpty()) {
            // Textfeldbereich wird aktiviert
            this.pane.setDisable(true);
            // Bearbeiten-Button wird ausgeblendet
            this.bearbeitenBT.setVisible(false);
            // Speichern-Button wird eingeblendet
            this.speichernBT.setVisible(true);
            // Der Bearbeitungsmodus des Adressdatensatzes wird aktiviert
            this.datensatzTP.setText("Geschäftspartnerdatensatz"
                    + " (Bearbeitungsmodus)");
            // Anlegen-Button wird deaktiviert
            this.anlegenBT.setDisable(true);
            // Löschen-Button wird deaktiviert
            this.loeschenBT.setDisable(true);

        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 21.08.17    GET     Methode erstellt.
    /* 22.08.17    HEN     Exceptions eingefügt. Getestet & Freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Speichert die gemachten Änderungen in die Datenbank und aktualisiert die
     * View mit den neuen Werten.
     * @throws java.sql.SQLException SQLException.
     */
    @FXML
    public void speichereAenderung() throws SQLException {

        String geschaeftspartnerID = this.tfGeschaeftspartnerID.getText();
        String typ = this.cbPartnerTyp.getValue();
        String anschriftID = this.tfAnschriftID.getText();
        String lieferID = this.tfLieferID.getText();
        String kreditlimit = this.tfKreditlimit.getText();
        String lkz = "N";

        Geschaeftspartner gp = new Geschaeftspartner(
                geschaeftspartnerID, typ, anschriftID, lieferID, 
                kreditlimit, lkz);

        GeschaeftspartnerDAO gpDAO = new GeschaeftspartnerDAO();
        gpDAO.aendernGeschaeftspartner(gp);

        refreshTable();

        // Textfeldbereich wird deaktivieren
        this.pane.setDisable(false);
        // Bearbeiten-Button wird ausgeblendet
        this.bearbeitenBT.setVisible(true);
        // Speichern-Button wird eingeblendet
        this.speichernBT.setVisible(false);
        // Der Bearbeitungsmodus des Adressdatensatzes wird aktiviert
        this.datensatzTP.setText("Geschäftspartnerdatensatz");
        // Anlegen-Button wird deaktiviert
        this.anlegenBT.setDisable(false);
        // Löschen-Button wird deaktiviert
        this.loeschenBT.setDisable(false);

    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 22.08.17    GET     Methode erstellt.
    /* 22.08.17    HEN     Exceptions eingefügt. Getestet & Freigegeben.
    /*------------------------------------------------------------------------*/

    /**
     * Zeigt die Werte einer ausgewählten Adresse im unteren Bereich an.
     */
    @FXML
    public void zeigeWerteAn() {
        Object geschaeftspartner = 
                gpTable.getSelectionModel().getSelectedItem();
        Geschaeftspartner b = (Geschaeftspartner) geschaeftspartner;

        if (b != null) {
            this.tfGeschaeftspartnerID.setText(b.getGeschaeftspartnerID());
            this.cbPartnerTyp.setValue(b.getTyp());
            this.tfAnschriftID.setText(b.getAdresseID());
            this.tfLieferID.setText(b.getLieferID());
            this.tfKreditlimit.setText(b.getKreditlimit());
        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 22.08.17    BER     Methode erstellt.
    /* 22.08.17    BER     An SucheDAO angepasst. Getestet & Freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt die Werte eines ausgewählten Geschäftspartners im unteren 
     * Bereich an.
     * @throws java.sql.SQLException SQLException
     */
    @FXML
    public void geschaeftspartnerSuchen() throws SQLException {

        SucheDAO gpDAO = new SucheDAO();
        ArrayList gefundeneGeschaeftspartner;

        String suchkriterium = this.cbSuchfeld.getValue();
        String suchbegriff = this.tfSuchbegriff.getText();

        gefundeneGeschaeftspartner = gpDAO.geschaeftspartnerSuche(
                suchkriterium, suchbegriff);

        zeigeGefundeneGeschaeftspartner(gefundeneGeschaeftspartner);

    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.08.17    HEN     Methode erstellt. - getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Methode bekommt eine ArrayList mit den gefundenen Geschäftspartner
     * übergeben und aktualisiert damit die TableView.
     * @param geschaeftspartner Übergebener Geschäftspartner.
     * @throws java.sql.SQLException SQL Exception
     */
    public void zeigeGefundeneGeschaeftspartner(
            ArrayList geschaeftspartner) throws SQLException {
        refreshTable();
        ObservableList<Geschaeftspartner> geschaeftspartnerAusgabe
                = FXCollections.observableArrayList(geschaeftspartner);
        gpTable.setItems(geschaeftspartnerAusgabe);
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 22.08.17    BER     Methode erstellt.
    /* 22.08.17    BER     Getestet & Freigegeben.
    /*------------------------------------------------------------------------*/

     /**
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
    /* 22.08.17    SEZ     Methode erstellt.
    /* 22.08.17    SEZ     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt dem Benutzer die Möglichkeit, seine Aktionen abzubrechen oder zu
     * bestätigen.
     */
    @FXML
    public void aktionAbbrechen() {

        if (!this.datensatzTP.getText().equalsIgnoreCase(
                "Geschäftspartnerdatensatz")) {
            Meldung meldung = new Meldung();
            meldung.verwerfenFenster();

            if (meldung.antwort()) {

                // Textfeldbereich wird aktiviert
                this.pane.setDisable(false);
                // Bearbeiten-Button wird ausgeblendet
                this.anlegenBT.setVisible(true);
                // Speichern-Button wird eingeblendet

                // Der Bearbeitungsmodus des Adressdatensatzes wird aktiviert
                this.datensatzTP.setText("Geschäftspartnerdatensatz");

                // Anlegen-Button wird deaktiviert
                this.bearbeitenBT.setDisable(false);

                this.bearbeitenBT.setVisible(true);

                this.speichernBT.setVisible(false);

                this.anlegenBT.setDisable(false);
                // Löschen-Button wird deaktiviert
                this.loeschenBT.setDisable(false);

                this.hinzufuegenBT.setVisible(false);
                gpTable.setMouseTransparent(false);

                clearTextFields();
            } else {

                meldung.schließeFenster();

            }

        }
    }
    
    public void setTableContentAdresse() throws SQLException {
        AdresseDAO gpd = new AdresseDAO();
        ObservableList<Adresse> geschaeftspartner
            = FXCollections.observableArrayList(
                    gpd.gibAlleAdressenOhneLKZ());
        adresseTV.setItems(geschaeftspartner);
    } 
    
    /**
     * Füllt das untere AdressID Feld mit einer ID, die in der 
     * Tabelle ausgewählt wurde.
     */
    @FXML
    public void waehleAnschriftID() {
        Object geschaeftspartner 
            = this.adresseTV.getSelectionModel().getSelectedItem();
        Adresse a = (Adresse) geschaeftspartner;

        if (a != null) {
            this.tfAnschriftID.setText(a.getAdresseID());        
        }
    }
    @FXML
    public void waehleLieferID() {
        Object geschaeftspartner 
            = this.adresseTV.getSelectionModel().getSelectedItem();
        Adresse a = (Adresse) geschaeftspartner;

        if (a != null) {
            this.tfLieferID.setText(a.getAdresseID());        
        }
    }
    
    private boolean validateFields(){
        
        boolean istValidiert = true;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Fehlende Eingaben");

        if (this.cbPartnerTyp.getValue().equals("Bitte wählen...")) {

            alert.setContentText("Bitte wählen Sie ein Geschäftspartertyp!");
            alert.showAndWait();

            istValidiert = false;

        } else if (this.tfAnschriftID.getText().isEmpty()) {

            alert.setContentText("Bitte wählen Sie eine Anschrift-ID!"
                    + " \n\nMarkieren Sie eine Anschrift und klicken dann"
                    + " in das Feld damit die ID geladen wird.");
            alert.showAndWait();

            istValidiert = false;

        } else if (this.tfLieferID.getText().isEmpty()) {

            alert.setContentText("Bitte wählen Sie eine Liefer-ID!"
                    + " \n\nMarkieren Sie eine Anschrift und klicken dann"
                    + " in das Feld damit die ID geladen wird.");
            alert.showAndWait();

            istValidiert = false;

        } else if (this.tfKreditlimit.getText().isEmpty()) {

            alert.setContentText("Bitte geben Sie ein Kreditlimit an!");
            alert.showAndWait();

            istValidiert = false;

        } 
        return istValidiert;
        
    }
    
    

}
