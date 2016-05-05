/**
 * Date : Mar 28, 2016 6:47:01 PM
 */
package data;
/**
 * 
 * @version version two . In the first version , there exits a class called service . while in this version. I delete it and move all its method to this class
 * 
 * 
 * @version version three : The basic operation is done . Then finished the link between AI and this Info.
 * 
 * @version version four . In this version , All the basic operations are finished . Then go and finished the Center and simple player
 * 
 * 这个类为逻辑处理类。
 * 处理各种低级的行动。移动一步、两步、三步。攻击。隐身。显示。
 * @author With You
 *
 * @warning  warning : All these methods in this class is never permitted to call by ANY CLASS except CLASS CONTROL !
 */

public final class SamuraiInfo {
	/**
	 *  每个武士的信息
	 *  大本营坐标
	 *  武士的横纵坐标
	 *  排名 分数 是否hidden
	 */
	  public int homeX, homeY;
	  public int curX, curY;
	  public int rank, score, hidden;

		/**
		 * 游戏信息
		 */
		private GameInfo gameInfo;

		/**
		 *  用于暂时存放每次检查是否可以移动时模拟后的X,Y;
		 */
		private int XAfterStep;
		private int YAfterStep;
		/**
		 *  于初始化时加载即可，省去了判断时的赋值
		 */
		private int[][] offset = ActionInfo.MOVE_OFFSET;
		private int[][][] attackField = ActionInfo.ATTACK_FIELD.SPEAR.attack_Field;
//		/**
//		 *  攻击范围，不去用旋转来计算，直接将其列出
//		 */
//		private final static int[][] ox = {
//				{0, 0, 0, 0, 0, 0, 0},
//				{0, 0, 1, 1, 2, 0, 0},
//				{-1,-1,-1,0, 1, 1, 1}
//		};
//		private final static int[][] oy = {
//				{1, 2, 3, 4},
//				{1, 2, 0, 1, 0},
//				{-1,-1,1, 1, 1,-1, 0}
//		};
	  
	  
	  public SamuraiInfo(GameInfo gameInfo){
		this.homeX = 0;
		this.homeY = 0;
		this.curX = 0;
		this.curY = 0;
		this.rank = 0;
		this.score = 0;
		this.hidden = 0;
		this.gameInfo = gameInfo;
	  }
	
	  /**
		 *  以下为定义AI的一些基本的操作。
		 */
	  
	  
	   /** 
	    * 
	     * 判断是否可以隐藏
	     * @param curY 一维
	     * @param curX 二维
	     * @return
	     */
	    public boolean canHide(){
	    	if (this.hidden == 1){
				return false;
			}
			if (this.gameInfo.field[this.curY][this.curX] >= 3){
				return false;
			}
			return true;
	    }
	    /**
	     * 是否可以采取占领
	     * @param moveDirection 占领的方向
	     * @return
	     */
	    public boolean canOccupy(){
	    	//如果要执行1-4 那么判断是否为隐藏。若隐藏则不执行命令
	    	if(this.hidden == 1){
	    		return false;
	    	}
	    	//如果边上都是自己或者友军的地盘。没有必要去占领。
	    	
	    	//敌方老巢
	    	return false;
//			return myself.hidden == 0;
	    }

	    /**
	     * 判断是否可以现身
	     * @return
	     */
	    public boolean canShow(){
	    	if (this.hidden == 0){
		    	return false;
				}
	    	
				for (int i = 0; i < GameInfo.PLAYER_NUM; ++i){
					SamuraiInfo other = this.gameInfo.samuraiInfo[i];
					//如果别人在这里显现了你就不能在这里显现
					if (other.hidden != 1 && (other.curX == this.curX && other.curY == this.curY)){
						return false;
					}
				}
				return true;
	    }
	    
	    public void show(){
	    	this.hidden = 0;
	    	System.out.print(10 + " ");
	    }
	    
	    public void hide(){
	    	this.hidden = 1;
	    	System.out.print(9 + " ");
	    }
	    
	    public void occupy(int direction){
	    	
	    	int weapon = this.gameInfo.weapon;
	    	
	    	//攻击范围需要根据武器来定。
	    	//故根据三种武器需要三种不同的方法。以下为SPEAR
	    	
	    	for(int i = 0 ; i < attackField.length ; i++ ){
	    			int[] offset = attackField[direction][i];
	    			int attackX = this.curX + offset[0];
	    			int attackY = this.curY + offset[1];
	    			if(! this.checkOutOfField(attackX, attackY)){
	    				//将该人物占领的区域设置为自身的区域
	    				//除去了原方法中的内嵌循环检查。不需要检查其中是否含有其他人物。因为这是会由GameManager来判断的。  				
	    				this.gameInfo.field[attackX][attackY] = weapon;
	    			}
	    	}
	    	System.out.print(direction + " ");
//			不一定能Hide
//	    	this.checkThenHide();
	    	
	    }
	    
	    /**
	     * 占领操作
	     * @param direction 占领方向
	     */
	    @Deprecated
	    public void occupys(int direction){
//	    	SamuraiInfo myself = this.samuraiInfo[this.weapon];
	    	int weapon = this.gameInfo.weapon;
	    	//攻击范围需要根据武器来定。
	    	//故根据三种武器需要三种不同的方法。以下为SPEAR
	    	int[][] attackField = ActionInfo.ATTACK_FIELD.SPEAR.attack_Field[direction];
	    	for(int i = 0 ; i < attackField.length ; i++ ){
	    			int[] offset = attackField[i];
	    			int attackX = this.curX + offset[0];
	    			int attackY = this.curY + offset[1];
	    			if(! this.checkOutOfField(attackX, attackY)){
	    				//将该人物占领的区域设置为自身的区域
	    				//除去了原方法中的内嵌循环检查。不需要检查其中是否含有其他人物。因为这是会由GameManager来判断的。  				
	    				this.gameInfo.field[attackX][attackY] = weapon;
	    			}
	    	}
	    	
//		for (int i = 0; i < weapon; ++i){
//			//更新旋转坐标
//		    int[] pos = this.rotate(direction, ox[weapon][i], oy[weapon][i]);
//		    pos[0] += curX;
//		    pos[1] += curY;
//		    //
//		    if (0 <= pos[0] && pos[0] < width && 0 <= pos[1] && pos[1] < height){
//		    	
//		    boolean isHome = false;
//			
//		    for (int j = 0; j < GameInfo.PLAYER_NUM; ++j){
//			    if (this.gameInfo.samuraiInfo[j].homeX == pos[0] && this.gameInfo.samuraiInfo[j].homeY == pos[1]){
//			    	isHome = true;
//			    }
//			}
//			if (isHome){
//			    this.gameInfo.field[pos[1]][pos[0]] = weapon;
//				
//			    for (int j = 3; j < GameInfo.PLAYER_NUM; ++j){
//				SamuraiInfo si = this.gameInfo.samuraiInfo[j];
//				if (si.curX == pos[0] && si.curY == pos[1]){
//				    si.curX = si.homeX;
//				    si.curY = si.homeY;
//				    si.hidden = 0;
//				    this.gameInfo.samuraiInfo[j] = si;
//				}
//			    }
//			}
//		    }
//		}
	    }
	    /**
	     * 移动模拟
	     * 
	     * 此类不需要被外部所知
	     * @param direction 移动方向
	     * @param curX 当前模拟坐标
	     * @param curY 当前模拟坐标
	     * @return 模拟结束后坐标
	     */
		private int[] moveStimulation(int direction , int curX , int curY){
			
			//更新AI的坐标
			curX = curX + offset[direction][0];
			curY = curY + offset[direction][1];
			
			return new int[]{curX , curY};
		}
		
		/**
		 * 
		 * 以下所有的移动操作之后
		 * 均执行Hide操作
		 * 
		 * Warning ! ： All the direction is the action code but not 1234
		 *
		 */
		
		
		/**
		 * 移动一步
		 * @param direction
		 * @param curX
		 * @param curY
		 */
		@Deprecated
	    private void moveOneStep(int direction , int curX , int curY ){
	    	
	    	int[] coordinations = this.moveStimulation(direction, curX, curY);
			this.curX = coordinations[0];
			this.curY = coordinations[1];
			
//			this.checkThenHide();
			
			System.out.print(direction + " ");
	    }
		
	    public void moveOneStep(int direction){
	    	
	    	int[] coordinations = this.moveStimulation(direction, curX, curY);
			this.curX = coordinations[0];
			this.curY = coordinations[1];
			
//			this.checkThenHide();
			
			System.out.print(direction + " ");
	    }
	
		/**
		 *  移动两步
		 * 
		 *  由于7 = 1 + 2 + 4 = 1 + 2 + 2 + 2 。所以只要执行了Occupy操作和Hide操作就一定有一个多余。如果可以Hide则Hide
		 *  
		 * 	考虑情形 ：	考虑手最长的Player
		 * 						在你的可见范围内。如果突然多了两个连续的敌军Occupy 。由于一次只能移动一步。 所以移动两步为安全的
		 * 
		 */
		public void moveTwoStep(int direction1 , int direction2){
			int[] coordinationsFirstStep = this.moveStimulation(direction1, curX, curY);
			int[] coordinationsSecondStep = this.moveStimulation(direction2, coordinationsFirstStep[0], coordinationsFirstStep[1]);
			this.curX = coordinationsSecondStep[0];
			this.curY = coordinationsSecondStep[1];
			System.out.print(direction1 + " " + direction2 + " ");
//			this.checkThenHide();
		}
		
		/**
		 * 移动三步
		 * 
		 * 其他与移动两步相同。但是这次是多了一个连续的。
		 */
		public void moveThreeStep(int direction1 , int direction2 , int direction3){
			int[] coordinationsFirstStep = this.moveStimulation(direction1, curX, curY);
			int[] coordinationsSecondStep = this.moveStimulation(direction2, coordinationsFirstStep[0], coordinationsFirstStep[1]);
			int[] coordinationsThirdStep = this.moveStimulation(direction3, coordinationsSecondStep[0], coordinationsSecondStep[1]);
			this.curX = coordinationsThirdStep[0];
			this.curY = coordinationsThirdStep[1];
			System.out.print(direction1 + " " + direction2 + " " + direction3 + " ");
//			this.checkThenHide();
		}
		/**
		 * 以下为一些组合操作。
		 * 执行前必须先判断CanMove
		 * 先移动再攻击
		 * 先攻击再移动
		 */
		public void hitThenMove(int moveDirection , int hitDirection){
			
			this.moveOneStep(moveDirection);
			this.occupy(hitDirection);
			
		}
		
		public void moveThenHit(int hitDirection , int moveDirection){
			
			this.occupy(hitDirection);
			this.moveOneStep(moveDirection);
//			this.checkThenHide();			
		}
		
		
	   
	  /**
	   * 是否可以移动
	   * @param moveDirection
	   * @return
	   */
		@Deprecated
	    public boolean canMove(int moveDirection , int curX , int curY ){
	    	
			//新的坐标
			int[] offset = ActionInfo.MOVE_OFFSET[moveDirection - 5];
			this.XAfterStep = curX + offset[0];
			this.YAfterStep = curY + offset[1];
			
				//判断是否出界
			if (this.checkOutOfField(this.XAfterStep, this.YAfterStep)){
				return false;
			}
				
			//不能隐身移到对方的field。
			if (this.hidden == 1 && this.gameInfo.field[this.XAfterStep][this.YAfterStep] >= 3){
				return false;
			}
			//AI主动选择不重叠	
			for (int i = 3; i < GameInfo.PLAYER_NUM; ++i){
//				
//				这里我为了提高效率，不选择不重叠。
//				实际上根据我的设置，是不会主动和别的敌军或者其他重叠的
//				//AI的curXY并没有更新
//				if (this.XAfterStep == this.gameInfo.samuraiInfo[i].curX && this.YAfterStep == this.gameInfo.samuraiInfo[i].curY){
//					return false;
//				}
				//踩到别人老家
//				没有说不能到友军他家
				//第一个判断完全没有用
				if (i != this.gameInfo.weapon && (this.XAfterStep == this.gameInfo.samuraiInfo[i].homeX && this.YAfterStep == this.gameInfo.samuraiInfo[i].homeY)){
					return false;
				}
				
			}
			
			return true;	
	    }
		/**
		 *  此类只用于内部判断多步移动时是否可行。
		 *  外部不需要知道有可以传递改类型参数的类
		 * @param moveDirection
		 * @param curX
		 * @param curY
		 * @return
		 */
	    private boolean canMoveOneStep(int moveDirection , int curX , int curY ){
	    	
			//新的坐标
			int[] offset = ActionInfo.MOVE_OFFSET[moveDirection - 5];
			this.XAfterStep = curX + offset[0];
			this.YAfterStep = curY + offset[1];
			//判断是否出界
			if (this.checkOutOfField(this.XAfterStep, this.YAfterStep)){
				return false;
			}	
			//不能隐身移到对方的field。
			if (this.hidden == 1 && this.gameInfo.field[this.XAfterStep][this.YAfterStep] >= 3){
				return false;
			}
			
			for (int i = 3; i < GameInfo.PLAYER_NUM; ++i){
				if ( this.XAfterStep == this.gameInfo.samuraiInfo[i].homeX && this.YAfterStep == this.gameInfo.samuraiInfo[i].homeY ){
					return false;
				}
			}
			return true;	
	    }
	    /**
	     * 此移动方法直接为外部可见
	     * @param moveDirection
	     * @return
	     */
	    public boolean canMoveOneStep(int moveDirection){
	    	
			//新的坐标
			int[] offset = ActionInfo.MOVE_OFFSET[moveDirection - 5];
			this.XAfterStep = curX + offset[0];
			this.YAfterStep = curY + offset[1];
			//判断是否出界
			if (this.checkOutOfField(this.XAfterStep, this.YAfterStep)){
				return false;
			}	
			//不能隐身移到对方的field。
			if (this.hidden == 1 && this.gameInfo.field[this.XAfterStep][this.YAfterStep] >= 3){
				return false;
			}
			
			for (int i = 3; i < GameInfo.PLAYER_NUM; ++i){
				if ( this.XAfterStep == this.gameInfo.samuraiInfo[i].homeX && this.YAfterStep == this.gameInfo.samuraiInfo[i].homeY ){
					return false;
				}
			}
			return true;	
	    }
		/**
		 * 
		 * 判断移动两步
		 * 
		 * @param direction1 移动方向一
		 * @param direction2 移动方向二
		 * @param curX 起始点坐标
		 * @param curY 起始点坐标
		 * @return 是否可以移动两步
		 * 
		 */
		public boolean canMoveTwoStep(int direction1 ,  int direction2){
			if(! this.canMoveOneStep(direction1, curX, curY)){
				return false;
			}
			if(! this.canMoveOneStep(direction2, this.XAfterStep, this.YAfterStep)){
				return false;
			}
			return true;
		}
		/**
		 *  移动三步。参数具体意义同上
		 * @param direction1
		 * @param direction2
		 * @param direction3
		 * @param curX
		 * @param curY
		 * @return
		 * 
		 * 该方法并没有采用Call MoveTwoStep Then Call moveOneStep
		 * 这是因为在相同的意义下。全部只Call moveOneStep所需要的压栈次数少。节省时间
		 * 
		 */
		public boolean canMoveThreeStep(int direction1 , int direction2 , int direction3){
			if(! this.canMoveOneStep(direction1, curX, curY)){
				return false;
			}
			if(! this.canMoveOneStep(direction2, this.XAfterStep, this.YAfterStep)){
				return false;
			}
			if(! this.canMoveOneStep(direction3, this.XAfterStep, this.YAfterStep)){
				return false;
			}
			return true;
		}
		
	    /**
	     * 判断是否出界
	     * @param curX 当前X坐标
	     * @param curY 当前Y坐标
	     * @return
	     */
	    public boolean checkOutOfField(int curX , int curY){
			return curX < 0 || this.gameInfo.width <= curX || curY < 0 || this.gameInfo.height <= curY;
	    }
	    
	    private void checkThenHide(){
	    	if(this.canHide()){
	    		this.hidden = 1 ;
	    		System.out.print(9 + " ");
	    	}
	    }
	    
}
