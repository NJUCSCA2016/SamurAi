package ui.panelmain;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.SuperButton;
import ui.panelgame.PanelGame;
import ui.panelhelp.PanelHelp;

public class ButtonHelp extends SuperButton implements MouseListener{

	
	private JFrame frame ;
	private JPanel fatherPanel;
	
	public ButtonHelp(JPanel fatherPanel) {
		super(0,0,0,0);
		// TODO Auto-generated constructor stub
	}
	public void mouseClicked(MouseEvent e) {
		this.frame.setContentPane(new PanelHelp());
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
