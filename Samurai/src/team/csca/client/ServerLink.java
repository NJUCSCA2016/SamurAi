package team.csca.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ServerLink {
	private RemoteHelper remoteHelper;
	
	public ServerLink() throws ServerNotFoundException{
		linkToServer();
	}
	
	private void linkToServer() throws ServerNotFoundException {
		try {
			remoteHelper = RemoteHelper.getInstance();
			remoteHelper.setRemote(Naming.lookup("rmi://localhost:8888/LinkServer"));
			System.out.println("linked");
		} catch (MalformedURLException e) {
			throw new ServerNotFoundException();
		} catch (RemoteException e) {
			throw new ServerNotFoundException();
		} catch (NotBoundException e) {
			throw new ServerNotFoundException();
		}
	}
	

	public void test(){
		try {
			System.out.println(remoteHelper.getUser().login("admin", "123456a" , RemoteHelper.getInstance().getUser()));
//			System.out.println(remoteHelper.getIOService().writeFile("2", "admin", "testFile"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
}
