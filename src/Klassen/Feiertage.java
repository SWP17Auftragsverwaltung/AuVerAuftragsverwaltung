/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Klassen;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Andre
 */
public class Feiertage {

    
    public boolean feiertag(GregorianCalendar cal) {
        boolean istFeiertag = false;
        int jahr = cal.get(GregorianCalendar.YEAR);
        
        int a, b, c, k, p, q, M, N, d, e, osternsonntag;
        a = jahr % 19;
        b = jahr % 4;
        c = jahr % 7;
        k = jahr / 100;
        p = (8 * k + 13) / 25;
        q = k / 4;
        M = (15 + k - p - q) % 30;
        N = (4 + k - q) % 7;
        d = (19 * a + M) % 30;
        e = (2 * b + 4 * c + 6 * d + N) % 7;
        if (d + e == 35) {
            osternsonntag = 50;
        } else if (d == 28 && e == 6 && (11 * M + 11) % 30 < 19) {
            osternsonntag = 49;
        } else {
            osternsonntag = 22 + d + e;
        }
        
    
        int maerz = 2;
        int april = 3;
        int const31 = 31;
        // definition des RÃ¼ckgabe Obj
        GregorianCalendar calOstersonntag = null;
        // Ostertag kleiner als 31
        if (osternsonntag < const31) {
            // Ostersonntag Obj wird erstellt und der Ostersonntag findet im MÃ¤rz statt
            calOstersonntag = new GregorianCalendar(jahr, maerz, osternsonntag);

        } else {
            // von dem Oster_tag muss noch 31 abgezogen werden
            osternsonntag = osternsonntag - const31;
            // Ostersonntag Obj wird erstellt und der Ostersonntag findet im April statt
            calOstersonntag = new GregorianCalendar(jahr, april, osternsonntag);
        }
  
   
   

        // GregorianCalendar Obj die Feiertage reprÃ¤sentieren
        GregorianCalendar gc_ostersonntag = calOstersonntag;
        GregorianCalendar gc_ostermontag = new GregorianCalendar(gc_ostersonntag.get(Calendar.YEAR), gc_ostersonntag.get(Calendar.MONTH), (gc_ostersonntag.get(Calendar.DATE) + 1));
        GregorianCalendar gc_karfreitag = new GregorianCalendar(gc_ostersonntag.get(Calendar.YEAR), gc_ostersonntag.get(Calendar.MONTH), (gc_ostersonntag.get(Calendar.DATE) - 2));
        GregorianCalendar gc_rosenmontag = new GregorianCalendar(gc_ostersonntag.get(Calendar.YEAR), gc_ostersonntag.get(Calendar.MONTH), (gc_ostersonntag.get(Calendar.DATE) - 48));
        GregorianCalendar gc_christihimmelfahrt = new GregorianCalendar(gc_ostersonntag.get(Calendar.YEAR), gc_ostersonntag.get(Calendar.MONTH), (gc_ostersonntag.get(Calendar.DATE) + 39));
        GregorianCalendar gc_pfinstsonntag = new GregorianCalendar(gc_ostersonntag.get(Calendar.YEAR), gc_ostersonntag.get(Calendar.MONTH), (gc_ostersonntag.get(Calendar.DATE) + 49));
        GregorianCalendar gc_pfinstmontag = new GregorianCalendar(gc_ostersonntag.get(Calendar.YEAR), gc_ostersonntag.get(Calendar.MONTH), (gc_ostersonntag.get(Calendar.DATE) + 50));
        GregorianCalendar gc_frohnleichnahm = new GregorianCalendar(gc_ostersonntag.get(Calendar.YEAR), gc_ostersonntag.get(Calendar.MONTH), (gc_ostersonntag.get(Calendar.DATE) + 60));
        GregorianCalendar gc_wiedervereinigung = new GregorianCalendar(gc_ostersonntag.get(Calendar.YEAR), 9, 1);
        GregorianCalendar gc_weihnachten_1 = new GregorianCalendar(gc_ostersonntag.get(Calendar.YEAR), 11, 24);
        GregorianCalendar gc_weihnachten_2 = new GregorianCalendar(gc_ostersonntag.get(Calendar.YEAR), 11, 25);
        GregorianCalendar gc_weihnachten_3 = new GregorianCalendar(gc_ostersonntag.get(Calendar.YEAR), 11, 26);
        GregorianCalendar gc_silvester = new GregorianCalendar(gc_ostersonntag.get(Calendar.YEAR), 11, 31);
        GregorianCalendar gc_neujahr = new GregorianCalendar(gc_silvester.get(Calendar.YEAR), 0, 1);

        // ÃœberprÃ¼fung ob der Ãœbergebene Tag (jetzt) ein Feiertag ist
        if (cal.getTime().equals(calOstersonntag.getTime()) || gc_ostermontag.getTime().equals(cal.getTime()) || gc_karfreitag.getTime().equals(cal.getTime())
                || gc_rosenmontag.getTime().equals(cal.getTime()) || gc_christihimmelfahrt.getTime().equals(cal.getTime())
                || gc_pfinstmontag.getTime().equals(cal.getTime()) || gc_frohnleichnahm.getTime().equals(cal.getTime())
                || gc_weihnachten_1.getTime().equals(cal.getTime()) || gc_weihnachten_2.getTime().equals(cal.getTime())
                || gc_weihnachten_3.getTime().equals(cal.getTime()) || gc_silvester.getTime().equals(cal.getTime())
                || gc_neujahr.getTime().equals(cal.getTime()) || gc_wiedervereinigung.getTime().equals(cal.getTime())) {
            istFeiertag = true; // ist ein Feiertag
        }

        return istFeiertag;
    }
    
    
    public static void main(String[] args) {
        Feiertage f = new Feiertage();
        
        GregorianCalendar cal = new GregorianCalendar();
        cal.clear();
        cal.set(2017, 4, 16);
        
        System.out.println(f.feiertag(cal));
        
        
    }
    
}
