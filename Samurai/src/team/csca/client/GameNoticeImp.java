package team.csca.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import team.csca.control.netControl.NetGameControl;
import team.csca.server.GameNotice;

public class GameNoticeImp extends UnicastRemoteObject implements GameNotice{
	protected GameNoticeImp() throws RemoteException {
		super();
	}

	private NetGameControl control;
	
	private boolean unNotice = true;
	
	public void setControl(NetGameControl control) {
		this.control = control;
	}
	
	@Override
	public void initGame(int[] basicInfo, int[] homeX, int[] homeY, int[] directions, int index , int[] occupation)
		throws RemoteException {
		this.control.initGame(basicInfo,  homeX, homeY, directions, index , occupation);
	}

	@Override
	public void feedBack(int[] basicInfo, int[] recoverRound, int[] curX, int[] curY,  int[] direction, int[] occupation , int[] count)
			throws RemoteException {
		this.control.feedBack(basicInfo , recoverRound , curX , curY , direction , occupation , count);
	}

	@Override
	public void playersInfoGet(String[] playersName, int yourIndex) throws RemoteException {
		this.control.setPlayersName(playersName);
		this.control.setPlayerID(yourIndex);
	}

	@Override
	public void notic(int indexOfGame) throws RemoteException {
		this.control.initPanel();
		this.unNotice = false;
		this.control.setGameID(indexOfGame);
	}

	@Override
	public void actionSign(int curIndex) throws RemoteException {
		this.control.changeCurIndex(curIndex);
	}

	@Override
	public void gameOverSign(int[] count) throws RemoteException {
		this.control.gameOver(count);
	}
	
	public boolean unNotice(){
		return this.unNotice;
	}
	
	

}
