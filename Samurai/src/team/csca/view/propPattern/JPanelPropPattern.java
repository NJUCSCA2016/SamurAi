package team.csca.view.propPattern;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;

import team.csca.ai.AI.PlayerAxe;
import team.csca.ai.AI.PlayerSpear;
import team.csca.ai.AI.PlayerSword;
import team.csca.controller.media.Player;
import team.csca.view.extend.JPanelGame;
import team.csca.view.extend.Layer;
import team.csca.view.extend.LayerBackground;
import team.csca.view.frame.JFrameMain;
import team.csca.view.gameOver.JButtonGameBackToMain;
import team.csca.view.gameOver.JPanelGameDraw;
import team.csca.view.gameOver.JPanelGameLose;
import team.csca.view.gameOver.JPanelGameWin;
import team.csca.view.image.ImgBackground;
import team.csca.view.image.ImgNumber;
import team.csca.view.image.ImgProps;
import team.csca.view.image.ImgRound;
import team.csca.view.image.ImgSamurai;
/**
 * 道具模式
 * @author Water
 *
 */
public class JPanelPropPattern extends JPanelGame implements KeyListener{
	
	
	/**
	 * 视野
	 */
	public int[] sight = new int[6];

	/**
	 * 道具坐标
	 * TODO:可能需要修改数量
	 */
	public int[] propX = new int[3];
	public int[] propY = new int[3];
	/**
	 * 道具图片
	 */
	public Image[] props = new Image[7];
	/**
	 * 道具数量
	 * TODO:可能需要修改数量
	 */
	public int[] propNum = new int[3];
	
	/**
	 * 每个武士的额外生命
	 */
	public int[] life = new int[6];
	/**
	 * 最大回合数
	 */


	private PlayerAxe axe;
	private PlayerSword sword;
	private PlayerSpear spear;

	public JPanelPropPattern() {
		super();
		initField();
		initAIField();
		this.addKeyListener(this);
		this.add(new JButtonBack(this));
		this.add(new JButtonExit(this));
		this.add(new JButtonSetting(this));
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 获得焦点
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
		
		
		// 计算index的值
		calculateIndex();
		/*
		 * 添加各个组件: 
		 * 1.地图 
		 * 2.每个武士老家的占领标志 
		 * 3.每个武士大本营的旗帜
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
		/*
		 * 绘制对方的武士
		 * TODO:人机对战的时候这部分需要修改
		 */
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
		/*
		 * 绘制自己方武士的图片
		 * TODO:人机对战的时候需要修改
		 */
		g.drawImage(ImgSamurai.A0_PICTURE[direction[0]], x[0] * 40 + y[0] * 13 + 228, y[0] * (-36) + 600, 50, 50, this);
		g.drawImage(ImgSamurai.A1_PICTURE[direction[1]], x[1] * 40 + y[1] * 13 + 228, y[1] * (-36) + 600, 50, 50, this);
		g.drawImage(ImgSamurai.A2_PICTURE[direction[2]], x[2] * 40 + y[2] * 13 + 228, y[2] * (-36) + 600, 50, 50, this);
		
		refreshProps();
		/*
		 * 绘制道具
		 * TODO：未完
		 */
		if (!outSight[propX[0]][propY[0]] && propNum[0] > 0) {
			g.drawImage(ImgProps.ANOTHER_LIFE, propX[0] * 40 + propY[0] * 13 + 228, propY[0] * (-36) + 600, 42, 48, this);
		}
		if (!outSight[propX[1]][propY[1]] && propNum[1] > 0) {
			g.drawImage(ImgProps.ADD_NOWPOWER, propX[1] * 40 + propY[1] * 13 + 228, propY[1] * (-36) + 600, 42, 48, this);
		}
		if (!outSight[propX[2]][propY[2]] && propNum[2] > 0) {
			g.drawImage(ImgProps.ADD_SIGHT, propX[2] * 40 + propY[2] * 13 + 228, propY[2] * (-36) + 600, 42, 48, this);
		}
		/*
		 * 状态栏
		 * 只有长期持有的状态才会绘制到状态栏中
		 */
		// 生命
		for (int i = 0; i < 3; i++) {
			if (life[i] > 0) {
				g.drawImage(ImgProps.ANOTHER_LIFE, 10, 230 * i + 187, 42, 48, this);
			}
		}
		for (int i = 3; i < 6; i++) {
			if (life[i] > 0) {
				g.drawImage(ImgProps.ANOTHER_LIFE, 1045, 230 * i + 187, 42, 48, this);
			}
		}
		// 视野
		for (int i = 0; i < 3; i++) {
			if (sight[i] > 5) {
				g.drawImage(ImgProps.ADD_SIGHT, 54, 230 * i + 187, 42, 48, this);
			}
		}
		for (int i = 3; i < 6; i++) {
			if (sight[i] > 5) {
				g.drawImage(ImgProps.ADD_SIGHT, 1089, 230 * i + 187, 42, 48, this);
			}
		}
		/*
		 * 绘制箭头
		 * 只在自己方行动的时候绘制
		 */
		if (index < 3) {
			g.drawImage(ImgSamurai.GUIDE_ARROW, x[index] * 40 + y[index] * 13 + 234, y[index] * (-36) + 550, 40, 51, this);
		}

		super.paintComponents(g);
//		/**
//		 * 提示是哪一个武士在行动
//		 */
//		if (index == 0) {
//			g.drawImage(ImgSamurai.A0, 985, 580, 50, 50, this);
//		}
//		if (index == 1) {
//			g.drawImage(ImgSamurai.A1, 985, 580, 50, 50, this);
//		}
//		if (index == 2) {
//			g.drawImage(ImgSamurai.A2, 985, 580, 50, 50, this);
//		}
//		if (index == 3) {
//			g.drawImage(ImgSamurai.B0, 210, 90, 50, 50, this);
//		}
//		if (index == 4) {
//			g.drawImage(ImgSamurai.B1, 210, 90, 50, 50, this);
//		}
//		if (index == 5) {
//			g.drawImage(ImgSamurai.B2, 210, 90, 50, 50, this);
//		}

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
		/*
		 * A0的胜利点
		 */
		printNumber(count[0], g, 120, 85, 18, 25);
		/*
		 * A1的胜利点
		 */
		printNumber(count[1], g, 120, 315, 18, 25);
		/*
		 * A2的胜利点
		 */
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
		}
		if (index == 1) {
			printNumber(nowPower, g, 120, 348, 18, 25);
		}
		if (index == 2) {
			printNumber(nowPower, g, 120, 578, 18, 25);
		}
		if (index == 3) {
			printNumber(nowPower, g, 1157, 118, 18, 25);
		}
		if (index == 4) {
			printNumber(nowPower, g, 1157, 348, 18, 25);
		}
		if (index == 5) {
			printNumber(nowPower, g, 1157, 578, 18, 25);
		}
		/*
		 * 绘制所有武士的恢复回合
		 */
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
		g.drawImage(ImgRound.REST_ROUND, 195, 150, 100, 100, this);
		printNumber(maxRound - round, g, 256, 208, 23, 33);
		if (round == maxRound) {
			frameMain.remove(this);
			judgeContest();
		}
	}
	
	public void judgeContest(){
		int score1 = count[0] + count[1] + count[2];
		int score2 = count[3] + count[4] + count[5];

		if (score1 > score2) {
			gameWin = new JPanelGameWin();
			gameWin.addReturnButton(new JButtonGameBackToMain(gameWin));
			frameMain.setContentPane(gameWin);
			gameWin.requestFocus();
			Player.stopMusic();
			if (Player.MUSiC_PLAYER.isBack_ON()) {
				Player.playMusic("win");
			}
		}
		if (score1 < score2) {
			gameLose = new JPanelGameLose();
			gameLose.addReturnButton(new JButtonGameBackToMain(gameLose));
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
			
			if(recoverRound[index] == 0){
				switch (index) {
				case 3:
					sendFieldInfo(spear);
					this.spear.play();
					break;
				case 4:
					sendFieldInfo(sword);
					this.sword.play();
					break;
				case 5:
					sendFieldInfo(axe);
					this.axe.play();
					break;
				default:
					break;
				}
			}
			
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
		if(index >= 3){
			changeCharacter();
		}
		repaint();
	}

	/**
	 * 向右移动
	 */
	public void moveRight() {
		cost = 2;
		if (canMoveTo(1, 0) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			// if (x[index]+1 <=14) {
			nowPower = nowPower - cost;
			x[index] += 1;
			direction[index] = 3;
			// if (isHidden()) {
			// direction[index] = 7;
			// }
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("1");
			}
			getProps();
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
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("2");
			}
			getProps();
			repaint();
		}

	}

	/**
	 * 向左移动
	 */
	public void moveLeft() {
		cost = 2;
		if (canMoveTo(-1, 0) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			// if (x[index]-1 >= 0) {
			nowPower = nowPower - cost;
			x[index] -= 1;
			direction[index] = 2;
			// if (isHidden()) {
			// direction[index] = 6;
			// }
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("1");
			}
			getProps();
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
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("2");
			}
			getProps();
			repaint();
		}

	}

	/**
	 * 向下移动
	 */
	public void moveDown() {
		cost = 2;
		if (canMoveTo(0, -1) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			// if (y[index]-1 >= 0) {
			nowPower = nowPower - cost;
			y[index] -= 1;
			direction[index] = 0;
			// if (isHidden()) {
			// direction[index] = 4;
			// }
		
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("1");
			}
			getProps();
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
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("2");
			}
			getProps();
			repaint();
		}

	}

	/**
	 * 向上移动
	 */
	public void moveUp() {
		cost = 2;
		if (canMoveTo(0, 1) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			// if (y[index]+1 <=14) {
			nowPower = nowPower - cost;
			y[index] += 1;
			direction[index] = 1;
			// if (isHidden()) {
			// direction[index] = 5;
			// }
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("1");
			}
			getProps();
			repaint();
		}
		if (canMoveTo(0, 1) && hasPower() && isHidden(index) && recoverRound[index] == 0) {
			// if (y[index]+1 <=14) {
			nowPower = nowPower - cost;
			y[index] += 1;
			direction[index] = 5;
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("2");
			}
			getProps();
			repaint();
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

	/**
	 * 得到道具
	 */
	public void getProps() {
		// 加一条命的道具
		getAnotherLife();
		// 增加行动力的道具
		getPower();
		// 增加视野
		addSight();
		
	}
	/**
	 * 加一条命
	 */
	public void getAnotherLife(){
		if (x[index] == propX[0] && y[index] == propY[0] && propNum[0] > 0) {
			life[index] += 1;
			propNum[0] -= 1;
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("hp");
			}
		}
	}
	/**
	 * 增加体力
	 * 当前回合体力增加5
	 */
	public void getPower(){
		if (x[index] == propX[1] && y[index] == propY[1] && propNum[1] > 0) {
			nowPower += 5;
			propNum[1] -= 1;
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("nowpower");
			}
		}
	}
	
	public void addSight(){
		if (x[index] == propX[2] && y[index] == propY[2] && propNum[2] > 0) {
			sight[index] ++;
			propNum[2] -= 1;
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("sight");
			}
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
		x[0] = random.nextInt(6) + 1; // 1 - 6
		y[0] = 0;                     // 0 - 0
		x[1] = 0;                     // 0 - 0
		y[1] = random.nextInt(5) + 1; // 1 - 5
		x[2] = 0;                     // 0 - 0
		y[2] = random.nextInt(6) + 8; // 8 - 13
		
		x[3] = random.nextInt(6) + 8; // 8 - 13
		y[3] = 14;                    // 0 - 0
		x[4] = 14;                    // 14- 14
		y[4] = random.nextInt(5) + 9; // 9 - 13
		x[5] = 14;                    // 14- 14
		y[5] = random.nextInt(6) + 1; // 14- 14
		// TODO:加其他道具的坐标
		propX[0] = random.nextInt(3) + 6; // 6 - 8
		propY[0] = random.nextInt(3) + 6; // 6 - 8
		propX[1] = random.nextInt(2) + 3; // 3 - 4
		propY[1] = random.nextInt(2) + 11;// 11- 12
		propX[2] = random.nextInt(2) + 10;// 10- 11
		propY[2] = random.nextInt(2) + 2; // 6 - 9
		
		
		props[0] = ImgProps.ANOTHER_LIFE;
		props[1] = ImgProps.ADD_NOWPOWER;
		props[2] = ImgProps.ADD_SIGHT;
		
		for (int i = 0; i < propNum.length; i++) {
			propNum[i] = 1;
		}
		for (int i = 0; i < 6; i++) {
			homeX[i] = x[i];
			homeY[i] = y[i];
		}
		for (int i = 0; i < direction.length; i++) {
			direction[i] = 0;
		}
		maxRecoverRound = 12 * (1 + random.nextInt(4));
		initRecoverRound();
		
		for (int i = 0; i < 6; i++) {
			life[i] = 0;
		}

		maxRound = 12 * (6 + random.nextInt(14));
		maxRecoverRound = 12 * (1 + random.nextInt(4));
		for (int i = 0; i < 6; i++) {
			life[i] = 0;
		}
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
		for (int i = 0; i < 6; i++) {
			sight[i] = 5;
		}
	}
	protected void initRecoverRound() {
		for (int i = 0; i < recoverRound.length; i++) {
			recoverRound[i] = 0;
		}
	}

	/**
	 * 刷新道具
	 */
	public void refreshProps(){
		if (propNum[0] == 0 && propNum[1] == 0 && propNum[2] == 0) {
			if (round == 36 || round == 72 || round == 108 || round == 144 || round == 180) {
				randomProp0();
				while(!canRefreshProp0()){
					randomProp0();
				}
				propNum[0] ++;
				
				randomProp1();
				while(!canRefreshProp1()){
					randomProp1();
				}
				propNum[1] ++;
				
				randomProp2();
				while(!canRefreshProp2()){
					randomProp2();
				}
				propNum[2] ++;
			}
		}
	}
	/**
	 * 是否可以刷新道具0
	 * @return
	 */
	public boolean canRefreshProp0(){
		for (int i = 0; i < 6; i++) {	
			if (propX[0] == x[i] && propY[0] == y[i]) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 随机生成道具0的坐标
	 */
	public void randomProp0(){
		propX[0] = random.nextInt(3) + 6;
		propY[0] = random.nextInt(3) + 6;
	}
	
	/**
	 * 是否可以刷新道具1
	 * @return
	 */
	public boolean canRefreshProp1(){
		for (int i = 0; i < 6; i++) {	
			if (propX[1] == x[i] && propY[1] == y[i]) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 随机生成道具1的坐标
	 */
	public void randomProp1(){
		propX[1] = random.nextInt(2) + 3;
		propY[1] = random.nextInt(2) + 11;
	}
	
	/**
	 * 是否可以刷新道具1
	 * @return
	 */
	public boolean canRefreshProp2(){
		for (int i = 0; i < 6; i++) {	
			if (propX[2] == x[i] && propY[2] == y[i]) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 随机生成道具1的坐标
	 */
	public void randomProp2(){
		propX[2] = random.nextInt(2) + 10;
		propY[2] = random.nextInt(2) + 2;
	}
	
	/**
	 * This method is to provide the info of current field.
	 * @param player
	 */
	private void sendFieldInfo(team.csca.ai.AI.Player player){
		
		int[] basicInfo = new int[]{this.round , this.recoverRound[index]};
		int[] curX = new int[this.x.length];
		int[] curY = new int[this.y.length];
		//Deep copy
		System.arraycopy(this.x, 0, curX, 0, this.x.length);
		System.arraycopy(this.y, 0, curY, 0, this.y.length);
		//Hide information . Provide the limited info.
		int[] hidden = new int[6];
		for(int i = 0 ; i < direction.length ; i ++ ){
			if(curX[i] == -1){
				hidden[i] = 1;
			}else{
				if(direction[i] > 3){
					hidden[i] = -1;
				}else{
					hidden[i] = 0;
				}
			}
		}		
		//Hide the AI out of eyes
		for(int i = 0 ; i < 3 ; i ++){
			int XofAI = curX[i];
			int YofAI = curY[i];
			if(hidden[i] == -1){
				curX[i] = -1;
				curY[i] = -1;
			}else{
				boolean out = true;
				for(int j = 3 ; j < 6 ; j ++) {
					int XofAlly = curX[j];
					int YofAlly = curY[j];
					if(Math.abs(YofAI - YofAlly) + Math.abs(XofAlly - XofAI) <= 5){
						out = false;
						break;
					}
				}
				if(out){
					hidden[i] = -1;
					curX[i] = -1;
					curY[i] = -1;
				}
			}
		}
		
		//Occupy info . Provide the info in sight
		int[] occupy = new int[this.occupation.length];
		System.arraycopy(occupation, 0, occupy, 0, occupation.length);
		for(int i = 0 ; i < 15 ; i ++){
			for(int j = 0 ; j < 15 ; j++){
				boolean out = true;
				for(int ai = 3 ; ai < 6 ; ai ++){
					if(Math.abs(i - curX[ai]) + Math.abs(j - curY[ai]) <= 5){
						out = false;
						break;
					}
				}
				if(out){
					occupy[15 * i + j] = 9;
				}
			}
		}
		
		player.sendInfo(basicInfo, curX, curY, hidden, occupy);
	}
	
	private void initAIField(){
		this.spear = new PlayerSpear();
		this.sword = new PlayerSword();
		this.axe = new PlayerAxe();
		int[] homeX = new int[JPanelGame.homeX.length];
		int[] homeY = new int[this.homeY.length];
		System.arraycopy(this.homeX, 0, homeX, 0, this.homeX.length);
		System.arraycopy(this.homeY, 0, homeY, 0, this.homeY.length);
		spear.initAIField(new int[]{maxRound, 3, maxRecoverRound}, homeX, homeY, this);
		sword.initAIField(new int[]{maxRound, 4, maxRecoverRound}, homeX, homeY, this);
		axe.initAIField(new int[]{maxRound, 5, maxRecoverRound}, homeX, homeY, this);
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
