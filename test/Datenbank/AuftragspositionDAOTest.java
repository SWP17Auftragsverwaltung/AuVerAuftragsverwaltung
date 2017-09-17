/*------------------------------------------------------------------------------
* Klasse: AuftragspositionDAOTest.
*-------------------------------------------------------------------------------
* Zweck:
* - In dieser Klasse werden alle Methoden der Klasse AuftragspositionDAO auf
*   Korrektheit und Ausführbarkeit überprüft.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 15.09.2017    CEL     Erstellt.
* 17.09.2017    CEL     Fertiggestellt, getestet und freigegeben.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Auftragsposition;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chakir
 */
public class AuftragspositionDAOTest {
    
    public AuftragspositionDAOTest() {
    }
    

    /**
     * Test of gibAlleAuftragspositionenOhneLKZ method, of class AuftragspositionDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testGibAlleAuftragspositionenOhneLKZ() throws Exception {
        System.out.println("gibAlleAuftragspositionenOhneLKZ");
        AuftragspositionDAO instance = new AuftragspositionDAO();
        ObservableList<Auftragsposition> expResult
            = FXCollections.observableArrayList(
                    instance.gibAlleAuftragspositionenOhneLKZ());
        ArrayList<Auftragsposition> result = instance.gibAlleAuftragspositionenOhneLKZ();
        assertEquals(expResult, result);
    }

    /**
     * Test of gibLetztID method, of class AuftragspositionDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testGibLetztID() throws Exception {
        System.out.println("gibLetztID");
        Auftragsposition ap = new Auftragsposition();
        String posNr = ap.getPositionsnummer();
        AuftragspositionDAO instance = new AuftragspositionDAO();
        String expResult = ap.getPositionsnummer();
        String result = instance.gibLetztID(posNr);
        assertEquals(expResult, result);
    }

    /**
     * Test of generiereID method, of class AuftragspositionDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testGeneriereID() throws Exception {
        System.out.println("generiereID");
        Auftragsposition ap = new Auftragsposition();
        String posNr = ap.getPositionsnummer();
        AuftragspositionDAO instance = new AuftragspositionDAO();
        String expResult = instance.generiereID(ap.getPositionsnummer());
        String result = instance.generiereID(posNr);
        assertEquals(expResult, result);
    }

    /**
     * Test of fuegeAuftragspositionHinzu method, of class AuftragspositionDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testFuegeAuftragspositionHinzu() throws Exception {
        System.out.println("fuegeAuftragspositionHinzu");
        Auftragsposition ap = new Auftragsposition("", "001", "000001", "2", "100", "N");
        AuftragspositionDAO instance = new AuftragspositionDAO();
        instance.fuegeAuftragspositionHinzu(ap);
    }

    /**
     * Test of berechneArtikelwert method, of class AuftragspositionDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testBerechneArtikelwert() throws Exception {
        System.out.println("berechneArtikelwert");
        String auftragskopfID = "000001";
        String artikelID = "000018";
        int menge = 2;
        AuftragspositionDAO instance = new AuftragspositionDAO();
        String expResult = "4";
        String result = instance.berechneArtikelwert(auftragskopfID, artikelID, menge);
        assertEquals(expResult, result);
    }

    /**
     * Test of berechneAuftragswert method, of class AuftragspositionDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testBerechneAuftragswert() throws Exception {
        System.out.println("berechneAuftragswert");
        double wert = 300.0;
        String auftragsID = "000033";
        AuftragspositionDAO instance = new AuftragspositionDAO();
        instance.berechneAuftragswert(wert, auftragsID);
    }

    /**
     * Test of setzeAuftragswert method, of class AuftragspositionDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetzeAuftragswert() throws Exception {
        System.out.println("setzeAuftragswert");
        String auftragswert = "100.0";
        String auftragsID = "000033";
        AuftragspositionDAO instance = new AuftragspositionDAO();
        instance.setzeAuftragswert(auftragswert, auftragsID);
    }

    /**
     * Test of gibAuftragswert method, of class AuftragspositionDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testGibAuftragswert() throws Exception {
        System.out.println("gibAuftragswert");
        String auftragsID = "000001";
        AuftragspositionDAO instance = new AuftragspositionDAO();
        String expResult = "90.42";
        String result = instance.gibAuftragswert(auftragsID);
        assertEquals(expResult, result);
    }

    /**
     * Test of gibPositionsMenge method, of class AuftragspositionDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testGibPositionsMenge() throws Exception {
        System.out.println("gibPositionsMenge");
        String positionsnummer = "001";
        AuftragspositionDAO instance = new AuftragspositionDAO();
        String expResult = "2";
        String result = instance.gibPositionsMenge(positionsnummer);
        assertEquals(expResult, result);
    }

    /**
     * Test of gibAuftragspositionenZuAuftrag method, of class AuftragspositionDAO.
     * @throws java.lang.Exception
     */
//    @Test
//    public void testGibAuftragspositionenZuAuftrag() throws Exception {
//        System.out.println("gibAuftragspositionenZuAuftrag");
//        String auftragsID = "000033";
//        AuftragspositionDAO instance = new AuftragspositionDAO();
//        ArrayList<Auftragsposition> expResult = null;
//        ArrayList<Auftragsposition> result = instance.gibAuftragspositionenZuAuftrag(auftragsID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of artikelVorhanden method, of class AuftragspositionDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testArtikelVorhanden() throws Exception {
        System.out.println("artikelVorhanden");
        String auftragskopfID = "000001";
        String artikelID = "000018";
        AuftragspositionDAO instance = new AuftragspositionDAO();
        boolean expResult = true;
        boolean result = instance.artikelVorhanden(auftragskopfID, artikelID);
        assertEquals(expResult, result);
    }

    /**
     * Test of setzeAuftragsposLKZ method, of class AuftragspositionDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetzeAuftragsposLKZ() throws Exception {
        System.out.println("setzeAuftragsposLKZ");
        Auftragsposition ap = new Auftragsposition();
        ap.setLkz("N");
        AuftragspositionDAO instance = new AuftragspositionDAO();
        instance.setzeAuftragsposLKZ(ap);
    }

    /**
     * Test of aendereAuftragsposition method, of class AuftragspositionDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testAendereAuftragsposition() throws Exception {
        System.out.println("aendereAuftragsposition");
        Auftragsposition ap = new Auftragsposition();
        String positionsnummer = "001";
        ap.setEinzelwert("37.99");
        ap.setLkz("N");
        ap.setMenge("1");
        
        AuftragspositionDAO instance = new AuftragspositionDAO();
        instance.aendereAuftragsposition(ap);
    }
    
}
