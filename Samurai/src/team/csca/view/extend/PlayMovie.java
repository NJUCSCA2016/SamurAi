package team.csca.view.extend;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 播放动画
 * @author Water
 *
 */
public class PlayMovie extends JPanel {

	String path;
	int num;
	private Image image;
	/**
	 * 
	 * @param path 播放图片的路径
	 * @param num 播放图片的数量
	 */
	public void playMovie(String path, int num){
			this.path = path;
			this.num = num;
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for (int i = 0; i < num; i++) {
						repaint();
						try {
							Thread.sleep(50);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					
				}
			}).start();
	}
		
	private Image getImage(int i) {
		image = new ImageIcon(path + i + ".jpg").getImage();
		return image;
	}

	public void paintComponent (Graphics g) {
		g.drawImage(getImage(num), 0, 0, null);
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setNum(int num) {
		this.num = num;
	}

	


}
