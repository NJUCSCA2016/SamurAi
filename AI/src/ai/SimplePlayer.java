/**
 * Date : Mar 27, 2016 7:28:35 PM
 */
package ai;

import java.util.Random;

import data.GameInfo;


/**
 * @author Alone
 * Written by YYM
 */
public class SimplePlayer extends Player{
	
	public Random rnd;
	
	public final static SimplePlayer SIMPLE = new SimplePlayer();
	
	private SimplePlayer() {
		
		this.rnd = new Random();
		
	}



		/**
	     * AI 的核心算法
	     */

	public GameInfo play(GameInfo info){
		int power = this.maxPower;
		int action;

		//当剩余行动力>=2时占领或者移动
		while (power >= 2){
		    action = this.rnd.nextInt(10)+1;

//		    if (cost[action] <= power && info.isValid(action)){
//			power -= cost[action];
//			info.doAction(action);
//		    }
		}
		//行动力小于2只能隐藏或者显示
		if (power != 0){
//		    if (rnd.nextInt(11) > 8){
//			action = info.samuraiInfo[info.weapon].hidden == 1 ? 10 : 9;
//			if (info.isValid(action)){
//			    info.doAction(action);
//			}
//		    }
		}
		
		//更新游戏信息
		return new GameInfo(info);
	}

}
