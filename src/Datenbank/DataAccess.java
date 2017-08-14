/*------------------------------------------------------------------------------
* Klasse: DataAccess.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zum Verbindungsaufbau zur Datenbank. Weiterhin schließt
*   sie die Verbindung und erzeugt einen Pfad zur Datenbank.
*-------------------------------------------------------------------------------
* Historie:
* 26.07.2017    HEN     Erstellt.
* 28.07.2017    CEL     Erweitert.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Andre
 */
public class DataAccess {

    /**
     *Variable für die Verbindung zur Datenbank.
     */
    public Connection con = null;

    /**
     *Variable für den Benutzernamen.
     */
    private String benutzername = "root";
    
    /**
     *Variable für das Benutzerpasswort.
     */
    private String passwort = "KauVer";
    
    /**
     *Variable für die Datenbank URL. Wird in einer Methode dynamisch erzeugt.
     */
    private String datenbankURL = "jdbc:derby://localhost:1527/SWPWI2017";


    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 23.06.17    Hen     Konstruktor DataAccess erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Konstruktor der DateAccess Klasse.
     * @throws java.sql.SQLException SQL Exception
     */
    public DataAccess() throws SQLException {
        datenbankVerbindung();
    }

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 23.06.17    Hen     Methode datenbankVerbindung erstellt.
    /* 24.06.17    CEL     'try-catch'-Block erweitert.
    */
    /*------------------------------------------------------------------------*/
    
    /**
     * Baut Verbindung zur Datenbank auf.
     * @return Connection
     * @throws SQLException SQL Exception
     */
    private Connection datenbankVerbindung() throws SQLException {
        /*Treiber laden und überprüfen inkl. Fehlerabfangen*/
        try {
            con = DriverManager.getConnection(datenbankURL, 
                    benutzername, passwort);
            
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
        return con;
    }

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 23.06.17    Hen     Methode schliessen erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Schließt eine bestehnde Verbindung zur Datenbank.
     */
    public void schliessen() {
        /*Datenbankverbindung wird geschlossen */
        if (con != null) {
            try {
                System.out.println("Datenbankverbindung wird geschlossen.");
                con.close();

                DriverManager.getConnection(datenbankURL + ";shutdown=true",
                        benutzername, passwort);

                /*Fehler beim Verbindungsabbau wird hier gefangen */
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
}
