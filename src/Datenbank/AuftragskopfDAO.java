/*------------------------------------------------------------------------------
* Klasse: AuftragskopfDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse bearbeitet Queries Auftragsköpfe.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 07.08.2017    BER     Erstellt.
*-------------------------------------------------------------------------------
*/

package Datenbank;

import Klassen.Auftragskopf;
import Klassen.Geschaeftspartner;
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
public class AuftragskopfDAO extends DataAccess {

    /**
     * Erzeugt ein neues DataDictionaryDAO Objekt.
     */
    private DataDictionaryDAO ddd = new DataDictionaryDAO();
    
    /**
     * 
     */
    private String TAB_AUFTRAGSKOPF = ddd.getTAB_AUFTRAGSKOPF();

    /**
     * 
     */
    private String TAB_GESCHAEFTSPARTNER = ddd.getTAB_GESCHAEFTSPARTNER();    
    
    
    /**
     * 
     */
    private HashMap<String, ArrayList> attribute;     
        /**
     * Konstruktor.
     *
     * @throws SQLException SQLException
     */
    public AuftragskopfDAO() throws SQLException {
        attribute = ddd.getTabellenAttribute();
        ddd.holeAlleAttribute(TAB_AUFTRAGSKOPF);
        ddd.holeAlleAttribute(TAB_GESCHAEFTSPARTNER);
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 07.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Adressen wieder die sich in der Datenbank befinden.
     * @return Gibt Arraylist aller Adressen wieder
     * @throws java.sql.SQLException SQLException
     */
    public ArrayList<Auftragskopf> gibAlleAuftragskoepfe() throws SQLException {

        //Variablendeklaration
        Statement stmt = null;
        ResultSet rs = null;
        Auftragskopf auftragskopf = null;
        ArrayList<Auftragskopf> auftragskopfListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT." + ddd.getTabAuftragskopf();

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                auftragskopf = new Auftragskopf(rs.getString(1), 
                        rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), 
                        rs.getString(8), rs.getString(9), rs.getString(10));
                auftragskopfListe.add(auftragskopf);
            }
            
            con.commit();
            con.close();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            con.rollback();
        }
        return auftragskopfListe;
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 07.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Adressen ohne Löschkennzeichen wieder.
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     * @throws java.sql.SQLException SQLException
     */
    public ArrayList<Auftragskopf> 
        gibAlleAuftragskoepfeOhneLKZ() throws SQLException {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Auftragskopf auftragskopf = null;
        ArrayList<Auftragskopf> auftragskopfListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT." + ddd.getTabAuftragskopf() 
            + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(9) + " = ?"
            + " ORDER BY " + attribute.get(TAB_AUFTRAGSKOPF).get(0) + " DESC";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            rs = stmt.executeQuery();

            con.commit();
            while (rs.next()) {
                auftragskopf = new Auftragskopf(rs.getString(1), 
                        rs.getString(2), rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6), rs.getString(7), 
                        rs.getString(8), rs.getString(9), rs.getString(10));

                auftragskopfListe.add(auftragskopf);
            }
            //Fehler werfen wenn Rückgabeobjekt leer ist
            if (auftragskopfListe.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText("Keine Auftragsköpfe gefunden!");
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
        return auftragskopfListe;
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Adressen mit Löschkennzeichen wieder.
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     * @throws java.sql.SQLException SQLException
     */
    public ArrayList<Auftragskopf> 
        gibAlleAuftragskoepfeMitLKZ() throws SQLException {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Auftragskopf auftragskopf = null;
        ArrayList<Auftragskopf> auftragskopfListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT." + ddd.getTabAuftragskopf() 
            + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(9) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "J");
            rs = stmt.executeQuery();

            con.commit();
            while (rs.next()) {
                auftragskopf = new Auftragskopf(rs.getString(1), 
                        rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), 
                        rs.getString(8), rs.getString(9), rs.getString(10));

                auftragskopfListe.add(auftragskopf);
            }
            //Fehler werfen wenn Rückgabeobjekt leer ist
            if (auftragskopfListe.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText("Keine Auftragsköpfe gefunden!");
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
        return auftragskopfListe;
    }

        
                   
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 26.08.17     HEN     Erstellt.    
    /*------------------------------------------------------------------------*/
    
    /**
     * Setzt Löschkennzeichen bei einem ausgewählten Auftrag. Prüft vorher, ob
     * der Auftrag noch Positionen hat.
     * @param a Adresse
     * @throws java.sql.SQLException SQLException
     */
    public void setzeLKZ(Auftragskopf a) throws SQLException {
        PreparedStatement stmt = null;
        String auftragskopfID = a.getAuftragskopfID();

//        if(Auftragskopf hat noch Positionen) {
//            Error
//        }
//        else {
            try {
                con.setAutoCommit(false);

                String query
                    = "UPDATE ROOT." + ddd.getTabAuftragskopf() 
                    + " SET " + attribute.get(TAB_AUFTRAGSKOPF).get(9) + " = ?"
                    + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(0) 
                    + " = ?";

                stmt = con.prepareStatement(query);
                stmt.setString(1, "J");
                stmt.setString(2, auftragskopfID);

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
//        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 26.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Fügt einen Auftrag der Datenbank hinzu.
     * @param a Adressobjekt
     * @throws java.sql.SQLException SQLException.
     */
    public void fuegeAuftragHinzu(Auftragskopf a) throws SQLException {
     
        PreparedStatement stmt = null;
        String auftragskopfID = generiereID();
        String geschaeftspartnerID = a.getGeschaeftspartnerID();
        String auftragsText = a.getAuftragstext();
        String erfassungsDatum = a.getErfassungsdatum();
        String lieferDatum = a.getLieferdatum();
        String abschlussDatum = a.getAbschlussDatum();
        String status = a.getStatus();
        String auftragsArt = a.getAuftragsart();
        String auftragsWert = a.getAuftragswert();
        String lkz = a.getLkz();
               
        try {
            con.setAutoCommit(false);

            String query = "INSERT INTO ROOT." + ddd.getTabAuftragskopf()
                    + "(" + attribute.get(TAB_AUFTRAGSKOPF).get(0) + ", " 
                    +  attribute.get(TAB_AUFTRAGSKOPF).get(1) + ", " 
                    +  attribute.get(TAB_AUFTRAGSKOPF).get(2) + ", " 
                    +  attribute.get(TAB_AUFTRAGSKOPF).get(3) + ", " 
                    +  attribute.get(TAB_AUFTRAGSKOPF).get(4) + ", "
                    +  attribute.get(TAB_AUFTRAGSKOPF).get(5) + ", "
                    +  attribute.get(TAB_AUFTRAGSKOPF).get(6) + ", "
                    +  attribute.get(TAB_AUFTRAGSKOPF).get(7) + ", "
                    +  attribute.get(TAB_AUFTRAGSKOPF).get(8) + ", "
                    +  attribute.get(TAB_AUFTRAGSKOPF).get(9) + ") "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)";

            stmt = con.prepareStatement(query);
            stmt.setString(1, auftragskopfID);
            stmt.setString(2, geschaeftspartnerID);
            stmt.setString(3, auftragsText);
            stmt.setString(4, erfassungsDatum);
            stmt.setString(5, lieferDatum);
            stmt.setString(6, abschlussDatum);
            stmt.setString(7, status);
            stmt.setString(8, auftragsArt);
            stmt.setString(9, auftragsWert);
            stmt.setString(10, lkz);

            stmt.executeUpdate();
            con.commit();
            con.close();
            
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
    /* Datum       Name    Was
    /* 26.08.17    HEN     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Ändert den Auftragskopf in der DB.
     * @param a Adressobjekt
     * @throws java.sql.SQLException SQLException
     */
    public void aendereAuftragskopf(Auftragskopf a) throws SQLException {
        PreparedStatement stmt = null;
        String query = "";

        try {
            con.setAutoCommit(false);

            query
                = "UPDATE ROOT." + ddd.getTabAuftragskopf() 
                + " SET " + attribute.get(TAB_AUFTRAGSKOPF).get(2) 
                + " = ? WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getAuftragstext());
            stmt.setString(2, a.getAuftragskopfID());
            stmt.executeUpdate();

            
            query
                = "UPDATE ROOT." + ddd.getTabAuftragskopf() 
                + " SET " + attribute.get(TAB_AUFTRAGSKOPF).get(3) 
                + " = ? WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getErfassungsdatum());
            stmt.setString(2, a.getAuftragskopfID());
            stmt.executeUpdate();

            
            query
                = "UPDATE ROOT." + ddd.getTabAuftragskopf() 
                + " SET " + attribute.get(TAB_AUFTRAGSKOPF).get(5) 
                + " = ? WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getAbschlussDatum());
            stmt.setString(2, a.getAuftragskopfID());
            stmt.executeUpdate();             
            
            
            query
                = "UPDATE ROOT." + ddd.getTabAuftragskopf() 
                + " SET " + attribute.get(TAB_AUFTRAGSKOPF).get(6) 
                + " = ? WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getStatus());
            stmt.setString(2, a.getAuftragskopfID());
            stmt.executeUpdate();

            
            query
                = "UPDATE ROOT." + ddd.getTabAuftragskopf() 
                + " SET " + attribute.get(TAB_AUFTRAGSKOPF).get(7) 
                + " = ? WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getAuftragsart());
            stmt.setString(2, a.getAuftragskopfID());
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
    /* 26.08.17     HEN     Erstellt.    
    /*------------------------------------------------------------------------*/
    
    /**
     * Setzt Löschkennzeichen bei einer ausgewählten Adresse.
     * @return neue ID aufgezählt.
     * @throws java.sql.SQLException SQLException
     */    
    public String gibLetztID() throws SQLException {
        Statement stmt = null;
        String value = "";
        ResultSet rs = null;
        String query = "SELECT MAX(" 
            + attribute.get(TAB_AUFTRAGSKOPF).get(0) + ") FROM ROOT." 
            + ddd.getTabAuftragskopf();
        
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
    /* 28.08.17     HEN     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die letzte ID aus, erhöht sie um 1 und gibt sie wieder.
     * @return neue ID aufgezählt.
     * @throws java.sql.SQLException SQLException.
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
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 07.09.17     HEN     Erstellt.    
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt den Auftragsstatus zu einem bestimmten Auftrag.
     * @param auftragskopfID Auftrags für den der Status ausgegeben werden soll.
     * @return Auftragsstatus
     * @throws java.sql.SQLException SQLFehler
     */    
    public String gibAuftragsstatus(String auftragskopfID) throws SQLException {
        PreparedStatement stmt = null;
        String status = "";
        ResultSet rs = null;
        
        String query = "SELECT " + attribute.get(TAB_AUFTRAGSKOPF).get(6) 
            + " FROM ROOT." + ddd.getTabAuftragskopf() 
            + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(0) + " = ?"
            + " AND " + attribute.get(TAB_AUFTRAGSKOPF).get(9) + " = ?";
        
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, auftragskopfID);
            stmt.setString(2, "N");
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                status = rs.getString(1);           
            }
           
            con.commit();
            
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Der Auftragssatus konnte"
                + " nicht abgerufen werden!");
            alert.showAndWait();
            con.rollback();
        }
        return status;
    }    
 
    
    
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 13.09.17     HEN     Erstellt.    
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt den Auftragswert zu einem bestimmten Auftrag.
     * @param auftragskopfID Auftrags für den der Status ausgegeben werden soll.
     * @return Auftragswert
     * @throws java.sql.SQLException SQLFehler
     */    
    public String gibAuftragswert(String auftragskopfID) throws SQLException {
        PreparedStatement stmt = null;
        String wert = "";
        ResultSet rs = null;
        
        String query = "SELECT " + attribute.get(TAB_AUFTRAGSKOPF).get(8) 
            + " FROM ROOT." + ddd.getTabAuftragskopf() 
            + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(0) + " = ?"
            + " AND " + attribute.get(TAB_AUFTRAGSKOPF).get(9) + " = ?";
        
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, auftragskopfID);
            stmt.setString(2, "N");
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                wert = rs.getString(1);           
            }
           
            con.commit();
            
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Der Auftragswert konnte"
                + " nicht abgerufen werden.");
            alert.showAndWait();
            con.rollback();
        }
        return wert;
    }     
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 12.09.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     *Gibt alle Geschäftspartner mit dem Typen "K" ohne Löschkennzeichen wieder.
     * @return Gibt ArrayList aller Geschäftspartner ohne LKZ wieder.
     * @throws java.sql.SQLException SQLFehler
     */
    public ArrayList<Auftragskopf> 
        gibAlleGeschaeftspartnerKunde() throws SQLException {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Auftragskopf auftragskopf = null;
        ArrayList<Auftragskopf> auftragskopfListe = new ArrayList<>();

        String query = "SELECT " + ddd.getTabAuftragskopf() + ".*"
            + " FROM ROOT." + ddd.getTabAuftragskopf()
            + " JOIN ROOT." + ddd.getTabGeschaeftspartner()
            + " ON ROOT." + ddd.getTabAuftragskopf() + "." 
            + attribute.get(TAB_AUFTRAGSKOPF).get(1) + " = " 
            + " ROOT." + ddd.getTabGeschaeftspartner() + "." 
            + attribute.get(TAB_GESCHAEFTSPARTNER).get(0) 
            + " AND ROOT." + ddd.getTabGeschaeftspartner() + "." 
            + attribute.get(TAB_GESCHAEFTSPARTNER).get(1) + " = ?";     
    
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "K");
            rs = stmt.executeQuery();

            con.commit();
            while (rs.next()) {
                auftragskopf = new Auftragskopf(rs.getString(1), 
                        rs.getString(2), rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6), rs.getString(7), 
                        rs.getString(8), rs.getString(9), rs.getString(10));

                auftragskopfListe.add(auftragskopf);
            }
            //Fehler werfen wenn Rückgabeobjekt leer ist
            if (auftragskopfListe.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText("Keine Geschäftspartner 'K' gefunden!");
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
        return auftragskopfListe;
    }       
    

        
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 12.09.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     *Gibt alle Geschäftspartner mit dem Typen "K" ohne Löschkennzeichen wieder.
     * @return Gibt ArrayList aller Geschäftspartner ohne LKZ wieder.
     * @throws java.sql.SQLException SQLFehler
     */
    public ArrayList<Auftragskopf> 
        gibAlleGeschaeftspartnerLieferant() throws SQLException {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Auftragskopf auftragskopf = null;
        ArrayList<Auftragskopf> auftragskopfListe = new ArrayList<>();

        String query = "SELECT " + ddd.getTabAuftragskopf() + ".*"
            + " FROM ROOT." + ddd.getTabAuftragskopf()
            + " JOIN ROOT." + ddd.getTabGeschaeftspartner()
            + " ON ROOT." + ddd.getTabAuftragskopf() + "." 
            + attribute.get(TAB_AUFTRAGSKOPF).get(1) + " = " 
            + " ROOT." + ddd.getTabGeschaeftspartner() + "." 
            + attribute.get(TAB_GESCHAEFTSPARTNER).get(0) 
            + " AND ROOT." + ddd.getTabGeschaeftspartner() + "." 
            + attribute.get(TAB_GESCHAEFTSPARTNER).get(1) + " = ?";     
    
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "L");
            rs = stmt.executeQuery();

            con.commit();
            while (rs.next()) {
                auftragskopf = new Auftragskopf(rs.getString(1), 
                        rs.getString(2), rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6), rs.getString(7), 
                        rs.getString(8), rs.getString(9), rs.getString(10));

                auftragskopfListe.add(auftragskopf);
            }
            //Fehler werfen wenn Rückgabeobjekt leer ist
            if (auftragskopfListe.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText("Keine Geschäftspartner 'L' gefunden!");
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
        return auftragskopfListe;
    }          
        
        
}
