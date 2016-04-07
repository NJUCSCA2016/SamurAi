/**
 * Date : Apr 4, 2016 6:12:11 PM
 */
package ui.panelmain;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.SuperButton;
import ui.panelgame.PanelGame;
import ui.panelsetting.PanelSet;

/**
 * @author Alone
 * Written by YYM
 */
public class ButtonSet extends SuperButton implements MouseListener{

	private JFrame frame;
	private JPanel fatherPanel;
	
	
	public ButtonSet(JFrame frame , JPanel fatherPanel) {
		super(0, 0, 0, 0);
		this.frame = frame;
		this.fatherPanel = fatherPanel;
		this.setIcon(null);
		this.addMouseListener(this);
	}
	
	public void mouseClicked(MouseEvent e) {
		this.frame.setContentPane(new PanelSet());
		this.remove(this.fatherPanel);
		this.revalidate();
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
