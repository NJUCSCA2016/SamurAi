package team.csca.view.propPattern;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;
import team.csca.view.propPattern.JPanelSetting;

public class JButtonExitSet extends DynamicButton {
	private JPanelSetting fatherPanel;
	
	private JPanelPropPattern jPanelPropPattern;

	public JButtonExitSet(JPanelSetting fatherPanel) {
		super(834, 148, 60, 60, ImgButton.RETURN_FROM_INIT, ImgButton.RETURN_FROM_ENTER, ImgButton.RETURN_FROM_CLICKED);
		this.fatherPanel = fatherPanel;
	}

	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);

		this.frame.remove(this.fatherPanel);
		jPanelPropPattern = new JPanelPropPattern();
		this.frame.setContentPane(jPanelPropPattern);
		jPanelPropPattern.requestFocus();
		this.frame.revalidate();

	}
}

