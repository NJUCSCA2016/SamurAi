package team.csca.controller.gamecontrol;

import team.csca.controller.gameservice.PPService;

public class PPControl {
	
	private PPService service;
	
	public PPControl(PPService service){
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
	public void useProp(int propNum){
		
	}
	
	
}
