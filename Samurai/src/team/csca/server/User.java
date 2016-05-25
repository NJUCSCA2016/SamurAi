package team.csca.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 
 * 用户登入登出接口。
 * @author With You
 *
 */
public interface User extends Remote{
	
	/**
	 * 用户注册
	 * @param userName 用户名
	 * @param password 注册密码
	 * @return 是否成功注册。如果用户名重叠返回false
	 * @throws RemoteException
	 */
	public boolean signIn(String userName , String password) throws RemoteException;
	/**
	 * 用户登录。
	 * @param userName  用户名
	 * @param password   密码
	 * @return 成功登入
	 * @throws RemoteException
	 */
	public int login(String userName , String password) throws RemoteException;
	/**
	 * 用户注销登陆
	 * @param userName 用户名
	 * @return  成功退出
	 * @throws RemoteException
	 */
	public boolean logout(String userName) throws RemoteException;
	/**
	 * 房主选择模式 。
	 * 第一个登陆的人作为房主
	 * @param moodleCode
	 * @throws RemoteException
	 */
	public void chooseModle(int moodleCode , GameNotice notice , String name) throws RemoteException;
	
	
	
}
