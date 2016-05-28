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
				new ImageIcon("Image/HelpPanel/backgroundhelp_page0.png").getImage(),
				new ImageIcon("Image/HelpPanel/backgroundhelp_page1.png").getImage() ,
				new ImageIcon("Image/HelpPanel/backgroundhelp_page2.png").getImage() ,
				new ImageIcon("Image/HelpPanel/backgroundhelp_page3.png").getImage(),
				new ImageIcon("Image/HelpPanel/backgroundhelp_page4.png").getImage(),
				new ImageIcon("Image/HelpPanel/backgroundhelp_page5.png").getImage(),
				new ImageIcon("Image/HelpPanel/backgroundhelp_page6.png").getImage(),
				};
	}
	
	/**
	 * 
	 * Background of setting panel
	 * 
	 */
	
	public final static Image MAIN_SETTING_PANEL_BACKGROUND = new ImageIcon("Image/SetPanel/main_back_set.png").getImage();
	
	public final static Image PM_PANEL = new ImageIcon("Image/Gamepanel/bg.png").getImage();

	public final static Image MODEL_CHOOSE = new ImageIcon("Image/ModelChoose/13.png").getImage();
	
	public final static Image PM_SETTING = new ImageIcon("Image/SetPanel/game_back_set.png").getImage();
}
