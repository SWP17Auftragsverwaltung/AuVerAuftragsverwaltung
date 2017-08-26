/*------------------------------------------------------------------------------
* Klasse: AufträgeAnzeigenController.
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

import Datenbank.AdresseDAO;
import Datenbank.AuftragskopfDAO;
import Klassen.Auftragskopf;
import Klassen.Auftragskopf;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Mudimbi
 */
public class AuftraegeAnzeigenController implements Initializable {
    /**
     *  Abbrechen-Button der Auftragsanzeige.
     */
    @FXML
    private Button closeAA;
    
    /**
     * Textfeld "Auftragskopf".
     */
    @FXML
    private TextField tfAuftragskopf;
    
    /**
     * Textfeld "Text zum Auftrag".
     */
    @FXML
    private TextArea tfText;
    
    /**
     * Textfeld "Partner-ID".
     */
    @FXML
    private TextField tfPartnerID;
    
    /**
     * Textfeld "Auftragswert".
     */
    @FXML
    private TextField tfAuftragswert;
    
    /**
     * Datepicker "Erfassungsdatum".
     */
    @FXML
    private DatePicker dpErfdatum;
    
    /**
     * Datepicker "Lieferdatum".
     */
    @FXML
    private DatePicker dpLieferdatum;
    
    /**
     * Datepicker "Abschlussdatum".
     */
    @FXML
    private DatePicker dpAbschlussdatum;
    
     /**
     * ComboBox "Auftragsstatus".
     */
    @FXML
    private ComboBox<String> cbAuftragsstatus = new ComboBox();
    
    /**
     * ComboBox "Suchfeld".
     */
    @FXML
    private ComboBox<String> cbAuftragsart = new ComboBox();
    
     /**
     * TableView für die Anzeige der Adressen.
     */
    @FXML
    private TableView tvAuftragskopf = new TableView<Auftragskopf>();
    
     /**
     * Tabellenspalte "Auftrags-ID".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragsID;
    
    /**
     * Tabellenspalte "Auftragstext".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragsText;
    
    /**
     * Tabellenspalte "Kunde/Lieferant-ID".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcgeschaeftspartnerID;
    
    /**
     * Tabellenspalte "Erfassungsdatum".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcErfDatum;
    
    /**
     * Tabellenspalte "Lieferdatum".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcLieferDatum;
    
    /**
     * Tabellenspalte "Auftragsart".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragsart;
    
    /**
     * Tabellenspalte "Auftragswert".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAuftragswert;
    
    /**
     * Tabellenspalte "Auftragsstatus".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcStatus;
    
    /**
     * Tabellenspalte "Abschlussdatum".
     */
    @FXML
    private TableColumn<Auftragskopf, String> tcAbschDatum;
    
        /**
     * Methode zum öffnen der Artikelverwaltung durch das betätigen des Buttons
     * "Artikelverwaltung" im Startfenster.
     *
     * @param event ActionEvent zur Prüfung ob der "Artikelverwaltung" - Button
     * getätigt wurde.
     */
    @FXML
    public void oeffneAuftragsposition(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "AuftragPosition.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Auftragsposition");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load the Auftragsposition!");
        }
    }

    /**
     * 
     * Mehtode die das öffnen der Suchmaske für Aufträge, durch den Button
     * "Auftrag suchen" ermöglicht.
     * 
     * @param event ActionEvent zur Prüfung ob der "Auftrag suchen" -
     *              Button getätigt wurde.
     */
    @FXML
    public void sucheAuftrag(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "AuftragSuchen.fxml"));
            Scene scene = new Scene(loader.load(), 755, 500);
            Stage stage = new Stage();
            stage.setTitle("Auftrag suchen");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load the AuftragSuchen!");
        }
    }

    /**
     * Methode zum Abbrechen der Auftragsanzeige.
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen" 
     *              abfängt.
     */
    @FXML
    public void closeAuftraegeAnzeigen(ActionEvent event) {
        Stage stage = (Stage) closeAA.getScene().getWindow();
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
        try {   
            setTableContent();
            
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText("Keine Adressen gefunden!");
            alert.showAndWait();
        }
        
        tcAuftragsID.setCellValueFactory(
                new PropertyValueFactory<>("auftragskopfID"));
        tcAuftragsText.setCellValueFactory(
                new PropertyValueFactory<>("auftragstext"));
        tcgeschaeftspartnerID.setCellValueFactory(
                new PropertyValueFactory<>("geschaeftspartnerID"));
        tcErfDatum.setCellValueFactory(
                new PropertyValueFactory<>("erfassungsdatum"));
        tcLieferDatum.setCellValueFactory(
                new PropertyValueFactory<>("lieferdatum"));
        tcAuftragsart.setCellValueFactory(
                new PropertyValueFactory<>("auftragsart"));
        tcAuftragswert.setCellValueFactory(
                new PropertyValueFactory<>("auftragswert"));
        tcStatus.setCellValueFactory(
                new PropertyValueFactory<>("status"));
        tcAbschDatum.setCellValueFactory(
                new PropertyValueFactory<>("abschlussDatum"));
        
        cbAuftragsstatus.getItems().addAll(
                "Erfasst", 
                "Freigegeben", 
                "abgeschlossen");
        
        cbAuftragsart.getItems().addAll(
                "Barauftrag",
                "Sofortauftrag", 
                "Terminauftrag",
                "Bestellauftrag");
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
        tvAuftragskopf.getItems().clear();
        setTableContent();
    } 

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Löscht alle Eingaben in den Textfeldern.
     */
    public void clearTextFields() {
        tfAuftragskopf.clear();
        tfText.clear();
        tfPartnerID.clear();
        dpErfdatum.valueProperty().set(null);
        dpLieferdatum.valueProperty().set(null);
        cbAuftragsart.valueProperty().set(null);
        tfAuftragswert.clear();
        cbAuftragsstatus.valueProperty().set(null);
        dpAbschlussdatum.valueProperty().set(null);
        cbAuftragsstatus.valueProperty().set(null);
        cbAuftragsart.valueProperty().set(null);
    }    
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 11.08.17    HEN     Methode erstellt.
    /* 12.08.17    HEN     ObservableArrayList hinzugefügt
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein AdressDAO Objekt und gibt eine Adress ArrayList an eine
     * OberservableList, die dann an die TableView übergeben wird.
     *
     * @throws java.sql.SQLException SQL Exception
    */
    public void setTableContent() throws SQLException {
        AuftragskopfDAO ak = new AuftragskopfDAO();
        ObservableList<Auftragskopf> auftragskopf
            = FXCollections.observableArrayList(
                    ak.gibAlleAuftragskoepfeOhneLKZ());
        tvAuftragskopf.setItems(auftragskopf);
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Adressen mit aktivem LKZ und stellt sie in der Tabelle
     * dar.
     *
     * @throws java.sql.SQLException SQL Exception
    */
    public void alleMitLKZ() throws SQLException {
        AuftragskopfDAO ak = new AuftragskopfDAO();
        ObservableList<Auftragskopf> adressen
                = FXCollections.observableArrayList(
                        ak.gibAlleAuftragskoepfeMitLKZ());
        tvAuftragskopf.setItems(adressen);
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * "Löscht" eine markierte Adresse, in dem das LKZ auf J gesetzt wird.
     * Aktualisiert anschließend die TableView.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void auftragskopfLoeschen() throws SQLException {
        Object auftragskopf = 
                tvAuftragskopf.getSelectionModel().getSelectedItem();
        Auftragskopf b = (Auftragskopf) auftragskopf;

        AuftragskopfDAO ak = new AuftragskopfDAO();
        ak.setzeLKZ(b);
        
        refreshTable();
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 26.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues.
     * Auftragskopf Objekt, welches dann über die DAO in die DB geschrieben wird
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void auftragHinzufuegen() throws SQLException {
        String auftragskopfID = tfAuftragskopf.getText();
        String geschaeftspartnerID = tfPartnerID.getText();
        String auftragsText = tfText.getText();
        String erfassungsDatum = dpErfdatum.getValue().toString();
        String lieferDatum = dpLieferdatum.getValue().toString();
        String abschlussDatum = dpAbschlussdatum.getValue().toString();
        String status = cbAuftragsstatus.getValue();
        String auftragsArt = cbAuftragsart.getValue();
        String auftragsWert = tfAuftragswert.getText();
        String lkz = "N";
        
        Auftragskopf auftragskopf = new Auftragskopf(auftragskopfID, 
                geschaeftspartnerID, auftragsText, erfassungsDatum, lieferDatum,
                abschlussDatum, status, auftragsArt, auftragsWert, lkz);

        AuftragskopfDAO ad = new AuftragskopfDAO();
        ad.fuegeAuftragHinzu(auftragskopf);

        clearTextFields();
        refreshTable();
    }
  

}
