/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datenbank;

import Klassen.Auftragskopf;
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
public class AuftragskopfDAO extends DataAccess {
    
        /**
     * Konstruktor.
     *
     * @throws SQLException SQLException
     */
    public AuftragskopfDAO() throws SQLException {

    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 07.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Adressen wieder die sich in der Datenbank befinden.
     * @return Gibt Arraylist aller Adressen wieder
     */
    public ArrayList<Auftragskopf> gibAlleAuftragskoepfe() {

        //Variablendeklaration
        Statement stmt = null;
        ResultSet rs = null;
        Auftragskopf auftragskopf = null;
        ArrayList<Auftragskopf> auftragskopfListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT.Auftragskopf";

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
            con.close();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
        return auftragskopfListe;
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 07.08.17    Hen     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Adressen ohne Löschkennzeichen wieder.
     *
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     */
    public ArrayList<Auftragskopf> gibAlleAuftragskoepfeOhneLKZ() {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Auftragskopf auftragskopf = null;
        ArrayList<Auftragskopf> auftragskopfListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT.AUFTRAGSKOPF WHERE LKZ = ?";

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
     */
    public ArrayList<Auftragskopf> gibAlleAuftragskoepfeMitLKZ() {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Auftragskopf auftragskopf = null;
        ArrayList<Auftragskopf> auftragskopfListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT.AUFTRAGSKOPF WHERE LKZ = ?";

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
        }
        return auftragskopfListe;
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
    public void setzeLKZ(Auftragskopf a) {

        PreparedStatement stmt = null;
        String auftragskopfID = a.getAuftragskopfID();

        try {
            con.setAutoCommit(false);

            String query
                    = "UPDATE ROOT.AUFTRAGSKOPF SET LKZ = ? WHERE AUFTRAGSKOPF_ID = ?";

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
        }

    }
}
