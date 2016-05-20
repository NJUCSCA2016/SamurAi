package data;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import user.GamePlayer;

public class FieldInfo {
	
	private int curIndex;
	private int maxRound;
	private int maxRecoverRound;
	private List<GamePlayer> players = new LinkedList<GamePlayer>();
	private int[] direction = new int[6];
	private final Random random = new Random();
	
	
	public FieldInfo() {
		initField();
	}
	
	
	private void initField(){

		/**
		 * 初始化需要的参数
		 */
		
			curIndex = 0;
			// 从左到右是x,从下到上是y
			// 关于x的系数为正，关于y的系数为负
			
			for (int i = 0; i < direction.length; i++) {
				direction[i] = 0;
			}
			maxRecoverRound = 12 * (1 + random.nextInt(4));

			maxRound = 12 * (6 + random.nextInt(14));
			maxRecoverRound  = 12 * (1 + random.nextInt(4));
			// x
//			for (int i = 0; i < 15; i++) {
//				// y
//				for (int j = 0; j < 15; j++) {
//					outSight[i][j] = true;
//				}
//			}
//
//			for (int i = 0; i < 6; i++) {
//				outSight[homeX[i]][homeY[i]] = false;
//			}
//		
	}
	private void homeInit(){
		this.players.get(0).initPlayer( random.nextInt(7) + 1, 0);
		this.players.get(1).initPlayer(14, random.nextInt(5) + 1);
		this.players.get(2).initPlayer(0,  random.nextInt(9) + 5);
		this.players.get(3).initPlayer(random.nextInt(6) + 8, 0);
		this.players.get(4).initPlayer(random.nextInt(7) + 1, 14);
		this.players.get(5).initPlayer(random.nextInt(6) + 7, 14);
	}
	
	
	
}
