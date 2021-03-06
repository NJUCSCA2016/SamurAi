package team.csca.link.tra;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JPanel;

import team.csca.control.netControl.NetGameControl;
import team.csca.controller.media.Player;
import team.csca.view.extend.Layer;
import team.csca.view.extend.LayerBackground;
import team.csca.view.frame.JFrameMain;
import team.csca.view.gameOver.JPanelGameDraw;
import team.csca.view.gameOver.JPanelGameLose;
import team.csca.view.gameOver.JPanelGameWin;
import team.csca.view.image.ImgBackground;
import team.csca.view.image.ImgNumber;
import team.csca.view.image.ImgRound;
import team.csca.view.image.ImgSamurai;

/**
 * 人机对战
 * 
 * @author Water
 *
 */
public class JPanelNetTra extends JPanel{
	
	private String[] names;
	
	private JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	
	private JPanelGameWin gameWin;
	
	private JPanelGameLose gameLose;
	
	private JPanelGameDraw gameDraw;
	
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
	public int maxRecoverRound;
	/**
	 * 每个武士的恢复周期
	 */
	public int[] recoverRound = new int[6];

	public int maxRound;

	public boolean[][] outSight = new boolean[15][15];
	
	private NetGameControl control;

	public JPanelNetTra(NetGameControl control) {
		
		this.control = control;
		
		this.setVisible(true);
		this.setLayout(null);
		this.setFocusable(true);
		this.requestFocus(true);
		this.setEnabled(true);
		this.requestFocus();
		this.addKeyListener(this.control.getListener());

		setLayer();
		
		
	}
	
	private void setLayer(){
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
				new LayerBackground(5, 0, 190, 240, ImgSamurai.INFO_A0),
				new LayerBackground(5, 230, 190, 240, ImgSamurai.INFO_A1),
				new LayerBackground(5, 460, 190, 240, ImgSamurai.INFO_A2),
				new LayerBackground(1040, 0, 190, 240, ImgSamurai.INFO_B0),
				new LayerBackground(1040, 230, 190, 240, ImgSamurai.INFO_B1),
				new LayerBackground(1040, 460, 190, 240, ImgSamurai.INFO_B2), };
	}

	public void paintComponent(Graphics g) {

		/*
		 * 添加各个组件: 1.地图 2.每个武士老家的占领标志 3.每个武士大本营的旗帜
		 */
		for (int i = 0; i < this.layers.length; i++) {
			layers[i].createWindow(g);
		}
		/**
		 * 防止大本营被占领 此时把所有的大本营重新赋值一遍
		 *  TIP:赋值在统计占领地盘数量之前
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

		/*
		 * 绘制旗帜 
		 * 因为之前绘制了占领地盘 
		 * 占领地盘会覆盖旗帜 
		 * 所以需要重新绘制旗帜
		 */
		for (int i = 7; i < this.layers.length; i++) {
			layers[i].createWindow(g);
		}

		
		
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
		
		/*
		 * 绘制自己方武士的图片
		 * TODO:人机对战的时候需要修改
		 */
		g.drawImage(ImgSamurai.A0_PICTURE[direction[0]], x[0] * 40 + y[0] * 13 + 228, y[0] * (-36) + 600, 50, 50, this);
		g.drawImage(ImgSamurai.A1_PICTURE[direction[1]], x[1] * 40 + y[1] * 13 + 228, y[1] * (-36) + 600, 50, 50, this);
		g.drawImage(ImgSamurai.A2_PICTURE[direction[2]], x[2] * 40 + y[2] * 13 + 228, y[2] * (-36) + 600, 50, 50, this);
		/*
		 * 绘制箭头
		 * 只在自己方行动的时候绘制
		 * TODO：人机对战的时候需要修改
		 */
		if (index < 3) {
			g.drawImage(ImgSamurai.GUIDE_ARROW, x[index] * 40 + y[index] * 13 + 234, y[index] * (-36) + 550, 40, 51, this);
		}

		super.paintComponents(g);

		/**
		 * 计算每个武士占领的格子
		 * TIP:统计占领地盘数量在赋值之后
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
		
		
		printNumber(count[0], g, 120, 85, 18, 25);
		
		printNumber(count[1], g, 120, 315, 18, 25);
		
		printNumber(count[2], g, 120, 545, 18, 25);
		/*
		 * B0的胜利点
		 */
		printNumber(count[3], g, 1157, 85, 18, 25);
		/*
		 * B1的胜利点
		 */
		printNumber(count[4], g, 1157, 315, 18, 25);
		/*
		 * B2的胜利点
		 */
		printNumber(count[5], g, 1157, 545, 18, 25);
		/*
		 * 绘制当前行动的武士的体力值
		 */
		if (index == 0) {
			printNumber(nowPower, g, 120, 118, 18, 25);
		}else
		if (index == 1) {
			printNumber(nowPower, g, 120, 348, 18, 25);
		}else
		if (index == 2) {
			printNumber(nowPower, g, 120, 578, 18, 25);
		}else
		if (index == 3) {
			printNumber(nowPower, g, 1157, 118, 18, 25);
		}else
		if (index == 4) {
			printNumber(nowPower, g, 1157, 348, 18, 25);
		}else
		if (index == 5) {
			printNumber(nowPower, g, 1157, 578, 18, 25);
		}
		
		for (int k = 0; k < 3; k++) {
			printNumber(recoverRound[k], g, 120, 151 + k * 230, 18, 25);
		}
		for (int i = 0; i < 3; i++) {
			printNumber(recoverRound[i + 3], g, 1157, 151 + i * 230, 18, 25);
		}
		/*
		 * 绘制当前进行回合数的图案及数字
		 */
		g.drawImage(ImgRound.NOW_ROUND, 195, 50, 100, 100, this);
		printNumber(round, g, 256, 106, 23, 33);
		/*
		 * 绘制已经进行回合数的图案及数字
		 */
		g.drawImage(ImgRound.REST_ROUND, 300, 46, 100, 100, this);
		printNumber(maxRound - round, g, 361, 106, 23, 33);
		
		if (round == maxRound) {

			frameMain.remove(this);

			judgeContest();
			
		}
		
	}
	public void judgeContest() {
		int score1 = count[0] + count[1] + count[2];
		int score2 = count[3] + count[4] + count[5];
		if (score1 > score2) {

			gameWin = new JPanelGameWin();
			gameWin.add(new JButtonBackToChoose());
			frameMain.remove(this);
			frameMain.setContentPane(gameWin);
			gameWin.requestFocus();
			Player.stopMusic();
			if (Player.MUSiC_PLAYER.isBack_ON()) {
				Player.playMusic("win");
			}
		}
		if (score1 < score2) {

			gameLose = new JPanelGameLose();
			gameLose.add(new JButtonBackToChoose());
			frameMain.remove(this);

			frameMain.setContentPane(gameLose);
			gameLose.requestFocus();
			Player.stopMusic();
			if (Player.MUSiC_PLAYER.isBack_ON()) {
				Player.playMusic("lose");
			}
		}
		if (score1 == score2) {
			gameDraw = new JPanelGameDraw();
			frameMain.remove(this);
			frameMain.setContentPane(gameDraw);
			gameDraw.requestFocus();
			Player.stopMusic();
			if (Player.MUSiC_PLAYER.isBack_ON()) {
				Player.playMusic("draw");
			}
		}
		frameMain.revalidate();
		
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
		if (hasPower() && recoverRound[index] == 0 && (!isHidden(index))) {
			nowPower = nowPower - cost;
			occupy();
		}
		repaint();
	}

	public void attackDown() {
		cost = 4;
		direction[index] = 0;
		if (hasPower() && recoverRound[index] == 0&& (!isHidden(index))) {
			nowPower = nowPower - cost;
			occupy();
		}
		repaint();
	}

	public void attackLeft() {
		cost = 4;
		direction[index] = 2;
		if (hasPower() && recoverRound[index] == 0&& (!isHidden(index))) {
			nowPower = nowPower - cost;
			occupy();
		}
		repaint();
	}

	public void attackRight() {
		cost = 4;
		direction[index] = 3;
		if (hasPower() && recoverRound[index] == 0&& (!isHidden(index))) {
			nowPower = nowPower - cost;
			occupy();
		}
		repaint();
	}

	/**
	 * 现身
	 */
	public boolean showMe() {
		cost = 1;
		if (canShow() && hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			direction[index] -= 4;
			
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("showMe");
			}
		repaint();
		return true;
		}
		repaint();
		return false;	
	}

	/**
	 * 隐身
	 */
	public boolean hideMe() {
		cost = 1;
		if (canHide() && hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			direction[index] += 4;
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("3");
			}
			repaint();
			return true;
		}
		repaint();
		return false;
	}

	/**
	 * 切换武士
	 */
	public void changeCharacter() {
		
		round++;
		this.control.changeCharacter();
		
	}
	
	/**
	 * 向右移动
	 */
	public boolean moveRight() {
		cost = 2;
		if (canMoveTo(1, 0) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			// if (x[index]+1 <=14) {
			nowPower = nowPower - cost;
			x[index] += 1;
			if(isHidden(index)){
				direction[index] = 7;
				if (Player.MUSiC_PLAYER.isGame_ON()) {
					Player.playSound("2");
				}
			}else{
				direction[index] = 3;
				if (Player.MUSiC_PLAYER.isGame_ON()) {
					Player.playSound("1");
				}
			}
			repaint();
			return true;
		}
		return false;

	}

	/**
	 * 向左移动
	 */
	public boolean moveLeft() {
		cost = 2;
		if (canMoveTo(-1, 0) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			// if (x[index]-1 >= 0) {
			nowPower = nowPower - cost;
			x[index] -= 1;
			 if (isHidden(index)) {
				 direction[index] = 6;
				 if (Player.MUSiC_PLAYER.isGame_ON()) {
						Player.playSound("2");
					}
			 }else{
				 direction[index] = 2;
				 if (Player.MUSiC_PLAYER.isGame_ON()) {
						Player.playSound("1");
					}
			 }
			repaint();
			return true;
		}
		return false;
	}

	/**
	 * 向下移动
	 */
	public boolean moveDown() {
		cost = 2;
		if (canMoveTo(0, -1) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			// if (y[index]-1 >= 0) {
			nowPower = nowPower - cost;
			y[index] -= 1;
			 if (isHidden(index)) {
				direction[index] = 4;
				if (Player.MUSiC_PLAYER.isGame_ON()) {
					Player.playSound("2");
				}
			 }else {
				direction[index] = 0;
				if (Player.MUSiC_PLAYER.isGame_ON()) {
					Player.playSound("1");
				}
			}
			repaint();
			return true;
		}
		return false;
	}

	/**
	 * 向上移动
	 */
	public boolean moveUp() {
		cost = 2;
		if (canMoveTo(0, 1) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			y[index] += 1;
			
			if(isHidden(index)){
				if (Player.MUSiC_PLAYER.isGame_ON()) {
					Player.playSound("2");
				}
				direction[index] = 5;
			}else{
				if (Player.MUSiC_PLAYER.isGame_ON()) {
					Player.playSound("1");
				}
				direction[index] = 1;
			}
			repaint();
			return true;
		}
		return false;
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

	public void occupy() {
		if (Player.MUSiC_PLAYER.isGame_ON()) {
			Player.playSound("occupy");
		}
		
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
					direction[j] = 0;
					if (Player.MUSiC_PLAYER.isGame_ON()) {
						Player.playSound("soundeffect0");
					}
					
				}

			}
		}
	}
	/**
	 * Method for panel
	 * @param num
	 * @param g
	 * @param x
	 * @param y
	 * @param w
	 * @param h	
	 */
	private void printNumber(int num, Graphics g, int x, int y, int w, int h) {
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
	 * TODO：在人机对战中需要修改
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


	public void initGame(int[] basicInfo, int[] homeX, int[] homeY, int[] directions, int index , int[] occupation) {
		Arrays.fill(count, 0);
		this.homeX = homeX ;
		this.homeY = homeY;
		this.direction = directions;
		this.index = index;
		this.maxRound = basicInfo[0];
		this.maxRecoverRound = basicInfo[1];
		this.occupation = occupation;
		repaint();
	}

	public void refresh(int[] basicInfo, int[] recoverRound, int[] curX, int[] curY, int[] direction,
			int[] occupation , int[] count) {
		this.round = basicInfo[0];
		this.nowPower = basicInfo[1];
		this.recoverRound = recoverRound;
		this.x = curX;
		this.y = curY;
		this.direction = direction;
		this.occupation = occupation;
		this.count = count;
		repaint();
	}

	public void setPlayersName(String[] names) {
		this.names = names;
	}

	public void changeCurIndex(int curIndex) {
		this.index = curIndex;
	}

	public void gameOver(int[] count) {
		this.count = count;
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
				gameWin = new JPanelGameWin();
				gameWin.addReturnButton(new JButtonBackToChoose());
				frameMain.setContentPane(gameWin);
				gameWin.requestFocus();
				Player.stopMusic();
				if (Player.MUSiC_PLAYER.isBack_ON()) {
					Player.playMusic("win");
				}
			}
			if (score1 < score2) {
				gameLose = new JPanelGameLose();
				gameLose.addReturnButton(new JButtonBackToChoose());
				frameMain.setContentPane(gameLose);
				gameLose.requestFocus();
				Player.stopMusic();
				if (Player.MUSiC_PLAYER.isBack_ON()) {
					Player.playMusic("lose");
				}
			}
			if (score1 == score2) {
				gameDraw = new JPanelGameDraw();
				frameMain.setContentPane(gameDraw);
				gameDraw.requestFocus();
				Player.stopMusic();
				if (Player.MUSiC_PLAYER.isBack_ON()) {
					Player.playMusic("draw");
				}
			}
			frameMain.revalidate();
		}
	}

	private boolean actionAvaliable() {
		return index == control.getIndexOfMyself() && round < maxRound;
	}

	public boolean canTakeAction(int actionCode) {
		if(actionAvaliable()){
			switch (actionCode) {
			case  1 :
				this.attackUp();
				break;
			case 2 :
				this.attackDown();
				break;
			case 3 :
				this.attackLeft();
				break;
			case 4 :
				this.attackRight();
				break;
			case 5 :
				return this.moveUp();
			case 6 :
				return this.moveDown();
			case 7 :
				return this.moveLeft();
			case 8 :
				return this.moveRight();
			case 9 :
				return this.hideMe();
			case 10:
				return this.showMe();
			case 0 :
				this.changeCharacter();
				break;
			default:
				break;
			}
			return true;
		}
		return false;
	}
}
