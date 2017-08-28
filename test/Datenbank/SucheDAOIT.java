/*------------------------------------------------------------------------------
* Klasse: SucheDAOTest.
*-------------------------------------------------------------------------------
* Zweck:
* - In dieser Klasse werden alle Methoden der Klasse SucheDAO auf
*   Korrektheit und Ausführbarkeit überprüft.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 18.08.2017    SAM     Erstellt.
* 26.08.2017    SAM     Fertiggestellt.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Adresse;
import Klassen.Artikel;
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
public class SucheDAOIT {
    
    public SucheDAOIT() {
    }

    
    
    /**
    *   In dieser Klasse wird die Suche auf Funktionalität überprüft.
    *   Wenn die in den Methoden angegebenen Attribute zu der Suche passen,
    *   ist der Test erfolgreich und die Methode korrekt implementiert.
    * Test of adressSuche method, of class SucheDAO.
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
     * @throws java.sql.SQLException SQLException
     */
    @Test
    public void testArtikelSuche() throws SQLException {
        System.out.println("artikelSuche");
        String suchkriterium = "Bestellbeschreibung";
        String suchbegriff = "Testtext";
        SucheDAO instance = new SucheDAO();
        ObservableList<Artikel> expResult
                = FXCollections.observableArrayList(
                        instance.artikelSuche(suchkriterium, suchbegriff));
        ArrayList<Artikel> result = 
                instance.artikelSuche(suchkriterium, suchbegriff);
        assertEquals(expResult, result);
    }

    /**
     * Test of geschaeftspartnerSuche method, of class SucheDAO.
     * @throws java.sql.SQLException SQLException
     */
    @Test
    public void testGeschaeftspartnerSuche() throws SQLException {
        System.out.println("geschaeftspartnerSuche");
        String suchkriterium = "";
        String suchbegriff = "";
        SucheDAO instance = new SucheDAO();
        ObservableList<Geschaeftspartner> expResult
                = FXCollections.observableArrayList(
                        instance.geschaeftspartnerSuche(
                                suchkriterium, suchbegriff));
        ArrayList result = 
                instance.geschaeftspartnerSuche(suchkriterium, suchbegriff);
        assertEquals(expResult, result);
    }

    /**
     * Test of zahlungskonditionSuche method, of class SucheDAO.
     * @throws java.sql.SQLException SQLException
     */
    @Test
    public void testZahlungskonditionSuche() throws SQLException {
        System.out.println("zahlungskonditionSuche");
        String suchkriterium = "";
        String suchbegriff = "";
        SucheDAO instance = new SucheDAO();
        ObservableList<Zahlungskonditionen> expResult
                = FXCollections.observableArrayList(
                        instance.zahlungskonditionSuche(
                                suchkriterium, suchbegriff));
        ArrayList<Zahlungskonditionen> result = 
                instance.zahlungskonditionSuche(suchkriterium, suchbegriff);
        assertEquals(expResult, result);
    }
    
}
