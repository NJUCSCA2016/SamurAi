package team.csca.view.filmProp;

import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgMap;

public class JPanelHurdle extends JPanel{
	
	private LayerBackground background;
	
	private JLabel label;
	
	private boolean mouseIn = true;
	
	private int labelCount = 0;
	
	private static int curHurdle = 0;
	
	
	
	public JPanelHurdle() {
		this.setLayout(null);
		label = new JLabel();
		background = new LayerBackground(0, 0, 1250, 700, ImgMap.MAPS[JPanelHurdle.curHurdle]);
		this.add(new JButtonForHurdleChoose(curHurdle, this));
		this.add(new JButtonBackToMain(this));
	}
	
	public void paintComponent(Graphics g){
		this.background.createWindow(g);
		super.paintComponents(g);
	}
	
	public void passHurdle(){
		if(curHurdle < 4){
			curHurdle ++;
			this.background.setImg(ImgMap.MAPS[curHurdle]);
			repaint();
		}
	}
	
	public static void resetHurdle(){
		curHurdle = 0;
	}
	
	public void playScroll(){
		if(label == null){
			this.label = new JLabel();
			this.label.setOpaque(true);
		}
		this.add(label);
		while(mouseIn && labelCount < 7){
			labelCount ++ ;
			this.label.setIcon(getScrollIcon(labelCount));
			repaint();
		}
	}
	
	public void closeScroll(){
		
		while((!mouseIn) && labelCount > 0){
			labelCount --;
			this.label.setIcon(getScrollIcon(labelCount));
			repaint();
		}
		
		if(labelCount == 0){
			this.remove(this.label);
		}
		repaint();
	}
	
	private Icon getScrollIcon(int picCount){
		return new ImageIcon("Image/GamePanel/situation/stage/stage" + (curHurdle+1)+ "_" + picCount + ".png");
	}
	
	public void mouseIn(){
		this.mouseIn = true;
	}
	
	public void mouseOut(){
		this.mouseIn = false;
	}
	
}
