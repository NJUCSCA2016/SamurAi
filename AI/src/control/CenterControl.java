package control;

import data.SamuraiInfo;

public class CenterControl {
	
	private  SamuraiInfo samuraiInfo;
	
	public CenterControl( SamuraiInfo samuraiInfo){
		
		this.samuraiInfo = samuraiInfo;
		
	}
	
	public boolean canMove(int direction , int curX , int curY){
		return this.samuraiInfo.canMoveOneStep(direction, curX, curY);
	}
	
}
