package team.csca.view.set;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import team.csca.controller.media.Player;
import team.csca.view.extend.StaticButton;
import team.csca.view.image.ImgSystem;

public class JButtonGameSound extends StaticButton implements ActionListener{
	
	/**
	 *  游戏音效默认为开启
	 */
	
//	private static boolean gameSoundOn = true;
	
	public JButtonGameSound(){
		super(715, 375, 52, 52, null);
		
		if(Player.MUSiC_PLAYER.isGame_ON()){
			this.setButtonImg(ImgSystem.TICK);
		}
		
		this.addActionListener(this);
	}
	/**
	 * 单击时。如果gameSoundOn = true 说明之前游戏音效为打开
	 * 此时则更新为关闭
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(Player.MUSiC_PLAYER.isGame_ON()){
			
			this.setButtonImg(null);
			Player.MUSiC_PLAYER.changeGame_ON();
		}else{
			this.setButtonImg(ImgSystem.TICK);
			Player.MUSiC_PLAYER.changeGame_ON();
			Player.MUSiC_PLAYER.setGameVol(Player.MUSiC_PLAYER.getVolGame());
		}
		
	}
	

}
