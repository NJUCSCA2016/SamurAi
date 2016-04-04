/**
 * Date : Apr 3, 2016 3:54:58 PM
 */
package main;

import ui.startmovie.ImgMovie;

/**
 * @author Alone
 * Written by YYM
 */
public class Main {
	
	public static Thread loadImage;
	
	public static void main(String[] args) {
		Main.loadImage = new Thread(new ImgMovie());
		Main.loadImage.start();
		//开始即加载。否则加载过程耗时导致movie显示出问题
		new FrameGame();
		
	}
	
}
