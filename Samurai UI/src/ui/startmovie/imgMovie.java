/**
 * Date : Apr 3, 2016 5:36:17 PM
 */
package ui.startmovie;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author Alone
 * Written by YYM
 */
public class ImgMovie implements Runnable{
	
	public static Image[] image;
	
	/**
	 * 线程式加载图片
	 */

	public void run() {
		image = new Image[70];
		try {
		//TODO : 还要加载 Loading和片尾图片
			for(int i = 1 ; i <= 70 ;i++){
				System.out.println("Read");
				//经测试，ImageIcon比ImageIo快很多
				image[i-1] = new ImageIcon("Image/Start/" + i + ".png").getImage();
			
			}
		}
		catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	
}
