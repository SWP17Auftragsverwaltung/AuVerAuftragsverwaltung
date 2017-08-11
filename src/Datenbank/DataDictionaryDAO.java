/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datenbank;

import Klassen.DataDictionary;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Chakir
 */
public class DataDictionaryDAO extends DataAccess {
    
    /**
     * MetaTabelle HashMap wird instanziert.
     */
    private final HashMap<String, String> MetaTabelle = gibMetaTabelle();

    //Tabellennamen aus der Metabelle beziehen
    private final String TabellennameDataDictionary = MetaTabelle.get("DATADICTIONARY");
    private final String TABELLENNAMEAUFTRAGSKOPF = MetaTabelle.get("AUFTRAGSKOPF");
    private final String TABELLENNAMEAUFTRAGSPOSITION = MetaTabelle.get("AUFTRAGSPOSITION");
    private final String TABELLENNAMEADRESSE = MetaTabelle.get("ADRESSE");
    private final String TABELLENNAMEARTIKEL = MetaTabelle.get("ARTIKEL");
    private final String TABELLENNAMEGESCHAEFTSPARTNER = MetaTabelle.get("GESCHAEFTSPARTNER");
    private final String TABELLENNAMEZAHLUNGSKONDITIONEN = MetaTabelle.get("ZAHLUNGSKONDITIONEN");
    
    
    //DataDictionary ArrayListen für die Tabellen instanziieren
    private final ArrayList<DataDictionary> DataDictionaryAuftragskopf = gibDataDictionaryTabelle(TABELLENNAMEAUFTRAGSKOPF);
    private final ArrayList<DataDictionary> DataDictionaryAuftragsposition = gibDataDictionaryTabelle(TABELLENNAMEAUFTRAGSPOSITION);
    private final ArrayList<DataDictionary> DataDictionaryAdresse = gibDataDictionaryTabelle(TABELLENNAMEADRESSE);
    private final ArrayList<DataDictionary> DataDictionaryArtikel = gibDataDictionaryTabelle(TABELLENNAMEARTIKEL);
    private final ArrayList<DataDictionary> DataDictionaryGeschaeftspartner = gibDataDictionaryTabelle(TABELLENNAMEGESCHAEFTSPARTNER);
    private final ArrayList<DataDictionary> DataDictionaryZahlungskonditionen = gibDataDictionaryTabelle(TABELLENNAMEZAHLUNGSKONDITIONEN);
    
    
    //Attribute aus der Tabelle werden als HashMap hinterlegt
    private final HashMap<String, String> AttributeAuftragskopf = gibAttributeAlsHashMap(TABELLENNAMEAUFTRAGSKOPF);
    private final HashMap<String, String> AttributeAuftragsposition = gibAttributeAlsHashMap(TABELLENNAMEAUFTRAGSPOSITION);
    private final HashMap<String, String> AttributeAdresse = gibAttributeAlsHashMap(TABELLENNAMEADRESSE);
    private final HashMap<String, String> AttributeArtikel = gibAttributeAlsHashMap(TABELLENNAMEARTIKEL);
    private final HashMap<String, String> AttributeGeschaeftspartner = gibAttributeAlsHashMap(TABELLENNAMEGESCHAEFTSPARTNER);
    private final HashMap<String, String> AttributeZahlungskonditionen = gibAttributeAlsHashMap(TABELLENNAMEZAHLUNGSKONDITIONEN);
    
    
    
    
    /*----------------------------------------------------------*/
    /* Datum        Name       Kommentar
    /* 11.08.2017   CEL        Erstellt.   
     */
    /*----------------------------------------------------------*/
    /**
     * Die MetaTabelle wird ausgelesen.
     * Key: TabellenID 
     * Value: Tabellenname
     * 
     * @return MetaTabelle mit den Tabellennamen
     */
    
    private HashMap<String, String> gibMetaTabelle() {
        return MetaTabelle;
    }

    private ArrayList<DataDictionary> gibDataDictionaryTabelle(String tabellenname) {
        ArrayList<DataDictionary> dataDic = new ArrayList<>();
        
        
        
return dataDic;    }
    
    
        

    private HashMap<String, String> gibAttributeAlsHashMap(String tabellenname) {
        HashMap<String, String> MetaTabelle = new HashMap<>();
    return MetaTabelle;
    }

    //Generierter Code Anfang
    /*
     * @return the MetaTabelle
     */
    
    
    public HashMap<String, String> getMetaTabelle() {
        return MetaTabelle;
    }
    
     /*
     * @return TabellennameDataDictionary
     */
    public String getTabellennameDataDictionary() {
        return TabellennameDataDictionary;
    }

     /*
     * @return TABELLENNAMEAUFTRAGSKOPF
     */
    public String getTABELLENNAMEAUFTRAGSKOPF() {
        return TABELLENNAMEAUFTRAGSKOPF;
    }
    
     /*
     * @return TABELLENNAMEAUFTRAGSPOSITION
     */
    public String getTABELLENNAMEAUFTRAGSPOSITION() {
        return TABELLENNAMEAUFTRAGSPOSITION;
    }
    
     /*
     * @return TABELLENNAMEADRESSE
     */
    public String getTABELLENNAMEADRESSE() {
        return TABELLENNAMEADRESSE;
    }
    
     /*
     * @return TABELLENNAMEARTIKEL
     */
    public String getTABELLENNAMEARTIKEL() {
        return TABELLENNAMEARTIKEL;
    }

    /*
     * @return TABELLENNAMEGESCHAEFTSPARTNER
     */
    public String getTABELLENNAMEGESCHAEFTSPARTNER() {
        return TABELLENNAMEGESCHAEFTSPARTNER;
    }

    /*
     * @return TABELLENNAMEZAHLUNGSKONDITIONEN
     */
    public String getTABELLENNAMEZAHLUNGSKONDITIONEN() {
        return TABELLENNAMEZAHLUNGSKONDITIONEN;
    }

    /*
     * @return DataDictionaryAuftragskopf
     */
    public ArrayList<DataDictionary> getDataDictionaryAuftragskopf() {
        return DataDictionaryAuftragskopf;
    }

    /*
     * @return DataDictionaryAuftragsposition
     */
    public ArrayList<DataDictionary> getDataDictionaryAuftragsposition() {
        return DataDictionaryAuftragsposition;
    }

    /*
     * @return DataDictionaryAdresse
     */
    public ArrayList<DataDictionary> getDataDictionaryAdresse() {
        return DataDictionaryAdresse;
    }

    /*
     * @return DataDictionaryArtikel
     */
    public ArrayList<DataDictionary> getDataDictionaryArtikel() {
        return DataDictionaryArtikel;
    }

    /*
     * @return DataDictionaryGeschaeftspartner
     */
    public ArrayList<DataDictionary> getDataDictionaryGeschaeftspartner() {
        return DataDictionaryGeschaeftspartner;
    }

    /*
     * @return DataDictionaryZahlungskonditionen
     */
    public ArrayList<DataDictionary> getDataDictionaryZahlungskonditionen() {
        return DataDictionaryZahlungskonditionen;
    }

    /*
     * @return AttributeAuftragskopf
     */
    public HashMap<String, String> getAttributeAuftragskopf() {
        return AttributeAuftragskopf;
    }

    /*
     * @return AttributeAuftragsposition
     */
    public HashMap<String, String> getAttributeAuftragsposition() {
        return AttributeAuftragsposition;
    }

    /*
     * @return AttributeAdresse
     */
    public HashMap<String, String> getAttributeAdresse() {
        return AttributeAdresse;
    }

    /*
     * @return AttributeArtikel
     */
    public HashMap<String, String> getAttributeArtikel() {
        return AttributeArtikel;
    }

    /*
     * @return AttributeGeschaeftspartner
     */
    public HashMap<String, String> getAttributeGeschaeftspartner() {
        return AttributeGeschaeftspartner;
    }

    /*
     * @return AttributeZahlungskonditionen
     */
    public HashMap<String, String> getAttributeZahlungskonditionen() {
        return AttributeZahlungskonditionen;
    }
    
}

