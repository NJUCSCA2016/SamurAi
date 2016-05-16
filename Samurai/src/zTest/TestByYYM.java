package zTest;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import team.csca.view.extend.DynamicButton;
import team.csca.view.extend.Layer;
import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgLink;

public class TestByYYM extends JPanel{
	
	public TestByYYM() {
		setLayout(null);
		JButton button = new JButton("CLICK");
		button.setBounds(50, 60, 70, 70);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TestByYYM.this.setEnabled(false);
				TestByYYM.this.add(new DiaPanel(ImgLink.SIGNIN_NAME_NOT_FOUND));
			}
		});
		add(button);
		
	}
	
private class DiaPanel extends JPanel{
		
		private Layer background;
		
		public DiaPanel(Image back) {
			setLayout(null);
			background = new LayerBackground( 0, 0, 170, 175, back);
			add(new Return());
		}
		
		public void paint(Graphics g){
			background.createWindow(g);
			super.paintComponent(g);
		}
			
	}
	
	private class Return extends DynamicButton{

		public Return() {
			super(45 , 120 ,80, 33,ImgLink.LOGIN_SMALL_ONE,ImgLink.LOGIN_SMALL_TWO,ImgLink.LOGIN_SMALL_THREE);
		}
		
		public void mouseClicked(MouseEvent e){
			setEnabled(false);
			repaint();
		}
		
	}
	
	public void paintComponents(Graphics g){
		super.paintComponent(g);
	}
	
	public static void main(String[] args) {
		JFrame test = new JFrame("YT");
		
		test.setSize(1250, 700);
		
		test.setContentPane(new TestByYYM());
		
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}
	
	
	
}
