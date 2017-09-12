/*------------------------------------------------------------------------------
* Klasse: SucheeDAO.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse verwaltet alle Suchfelder.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 18.08.2017    BER     Erstellt.
* 18.08.2017    BER     adressSuche erstellt.
* 19.08.2017    BER     artikelSuche erstellt.
* 19.08.2017    CEL     zahlungskonditionenSuche erstellt.
* 20 08.2017    GET     zahlungskonditionenSuche erweitert.
* 22.08.2017    BER     geschaeftspartnerSuche erstellt.
*-------------------------------------------------------------------------------
 */
package Datenbank;

import Klassen.Adresse;
import Klassen.Artikel;
import Klassen.Auftragskopf;
import Klassen.Geschaeftspartner;
import Klassen.Zahlungskonditionen;
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
public class SucheDAO extends DataAccess {

    /**
     * Erzeugt ein neues DataDictionaryDAO Objekt.
     */
    private DataDictionaryDAO ddd = new DataDictionaryDAO();
    
    /**
     * 
     */
    private HashMap<String, ArrayList> attribute;     
    /**
     * Konstruktor.
     *
     * @throws SQLException SQLException
     */
    public SucheDAO() throws SQLException {
        attribute = ddd.getTabellenAttribute();
    }
    
    
    
    /*------------------------------------------------------------------------*
    * Datum       Name    Was
    * 18.08.17    BER     Erstellt
    * 20.08.17    BER     Querys angepasst und Methode fertiggestellt.
    *
    /*------------------------------------------------------------------------*/

    /**
     *
     * Sucht mittels eines Suchbegriffs in der Datenbank nach den passenden
     * Adressen und speichert diese in einer ArrayList ab.
     * @param suchkriterium Suchtriterium welches die Suchspalte der DB angibt.
     * @param suchbegriff ein String nach dem in der Suchspalte gesucht wird.
     * @return Liefert eine ArrayList mit den zu dem Suchbegriff passenden
     * Adressen.
     * @throws java.sql.SQLException SQLException
     */
    public ArrayList<Adresse> adressSuche(
            String suchkriterium, String suchbegriff) throws SQLException {

        String TAB_ADRESSE = ddd.getTAB_ADRESSE();
        ddd.holeAlleAttribute(TAB_ADRESSE);
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
                    = "SELECT * FROM ROOT." + ddd.getTabAdresse() + " WHERE " 
                    + attribute.get(TAB_ADRESSE).get(0) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ADRESSE).get(12) + " LIKE 'N'";
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
                    = "SELECT * FROM ROOT." + ddd.getTabAdresse() 
                    + " WHERE " + attribute.get(TAB_ADRESSE).get(1) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ADRESSE).get(12) + " LIKE 'N'";
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
                    = "SELECT * FROM ROOT." + ddd.getTabAdresse() 
                    + " WHERE " + attribute.get(TAB_ADRESSE).get(2) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ADRESSE).get(12) + " LIKE 'N'";
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
                    = "SELECT * FROM ROOT." + ddd.getTabAdresse() 
                    + " WHERE " + attribute.get(TAB_ADRESSE).get(3) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ADRESSE).get(12) + " LIKE 'N'";
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
                    = "SELECT * FROM ROOT." + ddd.getTabAdresse() 
                    + " WHERE " + attribute.get(TAB_ADRESSE).get(4) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ADRESSE).get(12) + " LIKE 'N'";
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
                    = "SELECT * FROM ROOT." + ddd.getTabAdresse() 
                    + " WHERE " + attribute.get(TAB_ADRESSE).get(5) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ADRESSE).get(12) + " LIKE 'N'";
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
                    = "SELECT * FROM ROOT." + ddd.getTabAdresse() 
                    + " WHERE " + attribute.get(TAB_ADRESSE).get(6) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ADRESSE).get(12) + " LIKE 'N'";
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
                    = "SELECT * FROM ROOT." + ddd.getTabAdresse() 
                    + " WHERE " + attribute.get(TAB_ADRESSE).get(7) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ADRESSE).get(12) + " LIKE 'N'";
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
                    = "SELECT * FROM ROOT." + ddd.getTabAdresse() 
                    + " WHERE " + attribute.get(TAB_ADRESSE).get(8) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ADRESSE).get(12) + " LIKE 'N'";
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
                    = "SELECT * FROM ROOT." + ddd.getTabAdresse() 
                    + " WHERE " + attribute.get(TAB_ADRESSE).get(9) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ADRESSE).get(12) + " LIKE 'N'";
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
                    = "SELECT * FROM ROOT." + ddd.getTabAdresse() 
                    + " WHERE " + attribute.get(TAB_ADRESSE).get(10) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ADRESSE).get(12) + " LIKE 'N'";
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
                    = "SELECT * FROM ROOT." + ddd.getTabAdresse() 
                    + " WHERE " + attribute.get(TAB_ADRESSE).get(11) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ADRESSE).get(12) + " LIKE 'N'";
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
            con.close();

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
    * 19.08.2017    BER     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht mittels eines Suchbegriffs in der Datenbank nach den passenden
     * Artikeln und speichert diese in einer ArrayList ab.
     * @param suchkriterium Suchtriterium welches die Suchspalte der DB angibt.
     * @param suchbegriff ein String nach dem in der Suchspalte gesucht wird.
     * @return Liefert eine ArrayList mit den zu dem Suchbegriff passenden
     * Zahlungskonditionen.
     * @throws java.sql.SQLException SQL Exception
     */
    public ArrayList<Artikel> artikelSuche(
            String suchkriterium, String suchbegriff) throws SQLException {
        
        String TAB_ARTIKEL = ddd.getTAB_ARTIKEL();
        ddd.holeAlleAttribute(TAB_ARTIKEL);
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
                    = "SELECT * FROM ROOT." + ddd.getTabArtikel() 
                    + " WHERE " + attribute.get(TAB_ARTIKEL).get(0) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ARTIKEL).get(10) + " LIKE 'N'";
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

                
            } else if (suchkriterium.equals("Artikelbeschreibung")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabArtikel() 
                    + " WHERE " + attribute.get(TAB_ARTIKEL).get(1) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ARTIKEL).get(10) + " LIKE 'N'";
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

                
            } else if (suchkriterium.equals("Einzelwert")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabArtikel() 
                    + " WHERE " + attribute.get(TAB_ARTIKEL).get(2) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ARTIKEL).get(10) + " LIKE 'N'";
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

                
            } else if (suchkriterium.equals("Bestellbeschreibung")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabArtikel() 
                    + " WHERE " + attribute.get(TAB_ARTIKEL).get(3) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ARTIKEL).get(10) + " LIKE 'N'";
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

                
            } else if (suchkriterium.equals("Bestellwert")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabArtikel() 
                    + " WHERE " + attribute.get(TAB_ARTIKEL).get(4) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ARTIKEL).get(10) + " LIKE 'N'";
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

                
            } else if (suchkriterium.equals("MwSt. Satz")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabArtikel() 
                    + " WHERE " + attribute.get(TAB_ARTIKEL).get(5) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ARTIKEL).get(10) + " LIKE 'N'";
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


            } else if (suchkriterium.equals("Bestand Frei")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabArtikel() 
                    + " WHERE " + attribute.get(TAB_ARTIKEL).get(6) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ARTIKEL).get(10) + " LIKE 'N'";
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


            } else if (suchkriterium.equals("Bestand Reserviert")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabArtikel() 
                    + " WHERE " + attribute.get(TAB_ARTIKEL).get(7) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ARTIKEL).get(10) + " LIKE 'N'";
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


            } else if (suchkriterium.equals("Bestand Zulauf")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabArtikel() 
                    + " WHERE " + attribute.get(TAB_ARTIKEL).get(8) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ARTIKEL).get(10) + " LIKE 'N'";
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


            } else if (suchkriterium.equals("Bestand Verkauft")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabArtikel() 
                    + " WHERE " + attribute.get(TAB_ARTIKEL).get(9) + " LIKE '"
                    + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ARTIKEL).get(10) + " LIKE 'N'";
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
            
            con.commit();
            con.close();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
        return gefundenerArtikel;
    }

    

    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 22.08.2017    BER     Erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht mittels eines Suchbegriffs in der Datenbank nach den passenden
     * Geschäftspartnern und speichert diese in einer ArrayList ab.
     * @param suchkriterium Suchtriterium welches die Suchspalte der DB angibt.
     * @param suchbegriff ein String nach dem in der Suchspalte gesucht wird.
     * @return Liefert eine ArrayList mit den zu dem Suchbegriff passenden
     * Geschäftspartnern.
     * @throws java.sql.SQLException SQLException
     */
    public ArrayList geschaeftspartnerSuche(String suchkriterium,
            String suchbegriff) throws SQLException {

        String TAB_GESCHAEFTSPARTNER = ddd.getTAB_GESCHAEFTSPARTNER();
        ddd.holeAlleAttribute(TAB_GESCHAEFTSPARTNER);
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
                    = "SELECT * FROM ROOT." + ddd.getTabGeschaeftspartner()
                    + " WHERE " + attribute.get(TAB_GESCHAEFTSPARTNER).get(0) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_GESCHAEFTSPARTNER).get(5) + " LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Geschaeftspartner gp = new Geschaeftspartner(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6));
                    gefundeneGP.add(gp);
                }


            } else if (suchkriterium.equals("Geschäftspartner-Typ")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabGeschaeftspartner()
                    + " WHERE " + attribute.get(TAB_GESCHAEFTSPARTNER).get(1) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_GESCHAEFTSPARTNER).get(5) + " LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Geschaeftspartner gp = new Geschaeftspartner(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6));
                    gefundeneGP.add(gp);
                }

                
            } else if (suchkriterium.equals("Anschrift-ID")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabGeschaeftspartner()
                    + " WHERE " + attribute.get(TAB_GESCHAEFTSPARTNER).get(2) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_GESCHAEFTSPARTNER).get(5) + " LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Geschaeftspartner gp = new Geschaeftspartner(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6));
                    gefundeneGP.add(gp);
                }
 
                
            } else if (suchkriterium.equals("Liefer-ID")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabGeschaeftspartner()
                    + " WHERE " + attribute.get(TAB_GESCHAEFTSPARTNER).get(3) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_GESCHAEFTSPARTNER).get(5) + " LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Geschaeftspartner gp = new Geschaeftspartner(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6));
                    gefundeneGP.add(gp);
                }
 
                
            } else if (suchkriterium.equals("Kreditlimit")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabGeschaeftspartner()
                    + " WHERE " + attribute.get(TAB_GESCHAEFTSPARTNER).get(4) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_GESCHAEFTSPARTNER).get(5) + " LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Geschaeftspartner gp = new Geschaeftspartner(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6));

                    gefundeneGP.add(gp);
                }
            }
            
            con.commit();
            con.close();

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
    * 19.08.2017    CEL     Methode erstellt.
    * 20.08.2017    GET     Querys erstellt und Methode fertiggestellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht mittels eines Suchbegriffs in der Datenbank nach den passenden
     * Zahlungskonditionen und speichert diese in einer ArrayList ab.
     * @param suchkriterium Suchtriterium welches die Suchspalte der DB angibt.
     * @param suchbegriff ein String nach dem in der Suchspalte gesucht wird.
     * @return Liefert eine ArrayList mit den zu dem Suchbegriff passenden
     * Zahlungskonditionen.
     * @throws java.sql.SQLException SQLException
     */
    public ArrayList<Zahlungskonditionen> zahlungskonditionSuche(
            String suchkriterium, String suchbegriff) throws SQLException {

        String TAB_ZAHLUNGSKONDITIONEN = ddd.getTAB_ZAHLUNGSKONDITIONEN();
        ddd.holeAlleAttribute(TAB_ZAHLUNGSKONDITIONEN);
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
                    = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen() 
                    + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
                    + " LIKE 'N'";
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


            } else if (suchkriterium.equals("Auftragsart")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen() 
                    + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(1) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
                    + " LIKE 'N'";
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


            } else if (suchkriterium.equals("LieferzeitSOFORT")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen() 
                    + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(2) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
                    + " LIKE 'N'";
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
 

            } else if (suchkriterium.equals("SperrzeitWUNSCH")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen() 
                    + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(3) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
                    + " LIKE 'N'";
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
  

            } else if (suchkriterium.equals("Skontozeit 1")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen() 
                    + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(4) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
                    + " LIKE 'N'";
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
 

            } else if (suchkriterium.equals("Skonto 1")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen() 
                    + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(5) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
                    + " LIKE 'N'";
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
 

            } else if (suchkriterium.equals("Skontozeit 2")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen() 
                    + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(6) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
                    + " LIKE 'N'";
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


            } else if (suchkriterium.equals("Skonto 2")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen() 
                    + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(7) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
                    + " LIKE 'N'";
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
 
                
            } else if (suchkriterium.equals("Mahnzeit 1")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen() 
                    + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(8) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
                    + " LIKE 'N'";
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
 

            } else if (suchkriterium.equals("Mahnzeit 2")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen() 
                    + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(9) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
                    + " LIKE 'N'";
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
 

            } else if (suchkriterium.equals("Mahnzeit 3")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabZahlungskonditionen() 
                    + " WHERE " + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(10) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(11) 
                    + " LIKE 'N'";
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
            }
            
            con.commit();
            con.close();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
        return gefundeneZK;
    }
    
    

    /*------------------------------------------------------------------------*
    * Datum         Name    Was
    * 12.08.2017    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Sucht mittels eines Suchbegriffs in der Datenbank nach den passenden
     * Aufträgen und speichert diese in einer ArrayList ab.
     * @param suchkriterium Suchtriterium welches die Suchspalte der DB angibt.
     * @param suchbegriff ein String nach dem in der Suchspalte gesucht wird.
     * @return Liefert eine ArrayList mit den zu dem Suchbegriff passenden
     * Aufträgen.
     * @throws java.sql.SQLException SQLFehler
     */
    public ArrayList<Auftragskopf> auftragskopfSuche(
            String suchkriterium, String suchbegriff) throws SQLException {

        String TAB_AUFTRAGSKOPF = ddd.getTAB_AUFTRAGSKOPF();
        ddd.holeAlleAttribute(TAB_AUFTRAGSKOPF);
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Auftragskopf> gefundeneAK = new ArrayList<>();
        StringBuilder neuerSuchbegriff = new StringBuilder(suchbegriff);

        for (int i = 0; i < suchbegriff.length(); i++) {
            if (suchbegriff.charAt(i) == '*') {
                neuerSuchbegriff.setCharAt(i, '%');
            } else if (suchbegriff.charAt(i) == '?') {
                neuerSuchbegriff.setCharAt(i, '_');
            }
        }

        try {
            if (suchkriterium.equals("AuftragskopfID")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabAuftragskopf()
                    + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(0) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_AUFTRAGSKOPF).get(9) 
                    + " LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Auftragskopf ak = new Auftragskopf(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10));
                    gefundeneAK.add(ak);
                }


            } else if (suchkriterium.equals("GeschäftspartnerID")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabAuftragskopf() 
                    + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(1) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_AUFTRAGSKOPF).get(9) 
                    + " LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Auftragskopf ak = new Auftragskopf(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10));
                    gefundeneAK.add(ak);
                }


            } else if (suchkriterium.equals("Auftragstext")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabAuftragskopf() 
                    + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(2) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_AUFTRAGSKOPF).get(9) 
                    + " LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Auftragskopf ak = new Auftragskopf(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10));
                    gefundeneAK.add(ak);
                }
 

            } else if (suchkriterium.equals("Erfassungsdatum")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabAuftragskopf() 
                    + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(3) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_AUFTRAGSKOPF).get(9) 
                    + " LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Auftragskopf ak = new Auftragskopf(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10));
                    gefundeneAK.add(ak);
                }
  
                
            } else if (suchkriterium.equals("Lieferdatum")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabAuftragskopf() 
                    + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(4) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_AUFTRAGSKOPF).get(9) 
                    + " LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Auftragskopf ak = new Auftragskopf(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10));
                    gefundeneAK.add(ak);
                }
       
                
            } else if (suchkriterium.equals("Abschlussdatum")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabAuftragskopf() 
                    + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(5) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_AUFTRAGSKOPF).get(9) 
                    + " LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Auftragskopf ak = new Auftragskopf(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10));
                    gefundeneAK.add(ak);
                }
 

            } else if (suchkriterium.equals("Status")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabAuftragskopf() 
                    + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(6) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_AUFTRAGSKOPF).get(9) 
                    + " LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Auftragskopf ak = new Auftragskopf(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10));
                    gefundeneAK.add(ak);
                }
 

            } else if (suchkriterium.equals("Auftragsart")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabAuftragskopf() 
                    + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(7) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_AUFTRAGSKOPF).get(9) 
                    + " LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Auftragskopf ak = new Auftragskopf(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10));
                    gefundeneAK.add(ak);
                }


            } else if (suchkriterium.equals("Auftragswert")) {
                String query
                    = "SELECT * FROM ROOT." + ddd.getTabAuftragskopf() 
                    + " WHERE " + attribute.get(TAB_AUFTRAGSKOPF).get(8) 
                    + " LIKE '" + neuerSuchbegriff + "' AND " 
                    + attribute.get(TAB_AUFTRAGSKOPF).get(9) 
                    + " LIKE 'N'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Auftragskopf ak = new Auftragskopf(
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8), rs.getString(9),
                            rs.getString(10));
                    gefundeneAK.add(ak);
                }     
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
        return gefundeneAK;
    }
    
    
}
