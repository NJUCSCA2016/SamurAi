package control;

import java.util.List;

import user.UserInfo;

public class PPGameControl extends Control{

	public PPGameControl(List<UserInfo> sixplayers, int gameId) {
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
	
}
