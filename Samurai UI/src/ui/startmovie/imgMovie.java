/**
 * Date : Apr 3, 2016 5:36:17 PM
 */
package ui.startmovie;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author Alone
 * Written by YYM
 * Also by WZ
 */
public class ImgMovie extends Frame implements Runnable{
	
	public Image image;
	
	/**
	 * 线程式加载图片
	 */

	public void run() {
		try {
		//TODO : 还要加载 Loading和片尾图片
			for(int i = 1 ; i <= 70 ;i++){
				System.out.println("Read");
				//经测试，ImageIcon比ImageIo快很多
				image = new ImageIcon("Image/Start/" + i + ".png").getImage();
				repaint();
				Thread.sleep(50);
			}
		}
		catch (Exception e) {
				e.printStackTrace();
		}
	}
	public ImgMovie(){
		super("ImgMovie");
		setSize(1250,700);
		setVisible(true);
		new Thread(this).start();
	}
	
	public void paint(Graphics g){
		g.drawImage(image, 0, 0, null);
	}
	public static void main(String[] args) {
		ImgMovie workStart = new ImgMovie();
	}
	
}
