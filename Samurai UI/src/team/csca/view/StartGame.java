package team.csca.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StartGame extends JPanel{
	private MainFrame frame;
	
	private Layer[] layers;
	
	public Image img =  new ImageIcon("Image/Start/70.png").getImage();

	public void paint(Graphics g){
		g.drawImage(img, 0, 0, null);
	}
	
	public StartGame(MainFrame frame){
		this.frame = frame;
		this.setLayout(null);
		layers = new Layer[] {
			new LayerBackground(0, 0, 1250, 700, img),	
		};
	}
	
}
