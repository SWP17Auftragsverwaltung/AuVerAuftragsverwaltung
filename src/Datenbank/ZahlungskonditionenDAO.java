/*------------------------------------------------------------------------------
* Klasse: ZahlungskonditionenDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse stellt eine Verbindung zur Datenbank her und bearbeitet alles
*   bezüglich Zahlungskonditionen.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 14.08.2017    CEL     Erstellt.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Zahlungskonditionen;
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
public class ZahlungskonditionenDAO extends DataAccess {

    /**
     * Konstruktor.
     * @throws SQLException SQLException 
     */
    public ZahlungskonditionenDAO() throws SQLException {
        
    }

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.08.17    CEL     Erstellt.
    /*------------------------------------------------------------------------*/
  
    /**
     * Gibt alle Zahlungskonditionen wieder, die sich in der Datenbank befinden.
     *
     * @return Gibt Arraylist aller Zahlungskonditionen wieder
    */
    public ArrayList<Zahlungskonditionen> gibAlleZahlungskonditionen() {
        
        //Variablendeklaration
        Statement stmt = null;
        ResultSet rs = null;
        Zahlungskonditionen zahlungskonditionen = null;
        ArrayList<Zahlungskonditionen> zahlungskonditionenListe 
                = new ArrayList<>();
        
        String query = "SELECT * FROM ROOT.Zahlungskonditionen";    
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                zahlungskonditionen = new Zahlungskonditionen(rs.getString(1), 
                        rs.getString(2), rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6), rs.getString(7), 
                        rs.getString(8), rs.getString(9), rs.getString(10), 
                        rs.getString(11), rs.getString(12));
            
                zahlungskonditionenListe.add(zahlungskonditionen);
            }
            con.close();
                 
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
        return zahlungskonditionenListe;
    } 
    
}
