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
    public void replaceSelection(String replacement) {

        super.replaceSelection(replacement);
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[a-zA-Z0-9]+(.)") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }
}