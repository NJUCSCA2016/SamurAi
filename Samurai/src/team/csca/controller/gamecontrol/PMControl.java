package team.csca.controller.gamecontrol;

import team.csca.controller.gameservice.PMService;

public class PMControl {
	
	private PMService service;
	
	public PMControl(PMService service) {
		this.service = service;
	}
	
	public void move(int direction){
		service.move(direction);
	}
	public void occupy(int direction){
		service.move(direction);
	}
	public void show(){
		service.show();
	}
	public void hide(){
		service.hide();
	}
}
