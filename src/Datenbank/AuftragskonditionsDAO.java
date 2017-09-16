/*------------------------------------------------------------------------------
* Klasse: AuftragskonditionsDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse bearbeitet Queries Auftragsköpfe.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 16.09.2017    BER     Erstellt.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Zahlungskonditionen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Andre
 */
public class AuftragskonditionsDAO extends DataAccess {


    /**
     * Erzeugt ein neues DataDictionaryDAO Objekt.
     */
    private DataDictionaryDAO ddd = new DataDictionaryDAO();
    
    /**
     * Holt den Tabellennamen für die Artikel zur Laufzeit.
     */
    private String TAB_AUFTRAGSKONDITIONEN = ddd.getTAB_AUFTRAGSKONDITIONEN();
    
    /**
     * HashMap für die Attribute der Tabelle Artikel.
     */
    private HashMap<String, ArrayList> attribute;    
    
    /**
     * Konstruktor.
     * @throws SQLException SQLException. 
     */
    public AuftragskonditionsDAO() throws SQLException {
        attribute = ddd.getTabellenAttribute();
        ddd.holeAlleAttribute(TAB_AUFTRAGSKONDITIONEN);
    }
        
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 16.09.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
  
    /**
     * Gibt alle Artikel wieder die sich in der Datenbank befinden.
     * @param auftragskopfID Auftragskopf für den die Zahlungskondition 
     *                       ausgegeben werden soll.
     * @return Gibt die passende Zahlungskondition wieder.
     * @throws java.sql.SQLException SQLFehler
    */
    public Zahlungskonditionen gibKonditionZuAuftrag(String auftragskopfID) 
        throws SQLException { 
        //Variablendeklaration
        Statement stmt = null;
        ResultSet rs = null;
        Zahlungskonditionen zahlungskondition = null;
        
        String query = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen();
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            con.commit();
            
            while (rs.next()) {
                zahlungskondition = new Zahlungskonditionen(rs.getString(1), 
                    rs.getString(2), rs.getString(3), rs.getString(4), 
                    rs.getString(5), rs.getString(6), rs.getString(7), 
                    rs.getString(8), rs.getString(9), rs.getString(10), 
                    rs.getString(11), rs.getString(12));
            }
                             
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Die Zahlungskondition "
                + "zum Auftrag " + auftragskopfID + "konnt nicht abgerufen "
                + "werden.");
            alert.showAndWait();
            con.rollback();
        }
        return zahlungskondition;
    }      
}