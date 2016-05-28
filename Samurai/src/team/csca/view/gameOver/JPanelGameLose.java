package team.csca.view.gameOver;

import javax.swing.JButton;

import team.csca.view.extend.JPanelGameOver;
import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgGameOver;

public class JPanelGameLose extends JPanelGameOver{
	public JPanelGameLose(JButton buttonToAdd) {
		super(buttonToAdd);
		background = new LayerBackground(0, 0, 1250, 700, ImgGameOver.YOU_LOSE);
	}
	

<<<<<<< HEAD
	public Layer[] layers = {
			new LayerBackground(0, 0, 1250, 700, ImgGameOver.YOU_LOSE)
	};
	public JPanelGameLose() {
		this.setLayout(null);
		repaint();
		this.add(new JButtonGameBackToMain(this));
		this.add(new JButtonRankingListLose());

	}
	public void paintComponent(Graphics g) {
		for (int i = 0; i < layers.length; i++) {
			layers[i].createWindow(g);
		}	
	}
=======
>>>>>>> 32dce892a269f1c229b7797a1f41d886e8e7ae61
}
