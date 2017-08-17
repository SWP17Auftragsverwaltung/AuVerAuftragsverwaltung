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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private TableView tv_zahlungskonditionen = new TableView<Zahlungskonditionen>();
    
    @FXML
    private TableColumn<Zahlungskonditionen, String> tc_zahlungskonditionsID;
    @FXML
    private TableColumn<Zahlungskonditionen, String> tc_auftragsart;
    @FXML
    private TableColumn<Zahlungskonditionen, String> tc_lieferzeit;
    @FXML
    private TableColumn<Zahlungskonditionen, String> tc_sperrzeit;
    @FXML
    private TableColumn<Zahlungskonditionen, String> tc_skontozeit1;
    @FXML
    private TableColumn<Zahlungskonditionen, String> tc_skonto1;
    @FXML
    private TableColumn<Zahlungskonditionen, String> tc_skontozeit2;
    @FXML
    private TableColumn<Zahlungskonditionen, String> tc_skonto2;
    @FXML
    private TableColumn<Zahlungskonditionen, String> tc_mahnzeit1;
    @FXML
    private TableColumn<Zahlungskonditionen, String> tc_mahnzeit2;
    @FXML
    private TableColumn<Zahlungskonditionen, String> tc_mahnzeit3;

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
     *
     * @param url URL zur initialisierung.
     * @param rb Resourcen die geladen werden sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        begrenzeTextFeldEingabe(tf_lieferzeitSOFORT, 4);
        begrenzeTextFeldEingabe(tf_sperrzeitWUNSCH, 4);
        begrenzeTextFeldEingabe(tf_mahnzeit1, 4);
        begrenzeTextFeldEingabe(tf_mahnzeit2, 4);
        begrenzeTextFeldEingabe(tf_mahnzeit3, 4);
        begrenzeTextFeldEingabe(tf_skontozeit1, 4);
        begrenzeTextFeldEingabe(tf_skonto1, 3);
        begrenzeTextFeldEingabe(tf_skontozeit2, 4);
        begrenzeTextFeldEingabe(tf_skonto2, 3);

        tc_zahlungskonditionsID.setCellValueFactory(
                new PropertyValueFactory<>("zahlungskonditionsID"));
        tc_auftragsart.setCellValueFactory(
                new PropertyValueFactory<>("auftragsart"));
        tc_lieferzeit.setCellValueFactory(
                new PropertyValueFactory<>("lieferzeit"));
        tc_sperrzeit.setCellValueFactory(
                new PropertyValueFactory<>("sperrzeit"));
        tc_skontozeit1.setCellValueFactory(
                new PropertyValueFactory<>("skontozeit1"));
        tc_skonto1.setCellValueFactory(
                new PropertyValueFactory<>("skonto1"));
        tc_skontozeit2.setCellValueFactory(
                new PropertyValueFactory<>("skontozeit2"));
        tc_skonto2.setCellValueFactory(
                new PropertyValueFactory<>("skonto2"));
        tc_mahnzeit1.setCellValueFactory(
                new PropertyValueFactory<>("mahnzeit1"));
        tc_mahnzeit2.setCellValueFactory(
                new PropertyValueFactory<>("mahnzeit2"));
        tc_mahnzeit3.setCellValueFactory(
                new PropertyValueFactory<>("mahnzeit3"));
        
        
    }

    private void begrenzeTextFeldEingabe(TextField tf, int zahl) {

        tf.setTextFormatter(new TextFormatter<>(change
                -> change.getControlNewText().length() <= zahl ? change : null));
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
                        zk.gibAlleZahlungskonditionen());
        tv_zahlungskonditionen.setItems(zahlungskonditionen);
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
     * Adress Objekt, welches dann über die DAO in die DB geschrieben wird.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void adresseHinzufuegen() throws SQLException {    
        String zahlungskonditionsID = tc_zahlungskonditionsID.getText();
        String auftragsart = tc_auftragsart.getText();
        String lieferzeit = tc_lieferzeit.getText();
        String sperrzeit = tc_sperrzeit.getText();
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
