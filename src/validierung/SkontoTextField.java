
package validierung;

import javafx.scene.control.TextField;

/**
 *
 * @author Jakob
 */
public class SkontoTextField extends TextField {
    
    public SkontoTextField() {

        this.setPromptText(" ");

    }

    
    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[0-9,.]") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }
    
    @Override
    public void replaceSelection(String replacement) {
        
        super.replaceSelection(replacement);
    }

}