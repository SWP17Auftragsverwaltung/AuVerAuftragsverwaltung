/*------------------------------------------------------------------------------
* Klasse: Exceptions.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zur Verwaltung von Exceptions.
*-------------------------------------------------------------------------------
* Historie:
* 26.07.2017    HEN     Erstellt.
* 07.08.17      BER     Erweitert.
*-------------------------------------------------------------------------------
*/
package Klassen;

/**
 *
 * @author Andre
 */
public class Exceptions extends Exception {
    
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
    private final String errorCode100
            = "Es ist ein unbekannter Fehler aufgetreten.";
    
    /**
     * Fehler wird geworfen, wenn keine Datensätze gefunden wurden.
     */
    private final String errorCode101 
            = "Keine Datensätze in der Datenbank gefunden.";
   
    /**
     * Fehler wird geworfen, wenn es keine Ergebnisse zu den Suchkriterien gibt.
     */
    private final String errorCode102 
            = "Keine passenden Datensätze zu den Suchkriterien gefunden.";
    
    /**
     * Fehler wird geworfen, wenn Das Datum nicht dir korrekte Form hat.
     */
    private final String errorCode103 
            = "Datum ist nicht korrekt. Eingabe im DD.MM.YYYY Format.";
    
    /**
     * Fehler wird geworfen, wenn es keinen passenden Datensatz zur ID gibt.
     */
    private final String errorCode104 
            = "Kein passender Datensatz zu der ID vorhanden.";
    
    /**
     * Fehler wird geworfen, wenn kein Datensatz selektiert wurde.
     */
    private final String errorCode105 
            = "Es wurde kein Datensatz selektiert.";
    
    /**
    Fehler wird geworfen, wenn das Objekt nicht aktualisiert werden konnte.
    */
    private final String errorCode106 
            = "Objekt konnte nicht aktualisiert werden.";
    
    /**
     * Fehler wird geworfen, wenn das Objekt nicht hinzugefügt werden konnte.
     */
    private final String errorCode107
            = "Objekt konnte nicht hinzugefügt werden.";
    
    /**
     * Fehler wird geworfen, wenn das Objekt nicht gelöscht werden konnte.
     */
    private final String errorCode108 
            = "Objekt konnte nicht gelöscht werden.";
    
    /**
     * Fehler wird geworfen, wenn es einen Fehler bei der Suche gab.
     */
    private final String errorCode109 
            = "Fehler bei der Suche.";
    
    /**
     * Fehler wird geworfen, wenn ein SQL Fehler aufgetreten ist.
     */
    private final String errorCode110 
            = "Es ist ein SQL Fehler aufgetreten.";

    /**
     * Fehler wird geworfen, wenn nicht alle Felder ausgefüllt wurden.
     */
    private final String errorCode111 
            = "Bitte alle Felder ausfüllen!";
    
    /**
     * Fehler wird geworfen, wenn das Geburtsdatum in der Zukunft liegt.
     */
    private final String errorCode112 
            = "Geburtstag darf nicht in der Zukunft liegen!";    
    
    
    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstelltung Fehlerexception bezüglich Aufträge.
     * 07.08.17  BER     Fehlerexceptions hinzugefügt.
     *--------------------------------------------------------------------------
    */

    /**
     * Fehler wird geworfen, wenn der Auftragskopf keine Positionen hat.
     */
    private final String errorCode200 
            = "Der Auftragskopf muss mindestens eine Position enthalten.";
    
    /**
     * Fehler wird geworfen, wenn der Auftragskopf gelöscht werden soll.
     */
    private final String errorCode201 
            = "Der Auftragskopf kann nicht gelöscht werden.";
    
    /**
     * Fehler wird geworfen, wenn die Bestandsmenge nicht ausreicht.
     */
    private final String errorCode202 
            = "Bestandsmenge reicht für die Buchung nicht aus.";
    
    /**
     * Fehler wird geworfen, wenn Kreditlmit nicht ausreicht.
     */
    private String errorCode203 
            = "Kreditlimit des Geschäftspartner reicht nicht aus!";
    

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstelltung Fehlerexception bezüglich Artikel.
     * 07.08.17  BER     Fehlerexceptions hinzugefügt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * Fehler wird geworfen, wenn Artikel nicht gelöscht werden kann.
     */
    private final String errorCode300 
            = "Artikel kann nicht gelöscht werden, wird noch verwendet.";





    

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * Gibt für den übergebenen Fehlercode eine spezifische Nachricht aus.
     * @return Fehlermeldung für den Fehlercode.
    */
    public String getText() {

        switch (fehlerCode) {
            
            //Allgemeine Fehler
            case 100:
                fehlerMeldung = errorCode100;
                break;
            case 101:
                fehlerMeldung = errorCode101;
                break;
            case 102:
                fehlerMeldung = errorCode102;
                break;
            case 103:
                fehlerMeldung = errorCode103;
                break;
            case 104:
                fehlerMeldung = errorCode104;
                break;
            case 105:
                fehlerMeldung = errorCode105;
                break;
            case 106:
                fehlerMeldung = errorCode106;
                break;
            case 107:
                fehlerMeldung = errorCode107;
                break;
            case 108:
                fehlerMeldung = errorCode108;
                break;
            case 109:
                fehlerMeldung = errorCode109;
                break;
            case 110:
                fehlerMeldung = errorCode110;
                break;
            case 111:
                fehlerMeldung = errorCode111;
                break;
            case 112:
                fehlerMeldung = errorCode112;
                break;
                
                
            //Fehler bezüglich Aufträge         
            case 200:
                fehlerMeldung = errorCode200;
                break;           
            case 201:
                fehlerMeldung = errorCode201;
                break;
            case 202:
                fehlerMeldung = errorCode202;
                break;
            case 203:
                fehlerMeldung = errorCode203;
                break;
                

            //Fehler bezüglich Artikel
            case 300:
                fehlerMeldung = errorCode300;
                break;


            //Falls kein anderer Code passt.  
            default:
                fehlerMeldung = errorCode100;
        }

        return fehlerMeldung;

    }

    /**
     * Gibt den Fehlerdoce wieder.
     * @return fehlerCode
     */
    public int getCode() {
        return this.fehlerCode;
    }

    /**
     * Setz einen neuen Fehlercode.
     * @param code Code
     */
    public void setCode(int code) {
        this.fehlerCode = code;
    }    
    
}

