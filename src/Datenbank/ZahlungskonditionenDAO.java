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
    
    /**
     * Gibt alle Adressen ohne Löschkennzeichen wieder.
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     */
    public ArrayList<Zahlungskonditionen> gibAlleZahlungskonditionenOhneLKZ() {
        
        //Variablendeklaration
        PreparedStatement  stmt = null;
        ResultSet rs = null;
        Zahlungskonditionen zahlungskonditionen = null;  
        ArrayList<Zahlungskonditionen> zahlungskonditionenListe = new ArrayList<>();
        
        String query = "SELECT * FROM ROOT.Zahlungskonditionen WHERE LKZ = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            rs = stmt.executeQuery();

            con.commit();
            while (rs.next()) {
                zahlungskonditionen = new Zahlungskonditionen(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12));
                        
                
                zahlungskonditionenListe.add(zahlungskonditionen);
            }
            //Fehler werfen wenn Rückgabeobjekt leer ist
            if (zahlungskonditionenListe.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText("Keine Zahlungskonditionen gefunden!");
                alert.showAndWait();
            }
        
        //Mögliche SQL fehler fangen
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } 
        return zahlungskonditionenListe;
    }
    
    /**
     * Gibt alle Adressen mit Löschkennzeichen wieder.
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     */
    public ArrayList<Zahlungskonditionen> gibAlleZahlungskonditionenMitLKZ() {
        
        //Variablendeklaration
        PreparedStatement  stmt = null;
        ResultSet rs = null;
        Zahlungskonditionen zahlungskonditionen = null;  
        ArrayList<Zahlungskonditionen> zahlungskonditionenListe = 
                new ArrayList<>();
        
        String query = "SELECT * FROM ROOT.Zahlungskonditionen WHERE LKZ = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "J");
            rs = stmt.executeQuery();

            con.commit();
            while (rs.next()) {
                zahlungskonditionen = new Zahlungskonditionen(
                        rs.getString(1), 
                        rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11), 
                        rs.getString(12));
                
                zahlungskonditionenListe.add(zahlungskonditionen);
            }
            //Fehler werfen wenn Rückgabeobjekt leer ist
            if (zahlungskonditionenListe.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText("Keine Zahlungskonditionen gefunden!");
                alert.showAndWait();
            }
        
        //Mögliche SQL fehler fangen
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } 
        return zahlungskonditionenListe;
    }
    
        /**
     * Fügt Zahlungskonditionen der Datenbank hinzu.
     * @param z Zahlungskonditionsobjekt
    */
    public void fuegeZahlungskonditionenHinzu(Zahlungskonditionen z) {
        
        //Variablendeklaration
        PreparedStatement  stmt = null;
        String zahlungskonditionsID = z.getZahlungskondiID();
        String auftragsart = z.getAuftragsart();
        String lieferzeit = z.getLieferzeit();
        String sperrzeit = z.getSperrzeit();
        String skontozeit1 = z.getSkontozeit1();
        String skontozeit2 = z.getSkontozeit2();
        String skonto1 = z.getSkonto1();
        String skonto2 = z.getSkonto2();
        String mahnzeit1 = z.getMahnzeit1();
        String mahnzeit2 = z.getMahnzeit2();
        String mahnzeit3 = z.getMahnzeit3();
        String lkz = z.getLKZ();
        
        
        try {
            con.setAutoCommit(false);

            String query = "INSERT INTO ROOT.Zahlungskonditionen (Zahlungskonditions_ID, Auftragsart, "
                + "Lieferzeit, Sperrzeit, Skontozeit1, Skontozeit2, Skonto1, Skonto2, Mahnzeit1, "
                + "Mahnzeit2, Mahnzeit3, LKZ)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                
            stmt = con.prepareStatement(query);
            stmt.setString(1, zahlungskonditionsID);
            stmt.setString(2, auftragsart);
            stmt.setString(3, lieferzeit);
            stmt.setString(4, sperrzeit);
            stmt.setString(5, skontozeit1);
            stmt.setString(6, skontozeit2);
            stmt.setString(7, skonto1);
            stmt.setString(8, skonto2);
            stmt.setString(9, mahnzeit1);
            stmt.setString(10, mahnzeit2);
            stmt.setString(11, mahnzeit3);
            stmt.setString(12, lkz);
            
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
