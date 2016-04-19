package ui.panelhelp;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Background;
import main.SingletonClass;

public class PanelHelp extends JPanel{
	
	private JFrame frame ;
	
	private Background border = null;
	
	private Background center = null;
	
	private Background inside = null;
	
	public PanelHelp(){
		
		this.frame = SingletonClass.getFrameInstance();
		
		//TODO : 中间和边界的图片
		border = new Background(null);
		center = new Background(null);
		inside = new Background(null);
		this.add(new ButtonCloseHelp(this));
		
		
	}
	
	
	
	public void paintComponent(Graphics g){
		
		border.creatBack(g);
		center.creatBack(g);
		inside.creatBack(g);
		super.paintComponents(g);
		
	}
	
	public Background getInside(){
		return this.inside;
	}
	
}
