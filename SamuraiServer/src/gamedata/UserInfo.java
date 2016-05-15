package gamedata;

import team.csca.server.GameObserver;
import team.csca.server.User;

public class UserInfo {	
	
	private User user;
	
	private GameObserver observer;
	
	private String name = null;
	
	private String password = null;

	public UserInfo(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public void setUserRemote(User user){
		this.user = user;
	}
	
	public void setManagerRemote(GameObserver observer){
		this.observer = observer;
	}

	public User getUser() {
		return user;
	}

	public GameObserver getObserver() {
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
	
	

}
