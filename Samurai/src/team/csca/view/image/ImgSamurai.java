package team.csca.view.image;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 武士的图片
 * 
 * @author Water
 *
 */
public class ImgSamurai {
	public static final Image A0 = new ImageIcon("Image/AI/A0.png").getImage();

	public static final Image A1 = new ImageIcon("Image/AI/A1.png").getImage();

	public static final Image A2 = new ImageIcon("Image/AI/A2.png").getImage();

	public static final Image B0 = new ImageIcon("Image/AI/B0.png").getImage();

	public static final Image B1 = new ImageIcon("Image/AI/B1.png").getImage();

	public static final Image B2 = new ImageIcon("Image/AI/B2.png").getImage();

	public static final Image A0_LEFT = new ImageIcon("Image/AI/A0_LEFT.png").getImage();

	public static final Image A1_LEFT = new ImageIcon("Image/AI/A1_LEFT.png").getImage();

	public static final Image A2_LEFT = new ImageIcon("Image/AI/A2_LEFT.png").getImage();

	public static final Image B0_LEFT = new ImageIcon("Image/AI/B0_LEFT.png").getImage();

	public static final Image B1_LEFT = new ImageIcon("Image/AI/B1_LEFT.png").getImage();

	public static final Image B2_LEFT = new ImageIcon("Image/AI/B2_LEFT.png").getImage();

	public static final Image A0_RIGHT = new ImageIcon("Image/AI/A0_RIGHT.png").getImage();

	public static final Image A1_RIGHT = new ImageIcon("Image/AI/A1_RIGHT.png").getImage();

	public static final Image A2_RIGHT = new ImageIcon("Image/AI/A2_RIGHT.png").getImage();

	public static final Image B0_RIGHT = new ImageIcon("Image/AI/B0_RIGHT.png").getImage();

	public static final Image B1_RIGHT = new ImageIcon("Image/AI/B1_RIGHT.png").getImage();

	public static final Image B2_RIGHT = new ImageIcon("Image/AI/B2_RIGHT.png").getImage();

	public static final Image A0_BACK = new ImageIcon("Image/AI/A0_BACK.png").getImage();

	public static final Image A1_BACK = new ImageIcon("Image/AI/A1_BACK.png").getImage();

	public static final Image A2_BACK = new ImageIcon("Image/AI/A2_BACK.png").getImage();

	public static final Image B0_BACK = new ImageIcon("Image/AI/B0_BACK.png").getImage();

	public static final Image B1_BACK = new ImageIcon("Image/AI/B1_BACK.png").getImage();

	public static final Image B2_BACK = new ImageIcon("Image/AI/B2_BACK.png").getImage();

	public static final Image A0_FLAG = new ImageIcon("Image/Gamepanel/flags/flag1.png").getImage();

	public static final Image A1_FLAG = new ImageIcon("Image/Gamepanel/flags/flag2.png").getImage();

	public static final Image A2_FLAG = new ImageIcon("Image/Gamepanel/flags/flag3.png").getImage();

	public static final Image B0_FLAG = new ImageIcon("Image/Gamepanel/flags/flag4.png").getImage();

	public static final Image B1_FLAG = new ImageIcon("Image/Gamepanel/flags/flag5.png").getImage();

	public static final Image B2_FLAG = new ImageIcon("Image/Gamepanel/flags/flag6.png").getImage();

	/**
	 * 以数组的形式来表示每一个AI的各方向图片，方便通过direction来索引
	 * TODO:把阴影的照片给添加进去
	 * direction
	 */
	public static final Image[] A0_PICTURE = { A0, A0_BACK, A0_LEFT, A0_RIGHT };

	public static final Image[] A1_PICTURE = { A1, A1_BACK, A1_LEFT, A1_RIGHT };

	public static final Image[] A2_PICTURE = { A2, A2_BACK, A2_LEFT, A2_RIGHT };

	public static final Image[] B0_PICTURE = { B0, B0_BACK, B0_LEFT, B0_RIGHT };

	public static final Image[] B1_PICTURE = { B1, B1_BACK, B1_LEFT, B1_RIGHT };

	public static final Image[] B2_PICTURE = { B2, B2_BACK, B2_LEFT, B2_RIGHT };

	public static final Image A0_SHADOW = new ImageIcon("Image/Gamepanel/grids/gridA0.png").getImage();

	public static final Image A1_SHADOW = new ImageIcon("Image/Gamepanel/grids/gridA1.png").getImage();

	public static final Image A2_SHADOW = new ImageIcon("Image/Gamepanel/grids/gridA2.png").getImage();

	public static final Image B0_SHADOW = new ImageIcon("Image/Gamepanel/grids/gridB0.png").getImage();

	public static final Image B1_SHADOW = new ImageIcon("Image/Gamepanel/grids/gridB1.png").getImage();

	public static final Image B2_SHADOW = new ImageIcon("Image/Gamepanel/grids/gridB2.png").getImage();

	/**
	 * 以数组的形式来表示所有AI的阴影，方便通过index来索引
	 * 
	 * index
	 */
	public static final Image[] AI_SHADOW = { A0_SHADOW, A1_SHADOW, A2_SHADOW, B0_SHADOW, B1_SHADOW, B2_SHADOW };
}
