/*------------------------------------------------------------------------------
* Klasse: ArtikelDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse bearbeitet Queries bezüglich Artikel.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 14.08.2017    BER     Erstellt.
* 14.08.2017    BER     gibAlleArtikel() erstellt.
* 15.08.2017    BER     gibAlleArtikelOhneLKZ(), gibAlleArtikelMitLKZ() erstellt
* 17.08.2017    BER     setzteLKZ() erstellt.
* 17.08.2017    BER     fuegeArtikelHinzu() erstellt.
* 18.08.2017    BER     aendereArtikel() erstellt.
* 19.08.2017    SEZ     generiereID(), 
*               BER     gibLetzteID() erstellt.
* 06.09.2017    HEN     setzeMengeFreiRes(),
*               BER     gibMengeFrei(),gibMengeRes(), gibMengeZulauf() erstellt.
* 07.09.2017    HEN     setzeMengeResVer(),
*               BER     gibMengeVerkauft() erstellt.
* 10.09.2017    HEN     gibArtikelSteuer() erstellt.
* 13.09.2017    HEN     setzeMengeZulauf(), setzeMengeZulaufFrei() erstellt.     
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Artikel;
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
 * @author Tobi
 */
public class ArtikelDAO extends DataAccess {


    /**
     * Erzeugt ein neues DataDictionaryDAO Objekt.
     */
    private DataDictionaryDAO ddd = new DataDictionaryDAO();
    
    /**
     * Holt den Tabellennamen für die Artikel zur Laufzeit.
     */
    private String TAB_ARTIKEL = ddd.getTAB_ARTIKEL();
    
    /**
     * HashMap für die Attribute der Tabelle Artikel.
     */
    private HashMap<String, ArrayList> attribute;    
    
    /**
     * Konstruktor.
     * @throws SQLException SQLException. 
     */
    public ArtikelDAO() throws SQLException {
        attribute = ddd.getTabellenAttribute();
        ddd.holeAlleAttribute(TAB_ARTIKEL);
    }
        
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.08.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
  
    /**
     * Gibt alle Artikel wieder die sich in der Datenbank befinden.
     * @return Gibt Arraylist aller Adressen wieder
    */
    public ArrayList<Artikel> gibAlleArtikel() {
        
        //Variablendeklaration
        Statement stmt = null;
        ResultSet rs = null;
        Artikel artikel = null;
        ArrayList<Artikel> artikelListe = new ArrayList<>();
        
        String query = "SELECT * FROM ROOT." + ddd.getTabArtikel();    
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            con.commit();
            
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
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
    
     /**
     * Gibt alle Artikel ohne Löschkennzeichen wieder.
     * @return Gibt ArrayList aller Artikel ohne LKZ wieder.
     */
    public ArrayList<Artikel> gibAlleArtikelOhneLKZ() {
        
        //Variablendeklaration
        PreparedStatement  stmt = null;
        ResultSet rs = null;
        Artikel artikel = null;  
        ArrayList<Artikel> artikelListe = new ArrayList<>();
        
        String query = "SELECT * FROM ROOT." + ddd.getTabArtikel() 
            + " WHERE " + attribute.get(TAB_ARTIKEL).get(10) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            rs = stmt.executeQuery();
            con.commit();
            
            while (rs.next()) {
                artikel = new Artikel(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11));
                
                artikelListe.add(artikel);
            }
            //Fehler werfen wenn Rückgabeobjekt leer ist
            if (artikelListe.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText("Keine Artikel gefunden!");
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
        return artikelListe;
    }


    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
     /**
     * Gibt alle Artikel ohne Löschkennzeichen wieder.
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     */
    public ArrayList<Artikel> gibAlleArtikelMitLKZ() {
        
        //Variablendeklaration
        PreparedStatement  stmt = null;
        ResultSet rs = null;
        Artikel artikel = null;  
        ArrayList<Artikel> artikelListe = new ArrayList<>();
        
        String query = "SELECT * FROM ROOT." + ddd.getTabArtikel() + " WHERE " 
            + attribute.get(TAB_ARTIKEL).get(12) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "J");
            rs = stmt.executeQuery();
            con.commit();
            
            while (rs.next()) {
                artikel = new Artikel(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11));
                
                artikelListe.add(artikel);
            }
            //Fehler werfen wenn Rückgabeobjekt leer ist
            if (artikelListe.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText("Keine Artikel gefunden!");
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
        return artikelListe;
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.08.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
    
     /**
     * Fügt Artikel der Datenbank hinzu.
     * @param a Artikelobjekt
    */
    
    public void fuegeArtikelHinzu(Artikel a) {
        
        //Variablendeklaration
        PreparedStatement stmt = null;
        String artikelID  = a.getArtikelID();
        String artikeltext = a.getArtikeltext();
        String bestelltext = a.getBestelltext();
        String einzelwert = a.getEinzelwert();
        String bestellwert = a.getBestellwert();
        String steuer = a.getSteuer();
        String bestandsmengeFrei = a.getBestandsmengeFrei();
        String bestandsmengeReserviert = a.getBestandsmengeReserviert();
        String bestandsmengeZulauf = a.getBestandsmengeZulauf();
        String bestandsmengeVerkauft = a.getBestandsmengeVerkauft();
        String lkz = a.getLKZ();
        
        try {
            con.setAutoCommit(false);
            
            String query = "INSERT INTO ROOT." + ddd.getTabArtikel() 
                    + "(" + attribute.get(TAB_ARTIKEL).get(0) + ", "
                    + attribute.get(TAB_ARTIKEL).get(1) + ", "
                    + attribute.get(TAB_ARTIKEL).get(2) + ", "
                    + attribute.get(TAB_ARTIKEL).get(3) + ", "
                    + attribute.get(TAB_ARTIKEL).get(4) + ", "
                    + attribute.get(TAB_ARTIKEL).get(5) + ", "
                    + attribute.get(TAB_ARTIKEL).get(6) + ", "
                    + attribute.get(TAB_ARTIKEL).get(7) + ", "
                    + attribute.get(TAB_ARTIKEL).get(8) + ", "
                    + attribute.get(TAB_ARTIKEL).get(9) + ", "
                    + attribute.get(TAB_ARTIKEL).get(10) + ") "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, artikelID);
            stmt.setString(2, artikeltext);
            stmt.setString(3, bestelltext);
            stmt.setString(4, einzelwert);
            stmt.setString(5, bestellwert);
            stmt.setString(6, steuer);
            stmt.setString(7, bestandsmengeFrei);
            stmt.setString(8, bestandsmengeReserviert);
            stmt.setString(9, bestandsmengeZulauf);
            stmt.setString(10, bestandsmengeVerkauft);
            stmt.setString(11, lkz);
            
            stmt.executeUpdate();
            con.commit();
            con.close();
            
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();        
        }
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 18.08.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Ändern der Artikel in der Datenbank.
     * @param a Artikelobjekt
    */
    public void aendereArtikel(Artikel a) {
        PreparedStatement stmt = null;
        String query = "";

        try {
            con.setAutoCommit(false);

            query = 
                "UPDATE ROOT." + ddd.getTabArtikel() + " SET " 
                + attribute.get(TAB_ARTIKEL).get(1) + " = ? WHERE " 
                + attribute.get(TAB_ARTIKEL).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getArtikeltext());
            stmt.setString(2, a.getArtikelID());
            stmt.executeUpdate();
            
            
            query = 
                "UPDATE ROOT." + ddd.getTabArtikel() + " SET " 
                + attribute.get(TAB_ARTIKEL).get(2) + " = ? WHERE " 
                + attribute.get(TAB_ARTIKEL).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getBestelltext());
            stmt.setString(2, a.getArtikelID());
            stmt.executeUpdate();

            
            query = 
                "UPDATE ROOT." + ddd.getTabArtikel() + " SET " 
                + attribute.get(TAB_ARTIKEL).get(3) + " = ? WHERE " 
                + attribute.get(TAB_ARTIKEL).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getEinzelwert());
            stmt.setString(2, a.getArtikelID());
            stmt.executeUpdate();

            
            query = 
                "UPDATE ROOT." + ddd.getTabArtikel() + " SET " 
                + attribute.get(TAB_ARTIKEL).get(4) + " = ? WHERE " 
                + attribute.get(TAB_ARTIKEL).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getBestellwert());
            stmt.setString(2, a.getArtikelID());
            stmt.executeUpdate();

            
            query = 
                "UPDATE ROOT." + ddd.getTabArtikel() + " SET " 
                + attribute.get(TAB_ARTIKEL).get(5) + " = ? WHERE " 
                + attribute.get(TAB_ARTIKEL).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getSteuer());
            stmt.setString(2, a.getArtikelID());
            stmt.executeUpdate();

            
            query = 
                "UPDATE ROOT." + ddd.getTabArtikel() + " SET " 
                + attribute.get(TAB_ARTIKEL).get(6) + " = ? WHERE " 
                + attribute.get(TAB_ARTIKEL).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getBestandsmengeFrei());
            stmt.setString(2, a.getArtikelID());
            stmt.executeUpdate();

            
            query = 
                "UPDATE ROOT." + ddd.getTabArtikel() + " SET " 
                + attribute.get(TAB_ARTIKEL).get(7) + " = ? WHERE " 
                + attribute.get(TAB_ARTIKEL).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getBestandsmengeReserviert());
            stmt.setString(2, a.getArtikelID());
            stmt.executeUpdate();

            
            query = 
                "UPDATE ROOT." + ddd.getTabArtikel() + " SET " 
                + attribute.get(TAB_ARTIKEL).get(8) + " = ? WHERE " 
                + attribute.get(TAB_ARTIKEL).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getBestandsmengeZulauf());
            stmt.setString(2, a.getArtikelID());
            stmt.executeUpdate();

            
            query = 
                "UPDATE ROOT." + ddd.getTabArtikel() + " SET " 
                + attribute.get(TAB_ARTIKEL).get(9) + " = ? WHERE " 
                + attribute.get(TAB_ARTIKEL).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getBestandsmengeVerkauft());
            stmt.setString(2, a.getArtikelID());
            stmt.executeUpdate();
            
            con.commit();
            con.close();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 19.08.17     BER     Erstellt.    
    /*------------------------------------------------------------------------*/
    
    /**
     * Setzt Löschkennzeichen bei einem ausgewählten Artikel.
     * @return neue ID aufgezählt.
     */    
    public String gibLetztID() {
        Statement stmt = null;
        String value = "";
        ResultSet rs = null;
        String query = "SELECT MAX(" + attribute.get(TAB_ARTIKEL).get(0) 
            + ") FROM ROOT." + ddd.getTabArtikel();
        
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
        }
        return value;
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 19.08.17     SEZ     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die letzte ArtikelID aus, erhöht sie um 1 und gibt sie wieder.
     * @return neue ArtikelID aufgezählt.
     */    
    public String generiereID() {
   
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
        
    
    
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 17.08.17     SEZ     Erstellt.     
    /*------------------------------------------------------------------------*/
        
    /**
     * Setzt Löschkennzeichen bei einem ausgewählten Artikel.
     * @param a Artikel
     */
    public void setzeLKZ(Artikel a) {

        PreparedStatement stmt = null;
        String artikelID = a.getArtikelID();

        try {
            con.setAutoCommit(false);

            String query 
                = "UPDATE ROOT." + ddd.getTabArtikel() 
                + " SET " + attribute.get(TAB_ARTIKEL).get(10) + " = ?"
                + " WHERE " + attribute.get(TAB_ARTIKEL).get(0) + " = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, "J");
            stmt.setString(2, artikelID);

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
    

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 06.09.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
     
    /**
     * Gibt die Menge FREI zu einem bestimmten Artikel.
     * @param artikelID Artikel dessen Menge FREI wiedergegeben werden soll.
     * @return Gibt die Menge FREI wieder.
     */
    public String gibMengeFrei(String artikelID) {
        
        //Variablendeklaration
        PreparedStatement  stmt = null;
        ResultSet rs = null;  
        String menge = "";
        
        String query = "SELECT " + attribute.get(TAB_ARTIKEL).get(6) 
            + " FROM ROOT." + ddd.getTabArtikel() 
            + " WHERE " + attribute.get(TAB_ARTIKEL).get(0) + " = ?"
            + " AND " + attribute.get(TAB_ARTIKEL).get(10) + " = ?";    

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, artikelID);
            stmt.setString(2, "N");
            rs = stmt.executeQuery();
            con.commit();
            
            while (rs.next()) {
                menge = rs.getString(1);
            }
        
        //Mögliche SQL fehler fangen
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } 
        return menge;
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 06.09.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
     
    /**
     * Gibt die Menge RESERVIERT zu einem bestimmten Artikel.
     * @param artikelID Artikel dessen Menge FREI wiedergegeben werden soll.
     * @return Gibt die Menge RESERVIERT wieder.
     */
    public String gibMengeReserviert(String artikelID) {
        PreparedStatement  stmt = null;
        ResultSet rs = null;  
        String menge = "";
        
        String query = "SELECT " + attribute.get(TAB_ARTIKEL).get(7) 
            + " FROM ROOT." + ddd.getTabArtikel() 
            + " WHERE " + attribute.get(TAB_ARTIKEL).get(0) + " = ?"
            + " AND " + attribute.get(TAB_ARTIKEL).get(10) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, artikelID);
            stmt.setString(2, "N");
            rs = stmt.executeQuery();
            con.commit();
            
            while (rs.next()) {
                menge = rs.getString(1);
            }
        
        //Mögliche SQL fehler fangen
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } 
        return menge;
    }    
 
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 06.09.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
     
    /**
     * Gibt die Menge ZULAUF zu einem bestimmten Artikel.
     * @param artikelID Artikel dessen Menge ZULAUF wiedergegeben werden soll.
     * @return Gibt die Menge ZULAUF wieder.
     */
    public String gibMengeZulauf(String artikelID) {
        PreparedStatement  stmt = null;
        ResultSet rs = null;  
        String menge = "";
        
        String query = "SELECT " + attribute.get(TAB_ARTIKEL).get(8) 
            + " FROM ROOT." + ddd.getTabArtikel() 
            + " WHERE " + attribute.get(TAB_ARTIKEL).get(0) + " = ?"
            + " AND " + attribute.get(TAB_ARTIKEL).get(10) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, artikelID);
            stmt.setString(2, "N");
            rs = stmt.executeQuery();
            con.commit();
            
            while (rs.next()) {
                menge = rs.getString(1);
            }
        
        //Mögliche SQL fehler fangen
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Menge ZULAUF konnte nicht"
                + " ausgegeben werden.");
            alert.showAndWait();
        } 
        return menge;
    }   


    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 07.09.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
     
    /**
     * Gibt die Menge VERKAUFT zu einem bestimmten Artikel.
     * @param artikelID Artikel dessen Menge VERKAUFT wiedergegeben werden soll.
     * @return Gibt die Menge VERKAUFT wieder.
     */
    public String gibMengeVerkauft(String artikelID) {
        PreparedStatement  stmt = null;
        ResultSet rs = null;  
        String mengeVerkauft = "";
        
        String query = "SELECT " + attribute.get(TAB_ARTIKEL).get(9) 
            + " FROM ROOT." + ddd.getTabArtikel() 
            + " WHERE " + attribute.get(TAB_ARTIKEL).get(0) + " = ?"
            + " AND " + attribute.get(TAB_ARTIKEL).get(10) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, artikelID);
            stmt.setString(2, "N");
            rs = stmt.executeQuery();
            con.commit();
            
            while (rs.next()) {
                mengeVerkauft = rs.getString(1);
            }
        
        //Mögliche SQL fehler fangen
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } 
        return mengeVerkauft;
    }       
    

    
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 06.09.17     HEN     Erstellt.     
    /*------------------------------------------------------------------------*/
        
    /**
     * Berechnet die Menge FREI und RESERVIERT eines eingegebenen Artikels mit 
     * der eingegebenen Menge.
     * @param artikelID Artikel dessen Bestand berechnet werden soll.
     * @param mengeFrei Menge, mit der der Bestand FREI berechnet werden sollen.
     * @param mengeRes Menge, mit der der Bestand RES berechnet werden sollen.
     * @throws java.sql.SQLException SQLFehler
     */
    public void setzeMengeFreiRes(
            String artikelID, String mengeFrei, String mengeRes) 
            throws SQLException {
        PreparedStatement stmt = null;
        String query;
        
        try {
            con.setAutoCommit(false);

            query = "UPDATE ROOT." + ddd.getTabArtikel() 
                + " SET " + attribute.get(TAB_ARTIKEL).get(6) + " = ?"
                + " WHERE " + attribute.get(TAB_ARTIKEL).get(0) + " = ?"
                + " AND " + attribute.get(TAB_ARTIKEL).get(10) + " = ?";      
            stmt = con.prepareStatement(query);
            stmt.setString(1, mengeFrei);
            stmt.setString(2, artikelID);
            stmt.setString(3, "N");
            stmt.executeUpdate();
            
            
            query = "UPDATE ROOT." + ddd.getTabArtikel() 
                + " SET " + attribute.get(TAB_ARTIKEL).get(7) + " = ?"
                + " WHERE " + attribute.get(TAB_ARTIKEL).get(0) + " = ?"
                + " AND " + attribute.get(TAB_ARTIKEL).get(10) + " = ?"; 
            stmt = con.prepareStatement(query);
            stmt.setString(1, mengeRes);
            stmt.setString(2, artikelID);
            stmt.setString(3, "N");            
            stmt.executeUpdate();
            
            con.commit();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Die Mengen FREI und "
                + "RESERVIERT konnten nicht aktualisiert werden!");
            alert.showAndWait();
            con.rollback();
        }
    }    
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 07.09.17     HEN     Erstellt.     
    /*------------------------------------------------------------------------*/
        
    /**
     * Berechnet die Menge RESERVIERT und VERKAUFT eines eingegebenen Artikels 
     * mit der eingegebenen Menge.
     * @param artikelID Artikel dessen Bestand berechnet werden soll.
     * @param mengeVer Menge, mit der der Bestand VER berechnet werden sollen.
     * @param mengeRes Menge, mit der der Bestand RES berechnet werden sollen.
     * @throws java.sql.SQLException SQLFehler
     */
    public void setzeMengeResVer(
            String artikelID, String mengeVer, String mengeRes) 
            throws SQLException {
        PreparedStatement stmt = null;
        String query;
        
        try {
            con.setAutoCommit(false);

            query = "UPDATE ROOT." + ddd.getTabArtikel() 
                + " SET " + attribute.get(TAB_ARTIKEL).get(9) + " = ?"
                + " WHERE " + attribute.get(TAB_ARTIKEL).get(0) + " = ?"
                + " AND " + attribute.get(TAB_ARTIKEL).get(10) + " = ?";      
            stmt = con.prepareStatement(query);
            stmt.setString(1, mengeVer);
            stmt.setString(2, artikelID);
            stmt.setString(3, "N");
            stmt.executeUpdate();
            
            
            query = "UPDATE ROOT." + ddd.getTabArtikel() 
                + " SET " + attribute.get(TAB_ARTIKEL).get(7) + " = ?"
                + " WHERE " + attribute.get(TAB_ARTIKEL).get(0) + " = ?"
                + " AND " + attribute.get(TAB_ARTIKEL).get(10) + " = ?"; 
            stmt = con.prepareStatement(query);
            stmt.setString(1, mengeRes);
            stmt.setString(2, artikelID);
            stmt.setString(3, "N");            
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
    /* 13.09.17     HEN     Erstellt.     
    /*------------------------------------------------------------------------*/
        
    /**
     * Berechnet die Menge ZULAUF eines eingegebenen Artikels 
     * mit der eingegebenen Menge.
     * @param artikelID Artikel dessen Bestand berechnet werden soll.
     * @param mengeZulauf Menge, mit der der Bestand ZULAUF berechnet wird
     * @throws java.sql.SQLException SQLFehler
     */
    public void setzeMengeZulauf(
            String artikelID, String mengeZulauf) throws SQLException {
        PreparedStatement stmt = null;
        String query;
        
        try {
            con.setAutoCommit(false);
            
            query = "UPDATE ROOT." + ddd.getTabArtikel() 
                + " SET " + attribute.get(TAB_ARTIKEL).get(8) + " = ?"
                + " WHERE " + attribute.get(TAB_ARTIKEL).get(0) + " = ?"
                + " AND " + attribute.get(TAB_ARTIKEL).get(10) + " = ?"; 
            stmt = con.prepareStatement(query);
            stmt.setString(1, mengeZulauf);
            stmt.setString(2, artikelID);
            stmt.setString(3, "N");            
            stmt.executeUpdate();
            
            con.commit();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Menge: ZULAUF konnte nicht"
                + " gesetzt werden.");
            alert.showAndWait();
            con.rollback();
        }
    }    

    
    
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 13.09.17     HEN     Erstellt.     
    /*------------------------------------------------------------------------*/
        
    /**
     * Berechnet die Menge FREI und ZULAUF eines eingegebenen Artikels 
     * mit der eingegebenen Menge.
     * @param artikelID Artikel dessen Bestand berechnet werden soll.
     * @param mengeFrei Menge, mit der der Bestand FREI berechnet wird
     * @param mengeZulauf Menge, mit der der Bestand ZULAUF berechnet wird
     * @throws java.sql.SQLException SQLFehler
     */
    public void setzeMengeZulaufFrei(
            String artikelID, String mengeFrei, String mengeZulauf) 
            throws SQLException {
        PreparedStatement stmt = null;
        String query;
        
        try {
            con.setAutoCommit(false);

            query = "UPDATE ROOT." + ddd.getTabArtikel() 
                + " SET " + attribute.get(TAB_ARTIKEL).get(6) + " = ?"
                + " WHERE " + attribute.get(TAB_ARTIKEL).get(0) + " = ?"
                + " AND " + attribute.get(TAB_ARTIKEL).get(10) + " = ?";      
            stmt = con.prepareStatement(query);
            stmt.setString(1, mengeFrei);
            stmt.setString(2, artikelID);
            stmt.setString(3, "N");
            stmt.executeUpdate();
            
            
            query = "UPDATE ROOT." + ddd.getTabArtikel() 
                + " SET " + attribute.get(TAB_ARTIKEL).get(8) + " = ?"
                + " WHERE " + attribute.get(TAB_ARTIKEL).get(0) + " = ?"
                + " AND " + attribute.get(TAB_ARTIKEL).get(10) + " = ?"; 
            stmt = con.prepareStatement(query);
            stmt.setString(1, mengeZulauf);
            stmt.setString(2, artikelID);
            stmt.setString(3, "N");            
            stmt.executeUpdate();
            
            con.commit();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Mengen: FREI und ZULAUF "
                + " konnten nicht gesetzt werden.");
            alert.showAndWait();
            con.rollback();
        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 10.09.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt den Steuersatz zu einer bestimmten ArikelID aus.
     * @param artikelID Artikel für den die Steuer ausgegeben werden soll.
     * @return Ausgelesener Steuersatz
     * @throws java.sql.SQLException SQLFehler
     */
    public String gibArtikelSteuer(String artikelID) 
            throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String steuer = "";

        String query = "SELECT " + attribute.get(TAB_ARTIKEL).get(5) 
            + " FROM ROOT." + ddd.getTabArtikel()
            + " WHERE " + attribute.get(TAB_ARTIKEL).get(10) + " = ?"
            + " AND " + attribute.get(TAB_ARTIKEL).get(0) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            stmt.setString(2, artikelID);
            rs = stmt.executeQuery();
            con.commit();
            
            if (rs.next()) {
                steuer = rs.getString(1);
            }
            
        //Mögliche SQL fehler fangen
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Steuersatz konnte icht"
                + "abgerufen werden!");
            alert.showAndWait();
            con.rollback();
        }
        return steuer;
    }     
    
    
}
