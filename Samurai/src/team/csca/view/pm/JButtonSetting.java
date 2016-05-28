package team.csca.view.pm;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgButton;
import team.csca.view.pm.JPanelSetting;

public class JButtonSetting extends DynamicButton{
	
	private JPanelPM fatherPanel;
	private JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	
	public JButtonSetting(JPanelPM fatherPanel){
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
