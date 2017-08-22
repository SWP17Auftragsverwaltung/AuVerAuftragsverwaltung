/*------------------------------------------------------------------------------
* Klasse: ZahlungskonditionenDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse stellt eine Verbindung zur Datenbank her und bearbeitet alles
*   bezüglich Zahlungskonditionen.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 14.08.2017    CEL     Erstellt.
* 20.08.2017    GET     Klasse fertiggestellt 
*-------------------------------------------------------------------------------
 */
package Datenbank;

import Klassen.Zahlungskonditionen;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Chakir, Jakob
 */
public class ZahlungskonditionenDAO extends DataAccess {

    /**
     * Konstruktor.
     *
     * @throws SQLException SQLException
     */
    public ZahlungskonditionenDAO() throws SQLException {

    }

    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 14.08.2017    CEL     Erstellt.
    * 20.08.2017    GET     Querys erstellt und Methode fertiggestellt.
    *
    /*------------------------------------------------------------------------*/
    /**
     * Gibt alle Zahlungskonditionen wieder, die sich in der Datenbank befinden.
     *
     * @return Gibt Arraylist aller Zahlungskonditionen wieder
     */
    public ArrayList<Zahlungskonditionen> gibAlleZahlungskonditionen() {

        //Variablendeklaration
        Statement stmt = null;
        ResultSet rs = null;
        Zahlungskonditionen zahlungskonditionen = null;
        ArrayList<Zahlungskonditionen> zahlungskonditionenListe
                = new ArrayList<>();

        String query = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN";

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
            con.close();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
        return zahlungskonditionenListe;
    }
    
    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 20.08.2017    GET     Querys erstellt und Methode fertiggestellt.
    *
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt alle Adressen ohne Löschkennzeichen wieder.
     *
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     */
    public ArrayList<Zahlungskonditionen> gibAlleZahlungskonditionenOhneLKZ() {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Zahlungskonditionen zahlungskonditionen = null;
        ArrayList<Zahlungskonditionen> 
                zahlungskonditionenListe = new ArrayList<>();

        String query = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE LKZ = ?";

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
        }
        return zahlungskonditionenListe;
    }
    
    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 14.08.2017    CEL     Erstellt.
    * 20.08.2017    GET     Querys erstellt und Methode fertiggestellt.
    *
    /*------------------------------------------------------------------------*/

    /**
     * Gibt alle Adressen mit Löschkennzeichen wieder.
     *
     * @return Gibt ArrayList aller Adressen ohne LKZ wieder.
     */
    public ArrayList<Zahlungskonditionen> gibAlleZahlungskonditionenMitLKZ() {

        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Zahlungskonditionen zahlungskonditionen = null;
        ArrayList<Zahlungskonditionen> zahlungskonditionenListe
                = new ArrayList<>();

        String query = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE LKZ = ?";

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, "J");
            rs = stmt.executeQuery();

            con.commit();
            while (rs.next()) {
                zahlungskonditionen = new Zahlungskonditionen(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12));

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
        }
        return zahlungskonditionenListe;
    }
    
    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 20.08.2017    GET     Querys erstellt und Methode fertiggestellt.
    *
    /*------------------------------------------------------------------------*/

    /**
     * Fügt Zahlungskonditionen der Datenbank hinzu.
     *
     * @param z Zahlungskonditionsobjekt
     */
    public void fuegeZahlungskonditionenHinzu(Zahlungskonditionen z) {

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

            String query = "INSERT INTO ROOT.ZAHLUNGSKONDITIONEN ("
                    + "ZAHLUNGSKONDITIONS_ID, AUFTRAGSART, "
                    + "LIEFERZEIT_SOFORT, SPERRZEIT_WUNSCH,"
                    + " SKONTOZEIT_1, SKONTOZEIT_2, SKONTO_1, "
                    + "SKONTO_2, MAHNZEIT_1, "
                    + "MAHNZEIT_2, MAHNZEIT_3, LKZ)"
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
        }
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum        Name    Was
    /* 17.08.17     GET     Erstellt.    
    /*------------------------------------------------------------------------*/
    
    /**
     * Setzt Löschkennzeichen bei einer ausgewählten Adresse.
     * @return neue ID aufgezählt.
     */ 
    public String gibLetztID() {

        Statement stmt = null;
        String value = "";
        ResultSet rs = null;
        String query = "SELECT MAX(ZAHLUNGSKONDITIONS_ID) FROM "
                + "ROOT.ZAHLUNGSKONDITIONEN";

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
    /* 19.08.17     HEN     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Liest die letzte ID aus, erhöht sie um 1 und gibt sie wieder.
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

   /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 14.08.2017    CEL     Erstellt.
    * 20.08.2017    GET     Querys erstellt und Methode fertiggestellt.
    *
    /*------------------------------------------------------------------------*/
    /**
     * Änderd den Datensatz einer Zahlungskonditione in der Datenbank.
     *
     * @param zk Zahlungskonditionen die geändert werden.
     */
    public void aendereZahlungskonditionen(Zahlungskonditionen zk) {

        PreparedStatement stmt = null;
        String query = "";

        try {

            con.setAutoCommit(false);

            query
                    = "UPDATE ROOT.ZAHLUNGSKONDITIONEN SET AUFTRAGSART = ? "
                    + "WHERE ZAHLUNGSKONDITIONS_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getAuftragsart());
            stmt.setString(2, zk.getZahlungskonditionenID());

            stmt.executeUpdate();
            con.commit();

            query
                    = "UPDATE ROOT.ZAHLUNGSKONDITIONEN SET "
                    + "LIEFERZEIT_SOFORT = ? WHERE ZAHLUNGSKONDITIONS_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getLieferzeitSOFORT());
            stmt.setString(2, zk.getZahlungskonditionenID());

            stmt.executeUpdate();
            con.commit();

            query
                    = "UPDATE ROOT.ZAHLUNGSKONDITIONEN SET "
                    + "SPERRZEIT_WUNSCH = ? WHERE ZAHLUNGSKONDITIONS_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getSperrzeitWUNSCH());
            stmt.setString(2, zk.getZahlungskonditionenID());

            stmt.executeUpdate();
            con.commit();

            query
                    = "UPDATE ROOT.ZAHLUNGSKONDITIONEN SET MAHNZEIT_1 = ? "
                    + "WHERE ZAHLUNGSKONDITIONS_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getMahnzeit1());
            stmt.setString(2, zk.getZahlungskonditionenID());

            stmt.executeUpdate();
            con.commit();

            query
                    = "UPDATE ROOT.ZAHLUNGSKONDITIONEN SET MAHNZEIT_2 = ? "
                    + "WHERE ZAHLUNGSKONDITIONS_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getMahnzeit2());
            stmt.setString(2, zk.getZahlungskonditionenID());

            stmt.executeUpdate();
            con.commit();

            query
                    = "UPDATE ROOT.ZAHLUNGSKONDITIONEN SET MAHNZEIT_3 = ? "
                    + "WHERE ZAHLUNGSKONDITIONS_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getMahnzeit3());
            stmt.setString(2, zk.getZahlungskonditionenID());

            stmt.executeUpdate();
            con.commit();

            query
                    = "UPDATE ROOT.ZAHLUNGSKONDITIONEN SET SKONTOZEIT_1 = ? "
                    + "WHERE ZAHLUNGSKONDITIONS_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getSkontozeit1());
            stmt.setString(2, zk.getZahlungskonditionenID());

            stmt.executeUpdate();
            con.commit();

            query
                    = "UPDATE ROOT.ZAHLUNGSKONDITIONEN SET SKONTO_1 = ? "
                    + "WHERE ZAHLUNGSKONDITIONS_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getSkonto1());
            stmt.setString(2, zk.getZahlungskonditionenID());

            stmt.executeUpdate();
            con.commit();

            query
                    = "UPDATE ROOT.ZAHLUNGSKONDITIONEN SET SKONTOZEIT_2 = ? "
                    + "WHERE ZAHLUNGSKONDITIONS_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getSkontozeit2());
            stmt.setString(2, zk.getZahlungskonditionenID());

            stmt.executeUpdate();
            con.commit();

            query
                    = "UPDATE ROOT.ZAHLUNGSKONDITIONEN SET SKONTO_2 = ? "
                    + "WHERE ZAHLUNGSKONDITIONS_ID = ?";

            stmt = con.prepareStatement(query);
            stmt.setString(1, zk.getSkonto2());
            stmt.setString(2, zk.getZahlungskonditionenID());

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
    
    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 14.08.2017    CEL     Erstellt.
    * 20.08.2017    GET     Querys erstellt und Methode fertiggestellt.
    *
    /*------------------------------------------------------------------------*/

    /**
     * Setzt Löschkennzeichen bei einer ausgewählten Zahlungskonditionen.
     *
     * @param zk Zahlungskonditionen
     */
    public void setzeLKZ(Zahlungskonditionen zk) {

        PreparedStatement stmt = null;
        String zkID = zk.getZahlungskonditionenID();

        try {
            con.setAutoCommit(false);

            String query
                    = "UPDATE ROOT.ZAHLUNGSKONDITIONEN SET LKZ = ? "
                    + "WHERE ZAHLUNGSKONDITIONS_ID = ?";

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
        }
    }

    
//    /*------------------------------------------------------------------------*
//    * Datum         Name    Was
//    * 14.08.2017    CEL     Erstellt.
//    * 20.08.2017    GET     Querys erstellt und Methode fertiggestellt.
//    *
//    /*------------------------------------------------------------------------*/
//    
//    /**
//     *
//     * Sucht mittels eines Suchbegriffs in der Datenbank nach den passenden
//     * Zahlungskonditionen und speichert diese in einer ArrayList ab.
//     *
//     * @param suchkriterium Suchtriterium welches die Suchspalte der DB angibt.
//     * @param suchbegriff ein String nach dem in der Suchspalte gesucht wird.
//     * @return Liefert eine ArrayList mit den zu dem Suchbegriff passenden
//     * Zahlungskonditionen.
//     */
//    public ArrayList<Zahlungskonditionen> zahlungskonditionSuche(
//            String suchkriterium, String suchbegriff) {
//
//        Statement stmt = null;
//        ResultSet rs = null;
//        ArrayList<Zahlungskonditionen> gefundeneZK = new ArrayList<>();
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
//            if (suchkriterium.equals("Konditionen-ID")) {
//
//                String query
//                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
//                        + "ZAHLUNGSKONDITIONS_ID LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//                    Zahlungskonditionen zk = new Zahlungskonditionen(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6),
//                            rs.getString(7), rs.getString(8), rs.getString(9),
//                            rs.getString(10), rs.getString(11),
//                            rs.getString(12));
//                    gefundeneZK.add(zk);
//                }
//                con.commit();
//
//            } else if (suchkriterium.equals("Auftragsart")) {
//
//                String query
//                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
//                        + "AUFTRAGSART LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//                    Zahlungskonditionen zk = new Zahlungskonditionen(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6),
//                            rs.getString(7), rs.getString(8), rs.getString(9),
//                            rs.getString(10), rs.getString(11),
//                            rs.getString(12));
//                    gefundeneZK.add(zk);
//                }
//                con.commit();
//
//            } else if (suchkriterium.equals("LieferzeitSOFORT")) {
//
//                String query
//                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
//                        + "LIEFERZEIT_SOFORT LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//                    Zahlungskonditionen zk = new Zahlungskonditionen(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6),
//                            rs.getString(7), rs.getString(8), rs.getString(9),
//                            rs.getString(10), rs.getString(11),
//                            rs.getString(12));
//                    gefundeneZK.add(zk);
//                }
//                con.commit();
//
//            } else if (suchkriterium.equals("SperrzeitWUNSCH")) {
//
//                String query
//                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
//                        + "SPERRZEIT_WUNSCH LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//                    Zahlungskonditionen zk = new Zahlungskonditionen(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6),
//                            rs.getString(7), rs.getString(8), rs.getString(9),
//                            rs.getString(10), rs.getString(11),
//                            rs.getString(12));
//                    gefundeneZK.add(zk);
//                }
//                con.commit();
//
//            } else if (suchkriterium.equals("Skontozeit 1")) {
//
//                String query
//                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
//                        + "SKONTOZEIT_1 LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//                    Zahlungskonditionen zk = new Zahlungskonditionen(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6),
//                            rs.getString(7), rs.getString(8), rs.getString(9),
//                            rs.getString(10), rs.getString(11),
//                            rs.getString(12));
//                    gefundeneZK.add(zk);
//                }
//                con.commit();
//
//            } else if (suchkriterium.equals("Skonto 1")) {
//
//                String query
//                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
//                        + "SKONTO_1 LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//                    Zahlungskonditionen zk = new Zahlungskonditionen(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6),
//                            rs.getString(7), rs.getString(8), rs.getString(9),
//                            rs.getString(10), rs.getString(11),
//                            rs.getString(12));
//                    gefundeneZK.add(zk);
//                }
//                con.commit();
//
//            } else if (suchkriterium.equals("Skontozeit 2")) {
//
//                String query
//                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
//                        + "SKONTOZEIT_2 LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//                    Zahlungskonditionen zk = new Zahlungskonditionen(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6),
//                            rs.getString(7), rs.getString(8), rs.getString(9),
//                            rs.getString(10), rs.getString(11),
//                            rs.getString(12));
//                    gefundeneZK.add(zk);
//                }
//                con.commit();
//
//            } else if (suchkriterium.equals("Skonto 2")) {
//
//                String query
//                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
//                        + "SKONTO_2 LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//                    Zahlungskonditionen zk = new Zahlungskonditionen(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6),
//                            rs.getString(7), rs.getString(8), rs.getString(9),
//                            rs.getString(10), rs.getString(11),
//                            rs.getString(12));
//                    gefundeneZK.add(zk);
//                }
//                con.commit();
//            } else if (suchkriterium.equals("Mahnzeit 1")) {
//
//                String query
//                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
//                        + "MAHNZEIT_1 LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//                    Zahlungskonditionen zk = new Zahlungskonditionen(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6),
//                            rs.getString(7), rs.getString(8), rs.getString(9),
//                            rs.getString(10), rs.getString(11),
//                            rs.getString(12));
//                    gefundeneZK.add(zk);
//                }
//                con.commit();
//
//            } else if (suchkriterium.equals("Mahnzeit 2")) {
//
//                String query
//                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
//                        + "MAHNZEIT_2 LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//                    Zahlungskonditionen zk = new Zahlungskonditionen(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6),
//                            rs.getString(7), rs.getString(8), rs.getString(9),
//                            rs.getString(10), rs.getString(11),
//                            rs.getString(12));
//                    gefundeneZK.add(zk);
//                }
//                con.commit();
//
//            } else if (suchkriterium.equals("Mahnzeit 3")) {
//
//                String query
//                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
//                        + "MAHNZEIT_3 LIKE '"
//                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
//
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(query);
//
//                while (rs.next()) {
//                    Zahlungskonditionen zk = new Zahlungskonditionen(
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6),
//                            rs.getString(7), rs.getString(8), rs.getString(9),
//                            rs.getString(10), rs.getString(11),
//                            rs.getString(12));
//                    gefundeneZK.add(zk);
//                }
//                con.commit();
//
//            }
//
//        } catch (SQLException e) {
//
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.initStyle(StageStyle.UTILITY);
//            alert.setTitle("Fehler");
//            alert.setHeaderText(e.getMessage());
//            alert.showAndWait();
//
//        }
//
//        return gefundeneZK;
//    }

}
