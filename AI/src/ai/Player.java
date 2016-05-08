/**
 * Date : Mar 27, 2016 7:27:11 PM
 */
package ai;

import control.CenterControl;
import data.GameInfo;
import data.SamuraiInfo;

/**
 * 这个类用于规范Player 的一些基本操作。
 * 具体的每个高级操作落实到每个实体中。
 * 
 * AI 是不知道每次对方AI的行为的，只能知道它的位置，当然，是显示的情况下。
 * 
 *@warning :  每个AI即使在操作之后并不能更新自身的视野。
 * 
 * @author Alone
 * Written by YYM
 * 
 * 
 * @see : 游戏的目标是为了占领更多的领域。而不是杀人。
 * 
 * 
 * 
 */
public abstract class Player {
	
	
	/**
	 *  假设有三个在上场 ， 你只能杀一个。那么可能杀了以后你就死了。就逃跑吧
	 */
	public final static int RUN_AWAY = 0;
	/**
	 * 可以杀一个人并且应该为安全的
	 */
	public final static int CAN_KILL_ONE = 1;
	/**
	 * 双杀。不需要考虑会不会死。死俩值了
	 */
	public final static int DOUBLE_KILL = 2;
	/**
	 * 嘿嘿
	 */
	public final static int TRIPLE_KILL = 3;
	/**
	 * 有一个人但是打不到
	 */
	public final static int ONE_OUT_REACH = 4;
	/**
	 * 两个人并且打不到
	 */
	public final static int TWO_OUT_REACH = 5;
	/**
	 * 该片区域全部被占领了，并且没有人在。
	 */
	public final static int OCCUPY_ALL = 6;
	/**
	 * 没人并且部分占领了。
	 */
	public final static int NOBODY_PART = 7;
	
	
	
	
	/**
	 *  每个AI肯定了解游戏信息和自身信息。
	 */
	protected GameInfo gameInfo;
	protected SamuraiInfo samuraiInfo;
	/**
	 *  获取游戏控制器
	 */
	protected  CenterControl centerControl ;
	/**
	 * 行动力消耗
	 */
	public final int[] cost = {0, 4, 4, 4, 4, 2, 2, 2, 2, 1, 1};
	/**
	 * 
	 * 当前已消耗行动力
	 * 
	 */
	protected int current_Cost = 0;
	/**
	 * 最大行动力
	 */
	public final int maxPower = 7;
	/**
	 * 哈密尔顿距离最大值
	 */
	protected final static int MAX_PATH = 5;
	/**
	 * Using this to store all the enemies' location in sight . 
	 * 
	 * Which can be seen by all AI
	 * 
	 * 只能用于UI中。此处不适用
	 */
//	protected static ArrayList<int[]> enemy_Location = new ArrayList<int[]>(3);
	
	
	
	
	/**
	 * 
	 * @param info
	 * @return
	 */
	public abstract void play();
	
	public void initial(CenterControl centerControl , GameInfo gameInfo , SamuraiInfo samuraiInfo){
		this.centerControl =centerControl;
		this.gameInfo = gameInfo;
		this.samuraiInfo = samuraiInfo;
	}
	
	public void updateSamuraiInfo(SamuraiInfo samuraiInfo){
		this.samuraiInfo = samuraiInfo;
	}
	
	/**
	 * 
	 * 有需要遍历一下已占领及友军已占领区域来判断是否有敌军
	 * 
	 */
	
	/**
	 *  The following is the basic high level judge.	 
	 */
	
	
	public boolean canKill(){
		
		
		return false;
		
	}
	
	
	
	
	
	
	
	/**
	 *  Basic 
	 */
	public boolean canOccupy(int direction){
		return this.canOccupy(direction);
	}
	
	public boolean canMveOneStep(int direction){
		return this.centerControl.canMoveOneStep(direction);
	}
	
	public boolean canMoveTwoStep(int direction1 , int direction2){
		return this.centerControl.canMoveTwoStep(direction1, direction2);
	}
	
	public boolean canMoveThreeStep(int direction1 , int direction2 , int direction3){
		return this.centerControl.canMoveThreeStep(direction1, direction2, direction3);
	}
	
	public boolean canHide(){
		return this.centerControl.canHide();
	}
	
	public boolean canShow() {
		return this.centerControl.canShow();
	}
	
	public void hide(){
		this.centerControl.hide();
	}
	
	public void show(){
		this.centerControl.show();
	}
	
	public void occupy(int direction){
		this.centerControl.occupy(direction);
	}
	
	public void moveOneStep(int direction) {
		this.centerControl.moveOneStep(direction);
	}
	
	public void moveTwoStep(int direction1 , int direction2){
		this.centerControl.moveTwoStep(direction1, direction2);
	}
	
	public void moveThreeStep(int direction1 , int direction2 , int direction3) {
		this.centerControl.moveThreeStep(direction1, direction2, direction3);
	}
	
	public void moveThenHit(int moveDirection , int hitDirection){
		this.centerControl.moveThenHit(moveDirection, hitDirection);
	}
	
	public void hitThenMove(int hitDirection , int moveDirection){
		this.centerControl.hitThenMove(hitDirection, moveDirection);
	}
}
