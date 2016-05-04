package ui.panelhelp;

import java.awt.Graphics;

import javax.swing.JPanel;
import main.Background;

public class PanelHelp extends JPanel{
	
	private Background border = null;
	
	private Background center = null;
	
	private Background inside = null;
	
	protected int numOfPic = 0;
	
	protected int lastIndex = 10;
	
	public PanelHelp(){
		
		this.setLayout(null);
		
		//TODO : 中间和边界的图片
		
		this.border = new Background(null);
		this.center = new Background(null);
		this.inside = new Background(null);
		this.add(new ButtonCloseHelp(this));
		//添加下一张和上一张两个按钮
		ButtonNext buttonNext = new ButtonNext(this);
		ButtonPre buttonPre = new ButtonPre(this);
		
		
		this.add(this.border);
		this.add(this.center);
		this.add(this.inside);
		this.add(buttonNext);
		this.add(buttonPre);
		
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
