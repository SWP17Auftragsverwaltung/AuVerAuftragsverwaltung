
package validierung;

import javafx.scene.control.TextField;

/**
 *
 * @author Jakob
 */
public class NameTextField extends TextField {
    
    public NameTextField() {

        this.setPromptText(" ");

    }

    @Override
    public void replaceSelection(String replacement) {
        
        super.replaceSelection(replacement);
    }

    @Override
    public void replaceText(int start, int end, String text) {
//        if (text.matches("(^[\\p{L} .'-]+$)") || text.isEmpty()) {
        if (text.matches("(^[öÖäÄüÜßa-zA-Z -]*$)") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }

    
    
}
