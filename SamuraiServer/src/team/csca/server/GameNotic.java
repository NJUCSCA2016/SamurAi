package team.csca.server;

import java.rmi.RemoteException;

public interface GameNotic {
	/**
	 * 初始化信息反馈给每个Player
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public String initGame() throws RemoteException;
	/**
	 * 将游戏信息反馈给指定的AI
	 * 
	 * @return 所返回的信息。
	 * 
	 * 反馈格式可参考AI中的GameInfo。
	 * 
	 * @throws RemoteException
	 */
	public void feedBack() throws RemoteException;
	
	/**
	 * 获取玩家的信息
	 * @return
	 * @throws RemoteException
	 */
	public void playersInfoGet(String[] playersName , int yourIndex) throws RemoteException;
	
	
}
