/**
 * Date : Apr 3, 2016 5:34:55 PM
 */
package ui.startmovie;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.panelmain.PanelMain;

/**
 * @author Alone
 * Written by YYM
 */
public class PanelStartMovie extends JPanel implements Runnable{
	
	/**
	 * 当前图片数
	 */
	private int num = 0;
	
	private JFrame frame ; 
	
	private PanelMain panelMain = null;
	
	/**
	 * 开始播放开场动画
	 * 播放结束后立刻删除该Panel
	 */
	public PanelStartMovie(JFrame frame) {
		
		this.frame = frame;
		//请求焦点
		this.requestFocus();
		//播放动画
		new Thread(this).start();
		
	}
	
	public void paint(Graphics g){
		System.out.println("Draw Image");
		//g.drawImage(imgMovie.image[num], 0, 0, null);
		
	}
	
	
	@Override
	public void run() {
		//在开始即初始化该panel ， 避免延迟。 并且不在if(num == 0)中调用，去除对第一个Thread.sleep的影响
		this.panelMain = new PanelMain();
		
		//直接硬编码
		while(true){
			
			
			repaint();
			//开场就放歌
			
			if(this.num  == 0){
				//谁说没歌的。
				System.out.println("Play Music");
				//this.playMusic();
			}
			System.out.println("Biu~Biu~Biu~");
			//可以考虑更改线程速度。此处暂不处理，等我搞定了movie再来弄他
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.num++;
			//TODO : 弄好imgMovie 后 ， 更改num > imgMovie.length
			if(num >= 10){
				//取消焦点
				this.setFocusable(false);
				//移除该Panel
				this.frame.remove(this);
				this.frame.setContentPane(this.panelMain);
				//移除旧contentpane后进行重画
				this.frame.revalidate();
				
				break;
			}
		}
		System.out.println("Movie finished . ");
	}
	
	 
	

}
