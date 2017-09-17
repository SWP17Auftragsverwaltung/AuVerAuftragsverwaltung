/*------------------------------------------------------------------------------
* Klasse: ZahlungskonditionenDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse stellt eine Verbindung zur Datenbank her und bearbeitet alles
*   bezüglich Zahlungskonditionen.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 14.08.2017    CEL     Erstellt.
* 15.08.2017    GET     gibAlleZahlungskonditionen() fertiggestellt.
* 19.08.2017    SEZ     gibLetztID() erstellt.
* 19.08.2017    HEN     generiereID() erstellt.
* 20.08.2017    CEL     gibAlleZahlungskonditionenOhneLKZ() erstellt.
* 20.08.2017    CEL     gibAlleZahlungskonditionenMitLKZ() erstellt.
* 20.08.2017    CEL     fuegeZahlungskonditionenHinzu() erstellt.
* 21.08.2017    BER     setzeLKZ() erweitert.
* 21.08.2017    GET     aendereZahlungskonditionen() erweitert.
*
*-------------------------------------------------------------------------------
 */
package Datenbank;

import Klassen.Zahlungskonditionen;
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
 * @author Chakir, Jakob
 */
public class ZahlungskonditionenDAO extends DataAccess {
    
    /**
     * Erzeugt ein neues DataDictionaryDAO Objekt.
     */
    private DataDictionaryDAO ddd = new DataDictionaryDAO();
    
    /**
     * 
     */
    private String TAB_ZAHLUNGSKONDITIONEN = ddd.getTAB_ZAHLUNGSKONDITIONEN();
    
    /**
     * 
     */
    private HashMap<String, ArrayList> attribute; 
        
    /**
     * Konstruktor.
     *
     * @throws SQLException SQLException
     */
    public ZahlungskonditionenDAO() throws SQLException {
        attribute = ddd.getTabellenAttribute();
        ddd.holeAlleAttribute(TAB_ZAHLUNGSKONDITIONEN);
    }

    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 14.08.2017    CEL     Erstellt.
    * 15.08.2017    GET     Querys erstellt und Methode fertiggestellt.
    /*------------------------------------------------------------------------*/
    /**
     * Gibt alle Zahlungskonditionen wieder, die sich in der Datenbank befinden.
     *
     * @return Gibt Arraylist aller Zahlungskonditionen wieder
     * @throws java.sql.SQLException SQLFehler
     */
    public ArrayList<Zahlungskonditionen> gibAlleZahlungskonditionen() 
        throws SQLException {

        //Variablendeklaration
        Statement stmt = null;
        ResultSet rs = null;
        Zahlungskonditionen zahlungskonditionen = null;
        ArrayList<Zahlungskonditionen> zahlungskonditionenListe
                = new ArrayList<>();

        String query = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen();

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
            con.commit();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            con.rollback();
        }
        return zahlungskonditionenListe;
    }
    
    
    
    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 20.08.2017    CEL     Querys erstellt und Methode fertiggestellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Zahlungskonditionen ohne Löschkennzeichen wieder.
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     * @throws java.sql.SQLException SQLFehler
     */
    public ArrayList<Zahlungskonditionen> gibAlleZahlungskonditionenOhneLKZ() 
        throws SQLException {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Zahlungskonditionen zahlungskonditionen = null;
        ArrayList<Zahlungskonditionen> 
                zahlungskonditionenListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen() 
            + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
            + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            rs = stmt.executeQuery();
            con.commit();
            
            while (rs.next()) {
                zahlungskonditionen = new Zahlungskonditionen(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), 
                        rs.getString(8), rs.getString(9), rs.getString(10), 
                        rs.getString(11), rs.getString(12));

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
            con.rollback();
        }
        return zahlungskonditionenListe;
    }
    
    
    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 20.08.2017    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Zahlungskonditionen zu einer Auftragsart wieder.
     * @param auftragsart Auftragsart
     * @return Gibt ArrayList aller zu einer Auftragsart wieder.
     * @throws java.sql.SQLException SQLFehler
     */
    public ArrayList<Zahlungskonditionen> 
        gibZahlungskonditionenZuArt(String auftragsart) throws SQLException {
        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Zahlungskonditionen zahlungskonditionen = null;
        ArrayList<Zahlungskonditionen> 
                zahlungskonditionenListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen() 
            + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
            + " = ?"
            + " AND " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(1) 
            + " = ?";
        
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            stmt.setString(2, auftragsart);
            rs = stmt.executeQuery();
            con.commit();
            
            while (rs.next()) {
                zahlungskonditionen = new Zahlungskonditionen(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), 
                        rs.getString(8), rs.getString(9), rs.getString(10), 
                        rs.getString(11), rs.getString(12));

                zahlungskonditionenListe.add(zahlungskonditionen);
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
        return zahlungskonditionenListe;
    }
        
   
    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 17.09.2017    BER     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     *Gibt die Lieferzeit SOFORT zu einer bestimmten  Zahlungskonditione wieder.
     * @param zkID ZahlungskonditionsID
     * @return Lieferzeit SOFORT zu zkID.
     * @throws java.sql.SQLException SQLFehler
     */
    public String gibLieferzeitSofort(String zkID) throws SQLException {
        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String lieferzeitSofort = "";

        String query = "SELECT " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(2)
            + " FROM ROOT." + ddd.getTabZahlungskonditionen() 
            + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
            + " = ?"
            + " AND " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
            + " = ?";
   
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, zkID);
            stmt.setString(2, "N");
            rs = stmt.executeQuery();
            con.commit();
            
            while (rs.next()) {
                lieferzeitSofort = rs.getString(1);
            }

            //Mögliche SQL fehler fangen
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Lieferzeit SOFORT konnte"
                + " nicht abgerufen werden.");
            alert.showAndWait();
            con.rollback();
        }
        return lieferzeitSofort;
    }
    
    
    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 17.09.2017    GET     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     *Gibt die Lieferzeit WUNSCH zu einer bestimmten  Zahlungskonditione wieder.
     * @param zkID ZahlungskonditionsID
     * @return Lieferzeit WUNSCH zu zkID.
     * @throws java.sql.SQLException SQLFehler
     */
    public String gibLieferzeitWunsch(String zkID) throws SQLException {
        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String lieferzeitWunsch = "";

        String query = "SELECT " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(3)
            + " FROM ROOT." + ddd.getTabZahlungskonditionen() 
            + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
            + " = ?"
            + " AND " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
            + " = ?";
        
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, zkID);
            stmt.setString(2, "N");
            rs = stmt.executeQuery();
            con.commit();
            
            while (rs.next()) {
                lieferzeitWunsch = rs.getString(1);
            }

            //Mögliche SQL fehler fangen
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Lieferzeit WUNSCH konnte"
                + " nicht abgerufen werden.");
            alert.showAndWait();
            con.rollback();
        }
        return lieferzeitWunsch;
    }     
    
          
    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 20.08.2017    CEL     Querys erstellt und Methode fertiggestellt.
    /*------------------------------------------------------------------------*/

    /**
     * Fügt Zahlungskonditionen der Datenbank hinzu.
     * @param z Zahlungskonditionsobjekt
     * @throws java.sql.SQLException SQLFehler
     */
    public void fuegeZahlungskonditionenHinzu(Zahlungskonditionen z) 
        throws SQLException {

        //Variablendeklaration
        PreparedStatement stmt = null;
        String zahlungskonditionsID = z.getZahlungskonditionenID();
        String auftragsart = z.getAuftragsart();
        String lieferzeit = z.getLieferzeitSOFORT();
        String sperrzeit = z.getSperrzeitWUNSCH();
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

            String query = "INSERT INTO ROOT." + ddd.getTabZahlungskonditionen()
                + " (" + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) + ", "
                + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(1) + ", "
                + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(2) + ", "
                + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(3) + ", "
                + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(4) + ", "
                + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(5) + ", "
                + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(6) + ", "
                + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(7) + ", "
                + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(8) + ", "
                + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(9) + ", "
                + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(10) + ", "
                + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) + ")"
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
            con.rollback();
        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 19.08.17     SEZ     Erstellt.    
    /*------------------------------------------------------------------------*/
    
    /**
     * Setzt Löschkennzeichen bei einer ausgewählten Zahlungskondition.
     * @return neue ID aufgezählt.
     * @throws java.sql.SQLException SQLFehler
     */ 
    public String gibLetztID() throws SQLException {

        Statement stmt = null;
        String value = "";
        ResultSet rs = null;
        String query 
            = "SELECT MAX(" + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
                + ") FROM ROOT." + ddd.getTabZahlungskonditionen();

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
    /* 19.08.17     HEN     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die letzte KonditionenID aus, erhöht sie um 1 und gibt sie wieder.
     * @return gibt neue KonditionenID aufgezählt.
     * @throws java.sql.SQLException SQLFehler
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
            neueID = String.format("%06d", alteIDInt);
        
        } else {
            neueID = "000001";
        }
        return neueID;
    }

    
    
   /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 14.08.2017    CEL     Erstellt.
    * 21.08.2017    GET     Querys angepasst und Methode fertiggestellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Änderd den Datensatz einer Zahlungskonditione in der Datenbank.
     * @param zk Zahlungskonditionen die geändert werden.
     * @throws java.sql.SQLException SQLFehler
     */
    public void aendereZahlungskonditionen(Zahlungskonditionen zk) 
        throws SQLException {
        PreparedStatement stmt = null;
        String query = "";

        try {
            con.setAutoCommit(false);

            query
                = "UPDATE ROOT." + ddd.getTabZahlungskonditionen() 
                + " SET " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(1) 
                + " = ? "
                + "WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getAuftragsart());
            stmt.setString(2, zk.getZahlungskonditionenID());
            stmt.executeUpdate();


            query
                = "UPDATE ROOT." + ddd.getTabZahlungskonditionen() 
                + " SET " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(2) 
                + " = ? "
                + "WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getLieferzeitSOFORT());
            stmt.setString(2, zk.getZahlungskonditionenID());
            stmt.executeUpdate();


            query
                = "UPDATE ROOT." + ddd.getTabZahlungskonditionen() 
                + " SET " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(3) 
                + " = ? "
                + "WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getSperrzeitWUNSCH());
            stmt.setString(2, zk.getZahlungskonditionenID());
            stmt.executeUpdate();


            query
                = "UPDATE ROOT." + ddd.getTabZahlungskonditionen() 
                + " SET " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(4) 
                + " = ? "
                + "WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getSkontozeit1());
            stmt.setString(2, zk.getZahlungskonditionenID());
            stmt.executeUpdate();


            query
                = "UPDATE ROOT." + ddd.getTabZahlungskonditionen() 
                + " SET " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(5) 
                + " = ? "
                + "WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getSkontozeit2());
            stmt.setString(2, zk.getZahlungskonditionenID());
            stmt.executeUpdate();
 

            query
                = "UPDATE ROOT." + ddd.getTabZahlungskonditionen() 
                + " SET " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(6) 
                + " = ? "
                + "WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getSkonto1());
            stmt.setString(2, zk.getZahlungskonditionenID());
            stmt.executeUpdate();
 

            query
                = "UPDATE ROOT." + ddd.getTabZahlungskonditionen() 
                + " SET " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(7) 
                + " = ? "
                + "WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getSkonto2());
            stmt.setString(2, zk.getZahlungskonditionenID());
            stmt.executeUpdate();
 

            query
                = "UPDATE ROOT." + ddd.getTabZahlungskonditionen() 
                + " SET " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(8) 
                + " = ? "
                + "WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getMahnzeit1());
            stmt.setString(2, zk.getZahlungskonditionenID());
            stmt.executeUpdate();
 

            query
                = "UPDATE ROOT." + ddd.getTabZahlungskonditionen() 
                + " SET " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(9) 
                + " = ? "
                + "WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getMahnzeit2());
            stmt.setString(2, zk.getZahlungskonditionenID());
            stmt.executeUpdate();


            query
                = "UPDATE ROOT." + ddd.getTabZahlungskonditionen() 
                + " SET " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(10) 
                + " = ? "
                + "WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getMahnzeit3());
            stmt.setString(2, zk.getZahlungskonditionenID());
            stmt.executeUpdate();
    
            con.commit();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Zahlungskondition konnte "
                + "nicht bearbeitet werden.");
            alert.showAndWait();
            con.rollback();
        }
    }
    
    
    
    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 14.08.2017    CEL     Erstellt.
    * 21.08.2017    BER     Querys angepasst und Methode fertiggestellt.
    /*------------------------------------------------------------------------*/

    /**
     * Setzt Löschkennzeichen bei einer ausgewählten Zahlungskonditionen.
     * @param zk Zahlungskonditionen
     * @throws java.sql.SQLException SQLFehler
     */
    public void setzeLKZ(Zahlungskonditionen zk) throws SQLException {

        PreparedStatement stmt = null;
        String zkID = zk.getZahlungskonditionenID();

        try {
            con.setAutoCommit(false);

            String query
                = "UPDATE ROOT." + ddd.getTabZahlungskonditionen() 
                + " SET " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
                + " = ? "
                + "WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
                + " = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, "J");
            stmt.setString(2, zkID);
            stmt.executeUpdate();
            con.commit();

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
