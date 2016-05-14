package team.csca.controller.gameservice;

import team.csca.view.pm.JPanelPM;
/**
 * 
 * 人机对战逻辑处理。
 * 道具模式逻辑处理与此类似。
 * @author With You
 *
 */
public class PMService implements Service{
	private JPanelPM pm;
	private int[] homeX;
	private int[] homeY;
	private boolean hide = false;
			
	public PMService(JPanelPM pm) {
		this.pm = pm;
		
	}
	
	public void occupy(int direction){
		if(canOccupy(direction)){
			
		}
	}
	
	public void move(int direction){
		if(canMove(direction)){
			
		}
	}
	
	public void hide(){
		if(canHide()){
			
		}
	}
	
	public void show(){
		if(canShow()){
			hide = false;
		}
	}
	
	public boolean canOccupy(int direction){
		
		return false;
	}
	
	public boolean canMove(int direction){
		
		return false;
	}
	
	public boolean canHide(){
	  	if (hide){
			return false;
		}
//    	System.err.println(this.curX + " " + this.curY);
		if (true){
			return false;
		}
		return true;
	}
	
	public boolean canShow(){
		
		return hide;
	}
	
}
