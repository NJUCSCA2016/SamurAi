package main;

import javax.swing.JFrame;

/**
 * 单例模式
 * 存放�?有的单一对象
 * 
 * @author Alone
 * Written by YYM
 */
public class SingletonClass {
		
	private static FrameGame frame = null;
	
	
	public static JFrame getFrameInstance(){
		
		if(frame == null){
			
			synchronized(SingletonClass.class){
				if(frame == null){
					frame = new FrameGame();
				}		
			}
			
		}
		
		return frame;
	}
	
	
	
	
}
