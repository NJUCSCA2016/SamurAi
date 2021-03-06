package team.csca.view.startgame;

import java.awt.event.MouseEvent;

import team.csca.controller.media.Player;
import team.csca.view.extend.DynamicButton;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgButton;
import team.csca.view.pm.JPanelPM;

/**
 * 
 * 人机对战
 * @author With You
 *
 */
public class JButtonPM extends DynamicButton{
	private JPanelStartGame fatherPanel;
	private JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	private JPanelPM jPanelPM = new JPanelPM();
	public JButtonPM(JPanelStartGame fatherPanel){
		super(472, 319, 150, 50, ImgButton.BUTTON_MACHINE_INIT, ImgButton.BUTTON_MACHINE_ENTER, ImgButton.BUTTON_MACHINE_CLICK);
		
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		this.setButtonImg(ImgButton.BUTTON_MACHINE_ENTER);
		frameMain.remove(fatherPanel);
		frameMain.setContentPane(jPanelPM);
		jPanelPM.requestFocus();
		Player.stopMusic();
		if (Player.MUSiC_PLAYER.isBack_ON()) {
			Player.playMusic("bgm2");
		}
		frameMain.revalidate();
		
	}
}
