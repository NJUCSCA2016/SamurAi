package team.csca.view.pm;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;

import team.csca.view.extend.Layer;
import team.csca.view.extend.LayerBackground;
import team.csca.view.extend.PlayMovie;
import team.csca.view.image.ImgBackground;
import team.csca.view.image.ImgSamurai;

/**
 * 人机对战
 * 
 * @author Water
 *
 */
public class JPanelPM extends JPanel implements KeyListener {
	private Layer[] layers;

	int m, n;

	/**
	 * 武士编号 通过回合号对武士的编号进行索引
	 */
	int index = 0;
	/**
	 * 最大体力值
	 */
	int maxPower = 7;
	/**
	 * 当前体力值
	 */
	int nowPower = 7;
	/**
	 * 行动消耗的体力
	 */
	int cost;
	/**
	 * 回合数
	 */
	int round = 0;

	PlayMovie p = new PlayMovie();

	int[] x = new int[6];
	int[] y = new int[6];
	
	Font messageFont = new Font("宋体", Font.PLAIN, 40);

	public JPanelPM() {
		// p.setPath("Image/Start");
		// p.setNum(50);
		// p.playMovie("Image/Start", 50);
		this.setLayout(null);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this);
		this.add(new JButtonBack(this));
		Random r = new Random();

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
		layers = new Layer[] { new LayerBackground(0, 0, 1250, 700, ImgBackground.PP_PANEL),
				// new LayerBackground(40*x[0] + 13*y[0] + 232, -36*y[0] + 624,
				// 30, 30, ImgSystem.logo),
				// new LayerBackground(234, 624, 30, 30, ImgSystem.logo),
				// new LayerBackground(234+41, 624, 30, 30, ImgSystem.logo),
				// new LayerBackground(234+82, 624, 30, 30, ImgSystem.logo),
				// new LayerBackground(234+123, 624, 30, 30, ImgSystem.logo),
				// new LayerBackground(234+164, 624, 30, 30, ImgSystem.logo),
				// new LayerBackground(234+205, 624, 30, 30, ImgSystem.logo),
				// new LayerBackground(234+246, 624, 30, 30, ImgSystem.logo),
				// new LayerBackground(234+287, 624, 30, 30, ImgSystem.logo),
				// new LayerBackground(234+13, 624-36, 30, 30, ImgSystem.logo),
				// new LayerBackground(234+26, 624-72, 30, 30, ImgSystem.logo),
				// new LayerBackground(234+39, 624-108, 30, 30, ImgSystem.logo),
				// new LayerBackground(234+52, 624-144, 30, 30, ImgSystem.logo),
				// new LayerBackground(40*x[1] + 13*y[1] + 234, -36*y[1] + 624,
				// 30, 30, ImgSystem.logo),
				// TODO: 需要把武士的图片加进去
				new LayerBackground(x[0] * 40 + y[0] * 13 + 234, y[0] * (-36) + 624, 30, 30, ImgSamurai.A0_FLAG),
				new LayerBackground(x[1] * 40 + y[1] * 13 + 234, y[1] * (-36) + 624, 30, 30, ImgSamurai.A1_FLAG),
				new LayerBackground(x[2] * 40 + y[2] * 13 + 234, y[2] * (-36) + 624, 30, 30, ImgSamurai.A2_FLAG),
				new LayerBackground(x[3] * 40 + y[3] * 13 + 234, y[3] * (-36) + 624, 30, 30, ImgSamurai.B0_FLAG),
				new LayerBackground(x[4] * 40 + y[4] * 13 + 234, y[4] * (-36) + 624, 30, 30, ImgSamurai.B1_FLAG),
				new LayerBackground(x[5] * 40 + y[5] * 13 + 234, y[5] * (-36) + 624, 30, 30, ImgSamurai.B2_FLAG),

		};

		// if (Player.MUSiC_PLAYER.isBack_ON()) {
		// Player.stopMusic();
		// Player.playMusic("bgm2");
		// }

	}

	public void paintComponent(Graphics g) {
		for (int i = 0; i < this.layers.length; i++) {
			layers[i].createWindow(g);
		}
		// TODO：这里的g.drawImage是动画移动的关键，但是貌似移动写错了
		// g.drawImage(arg0, arg1, arg2, arg3, arg4, arg5);
		
			g.drawImage(ImgSamurai.A0, x[0] * 40 + y[0] * 13 + 234, y[0] * (-36) + 624, 30, 30, this);
			g.drawImage(ImgSamurai.A1, x[1] * 40 + y[1] * 13 + 234, y[1] * (-36) + 624, 30, 30, this);
			g.drawImage(ImgSamurai.A2, x[2] * 40 + y[2] * 13 + 234, y[2] * (-36) + 624, 30, 30, this);
			g.drawImage(ImgSamurai.B0, x[3] * 40 + y[3] * 13 + 234, y[3] * (-36) + 624, 30, 30, this);
			g.drawImage(ImgSamurai.B1, x[4] * 40 + y[4] * 13 + 234, y[4] * (-36) + 624, 30, 30, this);
			g.drawImage(ImgSamurai.B2, x[5] * 40 + y[5] * 13 + 234, y[5] * (-36) + 624, 30, 30, this);
		
	
		g.setFont(messageFont);
		g.drawString(Integer.toString(nowPower), 1100, 600);
		super.paintComponents(g);
		if (index == 0) {
			g.drawImage(ImgSamurai.A0, 1000, 600, 30, 30, this);
		}
		if (index == 1) {
			g.drawImage(ImgSamurai.A1, 1000, 600, 30, 30, this);
		}
		if (index == 2) {
			g.drawImage(ImgSamurai.A2, 1000, 600, 30, 30, this);
		}
		if (index == 3) {
			g.drawImage(ImgSamurai.B0, 1000, 600, 30, 30, this);
		}
		if (index == 4) {
			g.drawImage(ImgSamurai.B1, 1000, 600, 30, 30, this);
		}
		if (index == 5) {
			g.drawImage(ImgSamurai.B2, 1000, 600, 30, 30, this);
		}
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
			cost = 2;
			if (nowPower - cost >= 0 && y[index] + 1 <= 14) {
				// if (y[index]+1 <=14) {
				nowPower = nowPower - cost;
				y[index] += 1;
				repaint();
				System.out.println(x[0]);
			}
			break;
		// TODO:这里开始貌似写错了
		case KeyEvent.VK_DOWN:
			cost = 2;
			if (nowPower - cost >= 0 && y[index] - 1 >= 0) {
				// if (y[index]-1 >= 0) {
				nowPower = nowPower - cost;
				y[index] -= 1;
				repaint();
			}
			break;
		case KeyEvent.VK_LEFT:
			cost = 2;
			if (nowPower - cost >= 0 && x[index] - 1 >= 0) {
				// if (x[index]-1 >= 0) {
				nowPower = nowPower - cost;
				x[index] -= 1;
				repaint();
			}
			break;
		case KeyEvent.VK_RIGHT:
			cost = 2;
			if (nowPower - cost >= 0 && x[index] + 1 <= 14) {
				// if (x[index]+1 <=14) {
				nowPower = nowPower - cost;
				x[index] += 1;
				repaint();
			}
			break;
		case KeyEvent.VK_Q:
			round++;
			/**
			 * 0:A0，
			 * 1:A1，
			 * 2:A2，
			 * 3:B0，
			 * 4:B1，
			 * 5:B2
			 * A0 - B0 - B1 - A1 - A2 - B2 - B0 - A0 - A1 - B1 - B2 - A2
			 * 0 - 3 - 4 - 1 - 2 - 5 - 3 - 0 - 1 - 4 - 5 - 2 
			 */
			if (round % 12 == 0) {
				index = 0;
			}
			if (round % 12 == 1) {
				index = 3;
			}
			if (round % 12 == 2) {
				index = 4;
			}
			if (round % 12 == 3) {
				index = 1;
			}
			if (round % 12 == 4) {
				index = 2;
			}
			if (round % 12 == 5) {
				index = 5;
			}
			if (round % 12 == 6) {
				index = 3;
			}
			if (round % 12 == 7) {
				index = 0;
			}
			if (round % 12 == 8) {
				index = 1;
			}
			if (round % 12 == 9) {
				index = 4;
			}
			if (round % 12 == 10) {
				index = 5;
			}
			if (round % 12 == 11) {
				index = 2;
			}

			nowPower = 7;
			repaint();
		default:
			break;
		}
		// repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
