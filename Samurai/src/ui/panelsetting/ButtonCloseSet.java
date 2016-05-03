package ui.panelsetting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import main.SingletonClass;
import main.StaticButton;
import ui.panelmain.PanelMain;

public class ButtonCloseSet extends StaticButton implements ActionListener{

	private JPanel fatherPanel;
	
	public ButtonCloseSet(JPanel fatherPane){
		
		super(0, 0, 0, 0, null);
		
		this.fatherPanel = fatherPane;
		
		this.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae){
		
		this.frame.setContentPane(new PanelMain());
		this.frame.remove(this.fatherPanel);
		this.frame.revalidate();
		
	}
	
}
