package team.csca.link.modelChoose;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import team.csca.client.RemoteHelper;
import team.csca.control.netControl.NetGameControl;
import team.csca.link.gameWaiting.JPanelGameLoading;
import team.csca.server.User;
import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgModel;

public class JButtonPP extends DynamicButton{

	private JPanelModelChoose fatherPanel ;
	private RemoteHelper helper = RemoteHelper.getInstance();
	
	public JButtonPP(JPanelModelChoose fatherPanel) {
		super(464,320,313,107,ImgModel.PROP_INIT,ImgModel.PROP_ENTER,ImgModel.PROP_CLICK);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		try {
			NetGameControl control = NetGameControl.getInstance();
			helper.getNotic().setControl(control);
			
			this.frame.setContentPane(new JPanelGameLoading());
			this.frame.remove(this.fatherPanel);
			frame.revalidate();
			helper.setMoodle(2);
			helper.getUser().chooseModle(2, helper.getNotic(), helper.getName());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
}
