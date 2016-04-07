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
public class ButtonExit extends SuperButton implements MouseListener{

	public ButtonExit() {
		
		super(0,0,0,0);
		
		//TODO : 设置Icon . 
		this.setIcon(null);
		this.addMouseListener(this);
		
	}
	public void mouseClicked(MouseEvent e) {
		System.exit(0);
	}

	public void mouseEntered(MouseEvent e) {
		// TODO 改变Button的image
		
		this.setIcon(null);
		
	}
	public void mouseExited(MouseEvent e) {
		//TODO : 还原Button的image
		
		this.setIcon(null);
		
	}
	
	public void mousePressed(MouseEvent arg0) {	}
	public void mouseReleased(MouseEvent arg0) {}
	
	
	
}
