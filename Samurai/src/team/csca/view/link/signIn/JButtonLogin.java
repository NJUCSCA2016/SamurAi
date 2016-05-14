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
 * 登入游戏
 * @author With You
 *
 */

public class JButtonLogin extends StaticButton implements ActionListener{
	
	private JPanelSignIn fatherPanel;
	
	private RemoteHelper helper = RemoteHelper.getInstance();
	
	public JButtonLogin( JPanelSignIn fatherPanel) {
		super(0,0,0,0,null);
		this.addActionListener(this);
		this.fatherPanel = fatherPanel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
//		this.frame.remove(fatherPanel);
//		this.frame.setContentPane(new JPanelStartGame());
//		this.frame.revalidate();
//		Player.stopMusic();
//		Player.playMusic("bgm1");
		//进入游戏模式选择界面。
		try {
		 int stateCode = helper.getUser().login(fatherPanel.getName(), fatherPanel.getPassword());
		 if(stateCode == 0){
			 //Login success
		 }else if(stateCode == 1){
			 //No this user
		 }else{
			 //Password is wrong . 
		 }
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
}
