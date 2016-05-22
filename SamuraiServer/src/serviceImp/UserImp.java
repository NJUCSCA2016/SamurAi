package serviceImp;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import team.csca.server.GameNotic;
import team.csca.server.GameReceive;
import team.csca.server.User;
import user.UserInfo;
/**
 * 
 * User 代理实现类
 * @author With You
 *
 */
public class UserImp implements User{
	
	/**
	 * 
	 * 储存用户名和密码。
	 *  以及客户端的位置。
	 *  就不用数据库存了。如果能搞定的话我试试。
	 */
	
	public final static List<UserInfo> USERS = new ArrayList<UserInfo>();
	
	/**
	 *  如果选择人数到达6个。开启新线程。
	 */
	private int modelOneClient = 0;
	
	private int modelTwoClient = 0;
	
	/**
	 * 服务器缓存。
	 */
	private final static List<UserInfo> USER_CACHE = new ArrayList<UserInfo>();
	private final static List<String> NAMES = new ArrayList<String>();
	private final static List<String> PASSWORDS = new ArrayList<String>();
 	
	private final static int SUCCEED = 0;
	private final static int NAME_INVALID = 1;
	private final static int PASSWORD_ERROR = 2;
	
	@Override
	public boolean signIn(String userName, String password) throws RemoteException {
		System.out.println("UserName is : " + userName + "  Password is : " + password);
		Iterator<UserInfo> userItr = USERS.iterator();
		while(userItr.hasNext()){
			UserInfo each = userItr.next();
			if(each.getName().equals(userName)){
				return false;
			}
		}
		// 注册成功
		UserImp.USERS.add(new UserInfo(userName, password));
		
		return true;
	}
	
	
	
	
	@Override
	public int login(String userName, String password) throws RemoteException {
		
		if(! NAMES.contains(userName)){
			//用户名不存在
			return NAME_INVALID;
		}
		int indexOfName = NAMES.indexOf(userName);
		if(! PASSWORDS.get(indexOfName) .equals(password)){
			//密码错误
			return PASSWORD_ERROR;
		}
		UserInfo currentUser = this.getCurrentUser(userName, USERS);

		USER_CACHE.add(currentUser);
		//成功登陆。
		return UserImp.SUCCEED;
//	
	}

	@Override	
	public boolean logout(String userName) throws RemoteException {
		UserInfo userToLogout = this.getCurrentUser(userName, USER_CACHE);
		userToLogout.removeUserClient();
		return true;
	}

	
	

	@Override
	public void chooseMoodle(int moodleCode , GameReceive observer) throws RemoteException {
		
		
		
	}
	
	
	private UserInfo getCurrentUser(String userName , List<UserInfo> listToFind){
		Iterator<UserInfo> cacheIter = listToFind.iterator();
		while(cacheIter.hasNext()){
			UserInfo each =cacheIter.next();
			if(each.getName().equals(userName)){
				return each;
			}
		}
		return null;
	}
}
