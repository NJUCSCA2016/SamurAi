package team.csca.view.startgame;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.frame.JFrameMain;
import team.csca.view.help.JPanelHelp;
import team.csca.view.image.ImgButton;

/**
 * 
 * 帮助按钮
 * @author With You
 *
 */
public class JButtonHelp extends DynamicButton{
	private JPanelStartGame fatherPanel;
	private JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	
	public JButtonHelp(JPanelStartGame fatherPanel){
		super(455, 420, 162, 135, ImgButton.BUTTON_HELP_INIT, ImgButton.BUTTON_HELP_ENTER, ImgButton.BUTTON_HELP_CLICK);
		
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		this.setButtonImg(ImgButton.BUTTON_HELP_ENTER);
		repaint();
		frameMain.setContentPane(new JPanelHelp());
		frameMain.remove(fatherPanel);
		frameMain.revalidate();
	}
}
