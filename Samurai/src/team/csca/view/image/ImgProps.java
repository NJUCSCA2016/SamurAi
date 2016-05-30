package team.csca.view.image;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 道具模式
 * @author Water
 *
 */
public class ImgProps {
	/**
	 * HP + 1
	 */
	public static final Image ANOTHER_LIFE = new ImageIcon("Image/Gamepanel/props/hp.png").getImage();
	/**
	 * 本回合的体力值加5
	 */
	public static final Image ADD_NOWPOWER = new ImageIcon("Image/Gamepanel/props/nowPower.png").getImage();
	/**
	 * 增加视野
	 */
	public static final Image ADD_SIGHT = new ImageIcon("Image/Gamepanel/props/sight.png").getImage();
	
	public static final Image TRANSMISSION = new ImageIcon("Image/Gamepanel/props/transmission.png").getImage();
}
