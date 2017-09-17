/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
     */
    @Test
    public void testGibLetztID() throws Exception {
        System.out.println("gibLetztID");
        String posNr = "001";
        AuftragspositionDAO instance = new AuftragspositionDAO();
        String expResult = "001";
        String result = instance.gibLetztID(posNr);
        assertEquals(expResult, result);
    }

    /**
     * Test of generiereID method, of class AuftragspositionDAO.
     */
    @Test
    public void testGeneriereID() throws Exception {
        System.out.println("generiereID");
        String posNr = "001";
        AuftragspositionDAO instance = new AuftragspositionDAO();
        String expResult = "002";
        String result = instance.generiereID(posNr);
        assertEquals(expResult, result);
    }

    /**
     * Test of fuegeAuftragspositionHinzu method, of class AuftragspositionDAO.
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
        String auftragskopfID = "000033";
        String artikelID = "999999";
        int menge = 1;
        AuftragspositionDAO instance = new AuftragspositionDAO();
        String expResult = "2";
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
        String auftragsID = "000002";
        AuftragspositionDAO instance = new AuftragspositionDAO();
        String expResult = "1500.0";
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
        String expResult = "";
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
        String auftragskopfID = "000033";
        String artikelID = "999999";
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
     */
    @Test
    public void testAendereAuftragsposition() throws Exception {
        System.out.println("aendereAuftragsposition");
        Auftragsposition ap = new Auftragsposition();
        
        ap.setEinzelwert("100.0");
        ap.setLkz("N");
        ap.setMenge("5");
        
        AuftragspositionDAO instance = new AuftragspositionDAO();
        instance.aendereAuftragsposition(ap);
    }
    
}
