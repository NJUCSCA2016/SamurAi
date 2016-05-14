package server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

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
	
	public Server() {
		initServer();
	}
	
	public static void main(String[] args) {
		
		new Server();
		
	}
	
	public void initServer(){
		LinkServer linkServer;
		try {
			linkServer = new LinkServer();
			LocateRegistry.createRegistry(8888);
			Naming.bind("rmi://localhost:8888/LinkServer",
					linkServer);
			System.out.println("Link to server");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
