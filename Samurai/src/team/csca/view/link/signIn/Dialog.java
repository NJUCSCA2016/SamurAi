package team.csca.view.link.signIn;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

import team.csca.view.extend.DynamicButton;
import team.csca.view.extend.LayerBackground;

public class Dialog extends JDialog{
	
	private LayerBackground background;
	
	public Dialog(JFrame frame, Image back) {
		
		super(frame, true);
		setBounds(425,114,400,400);
		setUndecorated(true);
		setVisible(true);
		
		
	}
	private class Return extends DynamicButton{

		public Return() {
			super(0,0,0,0,null,null,null);
			// TODO Auto-generated constructor stub
		}
		
		public void mouseClicked(MouseEvent e){
			Dialog.this.dispose();
			revalidate();
		}
		
	}
	
}
