/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validierung;

import javafx.scene.control.TextField;

/**
 *
 * @author Tobi
 */
public class SuchTextField extends TextField {
    
    public SuchTextField() {

        this.setPromptText(" ");

    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[a-zA-Z0-9]") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }         
         
         
    @Override
    public void replaceSelection(String replacement) {
        
        super.replaceSelection(replacement);
    }


    
}
