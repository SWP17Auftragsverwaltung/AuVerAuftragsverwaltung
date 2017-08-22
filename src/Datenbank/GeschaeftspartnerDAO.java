/*------------------------------------------------------------------------------
* Klasse: GeschäftspartnerDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse stellt eine Verbindung zur Datenbank her und bearbeitet alles
*   bezüglich Geschäftspartner.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 14.08.2017    CEL     Erstellt.
*-------------------------------------------------------------------------------
 */
package Datenbank;

import Klassen.Geschaeftspartner;
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
public class GeschaeftspartnerDAO extends DataAccess {

    /**
     * Konstruktor.
     *
     * @throws SQLException SQLException
     */
    public GeschaeftspartnerDAO() throws SQLException {

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
    public ArrayList<Geschaeftspartner> gibAlleGeschaeftspartner() {

        //Variablendeklaration.
        Statement stmt = null;
        ResultSet rs = null;
        Geschaeftspartner geschaeftspartner = null;
        ArrayList<Geschaeftspartner> geschaeftspartnerListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT.Geschaeftspartner";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

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
     *
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     */
    public ArrayList<Geschaeftspartner> gibAlleGeschaeftspartnerOhneLKZ() {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Geschaeftspartner geschaeftspartner = null;
        ArrayList<Geschaeftspartner> geschaeftspartnerListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT.GESCHAEFTSPARTNER WHERE LKZ = ?";

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
    /* 15.08.17    CEL     Erstellt und getestet.
    /*------------------------------------------------------------------------*/
    /**
     * Gibt alle Geschaeftspartner ohne Löschkennzeichen wieder.
     *
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     */
    public ArrayList<Geschaeftspartner> gibAlleGeschaeftspartnerMitLKZ() {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Geschaeftspartner geschaeftspartner = null;
        ArrayList<Geschaeftspartner> geschaeftspartnerListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT.GESCHAEFTSPARTNER WHERE LKZ = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "J");
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

            String query = "INSERT INTO ROOT.GESCHAEFTSPARTNER "
                    + "(GESCHAEFTSPARTNER_ID, TYP, ANSCHRIFT_ID, "
                    + "LIEFER_ID, KREDITLIMIT, LKZ)"
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
     *
     * @param a Geschäftspartnerobjekt
     */
    public void aendernGeschaeftspartner(Geschaeftspartner a) {

        //Variablendeklaration
        PreparedStatement stmt = null;
        String query
                = "";

        try {
            con.setAutoCommit(false);

            query = "UPDATE ROOT.GESCHAEFTSPARTNER SET TYP = ? "
                    + "WHERE GESCHAEFTSPARTNER_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getTyp());
            stmt.setString(2, a.getGeschaeftspartnerID());

            stmt.executeUpdate();
            con.commit();

            query = "UPDATE ROOT.GESCHAEFTSPARTNER SET ANSCHRIFT_ID = ? "
                    + "WHERE GESCHAEFTSPARTNER_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getAdresseID());
            stmt.setString(2, a.getGeschaeftspartnerID());

            stmt.executeUpdate();
            con.commit();

            query = "UPDATE ROOT.GESCHAEFTSPARTNER SET LIEFER_ID = ? "
                    + "WHERE GESCHAEFTSPARTNER_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getLieferID());
            stmt.setString(2, a.getGeschaeftspartnerID());

            stmt.executeUpdate();
            con.commit();

            query = "UPDATE ROOT.GESCHAEFTSPARTNER SET KREDITLIMIT = ? "
                    + "WHERE GESCHAEFTSPARTNER_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, a.getKreditlimit());
            stmt.setString(2, a.getGeschaeftspartnerID());

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
     * Setzt Löschkennzeichen bei einer ausgewählten .
     *
     * @param g Geschäftspartner
     */
    public void setzeLKZ(Geschaeftspartner g) {

        PreparedStatement stmt = null;
        String geschaeftspartnerID = g.getGeschaeftspartnerID();

        try {
            con.setAutoCommit(false);

            String query
                    = "UPDATE ROOT.GESCHAEFTSPARTNER SET LKZ = ? "
                    + "WHERE GESCHAEFTSPARTNER_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, "J");
            stmt.setString(2, geschaeftspartnerID);

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

    /**
     * Setzt Löschkennzeichen bei einer ausgewählten Adresse.
     *
     * @return neue ID aufgezählt.
     */
    public String gibLetztID() {
        Statement stmt = null;
        String value = "";
        ResultSet rs = null;
        String query = "SELECT MAX(GESCHAEFTSPARTNER_ID) "
                + "FROM ROOT.GESCHAEFTSPARTNER";

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

    /**
     * Liest die letzte ID aus, erhöht sie um 1 und gibt sie wieder.
     *
     * @return neue ID aufgezählt.
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
//    /**
//     * 
//     * @param suchkriterium
//     * @param suchbegriff
//     * @return 
//     */
//    public ArrayList geschaeftspartnerSuche(String suchkriterium,
//            String suchbegriff) {
//
//        Statement stmt = null;
//        ResultSet rs = null;
//        ArrayList<Geschaeftspartner> gefundeneGP = new ArrayList<>();
//        StringBuilder neuerSuchbegriff = new StringBuilder(suchbegriff);
//
//        for (int i = 0; i < suchbegriff.length(); i++) {
//            if (suchbegriff.charAt(i) == '*') {
//                neuerSuchbegriff.setCharAt(i, '%');
//            } else if (suchbegriff.charAt(i) == '?') {
//                neuerSuchbegriff.setCharAt(i, '_');
//            }
//        }
//
//        try {
//
//            if (suchkriterium.equals("Geschaeftspartner-ID")) {
//                String query
//                        = "SELECT * FROM ROOT.GESCHAEFTSPARTNER "
//                        + "WHERE GESCHAEFTSPARTNER_ID LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//
//                    Geschaeftspartner gp = new Geschaeftspartner(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6));
//
//                    gefundeneGP.add(gp);
//                }
//                con.commit();
//
//            } else if (suchkriterium.equals("Geschäftspartner-Typ")) {
//                String query
//                        = "SELECT * FROM ROOT.GESCHAEFTSPARTNER "
//                        + "WHERE TYP LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//
//                    Geschaeftspartner gp = new Geschaeftspartner(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6));
//
//                    gefundeneGP.add(gp);
//                }
//                con.commit();
//            } else if (suchkriterium.equals("Anschrift-ID")) {
//                String query
//                        = "SELECT * FROM ROOT.GESCHAEFTSPARTNER "
//                        + "WHERE ANSCHRIFT_ID LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//
//                    Geschaeftspartner gp = new Geschaeftspartner(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6));
//
//                    gefundeneGP.add(gp);
//                }
//                con.commit();
//            } else if (suchkriterium.equals("Liefer-ID")) {
//                String query
//                        = "SELECT * FROM ROOT.GESCHAEFTSPARTNER "
//                        + "WHERE LIEFER_ID LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//
//                    Geschaeftspartner gp = new Geschaeftspartner(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6));
//
//                    gefundeneGP.add(gp);
//                }
//                con.commit();
//            } else if (suchkriterium.equals("Kreditlimit")) {
//                String query
//                        = "SELECT * FROM ROOT.GESCHAEFTSPARTNER "
//                        + "WHERE KREDITLIMIT LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//
//                    Geschaeftspartner gp = new Geschaeftspartner(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6));
//
//                    gefundeneGP.add(gp);
//                }
//                con.commit();
//            }
//
//        } catch (SQLException e) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.initStyle(StageStyle.UTILITY);
//            alert.setTitle("Fehler");
//            alert.setHeaderText(e.getMessage());
//            alert.showAndWait();
//        }
//        
//        return gefundeneGP;
//        
//    }

}
