/*------------------------------------------------------------------------------
* Klasse: DataAccess.
*-------------------------------------------------------------------------------
* Zweck:
* - Diese Klasse dient zum Verbindungsaufbau zur Datenbank. Weiterhin schließt
*   sie die Verbindung und erzeugt einen Pfad zur Datenbank.
*-------------------------------------------------------------------------------
* Historie:
* 2017-06-26 HEN Erstellt.
*-------------------------------------------------------------------------------
*/
package Datenbank;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Connection con = null;
    /**
     *Variable für den Derby Treiber.
     */
    private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    /**
     *Variable für den Benutzernamen.
     */
    private String benutzername = "Benutzer";
    /**
     *Variable für das Benutzerpasswort.
     */
    private String passwort = "555nase";
    /**
     *Variable für die Datenbank URL. Wird in einer Methode dynamisch erzeugt.
     */
    private String datenbankURL = erzeugeDatenbankPfad();
    /**
     *Variable für den Datenbankpfad.
     */
    private String datenbankPfad;

    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 23.06.17    Hen     Konstruktor DataAccess erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Konstruktor der DateAccess Klasse.
     */
    public DataAccess() {
        datenbankVerbindung();
    }

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 23.06.17    Hen     Methode datenbankVerbindung erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Baut Verbindung zur Datenbank auf.
     */
    private void datenbankVerbindung() {
        /*Treiber laden und überprüfen inkl. Fehlerabfangen*/
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText("Kein gültiger Treiber!");
            alert.showAndWait();
            e.printStackTrace();
        }
        /*Versuchen, eine Verbindung zur Datenbank aufzubauen. Bei Fehler eine
         * Exception werfen.
         */
        try {
            con = DriverManager.getConnection(datenbankURL, benutzername,
                    passwort);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText("Es ist ein Fehler aufgetreten");
            alert.showAndWait();

            System.err.println("Datenbankverbidungsfehler");
            e.printStackTrace();
        }
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
                System.err.println("SQLException: " + e.getMessage());
            }
        }
    }

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 23.06.17    Hen     Methode erzeugeDatenbankPfad erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Gibt den Pfad an, wo das Programm ausgeführt wird. Dieser Pfad ist auch
     * für die Datenbank zu verwenden.
     *
     * @return Datenbankpfad der Datenbank
     */
    public String erzeugeDatenbankPfad() {
        //Datenbankpfad wird zur Laufzeit erzeugt
        //DD muss sich im gleichen Ordner wie die ausführbare Datei befinden
        try {
            this.datenbankPfad = new File(".").getCanonicalPath();
            //Fehler wird gefangen.
        } catch (IOException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        datenbankURL = "jdbc:derby:" + datenbankPfad + "/Auftragsverwaltung";
        return datenbankURL;
    }
}
