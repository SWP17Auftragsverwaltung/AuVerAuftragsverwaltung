/*------------------------------------------------------------------------------
* Klasse: Adresse.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zur Verwaltung der Adresse.
*-------------------------------------------------------------------------------
* Historie:
* 26.07.2017    HEN     Erstellt.
* 27.07.2017    BER     Erweitert.
* 27.07.2017    CEL     Erweitert.
*-------------------------------------------------------------------------------
*/
package Klassen;

/**
 * @author Andre
 */
public class Adresse {

    /**
     * Variablendefinition
    */
    
    /**
     * Primärschlüssel des Adressobjektes.
    */
    private String adresseID;
    
    /**
     * Vorname des Geschäftspartners.
     */
    private String name;
    
    /**
     * Nachname des Geschäftspartners.
    */
    private String vorname;
    
    /**
     * Anrede des Geschäftspartners.
    */
    private String anrede;
    
    /**
     * Straße des Geschäftspartners.
    */
    private String strasse;
    
    /**
     * Hausnummer des Geschäftspartners.
    */
    private String hausnummer;
    
    /**
     * PLZ des Geschäftspartners.
    */
    private String plz;
    
    /**
     * Ort des Geschäftspartners.
    */
    private String ort;
    
    /**
     * Staat des Geschäftspartners.
    */
    private String staat;
    
    /**
     * Telefonnummer des Geschäftspartners.
    */
    private String telefon;

    /**
     * eMail-Adresse des Geschäftspartners.
    */
    private String Email;
    
    /**
     * Erfassungsdatum des Objektes.
     */
    private String erfassungsdatum;
    
    /**
     * LKZ des Adressobjektes.
     */
    private String Lkz;
    
    
    public Adresse() {
        
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     * 27.07.17  CEL     Javadoc Kommentare hinzugefügt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * Konstruktor für Adresse Objekt.
     *
     * @param adresseID Adress ID
     * @param anrede Anrede
     * @param name Vorname
     * @param vorname Nachname
     * @param strasse Straße
     * @param hausnummer HausNr
     * @param plz PLZ
     * @param ort Ort
     * @param staat Staat
     * @param telefon Telefon
     * @param Email Email
     * @param erfassungsdatum Erfassungsdatum
     * @param Lkz Löschkennzeichen
     */
    public Adresse(String adresseID, String anrede, String name, 
            String vorname, String strasse, String hausnummer, String plz, 
            String ort, String staat, String telefon, String Email, 
            String erfassungsdatum, String Lkz) {

        this.adresseID = adresseID;
        this.name = name;
        this.vorname = vorname;
        this.anrede = anrede;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.staat = staat;
        this.telefon = telefon;
        this.Email = Email;
        this.erfassungsdatum = erfassungsdatum;
        this.Lkz = Lkz;
    }


    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * HashCode Methode für Adressen Objekt.
     * @return int HashCode von Adresse Objekt.
     */
    @Override
    public int hashCode() {
        return adresseID.hashCode();
    }
    
    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */

    /**
     * Equals Methode für Adressen Objekt.
     * @return Gleichheit von Adressenobjekten.
     * @param obj Objekt
     * 
     * Die equals-Methode dient dem Zweck,
     * Objekte auf Gleichheit zu prüfen.
     * 
    */
    @Override
    public boolean equals(Object obj) {
        Adresse a = (Adresse) obj;
        return (obj instanceof Adresse)
                && this.adresseID.equals(a.adresseID);
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * toString Methode für Adresse Objekte.
     * @return String Wert mit AdresseID.
     */
    @Override
    public String toString() {
        return "ID: " + adresseID;
    }

    /*--------------------------------------------------------------------------
     *                       Generierter Code Anfang
     *------------------------------------------------------------------------*/
    
    /**
     * @return the AdresseID
     */
    public String getAdresseID() {
        return adresseID;
    }

    /**
     * @param adresseID the AdresseID to set
     */
    public void setAdresseID(String adresseID) {
        this.adresseID = adresseID;
    }

    /**
     * @return the Vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * @param vorname the Vorname to set
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the Anrede
     */
    public String getAnrede() {
        return anrede;
    }

    /**
     * @param anrede the Anrede to set
     */
    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    /**
     * @return the Straße
     */
    public String getStrasse() {
        return strasse;
    }

    /**
     * @param strasse the Strasse to set
     */
    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    /**
     * @return the Hausnummer
     */
    public String getHausnummer() {
        return hausnummer;
    }

    /**
     * @param hausnaummer the Hausnummer to set
     */
    public void setHausnummer(String hausnaummer) {
        this.hausnummer = hausnaummer;
    }

    /**
     * @return the PLZ
     */
    public String getPlz() {
        return plz;
    }

    /**
     * @param plz the PLZ to set
     */
    public void setPLZ(String plz) {
        this.plz = plz;
    }

    /**
     * @return the Ort
     */
    public String getOrt() {
        return ort;
    }

    /**
     * @param ort the Ort to set
     */
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     * @return the Staat
     */
    public String getStaat() {
        return staat;
    }

    /**
     * @param staat the Staat to set
     */
    public void setStaat(String staat) {
        this.staat = staat;
    }

    /**
     * @return the Telefon
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * @param telefon the Telefon to set
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
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
     * @return the LKZ
     */
    public String getLkz() {
        return Lkz;
    }

    /**
     * @param Lkz the Lkz to set
     */
    public void setLkz(String Lkz) {
        this.Lkz = Lkz;
    }    

    /*--------------------------------------------------------------------------
     *                       Generierter Code Ende
     *------------------------------------------------------------------------*/
}
