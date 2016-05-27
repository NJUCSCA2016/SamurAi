package team.csca.control.netControl;

import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import team.csca.client.RemoteHelper;
import team.csca.link.tra.JPanelNetTra;
import team.csca.link.tra.NetPlayer;
import team.csca.server.GameNotFoundException;
import team.csca.server.GameReceive;

public class NetGameControl {
	private static NetGameControl CONTROL;
	
	private GameReceive receive = RemoteHelper.getInstance().getObserver();
	private JPanelNetTra panel;
	private NetPlayer player;
	/**
	 * This is the game ID
	 */
	private int indexOfGame;
	
	private NetGameControl(){}
	
	synchronized public static NetGameControl getInstance(){
		if(CONTROL == null){
			CONTROL = new NetGameControl();
		}
		return CONTROL;
	}
	
	public static void destroyControl(){
		CONTROL = null;
	}
	
//	public NetGameControl(GameNotice notice , JPanelNetTra panel) {
//		// TODO Auto-generated constructor stub
//		this.notice = notice;
//		this.panel = panel;
//	}
	
	public void initPanel(){
		this.panel = new JPanelNetTra(CONTROL);
	}
	public void setGameID(int indexOfGame) {
		this.indexOfGame = indexOfGame;
	}
	
	public int getIndexOfMyself(){
		return this.player.getIndex();
	}
	
	public JPanelNetTra getPanel(){
		return this.panel;
	}

	public void initGame(int[] basicInfo, int[] homeX, int[] homeY, int[] directions, int index , int[] occupation) {
		this.panel.initGame(basicInfo, homeX,  homeY,  directions, index , occupation);
	}

	public void feedBack(int[] basicInfo, int[] recoverRound, int[] curX, int[] curY, int[] direction,
			int[] occupation, int[] count) {
		this.panel.refresh(basicInfo , recoverRound , curX , curY , direction , occupation , count);
	}
	
	public void setPlayersName(String[] names){
		this.panel.setPlayersName(names);
	}

	public void changeCurIndex(int curIndex) {
		this.panel.changeCurIndex(curIndex);
	}

	public void gameOver(int[] occupy) {
		this.panel.gameOver(occupy);
	}


	public void setPlayerID(int yourIndex) {
		this.player = new NetPlayer(yourIndex, CONTROL);
	}

	public void takeAction(int actionCode) {
		//Use this method to release the pressure of Server
		if(this.panel.canTakeAction(actionCode)){
			try {
				this.receive.acceptActionTra(actionCode, indexOfGame);
			} catch (RemoteException | GameNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public KeyListener getListener() {
		return this.player.getListener();
	}
	
}
