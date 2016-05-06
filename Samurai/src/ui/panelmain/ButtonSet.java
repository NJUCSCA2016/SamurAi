/**
 * Date : Apr 4, 2016 6:12:11 PM
 */
package ui.panelmain;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import main.DynamicButton;
import ui.panelsetting.PanelSet;

/**
 * @author Alone
 * Written by YYM
 */
public class ButtonSet extends DynamicButton{
	
	public ButtonSet(JPanel fatherPanel) {
		super(0, 0, 0, 0,null,null,null);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		
		this.frame.setContentPane(new PanelSet());
		this.remove(this.fatherPanel);
		this.revalidate();
	}
	
}
