package Klassen;

import Datenbank.ZahlungskonditionenDAO;
import auverauftragsverwaltung.ZahlungskonditionenController;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javax.swing.text.DateFormatter;
import org.apache.derby.client.am.DateTime;

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
    private final String verwerfenText = "Wollen Sie den Datensatz verwerfen?";
    
    /**
     * Variable für den "Löschen Titel".
     */
    private final String loeschenTitel = "Datensatz löschen?";
    
    /**
     * Variable für den "Löschen Text".
     */
    private final String loeschenText  
            = "Wollen Sie wirklich den Datensatz löschen?";

    /**
     * Variable für den "Letzte Position löschen Titel".
     */
    private final String loescheLetztePosTitel = "Letzte Position löschen?";    
    
    /**
     * Variable für den "Letzte Position löschen Text".
     */
    private final String loescheLetztePosText  
        = "Wollen Sie wirklich die letzte Position löschen? \n"
        + "ACHTUNG: Der Auftragskopf wird zusätzlich gelöscht!";    

    /**
     * Variable für den "Auftrag mit Position löschen Titel".
     */
    private final String loeschePosAuftragTitel = "Auftrag enthält Positionen";
    
    /**
     * Variable für den "Auftrag mit Positionen löschen Text".
     */
    private final String loescheLetztePosAuftragText  
        = "Möchten Sie den Auftrag wirklich löschen? \n\n"
        + "ACHTUNG: Der Auftragskopf enthält noch EINE letzte Position, die "
        + "auch gelöscht wird!";   

    /**
     * Variable für den "Auftrag mit Positionen löschen Text".
     */
    private final String loeschePosAuftragText  
        = "ACHTUNG: Der Auftragskopf enthält noch mehrere Positionen. \n"
        + "Bitte erst die Positionen löschen!";  
 
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
    
        /**
     * Variable für den "Zahlungskonditions Titel".
     */
    private final String datumZahlungskonditionTitel 
        = "Bitte Zahlungskondition wählen:";
    

    
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
                            if (item.isBefore(LocalDate.now())) {
                                setDisable(true);
                            }
                            
                            if (item.isAfter(LocalDate.now())) {
                                if (item.getDayOfWeek() == DayOfWeek.SATURDAY
                                    || item.getDayOfWeek() == DayOfWeek.SUNDAY
                                       
                                    || (item.getMonth() == Month.DECEMBER 
                                            && item.getDayOfMonth() == 25)
                                       
                                    || (item.getMonth() == Month.DECEMBER 
                                            && item.getDayOfMonth() == 26) 
                                       
                                    || (item.getMonth() == Month.OCTOBER 
                                            && item.getDayOfMonth() == 3)
                                       
                                    || (item.getMonth() == Month.OCTOBER 
                                            && item.getDayOfMonth() == 31)
                                       
                                    || (item.getMonth() == Month.JANUARY 
                                            && item.getDayOfMonth() == 1)   
                                       
                                    || (item.getMonth() == Month.JANUARY 
                                            && item.getDayOfMonth() == 6)  
                                       
                                    || (item.getMonth() == Month.AUGUST 
                                            && item.getDayOfMonth() == 8)  
                                       
                                    || (item.getMonth() == Month.AUGUST 
                                            && item.getDayOfMonth() == 15)  
                                       
                                    || (item.getMonth() == Month.NOVEMBER 
                                            && item.getDayOfMonth() == 1)) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
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
     * Erzeugt ein Fenster das dem Benutzer ermöglicht, ein Datum zu wählen.
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
        meldung.getButtonTypes().addAll(ButtonType.APPLY);
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
    /* 02.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erzeugt einen angepassten Datepicker, der die Auswahl von Wochenendtagen
     * nicht zulässt.
     * @return Datepicker
     */   
    private Callback<DatePicker, DateCell> 
        getDayCellFactoryLiefer(LocalDate ld) {
 
        final Callback<DatePicker, DateCell> dayCellFactory 
            = new Callback<DatePicker, DateCell>() {
 
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item.isBefore(ld)) {
                                setDisable(true);
                            }
                            
                            if (item.isAfter(ld)) {
                                if (item.getDayOfWeek() == DayOfWeek.SATURDAY
                                    || item.getDayOfWeek() == DayOfWeek.SUNDAY
                                       
                                    || (item.getMonth() == Month.DECEMBER 
                                            && item.getDayOfMonth() == 25)
                                       
                                    || (item.getMonth() == Month.DECEMBER 
                                            && item.getDayOfMonth() == 26) 
                                       
                                    || (item.getMonth() == Month.OCTOBER 
                                            && item.getDayOfMonth() == 3)
                                       
                                    || (item.getMonth() == Month.OCTOBER 
                                            && item.getDayOfMonth() == 31)
                                       
                                    || (item.getMonth() == Month.JANUARY 
                                            && item.getDayOfMonth() == 1)   
                                       
                                    || (item.getMonth() == Month.JANUARY 
                                            && item.getDayOfMonth() == 6)  
                                       
                                    || (item.getMonth() == Month.AUGUST 
                                            && item.getDayOfMonth() == 8)  
                                       
                                    || (item.getMonth() == Month.AUGUST 
                                            && item.getDayOfMonth() == 15)  
                                       
                                    || (item.getMonth() == Month.NOVEMBER 
                                            && item.getDayOfMonth() == 1)) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        }
                    };
                }
            };
        return dayCellFactory;
    }     
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 17.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erzeugt ein Fenster das dem Benutzer ermöglicht, ein Datum zu wählen. Die
     * Sperrzeit wird auf das Erfassungsdatum gerechnet.
     * @param datumLiefer Übergebenes Lieferdatum
     * @return Ausgewähltes Datum.
     * @throws java.text.ParseException ParceException
     */        
    public String dialogDatepickerLieferdatum(String datumLiefer) 
        throws ParseException {       
        DatePicker datepicker = new DatePicker();
        GridPane grid = new GridPane();
        
        meldung = new Alert(Alert.AlertType.CONFIRMATION);
        meldung.setTitle(datumDatepickerTitel);
        meldung.getButtonTypes().clear();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(datumLiefer, formatter);
        
        Callback<DatePicker, DateCell> dayCellFactory 
            = this.getDayCellFactoryLiefer(localDate);
        datepicker.setDayCellFactory(dayCellFactory); 
        datepicker.setValue(localDate);
        
        grid.getChildren().addAll(datepicker);
        GridPane.setConstraints(datepicker, 0, 0);
        
        meldung.getDialogPane().setContent(grid);       
        meldung.getButtonTypes().addAll(ButtonType.APPLY);
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
    /* 03.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erzeugt ein Fenster das dem Benutzer ermöglicht, eine Auftragskondition
     * zu wählen.
     * @param auftragsart Zeigt Konditionen zu dieser Auftragsart.
     * @return Ausgewählte Auftragskondition.
     * @throws java.sql.SQLException SQLFehler
     */        
    public String[] dialogAuftragskondition(String auftragsart) 
        throws SQLException {       

        TableView tv = new TableView<Zahlungskonditionen>();
     
        TableColumn tcKonditionenID = new TableColumn("ZahlungskonditionenID");
        tcKonditionenID.setCellValueFactory(
                new PropertyValueFactory<>("ZahlungskonditionenID"));
        
        TableColumn tcAuftragsart = new TableColumn("Auftragsart");
        tcAuftragsart.setCellValueFactory(
                new PropertyValueFactory<>("Auftragsart"));
        
        TableColumn tcLieferzeitSOFORT = new TableColumn("LieferzeitSOFORT");
        tcLieferzeitSOFORT.setCellValueFactory(
                new PropertyValueFactory<>("LieferzeitSOFORT"));
        
        TableColumn tcSperrzeitWUNSCH = new TableColumn("SperrzeitWUNSCH");
        tcSperrzeitWUNSCH.setCellValueFactory(
                new PropertyValueFactory<>("SperrzeitWUNSCH"));
        
        TableColumn tcSkontozeit1 = new TableColumn("Skontozeit1");
        tcSkontozeit1.setCellValueFactory(
                new PropertyValueFactory<>("Skontozeit1"));
        
        TableColumn tcSkonto1 = new TableColumn("Skonto1");
        tcSkonto1.setCellValueFactory(
                new PropertyValueFactory<>("Skonto1"));
        
        TableColumn tcSkontozeit2 = new TableColumn("Skontozeit2");
        tcSkontozeit2.setCellValueFactory(
                new PropertyValueFactory<>("Skontozeit2"));
        
        TableColumn tcSkonto2 = new TableColumn("Skonto2");
        tcSkonto2.setCellValueFactory(
                new PropertyValueFactory<>("Skonto2"));
        
        TableColumn tcMahnzeit1 = new TableColumn("Mahnzeit1");
        tcMahnzeit1.setCellValueFactory(
                new PropertyValueFactory<>("Mahnzeit1"));
        
        TableColumn tcMahnzeit2 = new TableColumn("Mahnzeit2");
        tcMahnzeit2.setCellValueFactory(
                new PropertyValueFactory<>("Mahnzeit2"));
        
        TableColumn tcMahnzeit3 = new TableColumn("Mahnzeit3");
        tcMahnzeit3.setCellValueFactory(
                new PropertyValueFactory<>("Mahnzeit3"));
           
        tv.getColumns().setAll(tcKonditionenID, tcAuftragsart, 
            tcLieferzeitSOFORT, tcSperrzeitWUNSCH, tcSkontozeit1, tcSkonto1, 
            tcSkontozeit2, tcSkonto2, tcMahnzeit1, tcMahnzeit2, tcMahnzeit3);
        
        ZahlungskonditionenDAO zkd = new ZahlungskonditionenDAO();
    
        GridPane grid = new GridPane();

        if (auftragsart.equals("Sofortauftrag")) {
            ObservableList<Zahlungskonditionen> zahlungskonditionen
                = FXCollections.observableArrayList(
                zkd.gibZahlungskonditionenZuArt(auftragsart));
            tv.setItems(zahlungskonditionen);           
            
        } else if (auftragsart.equals("Terminauftrag")) {
            ObservableList<Zahlungskonditionen> zahlungskonditionen
                = FXCollections.observableArrayList(
                zkd.gibZahlungskonditionenZuArt(auftragsart));
            tv.setItems(zahlungskonditionen);          
        
        } else if (auftragsart.equals("Bestellauftrag")) {
            ObservableList<Zahlungskonditionen> zahlungskonditionen
                = FXCollections.observableArrayList(
                zkd.gibZahlungskonditionenZuArt(auftragsart));
            tv.setItems(zahlungskonditionen);           
        } 
        
        meldung = new Alert(Alert.AlertType.NONE);
        meldung.setTitle(datumZahlungskonditionTitel);
        meldung.getButtonTypes().clear();

        grid.getChildren().addAll(tv);
        GridPane.setConstraints(tv, 0, 0);
        
        meldung.getDialogPane().setContent(grid);       
        meldung.getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
        meldung.showAndWait();
        
        String[] ausgabe = null;
        if (antwort()) {         
            String zeit = "";
            String zkID = "";
            Object zk = tv.getSelectionModel().getSelectedItem();
            Zahlungskonditionen z = (Zahlungskonditionen) zk;
            zkID = z.getZahlungskonditionenID();
            if (auftragsart.equals("Sofortauftrag")) {
                zeit = z.getLieferzeitSOFORT();
            
            } else if (auftragsart.equals("Terminauftrag")) {
                zeit = z.getSperrzeitWUNSCH();
            }
                    
            ausgabe = new String[] {zkID, zeit};        
        } 
        return ausgabe;
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
        return (this.meldung.getResult() == ButtonType.YES 
            || this.meldung.getResult() == ButtonType.APPLY);
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


    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erzeugt ein Fenster das dem Benutzer die Möglichkeit bietet, seine 
     * "Lösch Aktion" entweder zu bejaen oder zu verneinen.
     */       
    public void loescheLetztePos() { 
        meldung = new Alert(Alert.AlertType.WARNING);
        meldung.setTitle(loescheLetztePosTitel);
        meldung.setContentText(loescheLetztePosText);
        meldung.getButtonTypes().clear();
        meldung.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        meldung.showAndWait();
    }    


    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erzeugt ein Fenster das dem Benutzer die Möglichkeit bietet, seine 
     * "Lösch Aktion" entweder zu bejaen oder zu verneinen.
     */       
    public void loescheLetztePosAuftrag() { 
        meldung = new Alert(Alert.AlertType.WARNING);
        meldung.setTitle(loeschePosAuftragTitel);
        meldung.setContentText(loescheLetztePosAuftragText);
        meldung.getButtonTypes().clear();
        meldung.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        meldung.showAndWait();
    } 
    
    
    /*------------------------------------------------------------------------*/
    /* Datum       Name    Was
    /* 14.09.17    HEN     Methode erstellt.
    /*------------------------------------------------------------------------*/
    
    /**
     * Erzeugt ein Fenster das dem Benutzer den Hinweis gibt, dass der Auftrag
     * noch Positionen enthäkt und nicht gelöscht werden kann.
     */       
    public void loeschePosAuftrag() { 
        meldung = new Alert(Alert.AlertType.WARNING);
        meldung.setTitle(loeschePosAuftragTitel);
        meldung.setContentText(loeschePosAuftragText);
        meldung.getButtonTypes().clear();
        meldung.getButtonTypes().addAll(ButtonType.OK);
        meldung.showAndWait();
    }       
    
    
}
