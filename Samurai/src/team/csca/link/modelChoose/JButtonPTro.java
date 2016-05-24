package team.csca.link.modelChoose;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;

public class JButtonPTro extends DynamicButton{
	
	private JPanelMoodelChoose fatherPanel ;
	
	public JButtonPTro(JPanelMoodelChoose fatherPanel) {
		super(0,0,0,0,null,null,null);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		
	}
	
}
