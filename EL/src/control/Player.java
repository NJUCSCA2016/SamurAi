/**
 * Date : Mar 27, 2016 7:27:11 PM
 */
package control;

import data.GameInfo;

/**
 * @author Alone
 * Written by YYM
 */
public abstract class Player {
	//将cost 和 最大行动力 定义在终类中
	public final int[] cost = {0, 4, 4, 4, 4, 2, 2, 2, 2, 1, 1};
	public final int maxPower = 7;
	
	
	public abstract GameInfo play(GameInfo info);
	
}
