package team.csca.view.link.signIn;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import team.csca.view.extend.DynamicButton;
import team.csca.view.extend.Layer;
import team.csca.view.extend.LayerBackground;
import team.csca.view.frame.JFrameMain;
import team.csca.view.image.ImgLink;

public class Dialog extends JFrame{

	
	public Dialog( Image back) {
		super();
		setLayout(null);
		setBounds(595, 263, 170, 175);
		this.setContentPane(new DiaPanel(back));
		setUndecorated(true);
		setVisible(true);
		
	}
	
	private class DiaPanel extends JPanel{
		
		private Layer background;
		
		public DiaPanel(Image back) {
			setOpaque(true);
			setLayout(null);
			background = new LayerBackground( 0, 0, 170, 175, back);
			add(new Return());
		}
		
		public void paint(Graphics g){
			background.createWindow(g);
			super.paintComponents(g);
		}
			
	}
	
	private class Return extends DynamicButton{

		public Return() {
			super(45 , 120 ,80, 33,ImgLink.LOGIN_SMALL_ONE,ImgLink.LOGIN_SMALL_TWO,ImgLink.LOGIN_SMALL_THREE);
		}
		
		public void mouseClicked(MouseEvent e){
			repaint();
			Dialog.this.dispose();
			JFrameMain.J_FRAME_MAIN.setEnabled(true);
			JFrameMain.J_FRAME_MAIN.requestFocus();
			revalidate();
		}
		
	}
	
}
