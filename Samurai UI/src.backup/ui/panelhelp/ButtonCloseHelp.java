package ui.panelhelp;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.SingletonClass;
import main.SuperButton;
import ui.panelmain.PanelMain;

public class ButtonCloseHelp extends SuperButton{
	
	private JPanel fatherPanel;
	private JFrame frame;
	
	public ButtonCloseHelp(JPanel fatherPanel){
		
		super(0, 0, 0, 0, null, null, null);
		this.fatherPanel = fatherPanel;
		this.frame = SingletonClass.getFrameInstance();
		
	}
	
	public void mouseClicked(MouseEvent e){
		
		super.mouseClicked(e);
		this.frame.setContentPane(new PanelMain());
		this.frame.remove(fatherPanel);
		this.frame.revalidate();
		
	}
	
}
