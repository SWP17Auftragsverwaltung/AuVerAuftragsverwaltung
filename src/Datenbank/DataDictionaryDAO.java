/*------------------------------------------------------------------------------
* Klasse: AdresseDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - In dieser Klasse werden alle Tabellen der Datenbank zur Laufzeit abgerufen.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 23.08.2017    HEN     Erstellt.
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
     * Variable für die Tabelle Adresse.
     */
    private String tabArtikel;    
    
    /**
     * Variable für die Tabelle Adresse.
     */
    private String tabAuftragskonditionen;    
    
    /**
     * Variable für die Tabelle Adresse.
     */
    private String tabAuftragskopf;    

    /**
     * Variable für die Tabelle Adresse.
     */
    private String tabAuftragsposition;    
    
    /**
     * Variable für die Tabelle Adresse.
     */
    private String tabGeschaeftspartner;    
    
    /**
     * Variable für die Tabelle Adresse.
     */
    private String tabZahlungskonditionen;

    private final String TAB_ADRESSE = "TAB_ADRESSE";
    private final String TAB_ARTIKEL = "TAB_ARTIKEL";
    private final String TAB_AUFTRAGSKONDITIONEN = "TAB_AUFTRAGSKONDITIONEN";
    private final String TAB_AUFTRAGSKOPF = "TAB_AUFTRAGSKOPF";
    private final String TAB_AUFTRAGSPOSITION = "TAB_AUFTRAGSPOSITION";
    private final String TAB_GESCHAEFTSPARTNER = "TAB_GESCHAEFTSPARTNER";
    private final String TAB_ZAHLUNGSKONDITIONEN = "TAB_ZAHLUNGSKONDITIONEN";
        
    /**
     * Konstruktor.
     * @throws SQLException SQLException
     */
    public DataDictionaryDAO() throws SQLException {
        
    }
    
    
    
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
//            con.close();

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
    

    
    /**
     * 
     * @throws SQLException 
     */
    public void holeAlleAttribute(String tabelle) throws SQLException {
        String gesuchteTabelle = null;
        
        if(tabelle.equals(TAB_ADRESSE)) {
            gesuchteTabelle = TAB_ADRESSE;
        }
        else if(tabelle.equals(TAB_ARTIKEL)) {
            gesuchteTabelle = TAB_ARTIKEL;
        }
        
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String attributID = "ATTRIBUT_ID";
        ArrayList<String> attribute = new ArrayList<>();
        String query = "SELECT " + attributID + " FROM ROOT.DATADICTIONARY "
                + "WHERE TABELLEN_ID = ? ORDER BY CAST(POSITION as integer)";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, tabelle);
            rs = stmt.executeQuery();
            int a = 1;
            
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
//            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            con.rollback();
        }        
    }
    
    

    
    /*------------------------------------------------
     *            Generierter Code Anfang
     *------------------------------------------------
     */   
    
    /**
     * 
     * @return 
     */
    public String getTabAdresse() {
        return tabAdresse;
    }

    public void setTabAdresse(String tabAdresse) {
        this.tabAdresse = tabAdresse;
    }

    public String getTabArtikel() {
        return tabArtikel;
    }

    public void setTabArtikel(String tabArtikel) {
        this.tabArtikel = tabArtikel;
    }

    public String getTabAuftragskonditionen() {
        return tabAuftragskonditionen;
    }

    public void setTabAuftragskonditionen(String tabAuftragskonditionen) {
        this.tabAuftragskonditionen = tabAuftragskonditionen;
    }

    public String getTabAuftragskopf() {
        return tabAuftragskopf;
    }

    public void setTabAuftragskopf(String tabAuftragskopf) {
        this.tabAuftragskopf = tabAuftragskopf;
    }

    public String getTabAuftragsposition() {
        return tabAuftragsposition;
    }

    public void setTabAuftragsposition(String tabAuftragsposition) {
        this.tabAuftragsposition = tabAuftragsposition;
    }

    public String getTabGeschaeftspartner() {
        return tabGeschaeftspartner;
    }

    public void setTabGeschaeftspartner(String tabGeschaeftspartner) {
        this.tabGeschaeftspartner = tabGeschaeftspartner;
    }

    public String getTabZahlungskonditionen() {
        return tabZahlungskonditionen;
    }

    public void setTabZahlungskonditionen(String tabZahlungskonditionen) {    
        this.tabZahlungskonditionen = tabZahlungskonditionen;
    }
    /*------------------------------------------------
     *            Generierter Code Ende
     *------------------------------------------------
     */    
 
    
}