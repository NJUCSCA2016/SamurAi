package team.csca.view.link.signIn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import team.csca.client.RemoteHelper;
import team.csca.controller.media.Player;
import team.csca.view.extend.StaticButton;
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
		super(0,0,0,0,null);
		this.addActionListener(this);
		this.fatherPanel = fatherPanel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			boolean canReg =  helper.getUser().signIn(fatherPanel.getName(), fatherPanel.getPassword());
			if(! canReg){
				//Been registered already
			}else{
				//succeed.
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
}
