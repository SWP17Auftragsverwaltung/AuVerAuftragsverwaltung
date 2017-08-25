/*------------------------------------------------------------------------------
* Klasse: AdresseDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse bearbeitet Queries bezüglich Adressen.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 07.08.2017    HEN     Erstellt.
* 12.08.2017    HEN     DB Verbindung ausgelagert.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Adresse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * @author Andre
 */
public class AdresseDAO extends DataAccess {

    /**
     * Erzeugt ein neues DataDictionaryDAO Objekt.
     */
    private DataDictionaryDAO ddd = new DataDictionaryDAO();
    
    /**
     * 
     */
    private String TAB_ADRESSE = ddd.getTAB_ADRESSE();
    
    /**
     * 
     */
    private HashMap<String, ArrayList> attribute; 
    
    /**
     * Konstruktor.
     *
     * @throws SQLException SQLException
     */
    public AdresseDAO() throws SQLException {
        attribute = ddd.getTabellenAttribute();
        ddd.holeAlleAttribute(TAB_ADRESSE);
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
    public ArrayList<Adresse> gibAlleAdressen() throws SQLException {

        //Variablendeklaration
        Statement stmt = null;
        ResultSet rs = null;
        Adresse adresse = null;
        ArrayList<Adresse> adressListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT." + ddd.getTabAdresse();

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
            con.rollback();
        }
        return adressListe;
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 07.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Adressen ohne Löschkennzeichen wieder.
     *
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     * @throws java.sql.SQLException SQLException.
     */
    public ArrayList<Adresse> gibAlleAdressenOhneLKZ() throws SQLException {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Adresse adresse = null;
        ArrayList<Adresse> adressListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT." 
            + ddd.getTabAdresse() + " WHERE " 
            + attribute.get(TAB_ADRESSE).get(12) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "N");
            rs = stmt.executeQuery();

            con.commit();
            while (rs.next()) {
                adresse = new Adresse(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13));

                adressListe.add(adresse);
            }
            //Fehler werfen wenn Rückgabeobjekt leer ist
            if (adressListe.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText("Keine Adressen gefunden!");
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
        return adressListe;
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Adressen mit Löschkennzeichen wieder.
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     * @throws java.sql.SQLException SQLException.
     */
    public ArrayList<Adresse> gibAlleAdressenMitLKZ() throws SQLException {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Adresse adresse = null;
        ArrayList<Adresse> adressListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT." + ddd.getTabAdresse() 
                + " WHERE " + attribute.get(TAB_ADRESSE).get(12) + " = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "J");
            rs = stmt.executeQuery();

            con.commit();
            while (rs.next()) {
                adresse = new Adresse(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13));

                adressListe.add(adresse);
            }
            //Fehler werfen wenn Rückgabeobjekt leer ist
            if (adressListe.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText("Keine Adressen gefunden!");
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
        return adressListe;
    }

    

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Fügt Adresse der Datenbank hinzu.
     * @param a Adressobjekt
     * @throws java.sql.SQLException SQLException.
     */
    public void fuegeAdresseHinzu(Adresse a) throws SQLException {
        //Variablendeklaration
        PreparedStatement stmt = null;
        String anschriftID = generiereID();
        String anrede = a.getAnrede();
        String name = a.getName();
        String vorname = a.getVorname();
        String strasse = a.getStrasse();
        String hausnr = a.getHausnummer();
        String plz = a.getPlz();
        String ort = a.getOrt();
        String staat = a.getStaat();
        String tel = a.getTelefon();
        String email = a.getEmail();
        String erfdatum = a.getErfassungsdatum();
        String lkz = a.getLkz();
               
        try {
            con.setAutoCommit(false);

            String query = "INSERT INTO ROOT." + ddd.getTabAdresse()
                    + "(" + attribute.get(TAB_ADRESSE).get(0) + ", " 
                    +  attribute.get(TAB_ADRESSE).get(1) + ", " 
                    +  attribute.get(TAB_ADRESSE).get(2) + ", " 
                    +  attribute.get(TAB_ADRESSE).get(3) + ", " 
                    +  attribute.get(TAB_ADRESSE).get(4) + ", "
                    +  attribute.get(TAB_ADRESSE).get(5) + ", "
                    +  attribute.get(TAB_ADRESSE).get(6) + ", "
                    +  attribute.get(TAB_ADRESSE).get(7) + ", "
                    +  attribute.get(TAB_ADRESSE).get(8) + ", "
                    +  attribute.get(TAB_ADRESSE).get(9) + ", "
                    +  attribute.get(TAB_ADRESSE).get(10) + ", "
                    +  attribute.get(TAB_ADRESSE).get(11) + ", "
                    +  attribute.get(TAB_ADRESSE).get(12) + ") "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            stmt = con.prepareStatement(query);
            stmt.setString(1, anschriftID);
            stmt.setString(2, anrede);
            stmt.setString(3, name);
            stmt.setString(4, vorname);
            stmt.setString(5, strasse);
            stmt.setString(6, hausnr);
            stmt.setString(7, plz);
            stmt.setString(8, ort);
            stmt.setString(9, staat);
            stmt.setString(10, tel);
            stmt.setString(11, email);
            stmt.setString(12, erfdatum);
            stmt.setString(13, lkz);

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
    /* 15.08.17    GET     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Ändern die Adresse in der DB.
     * @param a Adressobjekt
     * @throws java.sql.SQLException SQLException
     */
    public void aendereAdresse(Adresse a) throws SQLException {
        PreparedStatement stmt = null;
        String query = "";

        try {
            con.setAutoCommit(false);

            query
                = "UPDATE ROOT." + ddd.getTabAdresse() 
                + " SET " + attribute.get(TAB_ADRESSE).get(1) + " = ? WHERE " 
                + attribute.get(TAB_ADRESSE).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getAnrede());
            stmt.setString(2, a.getAdresseID());
            stmt.executeUpdate();

            
            query
                = "UPDATE ROOT." + ddd.getTabAdresse() 
                + " SET " + attribute.get(TAB_ADRESSE).get(2) + " = ? WHERE " 
                + attribute.get(TAB_ADRESSE).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getName());
            stmt.setString(2, a.getAdresseID());
            stmt.executeUpdate();

            
            query
                = "UPDATE ROOT." + ddd.getTabAdresse() 
                + " SET " + attribute.get(TAB_ADRESSE).get(3) + " = ? WHERE " 
                + attribute.get(TAB_ADRESSE).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getVorname());
            stmt.setString(2, a.getAdresseID());
            stmt.executeUpdate();

            
            query
                = "UPDATE ROOT." + ddd.getTabAdresse() 
                + " SET " + attribute.get(TAB_ADRESSE).get(4) + " = ? WHERE " 
                + attribute.get(TAB_ADRESSE).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getStrasse());
            stmt.setString(2, a.getAdresseID());
            stmt.executeUpdate();

            
            query
                = "UPDATE ROOT." + ddd.getTabAdresse() 
                + " SET " + attribute.get(TAB_ADRESSE).get(5) + " = ? WHERE " 
                + attribute.get(TAB_ADRESSE).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getHausnummer());
            stmt.setString(2, a.getAdresseID());
            stmt.executeUpdate();

            
            query
                = "UPDATE ROOT." + ddd.getTabAdresse() 
                + " SET " + attribute.get(TAB_ADRESSE).get(6) + " = ? WHERE " 
                + attribute.get(TAB_ADRESSE).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getPlz());
            stmt.setString(2, a.getAdresseID());
            stmt.executeUpdate();

            
            query
                = "UPDATE ROOT." + ddd.getTabAdresse() 
                + " SET " + attribute.get(TAB_ADRESSE).get(7) + " = ? WHERE " 
                + attribute.get(TAB_ADRESSE).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getOrt());
            stmt.setString(2, a.getAdresseID());
            stmt.executeUpdate();

            
            query
                = "UPDATE ROOT." + ddd.getTabAdresse() 
                + " SET " + attribute.get(TAB_ADRESSE).get(8) + " = ? WHERE " 
                + attribute.get(TAB_ADRESSE).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getStaat());
            stmt.setString(2, a.getAdresseID());
            stmt.executeUpdate();

            
            query
                = "UPDATE ROOT." + ddd.getTabAdresse() 
                + " SET " + attribute.get(TAB_ADRESSE).get(9) + " = ? WHERE " 
                + attribute.get(TAB_ADRESSE).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getTelefon());
            stmt.setString(2, a.getAdresseID());
            stmt.executeUpdate();

            
            query
                = "UPDATE ROOT." + ddd.getTabAdresse() 
                + " SET " + attribute.get(TAB_ADRESSE).get(10) + " = ? WHERE " 
                + attribute.get(TAB_ADRESSE).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getEmail());
            stmt.setString(2, a.getAdresseID());
            stmt.executeUpdate();

            
            query
                = "UPDATE ROOT." + ddd.getTabAdresse() 
                + " SET " + attribute.get(TAB_ADRESSE).get(11) + " = ? WHERE " 
                + attribute.get(TAB_ADRESSE).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getErfassungsdatum());
            stmt.setString(2, a.getAdresseID());
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
    /* Datum        Name    Was
    /* 15.08.17     GET     Erstellt.
    /* 15.08.17     HEN     preparedStmt ergänzt, positiv getestet.     
    /*------------------------------------------------------------------------*/
    
    /**
     * Setzt Löschkennzeichen bei einer ausgewählten Adresse.
     * @param a Adresse
     * @throws java.sql.SQLException SQLException
     */
    public void setzeLKZ(Adresse a) throws SQLException {
        PreparedStatement stmt = null;
        String anschriftID = a.getAdresseID();

        try {
            con.setAutoCommit(false);

            String query
                = "UPDATE ROOT." + ddd.getTabAdresse() 
                + " SET " + attribute.get(TAB_ADRESSE).get(12) + " = ?"
                + " WHERE " + attribute.get(TAB_ADRESSE).get(0) + " = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, "J");
            stmt.setString(2, anschriftID);
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
    /* 17.08.17     GET     Erstellt.    
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
            + attribute.get(TAB_ADRESSE).get(0) + ") FROM ROOT." 
            + ddd.getTabAdresse();
        
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
    
    

}
