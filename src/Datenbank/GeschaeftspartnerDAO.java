/*------------------------------------------------------------------------------
* Klasse: GeschäftspartnerDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse stellt eine Verbindung zur Datenbank her und bearbeitet alles
*   bezüglich Geschäftspartner.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 14.08.2017    CEL     Erstellt.
* 15.08.2017    CEL     gibAlleGeschaeftspartnerMitLKZ(), 
                        gibAlleGeschaeftspartnerMitLKZ(),
                        gibAlleGeschaeftspartner() erstellt.
* 15.08.2017    HEN     fuegeGeschaeftspartnerHinzu() erstellt.
* 17.08.2017    SEZ     generiereID(), gibLetztID() erstellt.
* 17.08.2017    BER     setzeLKZ() erstellt.
* 17.08.2017    BER     aendernGeschaeftspartner() erstellt.
*-------------------------------------------------------------------------------
 */
package Datenbank;

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
 * @author Chakir
 */
public class GeschaeftspartnerDAO extends DataAccess {

    /**
     * Erzeugt ein neues DataDictionaryDAO Objekt.
     */
    private DataDictionaryDAO ddd = new DataDictionaryDAO();
    
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
    public GeschaeftspartnerDAO() throws SQLException {
        attribute = ddd.getTabellenAttribute();
        ddd.holeAlleAttribute(TAB_GESCHAEFTSPARTNER);
    }

    /*------------------------------------------------------------------------*/
 /* Datum       Name    Was
    /* 14.08.17    CEL     Erstellt.
    /*------------------------------------------------------------------------*/
    /**
     * Gibt alle Geschäftspartner wieder, die sich in der Datenbank befinden.
     *
     * @return Gibt Arraylist aller Zahlungskonditionen wieder
     */
    public ArrayList<Geschaeftspartner> gibAlleGeschaeftspartner() {

        //Variablendeklaration.
        Statement stmt = null;
        ResultSet rs = null;
        Geschaeftspartner geschaeftspartner = null;
        ArrayList<Geschaeftspartner> geschaeftspartnerListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT." + ddd.getTabGeschaeftspartner();

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            con.commit();
            
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

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    CEL     Erstellt und getestet.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Geschäftspartner ohne Löschkennzeichen wieder.
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     */
    public ArrayList<Geschaeftspartner> gibAlleGeschaeftspartnerOhneLKZ() {
        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Geschaeftspartner geschaeftspartner = null;
        ArrayList<Geschaeftspartner> geschaeftspartnerListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT." + ddd.getTabGeschaeftspartner() 
            + " WHERE " + attribute.get(TAB_GESCHAEFTSPARTNER).get(5) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            rs = stmt.executeQuery();
            con.commit();
            
            while (rs.next()) {
                geschaeftspartner = new Geschaeftspartner(rs.getString(1), 
                        rs.getString(2), rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6));

                geschaeftspartnerListe.add(geschaeftspartner);
            }
            //Fehler werfen wenn Rückgabeobjekt leer ist
            if (geschaeftspartnerListe.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText("Keine Geschaeftspartner gefunden!");
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
        return geschaeftspartnerListe;
    }

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Fügt einen Geschäftspartner der Datenbank hinzu.
     * @param gp Geschäftspartnerobjekt
     */
    public void fuegeGeschaeftspartnerHinzu(Geschaeftspartner gp) {
        PreparedStatement stmt = null;
        String geschaeftspartnerID = generiereID();
        String typ = gp.getTyp();
        String adresseID = gp.getAdresseID();
        String lieferID = gp.getLieferID();
        String kreditlimit = gp.getKreditlimit();
        String lkz = gp.getLKZ();

        try {
            con.setAutoCommit(false);

            String query = "INSERT INTO ROOT." + ddd.getTabGeschaeftspartner()
                    + "(" + attribute.get(TAB_GESCHAEFTSPARTNER).get(0) + "," 
                    + attribute.get(TAB_GESCHAEFTSPARTNER).get(1) + "," 
                    + attribute.get(TAB_GESCHAEFTSPARTNER).get(2) + ", "
                    + attribute.get(TAB_GESCHAEFTSPARTNER).get(3) + ", "
                    + attribute.get(TAB_GESCHAEFTSPARTNER).get(4) + ", "
                    + attribute.get(TAB_GESCHAEFTSPARTNER).get(5) + ") "
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
    /* 17.08.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Ändern der Geschäftspartner in der DB.
     * @param a Geschäftspartnerobjekt
     */
    public void aendernGeschaeftspartner(Geschaeftspartner a) {

        //Variablendeklaration
        PreparedStatement stmt = null;
        String query = "";

        try {
            con.setAutoCommit(false);

            query = "UPDATE ROOT." + ddd.getTabGeschaeftspartner() 
                + " SET " + attribute.get(TAB_GESCHAEFTSPARTNER).get(1) + " = ?"
                + " WHERE " + attribute.get(TAB_GESCHAEFTSPARTNER).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getTyp());
            stmt.setString(2, a.getGeschaeftspartnerID());
            stmt.executeUpdate();

            
            query = "UPDATE ROOT." + ddd.getTabGeschaeftspartner() 
                + " SET " + attribute.get(TAB_GESCHAEFTSPARTNER).get(2) + " = ?"
                + " WHERE " + attribute.get(TAB_GESCHAEFTSPARTNER).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getAdresseID());
            stmt.setString(2, a.getGeschaeftspartnerID());
            stmt.executeUpdate();


            query = "UPDATE ROOT." + ddd.getTabGeschaeftspartner() 
                + " SET " + attribute.get(TAB_GESCHAEFTSPARTNER).get(3) + " = ?"
                + " WHERE " + attribute.get(TAB_GESCHAEFTSPARTNER).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getLieferID());
            stmt.setString(2, a.getGeschaeftspartnerID());
            stmt.executeUpdate();
  

            query = "UPDATE ROOT." + ddd.getTabGeschaeftspartner() 
                + " SET " + attribute.get(TAB_GESCHAEFTSPARTNER).get(4) + " = ?"
                + " WHERE " + attribute.get(TAB_GESCHAEFTSPARTNER).get(0) 
                + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getKreditlimit());
            stmt.setString(2, a.getGeschaeftspartnerID());
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
    /* 17.08.17     BER     Erstellt.     
    /*------------------------------------------------------------------------*/
    
    /**
     * Setzt Löschkennzeichen bei einem ausgewählten Geschäftspartner.
     * @param g Geschäftspartner
     */
    public void setzeLKZ(Geschaeftspartner g) {

        PreparedStatement stmt = null;
        String geschaeftspartnerID = g.getGeschaeftspartnerID();

        try {
            con.setAutoCommit(false);

            String query
                = "UPDATE ROOT." + ddd.getTabGeschaeftspartner() 
                + " SET " + attribute.get(TAB_GESCHAEFTSPARTNER).get(5) + " = ?"
                + " WHERE " + attribute.get(TAB_GESCHAEFTSPARTNER).get(0) 
                + " = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, "J");
            stmt.setString(2, geschaeftspartnerID);

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
    /* 17.08.17     SEZ     Erstellt.     
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt die letzte PartnerID aus der Tabelle aus.
     * @return gibt neue PartnerID aufgezählt.
     */
    public String gibLetztID() {
        Statement stmt = null;
        String value = "";
        ResultSet rs = null;
        String query 
            = "SELECT MAX(" + attribute.get(TAB_GESCHAEFTSPARTNER).get(0) + ") "
            + "FROM ROOT." + ddd.getTabGeschaeftspartner();

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
    /* 17.08.17     SEZ     Erstellt.     
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die letzte PartnerID aus, erhöht sie um 1 und gibt sie wieder.
     * @return neue PartnerID aufgezählt.
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
    /* Datum       Name    Was
    /* 13.09.17    BER     Methode Erstellt.
    /*------------------------------------------------------------------------*/
   
    /**
     * Gibt das Kreditlimit zu einem bestimten GP wieder.
     * @param gpID Geschäftspartner ID
     * @return Gibt Kreditlimit eines GP.
     * @throws java.sql.SQLException SQLFehler
     */
    public String gibKreditlimit(String gpID) throws SQLException {
        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String kreditlimit = "";

        String query = "SELECT " + attribute.get(TAB_GESCHAEFTSPARTNER).get(4) 
            + " FROM ROOT." + ddd.getTabGeschaeftspartner() 
            + " WHERE " + attribute.get(TAB_GESCHAEFTSPARTNER).get(0) + " = ?"
            + " AND " + attribute.get(TAB_GESCHAEFTSPARTNER).get(5) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, gpID);
            stmt.setString(2, "N");
            rs = stmt.executeQuery();
            con.commit();
            
            while (rs.next()) {
                kreditlimit = rs.getString(1);
            }
            
          //Mögliche SQL fehler fangen
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Das Kreditlimit konnte"
                + "nicht abgefragt werden.");
            alert.showAndWait();
            con.rollback();
        }
        return kreditlimit;
    }    

    

    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 13.08.17     BER     Methode erstellt.     
    /*------------------------------------------------------------------------*/
    
    /**
     * Berechnet das Kreditlmit bei einem ausgewählten Geschäftspartner.
     * @param auftragswert Auftragswert der übergeben wird
     * @param gpID Geschäftsparter dessen Kreditlimit berechnet werden soll
     * @throws java.sql.SQLException SQLFehler
     */
    public void setzeKreditlmit(String auftragswert, String gpID) 
        throws SQLException {
        PreparedStatement stmt = null;   

        try {
            con.setAutoCommit(false);

            String query
                = "UPDATE ROOT." + ddd.getTabGeschaeftspartner() 
                + " SET " + attribute.get(TAB_GESCHAEFTSPARTNER).get(4) + " = ?"
                + " WHERE " + attribute.get(TAB_GESCHAEFTSPARTNER).get(0) 
                + " = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, auftragswert);
            stmt.setString(2, gpID);

            stmt.executeUpdate();
            con.commit();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Kreditlimit konnte nicht"
                + " gesetzt werden.");
            alert.showAndWait();
            con.rollback();
        }
    }
    
}
