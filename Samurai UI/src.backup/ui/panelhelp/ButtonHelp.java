package ui.panelhelp;

import java.awt.Graphics;

import javax.swing.JPanel;

import main.Background;
import main.SuperButton;

public class ButtonHelp extends SuperButton{
	
	private Background border = null;
	
	private Background center = null;
	
	public ButtonHelp(JPanel fatherPanel){
		
		super(0,0,0,0,null,null,null);
		
		border = new Background(null);
		center = new Background(null);
		
	}
	
	public void paintComponent(Graphics g){
		
		
		super.paintComponents(g);
	}
	
}
