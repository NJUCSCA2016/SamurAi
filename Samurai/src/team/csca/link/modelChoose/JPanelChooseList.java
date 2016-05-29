package team.csca.link.modelChoose;

import java.awt.Graphics;

import javax.swing.JPanel;

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
		for(int i = 0 ; i < 16 ; i ++){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			background.setImg(ImgModel.CHOOSE_MOVIE[i]);
		}
		JFrameMain main = JFrameMain.J_FRAME_MAIN;
		main.remove(this);
		main.setContentPane(new JPanelModelChoose());
		main.revalidate();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		background.createWindow(g);
		super.paintComponent(g);
	}
	
	
}
