package team.csca.controller;


import team.csca.controller.Controller;
import team.csca.view.MainFrame;

public class Controller {
	private MainFrame frame;
	
	private static Controller controller;
	
	public static Controller getController(){
		if(controller == null){
			controller = new Controller();
		}
		return controller;
	}
	
	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}
	
}
