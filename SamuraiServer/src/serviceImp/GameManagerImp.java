package serviceImp;

import java.rmi.RemoteException;

import team.csca.server.GameObserver;
/**
 * 
 * GameObserver代理实现类
 * @author With You
 *
 */
public class GameManagerImp implements GameObserver{

	@Override
	/**
	 * 请严格按照格式： Action1 + space + Action2 + space + Action3 ******
	 */
	public void acceptActionTro(String action) throws RemoteException {
		
		
	}

	@Override
	public void acceptActionProp(String action) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String initGame() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String feedBack() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
