package user;

import java.io.Serializable;

import team.csca.server.GameNotice;

public class UserInfo implements Serializable{	
	
	private GameNotice notic;
	
//	private GameReceive observer;
	
//	private LinkServer server;
	
	private String name = null;
	
	private String password = null;

	private int indexOfAI = -1;

//	public GamePlayer(FieldInfo field , int homeX , int homeY) {
//		
//		this.field = field;
//		
//	}
	
	
	
	
	public UserInfo(String name, String password) {
		this.name = name;
		this.password = password;
	}

	
//	public void setManagerRemote(GameReceive observer){
//		this.observer = observer;
//	}


//	public GameReceive getObserver() {
//		return observer;
//	}
	
	public void removeUserClient(){
		this.notic = null;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	
	public void chooseAI(int indexOfAI){
		this.indexOfAI = indexOfAI;
	}
	
	public int getIndexOfAI(){
		return this.indexOfAI;
	}
	
	public GameNotice getNotic(){
		return this.notic;
	}
	
	public void setNotic(GameNotice notice){
		this.notic = notice;
	}
	
}
