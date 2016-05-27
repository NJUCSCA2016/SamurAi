package team.csca.link.gameWaiting;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import team.csca.client.RemoteHelper;
import team.csca.control.netControl.NetGameControl;
import team.csca.controller.media.Player;
import team.csca.link.tra.JPanelNetTra;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgLink;


public class JPanelGameLoading extends JPanel{
	
	
	
	private JFrameMain main = JFrameMain.J_FRAME_MAIN;
	
	private Image currentImg = new ImageIcon("Image/LoginPanel/welcome.png").getImage();
	
	private JButtonReturn loadingReturn = null;
	
	public JPanelGameLoading() {
		
		this.setLayout(null);
		this.requestFocus();
		this.loadingReturn = new JButtonReturn(1150,20,50, 38,
				ImgLink.LOGIN_WAITING_RETURN1 ,
				ImgLink.LOGIN_WAITING_RETURN2 ,
				ImgLink.LOGIN_WAITING_RETURN3 ,
				this);
		this.add(loadingReturn);
		new Thread(new LoadingBefore()).run();
			
	}
	
	private class LoadingBefore implements Runnable{

		@Override
		public void run() {
			
			while(RemoteHelper.getInstance().getNotic().unNotice()){
				for(int i = 0 ; i <= 19 ; i++){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (this) {
//						System.out.println(timeCount);
						if(RemoteHelper.getInstance().getNotic().unNotice()){
							currentImg = getImage(i);
							repaint();
						}else{
							break;
						}
					} 
				}
			}
			
			main.setContentPane(NetGameControl.getInstance().getPanel());
			main.remove(JPanelGameLoading.this);
			Player.stopMusic();
			if (Player.MUSiC_PLAYER.isBack_ON()) {
				Player.playMusic("bgm2");
			}
			main.revalidate();
			
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
	
}
