package team.csca.link.modelChoose;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import team.csca.client.RemoteHelper;
import team.csca.control.netControl.NetGameControl;
import team.csca.link.gameWaiting.JPanelGameLoading;
import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgModel;

public class JButtonPTra extends DynamicButton{
	
	private JPanelModelChoose fatherPanel ;
	private RemoteHelper helper = RemoteHelper.getInstance();
	
	public JButtonPTra(JPanelModelChoose fatherPanel) {
		super(464,129,313,107,ImgModel.TRA_INIT,ImgModel.TRA_ENTER,ImgModel.TRA_CLICK);
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
			helper.setMoodle(1);
			helper.getUser().chooseModle(1, helper.getNotic(), helper.getName());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
