/**
 * Date : Mar 28, 2016 6:47:25 PM
 */
package team.csca.ai.AIdata;

import java.util.Arrays;

import team.csca.ai.AI.Player;
import team.csca.ai.AIControl.CenterControl;
import team.csca.ai.swapOfAI.InstructionSwap;

/**
 * @author Alone
 * Written by YYM
 */
public final class GameInfo {
	/**
	 * 本来为了实现面向对象编程。这里的实例变量应该都改为private
	 * 但是于此处不考虑，均为public直接获取
	 */
	
	/**
	 * 懒得set了
	 */
	public Player playerAI;
	public static final int PLAYER_NUM = 6;

    public int turns;
    public int weapon;
  
    public SamuraiInfo[] samuraiInfo;
    
    public int[][] field;
    public int turn, curePeriod;
    public int width, height;
    public int maxCure;
    
    public GameInfo(Player playerAI) {
		this.playerAI = playerAI;
	}
    
    public void initInfo(int[] basicInfo , int[] homeX , int[] homeY , InstructionSwap swap){
    	turns  = basicInfo[0];
    	
    	weapon = basicInfo[1];
    	width = 15;
    	height = 15;
    	maxCure = basicInfo[2];
    	field = new int[height][width];
    	this.samuraiInfo = new SamuraiInfo[GameInfo.PLAYER_NUM];
    	for (int i = 0; i < GameInfo.PLAYER_NUM; ++i){
    	    this.samuraiInfo[i] = new SamuraiInfo(this , swap);
    	}
    	for (int i = 0; i < GameInfo.PLAYER_NUM; ++i){
    	    this.samuraiInfo[i].homeX = homeX[i];
    	    this.samuraiInfo[i].homeY = homeY[i];
    	}
    	for (int i = 0; i < GameInfo.PLAYER_NUM; ++i){
    	    this.samuraiInfo[i].rank = 0;
    	    this.samuraiInfo[i].score = 0;
    	}
    	turn = 0;
    	curePeriod = 0;
    	playerAI.initial(new CenterControl(this.samuraiInfo[this.weapon]),this.samuraiInfo[this.weapon]);
    }

    /**
     * 读取回合信息
     */
    public void readTurnInfo(int[] basicInfo , int[] curXes , int[] curYes , int[] hidden , int[][] field){
    	//更新回合数信息
    	this.turn =basicInfo[0];
    	//更新恢复周期
		this.curePeriod = basicInfo[1];
		
			int i = 0 ;
			
			for(i = 0 ; i < 3 ; ++i){
			
				this.samuraiInfo[i].curX = curXes[i];
				this.samuraiInfo[i].curY = curYes[i];
				this.samuraiInfo[i].hidden =hidden[i];
				this.samuraiInfo[i].weapon = i;
				this.samuraiInfo[i].width = this.width;
				this.samuraiInfo[i].height = this.height;
			}
			SamuraiInfo myself = this.samuraiInfo[this.weapon];
	    	playerAI.updateSamuraiInfo(myself);
	    
			/**
			 * Update the enemies' information.
			 */
			this.playerAI.enemiesNum = 0;
			this.playerAI.enemyInOwnEyes.clear();
			this.playerAI.otherEnemies.clear();
			this.playerAI.markFieldOnOwn = false;
		
			for(i = 3; i < GameInfo.PLAYER_NUM  ; ++i){
				
				int curX =  curXes[i];
				int curY = curYes[i];
				this.samuraiInfo[i].curX = curX;
				this.samuraiInfo[i].curY = curY;
				//This info is of no use
				this.samuraiInfo[i].hidden = hidden[i];
				this.samuraiInfo[i].weapon = i;
				/**
				 * Consider to use what to store the information.
				 * Array is too waste and not convenient to search 
				 * You only need to know the enemy in your side
				 */
				
				//Only need to judge one coordinate
				//If is not -1 ; then it must lies in allies' and my own sight 
				if(curX != -1){
					//Five Manhattan distance
					for(int i1 = 0 ; i1 < 3 ; i1++){
						SamuraiInfo eachAI = this.samuraiInfo[i1];
						/**
						 * 如果是当前的AI添加到in own eyes
						 */
						if(curX != this.samuraiInfo[i].homeX || curY != this.samuraiInfo[i].homeY){
							if(Math.abs(eachAI.curX - curX) + Math.abs(eachAI.curY - curY) <= 5){
								if(eachAI.weapon == this.weapon){
									/**
									 * 在我的视野范围内
									 */
									this.playerAI.enemyInOwnEyes.add(new int[]{curX , curY});
									this.playerAI.enemiesNum++;
								}else{
									/**
								 	* 只能添加到Other Enemies
								 	*/
									this.playerAI.otherEnemies.add(eachAI);
								}
							}
						}
					}
				}
			}
		for (i = 0; i < this.height; ++i){
			Arrays.fill(this.field[i], 0);
		}
		
			
		/**
		 *  Finished the 	sight in own sight 
		 */
		this.playerAI.placeWaitingToOccupy.clear();
    	for (i = 0; i < this.height; ++i){
    		for (int j = 0; j < this.width; ++j){
    			this.field[i][j] = field[i][j];
    			
    			if(j != myself.homeX || i != myself.homeY){
    				boolean isOtherHome = false;
					for(int m = 0 ; m < 6 ; m++){
						if(this.samuraiInfo[m].homeX == j && this.samuraiInfo[m].homeY == i){
							isOtherHome = true;
						}
					}
				if(!isOtherHome){
					if(Math.abs(myself.curX - j) + Math.abs(myself.curY - i) <= 5 && this.field[i][j] >= 3){
						if(myself.curX == j && myself.curY == i){
							this.playerAI.markFieldOnOwn = true;
						}
    					this.playerAI.placeWaitingToOccupy.add(new int[]{j , i});
    				}
				}
    			}
    		}
    	}
    }
 
}
