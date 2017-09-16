/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datenbank;

import Klassen.Zahlungskonditionen;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Andre
 */
public class AuftragskonditionsDAO extends DataAccess {


    /**
     * Erzeugt ein neues DataDictionaryDAO Objekt.
     */
    private DataDictionaryDAO ddd = new DataDictionaryDAO();
    
    /**
     * Holt den Tabellennamen für die Artikel zur Laufzeit.
     */
    private String TAB_AUFTRAGSKONDITIONEN = ddd.getTAB_AUFTRAGSKONDITIONEN();
    
    /**
     * Holt den Tabellennamen für die Artikel zur Laufzeit.
     */
    private String TAB_ZAHLUNGSKONDITIONEN = ddd.getTAB_ZAHLUNGSKONDITIONEN();
    
    /**
     * HashMap für die Attribute der Tabelle Artikel.
     */
    private HashMap<String, ArrayList> attribute;    
    
    /**
     * Konstruktor.
     * @throws SQLException SQLException. 
     */
    public AuftragskonditionsDAO() throws SQLException {
        attribute = ddd.getTabellenAttribute();
        ddd.holeAlleAttribute(TAB_AUFTRAGSKONDITIONEN);
        ddd.holeAlleAttribute(TAB_ZAHLUNGSKONDITIONEN);
    }
        
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 16.09.17    BER     Erstellt.
    /*------------------------------------------------------------------------*/
  
    /**
     * Gibt alle Artikel wieder die sich in der Datenbank befinden.
     * @param auftragskopfID Auftragskopf für den die Zahlungskondition 
     *                       ausgegeben werden soll.
     * @return Gibt die passende Zahlungskondition wieder.
     * @throws java.sql.SQLException SQLFehler
    */
    public Zahlungskonditionen gibKonditionZuAuftrag(String auftragskopfID) 
        throws SQLException { 
        //Variablendeklaration
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Zahlungskonditionen zahlungskondition = null;
        
        String query = "SELECT ROOT." + ddd.getTabZahlungskonditionen() + ".*"
            + " FROM ROOT." + ddd.getTabAuftragskonditionen()
            + " JOIN ROOT." + ddd.getTabZahlungskonditionen()
            + " ON ROOT." + ddd.getTabAuftragskonditionen() + "." 
            + attribute.get(TAB_AUFTRAGSKONDITIONEN).get(1) + " = ROOT." 
            + ddd.getTabZahlungskonditionen() + "."
            + attribute.get(TAB_ZAHLUNGSKONDITIONEN).get(0)      
            + " AND ROOT." + ddd.getTabAuftragskonditionen() 
            + "." + attribute.get(TAB_AUFTRAGSKONDITIONEN).get(0) 
            + " = ?";
              
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, auftragskopfID);
            rs = stmt.executeQuery();
            con.commit();
            
            while (rs.next()) {
                zahlungskondition = new Zahlungskonditionen(rs.getString(1), 
                    rs.getString(2), rs.getString(3), rs.getString(4), 
                    rs.getString(5), rs.getString(6), rs.getString(7), 
                    rs.getString(8), rs.getString(9), rs.getString(10), 
                    rs.getString(11), rs.getString(12));
            }
                             
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Fehler");
            alert.setHeaderText(e.getMessage() + "\n Die Zahlungskondition "
                + "zum Auftrag " + auftragskopfID + "konnt nicht abgerufen "
                + "werden.");
            alert.showAndWait();
            con.rollback();
        }
        return zahlungskondition;
    }      
}