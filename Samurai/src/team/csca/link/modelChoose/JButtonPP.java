package team.csca.link.modelChoose;

import java.awt.event.MouseEvent;

import team.csca.client.RemoteHelper;
import team.csca.server.User;
import team.csca.view.extend.DynamicButton;

public class JButtonPP extends DynamicButton{

	private JPanelMoodelChoose fatherPanel ;
	private User user = RemoteHelper.getInstance().getUser();
	
	public JButtonPP(JPanelMoodelChoose fatherPanel) {
		super(0,0,0,0,null,null,null);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		
	}
	
}
