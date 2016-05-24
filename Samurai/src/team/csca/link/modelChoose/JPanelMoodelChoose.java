package team.csca.link.modelChoose;

import java.awt.Graphics;

import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;

public class JPanelMoodelChoose extends JPanel{
	
	private LayerBackground background = new LayerBackground(0, 0, 1250, 700, null);
	
	public JPanelMoodelChoose() {
		setLayout(null);
		
		this.add(new JButtonPP(this));
		this.add(new JButtonPTro(this));
		this.add(new JButtonReturn(this));
	}
	
	public void paint(Graphics g){
		background.createWindow(g);
		super.paintComponent(g);
	}
	
	
}
