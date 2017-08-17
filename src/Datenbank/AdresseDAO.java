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
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * @author Andre
 */
public class AdresseDAO extends DataAccess {

    /**
     * Konstruktor.
     * @throws SQLException SQLException 
     */
    public AdresseDAO() throws SQLException {
        
    }
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 07.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
  
    /**
     * Gibt alle Adressen wieder die sich in der Datenbank befinden.
     * @return Gibt Arraylist aller Adressen wieder
    */
    public ArrayList<Adresse> gibAlleAdressen() {
        
        //Variablendeklaration
        Statement stmt = null;
        ResultSet rs = null;
        Adresse adresse = null;  
        ArrayList<Adresse> adressListe = new ArrayList<>();
        
        String query = "SELECT * FROM ROOT.Adresse";    
        
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
        }
        return adressListe;
    } 
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 07.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Adressen ohne Löschkennzeichen wieder.
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     */
    public ArrayList<Adresse> gibAlleAdressenOhneLKZ() {
        
        //Variablendeklaration
        PreparedStatement  stmt = null;
        ResultSet rs = null;
        Adresse adresse = null;  
        ArrayList<Adresse> adressListe = new ArrayList<>();
        
        String query = "SELECT * FROM ROOT.ADRESSE WHERE LKZ = ?";

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
     */
    public ArrayList<Adresse> gibAlleAdressenMitLKZ() {
        
        //Variablendeklaration
        PreparedStatement  stmt = null;
        ResultSet rs = null;
        Adresse adresse = null;  
        ArrayList<Adresse> adressListe = new ArrayList<>();
        
        String query = "SELECT * FROM ROOT.ADRESSE WHERE LKZ = ?";

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
    */
    public void fuegeAdresseHinzu(Adresse a) {
        
        //Variablendeklaration
        PreparedStatement  stmt = null;
        String anschriftID = a.getAdresseID();
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

            String query = "INSERT INTO ROOT.ADRESSE (Anschrift_ID, Anrede, "
                + "Name, Vorname, Strasse, Hausnummer, PLZ, Ort, Staat, "
                + "Telefon, E_Mail, Erfassungsdatum, LKZ)"
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
    /* 15.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Ändern die Adresse in der DB.
     * @param a Adressobjekt
    */
    public void aendereAdresse(Adresse a) {
        
        
        PreparedStatement stmt = null;
        
        
        try{
        con.setAutoCommit(false);

            String query 
                    = "UPDATE ROOT.ADRESSE SET ANREDE = ? WHERE ANSCHRIFT_ID = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getAnrede());
            stmt.setString(2, a.getAdresseID());

            stmt.executeUpdate();
            con.commit();
            
            query = "UPDATE ROOT.ADRESSE SET ANREDE = ? WHERE ANSCHRIFT_ID = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getAnrede());
            stmt.setString(2, a.getAdresseID());

            stmt.executeUpdate();
            con.commit();
            
            query = "UPDATE ROOT.ADRESSE SET NAME = ? WHERE ANSCHRIFT_ID = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getName());
            stmt.setString(2, a.getAdresseID());

            stmt.executeUpdate();
            con.commit();
            
            query = "UPDATE ROOT.ADRESSE SET VORNAME = ? WHERE ANSCHRIFT_ID = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getVorname());
            stmt.setString(2, a.getAdresseID());

            stmt.executeUpdate();
            con.commit();
            
            query = "UPDATE ROOT.ADRESSE SET STRASSE = ? WHERE ANSCHRIFT_ID = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getStrasse());
            stmt.setString(2, a.getAdresseID());

            stmt.executeUpdate();
            con.commit();
            
            query = "UPDATE ROOT.ADRESSE SET HAUSNUMMER = ? WHERE ANSCHRIFT_ID = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getHausnummer());
            stmt.setString(2, a.getAdresseID());

            stmt.executeUpdate();
            con.commit();
            
            query = "UPDATE ROOT.ADRESSE SET PLZ = ? WHERE ANSCHRIFT_ID = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getPlz());
            stmt.setString(2, a.getAdresseID());

            stmt.executeUpdate();
            con.commit();
            
            query = "UPDATE ROOT.ADRESSE SET ORT = ? WHERE ANSCHRIFT_ID = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getOrt());
            stmt.setString(2, a.getAdresseID());

            stmt.executeUpdate();
            con.commit();
            
            query = "UPDATE ROOT.ADRESSE SET STAAT = ? WHERE ANSCHRIFT_ID = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getStaat());
            stmt.setString(2, a.getAdresseID());

            stmt.executeUpdate();
            con.commit();
            
            query = "UPDATE ROOT.ADRESSE SET TELEFON = ? WHERE ANSCHRIFT_ID = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getTelefon());
            stmt.setString(2, a.getAdresseID());

            stmt.executeUpdate();
            con.commit();
            
            query = "UPDATE ROOT.ADRESSE SET E_MAIL = ? WHERE ANSCHRIFT_ID = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getEmail());
            stmt.setString(2, a.getAdresseID());

            stmt.executeUpdate();
            con.commit();
            
            query = "UPDATE ROOT.ADRESSE SET ERFASSUNGSDATUM = ? WHERE ANSCHRIFT_ID = ?";
            
            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getErfassungsdatum());
            stmt.setString(2, a.getAdresseID());

            stmt.executeUpdate();
            con.commit();
        
        
        //Variablendeklaration
//        PreparedStatement  stmt = null;
//        String anschriftID = a.getAdresseID();
//        String anrede = a.getAnrede();
//        String name = a.getName();
//        String vorname = a.getVorname();
//        String strasse = a.getStrasse();
//        String hausnr = a.getHausnummer();
//        String plz = a.getPlz();
//        String ort = a.getOrt();
//        String staat = a.getStaat();
//        String tel = a.getTelefon();
//        String email = a.getEmail();
//        String erfdatum = a.getErfassungsdatum();
//        String lkz = a.getLkz();
        
       
//            con.setAutoCommit(false);
//
//            String query = "UPDATE ROOT.ADRESSE SET (Anschrift_ID, Anrede, "
//                + "Name, Vorname, Strasse, Hausnummer, PLZ, Ort, Staat, "
//                + "Telefon, E_Mail, Erfassungsdatum, LKZ)"
//                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
//                
//            stmt = con.prepareStatement(query);
//            stmt.setString(1, anschriftID);
//            stmt.setString(2, anrede);
//            stmt.setString(3, name);
//            stmt.setString(4, vorname);
//            stmt.setString(5, strasse);
//            stmt.setString(6, hausnr);
//            stmt.setString(7, plz);
//            stmt.setString(8, ort);
//            stmt.setString(9, staat);
//            stmt.setString(10, tel);
//            stmt.setString(11, email);
//            stmt.setString(12, erfdatum);
//            stmt.setString(13, lkz);
//            
//            stmt.executeUpdate();
//            con.commit();

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
    /* 15.08.17     GET     Erstellt.
    /* 15.08.17     HEN     preparedStmt ergänzt, positiv getestet.     
    /*------------------------------------------------------------------------*/
        
    /**
     * Setzt Löschkennzeichen bei einer ausgewählten Adresse.
     * @param a Adresse
     */
    public void setzeLKZ(Adresse a) {

        PreparedStatement stmt = null;
        String anschriftID = a.getAdresseID();


        try {
            con.setAutoCommit(false);

            String query 
                    = "UPDATE ROOT.ADRESSE SET LKZ = ? WHERE ANSCHRIFT_ID = ?";
            
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
        }

    }

}
