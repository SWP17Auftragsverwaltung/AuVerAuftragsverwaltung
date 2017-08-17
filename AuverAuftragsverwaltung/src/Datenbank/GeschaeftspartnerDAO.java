/*------------------------------------------------------------------------------
* Klasse: GeschäftspartnerDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse stellt eine Verbindung zur Datenbank her und bearbeitet alles
*   bezüglich Geschäftspartner.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 14.08.2017    CEL     Erstellt.
*-------------------------------------------------------------------------------
*/
//test
package Datenbank;

import Klassen.Geschaeftspartner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Chakir
 */
public class GeschaeftspartnerDAO extends DataAccess {

    /**
     * Konstruktor.
     * @throws SQLException SQLException 
     */
    public GeschaeftspartnerDAO() throws SQLException {
        
    }

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.08.17    CEL     Erstellt.
    /*------------------------------------------------------------------------*/
  
    /**
     * Gibt alle Zahlungskonditionen wieder, die sich in der Datenbank befinden.
     * @return Gibt Arraylist aller Zahlungskonditionen wieder
    */
    public ArrayList<Geschaeftspartner> gibAlleGeschaeftspartner() {
        
        //Variablendeklaration
        Statement stmt = null;
        ResultSet rs = null;
        Geschaeftspartner geschaeftspartner = null;
        ArrayList<Geschaeftspartner> geschaeftspartnerListe = new ArrayList<>();
        
        String query = "SELECT * FROM ROOT.Geschaeftspartner";    

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                geschaeftspartner = new Geschaeftspartner(rs.getString(1), 
                        rs.getString(2), rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6));
            
                geschaeftspartnerListe.add(geschaeftspartner);
            }
            con.close();
                 
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
        return geschaeftspartnerListe;
    } 
    
}
