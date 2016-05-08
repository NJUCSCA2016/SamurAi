package team.csca.view.set;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import team.csca.controller.media.Player;
import team.csca.view.extend.StaticButton;
import team.csca.view.image.ImgSystem;

public class JButtonBackSound extends StaticButton implements ActionListener{
	
	private static boolean backSoundOn = true;
	
	public JButtonBackSound() {
		//TODO : add the button and its configure
		super(728, 273, 47, 47, null);
		if (backSoundOn) {
			this.setButtonImg(ImgSystem.TICK);
		}else if(!backSoundOn){
			this.setButtonImg(null);
		}
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(backSoundOn){
			JButtonBackSound.backSoundOn = false;
			this.setButtonImg(null);
			Player.MUSiC_PLAYER.changeBack_ON();
			Player.stopMusic();
			
		}else{
			JButtonBackSound.backSoundOn = true;
			//TODO : Add the initial button
			this.setButtonImg(ImgSystem.TICK);
			Player.MUSiC_PLAYER.changeBack_ON();
			Player.playMusic("bgm1");
		}
		
	}
	

	
}
