/*------------------------------------------------------------------------------
* Klasse: ZahlungskonditionenController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 2017-06-15 SAM Angelegt.
* 2017-06-26 GET Checkstyleprüfung.
* 2017-07-27 BER Kommentarlayout angepasst.
* 2017-08-18 SAM Kommentare angepasst.
* 2017-08-19 SAM Methodenerweiterungen.
*-------------------------------------------------------------------------------
 */
package auverauftragsverwaltung;

import Datenbank.ZahlungskonditionenDAO;
import Klassen.Zahlungskonditionen;
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

/**
 *
 * @author Mudimbi
 */
public class ZahlungskonditionenController implements Initializable {

    /**
     * Abbrechen-Button der Zahlungskonditionverwaltung.
     */
    @FXML
    private Button closeZK;
    
    /**
     * ComboBox für die Auswahl des Suchkriteriums.
     */
    @FXML
    private ComboBox<String> cb_suchfeld = new ComboBox();
    
    /**
     * Textfeld für die Eingabe des Suchbegriffs.
     */
    @FXML
    private TextField tf_suchbegriff;
    
    /**
     * Dieses Textfeld dient zur Eingabe der Zahlungskonditions-ID.
     */
    @FXML
    private TextField tf_zahlungskonditionsID;
    
    /**
     * Diese ComboBox dient zur Auswahl der Auftragsarten.
     */
    @FXML
    private final ComboBox<String> cb_auftragsart = new ComboBox();
    
    /**
     * Dieses Textfeld dient zur Eingabe des Zeitraums fuer den
     * Liefertermin bei Sofortauftraegen.
     */
    @FXML
    private TextField tf_lieferzeitSOFORT;
    
    /**
     * Dieses Textfeld dient zur Eingabe des Zeitraums nachdem
     * ein Wunschlieferdatum angelegt werden kann.
     */
    @FXML
    private TextField tf_sperrzeitWUNSCH;
    
    /**
     * Dieses Textfeld dient zur Eingabe der Tage fuer die Mahnstufe 1.
     */
    @FXML
    private TextField tf_mahnzeit1;
    
    /**
     * Dieses Textfeld dient zur Eingabe der Tage fuer die Mahnstufe 2.
     */
    @FXML
    private TextField tf_mahnzeit2;
    
    /**
     * Dieses Textfeld dient zur Eingabe der Tage fuer die Mahnstufe 3.
     */
    @FXML
    private TextField tf_mahnzeit3;
    
    /**
     * Dieses Textfeld dient zur Eingabe des Zeitraums für Skontoabzug1.
     */
    @FXML
    private TextField tf_skontozeit1;
    
    /**
     * Dieses Textfeld dient zur Eingabe des Prozentsatzes für Skontozeitraum 1.
     */
    @FXML
    private TextField tf_skonto1;
    
    /**
     * Dieses Textfeld dient zur Eingabe des Zeitraums für Skontoabzug2.
     */
    @FXML
    private TextField tf_skontozeit2;
    
    /**
     * Dieses Textfeld dient zur Eingabe des Prozentsatzes für Skontozeitraum 2.
     */
    @FXML
    private TextField tf_skonto2;
    
    
    /**
     * Diese TableView dient der Anzeige der Zahlungskonditionen.
     */
    @FXML
    private final TableView tv_zahlungskonditionen = new TableView<Zahlungskonditionen>();
    
    
    /**
     * Das ist die Tabellenspalte fuer die KonditionenID.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> Konditionen_ID;
    
    /**
     * Das ist die Tabellenspalte fuer die Auftragsart.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> Auftragsart;
    
    /**
     * Das ist die Tabellenspalte fuer die Lieferzeit.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> LieferzeitSOFORT;
    
    /**
     * Das ist die Tabellenspalte fuer die Sperrzeit.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> SperrzeitWUNSCH;
    
    /**
     * Das ist die Tabellenspalte fuer die Skontozeit1.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> Skontozeit1;
    
    /**
     * Das ist die Tabellenspalte fuer das Skonto1.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> Skonto1;
    
    /**
     * Das ist die Tabellenspalte fuer die Skontozeit2.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> Skontozeit2;
    
    /**
     * Das ist die Tabellenspalte fuer das Skonto2.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> Skonto2;
    
    /**
     * Das ist die Tabellenspalte fuer die Mahnzeit1.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> Mahnzeit1;
    
    /**
     * Das ist die Tabellenspalte fuer die Mahnzeit2.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> Mahnzeit2;
    
    /**
     * Das ist die Tabellenspalte fuer die Mahnzeit3.
     */
    @FXML
    private TableColumn<Zahlungskonditionen, String> Mahnzeit3;
    
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
    private TitledPane zahlungskonditionendatensatzPane;
    
    /**
     * Hinzufügen Button.
     */
    @FXML
    private Button hinzufuegenZahlungskonditionenBT;
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    SAM     Methode erstellt.
    /*------------------------------------------------------------------------*/

    /**
     * Durch betätigen der Schaltfläche "Abbrechen"
     * soll das Fenster der Zahlungskonditionsverwaltung geschlossen werden.
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen"
     * abfängt.
     */
    @FXML
    public void closeZahlungskondition(ActionEvent event) {
        Stage stage = (Stage) closeZK.getScene().getWindow();
        stage.close();
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    SAM     Methode erstellt.
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
            alert.setHeaderText("Keine Zahlungskonditionen gefunden!");
            alert.showAndWait();
        }
        
        //Die Eingabe im Textfeld wird auf 6 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tf_zahlungskonditionsID, 6);
        
        //Die Eingabe im Textfeld wird auf 4 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tf_lieferzeitSOFORT, 4);
        
        //Die Eingabe im Textfeld wird auf 4 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tf_sperrzeitWUNSCH, 4);
        
        //Die Eingabe im Textfeld wird auf 4 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tf_mahnzeit1, 4);
        
        ///Die Eingabe im Textfeld wird auf 4 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tf_mahnzeit2, 4);
        
        //Die Eingabe im Textfeld wird auf 4 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tf_mahnzeit3, 4);
        
        //Die Eingabe im Textfeld wird auf 4 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tf_skontozeit1, 4);
        
        //Die Eingabe im Textfeld wird auf 3 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tf_skonto1, 3);
        
        //Die Eingabe im Textfeld wird auf 4 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tf_skontozeit2, 4);
        
        //Die Eingabe im Textfeld wird auf 3 Zeichen begrenzt 
        begrenzeTextFeldEingabe(tf_skonto2, 3);
        

        Konditionen_ID.setCellValueFactory(
                new PropertyValueFactory<>("zahlungskonditionsID"));
        Auftragsart.setCellValueFactory(
                new PropertyValueFactory<>("auftragsart"));
        LieferzeitSOFORT.setCellValueFactory(
                new PropertyValueFactory<>("lieferzeitSofort"));
        SperrzeitWUNSCH.setCellValueFactory(
                new PropertyValueFactory<>("sperrzeitWunsch"));
        Skontozeit1.setCellValueFactory(
                new PropertyValueFactory<>("skontozeit1"));
        Skonto1.setCellValueFactory(
                new PropertyValueFactory<>("skonto1"));
        Skontozeit2.setCellValueFactory(
                new PropertyValueFactory<>("skontozeit2"));
        Skonto2.setCellValueFactory(
                new PropertyValueFactory<>("skonto2"));
        Mahnzeit1.setCellValueFactory(
                new PropertyValueFactory<>("mahnzeit1"));
        Mahnzeit2.setCellValueFactory(
                new PropertyValueFactory<>("mahnzeit2"));
        Mahnzeit3.setCellValueFactory(
                new PropertyValueFactory<>("mahnzeit3"));
        
        cb_suchfeld.getItems().addAll(
                "Konditionen_ID",
                "LieferzeitSOFORT",
                "SperrzeitWUNSCH",
                "Skontozeit1",
                "Skonto1",
                "Skontozeit2",
                "Skonto2",
                "Mahnzeit1",
                "Mahnzeit2",
                "Mahnzeit3");
        
        cb_auftragsart.getItems().addAll("Privat", "Geschaeftlich");
        
        
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 18.08.17    SAM     Methode erstellt.
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
    /* 18.08.17    SAM     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
        /**
     * Erstellt ein ZahlungskonditionenDAO Objekt und gibt eine 
     * Zahlungskonditions ArrayList an eine OberservableList, die dann an 
     * die TableView übergeben wird.
     * @throws java.sql.SQLException SQL Exception
     */
    public void setTableContent() throws SQLException {    
        ZahlungskonditionenDAO zk = new ZahlungskonditionenDAO();     
        ObservableList<Zahlungskonditionen> zahlungskonditionen 
                = FXCollections.observableArrayList(
                        zk.gibAlleZahlungskonditionenOhneLKZ());
        tv_zahlungskonditionen.setItems(zahlungskonditionen);
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 18.08.17    SAM     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Methode bekommt eine ArrayList mit den gefundenen Zahlungskonditionen
     * übergeben und aktualisiert damit die TableView.
     * @param zahlungskonditionen Übergebene Zahlungskondition.
     * @throws java.sql.SQLException SQL Exception
    */
    @FXML
    public void zeigeGefundeneZahlungskonditionen(ArrayList zahlungskonditionen) 
            throws SQLException {
        refreshTable();
        ObservableList<Zahlungskonditionen> zahlungskonditionsAusgabe
            = FXCollections.observableArrayList(zahlungskonditionen);
        tv_zahlungskonditionen.setItems(zahlungskonditionsAusgabe);
    } 
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    SAM     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Aktualisiert die TableView mit aktuellem Inhalt.
     * @throws java.sql.SQLException SQL Exception
    */
    @FXML
    public void refreshTable() throws SQLException {
        tv_zahlungskonditionen.getItems().clear();
        setTableContent();
    }  
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    SAM     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Entfernt alle Eingaben in den Textfeldern.
     * @throws java.sql.SQLException SQL Exception
    */
    @FXML
    public void clearTextFields() throws SQLException {
        tf_zahlungskonditionsID.clear();
        cb_auftragsart.valueProperty().set(null);
        tf_lieferzeitSOFORT.clear();
        tf_sperrzeitWUNSCH.clear();
        tf_mahnzeit1.clear();
        tf_mahnzeit2.clear();
        tf_mahnzeit3.clear();
        tf_skontozeit1.clear();
        tf_skonto1.clear();
        tf_skontozeit2.clear();
        tf_skonto2.clear();
    }   
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    SAM     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Zahlungskonditionen mit aktivem LKZ 
     * und stellt sie in der Tabelle dar.
     * @throws java.sql.SQLException SQL Exception
     */    
    @FXML
    public void alleMitLKZ() throws SQLException {    
        ZahlungskonditionenDAO zk = new ZahlungskonditionenDAO();     
        ObservableList<Zahlungskonditionen> 
                zahlungskonditionen = FXCollections.observableArrayList
        (zk.gibAlleZahlungskonditionenMitLKZ());
        tv_zahlungskonditionen.setItems(zahlungskonditionen);
    }    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    SAM     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Zahlungskonditionen ohne LKZ 
     * und stellt sie in der Tabelle dar.
     * @throws java.sql.SQLException SQL Exception
     */       
    @FXML
    public void alleOhneLKZ() throws SQLException {    
        ZahlungskonditionenDAO ad = new ZahlungskonditionenDAO();     
        ObservableList<Zahlungskonditionen> zahlungskonditionen 
                = FXCollections.observableArrayList
        (ad.gibAlleZahlungskonditionenOhneLKZ());
        tv_zahlungskonditionen.setItems(zahlungskonditionen);
    } 
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    SAM     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt die unteren Eingabefelder für das Anlegen einer neuer Adresse frei.
    */    
    @FXML
    public void zahlungskonditionenAnlegen() {
        
        // Textfeldbereich wird aktiviert
        this.pane.setDisable(true);
        // Bearbeiten-Button wird ausgeblendet
        this.anlegenBT.setVisible(false);
        // Speichern-Button wird eingeblendet
        this.hinzufuegenZahlungskonditionenBT.setVisible(true);
        // Der Bearbeitungsmodus des Zahlungskonditionendatensatzes wird aktiviert
        this.zahlungskonditionendatensatzPane.setText("Zahlungskonditionendatensatz (Anlegemodus)");
        // Anlegen-Button wird deaktiviert
        this.bearbeitenBT.setDisable(true);
        // Löschen-Button wird deaktiviert
        this.loeschenBT.setDisable(true);
        
        tf_zahlungskonditionsID.setText("");
        cb_auftragsart.setValue("");
        tf_lieferzeitSOFORT.setText("");
        tf_sperrzeitWUNSCH.setText("");
        tf_skontozeit1.setText("");
        tf_skontozeit2.setText("");
        tf_skonto1.setText("");
        tf_skonto2.setText("");
        tf_mahnzeit1.setText("");
        tf_mahnzeit2.setText("");
        tf_mahnzeit3.setText("");
    }

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    SAM     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues
     * Zahlungskonditionen Objekt, welches dann über die DAO
     * in die DB eingefuegt wird.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void ZahlungskonditionenHinzufuegen() throws SQLException {    
        String zahlungskonditionsID = tf_zahlungskonditionsID.getText();
        String auftragsart = cb_auftragsart.getValue();
        String lieferzeit = tf_lieferzeitSOFORT.getText();
        String sperrzeit = tf_sperrzeitWUNSCH.getText();
        String skontozeit1 = tf_skontozeit1.getText();
        String skontozeit2 = tf_skontozeit2.getText();
        String skonto1 = tf_skonto1.getText();
        String skonto2 = tf_skonto2.getText();
        String mahnzeit1 = tf_mahnzeit1.getText();
        String mahnzeit2 = tf_mahnzeit2.getText();
        String mahnzeit3 = tf_mahnzeit3.getText();
        String lkz = "N";
        Zahlungskonditionen zahlungskonditionen = new Zahlungskonditionen(zahlungskonditionsID, auftragsart, lieferzeit, 
                sperrzeit, skontozeit1, skontozeit2, skonto1, skonto2, mahnzeit1, mahnzeit2, mahnzeit3, lkz);
        
        ZahlungskonditionenDAO ad = new ZahlungskonditionenDAO();     
        ad.fuegeZahlungskonditionenHinzu(zahlungskonditionen);
        
        clearTextFields();
        refreshTable();
        
        // Textfeldbereich wird aktiviert
        this.pane.setDisable(true);
        // Bearbeiten-Button wird ausgeblendet
        this.anlegenBT.setVisible(false);
        // Speichern-Button wird eingeblendet
        this.hinzufuegenZahlungskonditionenBT.setVisible(true);
        // Der Bearbeitungsmodus des Zahlungskonditionendatensatzes wird aktiviert
        this.zahlungskonditionendatensatzPane.setText("Zahlungskonditionendatensatz");
        // Anlegen-Button wird deaktiviert
        this.bearbeitenBT.setDisable(true);
        // Löschen-Button wird deaktiviert
        this.loeschenBT.setDisable(true);
        
    }     
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    SAM     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * "Löscht" eine markierte Adresse, in dem das LKZ auf J gesetzt wird.
     * Aktualisiert anschließend die TableView.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void adresseLoeschen() throws SQLException {
        Object zahlungskonditionen = tv_zahlungskonditionen.getSelectionModel().getSelectedItem();
        Zahlungskonditionen b = (Zahlungskonditionen) zahlungskonditionen;

        ZahlungskonditionenDAO zk = new ZahlungskonditionenDAO();
//        zk.setzeLKZ(b);//Siehe Klasse
        
        refreshTable();
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    SAM     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Lässt das Bearbeiten einer ausgewählten Adresse zu.
    */      
    @FXML
    public void bearbeiteZahlungskonditionen() {
        // Textfeldbereich wird aktiviert
        this.pane.setDisable(true);
        // Bearbeiten-Button wird ausgeblendet
        this.anlegenBT.setVisible(false);
        // Speichern-Button wird eingeblendet
        this.hinzufuegenZahlungskonditionenBT.setVisible(true);
        // Der Bearbeitungsmodus des Zahlungskonditionendatensatzes wird aktiviert
        this.zahlungskonditionendatensatzPane.setText("Zahlungskonditionendatensatz (Bearbeitungsmodus)");
        // Anlegen-Button wird deaktiviert
        this.bearbeitenBT.setDisable(true);
        // Löschen-Button wird deaktiviert
        this.loeschenBT.setDisable(true);
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    SAM     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Speichert die gemachten Änderungen in die Datenbank und aktualisiert
     * die View mit den neuen Werten.
     * @throws java.sql.SQLException SQLException.
    */      
    @FXML
    public void speichereAenderung() throws SQLException {    
        String zahlungskonditionsID = tf_zahlungskonditionsID.getText();
        String auftragsart = cb_auftragsart.getValue();
        String lieferzeit = tf_lieferzeitSOFORT.getText();
        String sperrzeit = tf_sperrzeitWUNSCH.getText();
        String skontozeit1 = tf_skontozeit1.getText();
        String skontozeit2 = tf_skontozeit2.getText();
        String skonto1 = tf_skonto1.getText();
        String skonto2 = tf_skonto2.getText();
        String mahnzeit1 = tf_mahnzeit1.getText();
        String mahnzeit2 = tf_mahnzeit2.getText();
        String mahnzeit3 = tf_mahnzeit3.getText();
        String lkz = "N";
        Zahlungskonditionen zahlungskonditionen = new Zahlungskonditionen(zahlungskonditionsID, auftragsart, lieferzeit, 
                sperrzeit, skontozeit1, skontozeit2, skonto1, skonto2, mahnzeit1, mahnzeit2, mahnzeit3, lkz);
        
        ZahlungskonditionenDAO zk = new ZahlungskonditionenDAO();     
        zk.aendereZahlungskonditionen(zahlungskonditionen);
        
        refreshTable();
        
        // Textfeldbereich wird aktiviert
        this.pane.setDisable(false);
        // Bearbeiten-Button wird ausgeblendet
        this.bearbeitenBT.setVisible(true);
        // Speichern-Button wird eingeblendet
        this.speichernBT.setVisible(true);
        // Der Bearbeitungsmodus des Zahlungskonditionendatensatzes wird aktiviert
        this.zahlungskonditionendatensatzPane.setText("Zahlungskonditionendatensatz");
        // Anlegen-Button wird deaktiviert
        this.bearbeitenBT.setDisable(true);
        // Löschen-Button wird deaktiviert
        this.loeschenBT.setDisable(true);
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    GET     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     *Zeigt die Werte einer ausgewählten Zahlungskondition im unteren Bereich an.
    */      
    @FXML
    public void zeigeWerteAn() {
        Object zahlungskonditionen = tv_zahlungskonditionen.getSelectionModel().getSelectedItem();
        Zahlungskonditionen b = (Zahlungskonditionen) zahlungskonditionen;
        
        if (b != null) {
            this.tf_zahlungskonditionsID.setText(b.getZahlungskondiID());
            this.cb_auftragsart.setValue(b.getAuftragsart());
            this.tf_lieferzeitSOFORT.setText(b.getLieferzeit());
            this.tf_sperrzeitWUNSCH.setText(b.getSperrzeit());
            this.tf_skontozeit1.setText(b.getSkontozeit1());
            this.tf_skontozeit2.setText(b.getSkontozeit2());
            this.tf_skonto1.setText(b.getSkonto1());
            this.tf_skonto2.setText(b.getSkonto2());
            this.tf_mahnzeit1.setText(b.getMahnzeit1());
            this.tf_mahnzeit2.setText(b.getMahnzeit2());
            this.tf_mahnzeit3.setText(b.getMahnzeit3());
        }  
    }
    
    @FXML
    public void letzteID() throws SQLException{
        ZahlungskonditionenDAO a = new ZahlungskonditionenDAO();
        System.out.println(a.gibLetztID());//Siehe Klasse
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    SAM     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt die Werte einer ausgewählten Adresse im unteren Bereich an.
     * @throws java.sql.SQLException SQLException
    */        
    @FXML
    public void zahlungskonditionenSuchen() throws SQLException {
        ZahlungskonditionenDAO zk = new ZahlungskonditionenDAO();
        ArrayList gefundeneZahlungskonditionen;
        
        String suchkriterium = cb_suchfeld.getValue();
        String suchbegriff = tf_suchbegriff.getText();
        
//        gefundeneZahlungskonditionen = zk.zahlungskonditionSuche(suchkriterium, suchbegriff);//Siehe Klasse
        
//        zeigeGefundeneZahlungskonditionen(gefundeneZahlungskonditionen);
    }    
    
}
