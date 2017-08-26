/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auverauftragsverwaltung;

import Klassen.Auftragskopf;
import Klassen.Auftragsposition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tobi
 */
public class AuftragPositionController implements Initializable {
    
     /**
     * Der Abbrechen-Button in der Adressverwaltung.
     */
    @FXML
    private Button closeAW;
    
     /**
     * Textfeld für die Eingabe der Positionsnummer.
     */
    @FXML
    private TextField tfPositionsNr;
    
    /**
     * Textfeld für die Eingabe der Materialnummer.
     */
    @FXML
    private TextField tfMaterialNr;
    
    /**
     * Textfeld für die Eingabe der Menge.
     */
    @FXML
    private TextField tfMenge;
    
    /**
     * Textfeld für die Eingabe des Einzelwerts.
     */
    @FXML
    private TextField tfEinzelwert;
    
     /**
     * TableView für die Anzeige der Adressen.
     */
//    @FXML
//    private TableView tvAuftragskopf = new TableView<Auftragskopf>();
    
    /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragsID;
    
     /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcText;
    
     /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcPartnerID;
    
    /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcErfDatum;
    
    /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcLieferDatum;
    
    /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragsart;
    
    /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragswert;
    
    /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcStatus;
    
    /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAbschlussDatum;
    
    
    
    /**
     * TableView für die Anzeige der Adressen.
     */
//    @FXML
//    private TableView tvAuftragsposition = new TableView<Auftragsposition>();
    
    /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcAuftragskopfID;
    
    /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcPositionsNr;
    
    /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcMaterialNr;
    
    /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcMenge;
    
    /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcEinzelwert;
    
     /**
     * Methode zum Abbrechen der Adressverwaltung.
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen"
     * abfängt.
     */
    @FXML
    public void closeAuftragsposition(ActionEvent event) {
        Stage stage = (Stage) closeAW.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
