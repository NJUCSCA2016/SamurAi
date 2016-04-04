/**
 * Date : Apr 4, 2016 6:24:55 PM
 */
package main;

import javax.swing.JButton;

/**
 * @author Alone
 * Written by YYM
 */
public abstract class SuperButton extends JButton{

	private int x ;
	private int y ;
	private int w ;
	private int h ; 
	
	/**
	 * 设置各类参数
	 */
	public SuperButton(int x , int y , int w , int h) {
		
		this.x = x;
		this.y = y; 
		this.w = w;
		this.h = h;
		this.setBounds(x, y, w, h);
		
		//TODO ： Button全部设置为透明的
		
	}
	
	
	private void setConfig(){
		
	}
	
	
	
}
