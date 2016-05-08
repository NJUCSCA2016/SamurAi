package team.csca.view.set;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import team.csca.controller.media.Player;
import team.csca.view.extend.StaticButton;
import team.csca.view.image.ImgSystem;

public class JButtonBackSound extends StaticButton implements ActionListener{
	
	private boolean backSoundOn = true;
	
	public JButtonBackSound() {
		//TODO : add the button and its configure
		super(728, 273, 47, 47, ImgSystem.TICK);
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(backSoundOn){
			this.backSoundOn = false;
			this.setButtonImg(null);
			Player.MUSiC_PLAYER.changeBack_ON();
			Player.MUSiC_PLAYER.stopMusic();
			
		}else{
			this.backSoundOn = true;
			//TODO : Add the initial button
			this.setButtonImg(ImgSystem.TICK);
			Player.MUSiC_PLAYER.changeBack_ON();
			Player.MUSiC_PLAYER.playMusic("bgm");
		}
		
	}
	

	
}
