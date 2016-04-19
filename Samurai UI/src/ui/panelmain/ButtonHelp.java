package ui.panelmain;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.SingletonClass;
import main.DynamicButton;
import ui.panelgame.PanelGame;
import ui.panelhelp.PanelHelp;

public class ButtonHelp extends DynamicButton{

	
	public ButtonHelp(JPanel fatherPanel) {
		super(0,0,0,0,null,null,null);
		this.fatherPanel = fatherPanel;
	}
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		
		this.frame.setContentPane(new PanelHelp());
		this.remove(this.fatherPanel);
		this.revalidate();
	}

}