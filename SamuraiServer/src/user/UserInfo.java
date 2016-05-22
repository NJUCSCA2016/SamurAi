package user;

import team.csca.server.GameNotic;
import team.csca.server.GameReceive;

public class UserInfo {	
	
	private GameNotic notic;
	
	private GameReceive observer;
	
	private String name = null;
	
	private String password = null;

	private int indexOfAI = -1;
	
	public UserInfo(String name, String password) {
		this.name = name;
		this.password = password;
	}

	
	public void setManagerRemote(GameReceive observer){
		this.observer = observer;
	}

	public GameNotic getUser() {
		return notic;
	}

	public GameReceive getObserver() {
		return observer;
	}
	
	public void removeUserClient(){
		this.notic = null;
		this.observer =null;
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

}
