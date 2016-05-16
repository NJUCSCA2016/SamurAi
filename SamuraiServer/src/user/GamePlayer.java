package user;

import gameinfo.FieldInfo;

public class GamePlayer {
	
	private int curX ;
	
	private int curY ;
	
	private final int homeX;
	
	private final int homeY;
	
	private int max_power = 7;
	
	private boolean hide = false;
	
	private FieldInfo field ;
	
	public GamePlayer(FieldInfo field , int homeX , int homeY) {
		
		this.field = field;
		this.homeX = homeX;
		this.homeY = homeY;
		
	}
	
	
	
	
	
	
}
