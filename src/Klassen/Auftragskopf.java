/*------------------------------------------------------------------------------
* Klasse: Auftragskopf.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zur Verwaltung des Auftragskopfs.
*-------------------------------------------------------------------------------
* Historie:
* 2017-06-26 HEN Erstellt.
*-------------------------------------------------------------------------------
*/
package Klassen;

import java.sql.Date;
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
    private int auftragskopfID;
    
    /**
     * Fremdschlüssel des Geschäftspartners im Auftragskopf.
    */
    private int geschaeftspartnerID;
    
    /**
     * Erfassungsdatum des Auftragskopfes.
    */
    private java.sql.Date erfassungsdatum;
    
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
    private double auftragswert;
    
    /**
     * Auftragstext des Auftragskopfes.
    */
    private String auftragstext;
    
    /**
     * Abschlussdatum des Auftragskopfes.
    */
    private java.sql.Date abschlussDatum;
    
    /**
     * LKZ des Auftragskopfes.
    */
    private boolean lkz;
    
    /**
     * Fremdschlüssel der Zahlungskonditionen im Auftragskopf.
    */
    private int zahlungskonditionsID;
    
    /**
     * Liste mit allen Auftagspositionen des Auftragskopfes.
    */
    private ArrayList<Auftragsposition> positionen;

    /**
     * 
     * @param auftragskopfID AuftragskopfID.
     */
    public Auftragskopf(int auftragskopfID) {
        this.auftragskopfID = auftragskopfID;
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * Konstruktor des Auftragskopf.
     *
     * @param auftragskopfID AuftragskopfID, Primärschlüssel
     * @param geschaeftspartnerID ID des Geschäftspartners
     * @param erfassungsdatum Datum wo Auftrag erfasst wird
     * @param auftragsart Art des Auftrages
     * @param status Status des Auftrages
     * @param auftragswert berechneter Auftragswert
     * @param auftragstext Text zum Auftrag
     * @param abschlussDatum vorrausichtliches Abschlussdatum
     * @param lkz Löschkennzeichen
     * @param zahlungskonditionsID ID der Zahlungskonditionen
     */
    public Auftragskopf(int auftragskopfID, int geschaeftspartnerID,
            Date erfassungsdatum, String auftragsart, String status,
            double auftragswert, String auftragstext, Date abschlussDatum, 
            boolean lkz, int zahlungskonditionsID) {

        this.auftragskopfID = auftragskopfID;    
        this.geschaeftspartnerID = geschaeftspartnerID;
        this.erfassungsdatum = erfassungsdatum;       
        this.auftragsart = auftragsart;
        this.status = status;
        this.auftragswert = auftragswert;
        this.auftragstext = auftragstext;  
        this.abschlussDatum = abschlussDatum;
        this.lkz = lkz;      
        this.zahlungskonditionsID = zahlungskonditionsID;
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
        return auftragskopfID;
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
                && this.auftragskopfID == a.auftragskopfID;
    }

    /*------------------------------------------------
     *            Generierter Code Anfang
     *------------------------------------------------
    */
    
    /**
     * @return auftragskopfID AuftragskopfID. 
    */
    public int getAuftragskopfID() {
        return auftragskopfID;
    }

    /**
     * @param auftragskopfID AUftragskopf ID
    */
    public void setAuftragskopfID(int auftragskopfID) {
        this.auftragskopfID = auftragskopfID;
    }


    /**
     * @return the Geschaeftspartner
    */
    public int getGeschaeftspartnerID() {
        return geschaeftspartnerID;
    }

    /**
     * @param geschaeftspartnerID GeschäftsparterID
     */
    public void setGeschaeftspartnerID(int geschaeftspartnerID) {
        this.geschaeftspartnerID = geschaeftspartnerID;
    }

    /**
     * @return the Erfassungsdatum
    */
    public Date getErfassungsdatum() {
        return erfassungsdatum;
    }

    /**
     * @param erfassungsdatum the Erfassungsdatum to set
    */
    public void setErfassungsdatum(Date erfassungsdatum) {
        this.erfassungsdatum = erfassungsdatum;
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
    public double getAuftragswert() {
        return auftragswert;
    }

    /**
     * @param auftragswert the Auftragswert to set
    */
    public void setAuftragswert(double auftragswert) {
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
    public Date getAbschlussDatum() {
        return abschlussDatum;
    }

    /**
     * @param abschlussDatum the AbschlussDatum to set
    */
    public void setAbschlussDatum(Date abschlussDatum) {
        this.abschlussDatum = abschlussDatum;
    }

    /**
     * @return the LKZ
    */
    public boolean getLKZ() {
        return lkz;
    }

    /**
     * @param lkz the LKZ to set
    */
    public void setLKZ(boolean lkz) {
        this.lkz = lkz;
    }

    /**
     * @return the ZahlungskonditionsID
    */
    public int getZahlungskonditionsID() {
        return zahlungskonditionsID;
    }

    /**
     * @param zahlungskonditionsID the ZahlungskondiID to set
    */
    public void setZahlungskonditionsID(int zahlungskonditionsID) {
        this.zahlungskonditionsID = zahlungskonditionsID;
    }

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
