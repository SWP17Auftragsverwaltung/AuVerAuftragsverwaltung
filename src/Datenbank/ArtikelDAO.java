/*------------------------------------------------------------------------------
* Klasse: ArtikelDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse stellt eine Verbindung zur Datenbank her und bearbeitet alles
*   bezüglich Adressen.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 14.08.2017    BER     Erstellt.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Artikel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Tobi
 */
public class ArtikelDAO {
        
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
    /* 14.08.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
  
    /**
     * Gibt alle Adressen wieder die sich in der Datenbank befinden.
     *
     * @return Gibt Arraylist aller Adressen wieder
    */
    public ArrayList<Artikel> gibAlleArtikel() {
        
        //Variablendeklaration
        ArrayList<Artikel> artikelListe = new ArrayList<>();
        Artikel artikel = null;
        String query = "SELECT * FROM ROOT.Adresse";    
        
        try {
            con = DriverManager.getConnection(datenbankPfad, 
                    benutzername, passwort);
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
                 
        } catch (SQLException except) {
            System.out.println(except);
        }
        return artikelListe;
    }  
    
}
