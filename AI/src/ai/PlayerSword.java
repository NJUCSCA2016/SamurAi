package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import data.SamuraiInfo;

public class PlayerSword extends Player{
	
	/**
	 * 在自身范围内的敌军的AI。如果有的话而且剁得到的话。就杀了吧。
	 * @Limit  
	 * 我懒得管别人那是不是有人。不过我这里有人的话，一定是要狗带的。
	 * 
	 * 我懒得管它放在哪了。放在Player里面也可以。放在这里也无妨。等我写完了再移过去吧。
	 */
	
	public ArrayList<int[]> enemyInOwnEyes = new ArrayList<int[]>(3);
	public ArrayList<int[]> placeWaitingToOccupy = new ArrayList<int[]>();
	
	public ArrayList<SamuraiInfo> otherEnemies = new ArrayList<SamuraiInfo>(3);
	public int enemiesNum = 0;

	public boolean markFieldOnOwn = false;
//	
//	private int[] enemyOne;
//	private int weaponOne;
//	private int[] enemyTwo;
//	private int weaponTwo;
//	private int[] enemyThree;
//	private int weaponThree;
	/**
	 * 12 / 16个方向。
	 */
	private int[] directions;
	
	private int[] indexOfMax;
	
	private Random random = new Random();
	
	
	
	@Override
	public void play() {
		/**
		 * 可能需要分析上一步的对局信息。
		 * 这里先不考虑上一步的时候的对局
		 */
		if(this.enemiesNum > 0){
			/**
			 * 执行对峙分析
			 */
		}else if(! this.placeWaitingToOccupy.isEmpty()){
			/**
			 * 执行移动和占领。
			 */
			this.occupyField();
		}else {
			/**
			 * 当前没有任何敌军。
			 * 可以往前走。但是注意不要走到有敌军的地方。
			 * 需要考虑友军那边的敌军
			 * 对于Sword。攻击范围比较长。所以可以往友军那边走。
			 * TODO：  如果友军那边没有敌军。则待定。
			 * //TODO : 添加计算行动方向的方法。此方法可以置于Player中。因为其他武士也会需要。
			 */
			//根据敌军数目来确定行动。
			switch (this.otherEnemies.size()) {
			case 0:
				//随意走一个方向
				break;
			case 1:
				
				break;
			case 2 :
				
				break;
			case 3 :
				
				break;
			default:
				break;
			}
			
//			if(this.otherEnemies.isEmpty()){
//				
//			}else{
//				
//				
//			}
//			
		}
		//@Notice : 如果不能杀的话一定要采取行动。否则后果很难看。
	}
	
	
	
	/**
	 * 
	 * @Thingking : 当人物重合时怎么办。他们有可能用的不是这里面的GameInfo。所以需要考虑。我想偷偷的Approach他们。然后Kill
	 * 当周围没有敌军时，采取此方法。分析周围战场。占领能够占领最多的地方的方向。
	 * 
	 */
	public void occupyField(){
		//将Directions以0填充。
		if(this.markFieldOnOwn){
			this.directions = new int[16];
		}else{
			this.directions = new int[12];
		}
		Arrays.fill(directions, 0);
		
		this.census();
		//TODO : 这里的数组遍历是错误的。
		//TODO : 改为this.indexOfMax的遍历就好了
		//如果两个数组的大小完全相同。这就说明可能每个都是0.需要添加判断。
		if(this.indexOfMax.length == this.directions.length){
			//完全有可能。此时应该采取相应行为。
			if(this.directions[0] == 0){
				//移动不占领。
				//TODO
			}else{
				// 说明每个方向都是一样的。
				//向人多的一占领
				//TODO
			}
	
		}else{
			int i = directions.length - 1;
			for(; i > 0 ; i--){
				if(directions[i] != directions[i+1]){
					i++;
					break;
				}
			}
			int actionCodeField = directions.length  - i;
			
			takeAction(i + random.nextInt(actionCodeField));
			
		}
		//Move to where there exist enemy.
	}
	/**
	 * 
	 * @param enemyX
	 * @param enemyY
	 * @param weapon
	 * @return
	 */
	public int checkDirectionOFEnemy(int enemyX, int enemyY, int weapon){
		int offsetX = Math.abs(enemyX - this.samuraiInfo.curX);
		int offsetY = Math.abs(enemyY - this.samuraiInfo.curY);
		int totle = offsetX + offsetY;
		/**
		 *@Warning ：  如果是碰到对面的剑士。Be careful
		 */
		if(weapon == this.samuraiInfo.weapon){
			if(totle < 8 && (offsetX <= 1 || offsetY <= 1)){
				/**
				 * Dangerous
				 */
				return Player.DANGER;
			}else{
				
			}
		}else{
			if(totle > 10){
				return Player.TOO_FAR;
			}
		}
		
		return 1; 
	}
	
	public void takeAction(int actionCode){
		//TODO : 补全下面的操作。
		switch (actionCode) {
		case 0:
			this.occupy(1);
			break;
		case 1:
			this.occupy(2);
			break;
		case 2:
			this.occupy(3);
			break;
		case 3:
			this.occupy(4);
			break;
		case 4:
			
			break;
		case 5:
			
			break;
		case 6:
			
			break;
		case 7:
			
			break;
		case 8:
			
			break;
		case 9:
			
			break;
		case 10:
			
			break;
		case 11:
		
			break;
		case 12:
			
			break;
		case 13:
			
			break;
		case 14:
			
			break;
		case 15:
			
			break;
		case 16:
			
			break;
		default:
			break;
		}
	}
	
	
	/**
	 * 
	 * 统计方法： 统计并确定占领位置最多的Greedy判断。
	 * 
	 */
	public void census(){
		if(this.markFieldOnOwn){
			this.onOwnField();
		}else{
			this.notOnOwnField();
		}	
		getMaxIndex();
	}
	
	public void getMaxIndex(){
		int max = directions[0];
		int count = 1;
		for(int i = 1 ; i < directions.length ; i++){
			if(max == directions[i]){
				count ++;
			}else if(max < directions[i]){
				max = directions[i];
				count = 1;
			}
		}
		count = 0;
		this.indexOfMax = new int[count];
		for(int i = 0 ; i < directions.length ; i++){
			if(directions[i] == max){
				indexOfMax[count] = i;
				count++;
			}
		}
	}
	
	
	public void onOwnField(){

		int curX = this.samuraiInfo.curX;
		int curY = this.samuraiInfo.curY;
		
		for(int i = 12 ; i < 16 ; i++){
			directions[i]++;
		}
		
			/**
			 * 自己这里没被占了
			 */
			for(int[] eachBlock : this.placeWaitingToOccupy){
				int blockX = eachBlock[0];
				int blockY = eachBlock[1];
			
				int offsetX = blockX - curX;
				int offsetY = blockY - curY;
				
				if(offsetY == 0){
					if(offsetX < 0){
						//左
						this.directions[0]++;
						if(offsetX > -4){
							//右移后左击
							this.directions[12]++;
						}
					}else{
						//右
						this.directions[1]++;
						if(offsetX < 4){
							//左移后右击
							this.directions[13]++;
						}
					}
				}else if(offsetX == 0){
					if(offsetY > 0){
						//上
						this.directions[2]++;
						if(offsetY < 4){
							//下移后上击
							this.directions[14]++;
						}
					}else{
						//下
						this.directions[3]++;
						if(offsetY > -4){
							//上移后下击。
							this.directions[15]++;
						}
					}
				}else if(offsetX == -1){
					if(offsetY > 0){
						//左上
						this.directions[4]++;
					}else{
						//左下
						this.directions[5]++;
					}
				}else if(offsetX == 1){
					if(offsetY > 0){
						//右上
						this.directions[6]++;
					}else{
						//右下
						this.directions[7]++;
					}
				}else if (offsetY == 1) {
					if(offsetX > 0){
						//上右
						this.directions[8]++;
					}else{
						//上左
						this.directions[9]++;
					}
				}else if(offsetY == -1){
					if(offsetX > 0){
						this.directions[10]++;
					}else{
						this.directions[11]++;
					}
				}
				//其他的因为打不到所以就不干了	
					
			}
	}
	
	public void notOnOwnField(){
		
		int curX = this.samuraiInfo.curX;
		int curY = this.samuraiInfo.curY;
		
			/**
			 * 自己这里没被占了
			 */
			for(int[] eachBlock : this.placeWaitingToOccupy){
				int blockX = eachBlock[0];
				int blockY = eachBlock[1];
			
				int offsetX = blockX - curX;
				int offsetY = blockY - curY;
				
				if(offsetY == 0){
					if(offsetX < 0){
						//左
						this.directions[0]++;
					}else{
						//右
						this.directions[1]++;
					}
				}else if(offsetX == 0){
					if(offsetY > 0){
						//上
						this.directions[2]++;
					}else{
						//下
						this.directions[3]++;
					}
				}else if(offsetX == -1){
					if(offsetY > 0){
						//左上
						this.directions[4]++;
					}else{
						//左下
						this.directions[5]++;
					}
				}else if(offsetX == 1){
					if(offsetY > 0){
						//右上
						this.directions[6]++;
					}else{
						//右下
						this.directions[7]++;
					}
				}else if (offsetY == 1) {
					if(offsetX > 0){
						//上右
						this.directions[8]++;
					}else{
						//上左
						this.directions[9]++;
					}
				}else if(offsetY == -1){
					if(offsetX > 0){
						this.directions[10]++;
					}else{
						this.directions[11]++;
					}
				}
				//其他的因为打不到所以就不干了	
					
			}
	}
	
	
	public int analizeField(){
		//如果这里有人的话就先杀人。
		
		//Which is most situation
		
		if(this.enemiesNum > 0){
			if(this.enemiesNum == 1){
				int[] only = this.enemyInOwnEyes.get(0);
				if(CanKillOne(only)){
					return Player.CAN_KILL_ONE;
				}else{
					return Player.ONE_OUT_REACH;
				}
				
			}else if(this.enemiesNum == 2){
				int[] first = this.enemyInOwnEyes.get(0);
				int[] second = this.enemyInOwnEyes.get(1);
				
				if(first[0] == second[0] || first[1] == second[1]){
					if(this.CanKillOne(first) && this.CanKillOne(second)){
						return Player.DOUBLE_KILL;
					}
				}else{
					
				}
				
			}
		}else{
			
		}
		
		
		
		
		
		return Player.OCCUPY_ALL;
	}
	
	
	
	
	public boolean CanKillOne(int[] only){
		return (only[0] - this.samuraiInfo.curX <= 1 && this.samuraiInfo.curX  - only[0] <= 1 )||(only[1] - this.samuraiInfo.curY <= 1 && this.samuraiInfo.curY  - only[1] <= 1 );
	}
	
	public boolean CanKillOnlyOne(int[] only, int curX , int curY){
		return (only[0] - curX <= 1 && curX  - only[0] <= 1 )||(only[1] - curY <= 1 && curY  - only[1] <= 1 );
	}
	
	
	
//	public boolean checkAndKill(){
//		
//		
//		
//	}
//	
	
	/**
	 * 
	 *	只有一个人在边上，必须得杀啊。
	 *没准还可以double kill
	 * @return
	 */
	public void  mustKillTheOnlyOneLajiGuy(){
		//Kill
	}
	/**
	 * 
	 * 能双杀还不杀这不傻逼吗。
	 * 
	 */
	public void mustDoubleKill(){
		
	}
	/**
	 * 
	 * 这个就不太可能了。
	 * 尴尬脸.jpg
	 * 
	 */
	public void allKill(){
		
		
	}


}