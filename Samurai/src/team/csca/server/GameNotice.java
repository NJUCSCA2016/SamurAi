package team.csca.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameNotice extends Remote{
	/**
	 * 初始化信息反馈给每个Player
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public void initGame(int[] basicInfo , int[] homeX , int[] homeY , int[] directions , int index , int[] occupation) throws RemoteException;
	/**
	 * 将游戏信息反馈给指定的AI
	 * 
	 * @return 所返回的信息。
	 * 
	 * 反馈格式可参考AI中的GameInfo。
	 * 
	 * @throws RemoteException
	 */
	public void feedBack(int[] basicInfo , int[] recoverRound , int[] curX , int[] curY ,  int[] direction , int[] occupation , int[] count) throws RemoteException;
	
	/**
	 * 获取玩家的信息
	 * @return
	 * @throws RemoteException
	 */
	public void playersInfoGet(String[] playersName , int yourIndex) throws RemoteException;
	/**
	 *
	 * Notice the player the game information
	 * 
	 * @param indexOfGame   当前的游戏在所有游戏中的编号。根据这个来接收玩家的信息。
	 * @throws RemoteException
	 */
	public void notic(int indexOfGame) throws RemoteException;
	/**
	 * 
	 * Notice it's the ai's turn to take action
	 * @throws RemoteException
	 * 
	 */
	public void actionSign(int curIndex) throws RemoteException;
	/**
	 * Tell the game player the game is over and send the field for game statistics
	 * @param occupy
	 * @throws RemoteException
	 */
	public void gameOverSign(int[] count) throws RemoteException;
	
}
