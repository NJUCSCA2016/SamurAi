package team.csca.client;

import java.rmi.Remote;

import team.csca.server.GameReceive;
import team.csca.server.User;

public class RemoteHelper {
	
	private static RemoteHelper remoteHelper = new RemoteHelper();
	public static RemoteHelper getInstance(){
		return remoteHelper;
	}
	
	private Remote remote;

	public void setRemote(Remote remote) {
		this.remote = remote;
	}
	/**
	 * @return 返回游戏监控器
	 */
	
	public GameReceive getObserver(){
		return (GameReceive) remote;
	}
	/**
	 * @return 返回用户登入登出权限。
	 */
	public User getUser(){
		return (User) remote;
	}
	
}
