package team.csca.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class JPanelStartGame extends JPanel{
	/*
	 * Frame
	 */
	private JFrameMain frame = JFrameMain.J_FRAME_MAIN;
	
	private Layer[] layers;
	// 开始界面
	public Image img =  new ImageIcon("Image/Start/80.png").getImage();
//	
//	JButton jbnPlay = new JButton();
//	JButton jbnPlay = new JButtonPlay(this);
	
	ImageIcon iiStart = new ImageIcon("Image/Button/P2.png");

//	public void paint(Graphics g){
//		g.drawImage(img, 0, 0, null);
//	}
	
	public JPanelStartGame(){
		
		this.setLayout(null);
		layers = new Layer[] {
			new LayerBackground(0, 0, 1250, 700, img),	
		};
//		JButton jbnPlay = new JButtonPlay(this);
//		this.add(jbnPlay);
//		this.add(jbnPlay);
//		jbnPlay.setIcon(iiStart);
//		jbnPlay.setBounds(350, 480, 300, 75);
		
	}
	public void paint(Graphics g){
		g.drawString("MUMAAAAA", getWidth() >> 1, getHeight() >> 1);
	}
}
