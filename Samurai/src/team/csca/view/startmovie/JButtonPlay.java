package team.csca.view.startmovie;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;

/**
 * 
 * @author YYM
 *
 */
public class JButtonPlay extends DynamicButton{

	private JPanelStartMovie fatherPanel;
	
	public JButtonPlay(JPanelStartMovie fatherPanel){
		super(385, 480, 300, 80, ImgButton.PLAY_1, ImgButton.PLAY_2, ImgButton.PLAY_3);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		
		super.mouseClicked(e);
//		this.setBounds(390, 495, 220, 60);
		this.setButtonImg(ImgButton.PLAY_2);
		try {
			Thread.sleep(10);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		this.fatherPanel.startTwo();
	}
	

}
