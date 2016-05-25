package team.csca.server;

public class GameNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void printStackTrace(){
		System.err.println("Can't Find The Assigned Game");
	}
	
}
