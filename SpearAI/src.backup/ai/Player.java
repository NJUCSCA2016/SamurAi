/**
 * Date : Mar 27, 2016 7:27:11 PM
 */
package ai;

import control.CenterControl;
import data.GameInfo;
import data.SamuraiInfo;

/**
 * 这个类用于规范Player 的一些基本操作�??
 * 具体的每个高级操作落实到每个实体中�??
 * @author Alone
 * Written by YYM
 */
public abstract class Player {
	/**
	 *  每个AI肯定了解游戏信息和自身信息�??
	 */
	protected GameInfo gameInfo;
	protected SamuraiInfo samuraiInfo;
	/**
	 *  获取游戏控制�?
	 */
	protected  CenterControl centerControl ;
	/**
	 * 行动力消�?
	 */
	public final int[] cost = {0, 4, 4, 4, 4, 2, 2, 2, 2, 1, 1};
	/**
	 * �?大行动力
	 */
	public final int maxPower = 7;
	/**
	 * 哈密尔顿距离�?大�??
	 */
	protected final static int MAX_PATH = 5;
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
}
