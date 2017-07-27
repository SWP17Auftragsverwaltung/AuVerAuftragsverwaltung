/*------------------------------------------------------------------------------
* Klasse: Auftragsposition.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zur Verwaltung der Auftragsposition.
*-------------------------------------------------------------------------------
* Historie:
* 2017-07-26 HEN Erstellt.
*-------------------------------------------------------------------------------
*/
package Klassen;

/**
 * @author Andre
 */
public class Auftragsposition {

    /**
     * Variablendeklaration
    */
    
    /**
     * Primärschlüssel der Auftragsposition. 
    */
    private int auftragskopfID;
    
    /**
     * Positionsnummer des Auftragskopfes. 
    */
    private int positionsnummer;
    
    /**
     * Fremdschlüssel der ArtikelID in der Position. 
    */
    private int artikelID;
    
    /**
     * Menge des Artikels in der Position. 
    */
    private int menge;
    
     /**
     * Einzelwert des Artikels in der Position. 
    */
    private double einzelwert;
    
    /**
     * gibt an ob das LKZ der Auftagsposition aktiviert ist. 
    */
    private boolean lkz;
    
    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * Konstruktor für Auftragspositionsobjekte.
     *
     * @param auftragskopfID AuftargskopfID
     * @param positionsnummer Positionsnummer
     * @param artikelID ArtikelID
     * @param menge Menge
     * @param einzelwert Einzelwert
     * @param lkz Löschkennzeichen
     */
    public Auftragsposition(int auftragskopfID, int positionsnummer, 
            int artikelID, int menge, 
            double einzelwert, boolean lkz) {

        this.auftragskopfID = auftragskopfID;
        this.positionsnummer = positionsnummer;
        this.artikelID = artikelID;
        this.einzelwert = einzelwert;
        this.menge = menge;
        this.lkz = lkz;
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * hashCode Methode für Auftragspositionobjekte.
     * @return int Wert mit hashCode des Auftragspositionobjektes
     */
    @Override
    public int hashCode() {
        return getAuftragskopfID() + getPositionsnummer();
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * equals Methode für Auftragspositionobjekte.
     * @param obj Objekt
     * @return Gleichheit von Auftragspositionobjekten
     */
    @Override
    public boolean equals(Object obj) {
        return gleicherArtikel(obj);
    }
    
    /**
     * Eigentlich die equals Methode, jedoch kann man diese von Außen aufrufen.
     * @param obj Zu prüfendes Objekt
     * @return Artikel gleich oder nicht
     */
    public boolean gleicherArtikel(Object obj) {
        boolean gleich = false;
        if (obj instanceof Auftragsposition) {
            Auftragsposition a = (Auftragsposition) obj;
            gleich = this.getArtikelID() == a.getArtikelID();
        }
        return gleich;
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * toString Methode für Auftragspositionobjekte.
     * @return Id vom Auftragspositionobjekt + Position Nr
     */
    @Override
    public String toString() {
        return "ID: " + auftragskopfID + " PN: " + positionsnummer;
    }

    
    /*------------------------------------------------
     *            Generierter Code Anfang
     *------------------------------------------------
    */
    
    /**
     * @return auftragskopfID 
     */
    public int getAuftragskopfID() {
        return auftragskopfID;
    }

    /**
     * @param auftragskopfID AuftragskopfID
     */
    public void setAuftragskopfID(int auftragskopfID) {
        this.auftragskopfID = auftragskopfID;
    }

    /**
     * @return positionsnummer 
     */
    public int getPositionsnummer() {
        return positionsnummer;
    }

    /**
     * @param positionsnummer Positionsnummer
     */
    public void setPositionsnummer(int positionsnummer) {
        this.positionsnummer = positionsnummer;
    }

    /**
     * @return artikelID 
     */
    public int getArtikelID() {
        return artikelID;
    }

    /**
     * @param artikelID ArtikelID 
     */
    public void setArtikelID(int artikelID) {
        this.artikelID = artikelID;
    }

    /**
     * @return menge 
     */
    public int getMenge() {
        return menge;
    }

    /**
     * @param menge Menge 
     */
    public void setMenge(int menge) {
        this.menge = menge;
    }

    /**
     * @return eunzelwert 
     */
    public double getEinzelwert() {
        return einzelwert;
    }

    /**
     * @param einzelwert Einzelwert
     */
    public void setEinzelwert(double einzelwert) {
        this.einzelwert = einzelwert;
    }

    /**
     * @return lkz 
     */
    public boolean isLkz() {
        return lkz;
    }

    /**
     * @param lkz Löschkennzeichen
     */
    public void setLkz(boolean lkz) {
        this.lkz = lkz;
    }

    /*------------------------------------------------
     *            Generierter Code Ende
     *------------------------------------------------
    */


}
