package gameinfo;

import user.GamePlayer;

public class FieldInfo {
	
	private int curIndex;
	private int round;
	
	
	public FieldInfo() {
		initField();
	}
	
	
	private void initField(){

		/**
		 * 初始化需要的参数
		 */
		public void initField() {
			curIndex = 0;
			round = 0;
			// 从左到右是x,从下到上是y
			// 关于x的系数为正，关于y的系数为负
			x[0] = random.nextInt(7) + 1;
			y[0] = 0;
			x[1] = 14;
			y[1] = random.nextInt(5) + 1;
			x[2] = 0;
			y[2] = random.nextInt(9) + 5;
			x[3] = random.nextInt(6) + 8;
			y[3] = 0;
			x[4] = random.nextInt(7) + 1;
			y[4] = 14;
			x[5] = random.nextInt(6) + 7;
			y[5] = 14;
			for (int i = 0; i < 6; i++) {
				homeX[i] = x[i];
				homeY[i] = y[i];
			}
			for (int i = 0; i < direction.length; i++) {
				direction[i] = 0;
			}
			maxRecoverRound = 12 * (1 + random.nextInt(4));
			for (int i = 0; i < recoverRound.length; i++) {
				recoverRound[i] = 0;
			}

			maxRound = 12 * (6 + random.nextInt(14));
			maxRecoverRound  = 12 * (1 + random.nextInt(4));
			// x
			for (int i = 0; i < 15; i++) {
				// y
				for (int j = 0; j < 15; j++) {
					outSight[i][j] = true;
				}
			}

			for (int i = 0; i < 6; i++) {
				outSight[homeX[i]][homeY[i]] = false;
			}
		}
	}
	
	
	
}
