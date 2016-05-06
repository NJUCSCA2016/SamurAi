package team.csca.view.help;

import java.awt.Graphics;

import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgBackground;

public class JPanelHelp extends JPanel{
	
	protected final static int LAST_INDEX = 2;
	
	protected int numOfPic = 0;
	
	private LayerBackground background ;
	
	public JPanelHelp(){
		
		this.setLayout(null);
		
		background = new LayerBackground(0, 0, 1250, 700, ImgBackground.HELP_BACKGOUNDS[0]);
		
		JButtonPre buttonPre = new JButtonPre(this);
		JButtonNext buttonNext = new JButtonNext(this);
		buttonPre.setJButtonNext(buttonNext);
		buttonNext.setJButtonPre(buttonPre);
		
		this.add(buttonPre);
		this.add(buttonNext);
		this.add(new JButtonExit(this));
	}
	
	public void paintComponent(Graphics g){
		this.background.createWindow(g);
		
		super.paintComponents(g);
	}
	
	protected void updateBackground(){
		background.setImg(ImgBackground.HELP_BACKGOUNDS[this.numOfPic]);
		repaint();
	}
	
}
