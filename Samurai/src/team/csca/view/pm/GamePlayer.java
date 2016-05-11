package team.csca.view.pm;

import java.awt.Point;

public class GamePlayer {
	/**
	 * x坐标
	 */
	private int x;
	/**
	 * y坐标
	 */
	private int y;
	/**
	 * 最大体力值
	 */
	private final int maxPower = 7;
	/**
	 * 目前体力值
	 */
	private int nowPower;
	/**
	 * 最大恢复周期
	 */
	private int maxRecoverRound;
	/**
	 * 剩余恢复周期
	 */
	private int recoverRound;
	/**
	 * 从0到5分别为A0，A1，A2，B0，B1，B2
	 */
	private int index;
	/**
	 * 武器的编号,分别为0，1，2
	 * 0代表长矛
	 * 1代表剑
	 * 2代表战斧
	 */
	private int weaponID;
	/**
	 * 阵营的编号，分别为0，1
	 * 0代表A
	 * 1代表B
	 */
	private int campID;
	/**
	 * 方向编号，分别为0，1，2，3
	 * 0代表向下（正面）
	 * 1代表向上（背面）
	 * 2代表向左（左面）
	 * 3代表向右（右面）
	 */
	private int direction;
	/**
	 * 获得视野
	 * @param x
	 * @param y
	 * @return
	 */
	public Point[] getSight(int x, int y, int direction) {
		// 长矛
		if (this.getWeaponID() == 0) {
			// 向下
			if (this.getDirection() == 0) {
				if (this.y <= 10) {
					Point[] sight = new Point[4];
					sight[0] = new Point(x, y + 1);
					sight[1] = new Point(x, y + 2);
					sight[2] = new Point(x, y + 3);
					sight[3] = new Point(x, y + 4);
					return sight;
				}
				if (this.y == 11) {
					Point[] sight = new Point[3];
					sight[0] = new Point(x, y + 1);
					sight[1] = new Point(x, y + 2);
					sight[2] = new Point(x, y + 3);
					return sight;
				}
				if (this.y == 12) {
					Point[] sight = new Point[2];
					sight[0] = new Point(x, y + 1);
					sight[1] = new Point(x, y + 2);
					return sight;
				}
				if (this.y == 13) {
					Point[] sight = new Point[1];
					sight[0] = new Point(x, y + 1);
					return sight;
				}
				if (this.y >= 14) {
					return null;
				}
			}
			// 向上
			if (this.getDirection() == 1) {
				if (this.y >= 4) {
					Point[] sight = new Point[4];
					sight[0] = new Point(x, y - 1);
					sight[1] = new Point(x, y - 2);
					sight[2] = new Point(x, y - 3);
					sight[3] = new Point(x, y - 4);
					return sight;
				}
				if (this.y == 3) {
					Point[] sight = new Point[3];
					sight[0] = new Point(x, y - 1);
					sight[1] = new Point(x, y - 2);
					sight[2] = new Point(x, y - 3);
					return sight;
				}
				if (this.y == 2) {
					Point[] sight = new Point[2];
					sight[0] = new Point(x, y - 1);
					sight[1] = new Point(x, y - 2);
					return sight;
				}
				if (this.y == 1) {
					Point[] sight = new Point[1];
					sight[0] = new Point(x, y - 1);
					return sight;
				}
				if (this.y == 0) {
					return null;
				}
			}
			// 向左
			if (this.getDirection() == 2) {
				if (this.x >= 4) {
					Point[] sight = new Point[4];
					sight[0] = new Point(x - 1, y);
					sight[1] = new Point(x - 2, y);
					sight[2] = new Point(x - 3, y);
					sight[3] = new Point(x - 4, y);
					return sight;
				}
				if (this.x == 3) {
					Point[] sight = new Point[3];
					sight[0] = new Point(x - 1, y);
					sight[1] = new Point(x - 2, y);
					sight[2] = new Point(x - 3, y);
					return sight;
				}
				if (this.x == 2) {
					Point[] sight = new Point[2];
					sight[0] = new Point(x - 1, y);
					sight[1] = new Point(x - 2, y);
					return sight;
				}
				if (this.x == 1) {
					Point[] sight = new Point[1];
					sight[0] = new Point(x - 1, y);
					return sight;
				}
				if (this.x == 0) {
					return null;
				}
			}
			// 向右
			if (this.getDirection() == 3) {
				if (this.x <= 10) {
					Point[] sight = new Point[4];
					sight[0] = new Point(x + 1, y);
					sight[1] = new Point(x + 2, y);
					sight[2] = new Point(x + 3, y);
					sight[3] = new Point(x + 4, y);
					return sight;
				}
				if (this.x == 11) {
					Point[] sight = new Point[3];
					sight[0] = new Point(x + 1, y);
					sight[1] = new Point(x + 2, y);
					sight[2] = new Point(x + 3, y);
					return sight;
				}
				if (this.x == 12) {
					Point[] sight = new Point[2];
					sight[0] = new Point(x + 1, y);
					sight[1] = new Point(x + 2, y);
					return sight;
				}
				if (this.x == 13) {
					Point[] sight = new Point[1];
					sight[0] = new Point(x + 1, y);
					return sight;
				}
				if (this.x == 14) {
					return null;
				}
			}
		}
		// 剑
		if (this.getWeaponID() == 1) {
			// 向下
			if (this.getDirection() == 0) {
				if (this.x <= 12 && this.y <= 12) {
					Point[] sight = new Point[5];
					sight[0] = new Point(x, y + 1);
					sight[1] = new Point(x, y + 2);
					sight[2] = new Point(x + 1, y);
					sight[3] = new Point(x + 2, y);
					sight[4] = new Point(x + 1, y + 1);
					return sight;
				}
				if (this.x == 13 && this.y <= 12) {
					Point[] sight = new Point[4];
					sight[0] = new Point(x, y + 1);
					sight[1] = new Point(x + 1, y);
					sight[2] = new Point(x + 2, y);
					sight[3] = new Point(x + 1, y + 1);
					return sight;
				}
				if (this.x == 14 && this.y <= 12) {
					Point[] sight = new Point[2];
					sight[0] = new Point(x + 1, y);
					sight[1] = new Point(x + 2, y);
					return sight;
				}
				// TODO：还有y方向上的情况
				if (this.x <= 12 && this.y == 13) {
					
				}
			}
			if (this.getDirection() == 1) {
				
			}
			if (this.getDirection() == 2) {
				
			}
			if (this.getDirection() == 3) {
				
			}
		}
		if (this.getWeaponID() == 2) {
			if (this.getDirection() == 0) {
				
			}
			if (this.getDirection() == 1) {
				
			}
			if (this.getDirection() == 2) {
				
			}
			if (this.getDirection() == 3) {
				
			}
		}
		
		return null;
	}
	//------------------------以下是get和set方法------------------------
	public void moveUp() {
		this.y += 1;
	}
	
	public void moveDown() {
		this.y -= 1;
	}
	
	public void moveLeft() {
		this.x -= 1;
	}
	
	public void moveRight() {
		this.x += 1;
	}
	
	public int getWeaponID() {
		return index % 3;
	}

	public int getCampID() {
		return index / 3;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getNowPower() {
		return nowPower;
	}
	
	public void setNowPower(int nowPower) {
		this.nowPower = nowPower;
	}
	
	public int getMaxRecoverRound() {
		return maxRecoverRound;
	}
	
	public void setMaxRecoverRound(int maxRecoverRound) {
		this.maxRecoverRound = maxRecoverRound;
	}
	
	public int getRecoverRound() {
		return recoverRound;
	}
	
	public void setRecoverRound(int recoverRound) {
		this.recoverRound = recoverRound;
	}
	
	public int getMaxPower() {
		return maxPower;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
}
