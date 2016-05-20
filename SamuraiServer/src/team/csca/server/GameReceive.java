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
	 */
	public void acceptActionTro(int action) throws RemoteException;
	/**
	 * 接受道具模式下用户传来的动作。
	 * 
	 * @param action 动作。使用split("\\s")即可进行分割
	 * @throws RemoteException
	 */
	public void acceptActionProp(int action) throws RemoteException;

	
}
