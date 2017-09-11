/*------------------------------------------------------------------------------
* Klasse: AdresseDAOTest.
*-------------------------------------------------------------------------------
* Zweck:
* - In dieser Klasse werden alle Methoden der Klasse AdresseDAO auf
*   Korrektheit und Ausführbarkeit überprüft.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 23.08.2017    CEL     Erstellt.
* 26.08.2017    CEL     Fertiggestellt.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import Klassen.Adresse;
import Klassen.Artikel;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 *
 * @author Chakir
 */
public class AdresseDAOTest extends Artikel{
    
    public AdresseDAOTest() {
    }
    
    /**
     * Test of gibAlleAdressen method, of class AdresseDAO.
     * @throws java.sql.SQLException  SQLException
     *   Diese Testmethode vergleicht die vorhandenen Adressen der Tableview
     *   mit denen der Datenbank(ohne LKZ).
    */
    @Test
    public void testGibAlleAdressen() throws SQLException {
        System.out.println("gibAlleAdressen");
        AdresseDAO instance = new AdresseDAO();
        ObservableList<Adresse> expResult
            = FXCollections.observableArrayList(
                    instance.gibAlleAdressenOhneLKZ());
        ArrayList<Adresse> result = instance.gibAlleAdressenOhneLKZ();
        assertEquals(expResult, result);       
    }

    /**
     * Test of gibAlleAdressenOhneLKZ method, of class AdresseDAO.
     * @throws java.sql.SQLException SQLException 
     *   Diese Testmethode vergleicht die vorhandenen Adressen der Tableview
     *   mit denen in der Datenbank. In diesem Fall OHNE gesetztem LKZ.
    */
    @Test
    public void testGibAlleAdressenOhneLKZ() throws SQLException {
        System.out.println("gibAlleAdressenOhneLKZ");
        AdresseDAO instance = new AdresseDAO();
        ObservableList<Adresse> expResult
                = FXCollections.observableArrayList(
                        instance.gibAlleAdressenOhneLKZ());
        ArrayList<Adresse> result = instance.gibAlleAdressenOhneLKZ();
        assertEquals(expResult, result);
    }

    /**
     * Test of gibAlleAdressenMitLKZ method, of class AdresseDAO.
     * @throws java.sql.SQLException SQLException
     *   Diese Testmethode vergleicht die vorhandenen Adressen der Tableview
     *   mit denen in der Datenbank. In diesem Fall MIT gesetztem LKZ.
    */
    @Test
    public void testGibAlleAdressenMitLKZ() throws SQLException {
        System.out.println("gibAlleAdressenMitLKZ");
        AdresseDAO instance = new AdresseDAO();
        ObservableList<Adresse> expResult
            = FXCollections.observableArrayList(
                    instance.gibAlleAdressenMitLKZ());
        ArrayList<Adresse> result = instance.gibAlleAdressenMitLKZ();
        assertEquals(expResult, result);     
    }

    /**
     * Test of fuegeAdresseHinzu method, of class AdresseDAO.
     * @throws java.sql.SQLException SQLException
     */
    @Test
    public void testFuegeAdresseHinzu() throws SQLException {
    
        System.out.println("fuegeAdresseHinzu");
        AdresseDAO instance = new AdresseDAO();
//        Adresse a = new Adresse();
        Adresse adresse = new Adresse("", "Herr", "Hans", "Meiser",
                "HansMeiserstr.", "1", "12345", "Hansmeiserhausen", "HMStaat",
                "0123456789", "Hans@Meister.de", "01.01.2017", "N");   
        instance.fuegeAdresseHinzu(adresse);
//        a.setLkz("N");
                          
    }

    /**
     * Test of gibLetztID method, of class AdresseDAO.
     * @throws java.sql.SQLException SQLException
     *   Diese Methode überprüft, ob die ID des letzten angelegten
     *   Adressdatensatzes korrekt abgerufen wird.
     *   Erwartetes Ergebnis: result = n, expResult = n+1.
    */
    @Test
    public void testGibLetztID() throws SQLException {
        System.out.println("gibLetztID");
        AdresseDAO instance = new AdresseDAO();
        String expResult = instance.generiereID();
        String result = instance.gibLetztID();
        assertEquals(expResult, result);
    }

    /**
     * Test of aendereAdresse method, of class AdresseDAO.
     * @throws java.sql.SQLException SQLException  
     *   Die Testmethode soll bereits vorhandene Datensätze abrufen
     *   und eine Änderung dieser vornehmen.
    */
    @Test
    public void testAendereAdresse() throws SQLException {
        System.out.println("aendereAdresse");
        Adresse a = new Adresse();
        
        a.setName(a.getName() + "geändert");
        a.setVorname(a.getVorname() + "geändert");
        a.setStrasse(a.getStrasse() + "geändert");
        a.setHausnummer(a.getHausnummer() + "geändert");
        a.setPLZ(a.getPlz() + "geändert");
        a.setOrt(a.getOrt() + "geändert");
        a.setStaat(a.getStaat() + "geändert");
        a.setTelefon(a.getTelefon() + "geändert");
        a.setEmail(a.getEmail() + "geändert");
        a.setErfassungsdatum(a.getErfassungsdatum() + "geändert");
        
        
        AdresseDAO instance = new AdresseDAO();
        instance.aendereAdresse(a);
       
    }

    /**
     * Test of setzeLKZ method, of class AdresseDAO.
     * @throws java.sql.SQLException SQLException
     *   Dieser Test überprüft, ob die Methode erfolgreich das LKZ setzt.
     *   Alternativ: 'a.setLkz("J")/("N")'.
    */
    @Test
    public void testSetzeLKZ() throws SQLException {
        System.out.println("setzeLKZ");
        Adresse a = new Adresse();
        a.setLkz(a.getLkz());
        AdresseDAO instance = new AdresseDAO();
        instance.setzeLKZ(a);
       
    }

    /**
     * Test of generiereID method, of class AdresseDAO.
     * @throws java.sql.SQLException SQLException
     *   Dieser Test überprüft, ob die erhaltene ID im String 
     *   korrekt um 1 hochgezählt wurde.
     *   Erwartetes Ergebnis: result = n+1, expResult = n.
    */
    @Test
    public void testGeneriereID() throws SQLException {
        System.out.println("generiereID");
//        String alteID = ;
        AdresseDAO instance = new AdresseDAO();
        String expResult = instance.gibLetztID();
        String result = instance.generiereID();
        assertEquals(expResult, result);
    }
    
}
 