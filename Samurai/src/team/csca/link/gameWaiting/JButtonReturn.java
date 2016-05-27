package team.csca.link.gameWaiting;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import team.csca.client.RemoteHelper;
import team.csca.link.signIn.JPanelSignIn;
import team.csca.view.extend.DynamicButton;

public class JButtonReturn extends DynamicButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6210352858197983480L;
	
	private JPanelGameLoading fatherPanel;
	private RemoteHelper helper = RemoteHelper.getInstance();
	
	public JButtonReturn(int x, int y, int w, int h, Image initImage, Image enterImage, Image clickImage , JPanelGameLoading fatherPanel) {
		super(x, y, w, h, initImage, enterImage, clickImage);
		this.fatherPanel = fatherPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		try {
			this.helper.getUser().exitChoose(helper.getMoodle(), helper.getName());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.frame.remove(fatherPanel);
		this.frame.setContentPane(new JPanelSignIn());
		this.frame.revalidate();
	}
	
	
	
}
