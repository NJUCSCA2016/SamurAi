package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 
 * 服务器
 * 
 * 迭代一 ：  该服务器智能实现单个游戏的开启 ， 不支持多游戏开启
 * 
 * @author With You
 *
 */
public class Server {
	
	private static Registry registry;
	
	
	public static void main(String[] args) {
		
		GameServer gameServer ;
		
		try {
			gameServer = new GameServer();
			
			registry = LocateRegistry.createRegistry(8808);
			
			registry.bind("GameServer", gameServer);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
