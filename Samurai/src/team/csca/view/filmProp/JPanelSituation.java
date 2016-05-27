package team.csca.view.filmProp;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;

public class JPanelSituation extends JPanel {

	public static int numOfPic = 0;

	private Image image;

	private LayerBackground background;

	public JPanelSituation() {
		this.setLayout(null);
		background = new LayerBackground(0, 0, 1250, 700, getImage(0));
		JButtonNextPic buttonNext = new JButtonNextPic(this);
		this.add(buttonNext);
	}

	private Image getImage(int i) {
		image = new ImageIcon("Image/Gamepanel/situation/" + i + ".png").getImage();
		return image;
	}

	public void paintComponent(Graphics g) {
		this.background.createWindow(g);

		super.paintComponents(g);
		
	}
	
	protected void updateBackground(){
		background.setImg(getImage(numOfPic));
//		System.out.println("Pic = " + this.numOfPic);
		repaint();
	}
	


}
