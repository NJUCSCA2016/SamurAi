package ui.panelmain;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.SuperButton;
import ui.panelgame.PanelGame;
import ui.panelhelp.PanelHelp;

public class ButtonHelp extends SuperButton{

	
	private JFrame frame ;
	private JPanel fatherPanel;
	
	public ButtonHelp(JFrame frame,JPanel fatherPanel) {
		super(0,0,0,0,null,null,null);
		this.frame = frame;
		this.fatherPanel = fatherPanel;
	}
	public void mouseClicked(MouseEvent e) {
		//TODO 改变Button的大�?
		
		this.frame.setContentPane(new PanelHelp());
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
