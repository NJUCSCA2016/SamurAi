package user;

import data.FieldInfo;

public class GamePlayer {
	
	
	
	private int curX ;
	
	private int curY ;
	
	private int homeX;
	
	private int homeY;
	
	private int max_power = 7;
	
	private int recover = 0;
	
	private boolean hide = false;
	
	private FieldInfo field ;
	
	public GamePlayer(FieldInfo field , int homeX , int homeY) {
		
		this.field = field;
		this.homeX = homeX;
		this.homeY = homeY;
		
	}
	
	public void initPlayer(int homeX , int homeY){
		this.homeX =  homeX;
		this.homeX = homeY;
		this.curX = homeX;
		this.curY = homeY;
	}
	
	public int getXOfPlayer(){
		return this.curX;
	}
	
	public int getYOfPlayer(){
		return this.curY;
	}
	
	
	
}
