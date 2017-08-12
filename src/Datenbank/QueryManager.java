/*------------------------------------------------------------------------------
* Klasse: QueryManager.
*-------------------------------------------------------------------------------
* Zweck:
* - .
*-------------------------------------------------------------------------------
* Historie:
* 11.08.2017    BER     Erstellt.
*-------------------------------------------------------------------------------
 */
package Datenbank;

import Klassen.Adresse;
import Klassen.Artikel;
import Klassen.Auftragskopf;
import Klassen.Geschaeftspartner;
import Klassen.Zahlungskonditionen;
import Klassen.DataDictionary;
import java.util.HashMap;
import Klassen.Auftragsposition;
import java.util.ArrayList;

/**
 *
 * @author Tobias
 */
public class QueryManager {

    /**
     * Methode dient dazu, zur Laufzeit einen Insert SQL Befehl für das
     * übergebene Objekt zu erstellen.
     *
     * @param hm HashMap mit den Attributen der Klasse
     * @param tabellenname Name der Tabelle
     * @param o Übergebenes Objekt für das ein Insert String erstelt werden soll
     * @return UpdateString zum aktualisieren des übergebenen Objektes in der
     * Datenbank.
     */
    public String gibupdateQueryKomplett(HashMap<String, String> hm,
            String tabellenname, Object o) {
        /*Der update-Query Befehl wird befüllt, 
        *hierfür wird der Tabellenname eingefügt
        */
        String query = "UPDATE ROOT." + tabellenname + " SET ";

        try {
            if (o instanceof Adresse) {
                Adresse a = Adresse.class.cast(o);
                query += "" + hm.get("Vorname") + " = '" 
                        + a.getVorname() + "',";
                query += "" + hm.get("Nachname") + " = '" 
                        + a.getName() + "',";
                query += "" + hm.get("Titel") + " = '" 
                        + a.getAnrede() + "',";
                query += "" + hm.get("Strasse") + " = '" 
                        + a.getStrasse() + "',";
                query += "" + hm.get("Hausnummer") + " = '" 
                        + a.getHausnummer() + "',";
                query += "" + hm.get("PLZ") + " = '" + a.getPLZ() + "',";
                query += "" + hm.get("Ort") + " = '" + a.getOrt() + "',";
                query += "" + hm.get("Staat") + " = '" + a.getStaat() + "',";
                query += "" + hm.get("Telefon") + " = '" 
                        + a.getTelefon() + "',";
                query += "" + hm.get("eMail") + " = '" + a.geteMail() + "',";
                query += "" + hm.get("Erfassungsdatum") + " = '" 
                        + a.getErfassungsdatum() + "',";
                query += "" + hm.get("LKZ") + " = '" + a.getLKZ() + "',";
                query += " WHERE ";
                query += "" + hm.get("AdresseID") + " = '" 
                        + a.getAdresseID() + "',";

            } else if (o instanceof Artikel) {
                Artikel a = Artikel.class.cast(o);
                query += "" + hm.get("Artikeltext") + " = '" 
                        + a.getArtikeltext() + "',";
                query += "" + hm.get("Bestelltext") + " = '" 
                        + a.getBestelltext() + "',";
                query += "" + hm.get("Einzelwert") + " = '" 
                        + a.getEinzelwert() + "',";
                query += "" + hm.get("Bestellwert") + " = '" 
                        + a.getBestellwert() + "',";
                query += "" + hm.get("Steuer") + " = '" + a.getSteuer() + "',";
                query += "" + hm.get("BestandsmengeFrei") + " = '" 
                        + a.getBestandsmengeFrei() + "',";
                query += "" + hm.get("BestandsmengeReserviert") + " = '" 
                        + a.getBestandsmengeReserviert() + "',";
                query += "" + hm.get("BestandsmengeVerkauft") + " = '" 
                        + a.getBestandsmengeVerkauft() + "',";
                query += "" + hm.get("LKZ") + " = '" + a.getLKZ() + "',";
                query += " WHERE ";
                query += "" + hm.get("ArtikelID") + " = '" 
                        + a.getArtikelID() + "',";

            } else if (o instanceof Auftragskopf) {
                /*Lieferdatum und Abschlussdatum werden nicht immer ausgefüllt
                *sein, daher Unterscheidung ob jeweiliges Datum null ist.
                */
                Auftragskopf ak = Auftragskopf.class.cast(o);
                query += "" + hm.get("GeschaeftspartnerID") + " = '" 
                        + ak.getGeschaeftspartnerID() + "',";
                query += "" + hm.get("Auftragart") + " = '" 
                        + ak.getAuftragsart() + "',";
                query += "" + hm.get("Erfassungsdatum") + " = '"
                        + ak.getErfassungsdatum() + "',";
                if (ak.getLieferdatum() != null) {
                    query += "" + hm.get("LIEFERDATUM") + " = '" 
                            + ak.getLieferdatum() + "',";
                }
                query += "" + hm.get("Status") + " = '" + ak.getStatus() + "',";
                query += "" + hm.get("Auftragswert") + " = '" 
                        + ak.getAuftragswert() + "',";
                query += "" + hm.get("Auftragstext") + " = '" 
                        + ak.getAuftragstext() + "',";
                if (ak.getAbschlussDatum() != null) {
                    query += "" + hm.get("Abschlussdatum") + " = '" 
                            + ak.getAbschlussDatum() + "',";
                }
                query += "" + hm.get("LKZ") + " = '" + ak.getLKZ() + "',";
                query += " WHERE ";
                query += "" + hm.get("AuftragskopfID") + " = '" 
                        + ak.getAuftragskopfID() + "',";

            } else if (o instanceof Auftragsposition) {
                Auftragsposition ap = Auftragsposition.class.cast(o);
                query += "" + hm.get("ArtikelID") + " = '" 
                        + ap.getArtikelID() + "',";
                query += "" + hm.get("Menge") + " = '" + ap.getMenge() + "',";
                query += "" + hm.get("Einzelwert") + " = '" 
                        + ap.getEinzelwert() + "',";
                query += "" + hm.get("LKZ") + " = '" + ap.getLkz() + "',";
                query += " WHERE ";
                query += "" + hm.get("AuftragskopfID") + " = '" 
                        + ap.getAuftragskopfID() + "',";
                query += " AND ";
                query += "" + hm.get("Positionsnummer") + " = '" 
                        + ap.getPositionsnummer() + "',";

            } else if (o instanceof Geschaeftspartner) {
                Geschaeftspartner gp = Geschaeftspartner.class.cast(o);
                query += "" + hm.get("AdresseID") + " = '" 
                        + gp.getAdresseID() + "',";
                query += "" + hm.get("LieferID") + " = '" 
                        + gp.getLieferID() + "',";
                query += "" + hm.get("Typ") + " = '" + gp.getTyp() + "',";
                query += "" + hm.get("Kreditlimit") + " = '" 
                        + gp.getKreditlimit() + "',";
                query += "" + hm.get("LKZ") + " = '" + gp.getLKZ() + "',";
                query += " WHERE ";
                query += "" + hm.get("GeschaeftspartnerID") + " = '" 
                        + gp.getGeschaeftspartnerID() + "',";

            } else if (o instanceof Zahlungskonditionen) {
                Zahlungskonditionen z = Zahlungskonditionen.class.cast(o);
                query += "" + hm.get("Auftragsart") + " = '" 
                        + z.getAuftragsart() + "',";
                query += "" + hm.get("Lieferzeit") + " = '" 
                        + z.getLieferzeit() + "',";
                query += "" + hm.get("Sperrzeit") + " = '" 
                        + z.getSperrzeit() + "',";
                query += "" + hm.get("Skontozeit1") + " = '" 
                        + z.getSkontozeit1() + "',";
                query += "" + hm.get("Skontozeit2") + " = '" 
                        + z.getSkontozeit2() + "',";
                query += "" + hm.get("Skonto1") + " = '" 
                        + z.getSkonto1() + "',";
                query += "" + hm.get("Skonto2") + " = '" 
                        + z.getSkonto2() + "',";
                query += "" + hm.get("Mahnzeit1") + " = '" 
                        + z.getMahnzeit1() + "',";
                query += "" + hm.get("Mahnzeit2") + " = '" 
                        + z.getMahnzeit2() + "',";
                query += "" + hm.get("Mahnzeit3") + " = '" 
                        + z.getMahnzeit3() + "',";
                query += "" + hm.get("LKZ") + " = '" + z.getLKZ() + "',";
                query += " WHERE ";
                query += "" + hm.get("ZahlungskonditionsID") + " = '" 
                        + z.getZahlungskondiID() + "',";

            } else {
                System.out.println("Kein passendes Objekt gefunden");
            }

        } catch (ClassCastException cnexep) {
            System.out.println("Fehler beim Casten:" + cnexep.getMessage());
        }

        return query;
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 11.08.17  BER     Erstellt.
     *--------------------------------------------------------------------------
     */
    /**
     * Liefert zur Laufzeit aus dem DataDictionary das angeforderte Attribut der
     * übergebenen Tabelle.
     *
     * @param hm HashMap mit Attributen der Klassen
     * @param attributname Name des Attributes
     * @return Attributwert
     */
    public String gibAttribut(HashMap<String, String> hm, String attributname) {
        //**Attribut wird mit dem instanziert mit dem übergeben Attribut.*//
        String attribut = "";
        attribut = hm.get(attributname);
        return attribut;
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 11.08.17  BER     Erstellt.
     *--------------------------------------------------------------------------
     */
    /**
     * Liefert zur Laufzeit den SQL Befehl um alle Objekte des übergebenen
     * Tabellennamen aus der Datenbank zu holen.
     *
     * @param tabellenname Name der jeweiligen Klasse
     * @return String Query für alle Objekte in der befindlichen Tabelle.
     */
    public String gibKompletteTabelle(String tabellenname) {
        String query = "Select * FROM ROOT.";
        query += "" + tabellenname + "";
        return query;
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 11.08.17  BER     Erstellt.
     *--------------------------------------------------------------------------
     */
     /* Methode dient dazu, zur Laufzeit einen Insert SQL Befehl für das
     * übergebene Objekt zu erstellen.
     *
     * @param datadic DataDictionary ArrayList der jeweiligen Tabelle
     * @param tabellenname Name der jeweiligen Klasse
     * @param o Übergebenes Objekt für das ein Insert String erstelt werden soll
     * @return InsertString zum Hinzufügen des übergebenen Objektes in der
     * Datenbank.
     */
    public String gibInsertQuery(ArrayList<DataDictionary> datadic,
            String tabellenname, Object o) {
        //InsertQuery wird gestartet mit dem Tabellenname der befüllt wird
        String insertQuery = "INSERT INTO ROOT." + tabellenname + " (";

        //Iteration über die komplette übergebene DataDictionary ArrayList um 
        //geordnet nach der Positionsnummer die Attribute auszulesen. Attribute
        //werden mit einem Komma getrennt.
        for (DataDictionary data : datadic) {

            insertQuery += "" + data.getAttributname() + ", ";

        }
        //beim letzten Attribut wird das Komma abgetrennt
        insertQuery = insertQuery.substring(0, insertQuery.length() - 2);
        // query "übergang"
        insertQuery += ") VALUES (";

        try {
            //Das übergebene object wird mit instance of in den passenden 
            //Fall eingestuft.
            if (o instanceof Auftragskopf) {
                Auftragskopf ak = Auftragskopf.class.cast(o);
                //Bei Baraufträgen wird das Lieferdatum nicht gepflegt, 
                //ist also vom Wert null.
                //Lieferdatum wird daher aus der Liste der Attribute 
                //gelöscht, falls im übergebenen Objekt das Lieferdatum vom 
                //      Wert 'null' ist.

                insertQuery = insertQuery.replace("ABSCHLUSSDATUM,", "");

                insertQuery += "" + ak.getAuftragskopfID() + ",";
                insertQuery += "" + ak.getGeschaeftspartnerID() + ",";
                insertQuery += "" + ak.getZahlungskonditionsID() + ",";
                insertQuery += "'" + ak.getErfassungsdatum() + "',";
                if (ak.getLieferdatum() == null) {insertQuery = 
                    insertQuery.replace("LIEFERDATUM,", "");
                } else {
                    insertQuery += "'" + ak.getLieferdatum() + "',";
                }
                insertQuery += "'" + ak.getAbschlussDatum() + "',";
                insertQuery += "" + "" + false + ",";
                insertQuery += "'" + ak.getAuftragsart() + "',";
                insertQuery += "'" + ak.getStatus() + "',";
                insertQuery += "" + ak.getAuftragswert() + ",";
                insertQuery += "'" + ak.getAuftragstext() + "',";


            } else if (o instanceof Auftragsposition) {
                Auftragsposition ap = Auftragsposition.class.cast(o);
                insertQuery += "" + ap.getAuftragskopfID() + ",";
                insertQuery += "" + "" + ap.getPositionsnummer() + ",";
                insertQuery += "" + "" + ap.getLkz() + ",";
                insertQuery += "" + "" + ap.getArtikelID() + ",";
                insertQuery += "" + "" + ap.getMenge() + ",";
                insertQuery += "" + "" + ap.getEinzelwert() + ")";

            } else if (o instanceof Artikel) {
                Artikel a = Artikel.class.cast(o);

                insertQuery += " " + a.getArtikelID() + ",";
                insertQuery += "'" + a.getArtikeltext() + "',";
                insertQuery += "'" + a.getBestelltext() + "',";
                insertQuery += "" + a.getBestandsmengeFrei() + ",";
                insertQuery += "" + a.getBestandsmengeReserviert() + ",";
                insertQuery += "" + a.getBestandsmengeZulauf() + ",";
                insertQuery += "" + a.getBestandsmengeVerkauft() + ",";
                insertQuery += "" + "" + false + ",";
                insertQuery += " " + a.getSteuer() + ",";
                insertQuery += "" + a.getEinzelwert() + ",";
                insertQuery += "" + a.getBestellwert() + ")";

            } else if (o instanceof Adresse) {
                Adresse a = Adresse.class.cast(o);
                insertQuery += " " + a.getAdresseID() + ",";
                insertQuery += " '" + a.getAnrede() + "',";
                insertQuery += " '" + a.getVorname() + "',";
                insertQuery += "'" + a.getName() + "',";
                insertQuery += "'" + a.getStrasse() + "',";
                insertQuery += "'" + a.getHausnummer() + "',";
                insertQuery += "" + a.getPLZ() + ",";
                insertQuery += "'" + a.getOrt() + "',";
                insertQuery += "'" + a.getStaat() + "',";
                insertQuery += "'" + a.getTelefon() + "',";
                insertQuery += "'" + a.geteMail() + "',";
                insertQuery += "'" + a.getErfassungsdatum() + "',";
                insertQuery += "" + "" + false + ",";

            } else if (o instanceof Zahlungskonditionen) {
                Zahlungskonditionen z = Zahlungskonditionen.class.cast(o);
                insertQuery += "" + z.getZahlungskondiID() + " ,";
                insertQuery += "'" + z.getAuftragsart() + "', ";
                insertQuery += "" + z.getLieferzeit() + " ,";
                insertQuery += "" + z.getSperrzeit() + " ,";
                insertQuery += "" + false + " ,";
                insertQuery += "" + z.getSkontozeit1() + " ,";
                insertQuery += "" + z.getSkontozeit2() + " ,";
                insertQuery += "" + z.getSkonto1() + " ,";
                insertQuery += "" + z.getSkonto2() + ",";
                insertQuery += "" + z.getMahnzeit1() + " ,";
                insertQuery += "" + z.getMahnzeit2() + " ,";
                insertQuery += "" + z.getMahnzeit3() + ")";

            } else if (o instanceof Geschaeftspartner) {
                Geschaeftspartner gp = Geschaeftspartner.class.cast(o);

                insertQuery += "" + gp.getGeschaeftspartnerID() + ",";
                insertQuery += "" + gp.getAdresseID() + ",";
                insertQuery += "" + gp.getLieferID() + ",";
                insertQuery += "" + false + " ,";
                insertQuery += "" + gp.getKreditlimit() + ",";
                insertQuery += "'" + gp.getTyp() + "')";

                //falls kein passendes Objekt gefunden wird, wird ein leere 
                //Query zurückgegbeen
            } else {
                System.out.println(
                        "Kein passendes Objekt erhalten für InsertQuery.");
                insertQuery = "";
            }

        } catch (ClassCastException cnexep) {
            System.out.println("Fehler beim Casten:" + cnexep.getMessage());
        }

        return insertQuery;
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 11.08.17  BER     Erstellt.
     *--------------------------------------------------------------------------
     */
    /**
     * Liefert zur Laufzeit den SQL Befehl um alle Objekte des übergebenen
     * Tabellennamen aus der Datenbank zu holen.
     *
     *
     * @param tabellenname Tabelle welche ausgegben werden soll
     * @param lkz kann false oder true sein
     * @return String Query für alle Objekte in der befindlichen Tabelle.
     */
    public String gibKompletteTabellemitLKZ(String tabellenname, String lkz) {
        String query = "Select * FROM ROOT.";
        query += "" + tabellenname + "";
        query += " WHERE LKZ = " + lkz + "";
        return query;
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 11.08.17  BER     Erstellt.
     *--------------------------------------------------------------------------
     */
    /**
     * Liefert zur Laufzeit den SQL Befehl um die den höchsten Primärschlüssel
     * der übergebenen Tabelle zu erhalten.
     *
     * @param id Primärschlüssel der jeweiligen Tabelle
     * @param tabellenname Tabelle worin sich das Objekt befindet
     * @return Höchster bisher vergebener Primärschlüssel der Tabelles
     */
    public String gibNaechsteID(String id, String tabellenname) {
        //maximale ID wird aus der Tabelle ausglesen
        String query = "Select MAX(" + id + ") FROM ROOT." + tabellenname + "";

        return query;
    }

    /*--------------------------------------------------------------------------
     * Datum     Name    Kommentar
     * 11.08.17  BER     Erstellt.
     *--------------------------------------------------------------------------
     */
    /**
     * Liefert zur Laufzeit den SQL Befehl um beim übergebenen Objekt das LKZ zu
     * aktivieren.
     *
     *
     * @param hmAttribute HashMap mit allen Attributen der Tabelle
     * @param tabellenname Tabelle worin sich das Objekt befindet
     * @param o Objekt bei dem LKZ aktiviert werden soll
     * @return Lösch Query für das übergebene Objekt
     */
    public String gibLkzBefehl(HashMap<String, String> hmAttribute,
            String tabellenname, Object o) {
        //Update Befehl wird gestartet mit den update befehl
        String query = "UPDATE ROOT." + tabellenname + " SET LKZ = true WHERE ";

        try {
            //mittels instanceof wird entschieden welche Query kreiert wird
            if (o instanceof Auftragskopf) {
                Auftragskopf ak = Auftragskopf.class.cast(o);
                query += "" + hmAttribute.get("AUFTRAGSKOPFID") + " = " 
                        + ak.getAuftragskopfID() + "";

            } else if (o instanceof Auftragsposition) {
                Auftragsposition ap = Auftragsposition.class.cast(o);
                query += "" + hmAttribute.get("AUFTRAGSKOPFID") + " = " 
                        + ap.getAuftragskopfID() + "";
                query += " AND ";
                query += "" + hmAttribute.get("ARTIKELID") + " = " 
                        + ap.getArtikelID() + "";

            } else if (o instanceof Artikel) {
                Artikel a = Artikel.class.cast(o);
                query += "" + hmAttribute.get("ARTIKELID") + " = " 
                        + a.getArtikelID() + "";

            } else if (o instanceof Adresse) {
                Adresse a = Adresse.class.cast(o);
                query += "" + hmAttribute.get("ADRESSEID") + " = " 
                        + a.getAdresseID() + "";

            } else if (o instanceof Zahlungskonditionen) {
                Zahlungskonditionen z = Zahlungskonditionen.class.cast(o);
                query += "" + hmAttribute.get("ZAHLUNGSKONDIID") + " = " 
                        + z.getZahlungskondiID() + "";

            } else if (o instanceof Geschaeftspartner) {
                Geschaeftspartner gp = Geschaeftspartner.class.cast(o);
                query += "" + hmAttribute.get("GESCHAEFTSPARTNERID") + " = " 
                        + gp.getGeschaeftspartnerID() + " ";
            } else {
                System.out.println(
                        "Kein passendes Objekt erhalten für Löschquery.");
                query = "";
            }

        } catch (ClassCastException cnexep) {
            System.out.println("Fehler beim Casten:" + cnexep.getMessage());
        }

        return query;
    }

}
