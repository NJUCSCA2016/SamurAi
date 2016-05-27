package team.csca.link.modelChoose;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import team.csca.client.RemoteHelper;
import team.csca.control.netControl.NetGameControl;
import team.csca.link.gameWaiting.JPanelGameLoading;
import team.csca.link.tra.JPanelNetTra;
import team.csca.view.extend.DynamicButton;

public class JButtonPTra extends DynamicButton{
	
	private JPanelMoodelChoose fatherPanel ;
	private RemoteHelper helper = RemoteHelper.getInstance();
	
	public JButtonPTra(JPanelMoodelChoose fatherPanel) {
		super(0,0,0,0,null,null,null);
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
