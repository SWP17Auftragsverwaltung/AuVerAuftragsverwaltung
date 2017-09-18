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
* 01.00.2017    CEL     testGibLetztID()&testGeneriereID() Methoden angepasst.
                        Getestet und freigegeben.
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
        String expResult = "000024";
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
        a.setHausnummer("54321");
        a.setOrt("NewOrt");
        a.setStaat("neuerStaat");
        a.setTelefon(a.getTelefon() + "9");
        a.setEmail("test@test.com");        
        
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
        a.setLkz("N");
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
        String expResult = "000025";
        String result = instance.generiereID();
        assertEquals(expResult, result);
    }
    
}
 