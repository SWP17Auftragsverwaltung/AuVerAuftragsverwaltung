/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auverauftragsverwaltung;

import Datenbank.AuftragskopfDAO;
import Datenbank.AuftragspositionDAO;
import Klassen.Auftragskopf;
import Klassen.Auftragsposition;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class.
 * @author Tobi
 */
public class AuftragPositionController implements Initializable {
    
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
     * Tabelle "Auftragskopf", Tabellenspalte "AuftragsID".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragsID;
    
     /**
     * Tabelle "Auftragskopf", Tabellenspalte "Text".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcText;
    
     /**
     * Tabelle "Auftragskopf", Tabellenspalte "GeschäftspartnerID".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcPartnerID;
    
    /**
     * Tabelle "Auftragskopf", Tabellenspalte "Erfassungsdatum".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcErfDatum;
    
    /**
     * Tabelle "Auftragskopf", Tabellenspalte "Lieferdatum".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcLieferDatum;
    
    /**
     * Tabelle "Auftragskopf", Tabellenspalte "Auftragsart".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragsart;
    
    /**
     * Tabelle "Auftragskopf", Tabellenspalte "Auftragswert".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragswert;
    
    /**
     * Tabelle "Auftragskopf", Tabellenspalte "Status".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcStatus;
    
    /**
     * Tabelle "Auftragskopf", Tabellenspalte "Abschlussdatum".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAbschlussDatum;
    
    /**
     * TableView für die Anzeige der Auftragspositionen.
     */
    @FXML
    private TableView tvAuftragsposition = new TableView<Auftragsposition>();
    
    /**
     * Tabelle "Auftragsposition", Tabellenspalte "AuftragskopfID".
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcAuftragskopfID;
    
    /**
     * Tabelle "Auftragsposition", Tabellenspalte "Positionsnummer".
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcPositionsNr;
    
    /**
     * Tabelle "Auftragsposition", Tabellenspalte "Materialnummer".
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcMaterialNr;
    
    /**
     * Tabelle "Auftragsposition", Tabellenspalte "Menge".
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcMenge;
    
    /**
     * Tabelle "Auftragsposition", Tabellenspalte "Einzelwert".
     */
    @FXML
    private TableColumn<Auftragsposition, String> tcEinzelwert;
    
    /**
     * TableView für die Anzeige des Auftragskopfes.
     */
    @FXML
    private TableView<Auftragsposition> tvAuftragskopf;
    
    /**
     * Der Schließen-Button in der Adressverwaltung.
     */
    @FXML
    private Button btClose;
    
    /**
     * Der Anlegen-Button in der Adressverwaltung.
     */
    @FXML
    private Button btAnlegen;
    
    /**
     * Der Löschen-Button in der Adressverwaltung.
     */
    @FXML
    private Button btLoeschen;
    
    /**
     * Der Bearbeiten-Button in der Adressverwaltung.
     */
    @FXML
    private Button btBearbeiten;
    
    /**
     * Der Abbrechen-Button in der Adressverwaltung.
     */
    @FXML
    private Button btAbbrechen;
    
    
    
    /**
     * Methode zum Abbrechen der Adressverwaltung.
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen"
     * abfängt.
     */
    @FXML
    public void closeAuftragsposition(ActionEvent event) {
        Stage stage = (Stage) btClose.getScene().getWindow();
        stage.close();
    }
    
    
    
    /**
     * Initializes the controller class.
     * @param url URL
     * @param rb RB
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setTableContent();
        
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText("Keine Auftragspositionen gefunden!");
            alert.showAndWait();
        }
        
        tcAuftragskopfID.setCellValueFactory(
                new PropertyValueFactory<>("auftragskopfID"));
        
        tcPositionsNr.setCellValueFactory(
                new PropertyValueFactory<>("positionsnummer"));        
        
        tcMaterialNr.setCellValueFactory(
                new PropertyValueFactory<>("artikelID"));
        
        tcMenge.setCellValueFactory(new PropertyValueFactory<>("menge"));
        
        tcEinzelwert.setCellValueFactory(
                new PropertyValueFactory<>("einzelwert"));
        
    }    



    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein AuftragspositionDAO Objekt und gibt eine Auftragsposition 
     * ArrayList an eine OberservableList, die dann an die TableView 
     * übergeben wird.
     * @throws java.sql.SQLException SQL Exception
     */
    public void setTableContent() throws SQLException {
        AuftragspositionDAO apd = new AuftragspositionDAO();
        ObservableList<Auftragsposition> auftragspositionen
            = FXCollections.observableArrayList(
                    apd.gibAlleAuftragspositionenOhneLKZ());
        tvAuftragsposition.setItems(auftragspositionen);
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Aktualisiert die TableView mit aktuellem Inhalt.
     * @throws java.sql.SQLException SQL Exception
     */
    public void refreshTable() throws SQLException {
        tvAuftragsposition.getItems().clear();
        setTableContent();
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Löscht alle Eingaben in den Textfeldern.
     */
    public void clearTextFields() {
        tfPositionsNr.clear();
        tfMaterialNr.clear();
        tfMenge.clear();
        tfEinzelwert.clear();
    }    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues 
     * Auftragsposition Objekt, welches dann über die DAO in die DB geschrieben 
     * wird.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void auftragspositionHinzufuegen() throws SQLException {
        String auftragskopfID = tcAuftragsID.getText();
        String positionsnummer = tfPositionsNr.getText();
        String artikelID = tfMaterialNr.getText();
        String menge = tfMenge.getText();
        String einzelwert = tfEinzelwert.getText();
        String lkz = "N";
        
        Auftragsposition auftragsposition = new Auftragsposition(auftragskopfID,
            positionsnummer, artikelID, menge, einzelwert, lkz);

        AuftragspositionDAO apd = new AuftragspositionDAO();
        apd.fuegeAuftragspositionHinzu(auftragsposition);

//        clearTextFields();
        refreshTable();
    }    


    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues 
     * Auftragsposition Objekt, welches dann über die DAO in die DB geschrieben 
     * wird.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void leereAuftragspositionHinzufuegen() throws SQLException {
        AuftragskopfDAO akd = new AuftragskopfDAO();
        
        String auftragskopfID = akd.gibLetztID();
        String positionsnummer = "";
        String artikelID = "";
        String menge = "";
        String einzelwert = "";
        String lkz = "N";
        
        Auftragsposition auftragsposition = new Auftragsposition(auftragskopfID,
            positionsnummer, artikelID, menge, einzelwert, lkz);

        AuftragspositionDAO apd = new AuftragspositionDAO();
        apd.fuegeAuftragspositionHinzu(auftragsposition);

//        clearTextFields();
        refreshTable();
    }
    
}
