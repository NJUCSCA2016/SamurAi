package team.csca.ai.AIControl;

import team.csca.ai.AIdata.SamuraiInfo;
/**
 * 
 * 
 * 此类用于隐藏所有人物的低级行为。
 * The connection between AI high Level operation and the the lowest level operations
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
	
	public boolean canMoveOneStep(int direction){
		return this.samuraiInfo.canMoveOneStep(direction);
	}
	
	public boolean canMoveTwoStep(int direction1 , int direction2){
		return this.samuraiInfo.canMoveTwoStep(direction1, direction2);
	}
	
	public boolean canMoveThreeStep(int direction1 , int direction2 , int direction3){
		return this.samuraiInfo.canMoveThreeStep(direction1, direction2, direction3);
	}
	
	public boolean canHide(){
		return this.samuraiInfo.canHide();
	}
	
	public boolean canShow() {
		return this.samuraiInfo.canShow();
	}
	
	public void hide(){
		this.samuraiInfo.hide();
	}
	
	public void show(){
		this.samuraiInfo.show();
	}
	
	public void occupy(int direction){
		this.samuraiInfo.occupy(direction);
	}
	
	public void moveOneStep(int direction) {
		this.samuraiInfo.moveOneStep(direction);
	}
	
	public void moveTwoStep(int direction1 , int direction2){
		this.samuraiInfo.moveTwoStep(direction1, direction2);
	}
	
	public void moveThreeStep(int direction1 , int direction2 , int direction3) {
		this.samuraiInfo.moveThreeStep(direction1, direction2, direction3);
	}
	
	public void moveThenHit(int moveDirection , int hitDirection){
		this.samuraiInfo.moveThenHit(moveDirection, hitDirection);
	}
	
	public void hitThenMove(int hitDirection , int moveDirection){
		this.samuraiInfo.hitThenMove(hitDirection, moveDirection);
	}
	
}
