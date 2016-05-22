package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serviceImp.GameObserverImp;
import serviceImp.UserImp;
import team.csca.server.GameNotic;
import team.csca.server.GameReceive;
import team.csca.server.User;
/**
 * 
 * 接口实现类
 * @author With You
 *
 */

public class LinkServer extends UnicastRemoteObject implements User , GameReceive{
	
	/**
	 *   
	 */
	private static final long serialVersionUID = -16634514387895372L;

	private GameReceive receive;
	
	private User user;

	public LinkServer() throws RemoteException{
		super();
		//初始化两个Remote
		receive = GameObserverImp.getReceiveIns();
		user = new UserImp();
	}

	@Override
	public void acceptActionTro(int action) throws RemoteException {
		receive.acceptActionTro(action);
	}

	@Override
	public void acceptActionProp(int action) throws RemoteException {
		receive.acceptActionProp(action);
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
	public void chooseMoodle(int moodleCode , GameReceive observer) throws RemoteException {
		user.chooseMoodle(moodleCode , observer);
	}

	

}
