package team.csca.view.link.modelChoose;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.link.signIn.JPanelSignIn;

public class JButtonReturn extends DynamicButton{
	
	private JPanelMoodelChoose fatherPanel ;
	
	
	public JButtonReturn(JPanelMoodelChoose fatherPanel) {
		super(0,0,0,0,null,null,null);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		
		this.frame.remove(this.fatherPanel);
		this.frame.setContentPane(new JPanelSignIn());
		
	}
	
}
