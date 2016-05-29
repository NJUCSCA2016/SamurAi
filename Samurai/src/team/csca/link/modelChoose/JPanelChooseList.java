package team.csca.link.modelChoose;

import java.awt.Graphics;

import javax.swing.JPanel;

import team.csca.link.signIn.Dialog;
import team.csca.view.extend.LayerBackground;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgModel;

public class JPanelChooseList extends JPanel{
	
	LayerBackground background = new LayerBackground(0, 0, 1250, 700, ImgModel.CHOOSE_MOVIE[0]);
	
	public JPanelChooseList() {
		setLayout(null);
		playMovie();
	}
	
	private void playMovie(){
		new Thread(new Movie()).start();;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		background.createWindow(g);
		super.paintComponents(g);
	}
	
	private class Movie implements Runnable {
		public void run() {
			for(int i = 0 ; i < 16 ; i ++){
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				background.setImg(ImgModel.CHOOSE_MOVIE[i]);
				repaint();
			}
			JFrameMain main = JFrameMain.J_FRAME_MAIN;
			main.remove(JPanelChooseList.this);
			main.setContentPane(new JPanelModelChoose());
			main.revalidate();
		}
	}
	
	
}
