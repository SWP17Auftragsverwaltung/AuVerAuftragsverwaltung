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
import Klassen.Zahlungskonditionen;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Chakir
 */
public class GeschaeftspartnerDAO {
           /**
     * Variablendefinition
    */


    /**
     * Verbindungsobjekt auf null setzen.
     */  
    private Connection con = null;
    
    /**
     * Statementobjekt auf null setzen.
     */
    private Statement stmt = null;
    
    /**
     * ResultSet auf null setzen.
     */
    private ResultSet rs = null;

    
    private static String benutzername = "root";
    /**
     *Variable für das Benutzerpasswort.
     */
    private static String passwort = "KauVer";

    /**
     *Variable für den Datenbankpfad.
     */
    private static String datenbankPfad 
            = "jdbc:derby://localhost:1527/SWPWI2017";  
    
    
        /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.08.17    CEL     Erstellt.
    /*------------------------------------------------------------------------*/
  
    /**
     * Gibt alle Zahlungskonditionen wieder, die sich in der Datenbank befinden.
     *
     * @return Gibt Arraylist aller Zahlungskonditionen wieder
    */
    public ArrayList<Geschaeftspartner> gibAlleGeschaeftspartner() {
        
        //Variablendeklaration
        ArrayList<Geschaeftspartner> geschaeftspartnerListe = new ArrayList<>();
        Geschaeftspartner geschaeftspartner = null;
        String query = "SELECT * FROM ROOT.Geschaeftspartner";    
        //test
        try {
            con = DriverManager.getConnection(datenbankPfad, 
                    benutzername, passwort);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                geschaeftspartner = new Geschaeftspartner(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6));
            
                geschaeftspartnerListe.add(geschaeftspartner);
            }
            con.close();
                 
        } catch (SQLException except) {
            System.out.println(except);
        }
        return geschaeftspartnerListe;
    } 
    
}
