package team.csca.view.startgame;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgButton;
import team.csca.view.set.JPanelSetting;

/**
 * 
 * 设置按钮
 * @author With You
 *
 */
public class JButtonSetting extends DynamicButton{
	
	private JPanelStartGame fatherPanel;
	private JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	
	public JButtonSetting(JPanelStartGame fatherPanel){
		super(801, 217, 150, 50, ImgButton.BUTTON_SET_INIT, ImgButton.BUTTON_SET_ENTER, ImgButton.BUTTON_SET_CLICK);
		
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		this.setButtonImg(ImgButton.BUTTON_SET_ENTER);
		frameMain.setContentPane(new JPanelSetting());
		frameMain.remove(fatherPanel);
		frameMain.revalidate();
	}
	
}
