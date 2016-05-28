package team.csca.view.propPattern;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;
import team.csca.view.pm.JPanelPM;

public class JButtonExit extends DynamicButton  {
	private JPanelPropPattern fatherPanel;
	public JButtonExit(JPanelPropPattern fatherPanel){
		super(300, 10, 100, 100, ImgButton.EXIT_1, ImgButton.EXIT_2, ImgButton.EXIT_3);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		System.exit(0);
	}
}
