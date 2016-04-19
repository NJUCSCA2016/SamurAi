package main;

import javax.swing.JFrame;

import sounds.Sounds;

/**
 * 单例模式
 * 存放所有的单一对象以及各种状态
 * 
 * @author Alone
 * Written by YYM
 */
public class SingletonClass {
		
	private static FrameGame frame = null;
	
	private static Sounds sound = null;
	
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
	
	public static Sounds getSoundInstance(){
		
		if(sound == null){
			
			synchronized(SingletonClass.class){
				if(sound == null){
					sound = new Sounds();
				}		
			}
			
		}
		
		return sound;
	}
	
	
}
