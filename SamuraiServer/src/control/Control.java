package control;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import data.FieldInfo;
import team.csca.server.GameNotice;
import user.UserInfo;

/**
 * 
 * The control of Net Field and Players
 * @author With You
 *
 */
public abstract class Control {
	
	public final int GAME_ID;
	/**
	 * To notify the players
	 */
	protected final List<UserInfo> sideOne ;
	
	protected final List<UserInfo> sideTwo;
	
	protected int curPlayer = 0 ;
	
	protected final FieldInfo field;
	
	public Control(List<UserInfo> sixplayers , int gameId) {
		
		this.GAME_ID = gameId;
		//Separate the player into two sides
		sideOne = new ArrayList<UserInfo>(3);
		sideTwo = new ArrayList<UserInfo>(3);
		for(int i = 0 ; i < 3 ; i ++ ){
			UserInfo one = sixplayers.get(i);
			one.chooseAI(i);
			UserInfo two = sixplayers.get(i + 3);
			two.chooseAI(i + 3);
			sideOne.add(one);
			sideTwo.add(two);
			// This step is to inform the client the id of the game .
			try {
				one.getNotic().notic(gameId);
				two.getNotic().notic(gameId);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		this.field = new FieldInfo(this);
	}
	
	
	
	/**
	 * 初始化客户端战场。
	 * 
	 * @param basicInfo
	 * @param homeX
	 * @param homeY
	 */
	
	public void initClientGame(int[] basicInfo , int[] homeX , int[] homeY , int[] directions){
		for(int i = 0 ; i < 3 ; i++){
			UserInfo playerOne = this.sideOne.get(i);
			UserInfo playerTwo = this.sideTwo.get(i);
			GameNotice sideOne = playerOne.getNotic();
			GameNotice sideTwo = playerTwo.getNotic();
			int[] newBasicInfoOne = new int[basicInfo.length];
			int[] newBasicInfoTwo = new int[basicInfo.length];
			System.arraycopy(basicInfo, 0, newBasicInfoOne, 0, basicInfo.length);
			System.arraycopy(basicInfo, 0, newBasicInfoTwo, 0, basicInfo.length);
			try {
				newBasicInfoOne[1] = i;
				newBasicInfoTwo[1] = i + 3;
				sideOne.initGame(newBasicInfoOne, homeX, homeY ,  directions , i );
				sideTwo.initGame(newBasicInfoTwo, homeX, homeY , directions , i + 3);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		noticAction(this.field.index);
	}
	
	public abstract void handleAction(int actionCode);
	
	public void noticAction(int curIndex){
		try{
			if(curIndex >= 3){
				this.sideTwo.get(curIndex - 3).getNotic().actionSign();
			}else{
				this.sideOne.get(curIndex).getNotic().actionSign();
			}
		}catch(RemoteException ex){
			ex.printStackTrace();
		}
	}
	
}
