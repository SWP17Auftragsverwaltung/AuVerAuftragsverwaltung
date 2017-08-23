/*------------------------------------------------------------------------------
* Klasse: MyException.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zur Verwaltung von MyException.
*-------------------------------------------------------------------------------
* Historie:
* 26.07.2017    HEN     Erstellt.
* 04.08.2017    CEL     Erweitert.
* 07.08.2017    BER     Erweitert.
*-------------------------------------------------------------------------------
*/
package Klassen;

import javafx.scene.control.Alert;

/**
 *
 * @author Andre
 */
public class MyException extends Exception {
    
    /**
     * Konstruktor mit Fehlercode.
     * @param code Fehlercode.
     */
    public MyException(int code) {
        this.fehlerCode = code;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Fehler");
        alert.setHeaderText(null);
        alert.setContentText(getText());
        alert.showAndWait();
    }
    
    
    
    /**
     * Variable für den Fehlercode.
     */
    private int fehlerCode;
    /**
     * Variable für die Fehlermeldung.
     */
    private String fehlerMeldung;

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellung allgemeiner Fehlerexceptions.
     * 07.08.17  BER     Fehlerexceptions hinzugefügt.
     *--------------------------------------------------------------------------
     */
    /**
     * Standard Fehler.
     */
    private final String errorCode10
            = "Es ist ein unbekannter Fehler aufgetreten.";

    /**
     * Fehler wird geworfen, wenn ein SQL Fehler aufgetreten ist.
     */
    private final String errorCode20
            = "Es ist ein SQL Fehler aufgetreten.";

    /**
     * Fehler wird geworfen, wenn ein SQL Fehler aufgetreten ist.
     */
    private final String errorCode21
            = "Es konnte keine Verbindung hergestellt werden.";    
    
    /**
     * Fehler wird geworfen, wenn nicht alle Felder ausgefüllt wurden.
     */
    private final String errorCode30
            = "Bitte alle Felder ausfüllen!";

    /**
     * Fehler wird geworfen, wenn das Geburtsdatum in der Zukunft liegt.
     */
    private final String errorCode40
            = "Geburtstag darf nicht in der Zukunft liegen!";

    /**
     * Fehler wird geworfen, keine Eingabe bei der Suche.
     */
    private final String errorCode50
            = "Bitte Suchkriterium und Suchbegriff eingeben!";    



    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     * 04.08.17  CEL     Fehlermeldungen hinzugefügt.
     *--------------------------------------------------------------------------
     */
    /**
     * Gibt für den übergebenen Fehlercode eine spezifische Nachricht aus.
     *
     * @return Fehlermeldung für den Fehlercode.
     */
    public String getText() {

        switch (fehlerCode) {

            //Allgemeine Fehler
            case 10:
                fehlerMeldung = errorCode10;
                break;
            case 20:
                fehlerMeldung = errorCode20;
                break;
            case 21:
                fehlerMeldung = errorCode21;
                break;                
            case 30:
                fehlerMeldung = errorCode30;
                break;
            case 40:
                fehlerMeldung = errorCode40;
                break;
            case 50:
                fehlerMeldung = errorCode50;
                break;

                

            //Falls kein anderer Code passt.  
            default:
                fehlerMeldung = errorCode10;
        }
        return fehlerMeldung;
    }
    
    

}
