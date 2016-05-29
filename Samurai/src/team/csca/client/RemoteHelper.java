package team.csca.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import team.csca.server.GameReceive;
import team.csca.server.User;

public class RemoteHelper {
	
	private static RemoteHelper remoteHelper = new RemoteHelper();
	private GameNoticeImp gameNoticeImp;
	
	private String userName = null;
	private int moodle = 0;
	
	public static RemoteHelper getInstance(){
		return remoteHelper;
	}
	
	private RemoteHelper(){
		try {
			gameNoticeImp = new GameNoticeImp();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public GameNoticeImp getNotic(){
		return gameNoticeImp;
	}
	
	public void setName(String name){
		this.userName = name;
	}
	
	public String getName(){
		return this.userName;
	}
	
	public void setMoodle(int moodle){
		this.moodle = moodle;
	}
	
	public int getMoodle(){
		return this.moodle;
	}
	
}
