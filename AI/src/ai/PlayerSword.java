package ai;

import java.util.ArrayList;
import java.util.Arrays;

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
	
	public ArrayList<int[]> otherEnemies = new ArrayList<int[]>(3);
	public int enemiesNum = 0;

	public boolean markFieldOnOwn = false;
	
	private int[] enemyOne;
	private int weaponOne;
	private int[] enemyTwo;
	private int weaponTwo;
	private int[] enemyThree;
	private int weaponThree;
	/**
	 * 12个方向。
	 */
	private int[] directions = new int[12];
	
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
		Arrays.fill(directions, 0);
		
		int curX = this.samuraiInfo.curX;
		int curY = this.samuraiInfo.curY;
	
		if(this.markFieldOnOwn){
				/**
				 * 自己这里没被占领。
				 */
			for(int[] eachBlock : this.placeWaitingToOccupy){
				
				int blockX = eachBlock[0];
				int blockY = eachBlock[1];
			
				int offsetX = blockX - curX;
				int offsetY = blockY - curY;
				
				if(offsetX == 0){
					
				}else if(offsetY == 0){
					
				}
				
			}
			
		}else{
			/**
			 * 自己这里没被占了
			 */
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
