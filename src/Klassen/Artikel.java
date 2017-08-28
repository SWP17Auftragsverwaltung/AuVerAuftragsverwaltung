/*------------------------------------------------------------------------------
* Klasse: Adresse.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zur Verwaltung der Artikel.
*-------------------------------------------------------------------------------
* Historie:
* 26.07.2017    HEN     Erstellt.
* 26.07.2017    BER     Angepasst an neue DB.
*-------------------------------------------------------------------------------
*/
package Klassen;

/**
 * @author Andre
 */
public class Artikel {

    /**
     * Variablendefinition
    */
    
    /**
     * ArtikelID des Artikelobjektes.
    */
    private String artikelID;
    
    /**
     * Artikeltext des Artikelobjektes.
    */
    private String artikeltext;
    
    /**
     * Bestelltext des Artikelobjektes.
    */
    private String bestelltext;
    
    /**
     * Einzelwert des Artikelobjektes.
    */
    private String einzelwert;
    
    /**
     * Bestellwert des Artikelobjektes.
    */
    private String bestellwert;
    
    /**
     * Steuersatz des Artikelobjektes.
    */
    private String steuer;
    
    /**
     * BestandsmengeFrei des Artikelobjektes.
    */
    private String bestandsmengeFrei;
    
    /**
     * BestandsmengeReserviert des Artikelobjektes.
    */
    private String bestandsmengeReserviert;
    
    /**
     * BestandsmengeZulauf des Artikelobjektes.
    */
    private String bestandsmengeZulauf;
    
    /**
     * BestandsmengeVerkauft des Artikelobjektes.
    */
    private String bestandsmengeVerkauft;
    
    /**
     * LKZ des Artikelobjektes.
    */
    private String lkz;   

    
    public Artikel() {
        
    }
    
    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * Konstruktor der Artikel Klasse.
     *
     * @param artikelID ArtikelID
     * @param artikeltext ArtikelText
     * @param bestelltext Bestelltext
     * @param einzelwert Einzelwert
     * @param bestellwert Bestelltwert
     * @param steuer MwSt.Satz
     * @param bestandsmengeFrei BestandsmengeFrei
     * @param bestandsmengeReserviert BestandsmengeReserviert
     * @param bestandsmengeZulauf BestandsmengeZulauf
     * @param bestandsmengeVerkauft BestandsmengeVerkauft
     * @param lkz Löschkennzeichen
     */
    public Artikel(String artikelID, String artikeltext, String bestelltext,
            String einzelwert, String bestellwert, String steuer, 
            String bestandsmengeFrei, String bestandsmengeReserviert,
            String bestandsmengeZulauf, String bestandsmengeVerkauft,
            String lkz) {
        
        this.artikelID = artikelID;
        this.artikeltext = artikeltext;
        this.bestelltext = bestelltext;
        this.einzelwert = einzelwert;
        this.bestellwert = bestellwert;
        this.steuer = steuer;
        this.bestandsmengeFrei = bestandsmengeFrei;
        this.bestandsmengeReserviert = bestandsmengeReserviert;
        this.bestandsmengeZulauf = bestandsmengeZulauf;
        this.bestandsmengeVerkauft = bestandsmengeVerkauft;
        this.lkz = lkz;
        
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * HashCode Methode für Artikel Objekte.
     * @return int Werte mit HashCode des Objekts.
     */
    @Override
    public int hashCode() {
        return artikelID.hashCode();
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * equals Methode für Artikel Objekte.
     * @param obj Zu prüfendes Objekt
     * @return Gleichheit von Artikeln.
     */
    @Override
    public boolean equals(Object obj) {
        Artikel a = (Artikel) obj;
        return (obj instanceof Artikel)
                && this.artikelID.equals(a.artikelID);
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * toString Methode für Artikel Objekte.
     * @return String Wert mit ArtikelID.
     */
    @Override
    public String toString() {
        return "ID: " + artikelID;
    }

    /*--------------------------------------------------------------------------
     *                       Generierter Code Anfang
     *------------------------------------------------------------------------*/
    
     /**
     * @return the ArtikelID
     */
    public String getArtikelID() {
        return artikelID;
    }

    /**
     * @param artikelID the ArtikelID to set
     */
    public void setArtikelID(String artikelID) {
        this.artikelID = artikelID;
    }

    /**
     * @return the Artikeltext
     */
    public String getArtikeltext() {
        return artikeltext;
    }

    /**
     * @param artikeltext the Artikeltext to set
     */
    public void setArtikeltext(String artikeltext) {
        this.artikeltext = artikeltext;
    }

    /**
     * @return the Bestelltext
     */
    public String getBestelltext() {
        return bestelltext;
    }

    /**
     * @param bestelltext the Bestelltext to set
     */
    public void setBestelltext(String bestelltext) {
        this.bestelltext = bestelltext;
    }

    /**
     * @return the Einzelwert
     */
    public String getEinzelwert() {
        return einzelwert;
    }

    /**
     * @param einzelwert the Einzelwert to set
     */
    public void setEinzelwert(String einzelwert) {
        this.einzelwert = einzelwert;
    }

    /**
     * @return the Bestellwert
     */
    public String getBestellwert() {
        return bestellwert;
    }

    /**
     * @param bestellwert the bestellwert to set
     */
    public void setBestellwert(String bestellwert) {
        this.bestellwert = bestellwert;
    }

    /**
     * @return the Steuer
     */
    public String getSteuer() {
        return steuer;
    }

    /**
     * @param steuer the Steuer to set
     */
    public void setSteuer(String steuer) {
        this.steuer = steuer;
    }    
    
    /**
     * @return the bestandsmengeFrei
     */
    public String getBestandsmengeFrei() {
        return bestandsmengeFrei;
    }

    /**
     * @param bestandsmengeFrei the bestandsmengeFrei to set
     */
    public void setBestandsmengeFrei(String bestandsmengeFrei) {
        this.bestandsmengeFrei = bestandsmengeFrei;
    }

    /**
     * @return the BestandsmengeReserviert
     */
    public String getBestandsmengeReserviert() {
        return bestandsmengeReserviert;
    }

    /**
     * @param bestandsmengeReserviert the BestandsmengeReserviert to set
     */
    public void setBestandsmengeReserviert(String bestandsmengeReserviert) {
        this.bestandsmengeReserviert = bestandsmengeReserviert;
    }

    /**
     * @return the BestandsmengeZulauf
     */
    public String getBestandsmengeZulauf() {
        return bestandsmengeZulauf;
    }

    /**
     * @param bestandsmengeZulauf the BestandsmengeZulauf to set
     */
    public void setBestandsmengeZulauf(String bestandsmengeZulauf) {
        this.bestandsmengeZulauf = bestandsmengeZulauf;
    }

    /**
     * @return the BestandsmengeVerkauft
     */
    public String getBestandsmengeVerkauft() {
        return bestandsmengeVerkauft;
    }

    /**
     * @param bestandsmengeVerkauft the BestandsmengeVerkauft to set
     */
    public void setBestandsmengeVerkauft(String bestandsmengeVerkauft) {
        this.bestandsmengeVerkauft = bestandsmengeVerkauft;
    }

    /**
     * @return the LKZ
     */
    public String getLKZ() {
        return lkz;
    }

    /**
     * @param lkz the LKZ to set
     */
    public void setLKZ(String lkz) {
        this.lkz = lkz;
    }

    /*--------------------------------------------------------------------------
     *                       Generierter Code Ende
     *------------------------------------------------------------------------*/
}
