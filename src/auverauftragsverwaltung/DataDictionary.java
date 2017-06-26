/*------------------------------------------------------------------------------
 * Datum     Name    Was
 * 26.06.17  Hen     DataDictionary Klasse angelegt.
 *--------------------------------------------------------------------------- */
package auverauftragsverwaltung;

/**
 *
 * @author Andre
 */
public class DataDictionary {

    /**
     * Name der Tabelle.
     */
    private String tabellenID;
    /**
     * ID des Attributs in der Tabelle.
     */
    private String attributID;
    /**
     * Name des Attributs in der Tabelle.
     */
    private String attributName;
    /**
     * Datentyp des Attributs.
     */
    private String datentyp;
    /**
     * Feldlaenge des Attributs.
     */
    private int feldlaenge;
    /**
     * Positionsnummer des Attributs. Gibt an, an welcher Stelle das Attribut
     * in der Tabelle steht.
     */
    private int position;

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 23.06.17    Hen     DataDictionary Konstruktor erstellt
    /*------------------------------------------------------------------------*/
    
    /**
     * Konstruktor des DataDictionary.
     *
     * @param tabellenID Gibt den Tabellennamen an.
     * @param attributID Gibt die ID des Attributs an.
     * @param attributName Gibt den Attributnamen an.
     * @param datentyp Gibt den Dateityp an.
     * @param feldlaenge Gibt die Länge des Attributs an.
     * @param position Gibt die Position des Attributs.
     */
    public DataDictionary(String tabellenID,
            String attributID, String attributName,
            String datentyp, int feldlaenge, int position) {
        this.tabellenID     = tabellenID;
        this.attributID     = attributID;
        this.attributName   = attributName;
        this.datentyp       = datentyp;
        this.feldlaenge     = feldlaenge;
        this.position       = position;
    }

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 23.06.17    Hen     toString Methode erstellt, gen. Code
    /*------------------------------------------------------------------------*/
    
    /**
     * toString Methode für DataDictonary Objekte.
     * @return String Wert mit der TabellenID.
     */
    @Override
    public String toString() {
        return "ID: " + tabellenID;
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
     * @param tabellenID the tabellenID to set
     */
    public void setTabellenID(String tabellenID) {
        this.tabellenID = tabellenID;
    }

    /**
     * @return the attributeID
     */
    public String getAttributID() {
        return attributID;
    }

    /**
     * @param attributeID the attributeID to set
     */
    public void setAttributID(String attributeID) {
        this.attributID = attributeID;
    }

    /**
     * @return the attributName
     */
    public String getAttributName() {
        return attributName;
    }

    /**
     * @param attributName the attributName to set
     */
    public void setAttributname(String attributName) {
        this.attributName = attributName;
    }

    /**
     * @return the datentyp
     */
    public String getDatentyp() {
        return datentyp;
    }

    /**
     * @param datentyp the datentyp to set
     */
    public void setDatentyp(String datentyp) {
        this.datentyp = datentyp;
    }

    /**
     * @return the feldlaenge
     */
    public int getFeldlaenge() {
        return feldlaenge;
    }

    /**
     * @param feldlaenge the feldlaenge to set
     */
    public void setFeldlaenge(int feldlaenge) {
        this.feldlaenge = feldlaenge;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /*------------------------------------------------
     *            Generierter Code Ende
     *------------------------------------------------
     */
}
