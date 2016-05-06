/**
 * Date : Apr 4, 2016 6:12:24 PM
 */
package ui.panelmain;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import main.DynamicButton;
import ui.panelgame.PanelGame;

/**
 * @author Alone
 * Written by YYM
 */
public class ButtonMachineGame extends DynamicButton{
	
	/**
	 *人机对战
	 */
	public ButtonMachineGame(JPanel fatherPanel) {
		super(0, 0, 0, 0,null,null,null);
		this.fatherPanel = fatherPanel;
	}

	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		this.frame.setContentPane(new PanelGame());
		this.remove(this.fatherPanel);
		this.revalidate();
	}

}
