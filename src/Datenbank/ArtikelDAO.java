/*------------------------------------------------------------------------------
* Klasse: ArtikelDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse bearbeitet Queries bezüglich Artikel.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 14.08.2017    BER     Erstellt.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Artikel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
/**
 *
 * @author Tobi
 */
public class ArtikelDAO extends DataAccess {

    /**
     * Konstruktor.
     * @throws SQLException SQLException. 
     */
    public ArtikelDAO() throws SQLException {
        
    }
        
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.08.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
  
    /**
     * Gibt alle Adressen wieder die sich in der Datenbank befinden.
     * @return Gibt Arraylist aller Adressen wieder
    */
    public ArrayList<Artikel> gibAlleArtikel() {
        
        //Variablendeklaration
        Statement stmt = null;
        ResultSet rs = null;
        Artikel artikel = null;
        ArrayList<Artikel> artikelListe = new ArrayList<>();
        
        String query = "SELECT * FROM ROOT.Artikel";    
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                artikel = new Artikel(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11));
            
                artikelListe.add(artikel);
            }
            con.close();
                 
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
        return artikelListe;
    }  
    
}
