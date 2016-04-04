/**
 * Date : Apr 3, 2016 3:54:58 PM
 */
package main;

import ui.startmovie.ImgMovie;

/**
 * @author Alone
 * Written by YYM
 */
public class Main {
		
	public static Thread loadImage;

	public static void main(String[] args) {
		Main.loadImage = new Thread(new ImgMovie());
		Main.loadImage.start();
		//��ʼ�����ء�������ع��̺�ʱ����movie��ʾ������
		new FrameGame();
		
	}
	
}
