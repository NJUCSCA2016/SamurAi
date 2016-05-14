package team.csca.view.link.signIn;

import java.awt.Graphics;

import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;

public class JPanelSignIn extends JPanel{
	
	private LayerBackground backgound = new LayerBackground(0, 0, 1250, 700, null);
	
	private PlaintfField nameArea = new PlaintfField(0, 0, 0, 0);
			
	private PlainpswField passwordArea = new PlainpswField(0, 0, 0, 0);
	
	public JPanelSignIn() {
		
		setLayout(null);
		
		this.add(nameArea);
		this.add(passwordArea);
		this.add(new JButtonLogin(this));
		this.add(new JButtonRegister(this));
		this.add(new JButtonReturn(this));
		
		
	}
	
	
	public void paintComponent(Graphics g){
		backgound.createWindow(g);
		super.paintComponent(g);
	}
	
	public String getName(){
		return this.nameArea.getText();
	}
	
	
	public String getPassword(){
		return new String(this.passwordArea.getPassword());
	}
	
}
