package team.csca.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class StartGame extends MainFrame{
	public Image img =  new ImageIcon("Image/Start/70.png").getImage();
	public StartGame() {
		repaint();
	}
	public void paint(Graphics g){
		g.drawImage(img, 0, 0, null);
	}
	
}
