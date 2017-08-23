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
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Alert;


/**
 * 
 * @author Andre
 */
public class DataDictionaryDAO extends DataAccess {

    /**
     * Konstruktor.
     * @throws SQLException SQLException
     */
    public DataDictionaryDAO() throws SQLException {
        
    }
    
    /**
     * 
     */
    public HashMap<String,ArrayList> tabellen = new HashMap<>();
    
    String tabAdresse = "ADRESSE";
    
    
    /**
     * Speicher alle Adress Attribute in einer HashMap.
     * @return HaspMap mit Tabellen und deren Attributen.
     * @throws java.sql.SQLException SQLException.
     */
    public HashMap holeAdressAttribute() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String attributID = "ATTRIBUT_ID";
        ArrayList<String> attribute = new ArrayList<>();
        String query = "SELECT ATTRIBUT_ID FROM ROOT.DATADICTIONARY "
                + "WHERE TABELLEN_ID = ? ORDER BY CAST(POSITION as integer)";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, tabAdresse);
            rs = stmt.executeQuery();
            int counter = 1;
            
            while (rs.next()) {
                if (tabellen.containsKey(tabAdresse)) {
                    tabellen.get(tabAdresse).add(rs.getString(attributID));
                
                } else {
                    attribute.add(rs.getString(attributID));
                    tabellen.put(tabAdresse, attribute);
                }
                counter++;
            }     
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            con.rollback();
        }
        return tabellen;
    }
    
    
    
    /**
     * Speicher alle Adress Attribute in einer HashMap.
     * @return HaspMap mit Tabellen und deren Attributen.
     */    
    public ArrayList gibAdressAttribute() {
        ArrayList<String> attribute = new ArrayList<>();
        attribute = tabellen.get(tabAdresse); 
        return attribute;
    }
    

    
    /**
     * Speicher alle Adress Attribute in einer HashMap.
     * @param tabName
     * @return HaspMap mit Tabellen und deren Attributen.
     */    
    public String gibTabellenNamen(String tabName) {
        
        for(String key : tabellen.keySet()) {
            System.out.print("Key: " + key + " - ");
        }
  
        return tabAdresse;
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) throws SQLException {
        DataDictionaryDAO d = new DataDictionaryDAO();
        d.holeAdressAttribute();
        d.gibTabellenNamen("HALLO");
    }
    
    
    
    
    
    
    
}