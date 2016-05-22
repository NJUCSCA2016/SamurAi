package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import user.GamePlayer;

public class InfoForPlayer extends UnicastRemoteObject{
	
	private int weapon;
	
	private int[] aiX  ;
	
	private int[] aiY ;
	

	
	protected InfoForPlayer() throws RemoteException {
		super();
	}
	
	
	
	
	
}
