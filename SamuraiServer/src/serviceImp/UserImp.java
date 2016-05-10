package serviceImp;

import java.rmi.RemoteException;
import java.util.ArrayList;

import service.User;

public class UserImp implements User{
	
	/**
	 * 储存用户名和密码。
	 * 我其实想用Java sql 的 如果万总会的话万总来试试。将check方法改成基于sql的实现方法。
	 */
	private final static ArrayList<String> USERNAMES = new ArrayList<String>();
	private final static ArrayList<String> PASSWORDS = new ArrayList<String>();
	
	private final static int SUCCEED = 0;
	private final static int NAME_INVALID = 1;
	private final static int PASSWORD_ERROR = 2;
	
	@Override
	public boolean signIn(String userName, String password) throws RemoteException {
		if(USERNAMES.contains(userName)){
			return false;
		}
		//执行注册操作
		USERNAMES.add(userName);
		PASSWORDS.add(password);
		// 注册成功
		return true;
	}
	
	@Override
	public int login(String userName, String password) throws RemoteException {
		
		if(! USERNAMES.contains(userName)){
			//用户名不存在
			return NAME_INVALID;
		}
		int indexOfName = USERNAMES.indexOf(userName);
		if(! PASSWORDS.get(indexOfName) .equals(password)){
			//密码错误
			return PASSWORD_ERROR;
		}
		//成功登陆。
		return UserImp.SUCCEED;
	
	}

	@Override
	public boolean logout(String userName) throws RemoteException {
		return true;
	}

	

}
