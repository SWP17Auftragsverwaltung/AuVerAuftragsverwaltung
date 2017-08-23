package auverauftragsverwaltung;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

/**
 *
 * @author Jakob
 */
public class Meldung {

    private final String verwerfenTitel = "Datensatz verwerfen?";

    private final String verwerfenText = "Wollen sie den Datensatz verwerfen?";
    
    private final String loeschenTitel = "Datensatz löschen?";
    
    private final String loeschenText  = "Wollen sie wirklich den Datensatz löschen?";

    private Alert meldung;
   

    public void verwerfenFenster() {
      

        meldung = new Alert(Alert.AlertType.CONFIRMATION);
        meldung.setTitle(verwerfenTitel);
        meldung.setContentText(verwerfenText);
        meldung.getButtonTypes().clear();
        meldung.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        meldung.showAndWait();

    }

    public boolean antwort() {

        return (this.meldung.getResult() == ButtonType.YES);

    }

    public void schließeFenster() {

        meldung.close();
    }
    
    public void loeschenAbfragen(){
        
        meldung = new Alert(Alert.AlertType.WARNING);
        meldung.setTitle(loeschenTitel);
        meldung.setContentText(loeschenText);
        meldung.getButtonTypes().clear();
        meldung.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        meldung.showAndWait();

    }

}
