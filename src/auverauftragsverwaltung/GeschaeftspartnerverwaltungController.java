/*------------------------------------------------------------------------------
* Klasse: GeschaeftspartnerverwaltungController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 15.06.2017    SAM     Angelegt.
* 26.06.2017    GET     Checkstyleprüfung sowie Fehlerkorrektur.
* 27.07.2017    BER     Kommentarlayout angepasst.
* 14.08.2017    HEN     setTabelContent() erstellt, TableColums erstellt.
* 17.08.2017    CEL     
*-------------------------------------------------------------------------------
 */
package auverauftragsverwaltung;

import Datenbank.GeschaeftspartnerDAO;
import Klassen.Geschaeftspartner;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Mudimbi
 */
public class GeschaeftspartnerverwaltungController implements Initializable {

    /**
     * Hier wird die Geschätspartner Scene verwaltet. Der Zugriff auf die
     * Datenbank etc wird hier implementiert
     */
    @FXML
    private Button closeGP;
    /**
     * Liefer-ID des Lieferanten.
     */
    @FXML
    private TextField tf_lieferID;
    /**
     * Anschrift-ID des Geschäftspartners.
     */
    @FXML
    private TextField tf_anschriftID;  
    /**
     * ComboBox "Suchfeld".
     */
    @FXML
    private ComboBox<String> cb_suchfeld = new ComboBox();    
    /**
     * Kreditlimit des Geschäftspartners.
     */
    @FXML
    private TextField tf_kreditlimit;
    /**
     * PartnerTyp des Geschäftspartners.
     */   
    @FXML
    private ComboBox<String> cb_partnerTyp = new ComboBox();
    /**
     * ID des Geschäftspartners.
     */
    @FXML
    private TextField tf_geschaeftspartnerID;
    
    
    /**
     * Tabellenspalte "GeschäftspartnerID".
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> gpID;
     
    /**
     * Tabellenspalte "Typ".
     */    
    @FXML
    private TableColumn<Geschaeftspartner, String> typ;
    
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
    @FXML
    private AnchorPane tf_partnerID;
    
    /**
     * Geschäftspartnertabelle.
     */
    @FXML
    private TableView gpTable = new TableView<Geschaeftspartner>();

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

    /**
     * Initialisiert die Controller-Klasse.
     *
     * @param url Zu initialisierende URL.
     * @param rb Verwendete Ressourcen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

                try {
            setTableContent();
        } catch (SQLException ex) {
            Logger.getLogger(AdressverwaltungController.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
                
        //Die Geschäftspartner-ID enthält max. 6 Zeichen.        
        begrenzeTextFeldEingabe(tf_geschaeftspartnerID, 6);
        //Die Anschrift-ID enthält max. 6 Zeichen.
        begrenzeTextFeldEingabe(tf_anschriftID, 6);
        //Die Liefer-Id enthält max. 6 Zeichen.
        begrenzeTextFeldEingabe(tf_lieferID, 6);
        //Das Kreditlimit enthält max. 6 Zeichen.
        begrenzeTextFeldEingabe(tf_kreditlimit, 6);
        
        gpID.setCellValueFactory(
                new PropertyValueFactory<>("geschaeftspartnerID"));
        typ.setCellValueFactory(new PropertyValueFactory<>("typ"));
        adressID.setCellValueFactory(new PropertyValueFactory<>("adresseID"));
        liefID.setCellValueFactory(new PropertyValueFactory<>("lieferID"));
        kredLimit.setCellValueFactory(
                new PropertyValueFactory<>("kreditlimit"));
        
//        cb_suchfeld.getItems().addAll(
//                "GeschaeftspartnerID",
//                "AdresseID",
//                "LieferID",
//                "Kreditlimit");
        
        cb_partnerTyp.getItems().addAll("K", "L");
    }

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
    /* 17.08.17    CEL     Methoden "alleOhneLKZ" & "alleMitLKZ" hinzugefügt.
    */
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein GeschäftspartnerDAO Objekt, gibt eine GP ArrayList an eine
     * OberservableList, die dann an die TableView übergeben wird.
     * @throws java.sql.SQLException SQL Exception
     */
    public void setTableContent() throws SQLException {    
        GeschaeftspartnerDAO gp = new GeschaeftspartnerDAO();     
        ObservableList<Geschaeftspartner> geschaeftspartner 
                = FXCollections.observableArrayList(
                        gp.gibAlleGeschaeftspartner());
        gpTable.setItems(geschaeftspartner);
    }    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    CEL     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Geschätspartnern mit aktivem LKZ und stellt sie in der Tabelle
     * dar.
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
    /* 15.08.17    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
     /**
     * Sucht nach allen Geschätspartnern mit aktivem LKZ und stellt sie in der Tabelle
     * dar.
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
    /* 17.08.17    CEL     Methode erstellt.
    /* 18.08.17    CEL     Clear-Elemente hinzugefügt.
    */
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues
     * Geschätspartner Objekt, welches dann über die DAO in die DB geschrieben wird.
     * @throws java.sql.SQLException SQL Exception
     */
    public void geschaeftspartnerHinzufuegen() throws SQLException {
        String geschaeftspartnerID = tf_geschaeftspartnerID.getText();
        String typ = cb_partnerTyp.getValue();
        String adresseID = tf_anschriftID.getText();
        String lieferID = tf_lieferID.getText();
        String kreditlimit = tf_kreditlimit.getText();
        String lkz = "N";
        Geschaeftspartner geschaeftspartner = new Geschaeftspartner(geschaeftspartnerID,
        typ, adresseID, lieferID, kreditlimit, lkz);
        
        GeschaeftspartnerDAO gp = new GeschaeftspartnerDAO();
        gp.fuegeGeschaeftspartnerHinzu(geschaeftspartner);
        
           tf_lieferID.clear();
           tf_anschriftID.clear();
           tf_kreditlimit.clear();
           tf_geschaeftspartnerID.clear();
           cb_partnerTyp.valueProperty().set(null);
        
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 18.08.17    CEL     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * "Löscht" einen markierten Geschäftspartner, in dem das LKZ auf J gesetzt wird.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void geschaeftspartnerLoeschen() throws SQLException {

        Object geschaeftspartner = gpTable.getSelectionModel().getSelectedItem();
        Geschaeftspartner g = (Geschaeftspartner) geschaeftspartner;

        GeschaeftspartnerDAO gp = new GeschaeftspartnerDAO();
        gp.setzeLKZ(g);
    }
    
    @FXML
    public void geschaeftspartnerAendern() throws SQLException {
        
        Object geschaeftspartner = gpTable.getSelectionModel().getSelectedItem();
        Geschaeftspartner g = (Geschaeftspartner) geschaeftspartner;

        GeschaeftspartnerDAO gp = new GeschaeftspartnerDAO();
        gp.aendernGeschaeftspartner(g);
    }

}
