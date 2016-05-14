package team.csca.client;

public class ServerNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServerNotFoundException() {
		System.out.println("No Server Is Found");
	}
}
