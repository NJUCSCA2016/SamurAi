package server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 
 * 一个基于C/S模式的支持多人同时在线的帅的一比的由严格写的超级辣鸡的服务器
 * 
 * 其实我想偷偷添加聊天室功能的但是怕队友又骂我偷偷添加了需求0.0 . 如果能写的话让我默默的写出来吧嘿嘿嘿===
 * 
 * @author With You
 *
 */
public class Server {

	public Server() {
		initServer();
	}

	public static void main(String[] args) {
		
		new ServerWindow();

	}

	public void initServer() {
		LinkServer linkServer;
		try {
			linkServer = new LinkServer();
			LocateRegistry.createRegistry(8888);
			Naming.bind("rmi://localhost:8888/LinkServer", linkServer);
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
