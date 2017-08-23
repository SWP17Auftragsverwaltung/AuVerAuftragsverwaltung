/*------------------------------------------------------------------------------
* Klasse: SucheeDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse verwaltet alle Suchfelder.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 17.08.2017    BER     Erstellt.
*-------------------------------------------------------------------------------
 */
package Datenbank;

import Klassen.Adresse;
import Klassen.Artikel;
import Klassen.Geschaeftspartner;
import Klassen.Zahlungskonditionen;
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
public class SucheDAO extends DataAccess {

    /**
     * Konstruktor.
     *
     * @throws SQLException SQLException
     */
    public SucheDAO() throws SQLException {

    }
    
    
    
    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 14.08.2017    CEL     Erstellt.
    * 20.08.2017    BER     Querys erstellt und Methode fertiggestellt.
    *
    /*------------------------------------------------------------------------*/

    /**
     *
     * Sucht mittels eines Suchbegriffs in der Datenbank nach den passenden
     * Adressen und speichert diese in einer ArrayList ab.
     *
     * @param suchkriterium Suchtriterium welches die Suchspalte der DB angibt.
     * @param suchbegriff ein String nach dem in der Suchspalte gesucht wird.
     * @return Liefert eine ArrayList mit den zu dem Suchbegriff passenden
     * Adressen.
     * @throws java.sql.SQLException SQLException
     */
    public ArrayList<Adresse> adressSuche(
            String suchkriterium, String suchbegriff) throws SQLException {

        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Adresse> gefundeneAdressen = new ArrayList<>();
        StringBuilder neuerSuchbegriff = new StringBuilder(suchbegriff);
    
        for (int i = 0; i < suchbegriff.length(); i++) {
            if (suchbegriff.charAt(i) == '*') {
                neuerSuchbegriff.setCharAt(i, '%');
            } else if (suchbegriff.charAt(i) == '?') {
                neuerSuchbegriff.setCharAt(i, '_');
            }
        }

        try {
            if (suchkriterium.equals("AnschriftID")) {
                String query
                        = "SELECT * FROM ROOT.ADRESSE WHERE ANSCHRIFT_ID LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Adresse adresse = new Adresse(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11), rs.getString(12),
                            rs.getString(13));
                    gefundeneAdressen.add(adresse);
                }

            } else if (suchkriterium.equals("Anrede")) {
                String query
                        = "SELECT * FROM ROOT.ADRESSE WHERE ANREDE LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Adresse adresse = new Adresse(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11), rs.getString(12),
                            rs.getString(13));
                    gefundeneAdressen.add(adresse);
                }

            } else if (suchkriterium.equals("Name")) {
                String query
                        = "SELECT * FROM ROOT.ADRESSE WHERE NAME LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Adresse adresse = new Adresse(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11), rs.getString(12),
                            rs.getString(13));
                    gefundeneAdressen.add(adresse);
                }

            } else if (suchkriterium.equals("Vorname")) {
                String query
                        = "SELECT * FROM ROOT.ADRESSE WHERE VORNAME LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Adresse adresse = new Adresse(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11), rs.getString(12),
                            rs.getString(13));
                    gefundeneAdressen.add(adresse);
                }

            } else if (suchkriterium.equals("Straße")) {
                String query
                        = "SELECT * FROM ROOT.ADRESSE WHERE STRASSE LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Adresse adresse = new Adresse(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11), rs.getString(12),
                            rs.getString(13));
                    gefundeneAdressen.add(adresse);
                }

            } else if (suchkriterium.equals("HausNr")) {
                String query
                        = "SELECT * FROM ROOT.ADRESSE WHERE HAUSNUMMER LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Adresse adresse = new Adresse(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11), rs.getString(12),
                            rs.getString(13));
                    gefundeneAdressen.add(adresse);
                }

            } else if (suchkriterium.equals("PLZ")) {
                String query
                        = "SELECT * FROM ROOT.ADRESSE WHERE PLZ LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Adresse adresse = new Adresse(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11), rs.getString(12),
                            rs.getString(13));
                    gefundeneAdressen.add(adresse);
                }

            } else if (suchkriterium.equals("Ort")) {
                String query
                        = "SELECT * FROM ROOT.ADRESSE WHERE ORT LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Adresse adresse = new Adresse(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11), rs.getString(12),
                            rs.getString(13));
                    gefundeneAdressen.add(adresse);
                }

            } else if (suchkriterium.equals("Staat")) {
                String query
                        = "SELECT * FROM ROOT.ADRESSE WHERE STAAT LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Adresse adresse = new Adresse(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11), rs.getString(12),
                            rs.getString(13));
                    gefundeneAdressen.add(adresse);
                }

            } else if (suchkriterium.equals("Tel")) {
                String query
                        = "SELECT * FROM ROOT.ADRESSE WHERE TELEFON LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Adresse adresse = new Adresse(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11), rs.getString(12),
                            rs.getString(13));
                    gefundeneAdressen.add(adresse);
                }

            } else if (suchkriterium.equals("Email")) {
                String query
                        = "SELECT * FROM ROOT.ADRESSE WHERE E_MAIL LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Adresse adresse = new Adresse(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11), rs.getString(12),
                            rs.getString(13));
                    gefundeneAdressen.add(adresse);
                }

            } else if (suchkriterium.equals("ErfDatum")) {
                String query
                        = "SELECT * FROM ROOT.ADRESSE WHERE ERFASSUNGSDATUM "
                        + "LIKE '" + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Adresse adresse = new Adresse(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11), rs.getString(12), 
                            rs.getString(13));
                    gefundeneAdressen.add(adresse);
                }
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
        return gefundeneAdressen;
    }

    
    
    
    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 14.08.2017    BER     Erstellt.
    * 
    *
    /*------------------------------------------------------------------------*/
    
    /**
     *
     * Sucht mittels eines Suchbegriffs in der Datenbank nach den passenden
     * Artikeln und speichert diese in einer ArrayList ab.
     *
     * @param suchkriterium Suchtriterium welches die Suchspalte der DB angibt.
     * @param suchbegriff ein String nach dem in der Suchspalte gesucht wird.
     * @return Liefert eine ArrayList mit den zu dem Suchbegriff passenden
     * Zahlungskonditionen.
     */
    public ArrayList<Artikel> artikelSuche(
            String suchkriterium, String suchbegriff) {

        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Artikel> gefundenerArtikel = new ArrayList<>();
        StringBuilder neuerSuchbegriff = new StringBuilder(suchbegriff);

        for (int i = 0; i < suchbegriff.length(); i++) {
            if (suchbegriff.charAt(i) == '*') {
                neuerSuchbegriff.setCharAt(i, '%');
            } else if (suchbegriff.charAt(i) == '?') {
                neuerSuchbegriff.setCharAt(i, '_');
            }
        }

        try {

            if (suchkriterium.equals("MaterialNr")) {
                String query
                        = "SELECT * FROM ROOT.ARTIKEL WHERE ARTIKEL_ID LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Artikel artikel = new Artikel(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11));
                    gefundenerArtikel.add(artikel);
                }
                con.commit();

            } else if (suchkriterium.equals("Artikelbeschreibung")) {
                String query
                        = "SELECT * FROM ROOT.ARTIKEL WHERE ARTIKELTEXT LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Artikel artikel = new Artikel(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11));
                    gefundenerArtikel.add(artikel);
                }
                con.commit();

            } else if (suchkriterium.equals("Einzelwert")) {
                String query
                        = "SELECT * FROM ROOT.ARTIKEL WHERE EINZELWERT LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Artikel artikel = new Artikel(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11));
                    gefundenerArtikel.add(artikel);
                }
                con.commit();

            } else if (suchkriterium.equals("Bestellbeschreibung")) {
                String query
                        = "SELECT * FROM ROOT.ARTIKEL WHERE BESTELLTEXT LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Artikel artikel = new Artikel(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11));
                    gefundenerArtikel.add(artikel);
                }
                con.commit();

            } else if (suchkriterium.equals("Bestellwert")) {
                String query
                        = "SELECT * FROM ROOT.ARTIKEL WHERE BESTELLWERT LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Artikel artikel = new Artikel(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11));
                    gefundenerArtikel.add(artikel);
                }
                con.commit();

            } else if (suchkriterium.equals("MwSt. Satz")) {
                String query
                        = "SELECT * FROM ROOT.ARTIKEL WHERE MWST_SATZ LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Artikel artikel = new Artikel(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11));
                    gefundenerArtikel.add(artikel);
                }
                con.commit();

            } else if (suchkriterium.equals("Bestand Frei")) {
                String query
                        = "SELECT * FROM ROOT.ARTIKEL WHERE BESTANDSMENGE_FREI LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Artikel artikel = new Artikel(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11));
                    gefundenerArtikel.add(artikel);
                }
                con.commit();

            } else if (suchkriterium.equals("Bestand Reserviert")) {
                String query
                        = "SELECT * FROM ROOT.ARTIKEL WHERE BESTANDSMENGE_RESERVIERT LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Artikel artikel = new Artikel(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11));
                    gefundenerArtikel.add(artikel);
                }
                con.commit();

            } else if (suchkriterium.equals("Bestand Zulauf")) {
                String query
                        = "SELECT * FROM ROOT.ARTIKEL WHERE BESTANDSMENGE_ZULAUF LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Artikel artikel = new Artikel(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11));
                    gefundenerArtikel.add(artikel);
                }
                con.commit();

            } else if (suchkriterium.equals("Bestand Verkauft")) {
                String query
                        = "SELECT * FROM ROOT.ARTIKEL WHERE BESTANDSMENGE_VERKAUFT LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Artikel artikel = new Artikel(rs.getString(1),
                            rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11));
                    gefundenerArtikel.add(artikel);
                }

            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
        return gefundenerArtikel;

    }

    /**
     *
     * Sucht mittels eines Suchbegriffs in der Datenbank nach den passenden
     * Geschäftspartnern und speichert diese in einer ArrayList ab.
     *
     * @param suchkriterium Suchtriterium welches die Suchspalte der DB angibt.
     * @param suchbegriff ein String nach dem in der Suchspalte gesucht wird.
     * @return Liefert eine ArrayList mit den zu dem Suchbegriff passenden
     * Geschäftspartnern.
     */
    public ArrayList geschaeftspartnerSuche(String suchkriterium,
            String suchbegriff) {

        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Geschaeftspartner> gefundeneGP = new ArrayList<>();
        StringBuilder neuerSuchbegriff = new StringBuilder(suchbegriff);

        for (int i = 0; i < suchbegriff.length(); i++) {
            if (suchbegriff.charAt(i) == '*') {
                neuerSuchbegriff.setCharAt(i, '%');
            } else if (suchbegriff.charAt(i) == '?') {
                neuerSuchbegriff.setCharAt(i, '_');
            }
        }

        try {

            if (suchkriterium.equals("Geschaeftspartner-ID")) {
                String query
                        = "SELECT * FROM ROOT.GESCHAEFTSPARTNER "
                        + "WHERE GESCHAEFTSPARTNER_ID LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {

                    Geschaeftspartner gp = new Geschaeftspartner(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6));

                    gefundeneGP.add(gp);
                }
                con.commit();

            } else if (suchkriterium.equals("Geschäftspartner-Typ")) {
                String query
                        = "SELECT * FROM ROOT.GESCHAEFTSPARTNER "
                        + "WHERE TYP LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {

                    Geschaeftspartner gp = new Geschaeftspartner(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6));

                    gefundeneGP.add(gp);
                }
                con.commit();
            } else if (suchkriterium.equals("Anschrift-ID")) {
                String query
                        = "SELECT * FROM ROOT.GESCHAEFTSPARTNER "
                        + "WHERE ANSCHRIFT_ID LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {

                    Geschaeftspartner gp = new Geschaeftspartner(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6));

                    gefundeneGP.add(gp);
                }
                con.commit();
            } else if (suchkriterium.equals("Liefer-ID")) {
                String query
                        = "SELECT * FROM ROOT.GESCHAEFTSPARTNER "
                        + "WHERE LIEFER_ID LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {

                    Geschaeftspartner gp = new Geschaeftspartner(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6));

                    gefundeneGP.add(gp);
                }
                con.commit();
            } else if (suchkriterium.equals("Kreditlimit")) {
                String query
                        = "SELECT * FROM ROOT.GESCHAEFTSPARTNER "
                        + "WHERE KREDITLIMIT LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {

                    Geschaeftspartner gp = new Geschaeftspartner(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6));

                    gefundeneGP.add(gp);
                }
                con.commit();
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }

        return gefundeneGP;

    }

    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 14.08.2017    CEL     Erstellt.
    * 20.08.2017    GET     Querys erstellt und Methode fertiggestellt.
    *
    /*------------------------------------------------------------------------*/
    
    /**
     *
     * Sucht mittels eines Suchbegriffs in der Datenbank nach den passenden
     * Zahlungskonditionen und speichert diese in einer ArrayList ab.
     *
     * @param suchkriterium Suchtriterium welches die Suchspalte der DB angibt.
     * @param suchbegriff ein String nach dem in der Suchspalte gesucht wird.
     * @return Liefert eine ArrayList mit den zu dem Suchbegriff passenden
     * Zahlungskonditionen.
     */
    public ArrayList<Zahlungskonditionen> zahlungskonditionSuche(
            String suchkriterium, String suchbegriff) {

        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Zahlungskonditionen> gefundeneZK = new ArrayList<>();
        StringBuilder neuerSuchbegriff = new StringBuilder(suchbegriff);

        for (int i = 0; i < suchbegriff.length(); i++) {
            if (suchbegriff.charAt(i) == '*') {
                neuerSuchbegriff.setCharAt(i, '%');
            } else if (suchbegriff.charAt(i) == '?') {
                neuerSuchbegriff.setCharAt(i, '_');
            }
        }

        try {

            if (suchkriterium.equals("Konditionen-ID")) {

                String query
                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
                        + "ZAHLUNGSKONDITIONS_ID LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Zahlungskonditionen zk = new Zahlungskonditionen(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10), rs.getString(11),
                            rs.getString(12));
                    gefundeneZK.add(zk);
                }
                con.commit();

            } else if (suchkriterium.equals("Auftragsart")) {

                String query
                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
                        + "AUFTRAGSART LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Zahlungskonditionen zk = new Zahlungskonditionen(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10), rs.getString(11),
                            rs.getString(12));
                    gefundeneZK.add(zk);
                }
                con.commit();

            } else if (suchkriterium.equals("LieferzeitSOFORT")) {

                String query
                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
                        + "LIEFERZEIT_SOFORT LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Zahlungskonditionen zk = new Zahlungskonditionen(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10), rs.getString(11),
                            rs.getString(12));
                    gefundeneZK.add(zk);
                }
                con.commit();

            } else if (suchkriterium.equals("SperrzeitWUNSCH")) {

                String query
                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
                        + "SPERRZEIT_WUNSCH LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Zahlungskonditionen zk = new Zahlungskonditionen(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10), rs.getString(11),
                            rs.getString(12));
                    gefundeneZK.add(zk);
                }
                con.commit();

            } else if (suchkriterium.equals("Skontozeit 1")) {

                String query
                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
                        + "SKONTOZEIT_1 LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Zahlungskonditionen zk = new Zahlungskonditionen(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10), rs.getString(11),
                            rs.getString(12));
                    gefundeneZK.add(zk);
                }
                con.commit();

            } else if (suchkriterium.equals("Skonto 1")) {

                String query
                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
                        + "SKONTO_1 LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Zahlungskonditionen zk = new Zahlungskonditionen(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10), rs.getString(11),
                            rs.getString(12));
                    gefundeneZK.add(zk);
                }
                con.commit();

            } else if (suchkriterium.equals("Skontozeit 2")) {

                String query
                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
                        + "SKONTOZEIT_2 LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Zahlungskonditionen zk = new Zahlungskonditionen(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10), rs.getString(11),
                            rs.getString(12));
                    gefundeneZK.add(zk);
                }
                con.commit();

            } else if (suchkriterium.equals("Skonto 2")) {

                String query
                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
                        + "SKONTO_2 LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Zahlungskonditionen zk = new Zahlungskonditionen(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10), rs.getString(11),
                            rs.getString(12));
                    gefundeneZK.add(zk);
                }
                con.commit();
            } else if (suchkriterium.equals("Mahnzeit 1")) {

                String query
                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
                        + "MAHNZEIT_1 LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Zahlungskonditionen zk = new Zahlungskonditionen(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10), rs.getString(11),
                            rs.getString(12));
                    gefundeneZK.add(zk);
                }
                con.commit();

            } else if (suchkriterium.equals("Mahnzeit 2")) {

                String query
                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
                        + "MAHNZEIT_2 LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Zahlungskonditionen zk = new Zahlungskonditionen(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10), rs.getString(11),
                            rs.getString(12));
                    gefundeneZK.add(zk);
                }
                con.commit();

            } else if (suchkriterium.equals("Mahnzeit 3")) {

                String query
                        = "SELECT * FROM ROOT.ZAHLUNGSKONDITIONEN WHERE "
                        + "MAHNZEIT_3 LIKE '"
                        + neuerSuchbegriff + "' AND LKZ LIKE 'N'";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Zahlungskonditionen zk = new Zahlungskonditionen(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10), rs.getString(11),
                            rs.getString(12));
                    gefundeneZK.add(zk);
                }
                con.commit();

            }

        } catch (SQLException e) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();

        }

        return gefundeneZK;
    }
}
