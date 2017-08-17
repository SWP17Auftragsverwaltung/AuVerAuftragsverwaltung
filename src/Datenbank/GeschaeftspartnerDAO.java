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
package Datenbank;

import Klassen.Geschaeftspartner;
import java.sql.PreparedStatement;
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
        
        //Variablendeklaration.
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
    
    public void fuegeGeschaeftspartnerHinzu(Geschaeftspartner gp) {
        PreparedStatement stmt = null;
        String geschaeftspartnerID = gp.getGeschaeftspartnerID();
        String typ = gp.getTyp();
        String adresseID = gp.getAdresseID();
        String lieferID = gp.getLieferID();
        String kreditlimit = gp.getKreditlimit();
        String lkz = gp.getLKZ();
        
        try {
            con.setAutoCommit(false);
            
            String query = "INSERT INTO ROOT.GESCHAEFTSPARTNER (Geschaeftspartner_ID, "
                    + "Typ, Anschrift_ID, Liefer_ID, Kreditlimit, LKZ)"
                    + "VALUES (?,?,?,?,?,?)";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, geschaeftspartnerID);
            stmt.setString(2, typ);
            stmt.setString(3, adresseID);
            stmt.setString(4, lieferID);
            stmt.setString(5, kreditlimit);
            stmt.setString(6, lkz);
            
            stmt.executeUpdate();
            con.commit();
            
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();        
        }
    }
}
