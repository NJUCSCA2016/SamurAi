package team.csca.link.modelChoose;

import java.awt.Graphics;

import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgModel;

public class JPanelModelChoose extends JPanel{
	
	private LayerBackground background = new LayerBackground(0, 0, 1250, 700, ImgModel.CHOOSE_MOVIE[15]);
	
	public JPanelModelChoose() {
		setLayout(null);
		
		this.add(new JButtonPP(this));
		this.add(new JButtonPTra(this));
		this.add(new JButtonReturn(this));
	}
	
	public void paint(Graphics g){
		background.createWindow(g);
		super.paintComponent(g);
	}
	
	
}
