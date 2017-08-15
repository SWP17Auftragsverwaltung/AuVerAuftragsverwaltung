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
*-------------------------------------------------------------------------------
 */
package auverauftragsverwaltung;

import Datenbank.GeschaeftspartnerDAO;
import Klassen.Geschaeftspartner;
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
    private TextField lieferID;
    /**
     * Anschrift-ID des Geschäftspartners.
     */
    @FXML
    private TextField anschriftID;
    /**
     * Kreditlimit des Geschäftspartners.
     */
    @FXML
    private TextField kreditlimit;
    @FXML
    private TableColumn<Geschaeftspartner, String> gpID;
    @FXML
    private TableColumn<Geschaeftspartner, String> typ;
    @FXML
    private TableColumn<Geschaeftspartner, String> adressID;
    @FXML
    private TableColumn<Geschaeftspartner, String> liefID;
    @FXML
    private TableColumn<Geschaeftspartner, String> kredLimit;
    @FXML
    private AnchorPane tf_partnerID;
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

        begrenzeTextFeldEingabe(anschriftID, 6);
        begrenzeTextFeldEingabe(lieferID, 6);
        begrenzeTextFeldEingabe(kreditlimit, 6);
        
        gpID.setCellValueFactory(
                new PropertyValueFactory<>("geschaeftspartnerID"));
        typ.setCellValueFactory(new PropertyValueFactory<>("typ"));
        adressID.setCellValueFactory(new PropertyValueFactory<>("adresseID"));
        liefID.setCellValueFactory(new PropertyValueFactory<>("lieferID"));
        kredLimit.setCellValueFactory(
                new PropertyValueFactory<>("kreditlimit"));
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
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein GeschäftspartnerDAO Objekt, gibt eine GP ArrayList an eine
     * OberservableList, die dann an die TableView übergeben wird.
     * @throws java.sql.SQLException SQL Exception
     */
    public void setTableContent() throws SQLException {    
        GeschaeftspartnerDAO gd = new GeschaeftspartnerDAO();     
        ObservableList<Geschaeftspartner> geschaeftspartner 
                = FXCollections.observableArrayList(
                        gd.gibAlleGeschaeftspartner());
        gpTable.setItems(geschaeftspartner);
    }     

}
