package serviceImp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import control.TRAGameControl;
import control.PPGameControl;
import team.csca.server.GameNotice;
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
	
	private final static File INFO_FILE = new File("InfoFile.txt");
	
	public final static List<UserInfo> USERS = new ArrayList<UserInfo>();
	
	/**
	 *  如果选择人数到达6个。开启新线程。
	 *  this two parameters are the ID of each game.
	 */
	private int modelOneClient = 0;
	
	private int modelTwoClient = 0;
	
	/**
	 * 服务器缓存。
	 * Current user
	 */
	private final static List<UserInfo> USER_CACHE = new ArrayList<UserInfo>();
//	private final static List<String> NAMES = new ArrayList<String>();
//	private final static List<String> PASSWORDS = new ArrayList<String>();
// 	
	
	
	private final static int SUCCEED = 0;
	private final static int NAME_INVALID = 1;
	private final static int PASSWORD_ERROR = 2;
	/**
	 *  The people who choose PP Game;
	 */
	private List<UserInfo> PPGameSeparate = new ArrayList<UserInfo>();
	/**
	 *  The people who choose traditional
	 */
	private List<UserInfo> TRAGameSeparate = new ArrayList<UserInfo>();
	/**
	 * The create of game id . which is based on the game that take already. which will make the increase of id is unique
	 */
	private int gameId = 0;
	
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
//		NAMES.add(userName);
//		PASSWORDS.add(password);
		return true;
	}
	
	
	
	
	@Override
	public int login(String userName, String password) throws RemoteException {
		System.out.println("UserName is : " + userName + "  Password is : " + password);

		int index = -1;
		for(int i = 0 ; i < USERS.size() ; i ++){
			if(USERS.get(i).getName().equals(userName)){
				index = i;
				break;
			}
		}
		if(index != -1){
			
			if(!USERS.get(index).getPassword().equals(password)){
				//密码错误
				return PASSWORD_ERROR;
			}
			UserInfo currentUser = this.getCurrentUser(userName, USERS);

			USER_CACHE.add(currentUser);
			
			//成功登陆。
			return UserImp.SUCCEED;
		}
		return NAME_INVALID;
		
	}

	@Override	
	public boolean logout(String userName) throws RemoteException {
		UserInfo userToLogout = this.getCurrentUser(userName, USER_CACHE);
		userToLogout.removeUserClient();
		return true;
	}

	
	
	/**
	 * Separate the characters by the fore and back the user choose model
	 */
	@Override
	synchronized public void chooseModel(int moodleCode , GameNotice notice , String name ) throws RemoteException {
		UserInfo linkGame = getCurrentUser(name, USER_CACHE);
		linkGame.setNotic(notice);
		if(moodleCode == 1){			
			this.TRAGameSeparate.add(linkGame);
			modelOneClient++;
			if(modelOneClient == 6){
					gameId ++ ;
					seperateTRA(gameId);
			}
		}else{
			this.PPGameSeparate.add(linkGame);
			modelTwoClient++;
			if(modelOneClient == 6){
				gameId ++;
				seperatePP(gameId);
			}
		}
	}
	

	@Override
	public void exitChoose(int moodle , String name) throws RemoteException {
		UserInfo linkGame = getCurrentUser(name, USER_CACHE);
		if(moodle == 1){
			TRAGameSeparate.remove(linkGame);
		}else{
			PPGameSeparate.remove(linkGame);
		}
	}
	
	
	private void seperatePP(int ID){
		List<UserInfo> pp = new ArrayList<UserInfo>();
		pp.addAll(PPGameSeparate);
		PPGameSeparate.clear();
		PPGameControl control  = new PPGameControl(pp, ID);
		GameReceiveImp.PPGAME_ON.add(control);
	}
	private void seperateTRA(int ID){
		List<UserInfo> tra = new ArrayList<UserInfo>();
		tra.addAll(TRAGameSeparate);
		TRAGameSeparate.clear();
		TRAGameControl control = new TRAGameControl(tra, ID);
		GameReceiveImp.TRAGAME_ON.add(control);
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
	
	
	/**
	 *  Data Write And Load
	 */
	public static void dataLoadFromDatabase(){
		ObjectInputStream stream = null;
		try{
			FileInputStream inputStream = new FileInputStream(INFO_FILE);
			stream = new ObjectInputStream(inputStream);
			while(stream.available() != 0){
				UserInfo info = (UserInfo)stream.readObject();
				USERS.add(info);
			}
			System.out.println("Go Here");
			stream.close();
			
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void dataWriteIntoDatabase(){
		ObjectOutputStream output = null;
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(INFO_FILE);
			output = new ObjectOutputStream(fileOutputStream);
			Iterator<UserInfo> infoIter = USERS.iterator();
			while(infoIter.hasNext()){
				output.writeObject(infoIter.next());
			}
			output.flush();
		} catch (IOException e) {
			// TODO: handle exception
		}finally {
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
