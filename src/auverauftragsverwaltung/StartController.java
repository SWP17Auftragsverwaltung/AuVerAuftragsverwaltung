/*------------------------------------------------------------------------------
* Klasse: StartController.
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jakob
 */
public class StartController implements Initializable {

    /**
     * Methode zum öffnen der Artikelverwaltung durch das betätigen des Buttons
     * "Artikelverwaltung" im Startfenster.
     *
     * @param event ActionEvent zur Prüfung ob der "Artikelverwaltung" - Button
     * getätigt wurde.
     */
    @FXML
    public void oeffneArtikelverwaltung(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "Artikelverwaltung.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Artikelverwaltung");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load the Artikelverwaltung!");
        }
    }

    /**
     * Methode zum öffnen der Geschäftspartnerverwaltung durch das betätigen des
     * Buttons "Geschäftspartnerverwaltung" im Startfenster.
     *
     * @param event ActionEvent zur Prüfung ob der "Geschäftspartnerverwaltung"
     * - Button getätigt wurde.
     */
    @FXML
    public void oeffneGeschaeftspartner(ActionEvent event) {
        try {
            //Die Ressource wird bezogen
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "Geschaeftspartnerverwaltung.fxml"));
            // Die Ressource wird geladen und Abmessungen werden festgelegt
            Scene scene = new Scene(loader.load(), 800, 600);
            Stage stage = new Stage();
            stage.setTitle("Geschäftspartnerverwaltung");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load the Geschaeftspartnerverwaltung!");
        }
    }

    /**
     * Methode zum öffnen der Adressverwaltung durch das betätigen des Buttons
     * "Adressverwaltung" im Startfenster.
     *
     * @param event ActionEvent zur Prüfung ob der "Adressverwaltung"-Button
     * getätigt wurde.
     */
    @FXML
    public void oeffneAdressverwaltung(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "Adressverwaltung.fxml"));
            Scene scene = new Scene(loader.load(), 1024, 768);
            Stage stage = new Stage();
            stage.setTitle("Adressverwaltung");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load the Adressverwaltung!");
        }
    }

    /**
     * Methode zum öffnen der Zahlungskonditionverwaltung durch das betätigen
     * des Buttons "Zahlungskonditionverwaltung" im Startfenster.
     *
     * @param event ActionEvent zur Prüfung ob der
     * "Zahlungskonditionverwaltung"-Button getätigt wurde.
     */
    @FXML
    public void oeffneZahlungskondition(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "Zahlungskonditionen.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Zahlungskonditionsverwaltung");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load the Zahlungskonditionen!");
        }
    }

    /**
     * Methode zum öffnen der Auftragsanzeige durch das betätigen des Buttons
     * "Aufträge anzeigen" im Startfenster.
     *
     * @param event ActionEvent zur Prüfung ob der "Aufträge anzeigen"-Button
     * getätigt wurde.
     */
    @FXML
    public void zeigeAuftragAn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "AuftraegeAnzeigen.fxml"));
            Scene scene = new Scene(loader.load(), 1095, 600);
            Stage stage = new Stage();
            stage.setTitle("Aufträge Anzeigen");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {

            System.out.println("Can't load the Auftraege!");
        }
    }

    /**
     * Methode zum öffnen des Fensters zum Anlegen von Aufträgen durch das
     * betätigen des Buttons "Auftrag anlegen" im Startfenster.
     *
     * @param event ActionEvent zur Prüfung ob der "Auftrag anlegen"-Button
     * getätigt wurde.
     */
    @FXML
    public void legeAuftragAn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "AuftragAnlegen.fxml"));
            Scene scene = new Scene(loader.load(), 1024, 768);
            Stage stage = new Stage();
            stage.setTitle("Auftrag anlegen");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            System.out.println("Can't load the Auftrag anlegen!");
        }
    }
    
    /**
     * Methode zum öffnen des Fensters zum Suchen von Aufträgen durch das
     * betätigen des Buttons "Auftrag suchen" im Startfenster.
     *
     * @param event ActionEvent zur Prüfung ob der "Auftrag suchen"-Button
     * getätigt wurde.
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
     * Initialisiert die Controller-Klasse.
     * 
     * @param url URL zur initialisierung.
     * @param rb Resourcen die geladen werden sollen.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
