package main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class StaticButton extends JButton{

	protected JFrame frame = FrameGame.FRAME_GAME;
	/**
	 * 不会随着鼠标放上去而改变图案的Button
	 */
	
	protected int x ;
	protected int y ;
	protected int w ;
	protected int h ; 
	protected Image buttonImg;
	
	public StaticButton(int x , int y , int w , int h , Image buttonImg){
		
		this.x = x;
		this.y = y; 
		this.w = w;
		this.h = h;
		this.buttonImg = buttonImg;
		this.setBounds(x, y, w, h);
		// Button全部设置为透明的
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		
	}
	
	public void paintComponent(Graphics g){
		
		g.drawImage(this.buttonImg, 0, 0, null);
		
	}
	
	public void setButtonImg(Image buttonImg){
		this.buttonImg = buttonImg;
	}
}
