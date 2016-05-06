package team.csca.controller;


import team.csca.view.frame.JFrameMain;

public class Controller {
	private JFrameMain frame;
	
	private static Controller controller;
	
	public static Controller getController(){
		if(controller == null){
			controller = new Controller();
		}
		return controller;
	}
	
	public void setFrame(JFrameMain frame) {
		this.frame = frame;
	}
	
}
