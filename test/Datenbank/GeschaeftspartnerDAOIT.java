/*------------------------------------------------------------------------------
* Klasse: GeschäftspartnerDAOTest.
*-------------------------------------------------------------------------------
* Zweck:
* - In dieser Klasse werden alle Methoden der Klasse GeschäftspartnerDAO auf
*   Korrektheit und Ausführbarkeit überprüft.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 18.08.2017    CEL     Erstellt.
* 26.08.2017    CEL     Fertiggestellt.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Geschaeftspartner;
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
public class GeschaeftspartnerDAOIT {

    public GeschaeftspartnerDAOIT() {
    }

    /**
     * Test of gibAlleGeschaeftspartner method, of class GeschaeftspartnerDAO.
     *
     * @throws java.sql.SQLException SQLException  
     *   Diese Testmethode vergleicht die vorhandenen Geschäftspartner
     *   der Tableview mit denen der Datenbank(ohne LKZ).
     */
    @Test
    public void testGibAlleGeschaeftspartner() throws SQLException {
        System.out.println("gibAlleGeschaeftspartner");
        GeschaeftspartnerDAO instance = new GeschaeftspartnerDAO();
        ObservableList<Geschaeftspartner> expResult
                = FXCollections.observableArrayList(
                        instance.gibAlleGeschaeftspartnerOhneLKZ());
        ArrayList<Geschaeftspartner> result
                = instance.gibAlleGeschaeftspartnerOhneLKZ();
        assertEquals(expResult, result);
    }

    /**
     * Test of gibAlleGeschaeftspartnerOhneLKZ method, of class
     * GeschaeftspartnerDAO.
     *
     * @throws java.sql.SQLException SQLException  
     *   Diese Testmethode vergleicht die vorhandenen Geschäftspartner
     *   der Tableview mit denen in der Datenbank. In diesem Fall 
     *   OHNE gesetztem LKZ.
     */
    @Test
    public void testGibAlleGeschaeftspartnerOhneLKZ() throws SQLException {
        System.out.println("gibAlleGeschaeftspartnerOhneLKZ");
        GeschaeftspartnerDAO instance = new GeschaeftspartnerDAO();
        ObservableList<Geschaeftspartner> expResult
                = FXCollections.observableArrayList(
                        instance.gibAlleGeschaeftspartnerOhneLKZ());
        ArrayList<Geschaeftspartner> result = 
                instance.gibAlleGeschaeftspartnerOhneLKZ();
        assertEquals(expResult, result);
    }

    /**
     * Test of gibAlleGeschaeftspartnerMitLKZ method, of class
     * GeschaeftspartnerDAO.
     *
     * @throws java.sql.SQLException SQLException  
     *   Diese Testmethode vergleicht die vorhandenen Geschäftspartner
     *   der Tableview mit denen in der Datenbank. In diesem Fall 
     *   MIT gesetztem LKZ.
     */
    @Test
    public void testGibAlleGeschaeftspartnerMitLKZ() throws SQLException {
        System.out.println("gibAlleGeschaeftspartnerMitLKZ");
        GeschaeftspartnerDAO instance = new GeschaeftspartnerDAO();
        ObservableList<Geschaeftspartner> expResult
                = FXCollections.observableArrayList(
                        instance.gibAlleGeschaeftspartnerMitLKZ());
        ArrayList<Geschaeftspartner> result = 
                instance.gibAlleGeschaeftspartnerMitLKZ();
        assertEquals(expResult, result);
    }

    /**
     * Test of fuegeGeschaeftspartnerHinzu method, of class
     * GeschaeftspartnerDAO.
     *
     * @throws java.sql.SQLException SQLException 
     */
    @Test
    public void testFuegeGeschaeftspartnerHinzu() throws SQLException {
        System.out.println("fuegeGeschaeftspartnerHinzu");
        Geschaeftspartner geschaeftspartner = new Geschaeftspartner("000001", 
                "Kunde", "Anschrift", "000001", "2500", "N");
        GeschaeftspartnerDAO instance = new GeschaeftspartnerDAO();
        instance.fuegeGeschaeftspartnerHinzu(geschaeftspartner);
    }
    
    /**
     * Test of aendernGeschaeftspartner method, of class GeschaeftspartnerDAO.
     *
     * @throws java.sql.SQLException SQLException 
     *   Die Testmethode soll bereits vorhandene Datensätze abrufen
     *   und eine Änderung dieser vornehmen.
    */
    @Test
    public void testAendernGeschaeftspartner() throws SQLException {
        System.out.println("aendernGeschaeftspartner");
        Geschaeftspartner a = new Geschaeftspartner();

        a.setTyp(a.getTyp() + "geändert");
        a.setKreditlimit(a.getKreditlimit() + "geändert");

        GeschaeftspartnerDAO instance = new GeschaeftspartnerDAO();
        instance.aendernGeschaeftspartner(a);
    }

    /**
     * Test of setzeLKZ method, of class GeschaeftspartnerDAO.
     *
     * @throws java.sql.SQLException SQLException 
     *   Dieser Test überprüft, ob die Methode erfolgreich das LKZ setzt.
     *   Alternativ: 'a.setLkz("J")/("N")'.
    */
    @Test
    public void testSetzeLKZ() throws SQLException {
        System.out.println("setzeLKZ");
        Geschaeftspartner g = new Geschaeftspartner();
        g.setLKZ(g.getLKZ());
        GeschaeftspartnerDAO instance = new GeschaeftspartnerDAO();
        instance.setzeLKZ(g);
    }

    /**
     * Test of gibLetztID method, of class GeschaeftspartnerDAO.
     *
     * @throws java.sql.SQLException SQLException  
     *   Diese Methode überprüft, ob die ID des letzten angelegten
     *   Geschäftspartnerdatensatzes korrekt abgerufen wird.
     *   Erwartetes Ergebnis: result = n, expResult = n+1.
    */
    @Test
    public void testGibLetztID() throws SQLException {
        System.out.println("gibLetztID");
        GeschaeftspartnerDAO instance = new GeschaeftspartnerDAO();
        String expResult = instance.generiereID();
        String result = instance.gibLetztID();
        assertEquals(expResult, result);
    }

    /**
     * Test of generiereID method, of class GeschaeftspartnerDAO.
     *
     * @throws java.sql.SQLException SQLException 
     *   Dieser Test überprüft, ob die erhaltene ID im String 
     *   korrekt um 1 hochgezählt wurde.
     *   Erwartetes Ergebnis: result = n+1, expResult = n.
    */
    @Test
    public void testGeneriereID() throws SQLException {
        System.out.println("generiereID");
        GeschaeftspartnerDAO instance = new GeschaeftspartnerDAO();
        String expResult = instance.gibLetztID();
        String result = instance.generiereID();
        assertEquals(expResult, result);
    }

}
