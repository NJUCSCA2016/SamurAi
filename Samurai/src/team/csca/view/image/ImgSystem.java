package team.csca.view.image;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImgSystem {
	
	// 图标
	public static Image logo = new ImageIcon("Image/Others/Logo.png").getImage();
	
	// 鼠标
	// TODO: 这个鼠标实在太丑了，需要换一个
	public static Image cursor = new ImageIcon("Image/Others/1.png").getImage();
	
	public final static Image TICK = new ImageIcon("Image/Others/tick.png").getImage();
	
	public final static Image LOADING = new ImageIcon("Image/Others/loading.gif").getImage();
}
