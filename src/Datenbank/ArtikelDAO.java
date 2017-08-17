/*------------------------------------------------------------------------------
* Klasse: ArtikelDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse bearbeitet Queries bezüglich Artikel.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 14.08.2017    BER     Erstellt.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Artikel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
/**
 *
 * @author Tobi
 */
public class ArtikelDAO extends DataAccess {

    /**
     * Konstruktor.
     * @throws SQLException SQLException. 
     */
    public ArtikelDAO() throws SQLException {
        
    }
        
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.08.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
  
    /**
     * Gibt alle Adressen wieder die sich in der Datenbank befinden.
     * @return Gibt Arraylist aller Adressen wieder
    */
    public ArrayList<Artikel> gibAlleArtikel() {
        
        //Variablendeklaration
        Statement stmt = null;
        ResultSet rs = null;
        Artikel artikel = null;
        ArrayList<Artikel> artikelListe = new ArrayList<>();
        
        String query = "SELECT * FROM ROOT.Artikel";    
        
        try {
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
    public ArrayList<Artikel> gibAlleArtikelOhneLKZ() {
        
        //Variablendeklaration
        PreparedStatement  stmt = null;
        ResultSet rs = null;
        Artikel artikel = null;  
        ArrayList<Artikel> artikelListe = new ArrayList<>();
        
        String query = "SELECT * FROM ROOT.ARTIKEL WHERE LKZ = ?";

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
        
        String query = "SELECT * FROM ROOT.ARTIKEL WHERE LKZ = ?";

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
    /* 15.08.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
    
        /**
     * Fügt Artikel der Datenbank hinzu
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
        
        try{
            con.setAutoCommit(false);
            
            String query = "INSERT INTO ROOT.ARTIKEL (Artikel_ID, Artikeltext, "
                    + "Bestelltext, Einzelwert, Bestellwert, MWST_Satz, "
                    + "Bestandsmenge_FREI, Bestandsmenge_Reserviert, "
                    + "Bestandsmenge_Zulauf, Bestandsmenge_Verkauft, LKZ)"
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
     * Ändern der Artikel in der DB.
     * @param a Artikelobjekt
    */
        public void aendernArtikel(Artikel a) {
        
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
        
        try{
            con.setAutoCommit(false);
            
            String query = "INSERT INTO ROOT.ARTIKEL (Artikel_ID, Artikeltext, "
                    + "Bestelltext, Einzelwert, Bestellwert, MWST_Satz, "
                    + "Bestandsmenge_FREI, Bestandsmenge_Reserviert, "
                    + "Bestandsmenge_Zulauf, Bestandsmenge_Verkauft, LKZ)"
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
     * Setzt Löschkennzeichen bei einer ausgewählten Artikel.
     * @param a Artikel
     */
    public void setzeLKZ(Artikel a) {

        PreparedStatement stmt = null;
        String artikelID = a.getArtikelID();


        try {
            con.setAutoCommit(false);

            String query 
                    = "UPDATE ROOT.ARTIKEL SET LKZ = ? WHERE ARTIKEL_ID = ?";
            
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
}
