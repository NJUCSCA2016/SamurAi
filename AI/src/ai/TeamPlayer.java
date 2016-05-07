/**
 * Date : Apr 2, 2016 9:45:00 PM
 */
package ai;

/**
 * @author Alone
 * Written by YYM
 */
public class TeamPlayer extends Player{
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
	 * 该片区域全部被占领了，并且没有人在。
	 */
	public final static int OCCUPY_ALL = 4;
	/**
	 * 没人并且部分占领了。
	 */
	public final static int NOBODY_PART = 5;
	
	
	public final static TeamPlayer SWORDS = new TeamPlayer();
	
	
	
	
	private TeamPlayer(){
	}
	
	
	
	public void play() {
		
		/**
		 * 
		 * Basic idea 
		 * Look whether there is enemy , if so , according to the weapon occupy .
		 * If is our army , don't occupy , just move to where there is the enemy is. 
		 * 
		 */
		
			
		
		
	}

}
