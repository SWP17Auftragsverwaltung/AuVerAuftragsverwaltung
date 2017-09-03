package validierung;

import javafx.scene.control.TextField;

/**
 *
 * @author Jakob
 */
public class MailTextField extends TextField {

    public MailTextField() {
        
        

        this.setPromptText(" ");

    }
    
    
        @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[A-Za-z0-9+_.@-]") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }
    
    @Override
    public void replaceSelection(String replacement) {

        super.replaceSelection(replacement);
    }
    
    
}
