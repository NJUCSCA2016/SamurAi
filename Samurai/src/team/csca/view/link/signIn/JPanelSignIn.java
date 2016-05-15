package team.csca.view.link.signIn;

import java.awt.Graphics;

import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgLink;

public class JPanelSignIn extends JPanel{
	
	private LayerBackground backgound = new LayerBackground(0, 0, 1250, 700, ImgLink.SIGNIN_BACK);
	
	private PlaintfField nameArea = new PlaintfField(775, 225, 190, 45);
			
	private PlainpswField passwordArea = new PlainpswField(775, 305, 150, 45);
	
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
	
	
	public void cleanName(){
		this.nameArea.setText("");
	}
	
	public void cleanPass(){
		this.passwordArea.setText("");
	}
	
	public String getName(){
		return this.nameArea.getText();
	}
	
	
	public String getPassword(){
		return new String(this.passwordArea.getPassword());
	}
	
}
