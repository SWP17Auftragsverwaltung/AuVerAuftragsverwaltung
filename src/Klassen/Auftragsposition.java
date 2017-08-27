/*------------------------------------------------------------------------------
* Klasse: Auftragsposition.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zur Verwaltung der Auftragsposition.
*-------------------------------------------------------------------------------
* Historie:
* 26.07.2017    HEN     Erstellt.
* 28.07.2017    CEL     Erweitert.
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
    private String auftragskopfID;
    
    /**
     * Positionsnummer des Auftragskopfes. 
    */
    private String positionsnummer;
    
    /**
     * Fremdschlüssel der ArtikelID in der Position. 
    */
    private String artikelID;
    
    /**
     * Menge des Artikels in der Position. 
    */
    private String menge;
    
     /**
     * Einzelwert des Artikels in der Position. 
    */
    private String einzelwert;
    
    /**
     * gibt an ob das LKZ der Auftagsposition aktiviert ist. 
    */
    private String lkz;
    
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
    public Auftragsposition(String auftragskopfID, String positionsnummer, 
            String artikelID, String menge, String einzelwert, String lkz) {

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
        return getAuftragskopfID().hashCode() + getPositionsnummer().hashCode();
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
            gleich = this.getArtikelID().equals(a.getArtikelID());
        }
        return gleich;
    }

    
    
    /*------------------------------------------------
     *            Generierter Code Anfang
     *------------------------------------------------
    */
    
    /**
     * @return auftragskopfID 
     */
    public String getAuftragskopfID() {
        return auftragskopfID;
    }

    /**
     * @param auftragskopfID AuftragskopfID
     */
    public void setAuftragskopfID(String auftragskopfID) {
        this.auftragskopfID = auftragskopfID;
    }

    /**
     * @return positionsnummer 
     */
    public String getPositionsnummer() {
        return positionsnummer;
    }

    /**
     * @param positionsnummer Positionsnummer
     */
    public void setPositionsnummer(String positionsnummer) {
        this.positionsnummer = positionsnummer;
    }

    /**
     * @return artikelID 
     */
    public String getArtikelID() {
        return artikelID;
    }

    /**
     * @param artikelID ArtikelID 
     */
    public void setArtikelID(String artikelID) {
        this.artikelID = artikelID;
    }

    /**
     * @return menge 
     */
    public String getMenge() {
        return menge;
    }

    /**
     * @param menge Menge 
     */
    public void setMenge(String menge) {
        this.menge = menge;
    }

    /**
     * @return eunzelwert 
     */
    public String getEinzelwert() {
        return einzelwert;
    }

    /**
     * @param einzelwert Einzelwert
     */
    public void setEinzelwert(String einzelwert) {
        this.einzelwert = einzelwert;
    }

    /**
     * @return lkz 
     */
    public String getLkz() {
        return lkz;
    }

    /**
     * @param lkz Löschkennzeichen
     */
    public void setLkz(String lkz) {
        this.lkz = lkz;
    }
    /*------------------------------------------------
     *            Generierter Code Ende
     *------------------------------------------------
    */


}
