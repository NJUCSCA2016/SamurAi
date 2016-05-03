package team.csca.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 这个文件写开场动画
 * 
 * @author Water
 * @author YYM
 */
public class JPanelStartMovie extends JPanel implements KeyListener {
	public int i = 1;

	public Image image;

	private JFrameMain frame;
	
	private JPanelStartGame st;



	public JPanelStartMovie(JFrameMain frame) {
		this.frame = frame;
		// 设置焦点
		this.setFocusable(true);
		frame.addKeyListener(JPanelStartMovie.this);
		new Thread(new movie()).start();
	}

	private class movie implements Runnable {
		/**
		 * 线程式加载图片
		 */
		public void run() {
			while (true) {
				// TODO : 还要加载 Loading和片尾图片
				// 经测试，ImageIcon比ImageIo快很多
				
				repaint();
				if (i==1) {
					// 当movie线程开启时马上new一个主界面panel，避免延迟
					st = new JPanelStartGame(frame);
				}
				
				if (i<=90) {
					try {			
						// TODO: 调慢一点
						Thread.sleep(50);
						
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}
				
				if (i == 90) {
					// 取消焦点
					st=new JPanelStartGame(frame);
					JPanelStartMovie.this.setFocusable(false);
					// 增加开始游戏panel
					frame.setContentPane(st);				
					// 开始游戏panel设置焦点
					st.setFocusable(true);
					// frame移除该panel
					frame.remove(JPanelStartMovie.this);
					// 开始游戏panel请求焦点
					st.requestFocus();
					
					frame.revalidate();
					break;
				}
				i++;
			}

		}
	}

	public void paint(Graphics g) {
		g.drawImage(getImage(i), 0, 0, null);
	}

	private Image getImage(int i2) {
		image = new ImageIcon("Image/Start/" + i2 + ".png").getImage();
		return image;
	}
	
	/**
	 * 键盘事件
	 */

	@Override
	public void keyReleased(KeyEvent e) {
		// 空格skip
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			i = 89;
//			System.out.println(222);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
