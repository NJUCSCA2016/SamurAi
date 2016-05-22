/**
 * Date : Mar 27, 2016 7:27:11 PM
 */
package team.csca.ai.AI;

import java.util.ArrayList;
import java.util.Random;

import team.csca.ai.AIControl.CenterControl;
import team.csca.ai.AIdata.GameInfo;
import team.csca.ai.AIdata.SamuraiInfo;
import team.csca.ai.swapOfAI.InstructionSwap;
import team.csca.view.pm.JPanelPM;

/**
 * 这个类用于规范Player 的一些基本操作。
 * 具体的每个高级操作落实到每个实体中。
 * 
 * AI 是不知道每次对方AI的行为的，只能知道它的位置，当然，是显示的情况下。
 * 
 *@warning :  每个AI即使在操作之后并不能更新自身的视野。
 * 
 * @author Alone
 * Written by YYM
 * 
 * 
 * @see : 游戏的目标是为了占领更多的领域。而不是杀人。
 * 
 * 
 * 
 */
public abstract class Player {
	
	
	public ArrayList<int[]> enemyInOwnEyes = new ArrayList<int[]>(3);
	public ArrayList<int[]> placeWaitingToOccupy = new ArrayList<int[]>();
	protected ArrayList<int[]> enemiesCanKill = new ArrayList<int[]>(3);
	public ArrayList<SamuraiInfo> otherEnemies = new ArrayList<SamuraiInfo>(3);
	public ArrayList<Integer> weapons = new ArrayList<Integer>(3);
	public int enemiesNum = 0;
	
	public boolean markFieldOnOwn = false;

	/**
	 * 12 / 16个方向。
	 */
	public int[] directions;
	
	public int[] indexOfMax;
	
	public Random random = new Random();
		
	public int curX =0;
	
	public int curY=0;
	
	
	
	/**
	 * 战场分析功能
	 */
	
	public final static int LEFT_SIDE = 0;
	
	public final static int RIGHT_SIDE = 1;
	
	public final static int UP_SIDE = 2;
	
	public final static int DOWN_SIDE = 3;

	public final static int DANGER =  12;
	
	
	
	
	
	
	/**
	 *  每个AI肯定了解游戏信息和自身信息。
	 */
	protected GameInfo gameInfo;
	protected SamuraiInfo samuraiInfo;
	/**
	 *  获取游戏控制器
	 */
	protected  CenterControl centerControl ;
	/**
	 * 行动力消耗
	 */
	public final int[] cost = {0, 4, 4, 4, 4, 2, 2, 2, 2, 1, 1};
	/**
	 * 
	 * 当前已消耗行动力
	 * 
	 */
	protected int current_Cost = 0;
	/**
	 * 最大行动力
	 */
	public final int maxPower = 7;

	/**
	 * 哈密尔顿距离最大值
	 */
	protected final static int MAX_PATH = 5;
	/**
	 * Using this to store all the enemies' location in sight . 
	 * 
	 * Which can be seen by all AI
	 * 
	 * 只能用于UI中。此处不适用
	 */
//	protected static ArrayList<int[]> enemy_Location = new ArrayList<int[]>(3);
	private InstructionSwap swap;
	
	public Player() {
		this.gameInfo = new GameInfo(this);
	}
	
	public void initAIField(int[] basicInfo , int[] homeX , int[] homeY , JPanelPM panel){
		swap = new InstructionSwap(this.gameInfo , panel);
		swap.initAIField(basicInfo, homeX, homeY);
	}
	
	public void sendInfo(int[] basicInfo , int[] curXes , int[] curYes , int[] hidden , int[] occupy){
		swap.sendInfo(basicInfo, curXes, curYes, hidden, occupy);
	}	
	
	/**
	 * 
	 * @param info
	 * @return
	 */
	public abstract void play();
	
	public void initial(CenterControl centerControl , SamuraiInfo samuraiInfo){
		this.centerControl =centerControl;
		this.samuraiInfo = samuraiInfo;
	}
	
	public void updateSamuraiInfo(SamuraiInfo samuraiInfo){
		this.samuraiInfo = samuraiInfo;
	}
	
	/**
	 * 
	 * 有需要遍历一下已占领及友军已占领区域来判断是否有敌军
	 * 
	 */
	
	/**
	 *  The following is the basic high level judge.	 
	 */
	
	
	public boolean canKill(){
		
		
		return false;
		
	}
	
	
	
	
	
	
	
	/**
	 *  Basic 
	 */
	public boolean canOccupy(int direction){
		return this.canOccupy(direction);
	}
	
	public boolean canMoveOneStep(int direction){
		return this.centerControl.canMoveOneStep(direction);
	}
	
	public boolean canMoveTwoStep(int direction1 , int direction2){
		return this.centerControl.canMoveTwoStep(direction1, direction2);
	}
	
	public boolean canMoveThreeStep(int direction1 , int direction2 , int direction3){
		return this.centerControl.canMoveThreeStep(direction1, direction2, direction3);
	}
	
	public boolean canHide(){
		return this.centerControl.canHide();
	}
	
	public boolean canShow() {
		return this.centerControl.canShow();
	}
	
	public void hide(){
		this.centerControl.hide();
	}
	
	public void show(){
		this.centerControl.show();
	}
	
	public void occupy(int direction){
		this.centerControl.occupy(direction);
	}
	
	public void moveOneStep(int direction) {
		this.centerControl.moveOneStep(direction);
	}
	
	public void moveTwoStep(int direction1 , int direction2){
		this.centerControl.moveTwoStep(direction1, direction2);
	}
	
	public void moveThreeStep(int direction1 , int direction2 , int direction3) {
		this.centerControl.moveThreeStep(direction1, direction2, direction3);
	}
	
	public void moveThenHit(int moveDirection , int hitDirection){
		this.centerControl.moveThenHit(moveDirection, hitDirection);
	}
	
	public void hitThenMove(int hitDirection , int moveDirection){
		this.centerControl.hitThenMove(hitDirection, moveDirection);
	}
	// UP :  hit 1 move 5
	// DOWN : hit 3 move 7
	//RIGHT :  hit 2 move 6
	//LEFT : hit 6 move 8
	
	public void takeActionFirst(int actionCode){
		System.err.println("ActionCode" + actionCode);
		if(actionCode <= 3){
			this.current_Cost += 4;
		}else{
			this.current_Cost += 6;
		}

		switch (actionCode) {
		case 0:
			//direct zero : hit 4 move 8
			//direction one : hit 2 move 6
			//direct two : hit 1 move 5
			//direct three : hit 3 move 7
			//direct four : move  8 hit 1
			this.occupy(4);
			this.moveOneStep(8);
			break;
		case 1:
			this.occupy(2);
			this.moveOneStep(6);
			break;
		case 2:
			this.occupy(1);
			this.moveOneStep(5);
			break;
		case 3:
			this.occupy(3);
			this.moveOneStep(7);
			break;
		case 4:
			//direct four : move  8 hit 1
			//direct five : move 8 hit 3
			//direct six : move 6 hit 1
			//direct seven : move 6 hit 3
			this.moveThenHit(8, 1);
			break;
		case 5:
			this.moveThenHit(8, 3);
			break;
		case 6:
			this.moveThenHit(6, 1);	
			break;
		case 7:
			this.moveThenHit(6, 3);
			break;
		case 8:
			//direct 8 : move 5 hit 2
			//direct 9 : move 5 hit  4
			//direct ten : move 7 hit 2;
			//direct eleven : move 7 hit 4;
			this.moveThenHit(5, 2);
			break;
		case 9:
			this.moveThenHit(5, 4);
			break;
		case 10:
			this.moveThenHit(7, 2);
			break;
		case 11:
			this.moveThenHit(7, 4);
			break;
		case 12:
			//direct twelve : move 6 hit 4
			//direct thirteen : move 8 hit 2
			//direct fourteen : move 7 hit 1
			//direct fifteen : move 5 hit 3
			//direct 16 : move 5 , 5 , 8
			//direct 17 : move 8 , 8 , 7
			//direct 18 : move 6 , 6 , 5
			//direct 19 : move 7 , 7 , 6
			this.moveThenHit(6, 4);
			break;
		case 13:
			this.moveThenHit(8, 2);
			break;
		case 14:
			this.moveThenHit(7, 1);
			break;
		case 15:
			this.moveThenHit(5, 3);
			break;
			//direct zero : hit 4 move 8
			//direction one : hit 2 move 6
			//direct two : hit 1 move 5
			//direct three : hit 3 move 7
		case 16:
			this.moveThenHit(8, 4);
			break;
		case 17:
			this.moveThenHit(6, 2);
			break;
		case 18:
			this.moveThenHit(5, 1);
			break;
		case 19:
			this.moveThenHit(7, 3);
			break;
		case 20:
			/**
			 * 20:左下 
			 * 21:左上
			 * 22:右下
			 * 23:右上
			 */
			this.moveThreeStep(5, 5, 8);
			break;
		case 21:
			this.moveThreeStep(8, 8, 7);
			break;
		case 22:
			this.moveThreeStep(6, 6, 5);
			break;
		case 23:
			this.moveThreeStep(7, 7, 6);
			break;
		default:
			break;
		}
		
			if(this.canHide()){
				this.samuraiInfo.hide();
			}
		
		
	}
	
	public void takeActionSecond(int direction){
		System.err.println("ActionTaken" + direction);
		// UP :  hit 1 move 5
		// DOWN : hit 3 move 7
		//RIGHT :  hit 2 move 6
		//LEFT : hit 4 move 8
		switch (direction) {
		case Player.UP_SIDE:
			this.occupy(1);
			this.moveOneStep(5);
			break;
		case Player.LEFT_SIDE:
			this.occupy(4);
			this.moveOneStep(8);
			break;
		case Player.RIGHT_SIDE:
			this.occupy(2);
			this.moveOneStep(6);
			break;
		case Player.DOWN_SIDE:
			this.occupy(3);
			this.moveOneStep(7);
		default:
			break;
		}
		if(this.current_Cost < 7){
			if(this.canHide()){
				this.samuraiInfo.hide();
			}
		}
	}
	
	
	public void justMove(){
		if(canHide()){
			hide();
			current_Cost++;
		}
		
		if(curX == this.samuraiInfo.width >> 1 && curY == this.samuraiInfo.height >> 1){
			takeActionFirst(20 + random.nextInt(4));
			/**
			 * 20:左下 
			 * 21:左上
			 * 22:右下
			 * 23:右上
			 */
		}else if(curX < this.samuraiInfo.width >> 1 && curY < this.samuraiInfo.height >> 1){
			if(curX == 0 && (curY == 3|| curY == 4)){
				moveThreeStep(6, 6, 5);
			}else{
				moveThreeStep(5, 5, 6);
			}
		}else if(curX < this.samuraiInfo.width >> 1 && curY > this.samuraiInfo.height >> 1){
			moveThreeStep(6, 6, 7);
		}else if(curX > this.samuraiInfo.width >> 1 && curY < this.samuraiInfo.height >> 1){
			moveThreeStep(8, 8, 5);
		}else if(curX > this.samuraiInfo.width >> 1 && curY > this.samuraiInfo.height >> 1){
			if(curX == 14 && (curY == 10 || curY == 11)){
				moveThreeStep(8, 8, 7);
			}else{
				moveThreeStep(7,7 , 8);
			}
		}else if(curX == this.samuraiInfo.width >> 1 && curY < this.samuraiInfo.height >> 1){
			switch (random.nextInt(3)) {
		case 0:
			moveThreeStep(7, 7, 7);
			break;
		case 1:
			moveThreeStep(7, 7, 8);
			break;
		case 2:
			moveThreeStep(7, 7, 6);
			break;
		default:
			break;
		}
			current_Cost += 6;
		}else if(curX == this.samuraiInfo.width >> 1 && curY > this.samuraiInfo.height >> 1){
			switch (random.nextInt(3)) {
			case 0:
				moveThreeStep(5, 5, 5);
				break;
			case 1:
				moveThreeStep(5, 5, 8);
				break;
			case 2:
				moveThreeStep(5, 5, 6);
				break;
			default:
				break;
			}
			current_Cost += 6;
			
		}else if(curX > this.samuraiInfo.width >> 1 && curY == this.samuraiInfo.height >> 1){
			switch (random.nextInt(3)) {
			case 0:
				moveThreeStep(8, 8, 8);
				break;
			case 1:
				moveThreeStep(8, 8, 7);
				break;
			case 2:
				moveThreeStep(8, 8, 5);
				break;
			default:
				break;
			}
			current_Cost += 6;
			
		}else if(curX < this.samuraiInfo.width >> 1 && curY == this.samuraiInfo.height >> 1){
			switch (random.nextInt(3)) {
			case 0:
				moveThreeStep(6, 6, 6);
				break;
			case 1:
				moveThreeStep(6, 6, 7);
				break;
			case 2:
				moveThreeStep(6, 6, 5);
				break;
			default:
				break;
			}
			current_Cost += 6;
			
		}
		
	}
	
	
	public void getMaxIndex(){
		int max = directions[0];
		int count = 1;
		for(int i = 1 ; i < 20 ; i++){
			if(max == directions[i]){
				count ++;
			}else if(max < directions[i]){
				max = directions[i];
				count = 1;
			}
		}
		System.err.println("count =" + count);
		this.indexOfMax = new int[count];
		
		count = 0;
		for(int i = 0 ; i < 20 ; i++){
			if(directions[i] == max){
				indexOfMax[count] = i;
				count++;
			}
		}
		for(int i = 0 ; i < 20 ; i++){
			System.err.println("Direction" + i + "   =   " + directions[i]);
		}
		for(int j = 0 ; j < indexOfMax.length ; j++){
			System.err.println("IndexMax = " + indexOfMax[j]);
		}
	}
	
	/**
	 * 
	 * @param enemyX
	 * @param enemyY
	 * @param weapon
	 * @return
	 */
	public int checkDirectionOFEnemy(int enemyX, int enemyY){
		int offsetX = enemyX - curX;
		int offsetY = enemyY - curY;
		/**
		 *@Warning ：  如果是碰到对面的剑士。Be careful
		 */
				return judgeDirection(offsetX, offsetY);
	}
	
	public int judgeDirection(int offsetX , int offsetY){
		if(offsetX == 0){
			return offsetY > 0 ? Player.UP_SIDE  : Player.DOWN_SIDE;
	 	}else if(offsetY == 0){
	 		return offsetX > 0 ? Player.RIGHT_SIDE : Player.LEFT_SIDE;
	 	}else if(offsetX > 0){
	 		return offsetY > 0 ? (offsetY <= offsetX ? Player.RIGHT_SIDE: Player.UP_SIDE) : (offsetY > -offsetX ? Player.RIGHT_SIDE : Player.DOWN_SIDE);
	 	}else{
	 		return offsetY > 0 ? (offsetY > -offsetX ? Player.UP_SIDE : Player.LEFT_SIDE) : (offsetY > offsetX ? Player.LEFT_SIDE : Player.DOWN_SIDE);
	 	}
	}
	// UP :  hit 1 move 5
	// DOWN : hit 3 move 7
	//RIGHT :  hit 2 move 6
	//LEFT : hit 6 move 8
	
}
