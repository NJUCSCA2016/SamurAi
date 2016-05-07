package team.csca.view.set;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import team.csca.controller.media.Player;
import team.csca.view.extend.StaticButton;

public class JButtonBackSound extends StaticButton implements ActionListener{
	
	private boolean backSoundOn = true;
	
	public JButtonBackSound() {
		//TODO : add the button and its configure
		super(0, 0, 0, 0, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(backSoundOn){
			this.backSoundOn = false;
			this.setButtonImg(null);
			Player.MUSiC_PLAYER.turnOffBack();
		}else{
			this.backSoundOn = true;
			//TODO : Add the initial button
			this.setButtonImg(null);
			Player.MUSiC_PLAYER.turnOnBack();
		}
		
	}
	

	
}
