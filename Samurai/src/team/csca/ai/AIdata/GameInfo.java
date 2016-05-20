/**
 * Date : Mar 28, 2016 6:47:25 PM
 */
package team.csca.ai.AIdata;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import team.csca.ai.AXE.PlayerAxe;

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
	public PlayerAxe playerAI;
	
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
//	System.err.println(turns);
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
			
			for(i = 0 ; i < 3 ; ++i){
				res = this.read();
				this.samuraiInfo[i].curX = Integer.parseInt(res[0]);
				this.samuraiInfo[i].curY = Integer.parseInt(res[1]);
				this.samuraiInfo[i].hidden = Integer.parseInt(res[2]);
				this.samuraiInfo[i].weapon = i;
				this.samuraiInfo[i].width = this.width;
				this.samuraiInfo[i].height = this.height;
			}
			SamuraiInfo myself = this.samuraiInfo[this.weapon];
//			System.err.println("CurX     "+myself.curX +  "    CurY   " + myself.curY);
			//更新Player的信息。
	    	playerAI.updateSamuraiInfo(myself);
	    
			/**
			 * Update the enemies' information.
			 */
			this.playerAI.enemiesNum = 0;
			this.playerAI.enemyInOwnEyes.clear();
			this.playerAI.otherEnemies.clear();
			this.playerAI.markFieldOnOwn = false;
		
			for(i = 3; i < GameInfo.PLAYER_NUM  ; ++i){
				
				res = this.read();
				int curX =  Integer.parseInt(res[0]);
				int curY = Integer.parseInt(res[1]);
				this.samuraiInfo[i].curX = curX;
				this.samuraiInfo[i].curY = curY;
				//This info is of no use
				this.samuraiInfo[i].hidden = Integer.parseInt(res[2]);
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
			
//      This step is of no need to do. For the loop immediately after it will get each element of the field
			
//		//以0填充field[i]
		for (i = 0; i < this.height; ++i){
			Arrays.fill(this.field[i], 0);
		}
		
			
		/**
		 *  Finished the 	sight in own sight 
		 */
		this.playerAI.placeWaitingToOccupy.clear();
		System.err.println("Myself HomeX  " + myself.homeX + "  HomeY  =" + myself.homeY );
    	for (i = 0; i < this.height; ++i){
    		res = this.read();
//    		System.err.println("CurX     "+myself.curX +  "    CurY   " + myself.curY);
    		for (int j = 0; j < this.width; ++j){
//    			System.err.println(j);
//    			System.err.println(res.length);
////    			
    			this.field[i][j] = Integer.parseInt(res[j+1]);
    			
    			if(j != myself.homeX || i != myself.homeY){
//    				System.err.println("Get");
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
//    					System.err.print("Place X = " + j + "   Y =  " + i);
    				}
				}
    			}
    		}
    		
//    		System.err.println(" "); 
    	}
//    	System.err.println("Field " + field[myself.curY][myself.curX] + "Weapon = " + myself.weapon) ;
//    	System.err.println(this.playerAI.placeWaitingToOccupy.size());

    	
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
