/*------------------------------------------------------------------------------
* Klasse: AdressverwaltungController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 2017-06-14 SAM Angelegt.
* 2017-06-26 GET Checkstyleprüfung.
* 2017-07-27 BER Kommentarlayout angepasst.
*-------------------------------------------------------------------------------
 */
package auverauftragsverwaltung;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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
    private TextField tf_erfassungsdatum;

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
        
        // E-Mail auf 30 Zeichen begrenzt
        begrenzeTextFeldEingabe(tf_email, 30);
        
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

    }
    
    private void begrenzeTextFeldEingabe(TextField tf , int zahl){
        
        tf.setTextFormatter(new TextFormatter<>(change
                -> {
            return change.getControlNewText().length() <= zahl ? change : null;
        }));
        
    }

}
