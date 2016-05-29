package team.csca.link.signIn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import team.csca.client.RemoteHelper;
import team.csca.link.modelChoose.JPanelChooseList;
import team.csca.view.extend.StaticButton;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgLink;

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
		super(425,395,150,100,ImgLink.LOGIN_LOGIN_INIT);
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
			 System.out.println("Success");
			 RemoteHelper.getInstance().setName(fatherPanel.getName());
			 frame.setContentPane(new JPanelChooseList());
			 frame.remove(this.fatherPanel);
			 frame.revalidate();
		 }else if(stateCode == 1){
			 //No this user
			 fatherPanel.cleanName();
			 fatherPanel.cleanPass();
			 frame.remove(fatherPanel);
			 frame.setContentPane(new Dialog(ImgLink.SIGNIN_NAME_NOT_FOUND , fatherPanel));
			 frame.revalidate();
		 }else{
			 //Password is wrong . 
			 fatherPanel.cleanPass();
			 frame.remove(fatherPanel);
			 frame.setContentPane(new Dialog( ImgLink.SIGNIN_WORD_WRONG , fatherPanel));
			 frame.revalidate();			 
		 }
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
}
