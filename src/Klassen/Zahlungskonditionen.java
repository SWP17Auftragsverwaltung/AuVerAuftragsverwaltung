/*------------------------------------------------------------------------------
* Klasse: Zahlungskonditionen.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zur Verwaltung der Zahlungskonditionen.
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


public class Zahlungskonditionen {

    /**
     * Variablendefinition
    */
    
    /**
     * Primärschlüssel der Zahlungskonditionen.
    */
    private String zahlungskonditionsID;
    
    /**
     * Auftragsart der Zahlungskonditionen.
    */
    private String auftragsart;
    
    /**
     * Lieferzeit der Zahlungskonditionen.
    */
    private String lieferzeit;
    
    /**
     * Sperrzeit der Zahlungskonditionen.
    */
    private String sperrzeit;
    
    /**
     * Skontofrist1 der Zahlungskonditionen.
     */
    private String skontozeit1;
    
    /**
     * Skontofrist2 der Zahlungskonditionen.
     */
    private String skontozeit2;
    
    /**
     * Skontosatz1 der Zahlungskonditionen.
     */
    private String skonto1;
    
    /**
     * Skontosatz2 der Zahlungskonditionen.
     */
    private String skonto2;
    
    /**
     * Mahnzeit1 der Zahlungskonditionen.
     */
    private String mahnzeit1;
    
    /**
     * Mahnzeit2 der Zahlungskonditionen.
     */
    private String mahnzeit2;
    
    /**
     * Mahnzeit3 der Zahlungskonditionen.
     */
    private String mahnzeit3;
    
    /**
     * Löschkennzeichen.
    */
    private String lkz;    
    

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     * 30.07.17  CEL     Erweitert und getestet.
     *--------------------------------------------------------------------------
    */
    
    /**
     * Konstruktor für Zahlungskonditionen.
     *
     * @param zahlungskonditionsID ZahlungskonditionsID
     * @param auftragsart Auftragsart
     * @param lieferzeit Lieferzeit
     * @param sperrzeit Sperrzeit
     * @param skontozeit1 Skontozeit1
     * @param skontozeit2 Skontozeit2
     * @param skonto1 Skonto1
     * @param skonto2 Skonto2
     * @param mahnzeit1 Mahnzeit1
     * @param mahnzeit2 Mahnzeit2
     * @param mahnzeit3 Mahnzeit3
     * @param lkz Löschkennzeichen
     */
    public Zahlungskonditionen(String zahlungskonditionsID, String auftragsart,
            String lieferzeit, String sperrzeit, String skontozeit1, String skontozeit2, 
            String skonto1, String skonto2, String mahnzeit1, String mahnzeit2, 
            String mahnzeit3, String lkz) {
        
        this.zahlungskonditionsID = zahlungskonditionsID;
        this.auftragsart = auftragsart;
        this.lieferzeit = lieferzeit;
        this.sperrzeit = sperrzeit;
        this.skontozeit1 = skontozeit1;
        this.skontozeit2 = skontozeit2;
        this.skonto1 = skonto1;
        this.skonto2 = skonto2;
        this.mahnzeit1 = mahnzeit1;
        this.mahnzeit2 = mahnzeit2;
        this.mahnzeit3 = mahnzeit3;
        this.lkz = lkz;
    }





    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * hashCode Methode für Zahlungskonditionenobjekte.
     * @return hashCode des Zahlungskonditionen Objektes
     */
    @Override
    public int hashCode() {
        return zahlungskonditionsID.hashCode();
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * equals Methode für Zahlungskonditionenobjekte.
     * @param obj Objekt
     * @return Gleichheit von Zahlungskonditionen Objekten
    */
    @Override
    public boolean equals(Object obj) {
        Zahlungskonditionen b = (Zahlungskonditionen) obj;
        return (obj instanceof Zahlungskonditionen)
                && this.zahlungskonditionsID.equals(b.zahlungskonditionsID);
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * toString Methode für Zahlungskonditionenobjekte.
     * @return Id vom Zahlungskonditionenobjekt
    */
    @Override
    public String toString() {
        return "ID: " + zahlungskonditionsID;
    }

    /*------------------------------------------------
     *            Generierter Code Anfang
     *------------------------------------------------
    */
    
    /**
     * @return the ZahlungskondiID
    */
    public String getZahlungskonditionenID() {
        return zahlungskonditionsID;
    }

    /**
     * @param zahlungskonditionsID the zahlungskonditionsID to set
    */
    public void setZahlungskonditionenID(String zahlungskonditionsID) {
        this.zahlungskonditionsID = zahlungskonditionsID;
    }

    /**
     * @return the auftragsart
    */
    public String getAuftragsart() {
        return auftragsart;
    }

    /**
     * @param auftragsart the auftragsart to set
    */
    public void setAuftragsart(String auftragsart) {
        this.auftragsart = auftragsart;
    }

    /**
     * @return the Lieferzeit
    */
    public String getLieferzeitSOFORT() {
        return lieferzeit;
    }

    /**
     * @param lieferzeit the Lieferzeit to set
    */
    public void setLieferzeitSOFORT(String lieferzeit) {
        this.lieferzeit = lieferzeit;
    }

    /**
     * @return the sperrzeit
    */
    public String getSperrzeitWUNSCH() {
        return sperrzeit;
    }

    /**
     * @param sperrzeit the sperrzeit to set
     */
    public void setSperrzeit(String sperrzeit) {
        this.sperrzeit = sperrzeit;
    }

    /**
     * @return the skontozeit1
     */
    public String getSkontozeit1() {
        return skontozeit1;
    }

    /**
     * @param skontozeit1 the skontozeit1 to set
     */
    public void setSkontozeit1(String skontozeit1) {
        this.skontozeit1 = skontozeit1;
    }

    /**
     * @return the skontozeit2
     */
    public String getSkontozeit2() {
        return skontozeit2;
    }

    /**
     * @param skontozeit2 the skontozeit2 to set
     */
    public void setSkontozeit2(String skontozeit2) {
        this.skontozeit2 = skontozeit2;
    }

    /**
     * @return the skonto1
     */
    public String getSkonto1() {
        return skonto1;
    }

    /**
     * @param skonto1 the skonto1 to set
     */
    public void setSkonto1(String skonto1) {
        this.skonto1 = skonto1;
    }

    /**
     * @return the skonto2
     */
    public String getSkonto2() {
        return skonto2;
    }

    /**
     * @param skonto2 the skonto2 to set
     */
    public void setSkonto2(String skonto2) {
        this.skonto2 = skonto2;
    }

    /**
     * @return the mahnzeit1
     */
    public String getMahnzeit1() {
        return mahnzeit1;
    }

    /**
     * @param mahnzeit1 the mahnzeit1 to set
     */
    public void setMahnzeit1(String mahnzeit1) {
        this.mahnzeit1 = mahnzeit1;
    }

    /**
     * @return the mahnzeit2
     */
    public String getMahnzeit2() {
        return mahnzeit2;
    }

    /**
     * @param mahnzeit2 the Mahnzeit2 to set
     */
    public void setMahnzeit2(String mahnzeit2) {
        this.mahnzeit2 = mahnzeit2;
    }

    /**
     * @return the mahnzeit3
     */
    public String getMahnzeit3() {
        return mahnzeit3;
    }

    /**
     * @param mahnzeit3 the mahnzeit3 to set
     */
    public void setMahnzeit3(String mahnzeit3) {
        this.mahnzeit3 = mahnzeit3;
    }

    /**
     * @return the lkz
     */
    public String getLKZ() {
        return lkz;
    }

    /**
     * @param lkz the lkz to set
     */
    public void setLKZ(String lkz) {
        this.lkz = lkz;
    }    
    /*------------------------------------------------
     *            Generierter Code Ende
     *------------------------------------------------
    */
}
