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
public class MengeTextField extends TextField{
    
        public MengeTextField() {

        this.setPromptText(" ");

    }

    
    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[1-9]") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }
    
    @Override
    public void replaceSelection(String replacement) {
        
        super.replaceSelection(replacement);
    }
    
}
