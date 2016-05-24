package team.csca.link.signIn;

import java.awt.Graphics;

import javax.swing.JPanel;

import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgLink;

public class JPanelSignIn extends JPanel{
	
	private LayerBackground backgound ;
	
	private PlaintfField nameArea = new PlaintfField(635, 232, 160, 30);
			
	private PlainpswField passwordArea = new PlainpswField(660, 288, 160, 30);
	
	public JPanelSignIn() {
		
		setLayout(null);
		backgound = new LayerBackground(0, 0, 1250, 700, ImgLink.SIGNIN_BACK);
		this.add(nameArea);
		this.add(passwordArea);
		this.add(new JButtonLogin(this));
		this.add(new JButtonRegister(this));
		this.add(new JButtonReturn(this));
		
		
	}
	
	
	public void paintComponent(Graphics g){
		backgound.createWindow(g);
		super.paintComponents(g);
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
