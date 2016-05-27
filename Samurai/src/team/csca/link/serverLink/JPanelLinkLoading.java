package team.csca.link.serverLink;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import team.csca.client.ServerLink;
import team.csca.client.ServerNotFoundException;
import team.csca.link.signIn.JPanelSignIn;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgLink;

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
	
	private JButtonReturn loadingReturn = null;
	
	private JButtonRetry retry = null;
	
	private JButtonReturn panelReturn = null;
	
	public JPanelLinkLoading() {
		
		this.setLayout(null);
		this.requestFocus();
		this.loadingReturn = new JButtonReturn(1150,20,50, 38,
				ImgLink.LOGIN_WAITING_RETURN1 ,
				ImgLink.LOGIN_WAITING_RETURN2 ,
				ImgLink.LOGIN_WAITING_RETURN3 ,
				this);
		this.add(loadingReturn);
		
		linkStart();
		
	}
	
	private void linkStart(){
		movieThread = new Thread(new LoadingMovie());
		movieThread.start();
		timer.schedule(new ActionTaken(), 1000,1000);
	}
	
	private class LoadingMovie implements Runnable{

		@Override
		public void run() {
			
			/**
			 * Forever Loop till server is found 
			 */
			while((!findServer) && timeCount <= 59){
				for(int i = 0 ; i <= 19 ; i++){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (this) {
//						System.out.println(timeCount);
						if(!findServer){
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
				main.setContentPane(new JPanelSignIn());
				main.revalidate();
			}else{
				//TODO : show can't find
				currentImg = new ImageIcon("Image/LoginPanel/failed.png").getImage();
				repaint();
				//TWO BUTTONS
			
				remove(loadingReturn);
				
				retry = new JButtonRetry(JPanelLinkLoading.this);
				
				panelReturn = new JButtonReturn(756 , 503 , 249 , 103 ,
						ImgLink.LOGIN_FAILED_RETURN_INIT ,
						ImgLink.LOGIN_FAILED_RETURN_ENTER ,
						ImgLink.LOGIN_FAILED_RETURN_CLICK , 
						JPanelLinkLoading.this);
				
				add(retry);
				add(panelReturn);
				repaint();
			}
		}
		
	}
	/**
	 * 
	 * 断线重连。 最多100次
	 * @author With You
	 *
	 */
	
	private class ActionTaken extends TimerTask{

		/**
		 * Take this for sixty times.
		 */
		@Override
		public void run() {
			System.out.println("Loop Count = " + timeCount);
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
				if(link != null){
					findServer = true;
					timer.cancel();
				}else if(timeCount == 60){
					//Repeat time is done and not find the server .
					timer.cancel();
				}
			}
		}
		
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(currentImg, 0, 0, null);
	}
	
	
	
	private Image getImage(int i) {
		Image imageToGet = new ImageIcon("Image/LoginPanel/" + i + ".png").getImage();
		return imageToGet;
	}
	
	public void restart(){
		this.remove(retry);
		this.remove(panelReturn);
		this.add(loadingReturn);
		
		repaint();
		this.timer  = new Timer("ServerLinkRestart");
		timeCount = 0;
		
		this.movieThread = new Thread(new LoadingMovie());
		this.movieThread.start();
		timer.schedule(new ActionTaken(), 1000,1000);
	}
	
	
	
}
