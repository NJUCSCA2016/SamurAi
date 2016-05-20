package serviceImp;

import java.rmi.RemoteException;
import java.util.List;

import team.csca.server.GameNotic;
import team.csca.server.GameReceive;
import user.UserInfo;
/**
 * 
 * GameObserver代理实现类
 * @author With You
 *
 */
public class GameObserverImp implements GameReceive , GameNotic{

	private final static GameObserverImp imp = new GameObserverImp();
	
	private GameObserverImp() {}
	
	public static GameReceive getReceiveIns(){
		return (GameReceive)imp;
	}
	
	public static GameNotic getNoticIns(){
		return (GameNotic)imp;
	}
	@Override
	/**
	 * 请严格按照格式： Action1 + space + Action2 + space + Action3 ******
	 */
	public void acceptActionTro(int action) throws RemoteException {
		
		
	}

	@Override
	public void acceptActionProp(int action) throws RemoteException {
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

	@Override
	public String[] playersInfoGet() throws RemoteException {
		String[] names = new String[6];
		return null;
	}
	
}
