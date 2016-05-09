package team.csca.view.pm;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;

import team.csca.view.extend.Layer;
import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgBackground;
import team.csca.view.image.ImgSamurai;

/**
 * 人机对战
 * @author Water
 *
 */
public class JPanelPM extends JPanel implements KeyListener{
	private Layer[] layers;
	
	int x,y;
	
	int index;
	
	int maxPower = 7;
	
	int nowPower;
	
	int cost;
	
	
	public JPanelPM(){
		this.setFocusable(true);
		this.addKeyListener(this);
		Random r = new Random();
		int[] x = new int[6];
		int[] y = new int[6];
		// 从左到右是x,从上到下是y
		// 关于x的系数为正，关于y的系数为负
		x[0] = r.nextInt(8);
		y[0] = 0;
		x[1] = 0;
		y[1] = r.nextInt(5) + 1;
		x[2] = 0;
		y[2] = r.nextInt(9) + 5;
		x[3] = r.nextInt(6) + 8;
		y[3] = 0;
		x[4] = r.nextInt(7) + 1;
		y[4] = 14;
		x[5] = r.nextInt(6) + 7;
		y[5] = 14;
		// x = x[i] * 40 + y[i] * 13 + 232
		// y = y[i] * (-36) + 624
		layers = new Layer[]{
				new LayerBackground(0, 0, 1250, 700, ImgBackground.PP_PANEL),
//				new LayerBackground(40*x[0] + 13*y[0] + 232, -36*y[0] + 624, 30, 30, ImgSystem.logo),
//				new LayerBackground(234, 624, 30, 30, ImgSystem.logo),
//				new LayerBackground(234+41, 624, 30, 30, ImgSystem.logo),
//				new LayerBackground(234+82, 624, 30, 30, ImgSystem.logo),
//				new LayerBackground(234+123, 624, 30, 30, ImgSystem.logo),
//				new LayerBackground(234+164, 624, 30, 30, ImgSystem.logo),
//				new LayerBackground(234+205, 624, 30, 30, ImgSystem.logo),
//				new LayerBackground(234+246, 624, 30, 30, ImgSystem.logo),
//				new LayerBackground(234+287, 624, 30, 30, ImgSystem.logo),
//				new LayerBackground(234+13, 624-36, 30, 30, ImgSystem.logo),
//				new LayerBackground(234+26, 624-72, 30, 30, ImgSystem.logo),
//				new LayerBackground(234+39, 624-108, 30, 30, ImgSystem.logo),
//				new LayerBackground(234+52, 624-144, 30, 30, ImgSystem.logo),
//				new LayerBackground(40*x[1] + 13*y[1] + 234, -36*y[1] + 624, 
//						30, 30, ImgSystem.logo),
				// TODO: 需要把武士的图片加进去
				new LayerBackground(x[0] * 40 + y[0] * 13 + 234, y[0] * (-36) + 624, 
						30, 30, ImgSamurai.A0),
				new LayerBackground(x[1] * 40 + y[1] * 13 + 234, y[1] * (-36) + 624, 
						30, 30, ImgSamurai.A1),
				new LayerBackground(x[2] * 40 + y[2] * 13 + 234, y[2] * (-36) + 624, 
						30, 30, ImgSamurai.A2),
				new LayerBackground(x[3] * 40 + y[3] * 13 + 234, y[3] * (-36) + 624, 
						30, 30, ImgSamurai.B0),
				new LayerBackground(x[4] * 40 + y[4] * 13 + 234, y[4] * (-36) + 624, 
						30, 30, ImgSamurai.B1),
				new LayerBackground(x[5] * 40 + y[5] * 13 + 234, y[5] * (-36) + 624, 
						30, 30, ImgSamurai.B2),
				
		};
	
		//		if (Player.MUSiC_PLAYER.isBack_ON()) {
//			Player.stopMusic();
//			Player.playMusic("bgm2");
//		}
		
	}
	
	public void paintComponent(Graphics g){
		for(int i = 0 ; i < this.layers.length ; i++){
			layers[i].createWindow(g);
		}
		
		super.paintComponents(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			// TODO:不知道AI是怎么表示出来的
			System.out.println(121);
			if (nowPower - cost >= 0 && x+1 <=14) {
				x=x+1;
			}
			break;
		case KeyEvent.VK_DOWN:
			if (nowPower - cost >= 0 && x-1 >= 0) {
				x=x-1;
			}
			break;
		case KeyEvent.VK_LEFT:
			if (nowPower - cost >= 0 && y-1 >= 0) {
				y=y-1;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (nowPower - cost >= 0 && y+1 <=14) {
				y=y+1;
			}
			break;
		default:
			break;
		}
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}

