package team.csca.view.startgame;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgButton;

/**
 * 
 * 人人对战
 * @author With You
 *
 */
public class JButtonPP extends DynamicButton{
	private JPanelStartGame fatherPanel;
	private JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	
	public JButtonPP(JPanelStartGame fatherPanel){
		super(381, 217, 150, 50, ImgButton.BUTTON_PEPOLE_INIT, ImgButton.BUTTON_PEOPLE_ENTER, ImgButton.BUTTON_PEOPLE_CLICK);
		
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		this.setButtonImg(ImgButton.BUTTON_PEOPLE_ENTER);
		//TODO : 完成人机对战面板
//		frameMain.setContentPane(null);
		frameMain.remove(fatherPanel);
		frameMain.revalidate();
		
	}
}
