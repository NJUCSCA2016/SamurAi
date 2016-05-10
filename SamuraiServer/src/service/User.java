package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

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
	
	
	
}