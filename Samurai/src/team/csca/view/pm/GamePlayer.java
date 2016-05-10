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
	 * 从0到5分别为A0，B0，A1，B1，A2，B2
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
		if (this.getWeaponID() == 0) {
			if (this.getDirection() == 0) {
				// TODO
			}
			if (this.getDirection() == 1) {
				
			}
			if (this.getDirection() == 2) {
				
			}
			if (this.getDirection() == 3) {
				
			}
		}
		if (this.getWeaponID() == 1) {
			if (this.getDirection() == 0) {
				
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
		return index / 2;
	}

	public int getCampID() {
		return index % 2;
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
