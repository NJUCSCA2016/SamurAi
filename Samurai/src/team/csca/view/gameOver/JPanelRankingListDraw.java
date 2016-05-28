package team.csca.view.gameOver;

import java.awt.Container;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JPanel;

import team.csca.view.extend.Layer;
import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgGameOver;
import team.csca.view.image.ImgNumber;
import team.csca.view.pm.JPanelPM;

public class JPanelRankingListDraw extends JPanel{

	
	public Layer[] layers = {
			new LayerBackground(0, 0, 1250, 700, ImgGameOver.YOU_LOSE),
			new LayerBackground(275, 125, 700, 700, ImgGameOver.RANKING_LIST)
			};
	public JPanelRankingListDraw(){
		this.setLayout(null);
		repaint();
		this.add(new JButtonReturnToDraw());
		
	}
	
	public void paintComponent(Graphics g) {
		for (int i = 0; i < layers.length; i++) {
			layers[i].createWindow(g);
		}
		for (int i = 5; i >= 0; i--) {
			System.out.println(rank()[i]);
		}
		for (int i = 5; i >= 0; i--) {
			printNumber(rank()[i], g, 620, 434 - i * 38, 24, 34);
		}	
	}
	
	public int[] rank(){
		int[] sortedScore = new int[6];
		for (int i = 0; i < sortedScore.length; i++) {
			sortedScore[i] = JPanelPM.count[i];
		}
		Arrays.sort(sortedScore);
		return sortedScore;
	}
	
	private void printNumber(int num, Graphics g, int x, int y, int w, int h) {
		if (num < 10) {
			g.drawImage(ImgNumber.NUMS[num], x, y, w, h, this);
		}
		if (10 <= num && num <= 99) {
			int tens = num / 10;
			int unit = num % 10;
			g.drawImage(ImgNumber.NUMS[tens], x - w, y, w, h, this);
			g.drawImage(ImgNumber.NUMS[unit], x, y, w, h, this);
		}
		if (100 <= num) {
			int hundreds = num / 100;
			int tens = (num - 100 * hundreds) / 10;
			int unit = num % 10;
			g.drawImage(ImgNumber.NUMS[hundreds], x - 2 * w, y, w, h, this);
			g.drawImage(ImgNumber.NUMS[tens], x - w, y, w, h, this);
			g.drawImage(ImgNumber.NUMS[unit], x, y, w, h, this);
		}
	}
}
