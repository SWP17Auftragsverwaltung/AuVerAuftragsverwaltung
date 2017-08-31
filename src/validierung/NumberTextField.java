/*------------------------------------------------------------------------------
* Klasse: NumberTextField.
*-------------------------------------------------------------------------------
* Zweck:
* - Validierung der Zahlenfelder.
*-------------------------------------------------------------------------------
* Historie:
* 2017-08-03 GET Angelegt.
* 2017-08-07 BER Kommentarlayout angepasst.
*-------------------------------------------------------------------------------
*/
package validierung;

import javafx.scene.control.TextField;

/**
 *
 * @author Jakob
 */
public class NumberTextField extends TextField {

    public NumberTextField() {

        this.setPromptText(" ");

    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[0-9]") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }
    
    @Override
    public void replaceSelection(String replacement) {
        
        super.replaceSelection(replacement);
    }



}
