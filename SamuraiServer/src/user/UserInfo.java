package user;

import team.csca.server.GameReceive;
import team.csca.server.User;

public class UserInfo {	
	
	private User user;
	
	private GameReceive observer;
	
	private String name = null;
	
	private String password = null;

	private int indexOfAI = -1;
	
	public UserInfo(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public void setUserRemote(User user){
		this.user = user;
	}
	
	public void setManagerRemote(GameReceive observer){
		this.observer = observer;
	}

	public User getUser() {
		return user;
	}

	public GameReceive getObserver() {
		return observer;
	}
	
	public void removeUserClient(){
		this.user = null;
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
