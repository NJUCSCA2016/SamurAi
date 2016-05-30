package team.csca.link.modelChoose;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import team.csca.client.GameNoticeImp;
import team.csca.client.RemoteHelper;
import team.csca.control.netControl.NetGameControl;
import team.csca.link.gameWaiting.JPanelGameLoading;
import team.csca.server.GameNotice;
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
			GameNoticeImp notice = helper.getNotic();
//			RemoteHelper.getInstance().setNotic(notice);
			NetGameControl control = NetGameControl.getInstance();
			notice.setControl(control);
			this.frame.remove(this.fatherPanel);
			this.frame.setContentPane(new JPanelGameLoading());
//			this.frame.setContentPane(new JPanelLinkLoading());
			
			frame.revalidate();
			helper.setMoodle(2);
			helper.getUser().chooseModel(2, (GameNotice)notice, helper.getName());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
}
