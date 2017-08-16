/*------------------------------------------------------------------------------
* Klasse: AdressverwaltungController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 14.06.2017    SAM     Angelegt.
* 26.06.2017    GET     Checkstyleprüfung.
* 27.07.2017    BER     Kommentarlayout angepasst.
* 12.08.2017    HEN     setTable() content erstellt, initialize() angepasst. 
*-------------------------------------------------------------------------------
 */
package auverauftragsverwaltung;

import Datenbank.AdresseDAO;
import Klassen.Adresse;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 *
 * @author Mudimbi
 */
public class AdressverwaltungController implements Initializable {

    /**
     * Der Abbrechen-Button in der Adressverwaltung.
     */
    @FXML
    private Button closeAW;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_vorname;
    @FXML
    private TextField tf_telefon;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_strasse;
    @FXML
    private TextField tf_hausNr;
    @FXML
    private TextField tf_plz;
    @FXML
    private TextField tf_ort;
    @FXML
    private TextField tf_staat;
    @FXML
    private TextField tf_datum;
    @FXML
    private ComboBox<String> cb_suchfeld = new ComboBox();
    @FXML
    private TextField tf_suchbegriff;
    @FXML
    private TextField tf_anschriftID;  
    @FXML
    private ComboBox<String> cb_anrede = new ComboBox();
    
    
    @FXML
    private TableView adresseTV = new TableView<Adresse>();
    /**
     * Tabellenspalte "AnschriftID".
     */
    @FXML
    private TableColumn<Adresse, String> Anschrift_ID;
    
    /**
     * Tabellenspalte "Anrede".
     */
    @FXML
    private TableColumn<Adresse, String> Anrede;
    
    /**
     * Tabellenspalte "Name".
     */
    @FXML
    private TableColumn<Adresse, String> Name;
    
    /**
     * Tabellenspalte "Vorname".
     */
    @FXML
    private TableColumn<Adresse, String> Vorname;
    
    /**
     * Tabellenspalte "Straße".
     */
    @FXML
    private TableColumn<Adresse, String> Straße;
    
    /**
     * Tabellenspalte "HausNr".
     */
    @FXML
    private TableColumn<Adresse, String> HausNr;
    
    /**
     * Tabellenspalte "PLZ".
     */
    @FXML
    private TableColumn<Adresse, String> PLZ;
    
    /**
     * Tabellenspalte "Ort".
     */
    @FXML
    private TableColumn<Adresse, String> Ort;
    
    /**
     * Tabellenspalte "Staat".
     */
    @FXML
    private TableColumn<Adresse, String> Staat;
    
    /**
     * Tabellenspalte "Tel".
     */
    @FXML
    private TableColumn<Adresse, String> Tel;
    
    /**
     * Tabellenspalte "Email".
     */
    @FXML
    private TableColumn<Adresse, String> EMail;  

    /**
     * Tabellenspalte "erfDatum".
     */
    @FXML
    private TableColumn<Adresse, String> ErfDatum;
    
    /**
     * Tabellenspalte LKZ.
     */
    @FXML
    private TableColumn<Adresse, String> LKZ;
 

    /**
     * Methode zum Abbrechen der Adressverwaltung.
     *
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen"
     * abfängt.
     */
    @FXML
    public void closeAdressverwaltung(ActionEvent event) {
        Stage stage = (Stage) closeAW.getScene().getWindow();
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
        // Name auf 20 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_name, 20);
        
        // Vorname auf 20 Zeichen begrenzt   
        begrenzeTextFeldEingabe(tf_vorname, 20);
          
        // Telefon auf 20 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_telefon, 20);
        
        // E-Mail auf 100 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_email, 100);
        
        // Strasse auf 30 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_strasse, 30);
               
        // Hausnummer auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_hausNr, 6);
        
        // PLZ auf 6 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_plz, 6);    
        
        // Ort auf 30 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_ort, 30);   
        
        // Staat auf 30 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_staat, 30);
        
        // Datum auf 10 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_datum, 10);
        
        // Datum auf 10 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_anschriftID, 6);
          
        Anschrift_ID.setCellValueFactory(
                new PropertyValueFactory<>("adresseID"));            
        
        Anrede.setCellValueFactory(new PropertyValueFactory<>("anrede"));     
        
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));   
        
        Vorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));     
        
        Straße.setCellValueFactory(new PropertyValueFactory<>("strasse"));      
        
        HausNr.setCellValueFactory(
                new PropertyValueFactory<>("hausnummer"));            
        
        PLZ.setCellValueFactory(new PropertyValueFactory<>("plz"));     
        
        Ort.setCellValueFactory(new PropertyValueFactory<>("ort"));       
        
        Staat.setCellValueFactory(
                new PropertyValueFactory<>("staat"));            
        
        Tel.setCellValueFactory(
                new PropertyValueFactory<>("telefon"));             
        
        EMail.setCellValueFactory(new PropertyValueFactory<>("Email"));       
        
        ErfDatum.setCellValueFactory(
                new PropertyValueFactory<>("erfassungsdatum"));
        
        LKZ.setCellValueFactory(
                new PropertyValueFactory<>("lkz"));
        
        cb_suchfeld.getItems().addAll(
                "AnschriftID", 
                "Name",
                "Vorname",
                "Straße",
                "HausNr",
                "PLZ",
                "Ort",
                "Staat",
                "Tel",
                "Email",
                "ErfDatum");
        
        cb_anrede.getItems().addAll("Herr", "Frau");
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
    /* 11.08.17    HEN     Methode erstellt.
    /* 12.08.17    HEN     ObservableArrayList hinzugefügt
    /*------------------------------------------------------------------------*/
    
    /**
     * Erstellt ein AdressDAO Objekt und gibt eine Adress ArrayList an eine
     * OberservableList, die dann an die TableView übergeben wird.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void setTableContent() throws SQLException {    
        AdresseDAO ad = new AdresseDAO();     
        ObservableList<Adresse> adressen 
                = FXCollections.observableArrayList(ad.gibAlleAdressen());
        adresseTV.setItems(adressen);
    }
    

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Adressen mit aktivem LKZ und stellt sie in der Tabelle
     * dar.
     * @throws java.sql.SQLException SQL Exception
     */    
    @FXML
    public void alleMitLKZ() throws SQLException {    
        AdresseDAO ad = new AdresseDAO();     
        ObservableList<Adresse> adressen 
                = FXCollections.observableArrayList(ad.gibAlleAdressenMitLKZ());
        adresseTV.setItems(adressen);
    }    
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht nach allen Adressen ohne LKZ und stellt sie in der Tabelle dar.
     * @throws java.sql.SQLException SQL Exception
     */       
    public void alleOhneLKZ() throws SQLException {    
        AdresseDAO ad = new AdresseDAO();     
        ObservableList<Adresse> adressen 
                = FXCollections.observableArrayList(
                        ad.gibAlleAdressenOhneLKZ());
        adresseTV.setItems(adressen);
    }    
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die Daten aus den Eingabefeldern aus und erstellt ein neues
     * Adress Objekt, welches dann über die DAO in die DB geschrieben wird.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void adresseHinzufuegen() throws SQLException {    
        String anschriftID = tf_anschriftID.getText();
        String anrede = cb_anrede.getValue();
        String name = tf_name.getText();
        String vorname = tf_vorname.getText();
        String strasse = tf_strasse.getText();
        String hausnr = tf_hausNr.getText();
        String plz = tf_plz.getText();
        String ort = tf_ort.getText();
        String staat = tf_staat.getText();
        String tel = tf_telefon.getText();
        String email = tf_email.getText();
        String erfdatum = tf_datum.getText();
        String lkz = "N";
        Adresse adresse = new Adresse(anschriftID, anrede, name, vorname, 
                strasse, hausnr, plz, ort, staat, tel, email, erfdatum, lkz);
        
        AdresseDAO ad = new AdresseDAO();     
        ad.fuegeAdresseHinzu(adresse);
    }
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * "Löscht" eine markierte Adresse, in dem das LKZ auf J gesetzt wird.
     * @throws java.sql.SQLException SQL Exception
     */
    @FXML
    public void adresseLoeschen() throws SQLException {  
        
        Object o = adresseTV.getItems().get(13);
        
        System.out.println(o);

    }         
}
