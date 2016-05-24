package team.csca.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import control.GameNotFoundException;
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
	 * @param action 动作。使用split("\\s")即可进行分割
	 * @throws RemoteException
	 * @throws GameNotFoundException 
	 */
	public void acceptActionProp(int action , int indexOfGame) throws RemoteException, GameNotFoundException;
	/**
	 * User Finished Action Sign
	 * @throws RemoteException
	 */
	public void acceptActionFinishedSign()throws RemoteException;
	
}
