package team.csca.view.link.serverLink;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import team.csca.client.ServerLink;
import team.csca.client.ServerNotFoundException;
import team.csca.view.frame.JFrameMain;

/**
 * 
 * 加载并且等间隔寻找服务器。
 * @author With You
 *
 */

public class JPanelLinkLoading extends JPanel{
	
	private JFrameMain main = JFrameMain.J_FRAME_MAIN;
	
	private Image currentImg = new ImageIcon("Image/LoginPanel/welcome.png").getImage();
	
	private ServerLink link = null;
	
	private Thread movieThread = null;
	
	private boolean findServer = false;
	/**
	 * To count the loading times .
	 */
	private int timeCount = 0;
	
	private Timer timer = new Timer("ServerLink");
	
	
	public JPanelLinkLoading() {
		
		this.setLayout(null);
		this.requestFocus();
		
		
		
		timer.schedule(new ActionTaken(), 1000);
		movieThread = new Thread(new LoadingMovie());
		movieThread.start();
		
	}
	
	private class LoadingMovie implements Runnable{

		@Override
		public void run() {
			
			/**
			 * Forever Loop till server is found 
			 */
			while(! findServer && timeCount <= 59){
				for(int i = 0 ; i <= 19 ; i++){
					synchronized (this) {
						if(! findServer){
							currentImg = getImage(i);
							repaint();
						}else{
							break;
						}
					} 
				}
			}
			
			if(findServer){
				main.remove(JPanelLinkLoading.this);
				//TODO : create sign in panel
				main.setContentPane(new JPanelLinkLoading());
				main.revalidate();
			}else{
				//TODO : show can't find
				currentImg = new ImageIcon("Image/LoginPanel/failed.png").getImage();
				//TWO BUTTONS
				
			}
			
		}
		
	}
	/**
	 * 
	 * 断线重连。 最多60次
	 * @author With You
	 *
	 */
	
	private class ActionTaken extends TimerTask{

		/**
		 * Take this for sixty times.
		 */
		@Override
		public void run() {
			try {
				synchronized (this) {
					timeCount ++ ;
				}
				link = new ServerLink();
			} catch (ServerNotFoundException e) {
				e.printStackTrace();
				link = null;
			} finally {
				//The last time
				if(timeCount == 59 || link != null){
					findServer = true;
					timer.cancel();
				}
			}
		}
		
	}
	
	
	public void paintComponents(Graphics g){
		super.paintComponent(g);
		g.drawImage(currentImg, 0, 0, null);
	}
	
	
	
	private Image getImage(int i) {
		Image imageToGet = new ImageIcon("Image/LoginPanel/" + i + ".png").getImage();
		return imageToGet;
	}
	
}
