/**
 * Date : Mar 27, 2016 7:28:35 PM
 */
package control;

import java.util.Random;

import data.GameInfo;


/**
 * @author Alone
 * Written by YYM
 */
public class SimplePlayer extends Player{
	
	public Random rnd;

	public SimplePlayer() {
		
		this.rnd = new Random();
		
	}



		/**
	     * AI �ĺ����㷨
	     */

	public GameInfo play(GameInfo info){
		int power = this.maxPower;
		int action;

		//��ʣ���ж���>=2ʱռ������ƶ�
		while (power >= 2){
		    action = this.rnd.nextInt(10)+1;

		    if (cost[action] <= power && info.isValid(action)){
			power -= cost[action];
			info.doAction(action);
		    }
		}
		//�ж���С��2ֻ�����ػ�����ʾ
		if (power != 0){
		    if (rnd.nextInt(11) > 8){
			action = info.samuraiInfo[info.weapon].hidden == 1 ? 10 : 9;
			if (info.isValid(action)){
			    info.doAction(action);
			}
		    }
		}
		
		//������Ϸ��Ϣ
		return new GameInfo(info);
	}

}
