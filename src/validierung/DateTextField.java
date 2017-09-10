
package validierung;

import javafx.scene.control.TextField;

/**
 *
 * @author Jakob
 */
public class DateTextField extends TextField{
    
    public DateTextField() {

        this.setPromptText(" ");

    }
    
    
    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[0-9.]") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        
        super.replaceSelection(replacement);
    }

//    @Override
//    public void replaceText(int start, int end, String text) {
//        if (text.matches("^[0-9]{2}.[0-9]{2}.[0-9]{4}$/") || text.isEmpty()) {
//            super.replaceText(start, end, text);
//        }
//    }
    
}
