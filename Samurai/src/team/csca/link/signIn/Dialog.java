package team.csca.link.signIn;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import team.csca.view.extend.DynamicButton;
import team.csca.view.extend.Layer;
import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgLink;

public class Dialog extends JPanel{

	private Layer background;
	
	private JPanelSignIn fatherPanel;
	
	public Dialog( Image back , JPanelSignIn fatherPanel) {
		setLayout(null);
		this.fatherPanel = fatherPanel;
		background = new LayerBackground(0, 0, 1250, 700, back);
		add(new Return());
	}

	public void paintComponent(Graphics g) {
		background.createWindow(g);
		System.out.println("Create back");
		super.paintComponents(g);
	}
	
	private class Return extends DynamicButton{

		public Return() {
			super(585 , 400,80, 33,ImgLink.LOGIN_SMALL_ONE,ImgLink.LOGIN_SMALL_TWO,ImgLink.LOGIN_SMALL_THREE);
		}
		
		public void mouseClicked(MouseEvent e){
			repaint();
			frame.remove(Dialog.this);
			frame.setContentPane(fatherPanel);
			revalidate();
		}
		
	}
	
}
