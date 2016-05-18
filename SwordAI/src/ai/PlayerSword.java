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
	public ArrayList<Integer> weapons = new ArrayList<Integer>(3);
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
		
	private int curX =0;
	
	private int curY=0;
	
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
//		occupy(4);
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
			// 三个都一样的，这个方法肯定是对的。
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
			
			System.err.println("Watch Them");
			//TODO： According to whether there is spear . if not ? , if so .
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
	
		if(this.weapons.contains(0)){
			//TODO Spear在。
			int index = this.weapons.indexOf(0);
			int[] spear = this.enemyInOwnEyes.get(index);
			int offXOfSp = spear[0] - curX;
			int offYOfSp = spear[1] - curY;
			int absXSp  = Math.abs(offXOfSp);
			int absYSp = Math.abs(offYOfSp);
			if((absXSp != 2 && absYSp != 2) && (absXSp != 2 && absYSp !=3) && (absXSp != 3 && absYSp != 2) ){
				//Dangerous TODO 
				chooseFleeRoute();
			}else{
					if(absXSp == 2 && absYSp == 2){
						//Do nothing but occupy the field around 
						if(canShow()){
							show();
						}
						dashasifang();
						if(canHide()){
							hide();
						}
					}else {
						duozhe(offXOfSp, offYOfSp);
					}
				} 
		}else{
			tancha();
		}
	}
	
	public void chooseFleeRoute(){
		int index = this.weapons.indexOf(0);
		int[] spear = this.enemyInOwnEyes.get(index);
		int offXOfSp = spear[0] - curX;
		int offYOfSp = spear[1] - curY;
		int absXSp  = Math.abs(offXOfSp);
		int absYSp = Math.abs(offYOfSp);
		//这个判断我不要了
		//写下去会死人的
		//判断完了这个还要判断有没有别人这样子是要玩的
//		if(enemiesNum == 1){
			if(canShow()){
				show();
			}
				int[] fleeDire = new int[12];
				int[] fourForTarget = new int[4];
				Arrays.fill(fleeDire, 0);
				Arrays.fill(fourForTarget, 0);
				if(absXSp == 0 || absYSp == 0){
					if(offXOfSp < 0){
							if(canMoveOneStep(5)){
								fourForTarget[0]++;
								if(canMoveTwoStep(5, 6)){
									fleeDire[4]++;
								}
								if (canMoveTwoStep(5,5)) {
									fleeDire[0]++;
								}
							}
							if(canMoveOneStep(6)){
								fourForTarget[1]++;
								if(canMoveTwoStep(6, 6)){
									fleeDire[1]++;
								}
								if(canMoveTwoStep(6, 5)){
									fleeDire[5]++;
								}
								if(canMoveTwoStep(6, 7)){
									fleeDire[6]++;
								}
							}
							if(canMoveOneStep(7)){
								fourForTarget[2]++;	
								if(canMoveTwoStep(7, 6)){
									fleeDire[7]++;
								}
								if(canMoveTwoStep(7, 7)){
								fleeDire[2]++;
								}
							}
					
					}else if(offXOfSp > 0){
						if(canMoveOneStep(5)){
							fourForTarget[0]++;
							if(canMoveTwoStep(5, 5)){
								fleeDire[0]++;
							}
							if(canMoveTwoStep(5, 8)){
								fleeDire[11]++;
							}
						}
						if(canMoveOneStep(7)){
							fourForTarget[2]++;
							if(canMoveTwoStep(7, 7)){
								fleeDire[2]++;
							}
							if(canMoveTwoStep(7, 8)){
								fleeDire[8]++;
							}
						}
						if(canMoveOneStep(8)){
							fourForTarget[3]++;
							if(canMoveTwoStep(8, 8)){
								fleeDire[3]++;
							}
							if(canMoveTwoStep(8, 5)){
								fleeDire[10]++;
							}
							if(canMoveTwoStep(8, 7)){
								fleeDire[9]++;
							}
						}
					}else if(offYOfSp > 0){
						if(canMoveOneStep(6)){
							fourForTarget[1]++;
							if(canMoveTwoStep(6, 6)){
								fleeDire[1]++;
							}
							if(canMoveTwoStep(6, 7)){
								fleeDire[6]++;
							}
						}
						if(canMoveOneStep(7)){
							fourForTarget[2]++;
							if(canMoveTwoStep(7, 7)){
								fleeDire[2]++;
							}
							if(canMoveTwoStep(7, 6)){
								fleeDire[7]++;
							}
							if(canMoveTwoStep(7, 8)){
								fleeDire[8]++;
							}
						}
						if(canMoveOneStep(8)){
							fourForTarget[3]++;
							if(canMoveTwoStep(8, 8)){
								fleeDire[3]++;
							}
							if(canMoveTwoStep(8, 7)){
								fleeDire[9]++;
							}
						}
					}else if(offYOfSp  < 0){
						
						if(canMoveOneStep(5)){
							fourForTarget[0]++;
							if(canMoveTwoStep(5, 5)){
								fleeDire[0]++;
							}
							if(canMoveTwoStep(5, 8)){
								fleeDire[11]++;
							}
							if(canMoveTwoStep(5, 6)){
								fleeDire[4]++;
							}
						}
						if(canMoveOneStep(6)){
							fourForTarget[1]++;
							if(canMoveTwoStep(6, 6)){
								fleeDire[1]++;
							}
							if(canMoveTwoStep(6, 5)){
								fleeDire[5]++;
							}
						}
						if(canMoveOneStep(8)){
							fourForTarget[3]++;
							if(canMoveTwoStep(8, 8)){
								fleeDire[3]++;
							}
							if(canMoveTwoStep(8, 5)){
								fleeDire[10]++;
							}
						}
					}
					List<Integer> dToFlee = new ArrayList<Integer>();
					for(int i = 0 ; i < fleeDire.length ; i ++){
						if(fleeDire[i] != 0){
							dToFlee.add(i);
						}
					}
					if(dToFlee.size() != 0){
						fleeAction(dToFlee.get(random.nextInt(dToFlee.size())));
					}else{
						List<Integer> weizhuang = new ArrayList<Integer>();
						for(int i = 0 ; i < 4 ; i++){
							if(fourForTarget[i] != 0){
								weizhuang.add(i);
							}
						}
						int move = weizhuang.get(random.nextInt(weizhuang.size()));
						moveOneStep(move + 5);
						//伪装策略。强行推出格子最多的地方迷惑对方
						switch (move) {
						case 0:
							occupy(2);
							break;
						case 1:
							occupy(3);
							break;
						case 2:
							occupy(4);
							break;
						case 3:
							occupy(1);
							break;
						default:
							break;
						}
					}
					
				}else{
					int moveDir = 0;
					if(offXOfSp == -1){
						moveDir = 6;
					}else if (offXOfSp == 1) {
						moveDir = 8;
					}else if (offYOfSp == 1) {
						moveDir = 7;
					}else if (offYOfSp == -1) {
						moveDir = 5;
					}
					moveOneStep(moveDir);
					dashasifang();
				}
				
				if(canHide()){
					hide();
				}

	}
	
	public void fleeAction(int action){
		switch (action) {
		case 0:
			moveTwoStep(5, 5);
			break;
		case 1:
			moveTwoStep(6, 6);
			break;
		case 2:
			moveTwoStep(7, 7);
			break;
		case 3:
			moveTwoStep(8, 8);
			break;
		case 4:
			moveTwoStep(5, 6);
			break;
		case 5:
			moveTwoStep(6, 5);
			break;
		case 6:
			moveTwoStep(6, 7);
			break;
		case 7:
			moveTwoStep(7, 6);
			break;
		case 8:
			moveTwoStep(7, 8);
			break;
		case 9:
			moveTwoStep(8, 7);
			break;
		case 10:
			moveTwoStep(8, 5);
			break;
		case 11:
			moveTwoStep(5, 8);
			break;
		default:
			break;
		}
	}
	
	
	public void duozhe(int offXOfSp , int offYOfSp){
		
		boolean isDangerous = false;
		
		for(int[] each : this.enemyInOwnEyes){
			int offX = Math.abs( each[0] - curX );
			int offY = Math.abs( each[1] - curY );
			if(offX + offY == 4){
				isDangerous = true;
			}
		}
		if(isDangerous){
			if (canShow()) {
				show();
			}
			dashasifang();
			if(canHide()){
				hide();
			}
		}else{
			if(canShow()){
				show();
			}
				if((offXOfSp == 2 || offXOfSp == -2)){
					if(offYOfSp > 0){
						moveOneStep(5);
					}else{
						moveOneStep(7);
					}
				}else if( (offYOfSp == 2 || offYOfSp == -2)){
					if(offXOfSp > 0){
						moveOneStep(6);
					}else{
						moveOneStep(8);
					}
				}
			dashasifang();
			if(canHide()){
				hide();
			}
		}
	}
	/**
	 * 算法改良： offX 和 offY 取abs 
	 * 如果manhattan distance == 4 。No need to switch case。
	 */
	public void tancha(){
		//搜寻八个方向。
		//情况增多。嗯。需要添加一点点就好了。
		int[] fourDirect = new int[10];
		int[] theOtherFour = new int[10];
		int[] sixteenInnerDirect = new int[16];
		Arrays.fill(fourDirect, 0);
		Arrays.fill(theOtherFour, 0);
		Arrays.fill(sixteenInnerDirect, 0);
		
		for(int[] each : this.enemyInOwnEyes){
			int offX = each[0] - curX;
			int offY = each[1] - curY;
				switch (offX) {
				case 2:
					if(offY == 2){
						sixteenInnerDirect[0]++;
					}else if(offY == 3){
						fourDirect[0]++;
					}else if(offY == -2){
						sixteenInnerDirect[1]++;
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
					if(offY == 2){
						theOtherFour[0]++;
					}else if(offY == -2){
						theOtherFour[1]++;
					}else if(offY == 1){
						sixteenInnerDirect[4]++;
					}else if (offY == -1) {
						sixteenInnerDirect[5]++;
					}
					break;
				case -2:
					if(offY == 2){
						sixteenInnerDirect[2]++;
					}else if(offY == 3){
						fourDirect[2]++;
					}else if(offY == -2){
						sixteenInnerDirect[3]++;
					}else{
					fourDirect[3]++;
				}
				break;
				case -3:
				if(offY == 2){
					theOtherFour[2]++;
				}else if(offY == -2){
					theOtherFour[3]++;
				}else if (offY  == 1) {
					sixteenInnerDirect[6]++;
				}else if(offY == -1){
					sixteenInnerDirect[7]++;
				}
				break;
				case 1 : 
				if(offY == 3){
					sixteenInnerDirect[8]++;
				}else if (offY == -3) {
					sixteenInnerDirect[9]++;;
				}if(offY == 4){
					fourDirect[4]++;
				}else{
					fourDirect[5]++;
				}
				break;
				case  -1:
				if(offY == 3){
					sixteenInnerDirect[10]++;
				}else if (offY == -3) {
					sixteenInnerDirect[11]++;;
				}else if(offY == 4 ){
					fourDirect[6]++;
				}else{
					fourDirect[7]++;
				}
				break;
				case 0:
				if(offY == 4){
					sixteenInnerDirect[12]++;
				}else if(offY == -4){
					sixteenInnerDirect[14]++;
				}else if (offY == 5) {
					fourDirect[8]++;
				}else {
					fourDirect[9]++;
				}
				case 4:
					if(offY == 1){
						theOtherFour[4]++;
					}else if(offY == -1){
						theOtherFour[5]++;
					}else {
						sixteenInnerDirect[13]++;
					}
					break;
				case -4:
					if(offY == 1){
						theOtherFour[6]++;
					}else if(offY == -1){
						theOtherFour[7]++;
					}else {
						sixteenInnerDirect[15]++;
					}
					break;
				case -5:
					theOtherFour[9]++;
					break;
				case 5:
					theOtherFour[8]++;
				default:
				break;
				}
			
			
			
		}
		
		//内部四个有人？
		boolean takeSafe = true;
		for(int i = 0 ; i < 16 ; i++){
			if(sixteenInnerDirect[i] != 0){
				takeSafe = false;
			}
		}
		for(int i = 0 ; i < 10 ; i++){
			System.err.println("OutFour  "+ i + "  =  " + theOtherFour[i] );
		}
		for(int i = 0 ; i < 16 ; i++){
			System.err.println("InnerFour  "+ i + "  =  " + sixteenInnerDirect[i] );
		}
		for(int i = 0 ; i < 10 ; i++){
			System.err.println("DangerFour  "+ i + "  =  " + fourDirect[i] );
		}
		if(takeSafe){
			int[] moveToFourSafePlace = new int[4];
			Arrays.fill(moveToFourSafePlace, 0);
			/**
			 * [0] : X > 0 ; Y > 0
			 * [1] : X > 0 ; y < 0
			 * [2] : X < 0 ; Y > 0
			 * [3] : X < 0 ; Y < 0
			 */
			//TODO : 补全
			
			int max = 0;
			ArrayList<Integer> maxIndex = new ArrayList<Integer>();
			for(int i = 0 ; i < 4 ; i++){
				if(moveToFourSafePlace[i] >  max){
					max = moveToFourSafePlace[i];
					maxIndex.clear();
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
				//本来此方法应该加个模拟杀最多的。
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
		//： 查四方方法已改。
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
		int[] count = new int[4];
		Arrays.fill(count, 0);
		for(int[] each : placeWaitingToOccupy){
			int offsetX = each[0] - curX;
			int offsetY = each[1] - curY;
			if(offsetX == 0){
				if(offsetY == 1 || offsetY == 2){
					count[0]++;
					count[3]++;
				}else if(offsetY == -1 || offsetY == -2){
					count[1]++;
					count[2]++;
				}
			}else if(offsetY == 0){
				if(offsetX == 1 || offsetX == 2){
					count[0]++;
					count[1]++;
				}
			}else if(offsetX == 1 && offsetY == 1){
				count[0]++;
			}else if (offsetX == 1 && offsetY == -1) {
				count[1]++;
			}else if(offsetY == 1 && offsetX == -1){
				count[3]++;
			}else if (offsetX == -1 && offsetY == -1) {
				count[2]++;
			}
		}
		return count;
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
			if(model == 2 && max == 0){
				moveOneStep(canMoveDirect.get(random.nextInt(canMoveDirect.size())));
				//还是躲着比较好。
			}else{
				//随机杀一个人最多的
				int[] getRandomAct = maxIndex.get(random.nextInt(maxIndex.size()));
				moveOneStep(canMoveDirect.get(getRandomAct[0]));
				show();
				occupy(getRandomAct[1] + 1);
				if(canHide()){
					hide();
				}
			}
			
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
			if(offsetX == 0){
				if(offsetY == 1 || offsetY == 2){
					count[0]++;
					count[3]++;
				}else if(offsetY == -1 || offsetY == -2){
					count[1]++;
					count[2]++;
				}
			}else if(offsetY == 0){
				if(offsetX == 1 || offsetX == 2){
					count[0]++;
					count[1]++;
				}
			}else if(offsetX == 1 && offsetY == 1){
				count[0]++;
			}else if (offsetX == 1 && offsetY == -1) {
				count[1]++;
			}else if(offsetY == 1 && offsetX == -1){
				count[3]++;
			}else if (offsetX == -1 && offsetY == -1) {
				count[2]++;
			}
		}
		return count;
	}

	//一样。没有错。
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
		}else if(curX < this.samuraiInfo.width >> 1 && curY < this.samuraiInfo.height >> 1){
			moveThreeStep(5, 5, 6);
		}else if(curX < this.samuraiInfo.width >> 1 && curY > this.samuraiInfo.height >> 1){
			moveThreeStep(6, 6, 7);
		}else if(curX > this.samuraiInfo.width >> 1 && curY < this.samuraiInfo.height >> 1){
			moveThreeStep(8, 8, 5);
		}else if(curX > this.samuraiInfo.width >> 1 && curY > this.samuraiInfo.height >> 1){
			moveThreeStep(7,7 , 8);
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
	
	/**
	 * TODO : 添加边上是否有友军的判断。
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
			//Move to where there exist enemy.
			
		
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
			//direct sixteen : 上移上击
			//direct 17 : 下移下击
			//direct 18： 左移左击
			//direct 19： 右移右击
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
			directions[15]++;
			directions[7]++;
			directions[12]++;
			directions[14]++;
			directions[11]++;
			directions[4]++;
			directions[13]++;
		}
//		System.err.println("Nums + " + placeWaitingToOccupy.size());
			for(int[] eachBlock : this.placeWaitingToOccupy){
				int blockX = eachBlock[0];
				int blockY = eachBlock[1];
			
				int offsetX = blockX - curX;
				int offsetY = blockY - curY;
				System.err.print("X = " +blockX+ "   Y =" + blockY);
				switch (offsetX) {
				case -3:
					directions[5]++;
					directions[16]++;
					break;
				case -2:
						switch (offsetY) {
						case 0:
							directions[0]++;
							directions[3]++;
							directions[5]++;
							directions[16]++;
							break;
						case 1:
							directions[9]++;
							directions[16]++;
						case -1:
							directions[19]++;
							directions[5]++;
							break;
						default:
							break;
						}
					
						break;
				case -1:
						switch (offsetY) {
						case 0:
							directions[0]++;
							directions[3]++;
							directions[15]++;
							directions[11]++;
							directions[12]++;
							directions[7]++;
							break;
						case 1:
							directions[0]++;
							directions[9]++;
							directions[15]++;
							directions[16]++;
							break;
						case 2:
							directions[9]++;
							directions[16]++;
							break;
						case -1:
							directions[3]++;
							directions[5]++;
							directions[19]++;
							directions[11]++;
							break;
						case -2:
							directions[19]++;
							directions[5]++;
							break;
						default:
						break;
						}
					
						break;
				case 0:
						switch (offsetY) {
						case 1:
							directions[0]++;
							directions[2]++;
							directions[4]++;
							directions[12]++;
							break;
						case 2:
							directions[0]++;
							directions[2]++;
							directions[9]++;
							directions[18]++;
							break;
						case 3:
							directions[9]++;
							directions[18]++;
							break;
						case -1:
							directions[3]++;
							directions[1]++;
							directions[13]++;
							directions[7]++;
							break;
						case -2:
							directions[3]++;
							directions[1]++;
							directions[10]++;
							directions[19]++;
							break;
						case -3:
							directions[10]++;
							directions[19]++;
							break;
						default:
							break;
						}
						break;
				case 1:
						switch (offsetY) {
						case 0:
							directions[2]++;
							directions[1]++;
							directions[8]++;
							directions[14]++;
							directions[4]++;
							directions[13]++;
							break;
						case 1:
							directions[2]++;
							directions[18]++;
							directions[6]++;
							directions[8]++;
							break;
						case 2:
							directions[6]++;
							directions[18]++;
							break;
						case -1:
							directions[1]++;
							directions[17]++;
							directions[10]++;
							directions[14]++;
							break;
						case -2:
							directions[17]++;
							directions[10]++;
							break;
						default:
							break;
						}
						break;
				case 2:
						switch (offsetY) {
						case 1:
							directions[6]++;
							directions[18]++;
							break;
						case 0:
							directions[2]++;
							directions[1]++;
							directions[6]++;
							directions[17]++;
							break;
						case -1:
							directions[10]++;
							directions[17]++;
							break;
						default:
							break;
						}
						break;
				case 3:
						directions[6]++;
						directions[17]++;
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
				}
			}
	}
	
	
	public boolean CanKillOne(int[] only){
		return Math.abs(only[0] - curX) + Math.abs(only[1] - curY) <=3;
	}

}
