package team.csca.view.startgame;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;

/**
 * 
 * 退出按钮
 * @author With You
 *
 */
public class JButtonExit extends DynamicButton{
	
	public JButtonExit(){
		super(659, 408, 145, 115, ImgButton.BUTTON_EXIT_INIT, ImgButton.BUTTON_EXIT_ENTER, ImgButton.BUTTON_EXIT_CLICK);
		
	
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		
		System.exit(0);
	}
}
