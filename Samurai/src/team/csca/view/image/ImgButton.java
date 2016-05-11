package team.csca.view.image;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImgButton {
	/**
	 * JPanelStartMovie里的Button
	 */
	public static Image PLAY_1 = new ImageIcon("Image/Button/P1.png").getImage();
	
	public static Image PLAY_2 = new ImageIcon("Image/Button/P2.png").getImage();
	
	public static Image PLAY_3 = new ImageIcon("Image/Button/P3.png").getImage();
	
	/**
	 * Button in JPanelStartGame.
	 */
	
	
	/**
	 *  退出按钮
	 */
	public final static Image BUTTON_EXIT_INIT;
	
	public final static Image BUTTON_EXIT_ENTER;
	
	public final static Image BUTTON_EXIT_CLICK;
	
	static{
		
		BUTTON_EXIT_INIT = new ImageIcon("Image/MainPanel/button_exit1.png").getImage();
		BUTTON_EXIT_ENTER = new ImageIcon("Image/MainPanel/button_exit2.png").getImage();
		BUTTON_EXIT_CLICK  = new ImageIcon("Image/MainPanel/button_exit3.png").getImage();
		
	}
	/**
	 * 帮助按钮
	 */
	public final static Image BUTTON_HELP_INIT;
	
	public final static Image BUTTON_HELP_ENTER;
	
	public final static Image BUTTON_HELP_CLICK;
	
	static{
		
		BUTTON_HELP_INIT = new ImageIcon("Image/MainPanel/button_help1.png").getImage();
		BUTTON_HELP_ENTER = new ImageIcon("Image/MainPanel/button_help2.png").getImage();
		BUTTON_HELP_CLICK = new ImageIcon("Image/MainPanel/button_help3.png").getImage();
		
	}
	/**
	 * 人人对战
	 */
	public final static Image BUTTON_PEPOLE_INIT;
	
	public final static Image BUTTON_PEOPLE_ENTER;
	
	public final static Image BUTTON_PEOPLE_CLICK;
	
	static{
		
		BUTTON_PEPOLE_INIT  = new ImageIcon("Image/MainPanel/button1_1.png").getImage();
		BUTTON_PEOPLE_ENTER = new ImageIcon("Image/MainPanel/button2_1.png").getImage();
		BUTTON_PEOPLE_CLICK = new ImageIcon("Image/MainPanel/button3_1.png").getImage();
		
	}
	
	/**
	 * 人机对战
	 */
	public final static Image BUTTON_MACHINE_INIT;
	
	public final static Image BUTTON_MACHINE_ENTER;
	
	public final static Image BUTTON_MACHINE_CLICK;
	
	static{
		
		BUTTON_MACHINE_INIT = new ImageIcon("Image/MainPanel/button1_4.png").getImage();
		BUTTON_MACHINE_ENTER = new ImageIcon("Image/MainPanel/button2_4.png").getImage();
		BUTTON_MACHINE_CLICK = new ImageIcon("Image/MainPanel/button3_4.png").getImage();
		
	}
	
	/**
	 * 情景模式
	 */
	public final static Image BUTTON_SITUATION_INIT;
	
	public final static Image BUTTON_SITUATION_ENTER;
	
	public final static Image BUTTON_SITUATION_CLICK;
	
	static{
		
		BUTTON_SITUATION_INIT = new ImageIcon("Image/MainPanel/button1_2.png").getImage();;
		BUTTON_SITUATION_ENTER = new ImageIcon("Image/MainPanel/button2_2.png").getImage();
		BUTTON_SITUATION_CLICK = new ImageIcon("Image/MainPanel/button3_2.png").getImage();
		
	}
	
	public final static Image BUTTON_PROPS_INIT;
	
	public final static Image BUTTON_PROPS_ENTER;
	
	public final static Image BUTTON_PROPS_CLICK;
	
	static{
		
		BUTTON_PROPS_INIT = new ImageIcon("Image/MainPanel/button1_3.png").getImage();
		BUTTON_PROPS_ENTER = new ImageIcon("Image/MainPanel/button2_3.png").getImage();
		BUTTON_PROPS_CLICK = new ImageIcon("Image/MainPanel/button3_3.png").getImage();
		
	}
	
	/**
	 * 设置按钮
	 */
	public final static Image BUTTON_SET_INIT;
	
	public final static Image BUTTON_SET_ENTER;
	
	public final static Image BUTTON_SET_CLICK;
	
	static{
		
		BUTTON_SET_INIT = new ImageIcon("Image/MainPanel/button1_5.png").getImage();
		BUTTON_SET_ENTER = new ImageIcon("Image/MainPanel/button2_5.png").getImage();
		BUTTON_SET_CLICK = new ImageIcon("Image/MainPanel/button3_5.png").getImage();
		
	}

	/**
	 * 
	 * Button in Help Panel
	 * 
	 */
	public final static Image NEXT_RIGHT_INIT;
	
	public final static Image NEXT_RIGHT_ENTER;
	
	public final static Image NEXT_RIGHT_CLICKED;
	
	public final static Image NEXT_RIGHT_ENABLE;
	
	public final static Image NEXT_LEFT_INIT;
	
	public final static Image NEXT_LEFT_ENTER;
	
	public final static Image NEXT_LEFT_CLICKED;
	
	public final static Image NEXT_LEFT_ENABLE;
	
	
	static{
		//TODO : Finishing the two picture of the initial type of two button 
		NEXT_RIGHT_INIT = new ImageIcon("Image/HelpPanel/right_button_init.png").getImage();
		
		NEXT_RIGHT_ENTER = new ImageIcon("Image/HelpPanel/right_button_enter.png").getImage();
		NEXT_RIGHT_CLICKED = new ImageIcon("Image/HelpPanel/right_button_clicked.png").getImage();
		NEXT_RIGHT_ENABLE = new ImageIcon("Image/HelpPanel/right_button_enable.png").getImage();
		
		NEXT_LEFT_INIT = new ImageIcon("Image/HelpPanel/left_button_init.png").getImage();
		
		NEXT_LEFT_CLICKED =  new ImageIcon("Image/HelpPanel/left_button_clicked.png").getImage();
		NEXT_LEFT_ENTER = new ImageIcon("Image/HelpPanel/left_button_enter.png").getImage();
		NEXT_LEFT_ENABLE = new ImageIcon("Image/HelpPanel/left_button_enable.png").getImage();
		
		
	}
	/**
	 * 
	 * 面板通用返回按钮
	 * 
	 */
	public final static Image RETURN_FROM_INIT;
	
	public final static Image RETURN_FROM_ENTER;
	
	public final static Image RETURN_FROM_CLICKED;
	
	
	static{
		
		RETURN_FROM_INIT = new ImageIcon("Image/HelpPanel/return_button_init.png").getImage();
		RETURN_FROM_ENTER = new ImageIcon("Image/HelpPanel/return_button_enter.png").getImage();
		RETURN_FROM_CLICKED = new ImageIcon("Image/HelpPanel/return_button_clicked.png").getImage();
		
	}
	
	/**
	 * 
	 * 人机对战里的按钮
	 * 
	 */
	public final static Image BACK_INIT;
	
	public final static Image BACK_ENTER;
	
	public final static Image BACK_CLICKED;
	
	static{
		// E:\大一下\SamurAi\Samurai\Image\Gamepanel\buttons
		BACK_INIT = new ImageIcon("Image/Gamepanel/buttons/back.png").getImage();
		// TODO:换图
		BACK_ENTER = new ImageIcon("Image/Gamepanel/buttons/back.png").getImage();
		// TODO:换图
		BACK_CLICKED = new ImageIcon("Image/Gamepanel/buttons/back.png").getImage();
	}
	
	
}
