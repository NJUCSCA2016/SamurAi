package team.csca.view.pm;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;
import team.csca.view.pm.JPanelSetting;
import team.csca.view.startgame.JPanelStartGame;

public class JButtonExitSet extends DynamicButton {
	private JPanelSetting fatherPanel;
	
	private JPanelPM jPanelPM;

	public JButtonExitSet(JPanelSetting fatherPanel) {
		super(834, 148, 60, 60, ImgButton.RETURN_FROM_INIT, ImgButton.RETURN_FROM_ENTER, ImgButton.RETURN_FROM_CLICKED);
		this.fatherPanel = fatherPanel;
	}

	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);

		this.frame.remove(this.fatherPanel);
		jPanelPM = new JPanelPM();
		this.frame.setContentPane(jPanelPM);
		jPanelPM.requestFocus();
		this.frame.revalidate();

	}
}
