/**
 * Date : Apr 3, 2016 3:56:28 PM
 */
package main;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

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
		
		//设置的是鼠标放在任务栏上显示的标题。去除后放在上面就没标题了。Undecorate不会改变任务栏上的标题
		this.setTitle("SAMURAI");
		
		this.setUndecorated(true);
		Toolkit toolkit = this.getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		//设置不可改变大小
		this.setResizable(false);
		this.setVisible(false);
		this.setSize(1250, 700);
		//设置坐标
		int x = this.getWidth() + dimension.width >> 1;
		int y = this.getHeight() + dimension.height >> 1;
 		this.setLocation(x, y);
 		
 		this.setUndecorated(true);
 		//TODO : Logo
 		//设置logo ：  this.setIconImage();
		
 		this.setContentPane(new PanelStartMovie(this));
 		
		this.setVisible(true); 
		
	}
	
	private Image getLogo(){
		
		
		return null;
	}
	
}
