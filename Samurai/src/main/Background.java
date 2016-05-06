package main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Background extends JPanel{

	private Image background = null;
	
	public Background(Image background) {
		super();
		this.background = background;
	}

	public void setBackground(Image back){
		this.background = back;
	}
	
	
	public void paintComponent(Graphics g){
		g.drawImage(background, 0, 0, null);
	}
	
	public void creatBack(Graphics g){
		this.paintComponent(g);
	}
	
}
