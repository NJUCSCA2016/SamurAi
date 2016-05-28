package team.csca.view.image;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImgMap {
	public final static Image[] MAPS ;
	 
	static{
		MAPS = new Image[5];
		for(int i = 0 ; i < 5 ; i ++){
			MAPS[i] = new ImageIcon("Image/GamePanel/situation/map/map" + (i+1) + ".png").getImage();
		}
	}
	
}
