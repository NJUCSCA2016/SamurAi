/**
 * Date : Mar 27, 2016 7:12:56 PM
 */
package main;

import ai.PlayerSpear;
import ai.SimplePlayer;
import control.CenterControl;
import data.ActionInfo;
import data.GameInfo;

/**
 * @author Alone
 * Written by YYM
 */
public class Main {
	
	public static void main(String[] argv) {
//		System.out.println((1+4)+" ");
		//用于初始化ActionInfo中信息。
		new ActionInfo();
		
		GameInfo info = new GameInfo();	
		PlayerSpear p = new PlayerSpear();
		info.playerAI = p;
		p.initial(new CenterControl(info.samuraiInfo[info.weapon]), info, info.samuraiInfo[info.weapon]);
		while (true){
			//读取信息
		    info.readTurnInfo();
		    System.out.println("# Turn "+info.turn);
		    System.err.println("Turn : = " + info.turn );
		    if (info.curePeriod != 0){
		    	// 仍然处于治愈周期。什么都不能做。
		    	System.out.println("0");
		    }
		    else {
		    	// 未被击杀，或者以及从治愈周期恢复
		    	p.play();
			
		    	System.out.println("0");
		    }
		}
	}
	
}
