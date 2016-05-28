package team.csca.view.pm;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;

public class JButtonExit extends DynamicButton  {
	private JPanelPM fatherPanel;
	public JButtonExit(JPanelPM jPanelSetting){
		super(300, 10, 100, 100, ImgButton.EXIT_1, ImgButton.EXIT_2, ImgButton.EXIT_3);
		this.fatherPanel = jPanelSetting;
	}
	
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		System.exit(0);
	}
}
