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
			//��ȡ�غ���Ϣ
		    info.readTurnInfo();
		    System.out.println("# Turn "+info.turn);
		    if (info.curePeriod != 0){
		    	//��ʼ
			System.out.println("0");
		    }
		    else {
			p.play(info);
				//����player���ж�
			System.out.println("0");
		    }
		}
	}
	
}
