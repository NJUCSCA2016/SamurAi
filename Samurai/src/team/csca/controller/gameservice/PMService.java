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
			//Hide
		}
	}
	
	public void show(){
		if(canShow()){
			//Show
		}
	}
	
	public boolean canOccupy(int direction){
		
		return false;
	}
	
	public boolean canMove(int direction){
		
		return false;
	}
	
	public boolean canHide(){
	  	if (pm.isHidden()){
			return false;
		}
//    	System.err.println(this.curX + " " + this.curY);
	  	int curIndex = pm.index;
	  	
	  	int op = pm.occupation[15 * pm.x[curIndex] + pm.y[curIndex]];
	  	
	  	if(curIndex/3 == 0){
	  		return op < 3 ? true : false;
	  	}else{
	  		return op == -1 || (op > 2 && op < 6) ? true : false;
	  	}
	  	
	}
	
	public boolean canShow(){
		
		return pm.isHidden();
	}
	
}
