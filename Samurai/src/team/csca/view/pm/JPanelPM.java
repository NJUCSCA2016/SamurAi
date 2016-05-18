package team.csca.view.pm;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;

import team.csca.controller.media.Player;
import team.csca.view.extend.Layer;
import team.csca.view.extend.LayerBackground;
import team.csca.view.extend.PlayMovie;
import team.csca.view.frame.JFrameMain;
import team.csca.view.gameOver.JPanelGameDraw;
import team.csca.view.gameOver.JPanelGameLose;
import team.csca.view.gameOver.JPanelGameWin;
import team.csca.view.gameOver.JPanelRankingList;
import team.csca.view.image.ImgBackground;
import team.csca.view.image.ImgNumber;
import team.csca.view.image.ImgRound;
import team.csca.view.image.ImgSamurai;
import team.csca.view.startgame.JPanelStartGame;

/**
 * 人机对战
 * 
 * @author Water
 *
 */
public class JPanelPM extends JPanel implements KeyListener {
	
	private JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	
//	private JPanelStartGame fatherPanel;
	
	private JPanelGameWin gameWin;
	
	private JPanelGameLose gameLose;
	
	private JPanelGameDraw gameDraw;
	
//	private JPanelRankingList jPanelRankingList;
	/**
	 * 面板上的组件
	 */
	private Layer[] layers;

	/**
	 * 武士编号 通过回合号对武士的编号进行索引
	 */
	public int index;
	/**
	 * 最大体力值
	 */
	public int maxPower;
	/**
	 * 当前体力值
	 */
	public int nowPower;
	/**
	 * 行动消耗的体力
	 */
	public int cost;
	/**
	 * 回合数
	 */
	public int round;
	/**
	 * 方向
	 */
	public int[] direction = new int[6];

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
	
	public int count[] = new int[6];

	Random random = new Random();
	/**
	 * 最大恢复周期
	 */
	public int maxRecoverRound = 12 * (1 + random.nextInt(4));
	/**
	 * 每个武士的恢复周期
	 */
	public int[] recoverRound = new int[6];

	public int maxRound = 12 * (2 + random.nextInt(18));

	Font messageFont = new Font("宋体", Font.PLAIN, 40);

	public boolean[][] outSight = new boolean[15][15];

	public JPanelPM() {
		initField();
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
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				requestFocus(true);
			}
		});

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
		layers = new Layer[] { new LayerBackground(0, 0, 1250, 700, ImgBackground.PM_PANEL),
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
				/*
				 * 信息栏
				 */
				new LayerBackground(1040, 0, 190, 240, ImgSamurai.INFO_A0),
				new LayerBackground(1040, 230, 190, 240, ImgSamurai.INFO_A1),
				new LayerBackground(1040, 460, 190, 240, ImgSamurai.INFO_A2),
				new LayerBackground(5, 0, 190, 240, ImgSamurai.INFO_B0),
				new LayerBackground(5, 230, 190, 240, ImgSamurai.INFO_B1),
				new LayerBackground(5, 460, 190, 240, ImgSamurai.INFO_B2), };
	}

	public void paintComponent(Graphics g) {
		// 计算index的值
		calculateIndex();
		/*
		 * 添加各个组件: 1.地图 2.每个武士老家的占领标志 3.每个武士大本营的旗帜
		 */
		for (int i = 0; i < this.layers.length; i++) {
			layers[i].createWindow(g);
		}
		/**
		 * 防止大本营被占领 此时把所有的大本营重新赋值一遍 TIP:赋值在统计占领地盘数量之前
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
					g.drawImage(ImgSamurai.AI_SHADOW[occupation[temp]], i * 40 + j * 13 + 227, j * (-36) + 622, 57, 36,
							this);
				}
			}
		}
		/**
		 * 获得视野
		 */
		getSight();
		/**
		 * 把不在视野内的地方画上阴影
		 */
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (outSight[i][j]) {
					g.drawImage(ImgSamurai.OUTSIGHT_SHADOW, i * 40 + j * 13 + 227, j * (-36) + 622, 57, 36, this);
				}
			}
		}

		// for (int i = 1; i < 7; i++) {
		// g.drawImage(ImgSamurai.FLAGS[i - 1], x[i - 1] * 40 + y[i - 1] * 13 +
		// 252, y[i - 1] * (-36) + 614, 30, 30, this);
		// }
		/**
		 * 绘制旗帜 因为之前绘制了占领地盘 占领地盘会覆盖旗帜 所以需要重新绘制旗帜
		 */
		for (int i = 7; i < this.layers.length; i++) {
			layers[i].createWindow(g);
		}
		if (index < 3) {
			if (!(isHidden(3) || outSight[x[3]][y[3]])) {
				g.drawImage(ImgSamurai.B0_PICTURE[direction[3]], x[3] * 40 + y[3] * 13 + 228, y[3] * (-36) + 600, 50,
						50, this);
			}
			if (!(isHidden(4) || outSight[x[4]][y[4]])) {
				g.drawImage(ImgSamurai.B1_PICTURE[direction[4]], x[4] * 40 + y[4] * 13 + 228, y[4] * (-36) + 600, 50,
						50, this);
			}
			if (!(isHidden(5) || outSight[x[5]][y[5]])) {
				g.drawImage(ImgSamurai.B2_PICTURE[direction[5]], x[5] * 40 + y[5] * 13 + 228, y[5] * (-36) + 600, 50,
						50, this);
			}
		}
		g.drawImage(ImgSamurai.A0_PICTURE[direction[0]], x[0] * 40 + y[0] * 13 + 228, y[0] * (-36) + 600, 50, 50, this);
		g.drawImage(ImgSamurai.A1_PICTURE[direction[1]], x[1] * 40 + y[1] * 13 + 228, y[1] * (-36) + 600, 50, 50, this);
		g.drawImage(ImgSamurai.A2_PICTURE[direction[2]], x[2] * 40 + y[2] * 13 + 228, y[2] * (-36) + 600, 50, 50, this);
//
//		// FIXME:测试用代码
//		g.setFont(messageFont);
//		g.drawString(Integer.toString(maxRound), 200, 30);
//		g.drawString(Integer.toString(round), 300, 30);
//		g.drawString("Power: " + Integer.toString(nowPower), 460, 30);
//		g.drawString("Index: " + Integer.toString(index), 640, 30);
//		g.drawString("IsHidden: " + isHidden(3), 330, 80);
//		g.drawString("Direction: " + Integer.toString(direction[index]), 660, 80);
		super.paintComponents(g);
		/**
		 * 提示是哪一个武士在行动
		 */
		if (index == 0) {
			g.drawImage(ImgSamurai.A0, 985, 580, 50, 50, this);
		}
		if (index == 1) {
			g.drawImage(ImgSamurai.A1, 985, 580, 50, 50, this);
		}
		if (index == 2) {
			g.drawImage(ImgSamurai.A2, 985, 580, 50, 50, this);
		}
		if (index == 3) {
			g.drawImage(ImgSamurai.B0, 210, 90, 50, 50, this);
		}
		if (index == 4) {
			g.drawImage(ImgSamurai.B1, 210, 90, 50, 50, this);
		}
		if (index == 5) {
			g.drawImage(ImgSamurai.B2, 210, 90, 50, 50, this);
		}

		/**
		 * 计算每个武士占领的格子
		 */

		for (int i = 0; i < count.length; i++) {
			count[i] = 0;
		}
		for (int i = 0; i < occupation.length; i++) {
			for (int j = 0; j < count.length; j++) {
				if (occupation[i] == j) {
					count[j]++;
				}
			}
		}
		// for (int i = 0; i < count.length; i++) {
		// System.out.println(i + " " + count[i]);
		// }
		/*
		 * A0的胜利点
		 */
		printNumber(count[0], g, 1157, 85, 18, 25);
		/*
		 * A1的胜利点
		 */
		printNumber(count[1], g, 1157, 315, 18, 25);
		/*
		 * A2的胜利点
		 */
		printNumber(count[2], g, 1157, 545, 18, 25);
		/*
		 * B0的胜利点
		 */
		printNumber(count[3], g, 120, 85, 18, 25);
		/*
		 * B1的胜利点
		 */
		printNumber(count[4], g, 120, 315, 18, 25);
		/*
		 * B2的胜利点
		 */
		printNumber(count[5], g, 120, 545, 18, 25);
		if (index == 0) {
			printNumber(nowPower, g, 1157, 118, 18, 25);
		}
		if (index == 1) {
			printNumber(nowPower, g, 1157, 348, 18, 25);
		}
		if (index == 2) {
			printNumber(nowPower, g, 1157, 578, 18, 25);
		}
		if (index == 3) {
			printNumber(nowPower, g, 120, 118, 18, 25);
		}
		if (index == 4) {
			printNumber(nowPower, g, 120, 348, 18, 25);
		}
		if (index == 5) {
			printNumber(nowPower, g, 120, 578, 18, 25);
		}
		for (int k = 0; k < 3; k++) {
			printNumber(recoverRound[k], g, 1157, 151 + k * 230, 18, 25);
		}
		for (int i = 0; i < 3; i++) {
			printNumber(recoverRound[i + 3], g, 120, 151 + i * 230, 18, 25);
		}
		
		g.drawImage(ImgRound.NOW_ROUND, 195, 50, 100, 100, this);
		printNumber(round, g, 256, 106, 23, 33);
		
		g.drawImage(ImgRound.REST_ROUND, 300, 46, 100, 100, this);
		printNumber(maxRound - round, g, 361, 106, 23, 33);
		if (round == maxRound) {
//			g.drawString("游戏结束！", 600, 340);
//			fatherPanel = new JPanelStartGame();
//			jPanelRankingList = new JPanelRankingList();
			frameMain.remove(this);
//			frameMain.setContentPane(jPanelRankingList);
//			jPanelRankingList.requestFocus();
			
//			System.out.println(222);
			int score1 = count[0] + count[1] + count[2];
			int score2 = count[3] + count[4] + count[5];
			if (score1 > score2) {
				System.out.println(">");
				gameWin = new JPanelGameWin();
				frameMain.setContentPane(gameWin);
				gameWin.requestFocus();
			}
			if (score1 < score2) {
				System.out.println("<");
				gameLose = new JPanelGameLose();
				frameMain.setContentPane(gameLose);
				gameLose.requestFocus();
			}
			if (score1 == score2) {
				System.out.println("=");
				gameDraw = new JPanelGameDraw();
				frameMain.setContentPane(gameDraw);
				gameDraw.requestFocus();
			}
			frameMain.revalidate();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if (index <= 2 && round < maxRound) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				moveUp();
				break;
			case KeyEvent.VK_DOWN:
				moveDown();
				break;
			case KeyEvent.VK_LEFT:
				moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				moveRight();
				break;
			// 切换人物
			case KeyEvent.VK_Q:
				changeCharacter();
				break;
			// 隐身
			case KeyEvent.VK_R:
				hideMe();
				break;
			// 现行
			case KeyEvent.VK_E:
				showMe();
				break;
			// case KeyEvent.VK_R:
			// attack();
			// break;
			case KeyEvent.VK_W:
				// TODO:向上攻击
				attackUp();
				break;
			case KeyEvent.VK_S:
				// TODO：向下攻击
				attackDown();
				break;
			case KeyEvent.VK_A:
				// TODO:向左攻击
				attackLeft();
				break;
			case KeyEvent.VK_D:
				// TODO:向右攻击
				attackRight();
				break;
			default:
				break;
			}
			// repaint();
		}

	}

	/**
	 * 进攻
	 */
	public void attack() {
		cost = 4;
		// TODO:增加判断条件
		if (hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			occupy();
		}

		repaint();

	}

	public void attackUp() {
		cost = 4;
		direction[index] = 1;
		if (hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			occupy();
		}
		repaint();
	}

	public void attackDown() {
		cost = 4;
		direction[index] = 0;
		if (hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			occupy();
		}
		repaint();
	}

	public void attackLeft() {
		cost = 4;
		direction[index] = 2;
		if (hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			occupy();
		}
		repaint();
	}

	public void attackRight() {
		cost = 4;
		direction[index] = 3;
		if (hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			occupy();
		}
		repaint();
	}

	/**
	 * 现身
	 */
	public void showMe() {
		cost = 1;
		if (canShow() && hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			direction[index] -= 4;
			Player.playSound("showMe");
		}
		repaint();

	}

	/**
	 * 隐身
	 */
	public void hideMe() {
		cost = 1;
		if (canHide() && hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			direction[index] += 4;
			Player.playSound("3");
		}
		repaint();
	}

	/**
	 * 切换武士
	 */
	public void changeCharacter() {
		if (round < maxRound) {
			round++;
		}
		calculateIndex();
		nowPower = 7;
		calculateIndex();
		if (index >= 3) {
			aiTakeAction();
		}
		for (int i = 0; i < 6; i++) {
			if (recoverRound[i] > 0) {
				recoverRound[i]--;
			}
			if (recoverRound[index] > 0) {
				nowPower = 0;
			}
		}
		repaint();
	}

	/**
	 * 向右移动
	 */
	public void moveRight() {
		cost = 2;
		// if (nowPower - cost >= 0 && x[index] + 1 <= 14) {
		// if (nowPower - cost >= 0 && canMoveTo(x[index], y[index], 1, 0))
		// {
		// FIXME:修改判定条件
		// if (canMoveTo(1, 0)) {
		if (canMoveTo(1, 0) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			// if (x[index]+1 <=14) {
			nowPower = nowPower - cost;
			x[index] += 1;
			direction[index] = 3;
			// if (isHidden()) {
			// direction[index] = 7;
			// }
			Player.playSound("1");
			repaint();
		}
		if (canMoveTo(1, 0) && hasPower() && isHidden(index) && recoverRound[index] == 0) {
			// if (x[index]+1 <=14) {
			nowPower = nowPower - cost;
			x[index] += 1;
			direction[index] = 7;
			// if (isHidden()) {
			// direction[index] = 7;
			// }
			Player.playSound("2");
			repaint();
		}

	}

	/**
	 * 向左移动
	 */
	public void moveLeft() {
		cost = 2;
		// if (nowPower - cost >= 0 && x[index] - 1 >= 0) {
		// if (nowPower - cost >= 0 && canMoveTo(x[index], y[index], -1, 0))
		// {
		// FIXME:修改判定条件
		// if (canMoveTo(-1, 0)) {
		if (canMoveTo(-1, 0) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			// if (x[index]-1 >= 0) {
			nowPower = nowPower - cost;
			x[index] -= 1;
			direction[index] = 2;
			// if (isHidden()) {
			// direction[index] = 6;
			// }
			Player.playSound("1");
			repaint();
		}
		if (canMoveTo(-1, 0) && hasPower() && isHidden(index) && recoverRound[index] == 0) {
			// if (x[index]-1 >= 0) {
			nowPower = nowPower - cost;
			x[index] -= 1;
			direction[index] = 6;
			// if (isHidden()) {
			// direction[index] = 6;
			// }
			Player.playSound("2");
			repaint();
		}

	}

	/**
	 * 向下移动
	 */
	public void moveDown() {
		cost = 2;
		// if (nowPower - cost >= 0 && y[index] - 1 >= 0) {
		// if (nowPower - cost >= 0 && canMoveTo(x[index], y[index], 0, -1))
		// {
		// FIXME:修改判定条件
		// if (canMoveTo(0, -1)) {
		if (canMoveTo(0, -1) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			// if (y[index]-1 >= 0) {
			nowPower = nowPower - cost;
			y[index] -= 1;
			direction[index] = 0;
			// if (isHidden()) {
			// direction[index] = 4;
			// }
			Player.playSound("1");
			repaint();
		}
		if (canMoveTo(0, -1) && hasPower() && isHidden(index) && recoverRound[index] == 0) {
			// if (y[index]-1 >= 0) {
			nowPower = nowPower - cost;
			y[index] -= 1;
			direction[index] = 4;
			// if (isHidden()) {
			// direction[index] = 4;
			// }
			Player.playSound("2");
			repaint();
		}

	}

	/**
	 * 向上移动
	 */
	public void moveUp() {
		// TODO:不知道AI是怎么表示出来的
		// FIXME:删除测试代码
		// System.out.println(121);
		cost = 2;
		// if (nowPower - cost >= 0 && y[index] + 1 <= 14) {
		// if (nowPower - cost >= 0 && canMoveTo(x[index], y[index], 0, 1))
		// {
		// FIXME:修改判定条件
		// if (canMoveTo(0, 1)) {
		if (canMoveTo(0, 1) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			// if (y[index]+1 <=14) {
			nowPower = nowPower - cost;
			y[index] += 1;
			direction[index] = 1;
			// if (isHidden()) {
			// direction[index] = 5;
			// }
			Player.playSound("1");
			repaint();
			// System.out.println(x[0]);
		}
		if (canMoveTo(0, 1) && hasPower() && isHidden(index) && recoverRound[index] == 0) {
			// if (y[index]+1 <=14) {
			nowPower = nowPower - cost;
			y[index] += 1;
			direction[index] = 5;
			// if (isHidden()) {
			// direction[index] = 5;
			// }
			Player.playSound("2");
			repaint();
			// System.out.println(x[0]);
		}

	}

	public void aiTakeAction() {
		/**
		 * 上，下，左，右 切换人物 隐身 现身 占领
		 */
		int action = random.nextInt(12);
		int[] aiCost = { 2, 2, 2, 2, 0, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4 };
		cost = aiCost[action];
		if (hasPower() && recoverRound[index] == 0) {
			switch (action) {
			case 0:
				moveUp();
				break;
			case 1:
				moveDown();
				break;
			case 2:
				moveLeft();
				break;
			case 3:
				moveRight();
				break;
			case 4:
				changeCharacter();
				break;
			case 5:
				hideMe();
				break;
			case 6:
				showMe();
				break;
			case 7:
				attackUp();
				break;
			case 8:
				attackUp();
				break;
			case 9:
				attackDown();
				break;
			case 10:
				attackDown();
				break;
			case 11:
				attackLeft();
				break;
			case 12:
				attackLeft();
				break;
			case 13:
				attackRight();
				break;
			case 14:
				attackRight();
				break;
			default:
				break;
			}
		} else {
			changeCharacter();
		}
		if (nowPower >= 0 && index >= 3) {
			aiTakeAction();
		}

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
	public boolean canShow() {
		// 在不是隐身的情况下不可以现身
		if (!isHidden(index)) {
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
	public boolean isHidden(int x) {
		calculateIndex();
		if (direction[x] > 3) {
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
			if (x[index] + dx == x[i] && y[index] + dy == y[i] && !isHidden(index)) {
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

		if (index < 3 && isHidden(index)) {
			int temp = 15 * (x[index] + dx) + y[index] + dy;
			if (!(occupation[temp] >= 0 && occupation[temp] <= 2)) {
				return false;
			}
		}
		if (index >= 3 && isHidden(index)) {
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
		Player.playSound("occupy");
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
					int[] location = { temp - 1, temp - 2, temp - 3, temp - 4 };
					beatOthers(location);
				}
				if (y[index] == 3) {
					occupation[temp - 1] = index;
					occupation[temp - 2] = index;
					occupation[temp - 3] = index;
					int[] location = { temp - 1, temp - 2, temp - 3 };
					beatOthers(location);
				}
				if (y[index] == 2) {
					occupation[temp - 1] = index;
					occupation[temp - 2] = index;
					int[] location = { temp - 1, temp - 2 };
					beatOthers(location);
				}
				if (y[index] == 1) {
					occupation[temp - 1] = index;
					int[] location = { temp - 1 };
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
					int[] location = { temp + 1, temp + 2, temp + 3, temp + 4 };
					beatOthers(location);
				}
				if (y[index] == 11) {
					occupation[temp + 1] = index;
					occupation[temp + 2] = index;
					occupation[temp + 3] = index;
					int[] location = { temp + 1, temp + 2, temp + 3 };
					beatOthers(location);
				}
				if (y[index] == 12) {
					occupation[temp + 1] = index;
					occupation[temp + 2] = index;
					int[] location = { temp + 1, temp + 2 };
					beatOthers(location);
				}
				if (y[index] == 13) {
					occupation[temp + 1] = index;
					int[] location = { temp + 1 };
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
					int[] location = { temp - 15, temp - 30, temp - 45, temp - 60 };
					beatOthers(location);
				}
				if (x[index] == 3) {
					occupation[temp - 15] = index;
					occupation[temp - 30] = index;
					occupation[temp - 45] = index;
					int[] location = { temp - 15, temp - 30, temp - 45 };
					beatOthers(location);
				}
				if (x[index] == 2) {
					occupation[temp - 15] = index;
					occupation[temp - 30] = index;
					int[] location = { temp - 15, temp - 30 };
					beatOthers(location);
				}
				if (x[index] == 1) {
					occupation[temp - 15] = index;
					int[] location = { temp - 15 };
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
					int[] location = { temp + 15, temp + 30, temp + 45, temp + 60 };
					beatOthers(location);
				}
				if (x[index] == 11) {
					occupation[temp + 15] = index;
					occupation[temp + 30] = index;
					occupation[temp + 45] = index;
					int[] location = { temp + 15, temp + 30, temp + 45 };
					beatOthers(location);
				}
				if (x[index] == 12) {
					occupation[temp + 15] = index;
					occupation[temp + 30] = index;
					int[] location = { temp + 15, temp + 30 };
					beatOthers(location);
				}
				if (x[index] == 13) {
					occupation[temp + 15] = index;
					int[] location = { temp + 15 };
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
					int[] location = { temp - 1, temp - 2, temp + 15, temp + 14, temp + 30 };
					beatOthers(location);
				}
				if (y[index] == 1 && x[index] <= 12) {
					occupation[temp - 1] = index;
					occupation[temp + 15] = index;
					occupation[temp + 14] = index;
					occupation[temp + 30] = index;
					int[] location = { temp - 1, temp + 15, temp + 14, temp + 30 };
					beatOthers(location);
				}
				if (y[index] == 0 && x[index] <= 12) {
					occupation[temp + 15] = index;
					occupation[temp + 30] = index;
					int[] location = { temp + 15, temp + 30 };
					beatOthers(location);
				}
				if (y[index] >= 2 && x[index] == 13) {
					occupation[temp - 1] = index;
					occupation[temp - 2] = index;
					occupation[temp + 15] = index;
					occupation[temp + 14] = index;
					int[] location = { temp - 1, temp - 2, temp + 15, temp + 14 };
					beatOthers(location);
				}
				if (y[index] == 1 && x[index] == 13) {
					occupation[temp - 1] = index;
					occupation[temp + 15] = index;
					occupation[temp + 14] = index;
					int[] location = { temp - 1, temp + 15, temp + 14 };
					beatOthers(location);
				}
				if (y[index] == 0 && x[index] == 13) {
					occupation[temp + 15] = index;
					int[] location = { temp + 15 };
					beatOthers(location);
				}
				if (y[index] >= 2 && x[index] == 14) {
					occupation[temp - 1] = index;
					occupation[temp - 2] = index;
					int[] location = { temp - 1, temp - 2 };
					beatOthers(location);
				}
				if (y[index] == 1 && x[index] == 14) {
					occupation[temp - 1] = index;
					int[] location = { temp - 1 };
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
						int[] location = { temp - 15, temp - 14, temp - 30, temp + 1, temp + 2 };
						beatOthers(location);
					}
					if (y[index] == 13) {
						occupation[temp - 15] = index;
						occupation[temp - 14] = index;
						occupation[temp - 30] = index;
						occupation[temp + 1] = index;
						int[] location = { temp - 15, temp - 14, temp - 30, temp + 1 };
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 15] = index;
						occupation[temp - 30] = index;
						int[] location = { temp - 15, temp - 30 };
						beatOthers(location);
					}
				}
				if (x[index] == 1) {
					if (y[index] <= 12) {
						occupation[temp - 15] = index;
						occupation[temp - 14] = index;
						occupation[temp + 1] = index;
						occupation[temp + 2] = index;
						int[] location = { temp - 15, temp - 14, temp + 1, temp + 2 };
						beatOthers(location);
					}
					if (y[index] == 13) {
						occupation[temp - 15] = index;
						occupation[temp - 14] = index;
						occupation[temp + 1] = index;
						int[] location = { temp - 15, temp - 14, temp + 1 };
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 15] = index;
						int[] location = { temp - 15 };
						beatOthers(location);
					}
				}
				if (x[index] == 0) {
					if (y[index] <= 12) {
						occupation[temp + 1] = index;
						occupation[temp + 2] = index;
						int[] location = { temp + 1, temp + 2 };
						beatOthers(location);
					}
					if (y[index] == 13) {
						occupation[temp + 1] = index;
						int[] location = { temp + 1 };
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
						int[] location = { temp - 15, temp - 16, temp - 30, temp - 1, temp - 2 };
						beatOthers(location);
					}
					if (y[index] == 1) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 30] = index;
						occupation[temp - 1] = index;
						int[] location = { temp - 15, temp - 16, temp - 30, temp - 1 };
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 15] = index;
						occupation[temp - 30] = index;
						int[] location = { temp - 15, temp - 30 };
						beatOthers(location);
					}
				}
				if (x[index] == 1) {
					if (y[index] >= 2) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 1] = index;
						occupation[temp - 2] = index;
						int[] location = { temp - 15, temp - 16, temp - 1, temp - 2 };
						beatOthers(location);
					}
					if (y[index] == 1) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 1] = index;
						int[] location = { temp - 15, temp - 16, temp - 1 };
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 15] = index;
						int[] location = { temp - 15 };
						beatOthers(location);
					}
				}
				if (x[index] == 0) {
					if (y[index] >= 2) {
						occupation[temp - 1] = index;
						occupation[temp - 2] = index;
						int[] location = { temp - 1, temp - 2 };
						beatOthers(location);
					}
					if (y[index] == 1) {
						occupation[temp - 1] = index;
						int[] location = { temp - 1 };
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
						int[] location = { temp + 1, temp + 2, temp + 15, temp + 16, temp + 30 };
						beatOthers(location);
					}
					if (y[index] == 13) {
						occupation[temp + 1] = index;
						occupation[temp + 15] = index;
						occupation[temp + 16] = index;
						occupation[temp + 30] = index;
						int[] location = { temp + 1, temp + 15, temp + 16, temp + 30 };
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp + 15] = index;
						occupation[temp + 30] = index;
						int[] location = { temp + 15, temp + 30 };
						beatOthers(location);
					}
				}
				if (x[index] == 13) {
					if (y[index] <= 12) {
						occupation[temp + 1] = index;
						occupation[temp + 2] = index;
						occupation[temp + 15] = index;
						occupation[temp + 16] = index;
						int[] location = { temp + 1, temp + 2, temp + 15, temp + 16 };
						beatOthers(location);
					}
					if (y[index] == 13) {
						occupation[temp + 1] = index;
						occupation[temp + 15] = index;
						occupation[temp + 16] = index;
						int[] location = { temp + 1, temp + 15, temp + 16 };
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp + 15] = index;
						int[] location = { temp + 15 };
						beatOthers(location);
					}
				}
				if (x[index] == 14) {
					if (y[index] <= 12) {
						occupation[temp + 1] = index;
						occupation[temp + 2] = index;
						int[] location = { temp + 1, temp + 2 };
						beatOthers(location);
					}
					if (y[index] == 13) {
						occupation[temp + 1] = index;
						int[] location = { temp + 1 };
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
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 1] = index;
						occupation[temp + 16] = index;
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = { temp - 14, temp - 15, temp - 16, temp - 1, temp + 16, temp + 15, temp + 14 };
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						occupation[temp + 16] = index;
						occupation[temp + 15] = index;
						int[] location = { temp - 14, temp - 15, temp + 16, temp + 15 };
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 1] = index;
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = { temp - 15, temp - 16, temp - 1, temp + 15, temp + 14 };
						beatOthers(location);
					}
				}
				if (x[index] == 0) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp - 1] = index;
						occupation[temp + 16] = index;
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = { temp - 1, temp + 16, temp + 15, temp + 14 };
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp + 16] = index;
						occupation[temp + 15] = index;
						int[] location = { temp + 16, temp + 15 };
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 1] = index;
						occupation[temp + 14] = index;
						occupation[temp + 15] = index;
						int[] location = { temp - 1, temp + 14, temp + 15 };
						beatOthers(location);
					}
				}
				if (x[index] == 14) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 1] = index;
						int[] location = { temp - 14, temp - 15, temp - 16, temp - 1 };
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						int[] location = { temp - 14, temp - 15 };
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
						int[] location = { temp + 1, temp - 14, temp - 15, temp - 16, temp + 14, temp + 15, temp + 16 };
						beatOthers(location);
					}
					// TODO:还有很多情况没有讨论
					if (y[index] == 14) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = { temp - 15, temp - 16, temp + 15, temp + 14 };
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp + 1] = index;
						occupation[temp + 15] = index;
						occupation[temp + 16] = index;
						occupation[temp - 15] = index;
						occupation[temp - 14] = index;
						int[] location = { temp + 1, temp + 15, temp + 16, temp - 15, temp - 14 };
						beatOthers(location);
					}
				}
				if (x[index] == 0) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp + 1] = index;
						occupation[temp + 14] = index;
						occupation[temp + 15] = index;
						occupation[temp + 16] = index;
						int[] location = { temp + 1, temp + 14, temp + 15, temp + 16 };
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp + 1] = index;
						occupation[temp + 15] = index;
						occupation[temp + 16] = index;
						int[] location = { temp + 1, temp + 15, temp + 16 };
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = { temp + 14, temp + 15 };
						beatOthers(location);
					}
				}
				if (x[index] == 14) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp + 1] = index;
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						int[] location = { temp + 1, temp - 14, temp - 15, temp - 16 };
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 15] = index;
						occupation[temp - 14] = index;
						occupation[temp + 1] = index;
						int[] location = { temp + 1, temp - 14, temp - 15 };
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						int[] location = { temp - 15, temp - 16 };
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
						int[] location = { temp - 14, temp - 15, temp - 16, temp + 1, temp - 1, temp + 16, temp + 14 };
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						occupation[temp + 1] = index;
						occupation[temp + 16] = index;
						int[] location = { temp - 14, temp - 15, temp + 1, temp + 16 };
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp - 1] = index;
						occupation[temp + 14] = index;
						int[] location = { temp - 15, temp - 16, temp - 1, temp + 14 };
						beatOthers(location);
					}
				}
				if (x[index] == 0) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp + 1] = index;
						occupation[temp - 1] = index;
						occupation[temp + 16] = index;
						occupation[temp + 14] = index;
						int[] location = { temp + 1, temp - 1, temp + 16, temp + 14 };
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp + 1] = index;
						occupation[temp + 16] = index;
						int[] location = { temp + 1, temp + 16 };
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 1] = index;
						occupation[temp + 14] = index;
						int[] location = { temp - 1, temp + 14 };
						beatOthers(location);
					}
				}
				if (x[index] == 14) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						occupation[temp + 1] = index;
						occupation[temp - 1] = index;
						int[] location = { temp - 14, temp - 15, temp - 16, temp + 1, temp - 1 };
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						// occupation[temp - 16] = index;
						occupation[temp + 1] = index;
						// occupation[temp - 1] = index;
						int[] location = { temp - 14, temp - 15, temp + 1 };
						beatOthers(location);
					}
					if (y[index] == 14) {
						// occupation[temp - 14] = index;
						occupation[temp - 15] = index;
						occupation[temp - 16] = index;
						// occupation[temp + 1] = index;
						occupation[temp - 1] = index;
						int[] location = { temp - 15, temp - 16, temp - 1 };
						beatOthers(location);
					}
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
						int[] location = { temp - 14, temp - 16, temp + 1, temp - 1, temp + 16, temp + 15, temp + 14 };
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 14] = index;
						occupation[temp + 1] = index;
						occupation[temp + 16] = index;
						occupation[temp + 15] = index;
						int[] location = { temp - 14, temp + 1, temp + 16, temp + 15 };
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 16] = index;
						occupation[temp - 1] = index;
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = { temp - 16, temp - 1, temp + 15, temp + 14 };
						beatOthers(location);
					}
				}
				if (x[index] == 0) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp + 1] = index;
						occupation[temp - 1] = index;
						occupation[temp + 16] = index;
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = { temp + 1, temp - 1, temp + 16, temp + 15, temp + 14 };
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp + 1] = index;
//						occupation[temp - 1] = index;
						occupation[temp + 16] = index;
						occupation[temp + 15] = index;
//						occupation[temp + 14] = index;
						int[] location = { temp + 1, temp + 16, temp + 15};
						beatOthers(location);
					}
					if (y[index] == 14) {
//						occupation[temp + 1] = index;
						occupation[temp - 1] = index;
//						occupation[temp + 16] = index;
						occupation[temp + 15] = index;
						occupation[temp + 14] = index;
						int[] location = { temp - 1, temp + 15, temp + 14 };
						beatOthers(location);
					}
				}
				if (x[index] == 14) {
					if (y[index] >= 1 && y[index] <= 13) {
						occupation[temp - 14] = index;
						occupation[temp - 16] = index;
						occupation[temp + 1] = index;
						occupation[temp - 1] = index;
						int[] location = { temp - 14, temp - 16, temp + 1, temp - 1 };
						beatOthers(location);
					}
					if (y[index] == 0) {
						occupation[temp - 14] = index;
						occupation[temp + 1] = index;
						int[] location = { temp - 14, temp + 1 };
						beatOthers(location);
					}
					if (y[index] == 14) {
						occupation[temp - 16] = index;
						occupation[temp + 1] = index;
						int[] location = { temp - 16, temp + 1 };
						beatOthers(location);
					}
				}
			}
		}

	}

	/**
	 * 
	 * @param location
	 *            占领的地盘
	 */
	public void beatOthers(int[] location) {
		for (int i = 0; i < location.length; i++) {
			for (int j = 0; j < 6; j++) {
				int temp = 15 * x[j] + y[j];
				int temp2 = 15 * homeX[j] + homeY[j];
				if (location[i] == temp && j != index && location[i] != temp2) {
					x[j] = homeX[j];
					y[j] = homeY[j];
					recoverRound[j] = maxRecoverRound;
					Player.playSound("soundeffect0");
				}

			}
		}
	}

	public void printNumber(int num, Graphics g, int x, int y, int w, int h) {
		if (num < 10) {
			g.drawImage(ImgNumber.NUMS[num], x, y, w, h, this);
		}
		if (10 <= num && num <= 99) {
			int tens = num / 10;
			int unit = num % 10;
			g.drawImage(ImgNumber.NUMS[tens], x - w, y, w, h, this);
			g.drawImage(ImgNumber.NUMS[unit], x, y, w, h, this);
		}
		if (100 <= num) {
			int hundreds = num / 100;
			int tens = (num - 100 * hundreds) / 10;
			int unit = num % 10;
			g.drawImage(ImgNumber.NUMS[hundreds], x - 2 * w, y, w, h, this);
			g.drawImage(ImgNumber.NUMS[tens], x - w, y, w, h, this);
			g.drawImage(ImgNumber.NUMS[unit], x, y, w, h, this);
		}
	}

	/**
	 * 在视野范围内的才能看到
	 */
	public void getSight() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				outSight[i][j] = true;
			}
		}
		for (int i = 0; i < 3; i++) {
			outSight[x[i]][y[i]] = false;
			for (int m = -5; m <= 5; m++) {
				for (int n = -5; n <= 5; n++) {
					if (x[i] + m <= 14 && x[i] + m >= 0 && y[i] + n <= 14 && y[i] + n >= 0
							&& (Math.abs(m) + Math.abs(n)) <= 5) {
						outSight[x[i] + m][y[i] + n] = false;
					}
				}
			}

		}
		for (int i = 0; i < 6; i++) {
			outSight[homeX[i]][homeY[i]] = false;
		}

	}

	/**
	 * 初始化需要的参数
	 */
	public void initField() {
		index = 0;
		maxPower = 7;
		nowPower = 7;
		round = 0;
		// 从左到右是x,从上到下是y
		// 关于x的系数为正，关于y的系数为负
		// TODO:x[0] = random.nextInt(7) + 1;
		x[0] = 5;
		// TODO:y[0]被改了，原来为0
		y[0] = 5;
		x[1] = 14;
		y[1] = random.nextInt(5) + 1;
		x[2] = 0;
		// TODO:y[2] = random.nextInt(9) + 5;
		y[2] = 14;
		x[3] = random.nextInt(6) + 8;
		y[3] = 0;
		x[4] = random.nextInt(7) + 1;
		y[4] = 14;
		x[5] = random.nextInt(6) + 7;
		y[5] = 14;
		for (int i = 0; i < 6; i++) {
			homeX[i] = x[i];
			homeY[i] = y[i];
		}
		for (int i = 0; i < direction.length; i++) {
			direction[i] = 0;
		}
		maxRecoverRound = 12 * (1 + random.nextInt(4));
		for (int i = 0; i < recoverRound.length; i++) {
			recoverRound[i] = 0;
		}

		maxRound = 12 * (6 + random.nextInt(14));
		// x
		for (int i = 0; i < 15; i++) {
			// y
			for (int j = 0; j < 15; j++) {
				outSight[i][j] = true;
			}
		}

		for (int i = 0; i < 6; i++) {
			outSight[homeX[i]][homeY[i]] = false;
		}
	}
}
