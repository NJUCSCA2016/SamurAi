package team.csca.view.image;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImgOthers {
	
	public final static Image[] SOUNDS_VOLUME;
	
	static{
		
		SOUNDS_VOLUME = new Image[11];
		
		for(int i = 0 ; i < 11 ; i++){
			SOUNDS_VOLUME[i] = new ImageIcon("Image/SetPanel/"+ i + ".png").getImage();
		}
		
	}
	
	
	
}
