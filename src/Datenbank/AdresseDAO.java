/*------------------------------------------------------------------------------
* Klasse: AdresseDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse stellt eine Verbindung zur Datenbank her und bearbeitet alles
*   bezüglich Adressen.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 07.08.2017    HEN     Erstellt.
* 12.08.2017    HEN     DB Verbindung ausgelagert.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Adresse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * @author Andre
 */
public class AdresseDAO extends DataAccess {

    /**
     * Konstruktor.
     * @throws SQLException SQLException 
     */
    public AdresseDAO() throws SQLException {
        
    }
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 07.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
  
    /**
     * Gibt alle Adressen wieder die sich in der Datenbank befinden.
     *
     * @return Gibt Arraylist aller Adressen wieder
    */
    public ArrayList<Adresse> gibAlleAdressen() {
        
        //Variablendeklaration
        Statement stmt = null;
        ResultSet rs = null;
        Adresse adresse = null;  
        ArrayList<Adresse> adressListe = new ArrayList<>();
        
        String query = "SELECT * FROM ROOT.Adresse";    
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                adresse = new Adresse(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13));
            
                adressListe.add(adresse);
            }
            con.close();
                 
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
        return adressListe;
    }
    
    public static void main(String[] args) {
        
    }
    
}
