package team.csca.view.gameOver;

import javax.swing.JButton;

import team.csca.view.extend.JPanelGameOver;
import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgGameOver;

<<<<<<< HEAD
public class JPanelGameWin extends JPanel{
	public Layer[] layers = {
			new LayerBackground(0, 0, 1250, 700, ImgGameOver.YOU_WIN)
	};
	public JPanelGameWin() {
		this.setLayout(null);
		repaint();
		this.add(new JButtonGameBackToMain(this));
		this.add(new JButtonRankingListWin());
		Player.stopMusic();
=======
public class JPanelGameWin extends JPanelGameOver{
>>>>>>> 32dce892a269f1c229b7797a1f41d886e8e7ae61

	public JPanelGameWin(JButton buttonToAdd){
		super(buttonToAdd);
		background = new LayerBackground(0, 0, 1250, 700, ImgGameOver.YOU_WIN);
	}
}
