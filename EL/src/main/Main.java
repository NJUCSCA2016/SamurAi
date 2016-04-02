/**
 * Date : Mar 27, 2016 7:12:56 PM
 */
package main;

import control.Player;
import control.SimplePlayer;
import data.GameInfo;

/**
 * @author Alone
 * Written by YYM
 */
public class Main {
	
	public static void main(String[] argv) {
		GameInfo info = new GameInfo();
		Player p = new SimplePlayer();

		while (true){
			//读取回合信息
		    info.readTurnInfo();
		    System.out.println("# Turn "+info.turn);
		    if (info.curePeriod != 0){
		    	//开始
			System.out.println("0");
		    }
		    else {
			p.play(info);
				//结束player的行动
			System.out.println("0");
		    }
		}
	}
	
}
