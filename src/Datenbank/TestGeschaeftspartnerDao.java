/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datenbank;

import Klassen.Geschaeftspartner;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author Mudimbi
 */
public class TestGeschaeftspartnerDao {
    
    public static Geschaeftspartner searchGeschaeftspartner (String gepId) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM Geschaeftspartner WHERE geschaeftspartner_id="+gepId;
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            //die dbExecuteQuery befindet sich normalerweise im DBUtil
            ResultSet rsGep = DBUtil.dbExecuteQuery(selectStmt);
 
            /*
            Send ResultSet to the getGeschaeftspartnerFromResultSet method and get Geschaeftspartner object
            */
            Geschaeftspartner geschaeftspartner = getGeschaeftspartnerFromResultSet(rsGep);
 
            //Return geschaeftspartner object
            return geschaeftspartner;
        } catch (SQLException e) {
            System.out.println("While searching an geschaeftspartner with " + gepId + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
    
    //Mit dem ResultSet holen wir den Geschaeftspartner aus der DB
    private static Geschaeftspartner getGeschaeftspartnerFromResultSet(ResultSet rs) throws SQLException
    {
        Geschaeftspartner gep = null;
        if (rs.next()) {
            gep = new Geschaeftspartner();
            gep.setGeschaeftspartnerID(rs.getInt("geschaeftspartnerID"));
            gep.setAdresseID(rs.getInt("adresseID"));
            gep.setLieferID(rs.getString("lieferID"));
            gep.setTyp(rs.getString("typ"));
            gep.setKreditlimit(rs.getInt("kreditlimit"));
            gep.setLKZ(rs.getBoolean("lkz"));
        }
        return gep;
    }
 
    //*******************************
    //SELECT Geschaeftspartner
    //*******************************
    public static ObservableList<Geschaeftspartner> searchGeschaeftspartner () throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM Geschaeftspartner";
 
  /*      
        try {
            ResultSet rsGeps = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Geschaeftspartner> gepList = getGeschaeftspartnerList(rsGeps);
            return gepList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
        */
    }
 
    
    //*************************************
    //DELETE an Geschaeftspartner
    //DBUtil ist die Klasse wo sich der QueryManager befindet
    //Zudem befindet sich in der DBUtil die Verbindung/en zu der Datenbank
    //Die DBUtil habe ich noch nicht erstellt, deshalb erscheint die Fehlermeldung
    //*************************************
    public static void deleteGepWithId (String gepId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "   DELETE FROM geschaeftspartner\n" +
                        "         WHERE geschaeftspartnerID ="+ gepId +";\n" +
                        "   COMMIT;\n" +
                        "END;";
 
        //Die Datenbank wird Upgedated weil eine Aenderung durchgeführt wurde
/*        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
*/
    }

    private static ObservableList<Geschaeftspartner> getGeschaeftspartnerList(ResultSet rsGeps) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}

