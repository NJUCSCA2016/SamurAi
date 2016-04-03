/**
 * Date : Apr 3, 2016 3:56:28 PM
 */
package main;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import ui.startmovie.PanelStartMovie;

/**
 * @author Alone
 * Written by YYM
 */
public class FrameGame extends JFrame{

	/**
	 * ���ô��ڲ���
	 */
	public FrameGame() {
		
		this.setTitle("SAMURAI");
		Toolkit toolkit = this.getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		//���ò��ɸı��С
		this.setVisible(false);
		this.setSize(1250, 700);
		//��������
		int x = this.getWidth() + dimension.width >> 1;
		int y = this.getHeight() + dimension.height >> 1;
 		this.setLocation(x, y);
 		
 		this.setUndecorated(true);
 		//TODO : Logo
 		//����logo ��  this.setIconImage();
		
 		this.setContentPane(new PanelStartMovie(this));
 		
		this.setVisible(true); 
		
	}
	
	private Image getLogo(){
		
		
		return null;
	}
	
}
