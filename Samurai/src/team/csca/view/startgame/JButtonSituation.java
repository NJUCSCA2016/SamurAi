package team.csca.view.startgame;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgButton;

public class JButtonSituation extends DynamicButton{
	private JPanelStartGame fatherPanel;
	private JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	
	public JButtonSituation(JPanelStartGame fatherPanel){
		super(591, 217, 150, 50, ImgButton.BUTTON_SITUATION_INIT, ImgButton.BUTTON_SITUATION_ENTER, ImgButton.BUTTON_SITUATION_CLICK);
		
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		this.setButtonImg( ImgButton.BUTTON_SITUATION_ENTER);
		//TODO : 完成情景模式面板
//		frameMain.setContentPane(null);
		frameMain.remove(fatherPanel);
		frameMain.revalidate();
	}
}
