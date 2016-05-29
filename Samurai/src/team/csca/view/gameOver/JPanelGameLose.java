package team.csca.view.gameOver;

import java.awt.Graphics;

import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;
import team.csca.view.extend.StaticButton;
import team.csca.view.image.ImgGameOver;

public class JPanelGameLose extends JPanel{
	
	
	public JPanelGameLose() {
		this.setLayout(null);
		background = new LayerBackground(0, 0, 1250, 700, ImgGameOver.YOU_LOSE);
//		this.add(new JButtonRankingListLose());
	}
	protected LayerBackground background;
	@Override
	protected void paintComponent(Graphics g) {
		background.createWindow(g);
		super.paintComponents(g);
	}
	
	public void addReturnButton(StaticButton button){
		this.add(button);
	}
}
