package team.csca.view.startgame;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgButton;

/**
 * 
 * 道具模式
 * @author With You
 *
 */
public class JButtonProps extends DynamicButton{
	
	private JPanelStartGame fatherPanel;
	private JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	
	public JButtonProps(JPanelStartGame fatherPanel) {
		super(675, 319, 150, 50, ImgButton.BUTTON_PROPS_INIT, ImgButton.BUTTON_PROPS_ENTER, ImgButton.BUTTON_PROPS_CLICK);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		this.setButtonImg( ImgButton.BUTTON_PROPS_ENTER);
		//TODO : 完成道具模式面板。
//		frameMain.setContentPane(null);
		frameMain.remove(fatherPanel);
		frameMain.revalidate();
	}
	
}
