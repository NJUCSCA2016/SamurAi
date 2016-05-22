package team.csca.ai.AI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import team.csca.ai.AIdata.ActionInfo;
import team.csca.ai.AIdata.SamuraiInfo;
import team.csca.view.pm.JPanelPM;

public class PlayerSpear extends Player{

	

	private static int FORCE = 1;
	
	@Override
	public void play() {
		this.current_Cost = 0;
//		System.err.println("Cost" + current_Cost);
		this.enemiesCanKill.clear();
		curX = this.samuraiInfo.curX;
		curY = this.samuraiInfo.curY;
//		System.err.println("curX " + curX + "   curY" + curY);
		/** 
		 * 可能需要分析上一步的对局信息。
		 * 这里先不考虑上一步的时候的对局
		 */
		
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
			if(canHide()){
				hide();
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
			//正确
			System.err.println("Watch Them");
					watchThenKill();
		}else{
			
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
			//TODO update axe
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
//				int max = 0;
//				ArrayList<Integer> indexes = new ArrayList<Integer>();
//				for(int i = 0 ; i < 4 ; i++){
//					if(fourDirect[i] > max){
//						max = fourDirect[i];
//						indexes.clear();
//						indexes.add(i);
//					}else if(fourDirect[i] == max){
//						indexes.add(i);
//					}
//				}
//				if(max == 0){
//					// No where to occupy TODO
//					this.show();
//					int moveDirect = random.nextInt(4) + 5 ;
//					moveTwoStep(moveDirect, moveDirect);
//					if(canHide()){
//						this.hide();
//					}
//				}else{			
//					//移一步。等着。
//					int act = random.nextInt(indexes.size());
//					show();
//					occupy(indexes.get(act) + 1);
//					hide();
//				}
			
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
			if(canShow()){show();}
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
		for(int[] each : this.enemiesCanKill){
			int offsetX = each[0] - curX;
			int offsetY =  each[1] - curY;
//		switch (moveDirection) {
//		case 1:
			if(each[0] < curX && each[1] == curY &&  offsetX >= -4){
				count[3]++;
			}
//			break;
//		case 2:
			else if(each[0] > curX && each[1] == curY&& offsetX <= 4){
				count[1]++; 
			}
//			break;
//		case 3:
			else if(each[0] == curX && each[1] < curY && offsetY >= -4 ){
				count[2]++;
			}
//			break;	
//		case 4:
			else if(each[0] == curX && each[1] > curY && offsetY <= 4 ){
				count[0]++;
			}
//			break;
//		default:
//			break;
//		}
		}
		return count;
	}
	
//	public void leaveAndKill(){
//		
//	}

	
	/**
	 * TODO : 忘记添加边上是否有友军的判断。
	 */
	public void occupyField(){
			
		int[] four = chasifang();
		boolean sifang = false;
		for(int i = 0 ; i < 4 ; i++){
			if(four[i] == 4){
				sifang = true;
			}
		}
			if(sifang){
				if(canShow()){
					show();
				}
				dashasifang();
				if(canHide()){
					hide();
				}
			}else{
			
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
//						if(count == 0){
//							takeActionFirst(random.nextInt(16));
//						}else if(count == 1){
//							for(int i = 0 ; i < distance.length ; i ++){
//								if(distance[i] != 0){
//									indexInDirection = i;
//								}
//							}
						if(othersNum == 1){
							takeActionSecond(direction[indexInDirection]);
						}
//						else if(enemiesNum == 2){
//							for(int i = 0 ; i < distance.length ; i++){
//								if(distance[i] != 0){
//									if(minDistance > distance[i]){
//										minDistance = distance[i];
//										indexInDirection = i;
//									}
//								}
//							}
//							takeActionSecond(direction[indexInDirection]);
//						}
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
			//Move to where there exist enemy.
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
	
	//No error
	public void onOwnField(){

		if(this.markFieldOnOwn){
			for(int i = 12 ; i < 16 ; i++){
				directions[i]++;
			}
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
				
				if(offsetY == 0){
					if(offsetX < 0){
						//左
						if(offsetX > -5){
							this.directions[0]++;
						}
						if(offsetX < -1){
							this.directions[16]++;
						}
						if(offsetX > -4){
							//右移后左击
							this.directions[12]++;
						}
					}else if(offsetX > 0){
						//右
						if(offsetX < 5){
							this.directions[1]++;
						}
						if(offsetX > 1){
							this.directions[17]++;
						}
						if(offsetX < 4){
							//左移后右击
							this.directions[13]++;
						}
					}
					//direct zero : hit 4 move 8
					//direction one : hit 2 move 6
					//direct twelve : move 6 hit 4
					//direct thirteen : move 8 hit 2
				}else if(offsetX == 0){
					if(offsetY > 0){
						//上
						if(offsetY < 5){
							this.directions[2]++;
						}
						if(offsetY > 1){
							this.directions[18]++;
						}
						if(offsetY < 4){
							//下移后上击
							this.directions[14]++;
						}
					}else if(offsetY < 0){
						//下
						if(offsetY > -5){
							this.directions[3]++;
						}
						if(offsetY < -1){
							this.directions[19]++;
						}
						if(offsetY > -4){
							//上移后下击。
							this.directions[15]++;
						}
					}
				}else if(offsetX == -1){
					if(offsetY > 0){
						//左上
						this.directions[4]++;
					}else if(offsetY < 0){
						//左下
						this.directions[5]++;
					}
				}else if(offsetX == 1){
					if(offsetY > 0){
						//右上
						this.directions[6]++;
					}else if(offsetY < 0){
						//右下
						this.directions[7]++;
					}
					
				}else if (offsetY == 1) {
					if(offsetX > 0){
						//上右
						this.directions[8]++;
					}else if(offsetX < 0){
						//上左
						this.directions[9]++;
					}
					//direct zero : hit 4 move 8
					//direction one : hit 2 move 6
					//direct two : hit 1 move 5
					//direct three : hit 3 move 7
					//direct four : move  8 hit 1
					//direct five : move 8 hit 3
					//direct six : move 6 hit 1
					//direct seven : move 6 hit 3
					//direct 8 : move 5 hit 2
					//direct 9 : move 5 hit  4
					//direct twelve : move 6 hit 4
					//direct thirteen : move 8 hit 2
					//direct fourteen : move 7 hit 1
					//direct fifteen : move 5 hit 3
					
				}else if(offsetY == -1){
					if(offsetX > 0){
						//下右
						this.directions[10]++;
					}else if(offsetX < 0){
						//下左
						this.directions[11]++;
					}
					//direct zero : hit 4 move 8
					//direction one : hit 2 move 6
					//direct two : hit 1 move 5
					//direct three : hit 3 move 7
					//direct four : move  8 hit 1
					//direct five : move 8 hit 3
					//direct six : move 6 hit 1
					//direct seven : move 6 hit 3
					//direct 8 : move 5 hit 2
					//direct 9 : move 5 hit  4
					//direct ten : move 7 hit 2;
					//direct eleven : move 7 hit 4;
					//direct twelve : move 6 hit 4
					//direct thirteen : move 8 hit 2
					//direct fourteen : move 7 hit 1
					//direct fifteen : move 5 hit 3
				}else if(offsetX <= -2){
					if(offsetY > 0){
						this.directions[20] ++;
					}else if(offsetY < 0){ 
						this.directions[21] ++;
					}
					
				}else {
					if(offsetY > 0 ){
						this.directions[22] ++;
					}else if(offsetY < 0){
						this.directions[23] ++;
					}
				}
				
				//direct zero : hit 4 move 8
				//direction one : hit 2 move 6
				//direct two : hit 1 move 5
				//direct three : hit 3 move 7
				//direct four : move  8 hit 1
				//direct five : move 8 hit 3
				//direct six : move 6 hit 1
				//direct seven : move 6 hit 3
				//direct 8 : move 5 hit 2
				//direct 9 : move 5 hit  4
				//direct ten : move 7 hit 2;
				//direct eleven : move 7 hit 4;
				//direct twelve : move 6 hit 4
				//direct thirteen : move 8 hit 2
				//direct fourteen : move 7 hit 1
				//direct fifteen : move 5 hit 3
				//direct 16 : move 5 , 5 , 8
				//direct 17 : move 8 , 8 , 7
				//direct 18 : move 6 , 6 , 5
				//direct 19 : move 7 , 7 , 6
			}
	}
	
//	public void notOnOwnField(){
//		
//		
//			/**
//			 * 自己这里没被占了
//			 */
//			for(int[] eachBlock : this.placeWaitingToOccupy){
//				int blockX = eachBlock[0];
//				int blockY = eachBlock[1];
//			
//				int offsetX = blockX - curX;
//				int offsetY = blockY - curY;
//				
//				if(offsetY == 0){
//					if(offsetX < 0){
//						//左
//						this.directions[0]++;
//					}else{
//						//右
//						this.directions[1]++;
//					}
//				}else if(offsetX == 0){
//					if(offsetY > 0){
//						//上
//						this.directions[2]++;
//					}else{
//						//下
//						this.directions[3]++;
//					}
//				}else if(offsetX == -1){
//					if(offsetY > 0){
//						//左上
//						this.directions[4]++;
//					}else{
//						//左下
//						this.directions[5]++;
//					}
//				}else if(offsetX == 1){
//					if(offsetY > 0){
//						//右上
//						this.directions[6]++;
//					}else{
//						//右下
//						this.directions[7]++;
//					}
//				}else if (offsetY == 1) {
//					if(offsetX > 0){
//						//上右
//						this.directions[8]++;
//					}else{
//						//上左
//						this.directions[9]++;
//					}
//				}else if(offsetY == -1){
//					if(offsetX > 0){
//						this.directions[10]++;
//					}else{
//						this.directions[11]++;
//					}
//				}else if(offsetX == -2){
//					if(offsetY == 2){
//						this.directions[12] ++;
//					}else if(offsetY == -2){ 
//						this.directions[13] ++;
//					}
//				}else {
//					if(offsetY == 2 ){
//						this.directions[14] ++;
//					}else if(offsetY == -2){
//						this.directions[15] ++;
//					}
//				}
//					
//			}
//	}
//	
//	
//	public int analizeField(){
//		//如果这里有人的话就先杀人。
//		
//		//Which is most situation
//		
//		if(this.enemiesNum > 0){
//			if(this.enemiesNum == 1){
//				int[] only = this.enemyInOwnEyes.get(0);
//				if(CanKillOne(only)){
//					return Player.CAN_KILL_ONE;
//				}else{
//					return Player.ONE_OUT_REACH;
//				}
//				
//			}else if(this.enemiesNum == 2){
//				int[] first = this.enemyInOwnEyes.get(0);
//				int[] second = this.enemyInOwnEyes.get(1);
//				
//				if(first[0] == second[0] || first[1] == second[1]){
//					if(this.CanKillOne(first) && this.CanKillOne(second)){
//						return Player.DOUBLE_KILL;
//					}
//				}else{
//					
//				}
//				
//			}
//		}
//		return Player.OCCUPY_ALL;
//	}
//	
//	
//	
//	
	public boolean CanKillOne(int[] only){
		return (only[0] - this.samuraiInfo.curX <= 1 && this.samuraiInfo.curX  - only[0] <= 1 )||(only[1] - this.samuraiInfo.curY <= 1 && this.samuraiInfo.curY  - only[1] <= 1 );
	}
//	
//	public boolean CanKillOnlyOne(int[] only, int curX , int curY){
//		return (only[0] - curX <= 1 && curX  - only[0] <= 1 )||(only[1] - curY <= 1 && curY  - only[1] <= 1 );
//	}
//	
//	
//	
////	public boolean checkAndKill(){
////		
////		
////		
////	}
////	
//	
//	/**
//	 * 
//	 *	只有一个人在边上，必须得杀啊。
//	 *没准还可以double kill
//	 * @return
//	 */
//	public void  mustKillTheOnlyOneLajiGuy(){
//		//Kill
//	}
//	/**
//	 * 
//	 * 能双杀还不杀这不傻逼吗。
//	 * 
//	 */
//	public void mustDoubleKill(){
//		
//	}
//	/**
//	 * 
//	 * 这个就不太可能了。
//	 * 尴尬脸.jpg
//	 * 
//	 */
//	public void allKill(){
//		
//		
//	}


}
