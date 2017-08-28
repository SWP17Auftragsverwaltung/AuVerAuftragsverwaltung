/*------------------------------------------------------------------------------
* Klasse: AdresseDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - In dieser Klasse werden alle Tabellen der Datenbank zur Laufzeit abgerufen.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 23.08.2017    HEN     Erstellt.
* 25.08.2017    BER     holeAlleAttribute() erweitert.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Alert;


/**
 * 
 * @author Andre
 */
public class DataDictionaryDAO extends DataAccess {

    /**
     * HashMap mit den einzelnen Tabellen und den dazugehörigen Attributen.
     */
    private HashMap<String, String> tabellenNamen = new HashMap<>();

    /**
     * HashMap mit den einzelnen Tabellen und den dazugehörigen Attributen.
     */
    private HashMap<String, ArrayList> tabellenAttribute = new HashMap<>();
    
    /**
     * Variable für die Tabelle Adresse.
     */
    private String tabAdresse;
    
    /**
     * Variable für die Tabelle Artikel.
     */
    private String tabArtikel;    
    
    /**
     * Variable für die Tabelle Auftragskonditionen.
     */
    private String tabAuftragskonditionen;    
    
    /**
     * Variable für die Tabelle Auftragskopf.
     */
    private String tabAuftragskopf;    

    /**
     * Variable für die Tabelle Auftragsposition.
     */
    private String tabAuftragsposition;    
    
    /**
     * Variable für die Tabelle Geschäftspartner.
     */
    private String tabGeschaeftspartner;    
    
    /**
     * Variable für die Tabelle Zahlungskonditionen.
     */
    private String tabZahlungskonditionen;

    /**
     * Zuordnung der Datenbanktabelle Adresse.
     */
    private final String TAB_ADRESSE = "TAB_ADRESSE";
    
    /**
     * Zuordnung der Datenbanktabelle Artikel.
     */
    private final String TAB_ARTIKEL = "TAB_ARTIKEL";
    
    /**
     * Zuordnung der Datenbanktabelle Auftragskonditionen.
     */
    private final String TAB_AUFTRAGSKONDITIONEN = "TAB_AUFTRAGSKONDITIONEN";
    
    /**
     * Zuordnung der Datenbanktabelle Auftragskopf.
     */
    private final String TAB_AUFTRAGSKOPF = "TAB_AUFTRAGSKOPF";
    
    /**
     * Zuordnung der Datenbanktabelle Auftragsposition.
     */
    private final String TAB_AUFTRAGSPOSITION = "TAB_AUFTRAGSPOSITION";
    
    /**
     * Zuordnung der Datenbanktabelle Geschäftspartner.
     */
    private final String TAB_GESCHAEFTSPARTNER = "TAB_GESCHAEFTSPARTNER";
    
    /**
     * Zuordnung der Datenbanktabelle Zahlungskonditionen.
     */
    private final String TAB_ZAHLUNGSKONDITIONEN = "TAB_ZAHLUNGSKONDITIONEN";
        
    /**
     * Konstruktor.
     * @throws SQLException SQLException
     */
    public DataDictionaryDAO() throws SQLException {
        gibTabellenNamen();
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 25.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Speichert anhand der TabellenID die Tabellen Namen in eine HashMap und
     * füllt die lokalen Variabeln mit den entsprechenden Tabellennamen.
     * @throws java.sql.SQLException SQLException
     */    
    public void gibTabellenNamen() throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT DISTINCT TABELLEN_ID, TABELLEN_NAME "
                + "FROM ROOT.DATADICTIONARY ";
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                tabellenNamen.put(rs.getString(1), rs.getString(2));
            }     
            con.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            con.rollback();
        }
        
        this.tabAdresse = tabellenNamen.get(TAB_ADRESSE);
        this.tabArtikel = tabellenNamen.get(TAB_ARTIKEL);
        this.tabAuftragskonditionen 
                = tabellenNamen.get(TAB_AUFTRAGSKONDITIONEN);
        this.tabAuftragskopf = tabellenNamen.get(TAB_AUFTRAGSKOPF);
        this.tabAuftragsposition = tabellenNamen.get(TAB_AUFTRAGSPOSITION);
        this.tabGeschaeftspartner = tabellenNamen.get(TAB_GESCHAEFTSPARTNER);
        this.tabZahlungskonditionen 
                = tabellenNamen.get(TAB_ZAHLUNGSKONDITIONEN);
    }     
    


    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 25.08.17    Hen     Erstellt.
    /* 25.08.17    Ber     while schleife erweitert.
    /*------------------------------------------------------------------------*/
    
    /**
     * Holt alle Attribute zu den augerufenen Tabelle.
     * @param tabelle Aufgerufene Tabelle.
     * @throws SQLException  SQLException
     */
    public void holeAlleAttribute(String tabelle) throws SQLException {
        String gesuchteTabelle = null;
        
        if (tabelle.equals(TAB_ADRESSE)) {
            gesuchteTabelle = TAB_ADRESSE;
        
        } else if (tabelle.equals(TAB_ARTIKEL)) {
            gesuchteTabelle = TAB_ARTIKEL;
        
        } else if (tabelle.equals(TAB_AUFTRAGSKONDITIONEN)) {
            gesuchteTabelle = TAB_AUFTRAGSKONDITIONEN;
        
        } else if (tabelle.equals(TAB_AUFTRAGSKOPF)) {
            gesuchteTabelle = TAB_AUFTRAGSKOPF;
        
        } else if (tabelle.equals(TAB_AUFTRAGSPOSITION)) {
            gesuchteTabelle = TAB_AUFTRAGSPOSITION;
        
        } else if (tabelle.equals(TAB_GESCHAEFTSPARTNER)) {
            gesuchteTabelle = TAB_GESCHAEFTSPARTNER;
        
        } else if (tabelle.equals(TAB_ZAHLUNGSKONDITIONEN)) {
            gesuchteTabelle = TAB_ZAHLUNGSKONDITIONEN;
        }
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String attributID = "ATTRIBUT_ID";
        ArrayList<String> attribute = new ArrayList<>();
        String query = "SELECT " + attributID + " FROM ROOT.DATADICTIONARY "
                + "WHERE TABELLEN_ID = ? ORDER BY CAST(POSITION as integer)";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, gesuchteTabelle);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                if (tabellenAttribute.containsKey(gesuchteTabelle)) {
                    tabellenAttribute.get(
                            gesuchteTabelle).add(rs.getString(attributID));
                
                } else {
                    attribute.add(rs.getString(attributID));
                    tabellenAttribute.put(gesuchteTabelle, attribute);
                }
            }     
            con.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            con.rollback();
        }        
    }
    
    

    
    /*--------------------------------------------------------------------------
     *                       Generierter Code Anfang
     *------------------------------------------------------------------------*/
    
    /**
    * @return the tabAdresse
    */
    public String getTabAdresse() {
        return tabAdresse;
    }

    /**
     * @param tabAdresse the tabAdresse to set
    */
    public void setTabAdresse(String tabAdresse) {
        this.tabAdresse = tabAdresse;
    }

    /**
     * @return the tabArtikel
    */
    public String getTabArtikel() {
        return tabArtikel;
    }

     /**
     * @param tabArtikel the tabArtikel to set
     */
    public void setTabArtikel(String tabArtikel) {
        this.tabArtikel = tabArtikel;
    }

    /**
     * @return the tabAuftragskonditionen
    */
    public String getTabAuftragskonditionen() {
        return tabAuftragskonditionen;
    }

    /**
     * @param tabAuftragskonditionen the tabAuftragskonditionen to set
     */
    public void setTabAuftragskonditionen(String tabAuftragskonditionen) {
        this.tabAuftragskonditionen = tabAuftragskonditionen;
    }

    /**
     * @return the tabAuftragskopf
    */
    public String getTabAuftragskopf() {
        return tabAuftragskopf;
    }

    /**
     * @param tabAuftragskopf the tabAuftragskopf to set
     */
    public void setTabAuftragskopf(String tabAuftragskopf) {
        this.tabAuftragskopf = tabAuftragskopf;
    }

    /**
    * @return the tabAuftragsposition
    */
    public String getTabAuftragsposition() {
        return tabAuftragsposition;
    }

    /**
     * @param tabAuftragsposition the tabAuftragsposition to set
     */
    public void setTabAuftragsposition(String tabAuftragsposition) {
        this.tabAuftragsposition = tabAuftragsposition;
    }

    /**
    * @return the tabGeschaeftspartner
    */
    public String getTabGeschaeftspartner() {
        return tabGeschaeftspartner;
    }

    /**
     * @param tabGeschaeftspartner the tabGeschaeftspartner to set
     */
    public void setTabGeschaeftspartner(String tabGeschaeftspartner) {
        this.tabGeschaeftspartner = tabGeschaeftspartner;
    }

    /**
    * @return the tabZahlungskonditionen
    */
    public String getTabZahlungskonditionen() {
        return tabZahlungskonditionen;
    }

    /**
     * @param tabZahlungskonditionen the tabZahlungskonditionen to set
     */
    public void setTabZahlungskonditionen(String tabZahlungskonditionen) {    
        this.tabZahlungskonditionen = tabZahlungskonditionen;
    }

    /**
    * @return the tabellenAttribute
    */
    public HashMap<String, ArrayList> getTabellenAttribute() {
        return tabellenAttribute;
    }

    /**
     * @param tabellenAttribute the tabellenAttribute to set
     */
    public void setTabellenAttribute(
            HashMap<String, ArrayList> tabellenAttribute) {
        this.tabellenAttribute = tabellenAttribute;
    }

    /**
    * @return the TAB_ADRESSE
    */
    public String getTAB_ADRESSE() {
        return TAB_ADRESSE;
    }

    /**
    * @return the TAB_ARTIKEL
    */
    public String getTAB_ARTIKEL() {
        return TAB_ARTIKEL;
    }

    /**
    * @return the TAB_AUFTRAGSKONDITIONEN
    */
    public String getTAB_AUFTRAGSKONDITIONEN() {
        return TAB_AUFTRAGSKONDITIONEN;
    }

    /**
    * @return the TAB_AUFTRAGSKOPF
    */
    public String getTAB_AUFTRAGSKOPF() {
        return TAB_AUFTRAGSKOPF;
    }

    /**
    * @return the TAB_AUFTRAGSPOSITION
    */
    public String getTAB_AUFTRAGSPOSITION() {
        return TAB_AUFTRAGSPOSITION;
    }

    /**
    * @return the TAB_GESCHAEFTSPARTNER
    */
    public String getTAB_GESCHAEFTSPARTNER() {
        return TAB_GESCHAEFTSPARTNER;
    }

    /**
    * @return the TAB_ZAHLUNGSKONDITIONEN
    */
    public String getTAB_ZAHLUNGSKONDITIONEN() {
        return TAB_ZAHLUNGSKONDITIONEN;
    }
    /*--------------------------------------------------------------------------
     *                       Generierter Code Ende
     *------------------------------------------------------------------------*/
 
    
}