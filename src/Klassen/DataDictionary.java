/*------------------------------------------------------------------------------
* Klasse: DataDictionary.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zur Verwaltung des DataDictionaries.
*-------------------------------------------------------------------------------
* Historie:
* 26.07.2017    HEN     Erstellt.
*-------------------------------------------------------------------------------
*/
package Klassen;

/**
 * @author Andre
 */

public class DataDictionary {

    /** 
     * Variablendeklaration
    */
    
    /**
     * Name der Tabelle.
    */
    private String tabellenID;
    
    /**
     * Nummer der des Attributes in der Tabelle.
     */
    private String attributeID;
    
    /**
     * Name des Attributes in der Tabelle.
     */
    private String attributname;
    
    /**
     * Datentyp des Attributes.
     */
    private String datentyp;
    
    /**
     * Feldlaenge des Attributes.
     */
    private String feldlaenge;
    
    /**
     * Positionsnummer des Atributes, dient der Ordnung.
     */
    private String position;

    
    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 26.07.17  Hen     Erstellt.
     *--------------------------------------------------------------------------
    */
    
    /**
     * Konstruktor des DataDictionary.
     *
     * @param tabellenID Gibt den Tabellennamen an.
     * @param attributeID Gibt den Attributnamen an.
     * @param attributname Gibt den Attributnamen an.
     * @param datentyp Dateityp.
     * @param feldlaenge Laenge des Attributes.
     * @param position Position des Attributes.
     **/
    public DataDictionary(String tabellenID, String attributeID, 
            String attributname, String datentyp, String feldlaenge, 
            String position) {
        
        this.tabellenID = tabellenID;
        this.attributeID = attributeID;
        this.attributname = attributname;
        this.datentyp = datentyp;
        this.feldlaenge = feldlaenge;
        this.position = position;
    }

    

    /*------------------------------------------------
     *            Generierter Code Anfang
     *------------------------------------------------
    */
    
    /**
     * @return the TabellenID
     */
    public String getTabellenID() {
        return tabellenID;
    }

    /**
     * @param tabellenID the TabellenID to set
     */
    public void setTabellenID(String tabellenID) {
        this.tabellenID = tabellenID;
    }

    /**
     * @return the AttributeID
     */
    public String getAttributeID() {
        return attributeID;
    }

    /**
     * @param attributeID the AttributeID to set
     */
    public void setAttributeID(String attributeID) {
        this.attributeID = attributeID;
    }

    /**
     * @return the Attributname
     */
    public String getAttributname() {
        return attributname;
    }

    /**
     * @param attributname the Attributname to set
     */
    public void setAttributname(String attributname) {
        this.attributname = attributname;
    }

    /**
     * @return the Datentyp
     */
    public String getDatentyp() {
        return datentyp;
    }

    /**
     * @param datentyp the Datentyp to set
     */
    public void setDatentyp(String datentyp) {
        this.datentyp = datentyp;
    }

    /**
     * @return the Feldlaenge
     */
    public String getFeldlaenge() {
        return feldlaenge;
    }

    /**
     * @param feldlaenge the Feldlaenge to set
     */
    public void setFeldlaenge(String feldlaenge) {
        this.feldlaenge = feldlaenge;
    }

    /**
     * @return the Position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the Position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /*------------------------------------------------
     *            Generierter Code Ende
     *------------------------------------------------
    */
}
