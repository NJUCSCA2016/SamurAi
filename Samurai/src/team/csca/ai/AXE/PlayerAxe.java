package team.csca.ai.AXE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import team.csca.ai.AIdata.ActionInfo;
import team.csca.ai.AIdata.SamuraiInfo;

public class PlayerAxe extends Player{
	
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
	public ArrayList<Integer> weapons = new ArrayList<Integer>(3);
	public int enemiesNum = 0;
	
//"java -cp ${java_classpath} Main"
	//${cpp_player_dir}/tooActivePlayer
	public boolean markFieldOnOwn = false;

	/**
	 * 12 / 16个方向。
	 */
	public int[] directions;
	
	public int[] indexOfMax;
	
	public Random random = new Random();
		
	public int curX =0;
	
	public int curY=0;
	
	@Override
	public void play() {
		this.current_Cost = 0;
		this.enemiesCanKill.clear();
		curX = this.samuraiInfo.curX;
		curY = this.samuraiInfo.curY;

		if(this.enemiesNum > 0){
			System.err.println("Kill");
			this.searchThenKill();
			/**
			 * 执行对峙分析
			 */
		}
		else if(! this.placeWaitingToOccupy.isEmpty()){
			System.err.println("Occupy");
			this.occupyField();
		}else {
			System.err.println("Move");
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
					//如果是的话那么肯定隐身。
					mark = true;
				}
			}
		}
		//TODO 如果当前站的位置有敌军Be careful
		System.err.println("Enemies size  = " + enemiesCanKill.size());
		if(enemiesCanKill.size() == 0){
			//只有八个角落的执行此操作。
			System.err.println("Watch Them");
					watchThenKill();
		}else{
			//True
			if(this.samuraiInfo.hidden == 1){
				if(! this.canShow()){
					if(mark){
						//这情况就非常严重的。
						//说明别人就站在你头上。
						//检查是否有可以跑的地方。如果没有。嗯，只能够等死了。如果有的话。好，杀了他把。
						checkAndKill(1);
					}
					else{
						//自己人
						checkAndKill(2);
					}
					
				}else{
					//完成
					show();
					this.placeWaitingToOccupy.clear();
					this.placeWaitingToOccupy.addAll(this.enemiesCanKill);
					this.markFieldOnOwn = false;
					this.occupyField();
					System.err.println("Show And Kill");
				}
			}	else{
				//自己这肯定没人。正确
				this.placeWaitingToOccupy.clear();
				this.placeWaitingToOccupy.addAll(this.enemyInOwnEyes);
				this.markFieldOnOwn = false;
				this.occupyField();
				System.err.println("There exit to kill");
			}
			
		}
	}
	
	public void watchThenKill(){
		//搜寻八个方向。
		if(enemiesNum == 1 && weapons.contains(5)){
			int[] axe = enemyInOwnEyes.get(0);
			if(Math.abs(axe[0] - curX) >3 || Math.abs(axe[1] - curY) > 3){
				occupyField();
			}
		}
		if(weapons.contains(3)){
			
		}
			int[] fourDirect = new int[4];
			int[] theOtherFour = new int[4];
			int[] fourInnerDirect = new int[4];
			Arrays.fill(fourDirect, 0);
			Arrays.fill(theOtherFour, 0);
			Arrays.fill(fourInnerDirect, 0);
			
			for(int[] each : this.enemyInOwnEyes){
				int offX = each[0] - curX;
				int offY = each[1] - curY;
				switch (offX) {
				case 2:
					if(offY == 2){
						fourInnerDirect[0]++;
					}else if(offY == 3){
						fourDirect[0]++;
					}else if(offY == -2){
						fourInnerDirect[1]++;
					}else{
						fourDirect[1]++;
					}
					break;
				case 3:
					/**
					 * [0] : X > 0 ; Y > 0
					 * [1] : X > 0 ; y < 0
					 * [2] : X < 0 ; Y > 0
					 * [3] : X < 0 ; Y < 0
					 */
					if(offY > 0){
						theOtherFour[0]++;
					}else{
						theOtherFour[1]++;
					}
					break;
				case -2:
					if(offY == 2){
						fourInnerDirect[2]++;
					}else if(offY == 3){
						fourDirect[2]++;
					}else if(offY == -2){
						fourInnerDirect[3]++;
					}else{
						fourDirect[3]++;
					}
					break;
				case -3:
					if(offY > 0){
						theOtherFour[2]++;
					}else{
						theOtherFour[3]++;
					}
					break;
				default:
					break;
				}
			}
			//内部四个有人？
			boolean takeSafe = true;
			for(int i = 0 ; i < 4 ; i++){
				if(fourInnerDirect[i] != 0){
					takeSafe = false;
				}
			}
			for(int i = 0 ; i < 4 ; i++){
				System.err.println("OutFour  "+ i + "  =  " + theOtherFour[i] );
			}
			for(int i = 0 ; i < 4 ; i++){
				System.err.println("InnerFour  "+ i + "  =  " + fourInnerDirect[i] );
			}
			for(int i = 0 ; i < 4 ; i++){
				System.err.println("DangerFour  "+ i + "  =  " + fourDirect[i] );
			}
			if(takeSafe){
				int[] moveToFourSafePlace = new int[4];
				Arrays.fill(moveToFourSafePlace, 0);
				if((fourDirect[0] != 0 || fourDirect[2] !=0) && (theOtherFour[0] == 0 && theOtherFour[2] == 0)){
					if(fourDirect[0] != 0){
						moveToFourSafePlace[0] ++;
					}
					if(fourDirect[2] != 0){
						moveToFourSafePlace[0]++;
					}
				}
				if((theOtherFour[0] !=0 || theOtherFour[1] !=0) && (fourDirect[0] == 0 && fourDirect[1] ==0))	{
					if(theOtherFour[0] != 0){
						moveToFourSafePlace[1]++;
					}
					if(theOtherFour[1] != 0){
						moveToFourSafePlace[1]++;
					}
				}
				/**
				 * [0] : X > 0 ; Y > 0
				 * [1] : X > 0 ; y < 0
				 * [2] : X < 0 ; Y > 0
				 * [3] : X < 0 ; Y < 0
				 */
				if((fourDirect[1] != 0 || fourDirect[3] !=0) && (theOtherFour[1] == 0 && theOtherFour[3] == 0)){
					if(fourDirect[3] != 0){
						moveToFourSafePlace[2] ++;
					}
					if(fourDirect[1] != 0){
						moveToFourSafePlace[2]++;
					}
				}
				if((theOtherFour[2] !=0 || theOtherFour[3] !=0) && (fourDirect[2] == 0 && fourDirect[3] ==0))	{
					if(theOtherFour[2] != 0){
						moveToFourSafePlace[3]++;
					}
					if(theOtherFour[3] != 0){
						moveToFourSafePlace[3]++;
					}
				}
				int max = 0;
				ArrayList<Integer> maxIndex = new ArrayList<Integer>();
				for(int i = 0 ; i < 4 ; i++){
					if(moveToFourSafePlace[i] >  max){
						max = moveToFourSafePlace[i];
						maxIndex.clear();
						maxIndex.add(i);
					}else if(fourDirect[i] == max){
						maxIndex.add(i);
					}
				}
				
				if(maxIndex.size() == 0){
					if(canShow()){
						show();
					}
					dashasifang();
					hide();
				}else{
					if(canShow()){
						show();
					}
					moveOneStep(5 + maxIndex.get(random.nextInt(maxIndex.size())));
					dashasifang();
					if(canHide()){
						hide();
					}
				}
			}else{
				//大杀四方。然后躲着
				if(canShow()){
					show();
				}
				dashasifang();
				hide();
			}
		
	}
	
	public int dashasifang(){
		
		int[] fourDirect = chasifang();
		
		int max = 0;
		ArrayList<Integer> maxIndex = new ArrayList<Integer>();
		for(int i = 0 ; i < 4 ; i++){
			if(fourDirect[i] >  max){
				max = fourDirect[i];
				maxIndex.clear();
				maxIndex.add(i);
			}else if(fourDirect[i] == max){
				maxIndex.add(i);
			}
		}
		System.err.println("Max == " + maxIndex);
		if(max != 0){
			int direct = maxIndex.get(random.nextInt(maxIndex.size()));
			occupy(1 + direct);
			return direct;
		}
		return -1;
		
	}
	
	public int[] chasifang(){
		int[] fourDirect = new int[4];
		Arrays.fill(fourDirect, 0);
		for(int[] each : placeWaitingToOccupy){
			int offX = each[0] - curX;
			int offY = each[1] - curY;
			if(offY > 0 && offY < 5 && offX == 0){
				fourDirect[0] ++;
			}else if(offY == 0 && offX > 0 && offX < 5){
				fourDirect[1] ++;
			}else if(offX == 0 &&  offY < 0 && offY > -5){
				fourDirect[2] ++ ;
			}else if(offY == 0 && offX < 0 && offX > -5){
				fourDirect[3] ++;
			}
		}
		return fourDirect;
	}

	
	/**
	 * @param model Model One . Yourself is hidden and enemy with you
	 */
	public void checkAndKill(int model){
		ArrayList<Integer> canMoveDirect = new ArrayList<Integer>(4);

		for(int i = 5 ; i <= 8 ; i++){
			if(canMoveOneStep(i)){
				canMoveDirect.add(i);
			}
		}
		//最大敌军数目方向
		if(canMoveDirect.size() > 0){
			int max = 0;
			//List to store the coordinations of enemies
			List<int[]> maxIndex = new ArrayList<int[]>(3);
			
			int[][] maxNum = new int[canMoveDirect.size()][4];
			
			for(int i = 0 ; i < canMoveDirect.size() ; i ++){
				//Judge the max number of enemies
				maxNum[i] = howManyEnemies(canMoveDirect.get(i));
				for(int j = 0 ; j < 4 ; j ++){
					if(maxNum[i][j] > max){
						max = maxNum[i][j];
						maxIndex.clear();
						maxIndex.add(new int[]{i , j});
					}else if(maxNum[i][j] == max){
						maxIndex.add(new int[]{i , j});
					}
				}
			}
//			In this method , you've already known that you're hidden.
			if(model == 2 && max == 0){
//No one can be kill ;
//	This is impossible But I add two situation in it.
//			Only one step is always the most safe.
//				if(canMoveDirect.size() == 1){
//					moveOneStep(canMoveDirect.get(0));
//					show();
//				}else{
//					
//				}
				moveOneStep(canMoveDirect.get(random.nextInt(canMoveDirect.size())));
				
			}else{
				//随机杀一个人最多的
				int[] getRandomAct = maxIndex.get(random.nextInt(maxIndex.size()));
				moveOneStep(canMoveDirect.get(getRandomAct[0]));
				show();
				occupy(getRandomAct[1] + 1);
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
		
		int curX = this.curX + ActionInfo.MOVE_OFFSET[moveDirection - 5][0];
		int curY = this.curY + ActionInfo.MOVE_OFFSET[moveDirection - 5][1];
		
		int[] count = new int[4];
		Arrays.fill(count, 0);
		for(int[] each : this.enemiesCanKill){
			int offsetX = each[0] - curX;
			int offsetY =  each[1] - curY;
			if(Math.abs(offsetX) == 1 && Math.abs(offsetY) == 1){
				for(int i = 0 ; i < 4 ; i++){
					count[i]++;
				}
			}else if (offsetX == 0 && offsetY == 1) {
				count[0]++;
			}else if (offsetX == 0 && offsetY == -1) {
				count[2]++;
			}else if (offsetX == 1 && offsetY == 0) {
				count[1]++;
			}else if (offsetX == -1 && offsetY == 0) {
				count[3] ++;
			}
		}
		return count;
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
			takeActionFirst(20 + random.nextInt(4));
			/**
			 * 20:左下 
			 * 21:左上
			 * 22:右下
			 * 23:右上
			 */
		}else if(curX < (this.samuraiInfo.width >> 1) && curY < (this.samuraiInfo.height >> 1)){
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
		}else if(curX == (this.samuraiInfo.width >> 1) && curY < (this.samuraiInfo.height >> 1)){
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
	
	/**
	 * TODO : 忘记添加边上是否有友军的判断。
	 */
	public void occupyField(){
		
			this.directions = new int[24];
			Arrays.fill(directions, 0);
			
			this.census();
			//TODO : 这里的数组遍历有可能是错误的。//如果两个数组的大小完全相同。这就说明可能每个都是0.需要添加判断。
			
			if(this.indexOfMax.length == 20){
				
				//完全有可能。此时应该采取相应行为。
				if(this.directions[0] == 0){
					int[] zeroDirections = new int[placeWaitingToOccupy.size()];
					int count = 0;
					for(int i = directions.length - 4 ; i < directions.length ; i++){
						if(directions[i] != 0){
							zeroDirections[count] = i;
							count ++ ;
						}
					}
					int actionCode = zeroDirections[random.nextInt(zeroDirections.length)];
					takeActionFirst(actionCode);
				}else{
					if(canShow()){
						show();
						current_Cost ++ ;
					}
//					dashasifang();
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
							direction[i] = checkDirectionOFEnemy(eachInfo.curX , eachInfo.curY);
							distance[i] = Math.abs(eachInfo.curX - this.samuraiInfo.curX) + Math.abs(eachInfo.curY - this.samuraiInfo.curY);
						}
						//TODO 
						int minDistance = this.samuraiInfo.width + this.samuraiInfo.height;
						int indexInDirection = 0;
						if(othersNum == 1){
							takeActionSecond(direction[indexInDirection]);
						}else{
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
						takeActionFirst(random.nextInt(20));
					}
					if(canHide()){
						hide();
					}
				}
		
			}else{
				if(this.samuraiInfo.hidden == 1){
					this.show();
					this.current_Cost++;
				}
				System.err.println("Max length = " + indexOfMax.length);
				int i = indexOfMax.length;
				if(i == 1){
					takeActionFirst(this.indexOfMax[0]);
					System.err.println("Action1" + this.indexOfMax[0]);
				}else{
					int randomAc = random.nextInt(i);
					
					System.err.println("Action2 " + randomAc);
					
					takeActionFirst(indexOfMax[randomAc]);
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

	
	/**
	 * 
	 * 统计方法： 统计并确定占领位置最多的Greedy判断。
	 * 
	 */
	public void census(){
//		if(this.markFieldOnOwn){
			System.err.println("True");
			this.onOwnField();
//		}else{
//			this.notOnOwnField();
//			System.err.println("False");
//		}	
		getMaxIndex();
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
	
	//No error
	public void onOwnField(){

		if(this.markFieldOnOwn){
			directions[8]++;
			directions[9]++;
			directions[15]++;
			directions[6]++;
			directions[7]++;
			directions[12]++;
			directions[10]++;
			directions[14]++;
			directions[11]++;
			directions[4]++;
			directions[5]++;
			directions[13]++;
		}
		//TODO Add four situation
			/**
			 * 自己这里没被占了
			 */
//		System.err.println("Nums + " + placeWaitingToOccupy.size());
			for(int[] eachBlock : this.placeWaitingToOccupy){
				int blockX = eachBlock[0];
				int blockY = eachBlock[1];
			
				int offsetX = blockX - curX;
				int offsetY = blockY - curY;
				System.err.print("X = " +blockX+ "   Y =" + blockY);
				
				switch (offsetX) {
				case -2:
					if(offsetY == 1 || offsetY == -1){
						directions[4]++;
						directions[5]++;
						directions[13]++;
						directions[16]++;
					}else if(offsetY == 0){
						directions[4]++;
						directions[5]++;
						directions[16]++;
					}
					break;
				case -1:
					if(offsetY == 1){
						directions[2]++;
						directions[3]++;
						directions[0]++;
						directions[1]++;
						directions[9]++;
						directions[15]++;
						directions[18]++;
						directions[4]++;
						directions[13]++;
						directions[16]++;
					}else if (offsetY == 0) {
						directions[2]++;
						directions[3]++;
						directions[0]++;
						directions[9]++;
						directions[15]++;
						directions[18]++;
						directions[8]++;
						directions[14]++;
						directions[10]++;
						directions[19]++;
						directions[11]++;
					}else if (offsetY == -1) {
						directions[2]++;
						directions[3]++;
						directions[0]++;
						directions[1]++;
						directions[19]++;
						directions[19]++;
						directions[11]++;
						directions[5]++;
						directions[16]++;
						directions[13]++;
					}else if (offsetY == 2) {
						directions[9]++;
						directions[15]++;
						directions[18]++;
						directions[8]++;
					}else if (offsetY == -2) {
						directions[14]++;
						directions[10]++;
						directions[19]++;
						directions[11]++;
					}
					break;
				case 0:
					if(offsetY == 1){
						directions[2]++;
						directions[0]++;
						directions[1]++;
						directions[4]++;
						directions[5]++;
						directions[6]++;
						directions[7]++;
						directions[12]++;
						directions[13]++;
						directions[16]++;
						directions[17]++;
					}else if (offsetY == 2) {
						directions[18]++;
						directions[8]++;
						directions[9]++;
					}else if (offsetY == -1) {
						directions[3]++;
						directions[0]++;
						directions[1]++;
						directions[4]++;
						directions[5]++;
						directions[6]++;
						directions[7]++;
						directions[12]++;
						directions[13]++;
						directions[16]++;
						directions[17]++;
					}else if (offsetY == 2) {
						directions[10]++;
						directions[19]++;
						directions[11]++;
					}
					break;
				case 1:
					if(offsetY == 0){
						directions[1]++;
						directions[2]++;
						directions[3]++;
						directions[18]++;
						directions[15]++;
						directions[8]++;
						directions[9]++;
						directions[10]++;
						directions[19]++;
						directions[11]++;
						directions[14]++;
					}else if (offsetY == 1) {
						directions[0]++;
						directions[1]++;
						directions[2]++;
						directions[3]++;
						directions[8]++;
						directions[15]++;
						directions[18]++;
						directions[6]++;
						directions[12]++;
						directions[17]++;
					}else if (offsetY == -1) {
						directions[0]++;
						directions[1]++;
						directions[2]++;
						directions[3]++;
						directions[7]++;
						directions[12]++;
						directions[17]++;
						directions[10]++;
						directions[14]++;
						directions[19]++;
					}else if (offsetY == 2) {
						directions[8]++;
						directions[15]++;
						directions[18]++;
						directions[9]++;
					}else if (offsetY == -2) {
						directions[10]++;
						directions[14]++;
						directions[19]++;
						directions[11]++;
					}
					break;
				case 2:
					if(offsetY == 1 || offsetY == -1){
						directions[6]++;
						directions[7]++;
						directions[12]++;
						directions[17]++;
					}else if(offsetY == 0){
						directions[6]++;
						directions[7]++;
						directions[17]++;
					}
					break;
				
				default:
					break;
				}
				if(Math.abs(offsetY) + Math.abs(offsetX) >= 4){
					if(offsetX >= 0 && offsetY >0){
						directions[22]++;
					}else if (offsetX > 0 && offsetY<=0) {
						directions[23]++;
					}else if (offsetX<0 && offsetY >= 0) {
						directions[20]++;
					}else{
						directions[21]++;
					}
				}else if (offsetX == 0 && offsetY == 3) {
					directions[22]++;
				}else if (offsetX == 3 && offsetY == 0) {
					directions[23]++;
				}else if (offsetX == 0 && offsetY == -3) {
					directions[21]++;
				}else if (offsetX == -3 && offsetY == 0) {
					directions[20]++;
				}
			}
	}

	public boolean CanKillOne(int[] only){
		int abX = Math.abs(only[0] - curX);
		int abY = Math.abs(only[1] - curY);
		return (abX <= 2 && abY <= 2) &&(abX != 2 || abY != 2);
	}


}
