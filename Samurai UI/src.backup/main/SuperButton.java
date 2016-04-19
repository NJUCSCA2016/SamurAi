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
	 * 
	 * 使用MouseListener 无法实现在点击时更改样式。故仍使用getModel
	 */
	protected int x ;
	protected int y ;
	protected int w ;
	protected int h ; 
	
//	private ImageIcon buttonImg ;
	/**
	 * 设置光标进入，离�?，点击时的Button样式
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
		// Button全部设置为�?�明�?
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		
	}
	/**
	 * 绘制每个Button的背�?
	 */
	public void paintComponent(Graphics g){
		g.drawImage(this.currentImage, 0, 0, null);
	}
	

	/**
	 * Clicked方法留在子类Button中来实现，�?�Enter和Exit只涉及图标变化�?�故可置于此实现
	 */
	public void mouseClicked(MouseEvent e) {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//TODO : �?有子类必须要重载此方法�??
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.currentImage = this.enterImage;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.currentImage = this.initImage;
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {	
		this.currentImage = this.clickImage;
		repaint();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
	/**
	 * @param enterImage the enterImage to set
	 */
	public void setEnterImage(Image enterImage) {
		this.enterImage = enterImage;
	}
	/**
	 * @param clickImage the clickImage to set
	 */
	public void setClickImage(Image clickImage) {
		this.clickImage = clickImage;
	}
	/**
	 * @param currentImage the currentImage to set
	 */
	public void setCurrentImage(Image currentImage) {
		this.currentImage = currentImage;
	}
	
	
	
}
