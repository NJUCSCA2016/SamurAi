/**
 * Date : Apr 4, 2016 6:24:55 PM
 */
package main;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
 * @author Alone
 * Written by YYM
 */
public abstract class SuperButton extends JButton implements MouseListener{
	
	/**
	 * 此SuperButton只能建立方形的Button ,建立圆形参照 http://biancheng.dnbcw.info/java/39029.html
	 */
	private int x ;
	private int y ;
	private int w ;
	private int h ; 
	
//	private ImageIcon buttonImg ;
	/**
	 * 设置光标进入，离开，点击时的Button样式
	 */
	private Image initImage = null;
	private Image enterImage = null;
	private Image clickImage = null;
	private Image currentImage = null;
	/**
	 * 设置各类参数
	 */
	public SuperButton(int x , int y , int w , int h , Image initImage , Image enterImage , Image clickImage) {
		
		this.x = x;
		this.y = y; 
		this.w = w;
		this.h = h;
		this.initImage = initImage;
		this.enterImage = enterImage;
		this.clickImage = clickImage;
		this.currentImage = this.initImage;
		this.setBounds(x, y, w, h);
		this.addMouseListener(this);
		// Button全部设置为透明的
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
	}
	/**
	 * 绘制每个Button的背景
	 */
	public void paintComponent(Graphics g){
		g.drawImage(this.currentImage, 0, 0, null);
	}
	

	/**
	 * Clicked方法留在子类Button中来实现，而Enter和Exit只涉及图标变化。故可置于此实现
	 */
	public void mouseClicked(MouseEvent e) {
		this.currentImage = this.clickImage;
		//TODO : 所有子类必须要重载此方法。
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.currentImage = this.enterImage;
		//TODO : 为重新设置大小。需重载此方法
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.currentImage = this.initImage;
		//TODO ： 同上
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
	
}
