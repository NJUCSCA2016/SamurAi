package team.csca.link.modelChoose;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import team.csca.client.RemoteHelper;
import team.csca.link.signIn.JPanelSignIn;
import team.csca.view.extend.DynamicButton;

public class JButtonReturn extends DynamicButton{
	
	private JPanelMoodelChoose fatherPanel ;
	
	
	public JButtonReturn(JPanelMoodelChoose fatherPanel) {
		super(0,0,0,0,null,null,null);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		try {
			RemoteHelper.getInstance().getUser().logout(RemoteHelper.getInstance().getName());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.frame.remove(this.fatherPanel);
		this.frame.setContentPane(new JPanelSignIn());
		
	}
	
}
