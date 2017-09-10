package Klassen;

import java.time.DayOfWeek;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 *
 * @author Jakob
 */
public class Meldung {

    /**
     * Variable für die Meldung vom Typ "Alert".
     */
    private Alert meldung;
    
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
     * Variable für den "Verwerfen Titel".
     */
    private final String datumDialogTitel = "Datum hinzufügen?";

    /**
     * Variable für den "Verwerfen Text".
     */
    private final String datumDialogWochenende = "Das eingegebene Datum fällt "
        + "auf ein Wochenende. Möchten Sie den Auftrag trotzdem hinzufügen?";

    /**
     * Variable für den "Verwerfen Text".
     */
    private final String datumDialogFeiertag = "Das eingegebene Datum fällt "
        + "auf einen Feiertag. Möchten Sie den Auftrag trotzdem hinzufügen?";
      
    
    /**
     * Button um das Datum zu ändern.
     */
    private final ButtonType btDatumAendern 
        = new ButtonType("Datum ändern", ButtonBar.ButtonData.CANCEL_CLOSE);
    
    /**
     * Variable für den "Verwerfen Titel".
     */
    private final String datumDatepickerTitel = "Bitte Datum wählen:";
    

    
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
    /* 02.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erzeugt ein Fenster das dem Benutzer ermöglicht, seine Aktion zu
     * verwerfen.
     */        
    public void dialogDatumWochenende() { 
        meldung = new Alert(Alert.AlertType.CONFIRMATION);
        meldung.setTitle(datumDialogTitel);
        meldung.setContentText(datumDialogWochenende);
        meldung.getButtonTypes().clear();
        meldung.getButtonTypes().addAll(ButtonType.YES, btDatumAendern);
        meldung.showAndWait();
    }


    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 02.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erzeugt ein Fenster das dem Benutzer ermöglicht, seine Aktion zu
     * verwerfen.
     */        
    public void dialogDatumFeiertag() { 
        meldung = new Alert(Alert.AlertType.CONFIRMATION);
        meldung.setTitle(datumDialogTitel);
        meldung.setContentText(datumDialogFeiertag);
        meldung.getButtonTypes().clear();
        meldung.getButtonTypes().addAll(ButtonType.YES, btDatumAendern);
        meldung.showAndWait();
    }    
 
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 02.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erzeugt einen angepassten Datepicker, der die Auswahl von Wochenendtagen
     * nicht zulässt.
     * @return Datepicker
     */   
    private Callback<DatePicker, DateCell> getDayCellFactory() {
 
        final Callback<DatePicker, DateCell> dayCellFactory 
            = new Callback<DatePicker, DateCell>() {
 
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item.getDayOfWeek() == DayOfWeek.SATURDAY
                                || item.getDayOfWeek() == DayOfWeek.SUNDAY) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                            if (item.isBefore(LocalDate.now())) {
                                setDisable(true);
                            }
                        }
                    };
                }
            };
        return dayCellFactory;
    }      
    
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 03.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erzeugt ein Fenster das dem Benutzer ermöglicht, seine Aktion zu
     * verwerfen.
     * @return Ausgewähltes Datum.
     */        
    public String dialogDatepicker() {       
        DatePicker datepicker = new DatePicker();
        GridPane grid = new GridPane();
        
        meldung = new Alert(Alert.AlertType.CONFIRMATION);
        meldung.setTitle(datumDatepickerTitel);
        meldung.getButtonTypes().clear();
        
        Callback<DatePicker, DateCell> dayCellFactory 
                = this.getDayCellFactory();
        datepicker.setDayCellFactory(dayCellFactory); 
        datepicker.setValue(LocalDate.now());
        
        grid.getChildren().addAll(datepicker);
        GridPane.setConstraints(datepicker, 0, 0);
        
        meldung.getDialogPane().setContent(grid);       
        meldung.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        meldung.showAndWait();
        
        LocalDate ld = datepicker.getValue();
        String jahr = String.valueOf(ld.getYear()); 
        String monat = String.valueOf(ld.getMonthValue()); 
        String tag = String.valueOf(ld.getDayOfMonth());
        
        String datum = tag + "." + monat + "." + jahr;
        
        return datum;
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
