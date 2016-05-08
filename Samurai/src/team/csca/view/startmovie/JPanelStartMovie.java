package team.csca.view.startmovie;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import team.csca.controller.media.Player;
import team.csca.view.frame.JFrameMain;
import team.csca.view.startgame.JPanelStartGame;

/**
 * 这个文件写开场动画
 * 
 * @author Water
 * @author YYM
 */
public class JPanelStartMovie extends JPanel implements KeyListener {
	/**
	 * 图片计数器
	 */
	public int pic_Number = 1;

	/**
	 * 所要播放的动画
	 */
	public Image image;

	/**
	 * Frame
	 */
	private JFrameMain frame =JFrameMain.J_FRAME_MAIN;

	/**
	 * Play按钮
	 */
	private JButtonPlay btnPlay;
	
	/**
	 * 背景音乐
	 */
	private boolean homeMusic;

	public JPanelStartMovie() {
		this.homeMusic = true;
		// 设置焦点
		this.setFocusable(true);
		// 增加键盘事件监听
		this.addKeyListener(this);
		// 添加Play按钮到面板上
		this.btnPlay = new JButtonPlay(this);
//		this.btnPlay.setEnabled(false);
//		this.btnPlay.setVisible(false);
//		this.add(this.btnPlay);
		// 开启第一部分动画线程
		new Thread(new MoviePartOne()).start();
		
	}
	/**
	 * 
	 * @author With You
	 *动画第一部分
	 *
	 */
	private class MoviePartOne implements Runnable{
		
		public void run(){
		
			/**
			 * @author With You
			 * 如下面的类
			 */
			if (Player.MUSiC_PLAYER.isBack_ON()) {
				// TODO
				Player.playMusic("bgm");
			}
			while (pic_Number <= 165) {
				
				// 经测试，ImageIcon比ImageIo快很多
				repaint();
				
				try{			
					// TODO: 调慢一点
					Thread.sleep(50);
					
				} catch (Exception e) {
					e.printStackTrace();
				}	

				pic_Number++;
			}
			pic_Number = 145;
			//显现Play
			add(btnPlay);

		}
		
	}
	public void startTwo(){
		new Thread(new MoviePartTwo()).start();
	}
	/**
	 * 
	 * @author With You
	 *动画第二部分
	 *
	 */
	private class MoviePartTwo implements Runnable{
		
		public void run(){
			
			JPanelStartGame mainPanel = null;
			//移除Play
			remove(btnPlay);
			pic_Number = 165;
			
			while(pic_Number <= 209){
				
				pic_Number++;
				repaint();
				try{			
					// TODO: 调慢一点
					Thread.sleep(125);
					
				} catch (Exception e) {
					e.printStackTrace();
				}	
				
				if(pic_Number == 166){
					mainPanel = new JPanelStartGame();
				}
				
				
			}
			setFocusable(false);
			//移除该Panel
			frame.remove(JPanelStartMovie.this);
			frame.setContentPane(mainPanel);
			Player.stopMusic();
			Player.playMusic("bgm1");
			//移除旧contentpane后进行重画
			frame.revalidate();
			
		}
		
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(getImage(pic_Number), 0, 0, null);
	}

	private Image getImage(int i2) {
		image = new ImageIcon("Image/Start/" + i2 + ".jpg").getImage();
		return image;
	}
	
	@Deprecated
	private class move implements Runnable {
		/**
		 * 线程式加载图片
		 */
		public void run() {
			
			/**
			 * @author With You
			 * 
			 * 加载动画第一部分。动画结束后显示出ButtonPlay。然后点击ButtonPlay后播放第二部分动画
			 */
			while (pic_Number <= 90) {
				// TODO : 还要加载 Loading和片尾图片
				// 经测试，ImageIcon比ImageIo快很多
				
				repaint();
				if (pic_Number==1) {
					// 当movie线程开启时马上new一个主界面panel，避免延迟
//					st = new JPanelStartGame(frame);
//					btnPlay = new JButtonPlay(st);
				}
				
//				if (pic_Number<=90) {
					try {			
						// TODO: 调慢一点
						Thread.sleep(50);
						
					} catch (Exception e) {
						e.printStackTrace();
					}	
//				}
				//下面这些全都放在ButtonPlay的事件监听中。由ButtonPlay的事件来
//				if (pic_Number == 90) {
//					
//					
//					// 取消焦点
//					st=new JPanelStartGame(frame);
//					JPanelStartMovie.this.setFocusable(false);
//					// 增加开始游戏panel
//					frame.setContentPane(st);				
//					// 开始游戏panel设置焦点
//					st.setFocusable(true);
//					// frame移除该panel
//					frame.remove(JPanelStartMovie.this);
//					// 开始游戏panel请求焦点
//					st.requestFocus();
//					
//					frame.revalidate();
//					break;
//				}
				pic_Number++;
			}

		}
	}


	
	/**
	 * 键盘事件
	 */

	@Override
	public void keyReleased(KeyEvent e) {
		// 空格skip
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			pic_Number = 163;
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
