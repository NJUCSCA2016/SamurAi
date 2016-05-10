package team.csca.client;

import java.rmi.Remote;

import team.csca.server.GameObsever;
import team.csca.server.User;

public class RemoteHelper {
	
	public final static RemoteHelper REMOTE_HELPER = new RemoteHelper();
	
	private RemoteHelper(){
	}
	
	private Remote remote;

	public void setRemote(Remote remote) {
		this.remote = remote;
	}
	/**
	 * @return 返回游戏监控器
	 */
	
	public GameObsever getObserver(){
		return (GameObsever) remote;
	}
	/**
	 * @return 返回用户登入登出权限。
	 */
	public User getUser(){
		return (User) remote;
	}
	
}
