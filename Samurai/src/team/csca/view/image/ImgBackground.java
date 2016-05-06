package team.csca.view.image;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImgBackground {
	/**
	 * 
	 * Background in the panel start game
	 * 
	 */
	public final static Image MAIN_PANEL_BACKGROUND1 = new ImageIcon("Image/MainPanel/background.png").getImage();
	
	public final static Image MAIN_PANEL_BACKGROUND2 = new ImageIcon("Image/MainPanel/title.png").getImage();
	
	/**
	 * 
	 * Background of panel help
	 * 
	 * Using Array to store this series of Image
	 * 
	 */
	
	public final static Image[] HELP_BACKGOUNDS;
	
	static{
		HELP_BACKGOUNDS = new Image[]{
				new ImageIcon("Image/HelpPanel/backgroundhelp_page1.png").getImage() ,
				new ImageIcon("Image/HelpPanel/backgroundhelp_page2.png").getImage() ,
				new ImageIcon("Image/HelpPanel/backgroundhelp_page3.png").getImage(),
				//TODO : Any more pictures;
				};
	}
	
	/**
	 * 
	 * Background of setting panel
	 * 
	 */
	
	public final static Image SETTING_PANEL_BACKGROUND = new ImageIcon("Image/SetPanel/back_set.png").getImage();
	
	
}
