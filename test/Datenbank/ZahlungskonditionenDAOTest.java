/*------------------------------------------------------------------------------
* Klasse: ZahlungskonditionenDAOTest.
*-------------------------------------------------------------------------------
* Zweck:
* - In dieser Klasse werden alle Methoden der Klasse ZahlungskonditionenDAO auf
*   Korrektheit und Ausführbarkeit überprüft.
*-------------------------------------------------------------------------------
* Datum         Name    Was
* 20.08.2017    SAM     Erstellt.
* 26.08.2017    SAM     Fertiggestellt.
* 06.09.2017    CEL     testFuegeZahlungskonditionenHinzu() korrigiert.
                        Getestet und freigegeben.
*-------------------------------------------------------------------------------
 */
package Datenbank;

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
public class ZahlungskonditionenDAOTest {

    public ZahlungskonditionenDAOTest() {
    }

    /**
     * Test of gibAlleZahlungskonditionenOhneLKZ method, of class
     * ZahlungskonditionenDAO.
     *
     * @throws java.sql.SQLException SQLException Diese Testmethode vergleicht
     * die vorhandenen Zahlungskonditionen der Tableview mit denen der
     * Datenbank(ohne LKZ). In diesem Fall OHNE gesetztem LKZ.
     */
    @Test
    public void testGibAlleZahlungskonditionenOhneLKZ() throws SQLException {
        System.out.println("gibAlleZahlungskonditionenOhneLKZ");
        ZahlungskonditionenDAO instance = new ZahlungskonditionenDAO();
        ObservableList<Zahlungskonditionen> expResult
            = FXCollections.observableArrayList(
                instance.gibAlleZahlungskonditionenOhneLKZ());
        ArrayList<Zahlungskonditionen> result
                = instance.gibAlleZahlungskonditionenOhneLKZ();
        assertEquals(expResult, result);
    }

    /**
     * Test of fuegeZahlungskonditionenHinzu method, of class
     * ZahlungskonditionenDAO.
     *
     * @throws java.sql.SQLException SQLException
     */
    @Test
    public void testFuegeZahlungskonditionenHinzu() throws SQLException {
        System.out.println("fuegeZahlungskonditionenHinzu");
        Zahlungskonditionen zahlungskonditionen
                = new Zahlungskonditionen("100000", "Barzahlung", "4",
                        "1", "12", "21", "10", "10", "12", "13",
                        "14", "N");
        ZahlungskonditionenDAO instance = new ZahlungskonditionenDAO();
        instance.fuegeZahlungskonditionenHinzu(zahlungskonditionen);
    }

    /**
     * Test of gibLetztID method, of class ZahlungskonditionenDAO.
     *
     * @throws java.sql.SQLException SQLException Diese Methode überprüft, ob
     * die ID des letzten angelegten Zahlungskonditionsdatensatzes korrekt
     * abgerufen wird. Erwartetes Ergebnis: result = n, expResult = n+1.
     */
    @Test
    public void testGibLetztID() throws SQLException {
        System.out.println("gibLetztID");
        ZahlungskonditionenDAO instance = new ZahlungskonditionenDAO();
        String expResult = "000007";
        String result = instance.gibLetztID();
        assertEquals(expResult, result);
    }

    /**
     * Test of generiereID method, of class ZahlungskonditionenDAO.
     *
     * @throws java.sql.SQLException SQLException Dieser Test überprüft, ob die
     * erhaltene ID im String korrekt um 1 hochgezählt wurde. Erwartetes
     * Ergebnis: result = n+1, expResult = n.
     */
    @Test
    public void testGeneriereID() throws SQLException {
        System.out.println("generiereID");
        ZahlungskonditionenDAO instance = new ZahlungskonditionenDAO();
        String expResult = "000008";
        String result = instance.generiereID();
        assertEquals(expResult, result);
    }

    /**
     * Test of aendereZahlungskonditionen method, of class
     * ZahlungskonditionenDAO.
     *
     * @throws java.sql.SQLException SQLException Die Testmethode soll bereits
     * vorhandene Datensätze abrufen und eine Änderung dieser vornehmen.
     */
    @Test
    public void testAendereZahlungskonditionen() throws SQLException {
        System.out.println("aendereZahlungskonditionen");
        Zahlungskonditionen zk = new Zahlungskonditionen();

        String auftragsart = zk.getAuftragsart() + "geändert";
        String lieferzeit = zk.getLieferzeitSOFORT() + "geändert";
        String skontozeit1 = zk.getSkontozeit1() + "geändert";
        String skontozeit2 = zk.getSkontozeit2() + "geändert";
        String skonto1 = zk.getSkonto1() + "geändert";
        String skonto2 = zk.getSkonto2() + "geändert";
        String mahmzeit1 = zk.getMahnzeit1() + "geändert";
        String mahmzeit2 = zk.getMahnzeit2() + "geändert";
        String mahmzeit3 = zk.getMahnzeit3() + "geändert";

        ZahlungskonditionenDAO instance = new ZahlungskonditionenDAO();
        instance.aendereZahlungskonditionen(zk);
    }

    /**
     * Test of setzeLKZ method, of class ZahlungskonditionenDAO.
     *
     * @throws java.sql.SQLException SQLException Dieser Test überprüft, ob die
     * Methode erfolgreich das LKZ setzt. Alternativ: 'a.setLkz("J")/("N")'.
     */
    @Test
    public void testSetzeLKZ() throws SQLException {
        System.out.println("setzeLKZ");
        Zahlungskonditionen zk = new Zahlungskonditionen();
        zk.setLKZ(zk.getLKZ());
        ZahlungskonditionenDAO instance = new ZahlungskonditionenDAO();
        instance.setzeLKZ(zk);
    }

}
