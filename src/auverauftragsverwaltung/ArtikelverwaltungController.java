/*------------------------------------------------------------------------------
* Klasse: ArtikelverwaltungController.
*-------------------------------------------------------------------------------
* Zweck:
* - FXML Controller-Klasse.
*-------------------------------------------------------------------------------
* Historie:
* 2017-06-14 SAM Angelegt.
* 2017-06-26 GET Checkstyleprüfung.
*                Fehler bei Start der GUI behoben.
* 2017-07-27 BER Kommentarlayout angepasst.
*-------------------------------------------------------------------------------
*/
package auverauftragsverwaltung;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

/**
 *
 * @author Mudimbi
 */
public class ArtikelverwaltungController implements Initializable {
    
    @FXML
    private ComboBox cb_feldwahl;
    @FXML
    private TableView tv_artikel;
//    @FXML
//    private TextField tf_materialNr;
    @FXML
    private TextArea ta_artikelbeschreibung;
    @FXML
    private TextField tf_einzelwert;
    @FXML
    private TextArea ta_bestellbeschreibung;
    @FXML
    private TextField tf_bestellwert;

    /**
     *  Abbrechen-Button der Artikelverwaltung.
     */
    @FXML
    private Button closeArW;

    /**
     * Methode zum Abbrechen der Artikelverwaltung.
     *
     * @param event ActionEvent welches das Klicken des Buttons "Abbrechen"
     * abfängt.
     */
    @FXML
    public void closeArtikelverwaltung(ActionEvent event) {
        Stage stage = (Stage) closeArW.getScene().getWindow();
        stage.close();
    }

    /**
     * Initialisiert die Controller-Klasse der Artikelverwaltung.
     *
     * @param url URL zur initialisierung.
     * @param rb Resourcen die geladen werden sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       // Zeilenumbruch bei Artikelbeschreibung 
        ta_artikelbeschreibung.setWrapText(true);
        //  Artikelbeschreibung auf 250 Zeichen begrenzt
        ta_artikelbeschreibung.setTextFormatter(new TextFormatter<>(change -> 
            change.getControlNewText().length() <= 250 ? change : null));
        
        // Zeilenumbruch bei Bestellbeschreibung 
        ta_bestellbeschreibung.setWrapText(true);
        // Bestellbeschreibung auf 250 Zeichen begrenzt
        ta_bestellbeschreibung.setTextFormatter(new TextFormatter<>(change -> 
            change.getControlNewText().length() <= 250 ? change : null));
        
        // Einzelwert auf 6 Zeichen begrenzt
         tf_einzelwert.setTextFormatter(new TextFormatter<>(change -> 
            change.getControlNewText().length() <= 6 ? change : null));
        
         // bestellwert auf 6 Zeichen begrenzt
         tf_bestellwert.setTextFormatter(new TextFormatter<>(change -> 
            change.getControlNewText().length() <= 6 ? change : null));
        
        setComboBox(tv_artikel);

    }
    
    private void setComboBox(TableView tv){
        
        ObservableList<Object> liste;
        liste = tv.getColumns();

        this.cb_feldwahl.getItems().addAll(liste);
        
    }

}
