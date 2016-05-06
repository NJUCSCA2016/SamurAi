package ui.panelhelp;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import main.DynamicButton;
import ui.panelmain.PanelMain;

public class ButtonCloseHelp extends DynamicButton{
	
	private JPanel fatherPanel;
	
	public ButtonCloseHelp(JPanel fatherPanel){
		
		super(0, 0, 0, 0, null, null, null);
		this.fatherPanel = fatherPanel;
		
	}
	
	public void mouseClicked(MouseEvent e){
		
		super.mouseClicked(e);
		this.frame.setContentPane(new PanelMain());
		this.frame.remove(fatherPanel);
		this.frame.revalidate();
		
	}
	
}
