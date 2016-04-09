package ui.startmovie;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import image.ImgButton;
import main.SuperButton;

public class ButtonPlay extends SuperButton{
	
	private JFrame frame;
	private JPanel fatherPanel;
	private JPanel newPanel;

	public ButtonPlay(JFrame frame, JPanel fatherPanel, JPanel newPanel) {
		super(350, 485, 300, 80, ImgButton.play1, ImgButton.play2, ImgButton.play3);
		this.frame = frame;
		this.fatherPanel = fatherPanel;
		this.newPanel = newPanel;
	}
	
	public void mouseClicked(MouseEvent e){
		super.mouseClicked(e);
		this.setBounds(390, 495, 220, 60);

		this.setFocusable(false);
		//移除该Panel
		this.frame.remove(this);
		this.frame.setContentPane(this.newPanel);
		//移除旧contentpane后进行重画
		this.frame.revalidate();
		
	}
	public void mouseEntered(MouseEvent e){
		super.mouseEntered(e);
		this.setBounds(370, 490, 260, 70);
	}
	public void mouseExited(MouseEvent e){
		super.mouseExited(e);
		this.setBounds(350, 485, 300, 80);
	}
}
