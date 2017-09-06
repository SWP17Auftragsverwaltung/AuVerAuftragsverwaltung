/*------------------------------------------------------------------------------
* Klasse: AuftragspositionDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse bearbeitet Queries Auftragspositionen.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 27.08.2017    HEN     Erstellt.
*-------------------------------------------------------------------------------
*/

package Datenbank;

import Klassen.Auftragsposition;
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
 * @author Andre
 */
public class AuftragspositionDAO extends DataAccess {

    /**
     * Erzeugt ein neues DataDictionaryDAO Objekt.
     */
    private DataDictionaryDAO ddd = new DataDictionaryDAO();
    
    /**
     * 
     */
    private String TAB_AUFTRAGSPOSITION = ddd.getTAB_AUFTRAGSPOSITION();
    
    /**
     * 
     */
    private String TAB_AUFTRAGSKOPF = ddd.getTAB_AUFTRAGSKOPF();
    
    /**
     * 
     */
    private HashMap<String, ArrayList> attribute;     
        /**
     * Konstruktor.
     *
     * @throws SQLException SQLException
     */
    public AuftragspositionDAO() throws SQLException {
        attribute = ddd.getTabellenAttribute();
        ddd.holeAlleAttribute(TAB_AUFTRAGSPOSITION);
        ddd.holeAlleAttribute(TAB_AUFTRAGSKOPF);
    }


    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Auftragspositionen ohne Löschkennzeichen wieder.
     * @return Gibt ArrayList aller Auftragspositionen ohne LKZ wieder.
     * @throws java.sql.SQLException SQLException
     */
    public ArrayList<Auftragsposition> 
        gibAlleAuftragspositionenOhneLKZ() throws SQLException {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Auftragsposition auftragsposition = null;
        ArrayList<Auftragsposition> auftragspositionListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT." + ddd.getTabAuftragsposition() 
            + " WHERE " + attribute.get(TAB_AUFTRAGSPOSITION).get(5) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            rs = stmt.executeQuery();

            con.commit();
            while (rs.next()) {
                auftragsposition = new Auftragsposition(rs.getString(1), 
                        rs.getString(2), rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6));

                auftragspositionListe.add(auftragsposition);
            }
            //Fehler werfen wenn Rückgabeobjekt leer ist
            if (auftragspositionListe.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText("Keine Auftragspositionen gefunden!");
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
        return auftragspositionListe;
    }    
    

        
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 27.08.17     HEN     Erstellt.    
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt die letzte ID einer ausgewählten Auftragsposition wieder und zählt
     * sie um 1 hoch.
     * @param posNr Positionsnummer
     * @return neue ID aufgezählt.
     * @throws java.sql.SQLException SQLException
     */    
    public String gibLetztID(String posNr) throws SQLException {
        PreparedStatement stmt = null;
        String value = "";
        ResultSet rs = null;
        String query = "SELECT MAX(" 
            + attribute.get(TAB_AUFTRAGSPOSITION).get(1) + ") FROM ROOT." 
            + ddd.getTabAuftragsposition() + " WHERE " 
            + attribute.get(TAB_AUFTRAGSPOSITION).get(0) + " = ?";
        
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, posNr);
            rs = stmt.executeQuery();
            
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
    /* 27.08.17     HEN     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die letzte ID aus, erhöht sie um 1 und gibt sie wieder.
     * @param posNr Pos
     * @return neue ID aufgezählt.
     * @throws java.sql.SQLException SQLException.
     */    
    public String generiereID(String posNr) throws SQLException {
        //Holt sich die aktuell maximale ID.
        String alteIDString = gibLetztID(posNr);
        String neueID;

        if (alteIDString != null) {
            //Parsed die ID von String nach Int.
            int alteIDInt = Integer.parseInt(alteIDString);

            //Zählt die ID um 1 hoch.
            alteIDInt++;

            //Fügt die neue ID in den String und füllt vordere Zahlen mit 0 auf,
            //wenn neueID < 6 Zeichen.
            neueID = String.format("%03d", alteIDInt);
        
        } else {
            neueID = "001";
        }
        return neueID;
    }            
        

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Fügt eine Auftragsposition einem bestehenden Auftrag hinzu.
     * @param ap AuftragspositionObjekt
     * @throws java.sql.SQLException SQLException.
     */
    public void fuegeAuftragspositionHinzu(
            Auftragsposition ap) throws SQLException {
        
        PreparedStatement stmt = null;
        String auftragskopfID = ap.getAuftragskopfID();
        String positionsnummer = generiereID(auftragskopfID);
        String artikelID = ap.getArtikelID();
        String menge = ap.getMenge();
        String einzelwert = ap.getEinzelwert();
        String lkz = ap.getLkz();
        String query = "";
               
        try {
            con.setAutoCommit(false);

            if (artikelVorhanden(artikelID)) {
                int mengeInt = Integer.parseInt(menge);
                menge = berechneArtikelwert(artikelID, mengeInt);
                
                query = "UPDATE ROOT." + ddd.getTabAuftragsposition() 
                    + " SET " + attribute.get(TAB_AUFTRAGSPOSITION).get(3) 
                    + " = ?" 
                    + " WHERE " + attribute.get(TAB_AUFTRAGSPOSITION).get(2)
                    + " = ?";
                
                stmt = con.prepareStatement(query);
                stmt.setString(1, menge);
                stmt.setString(2, artikelID);
                stmt.executeUpdate();
            
            } else {
                query = "INSERT INTO ROOT." + ddd.getTabAuftragsposition()
                    + " (" + attribute.get(TAB_AUFTRAGSPOSITION).get(0) + ", " 
                    +  attribute.get(TAB_AUFTRAGSPOSITION).get(1) + ", " 
                    +  attribute.get(TAB_AUFTRAGSPOSITION).get(2) + ", " 
                    +  attribute.get(TAB_AUFTRAGSPOSITION).get(3) + ", " 
                    +  attribute.get(TAB_AUFTRAGSPOSITION).get(4) + ", "
                    +  attribute.get(TAB_AUFTRAGSPOSITION).get(5) + ") "
                    + "VALUES (?,?,?,?,?,?)";

                stmt = con.prepareStatement(query);
                stmt.setString(1, auftragskopfID);
                stmt.setString(2, positionsnummer);
                stmt.setString(3, artikelID);
                stmt.setString(4, menge);
                stmt.setString(5, einzelwert);
                stmt.setString(6, lkz);

                stmt.executeUpdate();
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
    }        
    
       
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 04.09.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Rechnet einer bestehenden ArtikelID die eingegebene Menge hinzu.
     * @param artikelID Eingegebene Artikel ID
     * @param menge Zu addierende Menge
     * @return Gibt ArrayList aller Auftragspositionen ohne LKZ wieder.
     * @throws java.sql.SQLException SQLException
     */
    public String berechneArtikelwert(String artikelID, int menge)
            throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String neueMenge = "";
        int rechnung;

        String query = "SELECT MENGE FROM ROOT." + ddd.getTabAuftragsposition() 
            + " WHERE " + attribute.get(TAB_AUFTRAGSPOSITION).get(5) + " = ?"
            + " AND " + attribute.get(TAB_AUFTRAGSPOSITION).get(2) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            stmt.setString(2, artikelID);
            rs = stmt.executeQuery();

            con.commit();
            if (rs.next()) {
                neueMenge = rs.getString(1);
                rechnung = Integer.parseInt(neueMenge);
                rechnung = rechnung + menge;
                neueMenge = String.valueOf(rechnung);
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
        return neueMenge;
    } 


    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 04.09.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Rechnet einer bestehenden ArtikelID die eingegebene Menge hinzu.
     * @param wert Berechneter Auftrasgwert
     * @param auftragsID Auftragskopf, dessen Wert gesetzt werden soll
     * @throws java.sql.SQLException SQLException
     */
    public void berechneAuftragswert(double wert, String auftragsID)
            throws SQLException {
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String alterWert = "";
        double neuerWertDouble;
        String neuerWert = "";

        String query = "SELECT " + attribute.get(TAB_AUFTRAGSKOPF).get(8) 
            + " FROM ROOT." + ddd.getTabAuftragskopf()
            + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(9) + " = ?"
            + " AND " + attribute.get(TAB_AUFTRAGSKOPF).get(0) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            stmt.setString(2, auftragsID);
            rs = stmt.executeQuery();
            con.commit();
            
            if (rs.next()) {
                alterWert = rs.getString(1);
                neuerWertDouble = Double.parseDouble(alterWert);
                neuerWertDouble = neuerWertDouble + wert;
                
                neuerWertDouble = neuerWertDouble * 100;
                neuerWertDouble = Math.round(neuerWertDouble);
                neuerWertDouble = neuerWertDouble / 100;
                
                neuerWert = String.valueOf(neuerWertDouble);
                           
                setzeAuftragswert(neuerWert, auftragsID);
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
    } 

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 04.09.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Rechnet einer bestehenden ArtikelID die eingegebene Menge hinzu.
     * @param auftragswert Berechneter Auftrasgwert
     * @param auftragsID Auftragskopf, dessen Wert gesetzt werden soll
     * @throws java.sql.SQLException SQLException
     */
    public void setzeAuftragswert(String auftragswert, String auftragsID)
            throws SQLException {
        
        PreparedStatement stmt = null;
        String query = "";

        try {
            con.setAutoCommit(false);

            query = "UPDATE ROOT." + ddd.getTabAuftragskopf()
                + " SET " + attribute.get(TAB_AUFTRAGSKOPF).get(8) 
                + " = ? WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(0) 
                + " = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, auftragswert);
            stmt.setString(2, auftragsID);
            stmt.executeUpdate();
            con.commit();

            //Mögliche SQL fehler fangen
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
    /* 04.09.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Rechnet einer bestehenden ArtikelID die eingegebene Menge hinzu.
     * @param auftragsID Auftragskopf, dessen Wert ausgelesen werden soll
     * @return Ausgelesener Auftragswert
     * @throws java.sql.SQLException SQLException
     */
    public String gibAuftragswert(String auftragsID) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String auftragswert = "";

        String query = "SELECT " + attribute.get(TAB_AUFTRAGSKOPF).get(8) 
            + " FROM ROOT." + ddd.getTabAuftragskopf()
            + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(9) + " = ?"
            + " AND " + attribute.get(TAB_AUFTRAGSKOPF).get(0) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            stmt.setString(2, auftragsID);
            rs = stmt.executeQuery();
            con.commit();
            
            if (rs.next()) {
                auftragswert = rs.getString(1);
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
        return auftragswert;
    } 
    
    

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 04.09.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Rechnet einer bestehenden ArtikelID die eingegebene Menge hinzu.
     * @param positionsnummer PosNr
     * @return Ausgelesener Auftragswert
     * @throws java.sql.SQLException SQLException
     */
    public String gibPositionsMenge(String positionsnummer) 
            throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String posMenge = "";

        String query = "SELECT " + attribute.get(TAB_AUFTRAGSPOSITION).get(3) 
            + " FROM ROOT." + ddd.getTabAuftragsposition()
            + " WHERE " + attribute.get(TAB_AUFTRAGSPOSITION).get(5) + " = ?"
            + " AND " + attribute.get(TAB_AUFTRAGSPOSITION).get(1) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            stmt.setString(2, positionsnummer);
            rs = stmt.executeQuery();
            con.commit();
            
            if (rs.next()) {
                posMenge = rs.getString(1);
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
        return posMenge;
    }     
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 27.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Auftragspositionen zu einem bestimmten Auftrag wieder.
     * @param auftragsID AuftragsID, zu den Positionen gesucht werden sollen.
     * @return Gibt ArrayList aller Auftragspositionen ohne LKZ wieder.
     * @throws java.sql.SQLException SQLException
     */
    public ArrayList<Auftragsposition> 
        gibAuftragspositionenZuAuftrag(String auftragsID) throws SQLException {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Auftragsposition auftragsposition = null;
        ArrayList<Auftragsposition> auftragspositionListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT." + ddd.getTabAuftragsposition() 
            + " WHERE " + attribute.get(TAB_AUFTRAGSPOSITION).get(5) + " = ?"
            + " AND " + attribute.get(TAB_AUFTRAGSPOSITION).get(0) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            stmt.setString(2, auftragsID);
            rs = stmt.executeQuery();

            con.commit();
            while (rs.next()) {
                auftragsposition = new Auftragsposition(rs.getString(1), 
                        rs.getString(2), rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6));

                auftragspositionListe.add(auftragsposition);
            }
            //Fehler werfen wenn Rückgabeobjekt leer ist
            if (auftragspositionListe.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText("Keine Auftragspositionen gefunden!");
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
        return auftragspositionListe;
    } 
        


    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 04.09.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Auftragspositionen zu einem bestimmten Auftrag wieder.
     * @param artikelID ArtikelID
     * @return Gibt ArrayList aller Auftragspositionen ohne LKZ wieder.
     * @throws java.sql.SQLException S
     */
    public boolean artikelVorhanden(String artikelID) throws SQLException {
        boolean ergebnis = false;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM ROOT." + ddd.getTabAuftragsposition() 
                + " WHERE " + attribute.get(TAB_AUFTRAGSPOSITION).get(2) 
                + " = '" + artikelID + "' AND LKZ = 'N'";
            
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            con.commit();
            ergebnis = rs.next();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            con.rollback();       
        }

        return ergebnis;
    }
 
   
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 04.09.17     HEN     Erstellt.   
    /*------------------------------------------------------------------------*/
    
    /**
     * Setzt Löschkennzeichen bei einer markierten Auftragsposition.
     * @param ap Zu löschende Auftragsposition
     * @throws java.sql.SQLException Fehlerhafter SQL Befehl.
     */
    public void setzeAuftragsposLKZ(Auftragsposition ap) throws SQLException {
        PreparedStatement stmt = null;
        String positionsnummer = ap.getPositionsnummer();

        try {
            con.setAutoCommit(false);

            String query
                = "UPDATE ROOT." + ddd.getTabAuftragsposition()
                + " SET " + attribute.get(TAB_AUFTRAGSPOSITION).get(5) + " = ?"
                + " WHERE " + attribute.get(TAB_AUFTRAGSPOSITION).get(1) 
                + " = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, "J");
            stmt.setString(2, positionsnummer);
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
    /* Datum       Name    Was
    /* 05.09.17    HEN     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Ändert eine markierte Auftragsposition in der Datenbank.
     * @param ap Auftragspositionsobjekt
     * @throws java.sql.SQLException Fehlerhafter SQL Befehl.
     */
    public void aendereAuftragsposition(Auftragsposition ap) 
            throws SQLException {
        PreparedStatement stmt = null;
        String query = "";

        try {
            con.setAutoCommit(false);

            query
                = "UPDATE ROOT." + ddd.getTabAuftragsposition()
                + " SET " + attribute.get(TAB_AUFTRAGSPOSITION).get(3) 
                + " = ? WHERE " + attribute.get(TAB_AUFTRAGSPOSITION).get(1) 
                + " = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, ap.getMenge());
            stmt.setString(2, ap.getPositionsnummer());
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
