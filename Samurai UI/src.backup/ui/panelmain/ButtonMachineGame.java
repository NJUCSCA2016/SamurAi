/**
 * Date : Apr 4, 2016 6:12:24 PM
 */
package ui.panelmain;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.SuperButton;
import ui.panelgame.PanelGame;

/**
 * @author Alone
 * Written by YYM
 */
public class ButtonMachineGame extends SuperButton{
	
	private JFrame frame;
	private JPanel fatherPanel;
	
	/**
	 *人机对战
	 */
	public ButtonMachineGame(JFrame frame , JPanel fatherPanel) {
		super(0, 0, 0, 0,null,null,null);
		this.frame = frame;
		this.fatherPanel = fatherPanel;
	}

	public void mouseClicked(MouseEvent e) {
		//TODO 改变Button的大�?
		
		this.frame.setContentPane(new PanelGame());
		this.remove(this.fatherPanel);
		this.revalidate();
	}

	public void mouseEntered(MouseEvent e) {
		// TODO 改变Button的大�?
	}
	public void mouseExited(MouseEvent e) {
		//TODO : 还原Button的大�?
	}
	
}
