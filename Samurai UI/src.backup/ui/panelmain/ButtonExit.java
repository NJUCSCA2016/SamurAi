/**
 * Date : Apr 4, 2016 6:10:44 PM
 */
package ui.panelmain;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.SuperButton;

/**
 * @author Alone
 * Written by YYM
 */
public class ButtonExit extends SuperButton{

	public ButtonExit() {
		
		super(0,0,0,0,null,null,null);
		System.exit(0);
		
	}
	public void mouseClicked(MouseEvent e) {
		//TODO �? 改变Button的大�?
		System.exit(0);
	}

	public void mouseEntered(MouseEvent e) {
		// TODO 改变Button的大�?
		
	}
	public void mouseExited(MouseEvent e) {
		//TODO : 还原Button的大�?
		
	}
	
	
}
