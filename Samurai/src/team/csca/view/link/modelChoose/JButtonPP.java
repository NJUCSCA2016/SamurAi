package team.csca.view.link.modelChoose;

import java.awt.Image;
import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;

public class JButtonPP extends DynamicButton{

	private JPanelMoodelChoose fatherPanel ;
	
	public JButtonPP(JPanelMoodelChoose fatherPanel) {
		super(0,0,0,0,null,null,null);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		
	}
	
}
