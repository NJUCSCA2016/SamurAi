package team.csca.view.link.signIn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import team.csca.client.RemoteHelper;
import team.csca.controller.media.Player;
import team.csca.view.extend.StaticButton;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgLink;
import team.csca.view.startgame.JPanelStartGame;

/**
 * 
 * 注册游戏
 * @author With You
 *
 */

public class JButtonRegister extends StaticButton implements ActionListener{
	
	private JPanelSignIn fatherPanel;
	
	private RemoteHelper helper = RemoteHelper.getInstance();
	
	public JButtonRegister( JPanelSignIn fatherPanel) {
		super(575,395,150,100,ImgLink.LOGIN_REGISTER_INIT);
		this.addActionListener(this);
		this.fatherPanel = fatherPanel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			boolean canReg =  helper.getUser().signIn(fatherPanel.getName(), fatherPanel.getPassword());
			if(! canReg){
				//Been registered already
				fatherPanel.cleanName();
				fatherPanel.cleanPass();
				JFrameMain.J_FRAME_MAIN.setEnabled(false);
				new Dialog(ImgLink.SIGNIN_NAME_EXIST);
			}else{
				//succeed.
				JFrameMain.J_FRAME_MAIN.setEnabled(false);
				new Dialog( ImgLink.SIGNIN_SUCCEED);
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
}
