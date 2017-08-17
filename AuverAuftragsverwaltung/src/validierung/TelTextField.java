
package validierung;

import javafx.scene.control.TextField;

/**
 *
 * @author Jakob
 */
public class TelTextField extends TextField {
    
     public TelTextField() {

        this.setPromptText(" ");

    }

    @Override
    public void replaceSelection(String replacement) {
        
        super.replaceSelection(replacement);
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[0{1}\\d*\\d{1,}]") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }
}
