/*------------------------------------------------------------------------------
* Klasse: DataAccess.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zum Verbindungsaufbau zur Datenbank. Weiterhin schließt
*   sie die Verbindung.
*-------------------------------------------------------------------------------
* Historie:
* 26.07.2017    HEN     Erstellt.
* 28.07.2017    CEL     Erweitert.
* 12.08.2017    HEN     Methode datenbankVerbindung() angepasst.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import java.io.File;
import java.io.IOException;
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
//    private String datenbankURL = erzeugeDatenbankPfad();
    String relativerPfad;

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
    /* 12.08.17    HEN     Alert mit SQLException erstellt. 
    */
    /*------------------------------------------------------------------------*/
    
    /**
     * Baut Verbindung zur Datenbank auf.
     * @return Connection
     * @throws SQLException SQL Exception
     */
    private Connection datenbankVerbindung() throws SQLException {
        try {
            con = DriverManager.getConnection(datenbankURL, 
                    benutzername, passwort);
            
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\nDie Datenbankverbindung "
                + "konnte nicht hergestellt werden.");
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

            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Fehler");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
    

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 15.09.17    Hen     Methode erstellt.
    /*------------------------------------------------------------------------*/
    /**
     * Gib den Pfad, wo das Programm ausgeführt wird, an.
     * @return Datenbankpfad der Datenbank
     */
    public String erzeugeDatenbankPfad() {
        //holt den Datenbankpfad zur Laufzeit
        try {
            this.relativerPfad = new File(".").getCanonicalPath();
            //Fehler wird gefangen
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Datenbankpfad konnte nicht"
                + " erzeugt werden.");
            alert.showAndWait();
        }

        datenbankURL 
            = "jdbc:derby:" + relativerPfad + "/DBTobi";
        
        return datenbankURL;
    }
  
}
