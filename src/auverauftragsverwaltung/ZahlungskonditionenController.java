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
*-------------------------------------------------------------------------------
 */
package auverauftragsverwaltung;

import Datenbank.ZahlungskonditionenDAO;
import Klassen.Zahlungskonditionen;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
     * Textfeld der Zahlungskonditionen
     */
    @FXML
    private TextField tf_zahlungskonditionsID;
    /**
     * ComboBox für die Auswahl der Auftragsart.
     */
    @FXML
    private final ComboBox<String> cb_auftragsart = new ComboBox();
    /**
     * Textfeld der Lieferzeit bei einem Sofortauftrag
     */
    @FXML
    private TextField tf_lieferzeitSOFORT;
    /**
     * Textfeld der Sperrzeit bei einem Terminauftrag
     */
    @FXML
    private TextField tf_sperrzeitWUNSCH;
    /**
     * Mahnzeit 1 für die Zahlungskondition
     */
    @FXML
    private TextField tf_mahnzeit1;
    /**
     * Mahnzeit 2 für die Zahlungskondition
     */
    @FXML
    private TextField tf_mahnzeit2;
    /**
     * Mahnzeit 3 für die Zahlungskondition
     */
    @FXML
    private TextField tf_mahnzeit3;
    /**
     * Skontozeit 1
     */
    @FXML
    private TextField tf_skontozeit1;
    /**
     * Skonto 1
     */
    @FXML
    private TextField tf_skonto1;
    /**
     * Skontozeit 2
     */
    @FXML
    private TextField tf_skontozeit2;
    /**
     * Skonto 2
     */
    @FXML
    private TextField tf_skonto2;
    
    @FXML
    private final TableView tv_zahlungskonditionen = new TableView<Zahlungskonditionen>();
    
    @FXML
    private TableColumn<Zahlungskonditionen, String> KonditionenID;
    @FXML
    private TableColumn<Zahlungskonditionen, String> Auftragsart;
    @FXML
    private TableColumn<Zahlungskonditionen, String> Lieferzeit;
    @FXML
    private TableColumn<Zahlungskonditionen, String> Sperrzeit;
    @FXML
    private TableColumn<Zahlungskonditionen, String> Skontozeit1;
    @FXML
    private TableColumn<Zahlungskonditionen, String> Skonto1;
    @FXML
    private TableColumn<Zahlungskonditionen, String> Skontozeit2;
    @FXML
    private TableColumn<Zahlungskonditionen, String> Skonto2;
    @FXML
    private TableColumn<Zahlungskonditionen, String> Mahnzeit1;
    @FXML
    private TableColumn<Zahlungskonditionen, String> Mahnzeit2;
    @FXML
    private TableColumn<Zahlungskonditionen, String> Mahnzeit3;

    /**
     * Methode zum Abbrechen der Zahlungskonditionverwaltung.
     *
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen"
     * abfängt.
     */
    @FXML
    public void closeZahlungskondition(ActionEvent event) {
        Stage stage = (Stage) closeZK.getScene().getWindow();
        stage.close();
    }

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
        
        begrenzeTextFeldEingabe(tf_zahlungskonditionsID, 6);
        begrenzeTextFeldEingabe(tf_lieferzeitSOFORT, 4);
        begrenzeTextFeldEingabe(tf_sperrzeitWUNSCH, 4);
        begrenzeTextFeldEingabe(tf_mahnzeit1, 4);
        begrenzeTextFeldEingabe(tf_mahnzeit2, 4);
        begrenzeTextFeldEingabe(tf_mahnzeit3, 4);
        begrenzeTextFeldEingabe(tf_skontozeit1, 4);
        begrenzeTextFeldEingabe(tf_skonto1, 3);
        begrenzeTextFeldEingabe(tf_skontozeit2, 4);
        begrenzeTextFeldEingabe(tf_skonto2, 3);
        

        KonditionenID.setCellValueFactory(
                new PropertyValueFactory<>("zahlungskonditionsID"));
        Auftragsart.setCellValueFactory(
                new PropertyValueFactory<>("auftragsart"));
        Lieferzeit.setCellValueFactory(
                new PropertyValueFactory<>("lieferzeit"));
        Sperrzeit.setCellValueFactory(
                new PropertyValueFactory<>("sperrzeit"));
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
        
        cb_auftragsart.getItems().addAll("Privat", "Geschaeftlich");
        
        
    }

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
    /* 15.08.17    BER     Methode erstellt.
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
    
    /**
     * Aktualisiert die TableView mit aktuellem Inhalt.
     * @throws java.sql.SQLException SQL Exception
    */
    @FXML
    public void refreshTable() throws SQLException {
        tv_zahlungskonditionen.getItems().clear();
        setTableContent();
    }  
    
    /**
     * Löscht alle Eingaben in den Textfeldern.
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
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Zahlungskonditionen mit aktivem LKZ 
     * und stellt sie in der Tabelle dar.
     * @throws java.sql.SQLException SQL Exception
     */    
    @FXML
    public void alleMitLKZ() throws SQLException {    
        ZahlungskonditionenDAO zk = new ZahlungskonditionenDAO();     
        ObservableList<Zahlungskonditionen> zahlungskonditionen = FXCollections.observableArrayList(zk.gibAlleZahlungskonditionenMitLKZ());
        tv_zahlungskonditionen.setItems(zahlungskonditionen);
    }    
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Zahlungskonditionen ohne LKZ und stellt sie in der Tabelle dar.
     * @throws java.sql.SQLException SQL Exception
     */       
    @FXML
    public void alleOhneLKZ() throws SQLException {    
        ZahlungskonditionenDAO ad = new ZahlungskonditionenDAO();     
        ObservableList<Zahlungskonditionen> zahlungskonditionen 
                = FXCollections.observableArrayList(ad.gibAlleZahlungskonditionenOhneLKZ());
        tv_zahlungskonditionen.setItems(zahlungskonditionen);
    } 
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues
     * Zahlungskonditionen Objekt, welches dann über die DAO in die DB geschrieben wird.
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
    }     
    
}
