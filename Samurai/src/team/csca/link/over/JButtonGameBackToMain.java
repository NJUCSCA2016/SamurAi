package team.csca.link.over;

import java.awt.event.MouseEvent;

import team.csca.controller.media.Player;
import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgGameOver;
import team.csca.view.startgame.JPanelStartGame;

public class JButtonGameBackToMain extends DynamicButton{
//	public JButtonGameBackToMain(){
//		super(1200, 50, 50, 50, ImgGameOver.CROSS, ImgGameOver.CROSS, ImgGameOver.CROSS);
//	}
	private JPanelGameWin gameWin;
	
	private JPanelGameDraw gameDraw;
	
	private JPanelGameLose gameLose;

	public JButtonGameBackToMain(JPanelGameWin fatherPanel){
		// TODO:调整位置
		super(1150, 0, 71, 71, ImgGameOver.CROSS, ImgGameOver.CROSS, ImgGameOver.CROSS);
		this.gameWin = fatherPanel;
	}
	
	public JButtonGameBackToMain(JPanelGameLose fatherPanel){
		// TODO:调整位置
		super(1150, 0, 71, 71, ImgGameOver.CROSS, ImgGameOver.CROSS, ImgGameOver.CROSS);
		this.gameLose = fatherPanel;
	}
	
	public JButtonGameBackToMain(JPanelGameDraw fatherPanel){
		// TODO:调整位置
		super(1150, 0, 71, 71, ImgGameOver.CROSS, ImgGameOver.CROSS, ImgGameOver.CROSS);
		this.gameDraw = fatherPanel;
	}

	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if (this.gameWin != null) {
			this.frame.remove(this.gameWin);
		}
		if (this.gameDraw != null) {
			this.frame.remove(this.gameDraw);
		}
		if (this.gameLose != null) {
			this.frame.remove(this.gameLose);
			
		}
		
		this.frame.setContentPane(new JPanelStartGame());
		this.frame.revalidate();
		Player.stopMusic();
		Player.stopMidi();
		if (Player.MUSiC_PLAYER.isBack_ON()) {
			Player.playMusic("bgm1");
		}

	}
}
