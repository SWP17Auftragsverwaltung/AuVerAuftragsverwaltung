/*------------------------------------------------------------------------------
* Klasse: AuftragspositionDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse bearbeitet Queries Auftragspositionen.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 27.08.2017    HEN     Erstellt.
*-------------------------------------------------------------------------------
*/

package Datenbank;

import Klassen.Auftragskopf;
import Klassen.Auftragsposition;
import java.sql.PreparedStatement;
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
public class AuftragspositionDAO extends DataAccess {

    /**
     * Erzeugt ein neues DataDictionaryDAO Objekt.
     */
    private DataDictionaryDAO ddd = new DataDictionaryDAO();
    
    /**
     * 
     */
    private String TAB_AUFTRAGSPOSITION = ddd.getTAB_AUFTRAGSPOSITION();
    
    /**
     * 
     */
    private HashMap<String, ArrayList> attribute;     
        /**
     * Konstruktor.
     *
     * @throws SQLException SQLException
     */
    public AuftragspositionDAO() throws SQLException {
        attribute = ddd.getTabellenAttribute();
        ddd.holeAlleAttribute(TAB_AUFTRAGSPOSITION);
    }


    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Auftragspositionen ohne Löschkennzeichen wieder.
     * @return Gibt ArrayList aller Auftragspositionen ohne LKZ wieder.
     * @throws java.sql.SQLException SQLException
     */
    public ArrayList<Auftragsposition> 
        gibAlleAuftragspositionenOhneLKZ() throws SQLException {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Auftragsposition auftragsposition = null;
        ArrayList<Auftragsposition> auftragspositionListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT." + ddd.getTabAuftragsposition() 
            + " WHERE " + attribute.get(TAB_AUFTRAGSPOSITION).get(5) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            rs = stmt.executeQuery();

            con.commit();
            while (rs.next()) {
                auftragsposition = new Auftragsposition(rs.getString(1), 
                        rs.getString(2), rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6));

                auftragspositionListe.add(auftragsposition);
            }
            //Fehler werfen wenn Rückgabeobjekt leer ist
            if (auftragspositionListe.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText("Keine Auftragspositionen gefunden!");
                alert.showAndWait();
            }

            //Mögliche SQL fehler fangen
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            con.rollback();
        }
        return auftragspositionListe;
    }    
 
        
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 27.08.17     HEN     Erstellt.    
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt die letzte ID einer ausgewählten Auftragsposition wieder und zählt
     * sie um 1 hoch.
     * @return neue ID aufgezählt.
     * @throws java.sql.SQLException SQLException
     */    
    public String gibLetztID() throws SQLException {
        Statement stmt = null;
        String value = "";
        ResultSet rs = null;
        String query = "SELECT MAX(" 
            + attribute.get(TAB_AUFTRAGSPOSITION).get(0) + ") FROM ROOT." 
            + ddd.getTabAuftragsposition();
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                value = rs.getString(1);
            }
            con.commit();
        
            
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            con.rollback();
        }
        return value;
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 27.08.17     HEN     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die letzte ID aus, erhöht sie um 1 und gibt sie wieder.
     * @return neue ID aufgezählt.
     * @throws java.sql.SQLException SQLException.
     */    
    public String generiereID() throws SQLException {
        //Holt sich die aktuell maximale ID.
        String alteIDString = gibLetztID();
        String neueID;

        if (alteIDString != null) {
            //Parsed die ID von String nach Int.
            int alteIDInt = Integer.parseInt(alteIDString);

            //Zählt die ID um 1 hoch.
            alteIDInt++;

            //Fügt die neue ID in den String und füllt vordere Zahlen mit 0 auf,
            //wenn neueID < 6 Zeichen.
            neueID = String.format("%03d", alteIDInt);
        
        } else {
            neueID = "001";
        }
        return neueID;
    }            
        

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Fügt eine Auftragsposition einem bestehenden Auftrag hinzu.
     * @param ap AuftragspositionObjekt
     * @throws java.sql.SQLException SQLException.
     */
    public void fuegeAuftragspositionHinzu(
            Auftragsposition ap) throws SQLException {
        
        PreparedStatement stmt = null;
        String auftragskopfID = ap.getAuftragskopfID();
        String positionsnummer = generiereID();
        String artikelID = ap.getArtikelID();
        String menge = ap.getMenge();
        String einzelwert = ap.getEinzelwert();
        String lkz = ap.getLkz();
               
        try {
            con.setAutoCommit(false);

            String query = "INSERT INTO ROOT." + ddd.getTabAuftragsposition()
                    + "(" + attribute.get(TAB_AUFTRAGSPOSITION).get(0) + ", " 
                    +  attribute.get(TAB_AUFTRAGSPOSITION).get(1) + ", " 
                    +  attribute.get(TAB_AUFTRAGSPOSITION).get(2) + ", " 
                    +  attribute.get(TAB_AUFTRAGSPOSITION).get(3) + ", " 
                    +  attribute.get(TAB_AUFTRAGSPOSITION).get(4) + ", "
                    +  attribute.get(TAB_AUFTRAGSPOSITION).get(5) + ") "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)";

            stmt = con.prepareStatement(query);
            stmt.setString(1, auftragskopfID);
            stmt.setString(2, positionsnummer);
            stmt.setString(3, artikelID);
            stmt.setString(4, menge);
            stmt.setString(5, einzelwert);
            stmt.setString(6, lkz);

            stmt.executeUpdate();
            con.commit();
            con.close();
            
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            con.rollback();
        }
    }        
    
    
}
