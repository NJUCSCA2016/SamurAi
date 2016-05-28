package team.csca.view.pm;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;

public class JButtonExit extends DynamicButton  {
	private JPanelPM fatherPanel;
	public JButtonExit(JPanelPM fatherPanel){
		super(300, 10, 100, 100, ImgButton.EXIT, ImgButton.EXIT, ImgButton.EXIT);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		System.exit(0);
	}
}
