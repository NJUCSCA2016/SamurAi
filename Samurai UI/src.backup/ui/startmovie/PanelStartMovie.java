/**
 * Date : Apr 3, 2016 5:34:55 PM
 */
package ui.startmovie;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Background;

/**
 * @author Alone
 * Written by YYM
 */
public class PanelStartMovie extends JPanel implements Runnable{
	
	/**
	 * 当前图片�?
	 */
	private int num = 0;
	
	private JButton play;
	
//	private PanelMain panelMain = null;
	
	private Background bk ;
	/**
	 * �?始播放开场动�?
	 * 播放结束后立刻删除该Panel
	 */
	public PanelStartMovie() {
		
		this.setLayout(null);
//		this.play.setFocusPainted(false);
//		this.play.setBorderPainted(false);
//		this.play.setText("");
//		this.play.setVisible(false);
		bk = new Background(ImgMovie.image[0]);
		this.add(bk);
		
		//请求焦点
		this.requestFocus();
		//播放动画
		new Thread(this).start();
		
	}

	public void paintComponent(Graphics g){
		bk.creatBack(g);
		super.paintComponent(g);
	}
	@Override
	public void run() {
		//在开始即初始化该panel �? 避免延迟�? 并且不在if(num == 0)中调用，去除对第�?个Thread.sleep的影�?
//		this.panelMain = new PanelMain();
		this.play = new ButtonPlay(this);
		//TODO �? 放歌
		//直接硬编�?
		while(true){
			
			this.repaint();
			//�?场就放歌
			
//			if(this.num  == 0){
//				//谁说没歌的�??
//				//System.out.println("Play Music");
//				//this.playMusic();
//			}
	
			//可以考虑更改线程速度。此处暂不处理，等我搞定了movie再来弄他
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.bk.setBackground(ImgMovie.image[this.num]);
			this.num++;
			//TODO : 弄好imgMovie �? �? 更改num
			if(num == 90){
//				this.repaint();
//				// 添加�?个Button �? 注册事件为一下内容�??
//				//取消焦点
//				this.setFocusable(false);
//				//移除该Panel
//				this.frame.remove(this);
//				this.frame.setContentPane(this.panelMain);
//				//移除旧contentpane后进行重�?
//				this.frame.revalidate();
//				
				break;
			}

		}
		this.num = 75;
		this.add(this.play);
//		System.out.println("Movie finished . ");
	}

//		try {
//			Thread.sleep(50);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
//		this.setFocusable(false);
//		//移除该Panel
//		this.frame.remove(this);
//		this.frame.setContentPane(this.panelMain);
//		//移除旧contentpane后进行重�?
//		this.frame.revalidate();
//	}

//	class Background extends JPanel{
//		
//		public void paintComponent(Graphics g){
//			g.drawImage(ImgMovie.image[num], 0, 0, null);
//		}
//		
//		public void creatBack(Graphics g){
//			this.paintComponent(g);
//		}
//		
//	}

}
