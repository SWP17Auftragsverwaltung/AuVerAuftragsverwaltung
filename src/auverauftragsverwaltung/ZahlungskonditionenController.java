/*------------------------------------------------------------------------------
* Klasse: ZahlungskonditionenController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 15.06.17  SAM     Angelegt und closeZahlungskonditionen() erstellt.
* 26.06.17  GET     Checkstyleprüfung.
* 27.07.17  BER     Javadoc angepasst.
* 15.08.17  HEN     setTableContent(),clearFields, refreshTable() implementiert.
* 16.08.17  HEN     alleMitLKZ(), alleOhneLKZ() erstellt.
* 18.08.17  GET     begrenzeTextFeldEingabe()
*                   zeigeGefundeneZahlungskonditionen()
* 19.08.17  GET     bearbeiteZahlungskonditionen(), speicherAenderung()
*                   zahlungskoniditionenAnlegen(), 
*                   zahlungskonditionenHinzufuegen() implementiert.
* 20.08.17  GET     zeigeWertAn() erstellt 
* 21.08.17  BER     zahlungskonditionenLoeschen(), zahlungskonditionenSuchen()
*                    implementiert.
* 23.08.17  SEZ     aktionAbbrechen(), setzeSucheZurück() erstellt.
*-------------------------------------------------------------------------------
*/
package auverauftragsverwaltung;

import Klassen.Meldung;
import Datenbank.SucheDAO;
import Datenbank.ZahlungskonditionenDAO;
import Klassen.Zahlungskonditionen;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 * Controller-Klasse der Zahlungskonditionsverwaltungs View.
 *
 * @author Mudimbi
 */
public class ZahlungskonditionenController implements Initializable {

    /**
     * Zurück-Button der Zahlungskonditionverwaltung.
     */
    @FXML
    private Button closeZK;

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
     * Dieses Textfeld dient zur Eingabe der Zahlungskonditions-ID.
     */
    @FXML
    private TextField tfZahlungskonditionsID;

    /**
     * Diese ComboBox dient zur Auswahl der Auftragsarten.
     */
    @FXML
    private ComboBox<String> cbAuftragsart = new ComboBox();

    /**
     * Dieses Textfeld dient zur Eingabe des Zeitraums fuer den Liefertermin bei
     * Sofortauftraegen.
     */
    @FXML
    private TextField tfLieferzeitSOFORT;

    /**
     * Dieses Textfeld dient zur Eingabe des Zeitraums nachdem ein
     * Wunschlieferdatum angelegt werden kann.
     */
    @FXML
    private TextField tfSperrzeitWUNSCH;

    /**
     * Dieses Textfeld dient zur Eingabe der Tage fuer die Mahnstufe 1.
     */
    @FXML
    private TextField tfMahnzeit1;

    /**
     * Dieses Textfeld dient zur Eingabe der Tage fuer die Mahnstufe 2.
     */
    @FXML
    private TextField tfMahnzeit2;

    /**
     * Dieses Textfeld dient zur Eingabe der Tage fuer die Mahnstufe 3.
     */
    @FXML
    private TextField tfMahnzeit3;

    /**
     * Dieses Textfeld dient zur Eingabe des Zeitraums für Skontoabzug1.
     */
    @FXML
    private TextField tfSkontozeit1;

    /**
     * Dieses Textfeld dient zur Eingabe des Prozentsatzes für Skontozeitraum 1.
     */
    @FXML
    private TextField tfSkonto1;

    /**
     * Dieses Textfeld dient zur Eingabe des Zeitraums für Skontoabzug2.
     */
    @FXML
    private TextField tfSkontozeit2;

    /**
     * Dieses Textfeld dient zur Eingabe des Prozentsatzes für Skontozeitraum 2.
     */
    @FXML
    private TextField tfSkonto2;

    /**
     * Diese TableView dient der Anzeige der Zahlungskonditionen.
     */
    @FXML
    private TableView zahlungskonditionenTV
            = new TableView<Zahlungskonditionen>();

    /**
     * Das ist die Tabellenspalte fuer die KonditionenID.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> tcKonditionenID;

    /**
     * Das ist die Tabellenspalte fuer die Auftragsart.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> tcAuftragsart;

    /**
     * Das ist die Tabellenspalte fuer die Lieferzeit.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> tcLieferzeitSOFORT;

    /**
     * Das ist die Tabellenspalte fuer die Sperrzeit.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> tcSperrzeitWUNSCH;

    /**
     * Das ist die Tabellenspalte fuer die Skontozeit1.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> tcSkontozeit1;

    /**
     * Das ist die Tabellenspalte fuer das Skonto1.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> tcSkonto1;

    /**
     * Das ist die Tabellenspalte fuer die Skontozeit2.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> tcSkontozeit2;

    /**
     * Das ist die Tabellenspalte fuer das Skonto2.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> tcSkonto2;

    /**
     * Das ist die Tabellenspalte fuer die Mahnzeit1.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> tcMahnzeit1;

    /**
     * Das ist die Tabellenspalte fuer die Mahnzeit2.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> tcMahnzeit2;

    /**
     * Das ist die Tabellenspalte fuer die Mahnzeit3.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> tcMahnzeit3;

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
     * Zahlungskonditionen-Suchen Button.
     */
    @FXML
    private Button zkSuchenBT;

    /**
     * Zahlungskonditionen-Suchen Button.
     */
    @FXML
    private Button sucheZuruecksetzenBT;

    /**
     * ÜberschriftPane für den Eingabebereich.
     */
    @FXML
    private TitledPane zahlungskonditionendatensatzPane;

    /**
     * Hinzufügen Button.
     */
    @FXML
    private Button hinzufuegenZahlungskonditionenBT;
    
     /**
     * Abbrechen Button.
     */
    @FXML
    private Button abbrechenBT;

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    SAM     Methode erstellt.
    /* 17.08.17    SAM     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Durch betätigen der Schaltfläche "Abbrechen" soll das Fenster der
     * Zahlungskonditionsverwaltung geschlossen werden.
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen"
     * abfängt.
     */
    @FXML
    public void closeZahlungskondition(ActionEvent event) {
        Stage stage = (Stage) closeZK.getScene().getWindow();
        stage.close();
    }

    
    
    /*------------------------------------------------------------------------*
     * Datum       Name    Was
     * 17.08.17    SAM     Methode erstellt.
     * 19.08.17    GET     Startinitialisierungen für Comboboxen und TableView
     *                     eingefügt
     *------------------------------------------------------------------------*/
    
    /**
     * Initialisiert die Controller-Klasse.
     * @param url URL zur initialisierung.
     * @param rb Resourcen die geladen werden sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setTableContent();
            this.bearbeitenBT.setDisable(true);
            this.loeschenBT.setDisable(true);
            this.abbrechenBT.setDisable(true);

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText("Keine Zahlungskonditionen gefunden!");
            alert.showAndWait();
        }

        //Die Eingabe im Textfeld wird auf 6 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tfZahlungskonditionsID, 6);

        //Die Eingabe im Textfeld wird auf 4 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tfLieferzeitSOFORT, 4);

        //Die Eingabe im Textfeld wird auf 4 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tfSperrzeitWUNSCH, 4);

        //Die Eingabe im Textfeld wird auf 4 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tfMahnzeit1, 4);

        ///Die Eingabe im Textfeld wird auf 4 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tfMahnzeit2, 4);

        //Die Eingabe im Textfeld wird auf 4 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tfMahnzeit3, 4);

        //Die Eingabe im Textfeld wird auf 4 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tfSkontozeit1, 4);

        //Die Eingabe im Textfeld wird auf 3 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tfSkonto1, 3);

        //Die Eingabe im Textfeld wird auf 4 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tfSkontozeit2, 4);

        //Die Eingabe im Textfeld wird auf 3 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tfSkonto2, 3);

        tcKonditionenID.setCellValueFactory(
                new PropertyValueFactory<>("ZahlungskonditionenID"));
        tcAuftragsart.setCellValueFactory(
                new PropertyValueFactory<>("Auftragsart"));
        tcLieferzeitSOFORT.setCellValueFactory(
                new PropertyValueFactory<>("LieferzeitSOFORT"));
        tcSperrzeitWUNSCH.setCellValueFactory(
                new PropertyValueFactory<>("SperrzeitWUNSCH"));
        tcSkontozeit1.setCellValueFactory(
                new PropertyValueFactory<>("Skontozeit1"));
        tcSkonto1.setCellValueFactory(
                new PropertyValueFactory<>("Skonto1"));
        tcSkontozeit2.setCellValueFactory(
                new PropertyValueFactory<>("Skontozeit2"));
        tcSkonto2.setCellValueFactory(
                new PropertyValueFactory<>("Skonto2"));
        tcMahnzeit1.setCellValueFactory(
                new PropertyValueFactory<>("Mahnzeit1"));
        tcMahnzeit2.setCellValueFactory(
                new PropertyValueFactory<>("Mahnzeit2"));
        tcMahnzeit3.setCellValueFactory(
                new PropertyValueFactory<>("Mahnzeit3"));

        cbSuchfeld.getItems().addAll(
                "Konditionen-ID",
                "Auftragsart",
                "LieferzeitSOFORT",
                "SperrzeitWUNSCH",
                "Skontozeit 1",
                "Skonto 1",
                "Skontozeit 2",
                "Skonto 2",
                "Mahnzeit 1",
                "Mahnzeit 2",
                "Mahnzeit 3");

        cbAuftragsart.getItems().addAll("Barauftrag",
                "Sofortauftrag",
                "Terminauftrag",
                "Bestellauftrag");
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 18.08.17    GET     Methode erstellt.
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
    /* 15.08.17    HEN     Methode erstellt.
    /* 16.08.17    HEN     Methode überarbeitet.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein ZahlungskonditionenDAO Objekt und gibt eine
     * Zahlungskonditions ArrayList an eine OberservableList, die dann an die
     * TableView übergeben wird.
     * @throws java.sql.SQLException SQL Exception
     */
    public void setTableContent() throws SQLException {
        ZahlungskonditionenDAO zk = new ZahlungskonditionenDAO();
        ObservableList<Zahlungskonditionen> zahlungskonditionen
                = FXCollections.observableArrayList(
                        zk.gibAlleZahlungskonditionenOhneLKZ());
        zahlungskonditionenTV.setItems(zahlungskonditionen);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Aktualisiert die TableView mit aktuellem Inhalt.
     * @throws java.sql.SQLException SQL Exception
     */
    public void refreshTable() throws SQLException {
        zahlungskonditionenTV.getItems().clear();
        setTableContent();
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Entfernt alle Eingaben in den Textfeldern.
     */
    public void clearTextFields() {
        tfZahlungskonditionsID.clear();
        cbAuftragsart.valueProperty().set(null);
        tfLieferzeitSOFORT.clear();
        tfSperrzeitWUNSCH.clear();
        tfMahnzeit1.clear();
        tfMahnzeit2.clear();
        tfMahnzeit3.clear();
        tfSkontozeit1.clear();
        tfSkonto1.clear();
        tfSkontozeit2.clear();
        tfSkonto2.clear();
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 16.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Zahlungskonditionen ohne LKZ und stellt sie in der
     * Tabelle dar.
     * @throws java.sql.SQLException SQL Exception
     */
    public void alleOhneLKZ() throws SQLException {
        ZahlungskonditionenDAO ad = new ZahlungskonditionenDAO();
        ObservableList<Zahlungskonditionen> zahlungskonditionen
                = FXCollections.observableArrayList(
                        ad.gibAlleZahlungskonditionenOhneLKZ());
        zahlungskonditionenTV.setItems(zahlungskonditionen);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    SAM     Methode erstellt.
    /* 19.08.17    GET     Methode fertiggestellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt die unteren Eingabefelder für das Anlegen einer neuer 
     * Zahlungskondition frei.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void zahlungskonditionenAnlegen() throws SQLException {      
        zahlungskonditionenTV.setMouseTransparent(true);
        clearTextFields();
        this.pane.setDisable(true);
        this.anlegenBT.setVisible(false);
        this.hinzufuegenZahlungskonditionenBT.setVisible(true);
        this.zahlungskonditionendatensatzPane.setText(""
                + "Zahlungskonditionendatensatz (Anlegemodus)");
        this.bearbeitenBT.setDisable(true);
        this.loeschenBT.setDisable(true);
        this.abbrechenBT.setDisable(false);

        
        ZahlungskonditionenDAO zk = new ZahlungskonditionenDAO();
        this.tfZahlungskonditionsID.setText(zk.generiereID());
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    SAM     Methode erstellt.
    /* 18.08.17    GET     Methode fertiggestellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues
     * Zahlungskonditionen Objekt, welches dann über die DAO in die DB
     * eingefuegt wird.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void zahlungskonditionenHinzufuegen() throws SQLException {
        
        if (validateFields()) {
            
            if (validateSkonto1() && validateSkonto2()) {
            
                String zahlungskonditionsID = tfZahlungskonditionsID.getText();
                String auftragsart = cbAuftragsart.getValue();
                String lieferzeit = tfLieferzeitSOFORT.getText();
                String sperrzeit = tfSperrzeitWUNSCH.getText();
                String skontozeit1 = tfSkontozeit1.getText();
                String skontozeit2 = tfSkontozeit2.getText();
                String skonto1 = tfSkonto1.getText().replace(',', '.');
                String skonto2 = tfSkonto2.getText().replace(',', '.');
                String mahnzeit1 = tfMahnzeit1.getText();
                String mahnzeit2 = tfMahnzeit2.getText();
                String mahnzeit3 = tfMahnzeit3.getText();
                String lkz = "N";
                Zahlungskonditionen zahlungskonditionen = 
                        new Zahlungskonditionen(zahlungskonditionsID, 
                            auftragsart, lieferzeit, sperrzeit, skontozeit1, 
                            skontozeit2, skonto1, skonto2, mahnzeit1, mahnzeit2,
                            mahnzeit3, lkz);

                ZahlungskonditionenDAO ad = new ZahlungskonditionenDAO();
                ad.fuegeZahlungskonditionenHinzu(zahlungskonditionen);

                clearTextFields();
                refreshTable();

                // Textfeldbereich wird aktiviert
                this.pane.setDisable(false);
                // Bearbeiten-Button wird ausgeblendet
                this.anlegenBT.setVisible(true);
                // Speichern-Button wird eingeblendet
                this.hinzufuegenZahlungskonditionenBT.setVisible(false);
                // Der Bearbeitungsmodus des Zahlungskonditionendatensatzes 
                //wird aktiviert
                this.zahlungskonditionendatensatzPane.setText(
                    "Zahlungskonditionendatensatz");
                // Anlegen-Button wird deaktiviert
                this.bearbeitenBT.setDisable(false);
                // Löschen-Button wird deaktiviert
                this.loeschenBT.setDisable(false);

                zahlungskonditionenTV.setMouseTransparent(false);
            
            }
        }
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    SAM     Methode erstellt.
    /* 21.08.17    BER     Methode fertiggestellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * "Löscht" eine markierte Zahlungskondition, indem das LKZ auf J 
     * gesetzt wird.
     * Aktualisiert anschließend die TableView.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void zahlungskonditionenLoeschen() throws SQLException {
        Object zahlungskonditionen = zahlungskonditionenTV.getSelectionModel()
                .getSelectedItem();
        Zahlungskonditionen zk = (Zahlungskonditionen) zahlungskonditionen;

        if (!this.tfZahlungskonditionsID.getText().isEmpty()) {
            Meldung meldung = new Meldung();
            meldung.loeschenAbfragen();

            if (meldung.antwort()) {
                ZahlungskonditionenDAO zkDAO = new ZahlungskonditionenDAO();
                zkDAO.setzeLKZ(zk);
                refreshTable();
            
            } else {
                meldung.schließeFenster();                
            }
            clearTextFields();
            this.bearbeitenBT.setDisable(true);
            this.loeschenBT.setDisable(true);
            this.abbrechenBT.setDisable(true);
            this.closeZK.requestFocus();
        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    SAM     Methode erstellt.
    /* 19.08.17    GET     Methode fertiggestellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Lässt das Bearbeiten einer ausgewählten Zahlungskondition zu.
     */
    @FXML
    public void bearbeiteZahlungskonditionen() {
        this.pane.setDisable(true);
        this.anlegenBT.setDisable(true);
        this.speichernBT.setVisible(true);
        this.zahlungskonditionendatensatzPane.setText(
                "Zahlungskonditionendatensatz (Bearbeitungsmodus)");
        this.loeschenBT.setDisable(true);   
        this.bearbeitenBT.setDisable(true);
        this.abbrechenBT.setDisable(false);
        this.zahlungskonditionenTV.setMouseTransparent(true);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    SAM     Methode erstellt.
    /* 19.08.17    GET     Methode fertiggestellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Speichert die gemachten Änderungen in die Datenbank und aktualisiert die
     * View mit den neuen Werten.
     * @throws java.sql.SQLException SQLException.
     */
    @FXML
    public void speichereAenderung() throws SQLException {
        
        if (validateFields()) {
            
            if (validateSkonto1() && validateSkonto2()) {
         
                String zahlungskonditionsID = tfZahlungskonditionsID.getText();
                String auftragsart = cbAuftragsart.getValue();
                String lieferzeit = tfLieferzeitSOFORT.getText();
                String sperrzeit = tfSperrzeitWUNSCH.getText();
                String skontozeit1 = tfSkontozeit1.getText();
                String skontozeit2 = tfSkontozeit2.getText();
                String skonto1 = tfSkonto1.getText().replace(',', '.');
                String skonto2 = tfSkonto2.getText().replace(',', '.');
                String mahnzeit1 = tfMahnzeit1.getText();
                String mahnzeit2 = tfMahnzeit2.getText();
                String mahnzeit3 = tfMahnzeit3.getText();

                String lkz = "N";

                Zahlungskonditionen zahlungskonditionen = 
                    new Zahlungskonditionen(zahlungskonditionsID, auftragsart, 
                    lieferzeit, sperrzeit, skontozeit1, skontozeit2, skonto1, 
                    skonto2, mahnzeit1, mahnzeit2, mahnzeit3, lkz);

                ZahlungskonditionenDAO zkDAO = new ZahlungskonditionenDAO();
                zkDAO.aendereZahlungskonditionen(zahlungskonditionen);
            
                // Textfeldbereich wird aktiviert
                this.pane.setDisable(false);
                this.bearbeitenBT.setDisable(true);
                this.speichernBT.setVisible(false);
                this.zahlungskonditionendatensatzPane.setText(
                    "Zahlungskonditionendatensatz");
                this.anlegenBT.setDisable(false);
                this.loeschenBT.setDisable(true);
                this.abbrechenBT.setDisable(true);
                this.zahlungskonditionenTV.setMouseTransparent(false);
                refreshTable();
            }    
        }

    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 20.08.17    GET     Methode erstellt.
    /* 21.08.17    GET     Getestet & freigegeben.
    */
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt die Werte einer ausgewählten Zahlungskondition im unteren Bereich
     * an.
     */
    @FXML
    public void zeigeWerteAn() {
        Object zahlungskonditionen = zahlungskonditionenTV.getSelectionModel()
                .getSelectedItem();
        Zahlungskonditionen b = (Zahlungskonditionen) zahlungskonditionen;

        if (b != null) {
            this.tfZahlungskonditionsID.setText(b.getZahlungskonditionenID());
            this.cbAuftragsart.setValue(b.getAuftragsart());
            this.tfLieferzeitSOFORT.setText(b.getLieferzeitSOFORT());
            this.tfSperrzeitWUNSCH.setText(b.getSperrzeitWUNSCH());
            this.tfSkontozeit1.setText(b.getSkontozeit1());
            this.tfSkontozeit2.setText(b.getSkontozeit2());
            this.tfSkonto1.setText(b.getSkonto1());
            this.tfSkonto2.setText(b.getSkonto2());
            this.tfMahnzeit1.setText(b.getMahnzeit1());
            this.tfMahnzeit2.setText(b.getMahnzeit2());
            this.tfMahnzeit3.setText(b.getMahnzeit3());
            
            this.bearbeitenBT.setDisable(false);
            this.loeschenBT.setDisable(false);
            this.abbrechenBT.setDisable(true);
        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    SAM     Methode erstellt.
    /* 18.08.17    GET     Methode fertiggestellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Methode bekommt eine ArrayList mit den gefundenen Zahlungskonditionen
     * übergeben und aktualisiert damit die TableView.
     * @param zahlungskonditionen Übergebene Zahlungskondition.
     * @throws java.sql.SQLException SQL Exception
     */
    public void zeigeGefundeneZahlungskonditionen(
            ArrayList zahlungskonditionen) throws SQLException {
        refreshTable();
        ObservableList<Zahlungskonditionen> zahlungskonditionsAusgabe
                = FXCollections.observableArrayList(zahlungskonditionen);
        zahlungskonditionenTV.setItems(zahlungskonditionsAusgabe);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    SAM     Methode erstellt.
    /* 21.08.17    BER     Methode fertiggestellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt die Werte einer ausgewählten Zahlungskondition im unteren 
     * Bereich an.
     * @throws java.sql.SQLException SQLException
     */
    @FXML
    public void zahlungskonditionenSuchen() throws SQLException {
        SucheDAO zk = new SucheDAO();
        ArrayList gefundeneZahlungskonditionen;

        String suchkriterium = cbSuchfeld.getValue();
        String suchbegriff = tfSuchbegriff.getText();

        gefundeneZahlungskonditionen = zk.zahlungskonditionSuche(
                suchkriterium, suchbegriff);
        zeigeGefundeneZahlungskonditionen(gefundeneZahlungskonditionen);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 21.08.17    SEZ     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Setzt die Suche zurück.
     * @throws java.sql.SQLException SQLException
     */
    @FXML
    public void setzeSucheZurueck() throws SQLException {
        tfSuchbegriff.setText("");
        setTableContent();
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 23.08.17    SEZ     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Lässt den Benutzer die Aktion abbrechen.
     */    
    @FXML
    public void aktionAbbrechen() {

        if (!this.zahlungskonditionendatensatzPane.getText().equalsIgnoreCase(
                "Zahlungskonditionendatensatz")) {
            Meldung meldung = new Meldung();
            meldung.verwerfenFenster();
            if (!(this.tfZahlungskonditionsID.getText().isEmpty())) {

                if (meldung.antwort()) {
                    // Textfeldbereich wird aktiviert
                    this.pane.setDisable(false);
                    this.anlegenBT.setVisible(true);
                    this.zahlungskonditionendatensatzPane.setText(
                        "Zahlungskonditionendatensatz");
                    this.bearbeitenBT.setDisable(true);
                    this.bearbeitenBT.setVisible(true);
                    this.speichernBT.setVisible(false);
                    this.anlegenBT.setDisable(false);
                    this.loeschenBT.setDisable(true);
                    this.hinzufuegenZahlungskonditionenBT.setVisible(false);
                    this.zahlungskonditionenTV.setMouseTransparent(false);
                    this.abbrechenBT.setDisable(true);

                    clearTextFields();

                } else {
                    meldung.schließeFenster();
                }
            }
        }
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum         Name    Was
    /* 10.09.2017    GET     Methode erstellt.
    /* 10.09.2017    GET     Getestet & freigegeben 
    /*------------------------------------------------------------------------*/
    
    /**
     * Prüft ob das Skonto1 korrekt eingegeben wurde.
     *
     * @return boolschen Wert ob die eingaben korrekt sind.
     */
    private boolean validateSkonto1() {
        
        boolean istValidiert = false;
        
        Pattern p = Pattern.compile("[0-9]{1}([,.][0-9]{1})?");
        Matcher m = p.matcher(this.tfSkonto1.getText());

        if (m.find() && m.group().equals(this.tfSkonto1.getText())) {
            
            istValidiert = true;
            
        } else {
            
            this.tfSkonto1.requestFocus();
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fehlerhafter Skontowert!");
            alert.setContentText("Das Skonto1 entspricht nicht dem Format "
                    + "(z.B.: 9.9)");
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
     * Prüft ob das Skonto2 korrekt eingegeben wurde.
     *
     * @return boolschen Wert ob die eingaben korrekt sind.
     */
    private boolean validateSkonto2() {
        
        boolean istValidiert = false;
        
        Pattern p = Pattern.compile("[0-9]{1}([,.][0-9]{1})?");
        Matcher m = p.matcher(this.tfSkonto2.getText());

        if (m.find() && m.group().equals(this.tfSkonto2.getText())) {
            
            istValidiert = true;
            
        } else {
            
            this.tfSkonto2.requestFocus();
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fehlerhafter Skontowert!");
            alert.setContentText("Das Skonto2 entspricht nicht dem Format "
                    + "(z.B.: 9.9)");
            alert.showAndWait();
        }
        
        
        return istValidiert;
        
    }
    
    
    
    /**
     * Validiert die Zahlungskonditionsfelder.
     * @return True: Wenn Validierung erfolgreich, sonst False.
     */
    private boolean validateFields() {
        boolean istValidiert = true;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Fehlende Eingaben");

        if (this.cbAuftragsart.getValue() == null) {
            
            this.cbAuftragsart.requestFocus();   
            alert.setContentText("Bitte wählen Sie die Auftragsart!");
            alert.showAndWait();
            istValidiert = false;

        } else if (this.tfLieferzeitSOFORT.getText().isEmpty()) {
            
            this.tfLieferzeitSOFORT.requestFocus();
            
            alert.setContentText("Bitte setzen sie die Lieferzeit SOFORT.");
            alert.showAndWait();
            istValidiert = false;

        } else if (this.tfSperrzeitWUNSCH.getText().isEmpty()) {
            
            this.tfSperrzeitWUNSCH.requestFocus();
            
            alert.setContentText("Bitte setzen sie die Sperrzeit WUNSCH.");
            alert.showAndWait();
            istValidiert = false;

        } else if (this.tfMahnzeit1.getText().isEmpty()) {
            
            this.tfMahnzeit1.requestFocus();
            
            alert.setContentText("Bitte setzen sie die Mahnzeit 1.");
            alert.showAndWait();
            istValidiert = false;
        } else if (this.tfMahnzeit2.getText().isEmpty()) {
            
            this.tfMahnzeit2.requestFocus();
            
            alert.setContentText("Bitte setzen sie die Mahnzeit 2.");
            alert.showAndWait();
            istValidiert = false;
        } else if (this.tfMahnzeit3.getText().isEmpty()) {
            
            this.tfMahnzeit3.requestFocus();
            
            alert.setContentText("Bitte setzen sie die Mahnzeit 3.");
            alert.showAndWait();
            istValidiert = false;
        } else if (this.tfSkontozeit1.getText().isEmpty()) {
            
            this.tfSkontozeit1.requestFocus();
            
            alert.setContentText("Bitte setzen sie die Skontozeit 1.");
            alert.showAndWait();
            istValidiert = false;
        } else if (this.tfSkonto1.getText().isEmpty()) {
            
            this.tfSkonto1.requestFocus();
            
            alert.setContentText("Bitte setzen sie das Skonto 1.");
            alert.showAndWait();
            istValidiert = false;
        } else if (this.tfSkontozeit2.getText().isEmpty()) {
            
            this.tfSkontozeit2.requestFocus();
            
            alert.setContentText("Bitte setzen sie die Skontozeit 2.");
            alert.showAndWait();
            istValidiert = false;
        } else if (this.tfSkonto2.getText().isEmpty()) {
            
            this.tfSkonto2.requestFocus();
            
            alert.setContentText("Bitte setzen sie das Skonto 2.");
            alert.showAndWait();
            istValidiert = false;
        } 
        return istValidiert;  
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 20.08.17    GET     Methode erstellt und fertiggestellt. 
    /*                     Getestet & freigegeben.
    /*------------------------------------------------------------------------*/

    /**
     *  Prüft welche Auftragsart gesetzt ist. Um die Werte der Auftragsart 
     * anzupassen.
     * 
     */
    @FXML
    public void pruefeAufAuftragsart() {
        
        if (this.cbAuftragsart.getValue() != null) { 
            
            if (this.cbAuftragsart.getValue() == "Sofortauftrag") {

                this.tfSperrzeitWUNSCH.setText("0");
                this.tfLieferzeitSOFORT.setText("");
                this.tfMahnzeit1.setText("");
                this.tfMahnzeit2.setText("");
                this.tfMahnzeit3.setText("");
                this.tfSkontozeit1.setText("");
                this.tfSkonto1.setText("");
                this.tfSkontozeit2.setText("");
                this.tfSkonto2.setText("");
                this.tfSperrzeitWUNSCH.setEditable(false);
                this.tfLieferzeitSOFORT.setEditable(true);
                this.tfMahnzeit1.setEditable(true);
                this.tfMahnzeit2.setEditable(true);
                this.tfMahnzeit3.setEditable(true);
                this.tfSkontozeit1.setEditable(true);
                this.tfSkonto1.setEditable(true);
                this.tfSkontozeit2.setEditable(true);
                this.tfSkonto2.setEditable(true);

            } else if (this.cbAuftragsart.getValue() == "Terminauftrag") {

                this.tfLieferzeitSOFORT.setText("0");
                this.tfSperrzeitWUNSCH.setText("");
                this.tfMahnzeit1.setText("");
                this.tfMahnzeit2.setText("");
                this.tfMahnzeit3.setText("");
                this.tfSkontozeit1.setText("");
                this.tfSkonto1.setText("");
                this.tfSkontozeit2.setText("");
                this.tfSkonto2.setText("");
                this.tfLieferzeitSOFORT.setEditable(false);
                this.tfSperrzeitWUNSCH.setEditable(true);
                this.tfMahnzeit1.setEditable(true);
                this.tfMahnzeit2.setEditable(true);
                this.tfMahnzeit3.setEditable(true);
                this.tfSkontozeit1.setEditable(true);
                this.tfSkonto1.setEditable(true);
                this.tfSkontozeit2.setEditable(true);
                this.tfSkonto2.setEditable(true);

            } else if (this.cbAuftragsart.getValue() == "Barauftrag"){

                this.tfLieferzeitSOFORT.setText("0");
                this.tfSperrzeitWUNSCH.setText("0");
                this.tfMahnzeit1.setText("0");
                this.tfMahnzeit2.setText("0");
                this.tfMahnzeit3.setText("0");
                this.tfSkontozeit1.setText("0");
                this.tfSkonto1.setText("0");
                this.tfSkontozeit2.setText("0");
                this.tfSkonto2.setText("0");
                
                this.tfLieferzeitSOFORT.setEditable(false);
                this.tfSperrzeitWUNSCH.setEditable(false);
                this.tfMahnzeit1.setEditable(false);
                this.tfMahnzeit2.setEditable(false);
                this.tfMahnzeit3.setEditable(false);
                this.tfSkontozeit1.setEditable(false);
                this.tfSkonto1.setEditable(false);
                this.tfSkontozeit2.setEditable(false);
                this.tfSkonto2.setEditable(false);
                
                
            } else {
                
                this.tfSperrzeitWUNSCH.setText("");
                this.tfLieferzeitSOFORT.setText("");
                this.tfMahnzeit1.setText("");
                this.tfMahnzeit2.setText("");
                this.tfMahnzeit3.setText("");
                this.tfSkontozeit1.setText("");
                this.tfSkonto1.setText("");
                this.tfSkontozeit2.setText("");
                this.tfSkonto2.setText("");
                
                this.tfLieferzeitSOFORT.setEditable(true);
                this.tfSperrzeitWUNSCH.setEditable(true);
                this.tfMahnzeit1.setEditable(true);
                this.tfMahnzeit2.setEditable(true);
                this.tfMahnzeit3.setEditable(true);
                this.tfSkontozeit1.setEditable(true);
                this.tfSkonto1.setEditable(true);
                this.tfSkontozeit2.setEditable(true);
                this.tfSkonto2.setEditable(true);
            }
            
        }    
    }      
}
