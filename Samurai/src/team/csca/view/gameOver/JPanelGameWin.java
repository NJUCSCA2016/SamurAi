package team.csca.view.gameOver;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgGameOver;

public class JPanelGameWin extends JPanel{

	public JPanelGameWin(){
		setLayout(null);
		background = new LayerBackground(0, 0, 1250, 700, ImgGameOver.YOU_WIN);
		this.add(new JButtonRankingListWin());
		this.add(new JButtonGameBackToMain(this));
	}
	protected LayerBackground background;
	@Override
	protected void paintComponent(Graphics g) {
		background.createWindow(g);
		super.paintComponents(g);
	}
	public void addReturnButton(JButton button){
		this.add(button);
		repaint();
	}
}
