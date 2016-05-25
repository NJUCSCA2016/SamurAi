package data;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import control.Control;
import control.PPGameControl;

/**
 * 人机对战
 * 
 * @author Water
 *
 */
public class FieldInfo{

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
	
	private PPGameControl control;
	
//	private PlayerAxe axe;
//	private PlayerSword sword;
//	private PlayerSpear spear;

	public FieldInfo(Control control) {
		this.control = (PPGameControl)control;
		initField();
	}



	public void attackUp() {
		cost = 4;
		direction[index] = 1;
		if (hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			occupy();
		}
	}

	public void attackDown() {
		cost = 4;
		direction[index] = 0;
		if (hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			occupy();
		}
	}

	public void attackLeft() {
		cost = 4;
		direction[index] = 2;
		if (hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			occupy();
		}
	}

	public void attackRight() {
		cost = 4;
		direction[index] = 3;
		if (hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			occupy();
		}
	}

	/**
	 * 现身
	 */
	public void showMe() {
		cost = 1;
		if (canShow() && hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			direction[index] -= 4;
			this.sendFieldInfo(1);
			this.sendFieldInfo(2);
			
		}
	}

	/**
	 * 隐身
	 */
	public void hideMe() {
		cost = 1;
		if (canHide() && hasPower() && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			direction[index] += 4;	
			this.sendFieldInfo(1);
			this.sendFieldInfo(2);
		}
	}

	/**
	 * 切换武士
	 */
	public int changeCharacter() {
		if (round < maxRound) {
			round++;
			calculateIndex();
			nowPower = 7;
			calculateIndex();
			for (int i = 0; i < 6; i++) {
				if (recoverRound[i] > 0) {
					recoverRound[i]--;
				}
				if (recoverRound[index] > 0) {
					nowPower = 0;
				}
			}
			return 0;
		}else{
			return 1;
		}
		
		
	}
	
	public void infoForGameOver(){
		this.control.noticGameOver(this.occupation);
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
			if(isHidden(index)){
				direction[index] = 7;
			}else{
				direction[index] = 3;
			}
			this.sendFieldInfo(1);
			this.sendFieldInfo(2);
		}
//		if (canMoveTo(1, 0) && hasPower() && isHidden(index) && recoverRound[index] == 0) {
//			nowPower = nowPower - cost;
//			x[index] += 1;
//			direction[index] = 7;
//		}
	}

	/**
	 * 向左移动
	 */
	public void moveLeft() {
		cost = 2;
		if (canMoveTo(-1, 0) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			x[index] -= 1;
			if(isHidden(index)){
			// if (x[index]-1 >= 0) {
				direction[index] = 6;
			}else{
				direction[index] = 2;
			}
			this.sendFieldInfo(1);
			this.sendFieldInfo(2);
			// if (isHidden()) {
			// direction[index] = 6;
			// }
		}
//		if (canMoveTo(-1, 0) && hasPower() && isHidden(index) && recoverRound[index] == 0) {
//			// if (x[index]-1 >= 0) {
//			nowPower = nowPower - cost;
//			x[index] -= 1;
//			direction[index] = 6;
//			// if (isHidden()) {
//			// direction[index] = 6;
//			// }
//		}

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
			if(isHidden(index)){
				direction[index] = 4;
			}else{
				direction[index] = 0;
			}
			this.sendFieldInfo(1);
			this.sendFieldInfo(2);
			// if (isHidden()) {
			// direction[index] = 4;
			// 
		}
//		if (canMoveTo(0, -1) && hasPower() && isHidden(index) && recoverRound[index] == 0) {
//			nowPower = nowPower - cost;
//			y[index] -= 1;
//			direction[index] = 4;
//		}

	}

	/**
	 * 向上移动
	 */
	public void moveUp() {
		cost = 2;
		if (canMoveTo(0, 1) && hasPower() && !isHidden(index) && recoverRound[index] == 0) {
			nowPower = nowPower - cost;
			y[index] += 1;
			if(isHidden(index)){
				direction[index] = 5;
			}else{
				direction[index] = 1;
			}
			this.sendFieldInfo(1);
			this.sendFieldInfo(2);
		}
//		if (canMoveTo(0, 1) && hasPower() && isHidden(index) && recoverRound[index] == 0) {
//			nowPower = nowPower - cost;
//			y[index] += 1;
//			direction[index] = 5;
//		}

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
		
		this.sendFieldInfo(1);
		this.sendFieldInfo(2);

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
				}

			}
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
	 * 初始化需要的参数
	 */
	public void initField() {
		index = 0;
		maxPower = 7;
		nowPower = 7;
		round = 0;
		// 从左到右是x,从下到上是y
		// 关于x的系数为正，关于y的系数为负
		x[0] = random.nextInt(7) + 1;
		y[0] = 0;
		x[1] = 14;
		y[1] = random.nextInt(5) + 1;
		x[2] = 0;
		y[2] = random.nextInt(9) + 5;
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
		maxRecoverRound  = 12 * (1 + random.nextInt(4));
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
		
		int[] basicInfo = new int[]{this.maxRound , 0 , this.maxRecoverRound};
		
		this.control.initClientGame(basicInfo , homeX , homeY , this.direction);
		
	}
	
//	private void initAIField(){
//		this.spear = new PlayerSpear();
//		this.sword = new PlayerSword();
//		this.axe = new PlayerAxe();
//		int[] homeX = new int[this.homeX.length];
//		int[] homeY = new int[this.homeY.length];
//		System.arraycopy(this.homeX, 0, homeX, 0, this.homeX.length);
//		System.arraycopy(this.homeY, 0, homeY, 0, this.homeY.length);
//		spear.initAIField(new int[]{maxRound, 3, maxRecoverRound}, homeX, homeY, this);
//		sword.initAIField(new int[]{maxRound, 4, maxRecoverRound}, homeX, homeY, this);
//		axe.initAIField(new int[]{maxRound, 5, maxRecoverRound}, homeX, homeY, this);
//	}
//	
//	

	public void sendFieldInfo(int army){
		int[] recover = new int[this.recoverRound.length];
		System.arraycopy(recoverRound, 0, recover, 0, this.recoverRound.length);
		int[] curX = new int[this.x.length];
		int[] curY = new int[this.y.length];
		//Deep copy
		System.arraycopy(this.x, 0, curX, 0, this.x.length);
		System.arraycopy(this.y, 0, curY, 0, this.y.length);
		//Hide the enemies out of sight.
		int startEne = army == 1 ? 0 : 3 ;
		int endEne = startEne + 3 ; 
		int startAlly = army == 2 ? 0 : 3 ;
		int endAlly = startAlly + 3 ;
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
		for(int i = startEne ; i < endEne ; i ++){
			int XofEne = curX[i];
			int YofEne = curY[i];
			
			if(hidden[i] == -1){
				curX[i] = -1;
				curY[i] = -1;
			}else{
				boolean out = true;
				for(int j = startAlly ; j < endAlly ; j ++){
					int XofAlly = curX[j];
					int YofAlly = curY[j];
					if(Math.abs(XofEne - XofAlly) + Math.abs(YofEne - YofAlly) <= 5){
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
				for(int ai = startAlly ; ai < endAlly ; ai ++){
					if(Math.abs(i - curX[ai]) + Math.abs(j - curY[ai]) <= 5){
						out = false;
						break;
					}
				}
				if(out){
					// 9 means out of sight.
					occupy[15 * i + j] = 9;
				}
			}
		}
		this.control.feedBack(army , this.round, recover, curX, curY, hidden, occupy);
	}
	

}
