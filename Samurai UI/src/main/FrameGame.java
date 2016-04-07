/**
 * Date : Apr 3, 2016 3:56:28 PM
 */
package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import ui.startmovie.PanelStartMovie;

/**
 * @author Alone
 * Written by YYM
 */
public class FrameGame extends JFrame{
	
	/**
	 * 设置窗口参数
	 */
	public FrameGame() {
		
		this.setTitle("SAMURAI");
		
		Toolkit toolkit = this.getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		//设置不可改变大小
		this.setResizable(false);
		this.setSize(1250, 700);
		//设置坐标
		int x = dimension.width - this.getWidth() >> 1;
		int y = dimension.height - this.getHeight() >> 1;
		
 		this.setLocation(x, y-20);
 		//设置logo
 		this.setIconImage(new ImageIcon("Image/Others/Logo.png").getImage());
 		
 		this.setUndecorated(true);
 		
 		//图片加载完成后跳出
 		while(Main.loadImage.isAlive()){
 		}
 		
 		this.setContentPane(new PanelStartMovie(this));
 		this.setVisible(true); 
		
		
	}
	//不要了
	//private Image getLogo(){
	//	
	//	
	//	return null;
	//}
	
}
