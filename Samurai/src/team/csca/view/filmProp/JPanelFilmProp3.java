package team.csca.view.filmProp;

import team.csca.controller.media.Player;
import team.csca.view.gameOver.JPanelGameLose;
import team.csca.view.gameOver.JPanelGameWin;

public class JPanelFilmProp3 extends JPanelFilmProp1 {
	public JPanelFilmProp3(JPanelHurdle hurdle) {
		super(hurdle);
	}

	int death = 0;
	@Override
	public void beatOthers(int[] location){

		for (int i = 0; i < location.length; i++) {
			for (int j = 0; j < 6; j++) {
				int temp = 15 * x[j] + y[j];
				int temp2 = 15 * homeX[j] + homeY[j];
				if (location[i] == temp && j != index && location[i] != temp2) {
					if (life[j] == 0) {
						x[j] = homeX[j];
						y[j] = homeY[j];
						recoverRound[j] = maxRecoverRound;
						//TODO:人机对战此处需要修改
						direction[j] = 0;
						if (Player.MUSiC_PLAYER.isGame_ON()) {
							Player.playSound("soundeffect0");
						}
						if (j > 2) {
							death ++;
						}
					}
					if (life[j] != 0) {
						life[j] -= 1;
						// TODO:加音效
					}
				}
			}
		}
	}
	
	@Override
	public void judgeContest(){
		if (death >= 5) {
			gameWin = new JPanelGameWin();
			gameWin.addReturnButton(new JButtonGameBack(this));
			frameMain.setContentPane(gameWin);
			frameMain.remove(this);
			gameWin.requestFocus();
			Player.stopMusic();
			if (Player.MUSiC_PLAYER.isBack_ON()) {
				Player.playMusic("win");
			}
		}
		if (death < 5) {
			gameLose = new JPanelGameLose();
			gameLose.addReturnButton(new JButtonGameBack(this));
			frameMain.setContentPane(gameLose);
			frameMain.remove(this);
			gameLose.requestFocus();
			Player.stopMusic();
			if (Player.MUSiC_PLAYER.isBack_ON()) {
				Player.playMusic("lose");
			}
		}
		frameMain.revalidate();
	}
}
