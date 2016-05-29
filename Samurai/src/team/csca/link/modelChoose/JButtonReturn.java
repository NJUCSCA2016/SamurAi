package team.csca.link.modelChoose;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import team.csca.client.RemoteHelper;
import team.csca.link.signIn.JPanelSignIn;
import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgModel;

public class JButtonReturn extends DynamicButton{
	
	private JPanelModelChoose fatherPanel ;
	
	
	public JButtonReturn(JPanelModelChoose fatherPanel) {
		super(464,508,313,107,ImgModel.RETURN_INIT,ImgModel.RETURN_ENTER,ImgModel.RETURN_CLICK);
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
		frame.revalidate();
	}
	
}
