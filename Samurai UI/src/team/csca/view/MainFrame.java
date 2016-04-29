package team.csca.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * This class decides the main frame of this software
 * and other classes about GUI are based on it
 * 这个文件确定了软件的主框架
 * 并且其他关于人机交互的文件都是基于这个文件来写的
 * @author Water
 *
 */
public class MainFrame extends JFrame {
	public MainFrame(){
		super();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1250, 700);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = (screen.width - this.getWidth())>>1;
		int y = ((screen.height - this.getHeight())>>1)-20;
		this.setLocation(x, y);
	}
	
}
