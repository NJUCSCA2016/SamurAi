package team.csca.view.filmProp;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import team.csca.view.extend.DynamicButton;
import team.csca.view.extend.LayerBackground;

public class JPanelStory extends JPanel {

	public int numOfPic = 0;
	
	public final static int maxPic = 5;
	
	public final static int FINAL_PIC = 11;

	private Image image;

	private LayerBackground background;

	public JPanelStory(int numOfPic) {
		this.numOfPic = numOfPic;
		this.setLayout(null);
		background = new LayerBackground(0, 0, 1250, 700, getImage(numOfPic));
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
	
	public void setButton(DynamicButton button){
		this.add(button);
		repaint();
	}


}
