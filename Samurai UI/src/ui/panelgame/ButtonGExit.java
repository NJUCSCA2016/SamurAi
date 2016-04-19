package ui.panelgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.SingletonClass;
import main.StaticButton;
import ui.panelmain.PanelMain;

public class ButtonGExit extends StaticButton implements ActionListener{
	
	private JPanel fatherPanel;
	
	public ButtonGExit(JPanel fatherPanel){
		
		super(0, 0, 0, 0, null);
		this.frame = SingletonClass.getFrameInstance();
		this.fatherPanel = fatherPanel;
		this.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae){
		
		this.frame.setContentPane(new PanelMain());
		this.frame.remove(this.fatherPanel);
		this.frame.revalidate();
		
	}
	
}
