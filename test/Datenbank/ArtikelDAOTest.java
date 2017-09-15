/*------------------------------------------------------------------------------
* Klasse: ArtikelDAOTest.
*-------------------------------------------------------------------------------
* Zweck:
* - In dieser Klasse werden alle Methoden der Klasse AritkelDAO auf
*   Korrektheit und Ausführbarkeit überprüft.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 18.08.2017    SAM     Erstellt.
* 26.08.2017    SAM     Fertiggestellt.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Artikel;
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
public class ArtikelDAOTest {
    
    public ArtikelDAOTest() {
    }

    /**
     * Test of gibAlleArtikel method, of class ArtikelDAO.
     * @throws java.sql.SQLException SQLException  
     *   Diese Testmethode vergleicht die vorhandenen Artikel der Tableview
     *   mit denen in der Datenbank.
    */
    @Test
    public void testGibAlleArtikel() throws SQLException {
        System.out.println("gibAlleArtikel");
        ArtikelDAO instance = new ArtikelDAO();
        ObservableList<Artikel> expResult
            = FXCollections.observableArrayList(
                    instance.gibAlleArtikelOhneLKZ());
        ArrayList<Artikel> result = instance.gibAlleArtikelOhneLKZ();
        assertEquals(expResult, result);
    }

    /**
     * Test of gibAlleArtikelOhneLKZ method, of class ArtikelDAO.
     * @throws java.sql.SQLException SQLException  
     *   Diese Testmethode vergleicht die vorhandenen Artikel der Tableview
     *   mit denen in der Datenbank. In diesem Fall OHNE gesetztem LKZ.
    */
    @Test
    public void testGibAlleArtikelOhneLKZ() throws SQLException {
        System.out.println("gibAlleArtikelOhneLKZ");
        ArtikelDAO instance = new ArtikelDAO();
        ObservableList<Artikel> expResult
            = FXCollections.observableArrayList(
                instance.gibAlleArtikelOhneLKZ());
        ArrayList<Artikel> result = instance.gibAlleArtikelOhneLKZ();
        assertEquals(expResult, result);
    }


    /**
     * Test of fuegeArtikelHinzu method, of class ArtikelDAO.
     * @throws java.sql.SQLException SQLException
     */
    @Test
    public void testFuegeArtikelHinzu() throws SQLException {
        System.out.println("fuegeArtikelHinzu");
        Artikel artikel = new Artikel("999999", "Artikeltext", 
                "Bestelltext", "100", "10", 
                "19", "1000", "150", "500", "800", "N");
        ArtikelDAO instance = new ArtikelDAO();
        instance.fuegeArtikelHinzu(artikel);
    }

    /**
     * Test of aendernArtikel method, of class ArtikelDAO.
     * @throws java.sql.SQLException SQLException 
     *   Die Testmethode soll bereits vorhandene Datensätze abrufen
     *   und eine Änderung dieser vornehmen.
    */
    @Test
    public void testAendereArtikel() throws SQLException {
        System.out.println("aendernArtikel");
        Artikel a = new Artikel();
        
        a.setArtikeltext(a.getArtikeltext() + "geändert");
        a.setBestelltext(a.getBestelltext() + "geändert");
        a.setEinzelwert(a.getEinzelwert() + "geändert");
        a.setBestellwert(a.getBestellwert() + "geändert");
        a.setSteuer(a.getSteuer() + "geändert");
        a.setBestandsmengeFrei(a.getBestandsmengeFrei() 
                + "geändert");
        a.setBestandsmengeReserviert(a.getBestandsmengeReserviert() 
                + "geändert");
        a.setBestandsmengeZulauf(a.getBestandsmengeZulauf() 
                + "geändert");
        a.setBestandsmengeVerkauft(a.getBestandsmengeVerkauft() 
                + "geändert");
        
        ArtikelDAO instance = new ArtikelDAO();
        instance.aendereArtikel(a);
    }

    /**
     * Test of setzeLKZ method, of class ArtikelDAO.
     * @throws java.sql.SQLException SQLException
     *
     *   Dieser Test überprüft, ob die Methode erfolgreich das LKZ setzt.
     *   Alternativ: 'a.setLkz("J")/("N")'.
     */
    @Test
    public void testSetzeLKZ() throws SQLException {
        System.out.println("setzeLKZ");
        Artikel a = new Artikel();
        a.setLKZ(a.getLKZ());
        ArtikelDAO instance = new ArtikelDAO();
        instance.setzeLKZ(a);
    }
    
}
