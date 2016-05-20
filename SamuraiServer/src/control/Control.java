package control;

import java.util.ArrayList;
import java.util.List;

import gameinfo.FieldInfo;
import user.UserInfo;

public abstract class Control {
	/**
	 * To notify the players
	 */
	protected final List<UserInfo> sixplayers ;
	
	protected int curPlayer = 0 ;
	
	protected final FieldInfo field;
	
	public Control(ArrayList<UserInfo> sixplayers) {
		
		this.sixplayers = sixplayers;
		this.field = new FieldInfo();
		initGame();
		
	}
	
	protected void initGame(){
		
	}
}
