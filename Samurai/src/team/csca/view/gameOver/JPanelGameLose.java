package team.csca.view.gameOver;

import java.awt.Graphics;

import javax.swing.JButton;

import team.csca.view.extend.JPanelGameOver;
import team.csca.view.extend.LayerBackground;
import team.csca.view.gameOver.*;
import team.csca.view.image.ImgGameOver;

public class JPanelGameLose extends JPanelGameOver{
	
	
	public JPanelGameLose(JButton buttonToAdd) {
		super(buttonToAdd);
		background = new LayerBackground(0, 0, 1250, 700, ImgGameOver.YOU_LOSE);
		this.add(new JButtonRankingListLose());
	}
	protected LayerBackground background;
	@Override
	protected void paintComponent(Graphics g) {
		background.createWindow(g);
		super.paintComponent(g);
	}
}
