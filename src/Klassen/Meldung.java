package Klassen;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Jakob
 */
public class Meldung {

    /**
     * Variable für den "Verwerfen Titel".
     */
    private final String verwerfenTitel = "Datensatz verwerfen?";

    /**
     * Variable für den "Verwerfen Text".
     */
    private final String verwerfenText = "Wollen sie den Datensatz verwerfen?";
    
    /**
     * Variable für den "Löschen Titel".
     */
    private final String loeschenTitel = "Datensatz löschen?";
    
    /**
     * Variable für den "Löschen Text".
     */
    private final String loeschenText  
            = "Wollen sie wirklich den Datensatz löschen?";

    /**
     * Variable für die Meldung vom Typ "Alert".
     */
    private Alert meldung;
   

    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 23.08.17    GET     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erzeugt ein Fenster das dem Benutzer ermöglicht, seine Aktion zu
     * verwerfen.
     */        
    public void verwerfenFenster() {
        meldung = new Alert(Alert.AlertType.CONFIRMATION);
        meldung.setTitle(verwerfenTitel);
        meldung.setContentText(verwerfenText);
        meldung.getButtonTypes().clear();
        meldung.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        meldung.showAndWait();
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 23.08.17    GET     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erzeugt einen positiven Antwortbutton.
     * @return Positiver Button.
     */   
    public boolean antwort() {
        return (this.meldung.getResult() == ButtonType.YES);
    }

    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 23.08.17    GET     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Schließt ein geöffnet Fenster.
     */       
    public void schließeFenster() {
        meldung.close();
    }
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 23.08.17    GET     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erzeugt ein Fenster das dem Benutzer die Möglichkeit bietet, seine 
     * "Lösch Aktion" entweder zu bejaen oder zu verneinen.
     */       
    public void loeschenAbfragen() { 
        meldung = new Alert(Alert.AlertType.WARNING);
        meldung.setTitle(loeschenTitel);
        meldung.setContentText(loeschenText);
        meldung.getButtonTypes().clear();
        meldung.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        meldung.showAndWait();
    }
    
    

}
