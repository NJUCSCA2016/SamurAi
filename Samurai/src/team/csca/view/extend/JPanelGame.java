package team.csca.view.extend;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import team.csca.ai.AI.PlayerAxe;
import team.csca.ai.AI.PlayerSpear;
import team.csca.ai.AI.PlayerSword;
import team.csca.controller.media.Player;
import team.csca.view.frame.JFrameMain;
import team.csca.view.gameOver.JPanelGameDraw;
import team.csca.view.gameOver.JPanelGameLose;
import team.csca.view.gameOver.JPanelGameWin;
import team.csca.view.image.ImgNumber;

public class JPanelGame extends JPanel{
	

	protected JFrameMain frameMain = JFrameMain.J_FRAME_MAIN;
	
	protected JPanelGameWin gameWin;
	
	protected JPanelGameLose gameLose;
	
	protected JPanelGameDraw gameDraw;
	
	/**
	 * 面板上的组件
	 */
	protected static Layer[] layers;

	/**
	 * 武士编号 通过回合号对武士的编号进行索引
	 */
	protected static int index;
	/**
	 * 最大体力值
	 */
	protected static int maxPower;
	/**
	 * 当前体力值
	 */
	public static int nowPower;
	/**
	 * 行动消耗的体力
	 */
	public static int cost;
	/**
	 * 回合数
	 */
	public static int round;
	/**
	 * 方向
	 */
	public static int[] direction = new int[6];

	/**
	 * x,y 代表现在的坐标
	 */
	public static int[] x = new int[6];
	public static int[] y = new int[6];
	/**
	 * 代表大本营的位置
	 */
	public static int[] homeX = new int[6];
	public static int[] homeY = new int[6];
	/**
	 * 代表占领的位置
	 */
	public static int[] occupation = new int[225];
	
	public static int count[] = new int[6];

	protected static Random random = new Random();
	/**
	 * 最大恢复周期
	 */
	public static int maxRecoverRound;
	/**
	 * 每个武士的恢复周期
	 */
	public static int[] recoverRound = new int[6];

	public static int maxRound;

	public static boolean[][] outSight = new boolean[15][15];

	
	protected PlayerAxe axe;
	protected PlayerSword sword;
	protected PlayerSpear spear;

	
	public JPanelGame() {
		this.setVisible(true);
		this.setLayout(null);
		this.setFocusable(true);
		this.requestFocus(true);
		this.setEnabled(true);
		this.requestFocus();
	}
	
	
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
	protected void printNumber(int num, Graphics g, int x, int y, int w, int h) {
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
		
		if (hasPower() && recoverRound[index] == 0 && !isHidden(index)) {
			direction[index] = 1;
			nowPower = nowPower - cost;
			occupy();
		}
		repaint();
	}

	public void attackDown() {
		cost = 4;
		
		if (hasPower() && recoverRound[index] == 0 && !isHidden(index)) {
			direction[index] = 0;
			nowPower = nowPower - cost;
			occupy();
		}
		repaint();
	}

	public void attackLeft() {
		cost = 4;
	
		if (hasPower() && recoverRound[index] == 0 && !isHidden(index)) {
			direction[index] = 2;
			nowPower = nowPower - cost;
			occupy();
		}
		repaint();
	}

	public void attackRight() {
		cost = 4;
		
		if (hasPower() && recoverRound[index] == 0 && !isHidden(index)) {
			direction[index] = 3;
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
			
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("showMe");
			}
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
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("3");
			}
			
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
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("1");
			}
			repaint();
		}
		if (canMoveTo(1, 0) && hasPower() && isHidden(index) && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			x[index] += 1;
			direction[index] = 7;
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("2");
			}
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
			repaint();
		}
		if (canMoveTo(0, -1) && hasPower() && isHidden(index) && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			y[index] -= 1;
			direction[index] = 4;
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("2");
			}
			repaint();
		}

	}

	/**
	 * 向上移动
	 */
	public void moveUp() {
		cost = 2;
		if (canMoveTo(0, 1) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			y[index] += 1;
			direction[index] = 1;
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("1");
			}
			repaint();
		}
		if (canMoveTo(0, 1) && hasPower() && isHidden(index) && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			y[index] += 1;
			direction[index] = 5;
			if (Player.MUSiC_PLAYER.isGame_ON()) {
				Player.playSound("2");
			}
			repaint();
		}

	}
}
