/**
 * Date : Mar 27, 2016 7:27:11 PM
 */
package ai;

import java.util.ArrayList;

import control.CenterControl;
import data.GameInfo;
import data.SamuraiInfo;

/**
 * 这个类用于规范Player 的一些基本操作。
 * 具体的每个高级操作落实到每个实体中。
 * 
 * AI 是不知道每次对方AI的行为的，只能知道它的位置，当然，是显示的情况下。
 * 
 * 
 * @author Alone
 * Written by YYM
 */
public abstract class Player {
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
	 */
	protected static ArrayList<int[]> enemy_Location = new ArrayList<int[]>(3);
	/**
	 * 
	 * @param info
	 * @return
	 */
	public abstract GameInfo play(GameInfo info);
	
	public void initial(CenterControl centerControl , GameInfo gameInfo , SamuraiInfo samuraiInfo){
		this.centerControl =centerControl;
		this.gameInfo = gameInfo;
		this.samuraiInfo = samuraiInfo;
	}
	
	/**
	 *  The following is the basic high level judge.	 
	 */
	
	
	
}
