package service;

import data.GameInfo;
import control.CenterControl;
import data.ActionInfo;
import data.SamuraiInfo;
/**
 * 
 * 这个类为逻辑处理类�??
 * 处理各种低级的行动�?�移动一步�?�两步�?�三步�?�攻击�?�隐身�?�显示�??
 * @author With You
 *
 */
public class GameService {
	/**
	 * 游戏信息
	 */
	private GameInfo gameInfo;
	/**
	 *  自身信息 同样不设置为private
	 */
	public SamuraiInfo myself;
//	/**
//	 *  攻击范围，不去用旋转来计算，直接将其列出
//	 */
//	private final static int[][] ox = {
//			{0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 1, 1, 2, 0, 0},
//			{-1,-1,-1,0, 1, 1, 1}
//	};
//	private final static int[][] oy = {
//			{1, 2, 3, 4},
//			{1, 2, 0, 1, 0},
//			{-1,-1,1, 1, 1,-1, 0}
//	};
	public GameService(GameInfo gameInfo){
		this.gameInfo = gameInfo;
	}
//	 /**
//     * 判断是否可达
//     */
//    public Boolean isValid(int action){
//    	System.exit(-1);
//		return false;
//    }
    /**
     * 是否可以采取占领
     * @param moveDirection 占领的方�?
     * @return
     */
    public boolean canOccupy(){
    	//如果要执�?1-4 那么判断是否为隐藏�?�若隐藏则不执行命令
    	if(myself.hidden == 0){
    		return false;
    	}
    	//敌方老巢
    	return false;
//		return myself.hidden == 0;
    }
    
  /**
   * 是否可以移动
   * @param moveDirection
   * @return
   */
    public boolean canMove(int moveDirection , int curX , int curY){
    	
		//新的坐标
		int[] offset = ActionInfo.MOVE_OFFSET[moveDirection - 5];
		curX += offset[0];
		curY += offset[1];
		
			//判断是否出界
		if (this.checkOutOfField(curX, curY)){
			return false;
		}
			
		//不能隐身移到对方的field�?
		if (myself.hidden == 1 && this.gameInfo.field[curY][curX] >= 3){
			return false;
		}
		//AI主动选择不重�?	
		for (int i = 0; i < GameInfo.PLAYER_NUM; ++i){
			//AI的curXY并没有更�?
			if (curX == this.gameInfo.samuraiInfo[i].curX && curY == this.gameInfo.samuraiInfo[i].curY){
				return false;
			}
			//踩到别人老家
			if (i != this.gameInfo.weapon && (curX == this.gameInfo.samuraiInfo[i].homeX && curY == this.gameInfo.samuraiInfo[i].homeY)){
				return false;
			}
			
		}
		
		return true;	
    }
    /**
     * 判断是否可以隐藏
     * @param curY �?�?
     * @param curX 二维
     * @return
     */
    public boolean canHide(){
    	if (myself.hidden == 1){
			return false;
		}
		if (this.gameInfo.field[myself.curY][myself.curX] >= 3){
			return false;
		}
		return true;
    }
    /**
     * 判断是否可以现身
     * @return
     */
    public boolean canShow(){
    	if (myself.hidden != 1){
	    	return false;
			}
    	
			for (int i = 0; i < GameInfo.PLAYER_NUM; ++i){
				SamuraiInfo other = this.gameInfo.samuraiInfo[i];
				//如果别人在这里显现了你就不能在这里显�?
				if (other.hidden != 1 && (other.curX == myself.curX && other.curY == myself.curY)){
					return false;
				}
			}
			return true;
    }
    /**
     * 判断是否出界
     * @param curX 当前X坐标
     * @param curY 当前Y坐标
     * @return
     */
    private boolean checkOutOfField(int curX , int curY){
		return curX < 0 || this.gameInfo.width <= curX || curY < 0 || this.gameInfo.height <= curY;
    }
    
    public void occupy(int direction){
    	
    	int curX = myself.curX;
    	int curY = myself.curY;
    	
    	int weapon = this.gameInfo.weapon;
    	
    	
    }
    
    /**
     * 占领操作
     * @param direction 占领方向
     */
    public void occupys(int direction){
//    	SamuraiInfo myself = this.samuraiInfo[this.weapon];
    	//当前坐标
    	int curX = myself.curX;
    	int curY = myself.curY;
    	
    	int weapon = this.gameInfo.weapon;
    	int width = this.gameInfo.width;
    	int height = this.gameInfo.height;
    	
    	
    	
    	
//	for (int i = 0; i < weapon; ++i){
//		//更新旋转坐标
//	    int[] pos = this.rotate(direction, ox[weapon][i], oy[weapon][i]);
//	    pos[0] += curX;
//	    pos[1] += curY;
//	    //
//	    if (0 <= pos[0] && pos[0] < width && 0 <= pos[1] && pos[1] < height){
//	    	
//	    boolean isHome = false;
//		
//	    for (int j = 0; j < GameInfo.PLAYER_NUM; ++j){
//		    if (this.gameInfo.samuraiInfo[j].homeX == pos[0] && this.gameInfo.samuraiInfo[j].homeY == pos[1]){
//		    	isHome = true;
//		    }
//		}
//		if (isHome){
//		    this.gameInfo.field[pos[1]][pos[0]] = weapon;
//			
//		    for (int j = 3; j < GameInfo.PLAYER_NUM; ++j){
//			SamuraiInfo si = this.gameInfo.samuraiInfo[j];
//			if (si.curX == pos[0] && si.curY == pos[1]){
//			    si.curX = si.homeX;
//			    si.curY = si.homeY;
//			    si.hidden = 0;
//			    this.gameInfo.samuraiInfo[j] = si;
//			}
//		    }
//		}
//	    }
//	}
    }
    
	/**
	 *  以下为AI的一些基本的操作�?
	 */
	
	/**
	 *  移动两步
	 * 
	 *  由于7 = 1 + 2 + 4 = 1 + 2 + 2 + 2 。所以只要执行了Occupy操作和Hide操作就一定有�?个多余�?�如果可以Hide则Hide
	 *  
	 * 	考虑情形 �?	考虑手最长的Player
	 * 						在你的可见范围内。如果突然多了两个连续的敌军Occupy 。由于一次只能移动一步�?? �?以移动两步为安全�?
	 * 
	 */
	protected void moveTwoStep(){
		
	}
	
	/**
	 * 移动三步
	 * 
	 * 其他与移动两步相同�?�但是这次是多了�?个连续的�?
	 */
	protected void moveThreeStep(){
		
	}
	/**
	 * 以下为一些组合操作�??
	 * 先移动再攻击
	 * 先攻击再移动
	 * �?后一定为Hide
	 */
	protected void hitThenMove(){
		
	}
	
	protected void moveThenHit(){
		
	}
	
	
	
   
}
