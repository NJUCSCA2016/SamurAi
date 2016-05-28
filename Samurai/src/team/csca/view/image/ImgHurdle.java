package team.csca.view.image;

import java.awt.Image;

import javax.swing.ImageIcon;
 
public class ImgHurdle {
	
	public final static Image[] HURDLES_INIT ;
	public final static Image[] HURDLES_ENTER;
	public final static Image[] HURDLES_CLICKED;
	
	static{
		
		HURDLES_INIT = new Image[5];
		HURDLES_ENTER = new Image[5];
		HURDLES_CLICKED = new Image[5];
		for(int i = 0 ; i < 5 ; i ++){
			HURDLES_INIT[i] = new ImageIcon("Image/GamePanel/situation/map_button"+ i +"_1.png").getImage();
			HURDLES_ENTER[i] = new ImageIcon("Image/GamePanel/situation/map_button"+ i +"_2.png").getImage();
			HURDLES_CLICKED[i] = new ImageIcon("Image/GamePanel/situation/map_button"+ i +"_3.png").getImage(); 
		}
		
	}
	
	public final static Image BUTTON_RETURN_INIT = new ImageIcon("Image/GamePanel/situation/button_return_unclicked.png").getImage();
	
	public final static Image BUTTON_RETURN_ENTER = new ImageIcon("Image/GamePanel/situation/button_return_enter.png").getImage();
	
	public final static Image BUTTON_RETURN_CLICKED = new ImageIcon("Image/GamePanel/situation/button_return_clicked.png").getImage();
	
}
