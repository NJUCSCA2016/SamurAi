package team.csca.view.set;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import team.csca.controller.media.Player;
import team.csca.view.extend.StaticButton;
import team.csca.view.image.ImgSystem;

public class JButtonBackSound extends StaticButton implements ActionListener{
	
//	private static boolean backSoundOn = true;
	
	public JButtonBackSound() {
		
		super(715, 250, 52, 52, null);
		
		if (Player.MUSiC_PLAYER.isBack_ON()) {
			this.setButtonImg(ImgSystem.TICK);
		}
		
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(Player.MUSiC_PLAYER.isBack_ON()){
			this.setButtonImg(null);
			Player.MUSiC_PLAYER.changeBack_ON();
			Player.stopMusic();
			
		}else{
			this.setButtonImg(ImgSystem.TICK);
			Player.MUSiC_PLAYER.changeBack_ON();
			Player.MUSiC_PLAYER.setBackVol(Player.MUSiC_PLAYER.getVolBack());
			Player.playMusic("bgm1");
			Player.MUSiC_PLAYER.setBackVol(Player.MUSiC_PLAYER.getVolBack());
			Player.MUSiC_PLAYER.pauseBack();
			Player.MUSiC_PLAYER.rePlay();
		}
		
		repaint();
	}
	
	
}
