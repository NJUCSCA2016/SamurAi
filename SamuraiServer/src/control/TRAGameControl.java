package control;

import java.rmi.RemoteException;
import java.util.List;

import user.UserInfo;

public class TRAGameControl extends Control{

	public TRAGameControl(List<UserInfo> sixplayers, int gameId) {
		super(sixplayers, gameId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleAction(int actionCode) {
		switch (actionCode) {
		case  1 :
			this.field.attackUp();
			break;
		case 2 :
			this.field.attackDown();
			break;
		case 3 :
			this.field.attackLeft();
			break;
		case 4 :
			this.field.attackRight();
			break;
		case 5 :
			this.field.moveUp();
			break;
		case 6 :
			this.field.moveDown();
			break;
		case 7 :
			this.field.attackLeft();
			break;
		case 8 :
			this.field.attackRight();
			break;
		case 9 :
			this.field.hideMe();
			break;
		case 10:
			this.field.showMe();
			break;
		case 0 :
			this.field.changeCharacter();
			break;
		default:
			break;
		}
		//TODO : Fead back
		
	}
	
	public void feedBack(int army , int[] basicInfo , int[] recoverRound , int[] curX , int[] curY ,  int[] direction , int[] occupation , int[] count){
		try{
			if(army == 1){
				for(int i = 0 ; i < sideOne.size() ; i ++){
					sideOne.get(i).getNotic().feedBack(basicInfo, recoverRound, curX, curY,  direction, occupation, count);
				}
			}else{
				for(int i = 0 ; i < sideTwo.size() ; i ++){
					sideTwo.get(i).getNotic().feedBack(basicInfo, recoverRound, curX, curY,  direction, occupation , count);
				}
			}
		}catch(RemoteException ex){
			ex.printStackTrace();
		}
	}
	
}
