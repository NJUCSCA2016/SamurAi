package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 游戏监控器接口
 * @author With You
 *
 */
public interface GameObserver extends Remote{
	/**
	 * 接受传统模式用户传来的动作
	 * 
	 * @param action
	 * @throws RemoteException
	 */
	public void acceptActionTro(String action) throws RemoteException;
	/**
	 * 接受道具模式下用户传来的动作。
	 * 
	 * @param action 动作。使用split("\\s")即可进行分割
	 * @throws RemoteException
	 */
	public void acceptActionProp(String action) throws RemoteException;
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
	public String feedBack() throws RemoteException;
	
}
