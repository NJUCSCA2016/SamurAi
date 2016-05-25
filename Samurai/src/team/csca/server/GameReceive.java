package team.csca.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 游戏监控器接口
 * @author With You
 *
 */
public interface GameReceive extends Remote{
	/**
	 * 每一步都会传入Action
	 * 接受传统模式用户传来的动作
	 * 
	 * @param action
	 * @throws RemoteException
	 * @throws GameNotFoundException 
	 */
	public void acceptActionTra(int action , int indexOfGame) throws RemoteException, GameNotFoundException;
	/**
	 * 接受道具模式下用户传来的动作。
	 * 
	 * @param action 动作
	 * @throws RemoteException
	 * @throws GameNotFoundException 
	 */
	public void acceptActionProp(int action , int indexOfGame) throws RemoteException, GameNotFoundException;
	/**
	 * User Finished Action Sign
	 * @throws RemoteException
	 * @throws GameNotFoundException 
	 */
	public void acceptTRAActionFinishedSign(int indexOfGame)throws RemoteException, GameNotFoundException;
	public void acceptPPActionFinishedSign(int indexOfGame)throws RemoteException, GameNotFoundException;
}
