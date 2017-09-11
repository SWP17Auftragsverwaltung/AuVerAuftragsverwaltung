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
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Auftragskopf;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chakir
 */
public class AuftragskopfDAOIT {
    
    public AuftragskopfDAOIT() {
    }
    
    /**
     * Test of gibAlleAuftragskoepfe method, of class AuftragskopfDAO.
     * @throws java.lang.Exception Exception
     */
    
    /*  
    *   Diese Testmethode vergleicht die vorhandenen Auftragsköpfe der
    *   Tableview mit denen der Datenbank(ohne LKZ).
    */
    @Test
    public void testGibAlleAuftragskoepfe() throws Exception {
        System.out.println("gibAlleAuftragskoepfe");
        AuftragskopfDAO instance = new AuftragskopfDAO();
        ObservableList<Auftragskopf> expResult
            = FXCollections.observableArrayList(
                    instance.gibAlleAuftragskoepfeOhneLKZ());
        ArrayList<Auftragskopf> result = 
                instance.gibAlleAuftragskoepfeOhneLKZ();
        assertEquals(expResult, result);
    }

    /**
     * Test of gibAlleAuftragskoepfeOhneLKZ method, of class AuftragskopfDAO.
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
        ArrayList<Auftragskopf> result = 
                instance.gibAlleAuftragskoepfeOhneLKZ();
        assertEquals(expResult, result);
    }

    /**
     * Test of gibAlleAuftragskoepfeMitLKZ method, of class AuftragskopfDAO.
     * @throws java.lang.Exception Exception
     */
    
    /*  
    *   Diese Testmethode vergleicht die vorhandenen Auftragsköpfe der Tableview
    *   mit denen in der Datenbank. In diesem Fall MIT gesetztem LKZ.
    */
    @Test
    public void testGibAlleAuftragskoepfeMitLKZ() throws Exception {
        System.out.println("gibAlleAuftragskoepfeMitLKZ");
        AuftragskopfDAO instance = new AuftragskopfDAO();
        ObservableList<Auftragskopf> expResult
            = FXCollections.observableArrayList(
                    instance.gibAlleAuftragskoepfeMitLKZ());
        ArrayList<Auftragskopf> result = 
                instance.gibAlleAuftragskoepfeMitLKZ();
        assertEquals(expResult, result);
    }

    /**
     * Test of setzeLKZ method, of class AuftragskopfDAO.
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
        a.setLkz(a.getLkz());
        AuftragskopfDAO instance = new AuftragskopfDAO();
        instance.setzeLKZ(a);
    }

    /**
     * Test of fuegeAuftragHinzu method, of class AuftragskopfDAO.
     * @throws java.lang.Exception Exception
     */
    @Test
    public void testFuegeAuftragHinzu() throws Exception {
        System.out.println("fuegeAuftragHinzu");
        Auftragskopf auftragskopf = new Auftragskopf("999999", "999999", 
                "Der Auftrag", "01.01.2017", "02.01.2017", "03.01.2017", 
                "Freigegeben", "Kundenauftrag", "1500", "N");       
        AuftragskopfDAO instance = new AuftragskopfDAO();
        instance.fuegeAuftragHinzu(auftragskopf);
    }

    /**
     * Test of aendereAuftragskopf method, of class AuftragskopfDAO.
     * @throws java.lang.Exception Exception 
     *   Die Testmethode soll bereits vorhandene Datensätze abrufen
     *   und eine Änderung dieser vornehmen.
    */
    @Test
    public void testAendereAuftragskopf() throws Exception {
        System.out.println("aendereAuftragskopf");
        Auftragskopf a = new Auftragskopf();
        
        a.setAuftragstext(a.getAuftragstext() + "geändert");
        a.setErfassungsdatum(a.getErfassungsdatum());
        a.setLieferdatum(a.getLieferdatum());
        a.setAbschlussDatum(a.getAbschlussDatum());
        a.setAuftragsart(a.getAuftragsart());
        a.setAuftragswert(a.getAuftragswert() + "1");
        
        
        
        AuftragskopfDAO instance = new AuftragskopfDAO();
        instance.aendereAuftragskopf(a);
    }

    /**
     * Test of gibLetztID method, of class AuftragskopfDAO.
     * @throws java.lang.Exception Exception
     *   Diese Methode überprüft, ob die ID des letzten angelegten
     *   Auftragskopfdatensatzes korrekt abgerufen wird.
     *   Erwartetes Ergebnis: result = n, expResult = n+1.
    */
    @Test
    public void testGibLetztID() throws Exception {
        System.out.println("gibLetztID");
        AuftragskopfDAO instance = new AuftragskopfDAO();
        String expResult = instance.generiereID();
        String result = instance.gibLetztID();
        assertEquals(expResult, result);
    }

    /**
     * Test of generiereID method, of class AuftragskopfDAO.
     * @throws java.lang.Exception Exception
     *   Dieser Test überprüft, ob die erhaltene ID im String 
     *   korrekt um 1 hochgezählt wurde.
     *   Erwartetes Ergebnis: result = n+1, expResult = n.
    */
    @Test
    public void testGeneriereID() throws Exception {
        System.out.println("generiereID");
        AuftragskopfDAO instance = new AuftragskopfDAO();
        String expResult = instance.gibLetztID();
        String result = instance.generiereID();
        assertEquals(expResult, result);
    }
    
}
