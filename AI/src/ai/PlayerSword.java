package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import data.ActionInfo;
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
	private ArrayList<int[]> enemiesCanKill = new ArrayList<int[]>(3);
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
	
	private int curX ;
	
	private int curY;
	
	@Override
	public void play() {
		
		curX = this.samuraiInfo.curX;
		curY = this.samuraiInfo.curY;
		/**
		 * 可能需要分析上一步的对局信息。
		 * 这里先不考虑上一步的时候的对局
		 */
		if(this.enemiesNum > 0){
			this.searchThenKill();
			/**
			 * 执行对峙分析
			 */
		}else if(! this.placeWaitingToOccupy.isEmpty()){
			this.occupyField();
		}else {
			this.justMove();
		}
		//@Notice : 如果不能杀的话一定要采取行动。否则后果很难看。
	}
	
	public void searchThenKill(){
		boolean mark = false;
		
		for(int i =0 ; i < enemiesNum ; i++){
			int[] each = enemyInOwnEyes.get(i);
			if(CanKillOne(each)){
				this.enemiesCanKill.add(each);
				if(each[0] == curX && each[1] == curY){
					mark = true;
				}
			}
		}
		//TODO 如果当前站的位置有敌军Be careful
		
		if(enemiesCanKill.size() == 0){
			//在边角。冒险approach吧。
			if(this.samuraiInfo.hidden == 0){
				this.occupyField();
			}else{
				//留在原地。不好出手。
				if(this.canShow()){
					this.show();
					//搜寻四个方向。
					int[] fourDirect = new int[4];
					for(int[] each : this.placeWaitingToOccupy){
						if(each[0] == curX){
							if(each[1] < curY){
								fourDirect[2]++;
							}else {
								fourDirect[3]++;
							}
						}else if(each[1] == curY){
							if(each[0] < curX){
								fourDirect[0]++;
							}else{
								fourDirect[1]++;
							}
						}
					}
					 int max = 0;
					ArrayList<Integer> indexes = new ArrayList<Integer>();
					for(int i = 0 ; i < 4 ; i++){
						if(fourDirect[i] > max){
							max = fourDirect[i];
							indexes.clear();
							indexes.add(i);
						}else if(fourDirect[i] == max){
							indexes.add(i);
						}
					}
					if(max == 0){
						int moveDirect = random.nextInt(4) + 5 ;
						moveTwoStep(moveDirect, moveDirect);
					}else{
						int act = random.nextInt(4) + 1;
						show();
						occupy(act);
						hide();
					}
				}else{
					justMove();
				}
			}
		}else{
			
			if(this.samuraiInfo.hidden == 1){
				if(! this.canShow()){
					int[] blocksAround = new int[4];
					Arrays.fill(blocksAround, -1);
					if(! this.samuraiInfo.checkOutOfField(curX - 1, curY)){
						if(this.gameInfo.field[curX - 1][curY] < 3){
							blocksAround[0] = this.gameInfo.field[curX - 1][curY];
						}
					}
					if(! this.samuraiInfo.checkOutOfField(curX + 1, curY)){
						if(this.gameInfo.field[curX + 1][curY] < 3){
							blocksAround[1] = this.gameInfo.field[curX - 1][curY];
						}
					}
					if(! this.samuraiInfo.checkOutOfField(curX, curY - 1)){
						if(this.gameInfo.field[curX][curY - 1] < 3){
							blocksAround[2] = this.gameInfo.field[curX][curY - 1];
						}
					}
					if(! this.samuraiInfo.checkOutOfField(curX, curY + 1)){
						if(this.gameInfo.field[curX][curY] < 3){
							blocksAround[3] = this.gameInfo.field[curX][curY + 1];
						}
					}
					if(mark){
						//这情况就非常严重的。
						//说明别人就站在你头上。
						//检查是否有可以跑的地方。如果没有。嗯，只能够等死了。如果有的话。好，杀了他把。
						checkAndKill();
					}
					else{
						//自己人
						leaveAndKill();
					}
					
				}
			}else{
				this.placeWaitingToOccupy.clear();
				this.placeWaitingToOccupy.addAll(this.enemyInOwnEyes);
				this.markFieldOnOwn = mark;
				this.occupyField();
			}
			
		}
	}
	
	public void checkAndKill(){
		ArrayList<Integer> canMoveDirect = new ArrayList<Integer>(4);
		if(canMoveOneStep(5)){
			canMoveDirect.add(5);
		}
		if(canMoveOneStep(6)){
			canMoveDirect.add(6);
		}
		if(canMoveOneStep(7)){
			canMoveDirect.add(7);
		}
		if(canMoveOneStep(8)){
			canMoveDirect.add(8);
		}
		int max = 0;
		int maxIndex1 = 0;
		int maxIndex2 = 0;
		if(canMoveDirect.size() > 0){
			
			int[][] maxNum = new int[canMoveDirect.size()][4];
			for(int i = 0 ; i < canMoveDirect.size() ; i ++){
				maxNum[i] = howManyEnemies(canMoveDirect.get(i));
				for(int j = 0 ; j < 4 ; j ++){
					if(maxNum[i][j] > max){
						maxIndex1 = i;
						maxIndex2 = j;
					}
				}
			}
//			moveOneStep(canMoveDirect.get(0));
//			curX = this.samuraiInfo.curX;
//			curY = this.samuraiInfo.curY;
//			if(howManyEnemies(canMoveDirect.get(0) - 4) != 0){
//				show();
//				occupy(canMoveDirect.get(0) - 4);
//			}else{
//				
//			}
			
		}
		//等死吧
	}
	
	public int[] howManyEnemies(int moveDirection){
		int curX = 0;
		int curY = 0;
		curX = this.curX + ActionInfo.MOVE_OFFSET[moveDirection - 5][0];
		curY = this.curY + ActionInfo.MOVE_OFFSET[moveDirection - 5][1];
		int[] count = new int[4];
		for(int[] each : this.enemiesCanKill){
//		switch (moveDirection) {
//		case 1:
			if(each[0] < curX && each[1] == curY){
				count[0]++;
			}
//			break;
//		case 2:
			else if(each[0] > curX && each[1] == curY){
				count[1]++; 
			}
//			break;
//		case 3:
			else if(each[0] == curX && each[1] < curY){
				count[2]++;
			}
//			break;	
//		case 4:
			else if(each[0] == curX && each[1] > curY){
				count[3]++;
			}
//			break;
//		default:
//			break;
//		}
		}
		return count;
	}
	
	public void leaveAndKill(){
		
	}
	
	public void justMove(){
		/**
		 * 当前没有任何敌军。
		 * 可以往前走。但是注意不要走到有敌军的地方。
		 * 需要考虑友军那边的敌军
		 * 对于Sword。攻击范围比较长。所以可以往友军那边走。
		 * TODO：  如果友军那边没有敌军。则待定。
		 * //TODO : 添加计算行动方向的方法。此方法可以置于Player中。因为其他武士也会需要。
		 */
		//根据敌军数目来确定行动。
		if(canHide()){
			hide();
			current_Cost++;
		}
		
		if(curX == this.samuraiInfo.width >> 1 && curY == this.samuraiInfo.height >> 1){
			takeActionFirst(16 + random.nextInt(4));
		}else if(curX < this.samuraiInfo.width >> 1 && curY < this.samuraiInfo.height >> 1){
			takeActionFirst(18);
		}else if(curX < this.samuraiInfo.width >> 1 && curY > this.samuraiInfo.height >> 1){
			takeActionFirst(19);
		}else if(curX > this.samuraiInfo.width >> 1 && curY < this.samuraiInfo.height >> 1){
			takeActionFirst(16);
		}else if(curX > this.samuraiInfo.width >> 1 && curY > this.samuraiInfo.height >> 1){
			takeActionFirst(17);
		}else if(curX == this.samuraiInfo.width >> 1 && curY < this.samuraiInfo.height >> 1){
			switch (random.nextInt(3)) {
			case 0:
				moveThreeStep(7, 7, 7);
				break;
			case 1:
				moveThreeStep(7, 7, 5);
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
				moveThreeStep(8, 8, 8);
				break;
			case 1:
				moveThreeStep(8, 8, 5);
				break;
			case 2:
				moveThreeStep(8, 8, 6);
				break;
			default:
				break;
			}
			current_Cost += 6;
			
		}else if(curX > this.samuraiInfo.width >> 1 && curY == this.samuraiInfo.height >> 1){
			switch (random.nextInt(3)) {
			case 0:
				moveThreeStep(5, 5, 5);
				break;
			case 1:
				moveThreeStep(5, 5, 7);
				break;
			case 2:
				moveThreeStep(5, 5, 8);
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
				moveThreeStep(6, 6, 8);
				break;
			default:
				break;
			}
			current_Cost += 6;
			
		}
		
	}
	
	/**
	 * TODO : 忘记添加边上是否有友军的判断。
	 */
	public void occupyField(){
		//将Directions以0填充。
		if(this.markFieldOnOwn){
			this.directions = new int[20];
		}else{
			this.directions = new int[16];
		}
		Arrays.fill(directions, 0);
		
		this.census();
		//TODO : 这里的数组遍历有可能是错误的。//如果两个数组的大小完全相同。这就说明可能每个都是0.需要添加判断。
		if(this.indexOfMax.length == this.directions.length){
			//完全有可能。此时应该采取相应行为。
			if(this.directions[0] == 0){
				int[] zeroDirections = new int[placeWaitingToOccupy.size()];
				int count = 0;
				for(int i = directions.length - 4 ; i < directions.length ; i++){
					if(directions[i] != 0){
						zeroDirections[count] = i;
					}
				}
				int actionCode = zeroDirections[random.nextInt(zeroDirections.length)];
				if(directions.length == 12){
					takeActionFirst(actionCode + 4);
				}else{
					takeActionFirst(actionCode);
				}
				
			}else{
				if(this.samuraiInfo.hidden == 1){
					this.show();
					this.current_Cost++;
				}
				// 说明每个方向都是一样的。
				//向人多的一占领
				int othersNum = this.otherEnemies.size();
				if(othersNum != 0){
					// 敌军所在方向。
					int[] direction = new int[othersNum];
					int[] distance = new int[othersNum];
					Arrays.fill(distance, 0);
					for(int i = 0 ; i < othersNum ; i++){
						SamuraiInfo eachInfo = otherEnemies.get(i);
						//每个友军视野中的敌军的方向。
						direction[i] = checkDirectionOFEnemy(eachInfo.curX , eachInfo.curY , eachInfo.weapon);
						distance[i] = Math.abs(eachInfo.curX - this.samuraiInfo.curX) + Math.abs(eachInfo.curY - this.samuraiInfo.curY);
					}
					//TODO
					int minDistance = this.samuraiInfo.width + this.samuraiInfo.height;
					int indexInDirection = 0;
//					if(count == 0){
//						takeActionFirst(random.nextInt(16));
//					}else if(count == 1){
//						for(int i = 0 ; i < distance.length ; i ++){
//							if(distance[i] != 0){
//								indexInDirection = i;
//							}
//						}
					if(othersNum == 1){
						takeActionSecond(direction[indexInDirection]);
					}
//					else if(enemiesNum == 2){
//						for(int i = 0 ; i < distance.length ; i++){
//							if(distance[i] != 0){
//								if(minDistance > distance[i]){
//									minDistance = distance[i];
//									indexInDirection = i;
//								}
//							}
//						}
//						takeActionSecond(direction[indexInDirection]);
//					}
					else{
						ArrayList<Integer> manyEnemies = new ArrayList<Integer>(3);
						for(int i = 0 ; i < distance.length ; i++){
							if(distance[i] != 0){
								if(minDistance > distance[i]){
									minDistance = distance[i];
									manyEnemies.add(i);
									manyEnemies.clear();
								}else{
									manyEnemies.add(i);
								}
							}
						}
						takeActionSecond(direction[manyEnemies.get(random.nextInt(manyEnemies.size()))]);
					}
					
				}else{
					takeActionFirst(random.nextInt(16));
				}
			}
	
		}else{
			if(this.samuraiInfo.hidden == 1){
				this.show();
				this.current_Cost++;
			}
			int i = indexOfMax.length - 1;
			if(i == 0){
				takeActionFirst(this.indexOfMax[0]);
			}else{
				for(; i > 0 ; i--){
					if(directions[indexOfMax[i]] != directions[indexOfMax[i+1]]){
						i++;
						break;	
					}
				}
				int actionCodeField = indexOfMax.length- i;
				takeActionFirst(i + random.nextInt(actionCodeField));
			}
		}
		//Move to where there exist enemy.
		
	}
	
	public void takeActionSecond(int direction){
		switch (direction) {
		case Player.UP_SIDE:
			this.occupy(3);
			this.moveOneStep(7);
			break;
		case Player.LEFT_SIDE:
			this.occupy(1);
			this.moveOneStep(5);
			break;
		case Player.RIGHT_SIDE:
			this.occupy(2);
			this.moveOneStep(6);
			break;
		case Player.DOWN_SIDE:
			this.occupy(4);
			this.moveOneStep(8);
		default:
			break;
		}
		if(this.current_Cost < 7){
			if(this.canHide()){
				this.samuraiInfo.hide();
			}
		}
	}
	
	/**
	 * 
	 * @param enemyX
	 * @param enemyY
	 * @param weapon
	 * @return
	 */
	public int checkDirectionOFEnemy(int enemyX, int enemyY, int weapon){
		int offsetX = enemyX - this.samuraiInfo.curX;
		int offsetY = enemyY - this.samuraiInfo.curY;
		/**
		 *@Warning ：  如果是碰到对面的剑士。Be careful
		 */
				return judgeDirection(offsetX, offsetY);
	}
	
	public int judgeDirection(int offsetX , int offsetY){
		if(offsetX == 0){
			return offsetY > 0 ? Player.DOWN_SIDE  : Player.UP_SIDE;
	 	}else if(offsetY == 0){
	 		return offsetX > 0 ? Player.RIGHT_SIDE : Player.LEFT_SIDE;
	 	}else if(offsetX > 0){
	 		return offsetY > 0 ? (offsetY <= offsetX ? Player.RIGHT_SIDE: Player.UP_SIDE) : (offsetY > -offsetX ? Player.RIGHT_SIDE : Player.DOWN_SIDE);
	 	}else{
	 		return offsetY > 0 ? (offsetY > -offsetX ? Player.UP_SIDE : Player.LEFT_SIDE) : (offsetY > offsetX ? Player.LEFT_SIDE : Player.DOWN_SIDE);
	 	}
	}
	
	
	public void takeActionFirst(int actionCode){
		if(actionCode <= 3){
			this.current_Cost += 4;
		}else{
			this.current_Cost += 6;
		}

		switch (actionCode) {
		case 0:
			this.occupy(1);
			this.moveOneStep(5);
			break;
		case 1:
			this.occupy(2);
			this.moveOneStep(6);
			break;
		case 2:
			this.occupy(3);
			this.moveOneStep(7);
			break;
		case 3:
			this.occupy(4);
			this.moveOneStep(8);
			break;
		case 4:
			this.moveThenHit(5, 3);
			break;
		case 5:
			this.moveThenHit(5, 4);
			break;
		case 6:
			this.moveThenHit(6, 3);	
			break;
		case 7:
			this.moveThenHit(6, 4);
			break;
		case 8:
			this.moveThenHit(7, 1);
			break;
		case 9:
			this.moveThenHit(7, 2);
			break;
		case 10:
			this.moveThenHit(8, 1);
			break;
		case 11:
			this.moveThenHit(8, 2);
			break;
		case 12:
			this.moveThenHit(6, 1);
			break;
		case 13:
			this.moveThenHit(5, 2);
			break;
		case 14:
			this.moveThenHit(8, 3);
			break;
		case 15:
			this.moveThenHit(7, 4);
			break;
		case 16:
			this.moveThreeStep(5, 5, 7);
			break;
		case 17:
			this.moveThreeStep(5, 5, 8);
			break;
		case 18:
			this.moveThreeStep(6, 6, 7);
			break;
		case 19:
			this.moveThreeStep(6, 6, 8);
			break;
		default:
			break;
		}
		
		if(this.samuraiInfo.hidden == 0 && this.current_Cost < 7){
			if(this.canHide()){
				this.samuraiInfo.hide();
			}
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
		this.indexOfMax = new int[count];
		
		count = 0;
		for(int i = 0 ; i < directions.length ; i++){
			if(directions[i] == max){
				indexOfMax[count] = i;
				count++;
			}
		}
	}
	
	
	public void onOwnField(){

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
						//下右
						this.directions[10]++;
					}else{
						//下左
						this.directions[11]++;
					}
				}else if(offsetX == -2){
					if(offsetY == 2){
						this.directions[16] ++;
					}else if(offsetY == -2){ 
						this.directions[17] ++;
					}
				}else {
					if(offsetY == 2 ){
						this.directions[18] ++;
					}else if(offsetY == -2){
						this.directions[19] ++;
					}
				}
				
			}
	}
	
	public void notOnOwnField(){
		
		
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
				}else if(offsetX == -2){
					if(offsetY == 2){
						this.directions[12] ++;
					}else if(offsetY == -2){ 
						this.directions[13] ++;
					}
				}else {
					if(offsetY == 2 ){
						this.directions[14] ++;
					}else if(offsetY == -2){
						this.directions[15] ++;
					}
				}
					
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
