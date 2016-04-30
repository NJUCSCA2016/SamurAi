package team.csca.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 这个文件写开场动画
 * 
 * @author Water
 * @author YYM
 */
public class StartMovie extends JPanel {
	public int i = 1;

	public Image image;

	private MainFrame frame;

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}

	public StartMovie(MainFrame frame) {
		this.frame = frame;
		// 设置焦点
		this.setFocusable(true);
		// this.addKeyListener(new StartMovie(this));
		new Thread(new movie()).start();
	}

	private class movie implements Runnable {
		/**
		 * 线程式加载图片
		 */
		public void run() {

			try {
				// TODO : 还要加载 Loading和片尾图片
				for (i = 1; i <= 90; i++) {
					// 经测试，ImageIcon比ImageIo快很多
					image = new ImageIcon("Image/Start/" + i + ".png").getImage();
					repaint();
					Thread.sleep(50);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(i == 90){
				
			}

		}
	}

	/**
	 * 键盘事件
	 */
	public void keyReleased(KeyEvent e) {
		// 空格skip
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			i = 69;
		}
	}

}
