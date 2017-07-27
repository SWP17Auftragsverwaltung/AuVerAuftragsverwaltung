/*------------------------------------------------------------------------------
* Klasse: Adresse.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zur Verwaltung der Artikel.
*-------------------------------------------------------------------------------
* Historie:
* 26.07.2017    HEN     Erstellt.
* 26.07.2017    BER     Erweitert.
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
    private int artikelID;
    
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
    private double einzelwert;
    
    /**
     * Bestellwert des Artikelobjektes.
    */
    private double bestellwert;
    
    /**
     * Steuersatz des Artikelobjektes.
    */
    private int steuer;
    
    /**
     * BestandsmengeFrei des Artikelobjektes.
    */
    private int bestandsmengeFrei;
    
    /**
     * BestandsmengeReserviert des Artikelobjektes.
    */
    private int bestandsmengeReserviert;
    
    /**
     * BestandsmengeZulauf des Artikelobjektes.
    */
    private int bestandsmengeZulauf;
    
    /**
     * BestandsmengeVerkauft des Artikelobjektes.
    */
    private int bestandsmengeVerkauft;
    
    /**
     * LKZ des Artikelobjektes.
    */
    private boolean lkz;   

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
    public Artikel(int artikelID, String artikeltext, String bestelltext,
            double einzelwert, double bestellwert, int steuer, 
            int bestandsmengeFrei, int bestandsmengeReserviert,
            int bestandsmengeZulauf, int bestandsmengeVerkauft,
            boolean lkz) {
        
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
        return getArtikelID();
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
                && this.getArtikelID() == a.getArtikelID();
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

    /*------------------------------------------------
     *            Generierter Code Anfang
     *------------------------------------------------
     */
    
     /**
     * @return the ArtikelID
     */
    public int getArtikelID() {
        return artikelID;
    }

    /**
     * @param artikelID the ArtikelID to set
     */
    public void setArtikelID(int artikelID) {
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
    public double getEinzelwert() {
        return einzelwert;
    }

    /**
     * @param einzelwert the Einzelwert to set
     */
    public void setEinzelwert(double einzelwert) {
        this.einzelwert = einzelwert;
    }

    /**
     * @return the Bestellwert
     */
    public double getBestellwert() {
        return bestellwert;
    }

    /**
     * @param bestellwert the bestellwert to set
     */
    public void setBestellwert(double bestellwert) {
        this.bestellwert = bestellwert;
    }

    /**
     * @return the Steuer
     */
    public int getSteuer() {
        return steuer;
    }

    /**
     * @param steuer the Steuer to set
     */
    public void setSteuer(int steuer) {
        this.steuer = steuer;
    }    
    
    /**
     * @return the bestandsmengeFrei
     */
    public int getBestandsmengeFrei() {
        return bestandsmengeFrei;
    }

    /**
     * @param bestandsmengeFrei the bestandsmengeFrei to set
     */
    public void setBestandsmengeFrei(int bestandsmengeFrei) {
        this.bestandsmengeFrei = bestandsmengeFrei;
    }

    /**
     * @return the BestandsmengeReserviert
     */
    public int getBestandsmengeReserviert() {
        return bestandsmengeReserviert;
    }

    /**
     * @param bestandsmengeReserviert the BestandsmengeReserviert to set
     */
    public void setBestandsmengeReserviert(int bestandsmengeReserviert) {
        this.bestandsmengeReserviert = bestandsmengeReserviert;
    }

    /**
     * @return the BestandsmengeZulauf
     */
    public int getBestandsmengeZulauf() {
        return bestandsmengeZulauf;
    }

    /**
     * @param bestandsmengeZulauf the BestandsmengeZulauf to set
     */
    public void setBestandsmengeZulauf(int bestandsmengeZulauf) {
        this.bestandsmengeZulauf = bestandsmengeZulauf;
    }

    /**
     * @return the BestandsmengeVerkauft
     */
    public int getBestandsmengeVerkauft() {
        return bestandsmengeVerkauft;
    }

    /**
     * @param bestandsmengeVerkauft the BestandsmengeVerkauft to set
     */
    public void setBestandsmengeVerkauft(int bestandsmengeVerkauft) {
        this.bestandsmengeVerkauft = bestandsmengeVerkauft;
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

    /*------------------------------------------------
     *            Generierter Code Ende
     *------------------------------------------------
     */
}
