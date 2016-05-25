package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import control.GameNotFoundException;
import serviceImp.GameReceiveImp;
import serviceImp.UserImp;
import team.csca.server.GameNotice;
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
		receive = GameReceiveImp.getReceiveIns();
		user = new UserImp();
		UserImp.dataLoadFromDatabase();
	}

	@Override
	public void acceptActionTra(int action , int indexOfGame) throws RemoteException, GameNotFoundException {
		receive.acceptActionTra(action , indexOfGame);
	}

	@Override
	public void acceptActionProp(int action , int indexOfGame) throws RemoteException, GameNotFoundException {
		receive.acceptActionProp(action , indexOfGame);
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
	public void chooseModle(int moodleCode , GameNotice notice , String name) throws RemoteException {
		user.chooseModle(moodleCode , notice , name);
	}

	@Override
	public void acceptTRAActionFinishedSign(int indexOfGame) throws RemoteException, GameNotFoundException {
		receive.acceptTRAActionFinishedSign(indexOfGame);
	}

	@Override
	public void acceptPPActionFinishedSign(int indexOfGame) throws RemoteException, GameNotFoundException {
		receive.acceptTRAActionFinishedSign(indexOfGame);
	}

	

}
