package team.csca.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 这个文件写开场动画
 * @author Water
 *
 */
public class StartMovie extends MainFrame implements Runnable{
	public Image image;
	public volatile boolean exit = false;
	
	/**
	 * 线程式加载图片
	 */
	public void run() {
		
		try {
		//TODO : 还要加载 Loading和片尾图片
			for(int i = 1 ; i <= 90 ;i++){
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
	
	public void paint(Graphics g){
		g.drawImage(image, 0, 0, null);
	}
	
	public StartMovie(){
		new Thread(this).start();
		}
}
