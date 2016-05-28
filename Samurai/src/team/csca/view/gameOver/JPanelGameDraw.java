package team.csca.view.gameOver;

import java.awt.Graphics;

import team.csca.view.extend.JPanelGameOver;
import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgGameOver;

public class JPanelGameDraw extends JPanelGameOver{
	
	public JPanelGameDraw() {
		super(new JButtonGameBackToMain());
		background = new LayerBackground(0, 0, 1250, 700, ImgGameOver.YOU_DRAW);
	}
	protected LayerBackground background;
	@Override
	protected void paintComponent(Graphics g) {
		background.createWindow(g);
		super.paintComponent(g);
	}

}
