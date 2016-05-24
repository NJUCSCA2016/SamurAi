package team.csca.link.signIn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import team.csca.controller.media.Player;
import team.csca.view.extend.StaticButton;
import team.csca.view.image.ImgLink;
import team.csca.view.startgame.JPanelStartGame;

public class JButtonReturn extends StaticButton implements ActionListener{
	
	private JPanelSignIn fatherPanel;
	
	
	public JButtonReturn( JPanelSignIn fatherPanel) {
		super(725,395,150,100,ImgLink.LOGIN_RETURN_INIT);
		this.addActionListener(this);
		this.fatherPanel = fatherPanel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		this.frame.remove(fatherPanel);
		this.frame.setContentPane(new JPanelStartGame());
		this.frame.revalidate();
		Player.stopMusic();
		Player.playMusic("bgm1");
	}
	
}
