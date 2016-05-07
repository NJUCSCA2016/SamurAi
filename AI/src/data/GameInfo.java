/**
 * Date : Mar 28, 2016 6:47:25 PM
 */
package data;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ai.TeamPlayer;

/**
 * @author Alone
 * Written by YYM
 */
public final class GameInfo {
	/**
	 * 本来为了实现面向对象编程。这里的实例变量应该都改为private
	 * 但是于此处不考虑，均为public直接获取
	 */
	private static BufferedReader stdReader;
	public static final int PLAYER_NUM = 6;

    public int turns;
    public int side;
    public int weapon;
  
    public SamuraiInfo[] samuraiInfo;
    
    public int[][] field;
    public int turn, curePeriod;
    public int width, height;
    public int maxCure;
    
    
    public GameInfo(GameInfo info){
    //初始化游戏信息。
    //回合数 哪一边 武器 战场宽 战场高 治疗周期
    
	this.turns = info.turns;
	this.side = info.side;
	this.weapon = info.weapon;
	this.width = info.width;
	this.height = info.height;
	this.maxCure = info.maxCure;
	this.samuraiInfo = info.samuraiInfo;
	this.turn = info.turn;
	this.curePeriod = info.curePeriod;
	this.field = info.field;
	
    }
    
    
    //从流中读取Game信息
    
    public GameInfo(){
	GameInfo.stdReader = new BufferedReader(new InputStreamReader(System.in));

	String[] res = this.read();
	
	this.turns = Integer.parseInt(res[0]);
	this.side = Integer.parseInt(res[1]);
	this.weapon = Integer.parseInt(res[2]);
	this.width = Integer.parseInt(res[3]);
	this.height = Integer.parseInt(res[4]);
	this.maxCure = Integer.parseInt(res[5]);
	this.samuraiInfo = new SamuraiInfo[GameInfo.PLAYER_NUM];
	for (int i = 0; i < GameInfo.PLAYER_NUM; ++i){
	    this.samuraiInfo[i] = new SamuraiInfo(this);
	}
	for (int i = 0; i < GameInfo.PLAYER_NUM; ++i){
	    res = this.read();
	    this.samuraiInfo[i].homeX = Integer.parseInt(res[0]);
	    this.samuraiInfo[i].homeY = Integer.parseInt(res[1]);
	}
	for (int i = 0; i < GameInfo.PLAYER_NUM; ++i){
	    res = this.read();
	    this.samuraiInfo[i].rank = Integer.parseInt(res[0]);
	    this.samuraiInfo[i].score = Integer.parseInt(res[1]);
	}
	this.turn = 0;
	this.curePeriod = 0;
	this.field = new int[this.height][this.width];
	
	
	System.out.println("0");
    }
    
    
    
    /**
     * 读取
     */
    public String[] read(){
	String line = "";
	try{
		//以#开头 //一开始已经读取了一条 line
	    for (line = GameInfo.stdReader.readLine(); line.startsWith("#"); line = GameInfo.stdReader.readLine());
	} catch (Exception e) {
	    e.getStackTrace();
	    System.exit(-1);
	}
	
	return line.split("\\s");
    }

    /**
     * 读取回合信息
     */
    public void readTurnInfo(){
    	String[] res = this.read();
    	//可能异常：无信息
    	if (res.length == 0){
    		System.exit(-1);
    	}
    	//更新回合数信息
    	this.turn = Integer.parseInt(res[0]);
    	//异常处理
    	if (this.turn < 0){
    		System.exit(-1);
    	}
    	//更新恢复周期
    	res = this.read();
		this.curePeriod = Integer.parseInt(res[0]);
		
		/**
		 * Consider get the ally first.
		 */
		
		
		
			int i = 0 ;
			
			for( ; i < GameInfo.PLAYER_NUM>> 1 ; ++i){
				res = this.read();
				this.samuraiInfo[i].curX = Integer.parseInt(res[0]);
				this.samuraiInfo[i].curY = Integer.parseInt(res[1]);
				this.samuraiInfo[i].hidden = Integer.parseInt(res[2]);
			}
			SamuraiInfo myself = samuraiInfo[this.weapon - 1];
			//更新Player的信息。
	    	TeamPlayer.SWORDS.updateSamuraiInfo(myself);
			++i;
			/**
			 * Update the enemies' information.
			 */
			for(; i < GameInfo.PLAYER_NUM  ; ++i){
				
				res = this.read();
				int curX =  Integer.parseInt(res[0]);
				int curY = Integer.parseInt(res[1]);
				this.samuraiInfo[i].curX = curX;
				this.samuraiInfo[i].curY = curY;
				//This info is of no use
				this.samuraiInfo[i].hidden = Integer.parseInt(res[2]);
				/**
				 * Consider to use what to store the information.
				 * Array is too waste and not convenient to search 
				 * You only need to know the enemy in your side
				 */
				
				//Only need to judge one coordinate
				//If is not -1 ; then it must lies in allies' and my own sight 
				if(curX != -1){
					//Five Manhattan distance
					if(Math.abs(myself.curX - curX) + Math.abs(myself.curY - curY) <= 5){
						myself.enemyInOwnEyes.add(new int[]{curX , curY});
					}
				}
				
				
			}
			
//      This step is of no need to do. For the loop immediately after it will get each element of the field
			
//		//以0填充field[i]
//		for (i = 0; i < this.height; ++i){
//			Arrays.fill(this.field[i], 0);
//		}
//		
		/**
		 *  Finished the 	sight in own sight 
		 */
    	for (i = 0; i < this.height; ++i){
    		res = this.read();
    		
    		for (int j = 0; j < this.width; ++j){
    			this.field[i][j] = Integer.parseInt(res[j+1]);
    			if(Math.abs(myself.curX - j) + Math.abs(myself.curY - i) <= 5 && this.field[i][j] >= 3){
    				myself.placeWaitingToOccupy.add(new int[]{j , i});
    			}
    		}
    	}

    	
    }
 
//    
//    
//    
//    public void doAction(int action){
//	SamuraiInfo myself = this.samuraiInfo[this.weapon];
//	int curX = myself.curX;
//	int curY = myself.curY;
//
//	if (action >= 1 && action <= 4){
//	    this.occupy(action-1);
//	}
//	if (action == 5){
//	    curY += 1;
//	}
//	if (action == 6){
//	    curX += 1;
//	}
//	if (action == 7){
//	    curY -= 1;
//	}
//	if (action == 8){
//	    curX -= 1;
//	}
//	if (action == 9){
//	    myself.hidden = 1;
//	}
//	if (action == 10){
//	    myself.hidden = 0;
//	}
//	myself.curX = curX;
//	myself.curY = curY;
//	this.samuraiInfo[this.weapon] = myself;
//	System.out.print(action+"Finish action ");
//    }
}
