package team.csca.view.pm;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

//	int m, n;

	/**
	 * 武士编号 通过回合号对武士的编号进行索引
	 */
	public int index = 0;
	/**
	 * 最大体力值
	 */
	public int maxPower = 7;
	/**
	 * 当前体力值
	 */
	public int nowPower = 7;
	/**
	 * 行动消耗的体力
	 */
	public int cost;
	/**
	 * 回合数
	 */
	public int round = 0;
	/**
	 * 方向
	 */
	public int[] direction = { 0, 0, 0, 0, 0, 0 };

	PlayMovie p = new PlayMovie();
	/**
	 * x,y 代表现在的坐标
	 */
	public int[] x = new int[6];
	public int[] y = new int[6];
	/**
	 * 代表大本营的位置
	 */
	public int[] homeX = new int[6];
	public int[] homeY = new int[6];
	/**
	 * 代表占领的位置
	 */
	public int[] occupation = new int[225];


	Font messageFont = new Font("宋体", Font.PLAIN, 40);

	public JPanelPM() {
		// p.setPath("Image/Start");
		// p.setNum(50);
		// p.playMovie("Image/Start", 50);
		this.setVisible(true);
		this.setLayout(null);
		this.setFocusable(true);
		this.requestFocus(true);
		this.setEnabled(true);
		this.requestFocus();
		this.addKeyListener(this);
		this.add(new JButtonBack(this));
		Random r = new Random();

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				requestFocus(true);
			}
		});

		// 从左到右是x,从上到下是y
		// 关于x的系数为正，关于y的系数为负
		x[0] = r.nextInt(7) + 1;
		// TODO:y[0]被改了，原来为0
		y[0] = 2;
		x[1] = 14;
		y[1] = r.nextInt(5) + 1;
		x[2] = 0;
		y[2] = r.nextInt(9) + 5;
		x[3] = r.nextInt(6) + 8;
		y[3] = 0;
		x[4] = r.nextInt(7) + 1;
		y[4] = 14;
		x[5] = r.nextInt(6) + 7;
		y[5] = 14;
		for (int i = 0; i < 6; i++) {
			homeX[i] = x[i];
			homeY[i] = y[i];
		}
		/**
		 * 初始化occupation
		 */
		for (int i = 0; i < occupation.length; i++) {
			occupation[i] = -1;
		}
		/**
		 * 把所有大本营初始化
		 */
		for (int i = 0; i < 6; i++) {
			int temp = 15 * homeX[i] + homeY[i];
			occupation[temp] = i;
		}
		// x = x[i] * 40 + y[i] * 13 + 232
		// y = y[i] * (-36) + 624
		layers = new Layer[] { 
				new LayerBackground(0, 0, 1250, 700, ImgBackground.PM_PANEL),
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
				/**
				 * 占领的地方都有这个标志
				 */
				new LayerBackground(x[0] * 40 + y[0] * 13 + 227, y[0] * (-36) + 622, 57, 36, ImgSamurai.A0_SHADOW),
				new LayerBackground(x[1] * 40 + y[1] * 13 + 227, y[1] * (-36) + 622, 57, 36, ImgSamurai.A1_SHADOW),
				new LayerBackground(x[2] * 40 + y[2] * 13 + 227, y[2] * (-36) + 622, 57, 36, ImgSamurai.A2_SHADOW),
				new LayerBackground(x[3] * 40 + y[3] * 13 + 227, y[3] * (-36) + 622, 57, 36, ImgSamurai.B0_SHADOW),
				new LayerBackground(x[4] * 40 + y[4] * 13 + 227, y[4] * (-36) + 622, 57, 36, ImgSamurai.B1_SHADOW),
				new LayerBackground(x[5] * 40 + y[5] * 13 + 227, y[5] * (-36) + 622, 57, 36, ImgSamurai.B2_SHADOW),
				/**
				 * 大本营标志，旗帜
				 */
				new LayerBackground(x[0] * 40 + y[0] * 13 + 252, y[0] * (-36) + 614, 30, 30, ImgSamurai.A0_FLAG),
				new LayerBackground(x[1] * 40 + y[1] * 13 + 252, y[1] * (-36) + 614, 30, 30, ImgSamurai.A1_FLAG),
				new LayerBackground(x[2] * 40 + y[2] * 13 + 252, y[2] * (-36) + 614, 30, 30, ImgSamurai.A2_FLAG),
				new LayerBackground(x[3] * 40 + y[3] * 13 + 252, y[3] * (-36) + 614, 30, 30, ImgSamurai.B0_FLAG),
				new LayerBackground(x[4] * 40 + y[4] * 13 + 252, y[4] * (-36) + 614, 30, 30, ImgSamurai.B1_FLAG),
				new LayerBackground(x[5] * 40 + y[5] * 13 + 252, y[5] * (-36) + 614, 30, 30, ImgSamurai.B2_FLAG),

		};

		// if (Player.MUSiC_PLAYER.isBack_ON()) {
		// Player.stopMusic();
		// Player.playMusic("bgm2");
		// }

	}

	public void paintComponent(Graphics g) {
		calculateIndex();
		/*
		 * 添加各个组件
		 * 地图
		 * 每个武士老家的占领标志
		 * 每个武士大本营的旗帜
		 */
		for (int i = 0; i < this.layers.length; i++) {
			layers[i].createWindow(g);
		}
		/**
		 * 防止大本营被占领
		 * 此时把所有的大本营重新赋值一遍
		 * TIP:赋值在统计占领地盘数量之前
		 */
		for (int i = 0; i < 6; i++) {
			int temp = 15 * homeX[i] + homeY[i];
			occupation[temp] = i;
		}
		/*
		 * 把所有占领的地盘进行绘制
		 */
		// 行
		for (int i = 0; i < 15; i++) {
			// 列
			for (int j = 0; j < 15; j++) {
				int temp = 15 * i + j;
				if (occupation[temp] != -1) {
					g.drawImage(ImgSamurai.AI_SHADOW[occupation[temp]], i * 40 + j * 13 + 227, j * (-36) + 622, 57, 36, this);
				}
			}
		}

//		for (int i = 1; i < 7; i++) {
//			g.drawImage(ImgSamurai.FLAGS[i - 1], x[i - 1] * 40 + y[i - 1] * 13 + 252, y[i - 1] * (-36) + 614, 30, 30, this);
//		}
		/**
		 * 绘制旗帜
		 * 因为之前绘制了占领地盘
		 * 占领地盘会覆盖旗帜
		 * 所以需要重新绘制旗帜
		 */
		for (int i = 7; i < this.layers.length; i++) {
			layers[i].createWindow(g);
		}
		g.drawImage(ImgSamurai.A0_PICTURE[direction[0]], x[0] * 40 + y[0] * 13 + 228, y[0] * (-36) + 600, 50, 50, this);
		g.drawImage(ImgSamurai.A1_PICTURE[direction[1]], x[1] * 40 + y[1] * 13 + 228, y[1] * (-36) + 600, 50, 50, this);
		g.drawImage(ImgSamurai.A2_PICTURE[direction[2]], x[2] * 40 + y[2] * 13 + 228, y[2] * (-36) + 600, 50, 50, this);
		g.drawImage(ImgSamurai.B0_PICTURE[direction[3]], x[3] * 40 + y[3] * 13 + 228, y[3] * (-36) + 600, 50, 50, this);
		g.drawImage(ImgSamurai.B1_PICTURE[direction[4]], x[4] * 40 + y[4] * 13 + 228, y[4] * (-36) + 600, 50, 50, this);
		g.drawImage(ImgSamurai.B2_PICTURE[direction[5]], x[5] * 40 + y[5] * 13 + 228, y[5] * (-36) + 600, 50, 50, this);
		// FIXME:测试用代码
		g.setFont(messageFont);
		g.drawString("Power: " + Integer.toString(nowPower), 900, 300);
		g.drawString("Index: " + Integer.toString(index), 900, 350);
		g.drawString("IsHidden: " + isHidden(), 900, 400);
		g.drawString("Direction: " + Integer.toString(direction[index]), 900, 450);
		super.paintComponents(g);
		/**
		 * 提示是哪一个武士在行动
		 */
		if (index == 0) {
			g.drawImage(ImgSamurai.A0, 1000, 600, 50, 50, this);
		}
		if (index == 1) {
			g.drawImage(ImgSamurai.A1, 1000, 600, 50, 50, this);
		}
		if (index == 2) {
			g.drawImage(ImgSamurai.A2, 1000, 600, 50, 50, this);
		}
		if (index == 3) {
			g.drawImage(ImgSamurai.B0, 1000, 600, 50, 50, this);
		}
		if (index == 4) {
			g.drawImage(ImgSamurai.B1, 1000, 600, 50, 50, this);
		}
		if (index == 5) {
			g.drawImage(ImgSamurai.B2, 1000, 600, 50, 50, this);
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
			// FIXME:删除测试代码
			System.out.println(121);
			cost = 2;
			// if (nowPower - cost >= 0 && y[index] + 1 <= 14) {
			// if (nowPower - cost >= 0 && canMoveTo(x[index], y[index], 0, 1))
			// {
			// FIXME:修改判定条件
			// if (canMoveTo(0, 1)) {
			if (canMoveTo(0, 1) && hasPower() && !isHidden()) {
				// if (y[index]+1 <=14) {
				nowPower = nowPower - cost;
				y[index] += 1;
				direction[index] = 1;
				// if (isHidden()) {
				// direction[index] = 5;
				// }
				repaint();
				System.out.println(x[0]);
			}
			if (canMoveTo(0, 1) && hasPower() && isHidden()) {
				// if (y[index]+1 <=14) {
				nowPower = nowPower - cost;
				y[index] += 1;
				direction[index] = 5;
				// if (isHidden()) {
				// direction[index] = 5;
				// }
				repaint();
				System.out.println(x[0]);
			}
			break;
		// TODO:这里开始貌似写错了
		case KeyEvent.VK_DOWN:
			cost = 2;
			// if (nowPower - cost >= 0 && y[index] - 1 >= 0) {
			// if (nowPower - cost >= 0 && canMoveTo(x[index], y[index], 0, -1))
			// {
			// FIXME:修改判定条件
			// if (canMoveTo(0, -1)) {
			if (canMoveTo(0, -1) && hasPower() && !isHidden()) {
				// if (y[index]-1 >= 0) {
				nowPower = nowPower - cost;
				y[index] -= 1;
				direction[index] = 0;
				// if (isHidden()) {
				// direction[index] = 4;
				// }
				repaint();
			}
			if (canMoveTo(0, -1) && hasPower() && isHidden()) {
				// if (y[index]-1 >= 0) {
				nowPower = nowPower - cost;
				y[index] -= 1;
				direction[index] = 4;
				// if (isHidden()) {
				// direction[index] = 4;
				// }
				repaint();
			}
			break;
		case KeyEvent.VK_LEFT:
			cost = 2;
			// if (nowPower - cost >= 0 && x[index] - 1 >= 0) {
			// if (nowPower - cost >= 0 && canMoveTo(x[index], y[index], -1, 0))
			// {
			// FIXME:修改判定条件
			// if (canMoveTo(-1, 0)) {
			if (canMoveTo(-1, 0) && hasPower() && !isHidden()) {
				// if (x[index]-1 >= 0) {
				nowPower = nowPower - cost;
				x[index] -= 1;
				direction[index] = 2;
				// if (isHidden()) {
				// direction[index] = 6;
				// }
				repaint();
			}
			if (canMoveTo(-1, 0) && hasPower() && isHidden()) {
				// if (x[index]-1 >= 0) {
				nowPower = nowPower - cost;
				x[index] -= 1;
				direction[index] = 6;
				// if (isHidden()) {
				// direction[index] = 6;
				// }
				repaint();
			}
			break;
		case KeyEvent.VK_RIGHT:
			cost = 2;
			// if (nowPower - cost >= 0 && x[index] + 1 <= 14) {
			// if (nowPower - cost >= 0 && canMoveTo(x[index], y[index], 1, 0))
			// {
			// FIXME:修改判定条件
			// if (canMoveTo(1, 0)) {
			if (canMoveTo(1, 0) && hasPower() && !isHidden()) {
				// if (x[index]+1 <=14) {
				nowPower = nowPower - cost;
				x[index] += 1;
				direction[index] = 3;
				// if (isHidden()) {
				// direction[index] = 7;
				// }
				repaint();
			}
			if (canMoveTo(1, 0) && hasPower() && isHidden()) {
				// if (x[index]+1 <=14) {
				nowPower = nowPower - cost;
				x[index] += 1;
				direction[index] = 7;
				// if (isHidden()) {
				// direction[index] = 7;
				// }
				repaint();
			}
			break;
		// 切换人物
		case KeyEvent.VK_Q:
			round++;
			/**
			 * 0:A0， 1:A1， 2:A2， 3:B0， 4:B1， 5:B2 A0 - B0 - B1 - A1 - A2 - B2 -
			 * B0 - A0 - A1 - B1 - B2 - A2 0 - 3 - 4 - 1 - 2 - 5 - 3 - 0 - 1 - 4
			 * - 5 - 2
			 */
			// if (round % 12 == 0) {
			// index = 0;
			// }
			// if (round % 12 == 1) {
			// index = 3;
			// }
			// if (round % 12 == 2) {
			// index = 4;
			// }
			// if (round % 12 == 3) {
			// index = 1;
			// }
			// if (round % 12 == 4) {
			// index = 2;
			// }
			// if (round % 12 == 5) {
			// index = 5;
			// }
			// if (round % 12 == 6) {
			// index = 3;
			// }
			// if (round % 12 == 7) {
			// index = 0;
			// }
			// if (round % 12 == 8) {
			// index = 1;
			// }
			// if (round % 12 == 9) {
			// index = 4;
			// }
			// if (round % 12 == 10) {
			// index = 5;
			// }
			// if (round % 12 == 11) {
			// index = 2;
			// }
			calculateIndex();
			nowPower = 7;
			repaint();
			break;

		// 隐身
		case KeyEvent.VK_W:
			cost = 1;
			if (canHide() && hasPower()) {
				nowPower = nowPower - cost;
				direction[index] += 4;
			}
			repaint();
			break;
		// 现行
		case KeyEvent.VK_E:
			cost = 1;
			if (canShow() && hasPower()) {
				nowPower = nowPower - cost;
				direction[index] -= 4;
			}
			repaint();
			break;
		case KeyEvent.VK_R:
			cost = 4;
			// TODO:增加判断条件
			if (hasPower()) {
				nowPower = nowPower - cost;
				occupy();
			}
			
			repaint();
			break;
		default:
			break;
		}
		// repaint();

	}

	public void calculateIndex() {
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

	}

	/**
	 * 是否能现身 TODO：增加其他判定条件
	 * 
	 * @return
	 */
	private boolean canShow() {
		// 在不是隐身的情况下不可以现身
		if (!isHidden()) {
			return false;
		}
		// 这格子有现行的人的情况下不能现身
		for (int i = 0; i < 6; i++) {
			if (x[index] == x[i] && y[index] == y[i] && i != index && direction[i] <= 3) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否能隐藏 TODO：可能还需要添加其他判定条件
	 * 
	 * @return
	 */
	public boolean canHide() {
		/*
		 * 如果已经处在隐身状态下，就不能隐身
		 */
		if (direction[index] > 3) {
			return false;
		}
		/*
		 * 只有在自己家的地盘上才能隐身
		 */
		int temp = 15 * x[index] + y[index];
		if (index >= 0 && index <= 2) {
			if (!(occupation[temp] >= 0 && occupation[temp] <= 2)) {
				return false;
			}
		}
		if (index >= 3 && index <= 5) {
			if (!(occupation[temp] >= 3 && occupation[temp] <= 5)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 单位是否隐身
	 * 
	 * @return
	 */
	public boolean isHidden() {
		calculateIndex();
		if (direction[index] > 3) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param dx
	 *            x方向的位移
	 * @param dy
	 *            y方向的位移
	 * @param index
	 *            AI的编号
	 * @return 是否能够移动
	 */
	public boolean canMoveTo(int dx, int dy) {
		/*
		 * 这一块是判断是否越界
		 */
		if (x[index] + dx > 14) {
			return false;
		}
		if (x[index] + dx < 0) {
			return false;
		}
		if (y[index] + dy > 14) {
			return false;
		}
		if (y[index] + dy < 0) {
			return false;
		}
		/*
		 * 如果有非隐身单位，那么无法移动过去
		 */
		for (int i = 0; i < 6; i++) {
			if (x[index] + dx == x[i] && y[index] + dy == y[i] && !isHidden()) {
				return false;
			}
		}
		/*
		 * 无法移动到别人的大本营
		 */
		for (int i = 0; i < 6; i++) {
			if (x[index] + dx == homeX[i] && y[index] + dy == homeY[i] && index != i) {
				return false;
			}
		}
		
		if (index < 3 && isHidden()) {
			int temp = 15 * (x[index] + dx) + y[index] + dy;
			if (!(occupation[temp] >= 0 && occupation[temp] <= 2)) {
				return false;
			}
		}
		if (index >= 3 && isHidden()) {
			int temp = 15 * (x[index] + dx) + y[index] + dy;
			if (!(occupation[temp] >= 3 && occupation[temp] <= 5)) {
				return false;
			}
		}
		return true;
	}

	public boolean hasPower() {
		if (nowPower - cost < 0) {
			return false;
		}
		return true;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void occupy() {
		// 长矛
		if (index == 0 || index == 3) {
			// 向下
			if (direction[index] == 0) {
				// TODO: 添加判断条件
				int temp = 15 * x[index] + y[index];
				if (y[index] >= 4) {
					occupation[temp - 1] = index;
					occupation[temp - 2] = index;
					occupation[temp - 3] = index;
					occupation[temp - 4] = index;
					int[] location = {temp-1, temp-2, temp-3, temp-4};
					beatOthers(location);
				}
				if (y[index] == 3) {
					occupation[temp - 1] = index;
					occupation[temp - 2] = index;
					occupation[temp - 3] = index;
					int[] location = {temp-1, temp-2, temp-3};
					beatOthers(location);
				}
				if (y[index] == 2) {
					occupation[temp - 1] = index;
					occupation[temp - 2] = index;
					int[] location = {temp-1, temp-2};
					beatOthers(location);
				}
				if (y[index] == 1) {
					occupation[temp - 1] = index;
					int[] location = {temp-1};
					beatOthers(location);
				}
			}
			// 向上
			if (direction[index] == 1) {
				// TODO: 添加判断条件
				int temp = 15 * x[index] + y[index];
				if (y[index] <= 10) {
					occupation[temp + 1] = index;
					occupation[temp + 2] = index;
					occupation[temp + 3] = index;
					occupation[temp + 4] = index;
					int[] location = {temp+1, temp+2, temp+3, temp+4};
					beatOthers(location);
				}
				if (y[index] == 11) {
					occupation[temp + 1] = index;
					occupation[temp + 2] = index;
					occupation[temp + 3] = index;
					int[] location = {temp+1, temp+2, temp+3};
					beatOthers(location);
				}
				if (y[index] == 12) {
					occupation[temp + 1] = index;
					occupation[temp + 2] = index;
					int[] location = {temp+1, temp+2};
					beatOthers(location);
				}
				if (y[index] == 13) {
					occupation[temp + 1] = index;
					int[] location = {temp+1};
					beatOthers(location);
				}
				
			}
			// 向左
			if (direction[index] == 2) {
				// TODO: 添加判断条件
				int temp = 15 * x[index] + y[index];
				if (x[index] >= 4) {
					occupation[temp - 15] = index;
					occupation[temp - 30] = index;
					occupation[temp - 45] = index;
					occupation[temp - 60] = index;
					int[] location = {temp-15, temp-30, temp-45, temp-60};
					beatOthers(location);
				}
				if (x[index] == 3) {
					occupation[temp - 15] = index;
					occupation[temp - 30] = index;
					occupation[temp - 45] = index;
					int[] location = {temp-15, temp-30, temp-45};
					beatOthers(location);
				}
				if (x[index] == 2) {
					occupation[temp - 15] = index;
					occupation[temp - 30] = index;
					int[] location = {temp-15, temp-30};
					beatOthers(location);
				}
				if (x[index] == 1) {
					occupation[temp - 15] = index;
					int[] location = {temp-15};
					beatOthers(location);
				}
				
			}
			// 向右
			if (direction[index] == 3) {
				// TODO: 添加判断条件
				int temp = 15 * x[index] + y[index];
				if (x[index] <= 10) {
					occupation[temp + 15] = index;
					occupation[temp + 30] = index;
					occupation[temp + 45] = index;
					occupation[temp + 60] = index;
					int[] location = {temp+15, temp+30, temp+45, temp+60};
					beatOthers(location);
				}
				if (x[index] == 11) {
					occupation[temp + 15] = index;
					occupation[temp + 30] = index;
					occupation[temp + 45] = index;
					int[] location = {temp+15, temp+30, temp+45};
					beatOthers(location);
				}
				if (x[index] == 12) {
					occupation[temp + 15] = index;
					occupation[temp + 30] = index;
					int[] location = {temp+15, temp+30};
					beatOthers(location);
				}
				if (x[index] == 13) {
					occupation[temp + 15] = index;
					int[] location = {temp+15};
					beatOthers(location);
				}
				
			}
			
			
		}
		// 剑
		if (index == 1 || index == 4) {
			// 向下
			if (direction[index] == 0) {
				int temp = 15 * x[index] + y[index];
				if (y[index] >= 2 && x[index] <= 12) {
					occupation[temp - 1] = index;
					occupation[temp - 2] = index;
					occupation[temp + 15] = index;
					occupation[temp + 14] = index;
					occupation[temp + 30] = index;
					int[] location = {temp-1, temp-2, temp+15, temp+14, temp+30};
					beatOthers(location);
				}
				if (y[index] == 1 && x[index] <= 12) {
					occupation[temp - 1] = index;
					occupation[temp + 15] = index;
					occupation[temp + 14] = index;
					occupation[temp + 30] = index;
					int[] location = {temp-1, temp+15, temp+14, temp+30};
					beatOthers(location);
				}
				if (y[index] == 0 && x[index] <= 12) {
					occupation[temp + 15] = index;
					occupation[temp + 30] = index;
					int[] location = {temp+15, temp+30};
					beatOthers(location);
				}
				if (y[index] >= 2 && x[index] == 13) {
					occupation[temp - 1] = index;
					occupation[temp - 2] = index;
					occupation[temp + 15] = index;
					occupation[temp + 14] = index;
					int[] location = {temp-1, temp-2, temp+15, temp+14};
					beatOthers(location);
				}
				if (y[index] == 1 && x[index] == 13) {
					occupation[temp - 1] = index;
					occupation[temp + 15] = index;
					occupation[temp + 14] = index;
					int[] location = {temp-1,temp+15, temp+14};
					beatOthers(location);
				}
				if (y[index] == 0 && x[index] == 13) {
					occupation[temp + 15] = index;
					int[] location = {temp+15};
					beatOthers(location);
				}
				if (y[index] >= 2 && x[index] == 14) {
					occupation[temp - 1] = index;
					occupation[temp - 2] = index;
					int[] location = {temp-1, temp-2};
					beatOthers(location);
				}
				if (y[index] == 1 && x[index] == 14) {
					occupation[temp - 1] = index;
					int[] location = {temp-1};
					beatOthers(location);
				}
			}
			// 向上
			if (direction[index] == 1) {
				int temp = 15 * x[index] + y[index];
				if (x[index] >= 2) {
					if (y[index] <= 12) {
						occupation[temp - 15] = index;
						occupation[temp - 14] = index;
						occupation[temp - 30] = index;
						occupation[temp + 1] = index;
						occupation[temp + 2] = index;
						int[] location = {temp-15, temp-14, temp-30, temp+1, temp+2};
						beatOthers(location);
					}
					if (y[index] == 13) {
						occupation[temp - 15] = index;
						occupation[temp - 14] = index;
						occupation[temp - 30] = index;
						occupation[temp + 1] = index;
						int[] location = {temp-15, temp-14, temp-30, temp+1};
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 15] = index;
						occupation[temp - 30] = index;
						int[] location = {temp-15,temp-30};
						beatOthers(location);
					}
				}
				if (x[index] == 1) {
					if (y[index] <= 12) {
						occupation[temp - 15] = index;
						occupation[temp - 14] = index;
						occupation[temp + 1] = index;
						occupation[temp + 2] = index;
						int[] location = {temp-15, temp-14, temp+1, temp+2};
						beatOthers(location);
					}
					if (y[index] == 13) {
						occupation[temp - 15] = index;
						occupation[temp - 14] = index;
						occupation[temp + 1] = index;
						int[] location = {temp-15, temp-14, temp+1};
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 15] = index;
						int[] location = {temp-15};
						beatOthers(location);
					}
				}
				if (x[index] == 0) {
					if (y[index] <= 12) {
						occupation[temp + 1] = index;
						occupation[temp + 2] = index;
						int[] location = {temp+1, temp+2};
						beatOthers(location);
					}
					if (y[index] == 13) {
						occupation[temp + 1] = index;
						int[] location = {temp+1};
						beatOthers(location);
					}
				}
			}
			// 向左
			if (direction[index] == 2) {
				int temp = 15 * x[index] + y[index];
				if (x[index] >= 2) {
					if (y[index] >= 2) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 30] = index;
						occupation[temp - 1] = index;
						occupation[temp - 2] = index;
						int[] location = {temp-15, temp-16, temp-30, temp-1, temp-2};
						beatOthers(location);
					}
					if (y[index] == 1) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 30] = index;
						occupation[temp - 1] = index;
						int[] location = {temp-15, temp-16, temp-30, temp-1};
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 15] = index;
						occupation[temp - 30] = index;
						int[] location = {temp-15,temp-30};
						beatOthers(location);
					}
				}
				if (x[index] == 1) {
					if (y[index] >= 2) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 1] = index;
						occupation[temp - 2] = index;
						int[] location = {temp-15, temp-16, temp-1, temp-2};
						beatOthers(location);
					}
					if (y[index] == 1) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 1] = index;
						int[] location = {temp-15, temp-16, temp-1};
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 15] = index;
						int[] location = {temp-15};
						beatOthers(location);
					}
				}
				if (x[index] == 0) {
					if (y[index] >= 2) {
						occupation[temp - 1] = index;
						occupation[temp - 2] = index;
						int[] location = {temp-1, temp-2};
						beatOthers(location);
					}
					if (y[index] == 1) {
						occupation[temp - 1] = index;
						int[] location = {temp-1};
						beatOthers(location);
					}
				}
			}
			// 向右
			if (direction[index] == 3) {
				int temp = 15 * x[index] + y[index];
				if (x[index] <= 12) {
					if (y[index] <= 12) {
						occupation[temp + 1] = index;
						occupation[temp + 2] = index;
						occupation[temp + 15] = index;
						occupation[temp + 16] = index;
						occupation[temp + 30] = index;
						int[] location = {temp+1, temp+2, temp+15, temp+16, temp+30};
						beatOthers(location);
					}
					if (y[index] == 13) {
						occupation[temp + 1] = index;
						occupation[temp + 15] = index;
						occupation[temp + 16] = index;
						occupation[temp + 30] = index;
						int[] location = {temp+1, temp+15, temp+16, temp+30};
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp + 15] = index;
						occupation[temp + 30] = index;
						int[] location = {temp+15, temp+30};
						beatOthers(location);
					}
				}
				if (x[index] == 13) {
					if (y[index] <= 12) {
						occupation[temp + 1] = index;
						occupation[temp + 2] = index;
						occupation[temp + 15] = index;
						occupation[temp + 16] = index;
						int[] location = {temp+1, temp+2, temp+15, temp+16};
						beatOthers(location);
					}
					if (y[index] == 13) {
						occupation[temp + 1] = index;
						occupation[temp + 15] = index;
						occupation[temp + 16] = index;
						int[] location = {temp+1, temp+15, temp+16};
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp + 15] = index;
						int[] location = {temp+15};
						beatOthers(location);
					}
				}
				if (x[index] == 14) {
					if (y[index] <= 12) {
						occupation[temp + 1] = index;
						occupation[temp + 2] = index;
						int[] location = {temp+1, temp+2};
						beatOthers(location);
					}
					if (y[index] == 13) {
						occupation[temp + 1] = index;
						int[] location = {temp+1};
						beatOthers(location);
					}
				}
			}
		}
		// 战斧
		if (index == 2 || index == 5) {
			// 向下
			if (direction[index] == 0) {
				int temp = 15 * x[index] + y[index];
				if (x[index] >= 1 && x[index] <= 13) {
					if (y[index] >= 1 && y[index] <= 13 ) {
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 1] = index;
						occupation[temp + 16] = index;
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = {temp-14, temp-15, temp-16, temp-1, temp+16, temp+15, temp+14};
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						occupation[temp + 16] = index;
						occupation[temp + 15] = index;
						int[] location = {temp-14, temp-15, temp+16, temp+15};
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 1] = index;
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = {temp-15, temp-16, temp-1, temp+15,temp+14};
						beatOthers(location);
					}
				}
				if (x[index] == 0) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp - 1] = index;
						occupation[temp + 16] = index;
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = {temp-1, temp+16,temp+15,temp+14};
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp + 16] = index;
						occupation[temp + 15] = index;
						int[] location = {temp+16,temp+15};
						beatOthers(location);
					}
					if (y[index] == 14) {
						// 这种情况并不会发生
					}
				}
				if (x[index] == 14) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 1] = index;
						int[] location = {temp-14, temp-15, temp-16, temp-1};
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						int[] location = {temp-14, temp-15};
						beatOthers(location);
					}
					if (y[index] == 14) {
						// 这种情况并不会发生
					}
				}
			}
			// 向上
			if (direction[index] == 1) {
				int temp = 15 * x[index] + y[index];
				if (x[index] >= 1 && x[index] <= 13) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp + 1] = index;
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp + 14] = index;
						occupation[temp + 15] = index;
						occupation[temp + 16] = index;
						int[] location = {temp+1, temp-14, temp-15, temp-16, temp+14,temp+15,temp+16};
						beatOthers(location);
					}
					// TODO:还有很多情况没有讨论
					if (y[index] == 14) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = {temp-15, temp-16, temp+15,temp+14};
						beatOthers(location);
					}
					if (y[index] == 0) {
						// 这种情况并不会发生
					}
				}
				if (x[index] == 0) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp + 1] = index;
						occupation[temp + 14] = index;
						occupation[temp + 15] = index;
						occupation[temp + 16] = index;
						int[] location = {temp+1, temp+14, temp+15, temp+16};
						beatOthers(location);
					}
					if (y[index] == 0) {
						// 这种情况并不会发生
					}
					if (y[index] == 14) {
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = {temp+14, temp+15};
						beatOthers(location);
					}
				}
				if (x[index] == 14) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp + 1] = index;
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						int[] location = {temp+1, temp-14, temp-15, temp-16};
						beatOthers(location);
					}
					if (y[index] == 0) {
						// 这种情况并不会发生
					}
					if (y[index] == 14) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						int[] location = {temp-15, temp-16};
						beatOthers(location);
					}
				}
			}
			// 向左
			if (direction[index] == 2) {
				int temp = 15 * x[index] + y[index];
				if (x[index] >= 1 && x[index] <= 13) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp + 1] = index;
						occupation[temp - 1] = index;
						occupation[temp + 16] = index;
						occupation[temp + 14] = index;
						int[] location = {temp-14, temp-15, temp-16, temp+1, temp-1, temp+16, temp+14};
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						occupation[temp + 1] = index;
						occupation[temp + 16] = index;
						int[] location = {temp-14, temp-15, temp+1, temp+16};
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 1] = index;
						occupation[temp + 14] = index;
						int[] location = {temp-15, temp-16, temp-1, temp+14};
						beatOthers(location);
					}
				}
				if (x[index] == 0) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp + 1] = index;
						occupation[temp - 1] = index;
						occupation[temp + 16] = index;
						occupation[temp + 14] = index;
						int[] location = {temp+1, temp-1, temp+16, temp+14};
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp + 1] = index;
						occupation[temp + 16] = index;
						int[] location = {temp+1, temp+16};
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 1] = index;
						occupation[temp + 14] = index;
						int[] location = {temp-1,temp+14};
						beatOthers(location);
					}
				}
				if (x[index] == 14) {
					// 这种情况并不存在
				}
			}
			// 向右
			if (direction[index] == 3) {
				int temp = 15 * x[index] + y[index];
				if (x[index] >= 1 && x[index] <= 13) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp - 14] = index;
						occupation[temp - 16] = index;
						occupation[temp + 1] = index;
						occupation[temp - 1] = index;
						occupation[temp + 16] = index;
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = {temp-14, temp-16, temp+1, temp-1, temp+16, temp+15, temp+14};
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 14] = index;
						occupation[temp + 1] = index;
						occupation[temp + 16] = index;
						occupation[temp + 15] = index;
						int[] location = {temp-14, temp+1, temp+16, temp+15};
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 16] = index;
						occupation[temp - 1] = index;
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = {temp-16, temp-1, temp+15, temp+14};
						beatOthers(location);
					}
				}
				if (x[index] == 0) {
//					if (y[index] >= 1 && y[index] <= 13) {
//						occupation[temp + 1] = index;
//						occupation[temp - 1] = index;
//						occupation[temp + 16] = index;
//						occupation[temp + 15] = index;
//						occupation[temp + 14] = index;
//					}
					// 这种情况不会发生
				}
				if (x[index] == 14) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp - 14] = index;
						occupation[temp - 16] = index;
						occupation[temp + 1] = index;
						occupation[temp - 1] = index;
						int[] location = {temp-14, temp-16, temp+1, temp-1};
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 14] = index;
						occupation[temp + 1] = index;
						int[] location = {temp-14, temp+1};
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 16] = index;
						occupation[temp + 1] = index;
						int[] location = {temp-16, temp+1};
						beatOthers(location);
					}
				}
			}
		}
		
		
	}

	public void beatOthers(int[] location){
		for (int i = 0; i < location.length; i++) {
			for (int j = 0; j < 6; j++) {
				int temp = 15 * x[j] + y[j];
				if (location[i] == temp && j != index) {
					x[j] = homeX[j];
					y[j] = homeY[j];
				}
			}
		}
	}

}
