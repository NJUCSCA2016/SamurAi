package team.csca.view.filmProp;

import team.csca.controller.media.Player;
import team.csca.view.gameOver.JPanelGameLose;
import team.csca.view.gameOver.JPanelGameWin;

public class JPanelFilmProp5 extends JPanelFilmProp1{
	public JPanelFilmProp5(JPanelHurdle hurdle) {
		super(hurdle);
	}
	int score = count[0] + count[1] + count[2];
	@Override
	public void judgeContest(){
		if (score >= 158) {
			gameWin = new JPanelGameWin(new JButtonGamePass(this));
			frameMain.setContentPane(gameWin);
			gameWin.requestFocus();
			Player.stopMusic();
			if (Player.MUSiC_PLAYER.isBack_ON()) {
				Player.playMusic("win");
			}
		}
		if (score < 158) {
			gameLose = new JPanelGameLose(new JButtonGameBack(this));
			frameMain.setContentPane(gameLose);
			gameLose.requestFocus();
			Player.stopMusic();
			if (Player.MUSiC_PLAYER.isBack_ON()) {
				Player.playMusic("lose");
			}
		}
		frameMain.revalidate();
	}
}
