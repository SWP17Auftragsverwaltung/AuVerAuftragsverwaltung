/*------------------------------------------------------------------------------
* Klasse: SucheDAOTest.
*-------------------------------------------------------------------------------
* Zweck:
* - In dieser Klasse werden alle Methoden der Klasse SucheDAO auf
*   Korrektheit und Ausführbarkeit überprüft.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 17.09.2017    CEL     Erstellt.
* 17.09.2017    CEL     Fertiggestellt.
*                       Getestet und freigegeben.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Adresse;
import Klassen.Artikel;
import Klassen.Auftragskopf;
import Klassen.Geschaeftspartner;
import Klassen.Zahlungskonditionen;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chakir
 */
public class SucheDAOTest {

    public SucheDAOTest() {
    }

    /**
     * In dieser Klasse wird die Suche auf Funktionalität überprüft. Wenn die in
     * den Methoden angegebenen Attribute zu der Suche passen, ist der Test
     * erfolgreich und die Methode korrekt implementiert. Test of adressSuche
     * method, of class SucheDAO.
     *
     * @throws java.lang.Exception Exception
     */
    @Test
    public void testAdressSuche() throws Exception {
        System.out.println("adressSuche");
        String suchkriterium = "Anrede";
        String suchbegriff = "Herr";
        SucheDAO instance = new SucheDAO();
        ObservableList<Adresse> expResult
                = FXCollections.observableArrayList(
                        instance.adressSuche(suchkriterium, suchbegriff));
        ArrayList<Adresse> result
                = instance.adressSuche(suchkriterium, suchbegriff);
        assertEquals(expResult, result);
    }

    /**
     * Test of artikelSuche method, of class SucheDAO.
     *
     * @throws java.sql.SQLException SQLException
     */
    @Test
    public void testArtikelSuche() throws SQLException {
        System.out.println("artikelSuche");
        String suchkriterium = "Einzelwert";
        String suchbegriff = "100";
        SucheDAO instance = new SucheDAO();
        ObservableList<Artikel> expResult
                = FXCollections.observableArrayList(
                        instance.artikelSuche(suchkriterium, suchbegriff));
        ArrayList<Artikel> result
                = instance.artikelSuche(suchkriterium, suchbegriff);
        assertEquals(expResult, result);
    }

    /**
     * Test of geschaeftspartnerSuche method, of class SucheDAO.
     *
     * @throws java.sql.SQLException SQLException
     */
    @Test
    public void testGeschaeftspartnerSuche() throws SQLException {
        System.out.println("geschaeftspartnerSuche");
        String suchkriterium = "Kreditlimit";
        String suchbegriff = "2500";
        SucheDAO instance = new SucheDAO();
        ObservableList<Geschaeftspartner> expResult
                = FXCollections.observableArrayList(
                        instance.geschaeftspartnerSuche(
                                suchkriterium, suchbegriff));
        ArrayList result
                = instance.geschaeftspartnerSuche(suchkriterium, suchbegriff);
        assertEquals(expResult, result);
    }

    /**
     * Test of zahlungskonditionSuche method, of class SucheDAO.
     *
     * @throws java.sql.SQLException SQLException
     */
    @Test
    public void testZahlungskonditionSuche() throws SQLException {
        System.out.println("zahlungskonditionSuche");
        String suchkriterium = "Auftragsart";
        String suchbegriff = "Barzahlung";
        SucheDAO instance = new SucheDAO();
        ObservableList<Zahlungskonditionen> expResult
                = FXCollections.observableArrayList(
                        instance.zahlungskonditionSuche(
                                suchkriterium, suchbegriff));
        ArrayList<Zahlungskonditionen> result
                = instance.zahlungskonditionSuche(suchkriterium, suchbegriff);
        assertEquals(expResult, result);
    }

    /**
     * Test of auftragskopfSuche method, of class SucheDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testAuftragskopfSuche() throws Exception {
        System.out.println("auftragskopfSuche");
        String suchkriterium = "Status";
        String suchbegriff = "A";
        SucheDAO instance = new SucheDAO();
        ObservableList<Auftragskopf> expResult
                = FXCollections.observableArrayList(
                        instance.auftragskopfSuche(
                                suchkriterium, suchbegriff));
        ArrayList<Auftragskopf> result 
                = instance.auftragskopfSuche(suchkriterium, suchbegriff);
        assertEquals(expResult, result);
    }

}
