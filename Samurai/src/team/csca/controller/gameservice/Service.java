package team.csca.controller.gameservice;

public interface Service {
	public void occupy(int direction);
	
	public void move(int direction);
	
	public void hide();
	
	public void show();
	
	public boolean canOccupy(int direction);
	
	public boolean canMove(int direction);
	
	public boolean canHide();
	
	public boolean canShow();
	
}
