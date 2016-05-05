package control;

import data.SamuraiInfo;
/**
 * 
 * 
 * 此类用于隐藏所有人物的低级行为。
 * 禁止Player直接调用SamuraiInfo中的任何方法
 * @author With You
 *
 */
public class CenterControl {
	
	private  SamuraiInfo samuraiInfo;
	
	public CenterControl( SamuraiInfo samuraiInfo){
		
		this.samuraiInfo = samuraiInfo;
		
	}
	/**
	 *  以下为Player可以执行的最基本的几个操作。
	 *  
	 *  尚未决定是否需要把Hide 和 Show 加入该操作
	 */
	
	public boolean canOccupy(int direction){
		return this.canOccupy(direction);
	}
	
	public boolean canMveOneStep(int direction){
		return this.samuraiInfo.canMoveOneStep(direction);
	}
	
	public boolean canMoveTwoStep(int direction1 , int direction2){
		return this.samuraiInfo.canMoveTwoStep(direction1, direction2);
	}
	
	public boolean canMoveThreeStep(int direction1 , int direction2 , int direction3){
		return this.samuraiInfo.canMoveThreeStep(direction1, direction2, direction3);
	}
	
	public void occupy(int direction){
		this.samuraiInfo.occupy(direction);
	}
	
}
