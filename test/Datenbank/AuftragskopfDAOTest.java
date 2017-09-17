/*------------------------------------------------------------------------------
* Klasse: AuftragskopfDAOTest.
*-------------------------------------------------------------------------------
* Zweck:
* - In dieser Klasse werden alle Methoden der Klasse AuftragskopfDAO auf
*   Korrektheit und Ausführbarkeit überprüft.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 20.08.2017    CEL     Erstellt.
* 26.08.2017    CEL     Fertiggestellt.
* 17.09.2017    CEL     Testmethoden hinzugefügt.
                        Getestet und freigegeben.
*-------------------------------------------------------------------------------
 */
package Datenbank;

import Klassen.Auftragskopf;
import Klassen.Geschaeftspartner;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chakir
 */
public class AuftragskopfDAOTest {

    public AuftragskopfDAOTest() {
    }

    /**
     * Test of gibAlleAuftragskoepfeOhneLKZ method, of class AuftragskopfDAO.
     *
     * @throws java.lang.Exception Exception
     */
    /*  
    *   Diese Testmethode vergleicht die vorhandenen Auftragsköpfe der Tableview
    *   mit denen in der Datenbank. In diesem Fall OHNE gesetztem LKZ.
     */
    @Test
    public void testGibAlleAuftragskoepfeOhneLKZ() throws Exception {
        System.out.println("gibAlleAuftragskoepfeOhneLKZ");
        AuftragskopfDAO instance = new AuftragskopfDAO();
        ObservableList<Auftragskopf> expResult
                = FXCollections.observableArrayList(
                        instance.gibAlleAuftragskoepfeOhneLKZ());
        ArrayList<Auftragskopf> result
                = instance.gibAlleAuftragskoepfeOhneLKZ();
        assertEquals(expResult, result);
    }

    /**
     * Test of setzeLKZ method, of class AuftragskopfDAO.
     *
     * @throws java.lang.Exception Exception
     */
    /*
    *   Dieser Test überprüft, ob die Methode erfolgreich das LKZ setzt.
    *   Alternativ: 'a.setLkz("J")/("N")'.
     */
    @Test
    public void testSetzeLKZ() throws Exception {
        System.out.println("setzeLKZ");
        Auftragskopf a = new Auftragskopf();
        a.setLkz("N");
        AuftragskopfDAO instance = new AuftragskopfDAO();
        instance.setzeLKZ(a);
    }

    /**
     * Test of fuegeAuftragHinzu method, of class AuftragskopfDAO.
     *
     * @throws java.lang.Exception Exception
     */
    @Test
    public void testFuegeAuftragHinzu() throws Exception {
        System.out.println("fuegeAuftragHinzu");
        Auftragskopf auftragskopf = new Auftragskopf("", "000001",
                "Auftragstext", "20.09.2017", "23.01.2017", "30.09.2017",
                "", "", "1500.0", "N");
        AuftragskopfDAO instance = new AuftragskopfDAO();
        instance.fuegeAuftragHinzu(auftragskopf);
    }

    /**
     * Test of aendereAuftragskopf method, of class AuftragskopfDAO.
     *
     * @throws java.lang.Exception Exception Die Testmethode soll bereits
     * vorhandene Datensätze abrufen und eine Änderung dieser vornehmen.
     */
    @Test
    public void testAendereAuftragskopf() throws Exception {
        System.out.println("aendereAuftragskopf");
        Auftragskopf a = new Auftragskopf();

        a.setAuftragstext(a.getAuftragstext() + "geändert");
        a.setErfassungsdatum("01.01.2017");
        a.setLieferdatum("10.01.2017");
        a.setAbschlussDatum("20.01.2017");
        a.setAuftragsart(a.getAuftragsart());
        a.setAuftragswert("1000");

        AuftragskopfDAO instance = new AuftragskopfDAO();
        instance.aendereAuftragskopf(a);
    }

    /**
     * Test of gibLetztID method, of class AuftragskopfDAO.
     *
     * @throws java.lang.Exception Exception Diese Methode überprüft, ob die ID
     * des letzten angelegten Auftragskopfdatensatzes korrekt abgerufen wird.
     * Erwartetes Ergebnis: result = n, expResult = n+1.
     */
    @Test
    public void testGibLetztID() throws Exception {
        System.out.println("gibLetztID");
        AuftragskopfDAO instance = new AuftragskopfDAO();
        String expResult = "000009";
        String result = instance.gibLetztID();
        assertEquals(expResult, result);
    }

    /**
     * Test of generiereID method, of class AuftragskopfDAO.
     *
     * @throws java.lang.Exception Exception Dieser Test überprüft, ob die
     * erhaltene ID im String korrekt um 1 hochgezählt wurde. Erwartetes
     * Ergebnis: result = n+1, expResult = n.
     */
    @Test
    public void testGeneriereID() throws Exception {
        System.out.println("generiereID");
        AuftragskopfDAO instance = new AuftragskopfDAO();
        String expResult = "000010";
        String result = instance.generiereID();
        assertEquals(expResult, result);
    }

    /**
     * Test of gibAuftragsstatus method, of class AuftragskopfDAO.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGibAuftragsstatus() throws Exception {
        System.out.println("gibAuftragsstatus");
        String auftragskopfID = "000001";
        AuftragskopfDAO instance = new AuftragskopfDAO();
        String expResult = "A";
        String result = instance.gibAuftragsstatus(auftragskopfID);
        assertEquals(expResult, result);
    }

    /**
     * Test of gibAuftragswert method, of class AuftragskopfDAO.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGibAuftragswert() throws Exception {
        System.out.println("gibAuftragswert");
//        Auftragskopf ak = new Auftragskopf();
        String auftragskopfID = "000001";
        AuftragskopfDAO instance = new AuftragskopfDAO();
        String expResult = "90.42";
        String result = instance.gibAuftragswert(auftragskopfID);
        assertEquals(expResult, result);
    }

    /**
     * Test of gibAlleGeschaeftspartnerKunde method, of class AuftragskopfDAO.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGibAlleGeschaeftspartnerKunde() throws Exception {
        System.out.println("gibAlleGeschaeftspartnerKunde");
        AuftragskopfDAO instance = new AuftragskopfDAO();
        ObservableList<Auftragskopf> expResult
                = FXCollections.observableArrayList(
                        instance.gibAlleGeschaeftspartnerKunde());
        ArrayList<Auftragskopf> result 
                = instance.gibAlleGeschaeftspartnerKunde();
        assertEquals(expResult, result);
    }

    /**
     * Test of gibAlleGeschaeftspartnerLieferant method, of class
     * AuftragskopfDAO.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGibAlleGeschaeftspartnerLieferant() throws Exception {
        System.out.println("gibAlleGeschaeftspartnerLieferant");
        AuftragskopfDAO instance = new AuftragskopfDAO();
        ObservableList<Auftragskopf> expResult
                = FXCollections.observableArrayList(
                        instance.gibAlleGeschaeftspartnerLieferant());
        ArrayList<Auftragskopf> result
                = instance.gibAlleGeschaeftspartnerLieferant();
        assertEquals(expResult, result);
    }

    /**
     * Test of gibGeschaeftspartnerTyp method, of class AuftragskopfDAO.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGibGeschaeftspartnerTyp() throws Exception {
        System.out.println("gibGeschaeftspartnerTyp");
        Geschaeftspartner gp = new Geschaeftspartner();
        String gpID = "000001";
        AuftragskopfDAO instance = new AuftragskopfDAO();
        String expResult = "K";
        String result = instance.gibGeschaeftspartnerTyp(gpID);
        assertEquals(expResult, result);
    }

}
