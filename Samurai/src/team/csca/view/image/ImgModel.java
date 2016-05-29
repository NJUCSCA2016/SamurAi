package team.csca.view.image;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImgModel {
	
	public final static Image[] CHOOSE_MOVIE;
	
	static{
		CHOOSE_MOVIE = new Image[16];
		for(int i = 0 ; i < 16 ; i ++){
			CHOOSE_MOVIE[i] = new ImageIcon("Image/ModelChoose/"+ (i+1) + ".png").getImage();
		}
		
	}
	
	public final static Image PROP_INIT = new ImageIcon("Image/ModelChoose/prop1").getImage();
	
	public final static Image PROP_ENTER = new ImageIcon("Image/ModelChoose/prop2").getImage();
	
	public final static Image PROP_CLICK = new ImageIcon("Image/ModelChoose/prop3").getImage();
	
	public final static Image TRA_INIT = new ImageIcon("Image/ModelChoose/tra1").getImage();
	
	public final static Image TRA_ENTER = new ImageIcon("Image/ModelChoose/tra2").getImage();
	
	public final static Image TRA_CLICK = new ImageIcon("Image/ModelChoose/tra3").getImage();
	
	public final static Image RETURN_INIT = new ImageIcon("Image/ModelChoose/return1").getImage();
	
	public final static Image RETURN_ENTER = new ImageIcon("Image/ModelChoose/return2").getImage();
	
	public final static Image RETURN_CLICK = new ImageIcon("Image/ModelChoose/return3").getImage();
	
}
