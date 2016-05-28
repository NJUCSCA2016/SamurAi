package team.csca.view.propPattern;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgButton;
public class JButtonSetting extends DynamicButton{
	
	private JPanelPropPattern fatherPanel;
	private JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	
	public JButtonSetting(JPanelPropPattern fatherPanel){
		super(410, 10, 100, 100, ImgButton.SETTING_1, ImgButton.SETTING_2, ImgButton.SETTING_3);
		
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