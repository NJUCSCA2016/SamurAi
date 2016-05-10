package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.GameObserver;
import service.User;
import serviceImp.GameManagerImp;
import serviceImp.UserImp;
/**
 * 
 * 接口实现类
 * @author With You
 *
 */

public class GameServer extends UnicastRemoteObject implements User , GameObserver{
	
	private GameObserver gameObserver;
	
	private User user;
	
	public GameServer() throws RemoteException{
		super();
		//初始化两个Remote
		gameObserver = new GameManagerImp();
		user = new UserImp();
	}

	@Override
	public void acceptActionTro(String action) throws RemoteException {
		gameObserver.acceptActionTro(action);
	}

	@Override
	public void acceptActionProp(String action) throws RemoteException {
		gameObserver.acceptActionProp(action);
	}

	@Override
	public String initGame() throws RemoteException {
		return gameObserver.initGame();
	}

	@Override
	public String feedBack() throws RemoteException {
		return gameObserver.feedBack();
	}

	@Override
	public boolean signIn(String userName, String password) throws RemoteException {
		return user.signIn(userName, password);
	}

	@Override
	public int login(String userName, String password) throws RemoteException {
		return user.login(userName, password);
	}

	@Override
	public boolean logout(String userName) throws RemoteException {
		return user.logout(userName);
	}

	@Override
	public void chooseMoodle(int moodleCode) throws RemoteException {
		user.chooseMoodle(moodleCode);
	}
	
	

}
