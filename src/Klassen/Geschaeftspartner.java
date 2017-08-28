/*------------------------------------------------------------------------------
* Klasse: Geschaeftspartner.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zur Verwaltung der Geschäftspartner.
*-------------------------------------------------------------------------------
* Historie:
* 26.07.2017    HEN     Erstellt.
* 01.08.2017    CEL     Erweiterung/Anpassung.
*-------------------------------------------------------------------------------
*/
package Klassen;

import java.util.ArrayList;

/**
 * @author Andre
*/
public class Geschaeftspartner {

    /**
     * Variablendefinition
    */
    
    /**
     * Primärschlüssel des Geschaeftspartners.
    */
    private String geschaeftspartnerID;

    /**
     * Fremdschlüssel Rechnungsadresse für Adressobjekt .
    */
    private String adresseID;

    /**
     * Lieferanten ID.
    */
    private String lieferID;
    
    /**
     * Typ des Geschaeftspartners.
    */
    private String typ;
    
    /**
     * Kreditlimit des Geschaeftspartners.
    */
    private String kreditlimit;   
   
    /**
     * Löschkennzeichen.
    */
    private String lkz;
    
    /**
     * Liste mit allen Aufträgen des Geschäftspartners.
    */
    private ArrayList<Auftragskopf> auftraege;
    

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     * 01.08.17  CEL     Anpassungen - neue DB-Struktur
     *--------------------------------------------------------------------------
    */
    
    /**
     * Konstruktor für Geschaeftspartner.
     *
     * @param geschaeftspartnerID GeschäftspartnerID
     * @param adresseID AdressID
     * @param lieferID Lieferanten ID
     * @param typ Typ
     * @param kreditlimit Kreditlimit
     * @param lkz Löschkennzeichen
    */
    public Geschaeftspartner(String geschaeftspartnerID,  String typ, 
            String adresseID, String lieferID, String kreditlimit, String lkz) {
        
        this.geschaeftspartnerID = geschaeftspartnerID;
        this.typ = typ;
        this.adresseID = adresseID;
        this.lieferID = lieferID;
        this.kreditlimit = kreditlimit;
        this.lkz = lkz;
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * hashCode Methode für Geschaeftspartnerobjekte.
     * @return hashCode von Geschaeftspartner Objekten
    */
    @Override
    public int hashCode() {
        return geschaeftspartnerID.hashCode();
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * equals Methode für Geschaeftspartnerobjekte.
     * @param obj Objekt
     * @return Gleichheit Geschaeftspartner Objekte
    */
    @Override
    public boolean equals(Object obj) {
        Geschaeftspartner gp = (Geschaeftspartner) obj;
        return (obj instanceof Geschaeftspartner)
                && this.geschaeftspartnerID.equals(gp.geschaeftspartnerID);
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * toString Methode für Geschaeftspartnerobjekte.
     * @return Id vom Geschaeftspartnerobjekt
    */
    @Override
    public String toString() {
        return "ID: " + geschaeftspartnerID;
    }

    /*--------------------------------------------------------------------------
     *                       Generierter Code Anfang
     *------------------------------------------------------------------------*/
    
    /**
     * @return the geschaeftspartnerID
    */
    public String getGeschaeftspartnerID() {
        return geschaeftspartnerID;
    }

    /**
     * @param geschaeftspartnerID the geschaeftspartnerID to set
    */
    public void setGeschaeftspartnerID(String geschaeftspartnerID) {
        this.geschaeftspartnerID = geschaeftspartnerID;
    }

    /**
     * @return the adresseID
    */
    public String getAdresseID() {
        return adresseID;
    }

    /**
     * @param adresseID the adresseID to set
    */
    public void setAdresseID(String adresseID) {
        this.adresseID = adresseID;
    }    

    /**
     * @return the lieferID
    */
    public String getLieferID() {
        return lieferID;
    }

    /**
     * @param lieferID the typ to set
    */
    public void setLieferID(String lieferID) {
        this.lieferID = lieferID;
    }
    
    /**
     * @return the typ
    */
    public String getTyp() {
        return typ;
    }

    /**
     * @param typ the typ to set
    */
    public void setTyp(String typ) {
        this.typ = typ;
    }

    /**
     * @return the kreditlimit
    */
    public String getKreditlimit() {
        return kreditlimit;
    }

    /**
     * @param kreditlimit the kreditlimit to set
    */
    public void setKreditlimit(String kreditlimit) {
        this.kreditlimit = kreditlimit;
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



    /**
     * @return the auftraege
    */
    public ArrayList<Auftragskopf> getAuftraege() {
        return auftraege;
    }

    /**
     * @param auftraege the auftraege to set
    */
    public void setAuftraege(ArrayList<Auftragskopf> auftraege) {
        this.auftraege = auftraege;
    }


    /*--------------------------------------------------------------------------
     *                       Generierter Code Ende
     *------------------------------------------------------------------------*/
}
