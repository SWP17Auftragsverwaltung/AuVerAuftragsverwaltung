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

import Klassen.Meldung;
import Datenbank.GeschaeftspartnerDAO;
import Datenbank.SucheDAO;
import Klassen.Geschaeftspartner;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
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
     * Textfeld "LieferID".
     */
    @FXML
    private TextField tfLieferID;
    
    /**
     * Textfeld "AnschriftID".
     */
    @FXML
    private TextField tfAnschriftID;
    
    /**
     * ComboBox "Suchfeld".
     */
    @FXML
    private ComboBox<String> cbSuchfeld = new ComboBox();

     /**
     * Textfeld "Suchbegriff".
     */
    @FXML
    private TextField tfSuchbegriff;
    
    /**
     * Kreditlimit des Geschäftspartners.
     */
    @FXML
    private TextField tfKreditlimit;
    
    /**
     * PartnerTyp des Geschäftspartners.
     */
    @FXML
    private ComboBox<String> cbPartnerTyp = new ComboBox();
    
    /**
     * ID des Geschäftspartners.
     */
    @FXML
    private TextField tfGeschaeftspartnerID;

    /**
     * Tabellenspalte "GeschäftspartnerID".
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> gpID;

    /**
     * Tabellenspalte "Typ".
     */
    @FXML
    private TableColumn<Geschaeftspartner, String> gpTyp;

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

//    @FXML
//    private AnchorPane tf_partnerID;
    
     /**
     * Pane "Pane".
     */
    @FXML
    private Pane pane;

    /**
     * Button "Hinzufügen".
     */
    @FXML
    private Button hinzufuegenBT;

    /**
     * Button "Anlegen".
     */
    @FXML
    private Button anlegenBT;

    /**
     * Button "Bearbeiten".
     */
    @FXML
    private Button bearbeitenBT;

    /**
     * Button "Speichern".
     */
    @FXML
    private Button speichernBT;

    /**
     * Button "Löschen".
     */
    @FXML
    private Button loeschenBT;

    /**
     * TitledPane "Datensatz".
     */
    @FXML
    private TitledPane datensatzTP;

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
        begrenzeTextFeldEingabe(tfGeschaeftspartnerID, 6);
        //Die Anschrift-ID enthält max. 6 Zeichen.
        begrenzeTextFeldEingabe(tfAnschriftID, 6);
        //Die Liefer-Id enthält max. 6 Zeichen.
        begrenzeTextFeldEingabe(tfLieferID, 6);
        //Das Kreditlimit enthält max. 6 Zeichen.
        begrenzeTextFeldEingabe(tfKreditlimit, 6);

        gpID.setCellValueFactory(
                new PropertyValueFactory<>("geschaeftspartnerID"));
        gpTyp.setCellValueFactory(new PropertyValueFactory<>("typ"));
        adressID.setCellValueFactory(new PropertyValueFactory<>("adresseID"));
        liefID.setCellValueFactory(new PropertyValueFactory<>("lieferID"));
        kredLimit.setCellValueFactory(
                new PropertyValueFactory<>("kreditlimit"));

        cbSuchfeld.getItems().addAll(
                "Geschaeftspartner-ID",
                "Geschäftspartner-Typ",
                "Anschrift-ID",
                "Liefer-ID",
                "Kreditlimit");

        cbPartnerTyp.getItems().addAll("K", "L");
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
     *
     * @throws java.sql.SQLException SQL Exception
     */
    public void setTableContent() throws SQLException {
        GeschaeftspartnerDAO gp = new GeschaeftspartnerDAO();
        ObservableList<Geschaeftspartner> geschaeftspartner
                = FXCollections.observableArrayList(
                        gp.gibAlleGeschaeftspartnerOhneLKZ());
        gpTable.setItems(geschaeftspartner);
    }

    /*------------------------------------------------------------------------*/
 /* Datum       Name    Was
    /* 17.08.17    CEL     Methode erstellt.
    /*------------------------------------------------------------------------*/
    /**
     * Sucht nach allen Geschätspartnern mit aktivem LKZ und stellt sie in der
     * Tabelle dar.
     *
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
     * Sucht nach allen Geschätspartnern mit aktivem LKZ und stellt sie in der
     * Tabelle dar.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    public void alleMitLKZ() throws SQLException {
        GeschaeftspartnerDAO gp = new GeschaeftspartnerDAO();
        ObservableList<Geschaeftspartner> geschaeftspartner
                = FXCollections.observableArrayList(
                        gp.gibAlleGeschaeftspartnerMitLKZ());
        gpTable.setItems(geschaeftspartner);
    }

    
    
    
    /**
     * Ermöglicht das Eingeben des Datensatzes für das Hinzufügen eines
     * Geschäftspartners.
     *
     * @throws java.sql.SQLException SQLException
     */
    public void geschaeftspartnerAnlegen() throws SQLException {

        gpTable.setMouseTransparent(true);
        clearTextFields();

        this.pane.setDisable(true);

        this.anlegenBT.setVisible(false);

        this.hinzufuegenBT.setVisible(true);

        this.datensatzTP.setText("Geschäftspartnerdatensatz (Anlegemodus)");

        this.bearbeitenBT.setDisable(true);

        this.loeschenBT.setDisable(true);

        GeschaeftspartnerDAO gpDAO = new GeschaeftspartnerDAO();
        this.tfGeschaeftspartnerID.setText(gpDAO.generiereID());

    }

    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues
     * Geschätspartner Objekt, welches dann über die DAO in die DB geschrieben
     * wird.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    public void geschaeftspartnerHinzufuegen() throws SQLException {

        String geschaeftspartnerID = tfGeschaeftspartnerID.getText();
        String typ = cbPartnerTyp.getValue();
        String adresseID = tfAnschriftID.getText();
        String lieferID = tfLieferID.getText();
        String kreditlimit = tfKreditlimit.getText();
        String lkz = "N";
        Geschaeftspartner geschaeftspartner = new Geschaeftspartner(
                geschaeftspartnerID, typ, adresseID, lieferID,
                kreditlimit, lkz);

        GeschaeftspartnerDAO gpDAO = new GeschaeftspartnerDAO();
        gpDAO.fuegeGeschaeftspartnerHinzu(geschaeftspartner);

        clearTextFields();
        refreshTable();

        // Textfeldbereich wird aktiviert
        this.pane.setDisable(false);
        // Bearbeiten-Button wird ausgeblendet
        this.anlegenBT.setVisible(true);
        // Speichern-Button wird eingeblendet
        this.hinzufuegenBT.setVisible(false);
        // Der Anlegemodus wird dektiviert
        this.datensatzTP.setText("Geschäftspartnerdatensatz");
        // Anlegen-Button wird deaktiviert
        this.bearbeitenBT.setDisable(false);
        // Löschen-Button wird deaktiviert
        this.loeschenBT.setDisable(false);
        gpTable.setMouseTransparent(false);

    }

    /**
     * Löscht alle Eingaben in den Textfeldern.
     */
    public void clearTextFields() {

        this.tfGeschaeftspartnerID.clear();
        this.cbPartnerTyp.setValue("Bitte wählen...");
        this.tfAnschriftID.clear();
        this.tfLieferID.clear();
        this.tfKreditlimit.clear();

    }

    /**
     * Aktualisiert die TableView mit aktuellem Inhalt.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    public void refreshTable() throws SQLException {
        this.gpTable.getItems().clear();
        setTableContent();
    }

    /*------------------------------------------------------------------------*/
 /* Datum       Name    Was
    /* 18.08.17    CEL     Methode erstellt.
    /*------------------------------------------------------------------------*/
    /**
     * "Löscht" einen markierten Geschäftspartner, in dem das LKZ auf J gesetzt
     * wird.
     *
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void geschaeftspartnerLoeschen() throws SQLException {

        Object geschaeftspartner = 
                gpTable.getSelectionModel().getSelectedItem();
        Geschaeftspartner g = (Geschaeftspartner) geschaeftspartner;

        if (!this.tfGeschaeftspartnerID.getText().isEmpty()) {
            Meldung meldung = new Meldung();
            meldung.loeschenAbfragen();

            if (meldung.antwort()) {
                GeschaeftspartnerDAO gp = new GeschaeftspartnerDAO();
                gp.setzeLKZ(g);

                refreshTable();
            } else {
                meldung.schließeFenster();
                clearTextFields();
            }
        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    GET     Methode erstellt.
    /* 22.08.17    HEN     Adressdatenpane geändert. Getestet & freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Lässt das Bearbeiten eines ausgewählten Geschäftspartners zu.
     * @throws java.sql.SQLException SQLException
     */
    @FXML
    public void bearbeiteGeschaeftspartner() throws SQLException {

        if (!this.tfGeschaeftspartnerID.getText().isEmpty()) {
            // Textfeldbereich wird aktiviert
            this.pane.setDisable(true);
            // Bearbeiten-Button wird ausgeblendet
            this.bearbeitenBT.setVisible(false);
            // Speichern-Button wird eingeblendet
            this.speichernBT.setVisible(true);
            // Der Bearbeitungsmodus des Adressdatensatzes wird aktiviert
            this.datensatzTP.setText("Geschäftspartnerdatensatz"
                    + " (Bearbeitungsmodus)");
            // Anlegen-Button wird deaktiviert
            this.anlegenBT.setDisable(true);
            // Löschen-Button wird deaktiviert
            this.loeschenBT.setDisable(true);

        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    GET     Methode erstellt.
    /* 22.08.17    HEN     Exceptions eingefügt. Getestet & Freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Speichert die gemachten Änderungen in die Datenbank und aktualisiert die
     * View mit den neuen Werten.
     * @throws java.sql.SQLException SQLException.
     */
    @FXML
    public void speichereAenderung() throws SQLException {

        String geschaeftspartnerID = this.tfGeschaeftspartnerID.getText();
        String typ = this.cbPartnerTyp.getValue();
        String anschriftID = this.tfAnschriftID.getText();
        String lieferID = this.tfLieferID.getText();
        String kreditlimit = this.tfKreditlimit.getText();
        String lkz = "N";

        Geschaeftspartner gp = new Geschaeftspartner(
                geschaeftspartnerID, typ, anschriftID, lieferID, 
                kreditlimit, lkz);

        GeschaeftspartnerDAO gpDAO = new GeschaeftspartnerDAO();
        gpDAO.aendernGeschaeftspartner(gp);

        refreshTable();

        // Textfeldbereich wird deaktivieren
        this.pane.setDisable(false);
        // Bearbeiten-Button wird ausgeblendet
        this.bearbeitenBT.setVisible(true);
        // Speichern-Button wird eingeblendet
        this.speichernBT.setVisible(false);
        // Der Bearbeitungsmodus des Adressdatensatzes wird aktiviert
        this.datensatzTP.setText("Geschäftspartnerdatensatz");
        // Anlegen-Button wird deaktiviert
        this.anlegenBT.setDisable(false);
        // Löschen-Button wird deaktiviert
        this.loeschenBT.setDisable(false);

    }

    /**
     * Zeigt die Werte einer ausgewählten Adresse im unteren Bereich an.
     */
    @FXML
    public void zeigeWerteAn() {
        Object geschaeftspartner = 
                gpTable.getSelectionModel().getSelectedItem();
        Geschaeftspartner b = (Geschaeftspartner) geschaeftspartner;

        if (b != null) {
            this.tfGeschaeftspartnerID.setText(b.getGeschaeftspartnerID());
            this.cbPartnerTyp.setValue(b.getTyp());
            this.tfAnschriftID.setText(b.getAdresseID());
            this.tfLieferID.setText(b.getLieferID());
            this.tfKreditlimit.setText(b.getKreditlimit());
        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    HEN     Methode erstellt.
    /* 18.08.17    BER     IF Fälle ergänzt. Getestet & Freigegeben.
    /*------------------------------------------------------------------------*/
    
    /**
     * Zeigt die Werte eines ausgewählten Geschäftspartners im unteren 
     * Bereich an.
     * @throws java.sql.SQLException SQLException
     */
    @FXML
    public void geschaeftspartnerSuchen() throws SQLException {

        SucheDAO gpDAO = new SucheDAO();
        ArrayList gefundeneGeschaeftspartner;

        String suchkriterium = this.cbSuchfeld.getValue();
        String suchbegriff = this.tfSuchbegriff.getText();

        gefundeneGeschaeftspartner = gpDAO.geschaeftspartnerSuche(
                suchkriterium, suchbegriff);

        zeigeGefundeneAdressen(gefundeneGeschaeftspartner);

    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Methode bekommt eine ArrayList mit den gefundenen Adressen übergeben und
     * aktualisiert damit die TableView.
     * @param adressen Übergebene Adresse.
     * @throws java.sql.SQLException SQL Exception
     */
    public void zeigeGefundeneAdressen(ArrayList adressen) throws SQLException {
        refreshTable();
        ObservableList<Geschaeftspartner> adressenAusgabe
                = FXCollections.observableArrayList(adressen);
        gpTable.setItems(adressenAusgabe);
    }

     /**
     *
     * @throws java.sql.SQLException SQLException
     */
    @FXML
    public void setzeSucheZurueck() throws SQLException {
        this.tfSuchbegriff.setText("");
        this.cbSuchfeld.setValue("Bitte wählen...");
        setTableContent();
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 19.08.17    GET     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt dem Benutzer die Möglichkeit, seine Aktionen abzubrechen oder zu
     * bestätigen.
     */
    @FXML
    public void aktionAbbrechen() {

        if (!this.datensatzTP.getText().equalsIgnoreCase(
                "Geschäftspartnerdatensatz")) {
            Meldung meldung = new Meldung();
            meldung.verwerfenFenster();

            if (meldung.antwort()) {

                // Textfeldbereich wird aktiviert
                this.pane.setDisable(false);
                // Bearbeiten-Button wird ausgeblendet
                this.anlegenBT.setVisible(true);
                // Speichern-Button wird eingeblendet

                // Der Bearbeitungsmodus des Adressdatensatzes wird aktiviert
                this.datensatzTP.setText("Geschäftspartnerdatensatz");

                // Anlegen-Button wird deaktiviert
                this.bearbeitenBT.setDisable(false);

                this.bearbeitenBT.setVisible(true);

                this.speichernBT.setVisible(false);

                this.anlegenBT.setDisable(false);
                // Löschen-Button wird deaktiviert
                this.loeschenBT.setDisable(false);

                this.hinzufuegenBT.setVisible(false);
                gpTable.setMouseTransparent(false);

                clearTextFields();
            } else {

                meldung.schließeFenster();

            }

        }
    }

}
