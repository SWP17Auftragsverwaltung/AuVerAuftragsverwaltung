/*------------------------------------------------------------------------------
* Klasse: Adresse.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zur Verwaltung der Adresse.
*-------------------------------------------------------------------------------
* Historie:
* 26.07.2017    HEN     Erstellt.
* 26.07.2017    BER     Erweitert.
*-------------------------------------------------------------------------------
*/
package Klassen;

import java.util.Date;

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
    private int adresseID;
    
    /**
     * Vorname des Geschäftspartners.
     */
    private String vorname;
    
    /**
     * Nachname des Geschäftspartners.
    */
    private String nachname;
    
    /**
     * Titel des Geschäftspartners.
    */
    private String titel;
    
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
    private int plz;
    
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
     * Faxnummer des Geschäftspartners.
    */
    private String fax;
    
    /**
     * eMail-Adresse des Geschäftspartners.
    */
    private String eMail;
    
    /**
     * Geburtsdatum des Geschäftspartners.
     */
    private Date geburtsdatum;
    
    /**
     * Erfassungsdatum des Objektes.
     */
    private Date erfassungsdatum;
    
    /**
     * Firmenname des Geschäftspartners.
     */
    private String firmenname;
    
    /**
     * LKZ des Adressobjektes.
     */
    private boolean lkz;

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     * 05.08.17  CEL     Anpassung - neue DB-Struktur und 
     *                   Javadoc Kommentare hinzugefügt
     *--------------------------------------------------------------------------
    */
    
    /**
     * Konstruktor für Adresse Objekt.
     *
     * @param adresseID Adress ID
     * @param titel Titel
     * @param vorname Vorname
     * @param nachname Nachname
     * @param strasse Straße
     * @param hausnummer HausNr
     * @param plz PLZ
     * @param ort Ort
     * @param staat Staat
     * @param telefon Telefon
     * @param fax FAX
     * @param eMail Email
     * @param geburtsdatum Geburtsdatum
     * @param erfassungsdatum Erfassungsdatum
     * @param lkz Löschkennzeichen
     * @param firmenname Firmenname
     */
    
    /*
    *  Initialisierung aller vorhandenen Variablen innerhalb der Adresse-Klasse.
    */
    
    public Adresse(int adresseID, String titel, String vorname, String nachname,
            String strasse, String hausnummer, int plz, String ort, 
            String staat, String telefon, String fax, String eMail, 
            Date geburtsdatum, Date erfassungsdatum, Boolean lkz, 
            String firmenname) {

        this.adresseID = adresseID;
        this.vorname = vorname;
        this.nachname = nachname;
        this.titel = titel;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.staat = staat;
        this.telefon = telefon;
        this.fax = fax;
        this.eMail = eMail;
        this.geburtsdatum = geburtsdatum;
        this.erfassungsdatum = erfassungsdatum;
        this.lkz = lkz;
        this.firmenname = firmenname;
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
        return adresseID;
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
                && this.adresseID == a.adresseID;
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

    /*------------------------------------------------
     *            Generierter Code Anfang
     *------------------------------------------------
     */
    
    /**
     * @return the AdresseID
     */
    public int getAdresseID() {
        return adresseID;
    }

    /**
     * @param adresseID the AdresseID to set
     */
    public void setAdresseID(int adresseID) {
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
     * @return the Nachname
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * @param nachname the Nachname to set
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * @return the Titel
     */
    public String getTitel() {
        return titel;
    }

    /**
     * @param titel the Titel to set
     */
    public void setTitel(String titel) {
        this.titel = titel;
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
    public int getPLZ() {
        return plz;
    }

    /**
     * @param plz the PLZ to set
     */
    public void setPLZ(int plz) {
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
     * @return the Fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the Fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the eMail
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * @param eMail the eMail to set
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * @return the Geburtsdatum
     */
    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     * @param geburtsdatum the Geburtsdatum to set
     */
    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
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
     * @return the Firmenname
     */
    public String getFirmenname() {
        return firmenname;
    }

    /**
     * @param firmenname the Firmenname to set
     */
    public void setFirmenname(String firmenname) {
        this.firmenname = firmenname;
    }

    /*------------------------------------------------
     *            Generierter Code Ende
     *------------------------------------------------
    */
}
