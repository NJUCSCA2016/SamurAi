/**
 * Date : Apr 4, 2016 6:10:44 PM
 */
package ui.panelmain;
import java.awt.event.MouseEvent;

import main.DynamicButton;

/**
 * @author Alone
 * Written by YYM
 */
public class ButtonExit extends DynamicButton{

	public ButtonExit() {
		
		super(0,0,0,0,null,null,null);
	
		
	}
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		
		System.exit(0);
	}
	
}
