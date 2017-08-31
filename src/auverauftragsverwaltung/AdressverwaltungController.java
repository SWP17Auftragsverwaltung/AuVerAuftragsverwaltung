/*------------------------------------------------------------------------------
* Klasse: AdressverwaltungController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 14.06.2017    SAM     Angelegt.
* 26.06.2017    GET     Checkstyleprüfung.
* 27.07.2017    BER     Javadoc angepasst.
* 10.08.2017    SAM     closeAdressverwaltung erstellt.
* 10.08.2017    SAM     initialize() ergänzt
* 11.08.2017    HEN     setTableContent() erstellt.
* 11.08.2017    BER     clearTextFields() erstellt.
* 14.08.2017    HEN     alleOhneLKZ(), alleMitLKZ(), zeigeAlleAdressen() 
*                       erstellt.
* 15.08.2017    HEN     refreshTable(), adresseLoeschen() erstellt.
* 16.08.2017    GET     zeigeWerte() erstellt.
* 17.08.2017    GET     adresseAnlegen(), bearbeiteAdresse(), speichere
*                       Aenderung(),  
* 18.08.2017    HEN     adresseSuchen erstellt.
* 20.08.2017    BER     adresseSuchen() an SucheDAO angepasst
* 20.08.2017    GET     Methode: aktionAbbrechen() erstellt.
*-------------------------------------------------------------------------------
 */
package auverauftragsverwaltung;

import Klassen.Meldung;
import Datenbank.AdresseDAO;
import Datenbank.SucheDAO;
import Klassen.Adresse;
import Klassen.MyException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import validierung.NumberTextField;

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

    /**
     * Textfeld für die Eingabe des Namens.
     */
    @FXML
    private TextField tfName;

    /**
     * Textfeld für die Eingabe des Vornamens.
     */
    @FXML
    private TextField tfVorname;

    /**
     * Textfeld für die Eingabe der Telefonnummer.
     */
    @FXML
    private TextField tfTelefon;

    /**
     * Textfeld für die Eingabe der Emailadresse.
     */
    @FXML
    private TextField tfEmail;

    /**
     * Textfeld für die Eingabe der Strasse.
     */
    @FXML
    private TextField tfStrasse;

    /**
     * Textfeld für die Eingabe der Hausnummer.
     */
    @FXML
    private TextField tfHausNr;

    /**
     * Textfeld für die Eingabe der Postleitzahl.
     */
    @FXML
    private TextField tfPlz;

    /**
     * Textfeld für die Eingabe des Wohnortes.
     */
    @FXML
    private TextField tfOrt;

    /**
     * Textfeld für die Eingabe des Staates.
     */
    @FXML
    private TextField tfStaat;

    /**
     * Textfeld für die Eingabe des Erfassungsdatums.
     */
    @FXML
    private TextField tfDatum;

    /**
     * ComboBox für die Auswahl des Suchkriteriums.
     */
    @FXML
    private ComboBox<String> cbSuchfeld = new ComboBox();

    /**
     * Textfeld für die Eingabe des Suchbegriffs.
     */
    @FXML
    private TextField tfSuchbegriff;

    /**
     * Textfeld für die Eingabe der AnschriftID.
     */
    @FXML
    private TextField tfAnschriftID;

    /**
     * ComboBox für die Auswahl der Anrede.
     */
    @FXML
    private ComboBox<String> cbAnrede = new ComboBox();

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

    /**
     * Unsichtbares Pane, um die Eingabe zu verhindern.
     */
    @FXML
    private Pane pane;

    /**
     * Anlegen Button.
     */
    @FXML
    private Button anlegenBT;

    /**
     * Speichern Button.
     */
    @FXML
    private Button speichernBT;

    /**
     * Bearbeiten Button.
     */
    @FXML
    private Button bearbeitenBT;

    /**
     * LöschenButton.
     */
    @FXML
    private Button loeschenBT;

    /**
     * ÜberschriftPane für den Eingabebereich.
     */
    @FXML
    private TitledPane adressdatensatzPane;

    /**
     * Hinzufügen Button.
     */
    @FXML
    private Button hinzufuegenAdresseBT;

    /**
     * Hinzufügen Button.
     */
    @FXML
    private Button btZuruecksetzen;

    /**
     * Suchen Button.
     */
    @FXML
    private Button btSuchen;
    
    /**
     * Abbrechen Button.
     */
    @FXML
    private Button abbrechenBT;

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 10.08.17    SAM     begrenzeTextFeldEingabe, TableColumn werte zugewiesen
    /*------------------------------------------------------------------------*/
    
    /**
     * Initialisiert die Controller-Klasse.
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

        // Name auf 20 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfName, 20);

        // Vorname auf 20 Zeichen begrenzt   
        begrenzeTextFeldEingabe(tfVorname, 20);

        // Telefon auf 20 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfTelefon, 20);

        // E-Mail auf 100 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfEmail, 100);

        // Strasse auf 30 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfStrasse, 30);

        // Hausnummer auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfHausNr, 6);

        // PLZ auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfPlz, 6);

        // Ort auf 30 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfOrt, 30);

        // Staat auf 30 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfStaat, 30);

        // Datum auf 10 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfDatum, 10);

        // Datum auf 10 Zeichen begrenzt
        begrenzeTextFeldEingabe(tfAnschriftID, 6);

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

        cbSuchfeld.getItems().addAll(
                "AnschriftID",
                "Anrede",
                "Name",
                "Vorname",
                "Straße",
                "HausNr",
                "PLZ",
                "Ort",
                "Staat",
                "Tel",
                "Email",
                "ErfDatum");

        cbAnrede.getItems().addAll("Herr", "Frau");
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 10.08.17    SAM     Methode erstellt.
    /* 16.08.17    SAM     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Methode zum Abbrechen der Adressverwaltung.
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen"
     * abfängt.
     */
    @FXML
    public void closeAdressverwaltung(ActionEvent event) {
        Stage stage = (Stage) closeAW.getScene().getWindow();
        stage.close();
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 10.08.17    GET     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
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
    public void setTableContent() throws SQLException {
        AdresseDAO ad = new AdresseDAO();
        ObservableList<Adresse> adressen
            = FXCollections.observableArrayList(ad.gibAlleAdressenOhneLKZ());
        adresseTV.setItems(adressen);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Methode bekommt eine ArrayList mit den gefundenen Adressen übergeben und
     * aktualisiert damit die TableView.
     * @param adressen Übergebene Adresse.
     * @throws java.sql.SQLException SQL Exception
     */
    public void zeigeGefundeneAdressen(ArrayList adressen) throws SQLException {
        refreshTable();
        ObservableList<Adresse> adressenAusgabe
                = FXCollections.observableArrayList(adressen);
        adresseTV.setItems(adressenAusgabe);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Aktualisiert die TableView mit aktuellem Inhalt.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    public void refreshTable() throws SQLException {
        adresseTV.getItems().clear();
        setTableContent();
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 11.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Löscht alle Eingaben in den Textfeldern.
     */
    public void clearTextFields() {
        tfAnschriftID.clear();
        cbAnrede.valueProperty().set(null);
        tfName.clear();
        tfVorname.clear();
        tfStrasse.clear();
        tfHausNr.clear();
        tfPlz.clear();
        tfOrt.clear();
        tfStaat.clear();
        tfTelefon.clear();
        tfEmail.clear();
        tfDatum.clear();
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.08.17    HEN     Methode erstellt.
    /* 16.08.17    HEN     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Adressen mit aktivem LKZ und stellt sie in der Tabelle
     * dar.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    public void alleMitLKZ() throws SQLException {
        AdresseDAO ad = new AdresseDAO();
        ObservableList<Adresse> adressen
                = FXCollections.observableArrayList(ad.gibAlleAdressenMitLKZ());
        adresseTV.setItems(adressen);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.08.17    HEN     Methode erstellt.
    /* 16.08.17    HEN     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Adressen ohne LKZ und stellt sie in der Tabelle dar.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    public void alleOhneLKZ() throws SQLException {
        AdresseDAO ad = new AdresseDAO();
        ObservableList<Adresse> adressen
                = FXCollections.observableArrayList(
                        ad.gibAlleAdressenOhneLKZ());
        adresseTV.setItems(adressen);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    GET     Methode erstellt.
    /* 22.08.17    BER     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt die unteren Eingabefelder für das Anlegen einer neuer Adresse frei.
     * @throws java.sql.SQLException SQLException
     */
    @FXML
    public void adresseAnlegen() throws SQLException {
        adresseTV.setMouseTransparent(true);
        clearTextFields();

        // Textfeldbereich wird aktiviert
        this.pane.setDisable(true);
        // Bearbeiten-Button wird ausgeblendet
        this.anlegenBT.setVisible(false);
        // Speichern-Button wird eingeblendet
        this.hinzufuegenAdresseBT.setVisible(true);
        // Der Anlegemodus des Adressdatensatzes wird aktiviert
        this.adressdatensatzPane.setText(
                "Adressdatensatz (Anlegemodus)");
        // Anlegen-Button wird deaktiviert
        this.bearbeitenBT.setDisable(true);
        // Löschen-Button wird deaktiviert
        this.loeschenBT.setDisable(true);

        AdresseDAO ad = new AdresseDAO();
        tfAnschriftID.setText(ad.generiereID());
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /* 21.08.17    GET     clearTextFields() & refresTable() ergänzt.
    /*                     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues Adress
     * Objekt, welches dann über die DAO in die DB geschrieben wird.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void adresseHinzufuegen() throws SQLException {
        String anschriftID = tfAnschriftID.getText();
        String anrede = cbAnrede.getValue();
        String name = tfName.getText();
        String vorname = tfVorname.getText();
        String strasse = tfStrasse.getText();
        String hausnr = tfHausNr.getText();
        String plz = tfPlz.getText();
        String ort = tfOrt.getText();
        String staat = tfStaat.getText();
        String tel = tfTelefon.getText();
        String email = tfEmail.getText();
        String erfdatum = tfDatum.getText();
        String lkz = "N";
        Adresse adresse = new Adresse(anschriftID, anrede, name, vorname,
                strasse, hausnr, plz, ort, staat, tel, email, erfdatum, lkz);

        AdresseDAO ad = new AdresseDAO();
        ad.fuegeAdresseHinzu(adresse);

        clearTextFields();
        refreshTable();

        // Textfeldbereich wird aktiviert
        this.pane.setDisable(false);
        // Bearbeiten-Button wird ausgeblendet
        this.anlegenBT.setVisible(true);
        // Speichern-Button wird eingeblendet
        this.hinzufuegenAdresseBT.setVisible(false);
        // Der Bearbeitungsmodus des Adressdatensatzes wird aktiviert
        this.adressdatensatzPane.setText("Adressdatensatz");
        // Anlegen-Button wird deaktiviert
        this.bearbeitenBT.setDisable(false);
        // Löschen-Button wird deaktiviert
        this.loeschenBT.setDisable(false);
        adresseTV.setMouseTransparent(false);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    HEN     Methode erstellt.
    /* 22.08.17    BER     refreshTable() ergänzt. Getestet & Freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * "Löscht" eine markierte Adresse, in dem das LKZ auf J gesetzt wird.
     * Aktualisiert anschließend die TableView.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void adresseLoeschen() throws SQLException {
        Object adresse = adresseTV.getSelectionModel().getSelectedItem();
        Adresse b = (Adresse) adresse;

        if (!this.tfAnschriftID.getText().isEmpty()) {
            Meldung meldung = new Meldung();
            meldung.loeschenAbfragen();

            if (meldung.antwort()) {
                AdresseDAO ad = new AdresseDAO();
                ad.setzeLKZ(b);

                refreshTable();

            } else {
                meldung.schließeFenster();
                clearTextFields();
            }
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
    public void bearbeiteAdresse() {
        // Textfeldbereich wird aktiviert
        this.pane.setDisable(true);
        // Bearbeiten-Button wird ausgeblendet
        this.bearbeitenBT.setVisible(false);
        // Speichern-Button wird eingeblendet
        this.speichernBT.setVisible(true);
        // Der Bearbeitungsmodus des Adressdatensatzes wird aktiviert
        this.adressdatensatzPane.setText("Adressdatensatz (Bearbeitungsmodus)");
        // Anlegen-Button wird deaktiviert
        this.anlegenBT.setDisable(true);
        // Löschen-Button wird deaktiviert
        this.loeschenBT.setDisable(true);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 16.08.17    GET     Methode erstellt.
    /* 22.08.17    HEN     Exceptions eingefügt. Getestet & Freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Speichert die gemachten Änderungen in die Datenbank und aktualisiert die
     * View mit den neuen Werten.
     * @throws java.sql.SQLException SQLException.
     * @throws Klassen.MyException Eigene Exception.
     */
    @FXML
    public void speichereAenderung() throws SQLException, MyException {
        String anschriftID = tfAnschriftID.getText();
        String anrede = cbAnrede.getValue();
        String name = tfName.getText();
        String vorname = tfVorname.getText();
        String strasse = tfStrasse.getText();
        String hausnr = tfHausNr.getText();
        String plz = tfPlz.getText();
        String ort = tfOrt.getText();
        String staat = tfStaat.getText();
        String tel = tfTelefon.getText();
        String email = tfEmail.getText();
        String erfdatum = tfDatum.getText();

        if (anrede.isEmpty() || name.isEmpty() || vorname.isEmpty()
                || strasse.isEmpty() || hausnr.isEmpty() || plz.isEmpty()
                || ort.isEmpty() || staat.isEmpty() || tel.isEmpty()
                || email.isEmpty() || erfdatum.isEmpty()) {
            throw new MyException(30);

        } else {
            String lkz = "N";
            Adresse adresse = new Adresse(anschriftID, anrede, name, vorname,
                    strasse, hausnr, plz, ort, staat, tel, email, erfdatum,
                    lkz);

            AdresseDAO aDAO = new AdresseDAO();
            aDAO.aendereAdresse(adresse);

            refreshTable();

            // Textfeldbereich wird deaktivieren
            this.pane.setDisable(false);
            // Bearbeiten-Button wird ausgeblendet
            this.bearbeitenBT.setVisible(true);
            // Speichern-Button wird eingeblendet
            this.speichernBT.setVisible(false);
            // Der Bearbeitungsmodus des Adressdatensatzes wird aktiviert
            this.adressdatensatzPane.setText("Adressdatensatz");
            // Anlegen-Button wird deaktiviert
            this.anlegenBT.setDisable(false);
            // Löschen-Button wird deaktiviert
            this.loeschenBT.setDisable(false);
        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 16.08.17    GET     Methode erstellt.
    /* 18.08.17    BER     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt die Werte einer ausgewählten Adresse im unteren Bereich an.
     */
    @FXML
    public void zeigeWerteAn() {
        Object adresse = adresseTV.getSelectionModel().getSelectedItem();
        Adresse b = (Adresse) adresse;

        if (b != null) {
            this.tfAnschriftID.setText(b.getAdresseID());
            this.cbAnrede.setValue(b.getAnrede());
            this.tfName.setText(b.getName());
            this.tfVorname.setText(b.getVorname());
            this.tfTelefon.setText(b.getTelefon());
            this.tfEmail.setText(b.getEmail());
            this.tfStrasse.setText(b.getStrasse());
            this.tfHausNr.setText(b.getHausnummer());
            this.tfPlz.setText(b.getPlz());
            this.tfOrt.setText(b.getOrt());
            this.tfStaat.setText(b.getStaat());

            this.tfDatum.setText(b.getErfassungsdatum());
        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 18.08.17    HEN     Methode erstellt.
    /* 20.08.17    BER     Getestet & Freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt die Werte einer ausgewählten Adresse im unteren Bereich an.
     * @throws java.sql.SQLException SQLException
     */
    @FXML
    public void adresseSuchen() throws SQLException  {
        SucheDAO sd = new SucheDAO();
        ArrayList gefundeneAdressen;

        String suchkriterium = cbSuchfeld.getValue();
        String suchbegriff = tfSuchbegriff.getText();
      
        gefundeneAdressen = sd.adressSuche(suchkriterium, suchbegriff);
        zeigeGefundeneAdressen(gefundeneAdressen);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    HEN     Methode erstellt.
    /* 22.08.17    HEN     Suchfeld aktualisiert, setTableContent() eingefügt.
    /*                     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Setzt die Suche zurück.
     * @throws java.sql.SQLException SQLException
     */
    @FXML
    public void setzeSucheZurueck() throws SQLException {
        this.tfSuchbegriff.setText("");
        this.cbSuchfeld.setValue(null);
        setTableContent();
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 20.08.17    GET     Methode erstellt.
    /* 27.08.17    GET     Getestet & freigegeben 
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt dem Benutzer die Möglichkeit, seine Aktionen abzubrechen oder zu
     * bestätigen.
     */    
    @FXML
    public void aktionAbbrechen() {
        if (!this.adressdatensatzPane.getText().equalsIgnoreCase(
            "Adressdatensatz")) {

            Meldung meldung = new Meldung();
            meldung.verwerfenFenster();

            if (meldung.antwort()) {

                // Textfeldbereich wird aktiviert
                this.pane.setDisable(false);
                // Bearbeiten-Button wird ausgeblendet
                this.anlegenBT.setVisible(true);
                // Speichern-Button wird eingeblendet

                // Der Bearbeitungsmodus des Adressdatensatzes wird aktiviert
                this.adressdatensatzPane.setText("Adressdatensatz");

                // Anlegen-Button wird deaktiviert
                this.bearbeitenBT.setDisable(false);

                this.bearbeitenBT.setVisible(true);

                this.speichernBT.setVisible(false);

                this.anlegenBT.setDisable(false);
                // Löschen-Button wird deaktiviert
                this.loeschenBT.setDisable(false);

                this.hinzufuegenAdresseBT.setVisible(false);

                this.adresseTV.setMouseTransparent(false);

                clearTextFields();
            
            } else {
                meldung.schließeFenster();
            }
        }
    }

    
    
}
