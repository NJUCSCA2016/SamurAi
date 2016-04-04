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
	 * 当前图片�?
	 */
	private int num = 0;
	
	private JFrame frame ; 
	
	private PanelMain panelMain = null;
	
	/**
	 * �?始播放开场动�?
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
		
		g.drawImage(ImgMovie.image[num], 0, 0, null);
		
	}
	
	
	@Override
	public void run() {
		//在开始即初始化该panel �? 避免延迟�? 并且不在if(num == 0)中调用，去除对第�?个Thread.sleep的影�?
		this.panelMain = new PanelMain();
		
		//直接硬编�?
		while(true){
			//TODO : 在该loading线程中完成其他一系列初始化操作�??
			
			this.repaint();
			//�?场就放歌
			
			if(this.num  == 0){
				//谁说没歌的�??
				//System.out.println("Play Music");
				//this.playMusic();
			}
	
			//可以考虑更改线程速度。此处暂不处理，等我搞定了movie再来弄他
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.num++;
			//TODO : 弄好imgMovie �? �? 更改num > imgMovie.length
			if(num == 60){
				this.repaint();
				//TODO 添加�?个Button �? 注册事件为一下内容�??
				//取消焦点
				this.setFocusable(false);
				//移除该Panel
				this.frame.remove(this);
				this.frame.setContentPane(this.panelMain);
				//移除旧contentpane后进行重�?
				this.frame.revalidate();
				
				break;
			}
		}
		System.out.println("Movie finished . ");
	}
	
	 
	

}
