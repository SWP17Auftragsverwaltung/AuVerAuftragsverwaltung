/*------------------------------------------------------------------------------
* Klasse: Auftragskopf.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zur Verwaltung des Auftragskopfs.
*-------------------------------------------------------------------------------
* Historie:
* 26.07.2017    HEN     Erstellt.
* 26.07.2017    BER     Erweitert.
*-------------------------------------------------------------------------------
*/
package Klassen;

import java.util.ArrayList;

/**
 *
 * @author Andre
 */
public class Auftragskopf {

    /**
     * Variablendefinition
    */
    
    /**
     *Primärschküssel des Auftragskopfes.
    */
    private String auftragskopfID;
    
    /**
     * Fremdschlüssel des Geschäftspartners im Auftragskopf.
    */
    private String geschaeftspartnerID;
    
    /**
     * Erfassungsdatum des Auftragskopfes.
    */
    private String erfassungsdatum;
    
    /**
     * Auftragsart des Auftragskopfes.
    */
    private String auftragsart;    
    
    /**
     * Status des Auftragskopfes.
    */
    private String status;
    
    /**
     * Auftragswert des Auftragskopfes.
    */
    private String auftragswert;
    
    /**
     * Auftragstext des Auftragskopfes.
    */
    private String auftragstext;
    
    /**
     * Abschlussdatum des Auftragskopfes.
    */
    private String abschlussDatum;
    
     /**
     * Lieferdatum des Auftragskopfes.
     */
    private String lieferdatum;
    
    /**
     * LKZ des Auftragskopfes.
    */
    private String lkz;
    
//    /**
//     * Fremdschlüssel der Zahlungskonditionen im Auftragskopf.
//    */
//    private int zahlungskonditionsID;
    
    /**
     * Liste mit allen Auftagspositionen des Auftragskopfes.
    */
    private ArrayList<Auftragsposition> positionen;

    /**
     * 
     * @param auftragskopfID AuftragskopfID.
     */
    public Auftragskopf(String auftragskopfID) {
        this.auftragskopfID = auftragskopfID;
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     * 11.08.17  Ber     Lieferdatum hinzugefügt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * Konstruktor des Auftragskopf.
     *
     * @param auftragskopfID AuftragskopfID, Primärschlüssel
     * @param geschaeftspartnerID ID des Geschäftspartners
     * @param erfassungsdatum Datum wo Auftrag erfasst wird
     * @param lieferdatum Berechnetes Lieferdatum
     * @param auftragsart Art des Auftrages
     * @param status Status des Auftrages
     * @param auftragswert berechneter Auftragswert
     * @param auftragstext Text zum Auftrag
     * @param abschlussDatum vorrausichtliches Abschlussdatum
     * @param lkz Löschkennzeichen
     */
    public Auftragskopf(String auftragskopfID, String geschaeftspartnerID,
            String auftragstext, String erfassungsdatum, String lieferdatum, 
            String abschlussDatum, String status, String auftragsart, 
            String auftragswert, String lkz) {

        this.auftragskopfID = auftragskopfID;    
        this.geschaeftspartnerID = geschaeftspartnerID;
        this.erfassungsdatum = erfassungsdatum;
        this.lieferdatum = lieferdatum;
        this.auftragsart = auftragsart;
        this.status = status;
        this.auftragswert = auftragswert;
        this.auftragstext = auftragstext;  
        this.abschlussDatum = abschlussDatum;
        this.lkz = lkz;      
    }

    
    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */

    /**
     * toString Methode für Auftragskopf Objekte.
     * @return String Wert mit Auftragskopf-ID.
    */
    @Override
    public String toString() {
        return "ID: " + auftragskopfID + "STATUS" + status + " ERFASSUNG " 
                + erfassungsdatum; 
    }
    
    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */

    /**
     * hashCode Methode für Auftragskopf Objekte.
     * @return HashCode für Auftragskopfobjekte.
    */
    @Override
    public int hashCode() {
        return auftragskopfID.hashCode();
    }
    
    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */

    /**
     * Equals Methode für Auftragskopf Objekte.
     * @return boolescher Wert mit Gleichheit für Auftragskopfobjekte.
     * @param obj Objekt, das gegengeprüft wird.
    */
    @Override
    public boolean equals(Object obj) {
        Auftragskopf a = (Auftragskopf) obj;
        return (obj instanceof Auftragskopf)
                && this.auftragskopfID.equals(a.auftragskopfID);
    }

    /*------------------------------------------------
     *            Generierter Code Anfang
     *------------------------------------------------
    */
    
    /**
     * @return auftragskopfID AuftragskopfID. 
    */
    public String getAuftragskopfID() {
        return auftragskopfID;
    }

    /**
     * @param auftragskopfID AUftragskopf ID
    */
    public void setAuftragskopfID(String auftragskopfID) {
        this.auftragskopfID = auftragskopfID;
    }


    /**
     * @return the Geschaeftspartner
    */
    public String getGeschaeftspartnerID() {
        return geschaeftspartnerID;
    }

    /**
     * @param geschaeftspartnerID GeschäftsparterID
     */
    public void setGeschaeftspartnerID(String geschaeftspartnerID) {
        this.geschaeftspartnerID = geschaeftspartnerID;
    }

    /**
     * @return the Erfassungsdatum
    */
    public String getErfassungsdatum() {
        return erfassungsdatum;
    }

    /**
     * @param erfassungsdatum the Erfassungsdatum to set
    */
    public void setErfassungsdatum(String erfassungsdatum) {
        this.erfassungsdatum = erfassungsdatum;
    }

        /**
     * @return the Lieferdatum
     */
    public String getLieferdatum() {
        return lieferdatum;
    }

    /**
     * @param lieferdatum the Lieferdatum to set
     */
    public void setLieferdatum(String lieferdatum) {
        this.lieferdatum = lieferdatum;
    }
    
    /**
     * @return the Auftragsart
    */
    public String getAuftragsart() {
        return auftragsart;
    }

    /**
     * @param auftragsart the Auftragsart to set
    */
    public void setAuftragsart(String auftragsart) {
        this.auftragsart = auftragsart;
    }

    /**
     * @return the Status
    */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the Status to set
    */
    public void setStatus(String status) {
        this.status = status;
    }    
    
    /**
     * @return the Auftragswert
    */
    public String getAuftragswert() {
        return auftragswert;
    }

    /**
     * @param auftragswert the Auftragswert to set
    */
    public void setAuftragswert(String auftragswert) {
        this.auftragswert = auftragswert;
    }

    /**
     * @return the Auftragstext
    */
    public String getAuftragstext() {
        return auftragstext;
    }

    /**
     * @param auftragstext the Auftragstext to set
    */
    public void setAuftragstext(String auftragstext) {
        this.auftragstext = auftragstext;
    }    
    
    /**
     * @return the AbschlussDatum
    */
    public String getAbschlussDatum() {
        return abschlussDatum;
    }

    /**
     * @param abschlussDatum the AbschlussDatum to set
    */
    public void setAbschlussDatum(String abschlussDatum) {
        this.abschlussDatum = abschlussDatum;
    }

    /**
     * @return the LKZ
    */
    public String getLkz() {
        return lkz;
    }

    /**
     * @param lkz the LKZ to set
    */
    public void setLkz(String lkz) {
        this.lkz = lkz;
    }
//
//    /**
//     * @return the ZahlungskonditionsID
//    */
//    public int getZahlungskonditionsID() {
//        return zahlungskonditionsID;
//    }
//
//    /**
//     * @param zahlungskonditionsID the ZahlungskondiID to set
//    */
//    public void setZahlungskonditionsID(int zahlungskonditionsID) {
//        this.zahlungskonditionsID = zahlungskonditionsID;
//    }

    /**
     * @return the positionen
    */
    public ArrayList<Auftragsposition> getPositionen() {
        return positionen;
    }

    /**
     * @param positionen the positionen to set
    */
    public void setPositionen(ArrayList<Auftragsposition> positionen) {
        this.positionen = positionen;
    }

    /*------------------------------------------------
     *            Generierter Code Ende
     *------------------------------------------------
    */
 
}
