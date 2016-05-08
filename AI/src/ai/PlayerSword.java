package ai;

import data.SamuraiInfo;

public class PlayerSword extends Player{
	

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
	
	public int analizeField(){
		//如果这里有人的话就先杀人。
		int enemiesNum = this.samuraiInfo.enemyInOwnEyes.size();
		//Which is most situation
		if(enemiesNum == 1){
			int[] only = this.samuraiInfo.enemyInOwnEyes.get(0);
			if(CanKillOne(only)){
				return Player.CAN_KILL_ONE;
			}else{
				return Player.ONE_OUT_REACH;
			}
			
		}else if(enemiesNum == 2){
			int[] first = this.samuraiInfo.enemyInOwnEyes.get(0);
			int[] second = this.samuraiInfo.enemyInOwnEyes.get(1);
			
			if(first[0] == second[0] || first[1] == second[1]){
				if(this.CanKillOne(first) && this.CanKillOne(second)){
					return Player.DOUBLE_KILL;
				}
			}else{
				
			}
			
		}
		if(enemiesNum > 0){
			int canKillNum = 0;
			for(int[] each : this.samuraiInfo.enemyInOwnEyes){
				if(Math.abs(each[0] - this.samuraiInfo.curX) + Math.abs(each[1] - this.samuraiInfo.curY) <= 5){
					canKillNum++;
				}
			}
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
