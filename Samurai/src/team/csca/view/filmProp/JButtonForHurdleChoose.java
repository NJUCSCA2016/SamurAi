package team.csca.view.filmProp;

import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgHurdle;

public class JButtonForHurdleChoose extends DynamicButton{

	private final static int[] XOfHurdle = new int[]{50, 50 , 450 , 670 , 850};
	private final static int[] YOfHurdle = new int[]{530 , 350 , 200 , 110 , 20};
	
	private final static int[] WidthOfHurdle = new int[]{250 , 280 , 220 , 210 , 180};
	private final static int[] HeightOfHurdle = new int[]{170 , 175 , 245 , 185 , 125};
	
	private int hurdle;
	
	private JPanelHurdle fatherPanel;
	
	public JButtonForHurdleChoose(int hurdle , JPanelHurdle fatherPanel) {
		
		super(XOfHurdle[hurdle],
				YOfHurdle[hurdle],
				WidthOfHurdle[hurdle],
				HeightOfHurdle[hurdle],
				ImgHurdle.HURDLES_INIT[hurdle], 
				ImgHurdle.HURDLES_ENTER[hurdle] , 
				ImgHurdle.HURDLES_CLICKED[hurdle]
		);
		
		this.hurdle = hurdle;
		this.fatherPanel = fatherPanel;
		
	}
	
	public void mouseClicked(MouseEvent e){
//		int x = e.getX();
//		int y = e.getY();
//		if(x > XOfHurdle[hurdle] && x < XOfHurdle[hurdle] + WidthOfHurdle[hurdle]&&
//			y > YOfHurdle[hurdle] && y < YOfHurdle[hurdle] + HeightOfHurdle[hurdle]	){
//			
//		}
		super.mouseClicked(e);
		try {
			Class<?> film = Class.forName("JPanelFilmProp" + hurdle);
			Constructor<?> propCons = film.getConstructor(JPanelHurdle.class);
			JPanelPropPattern prop = (JPanelPropPattern) propCons.newInstance(this.fatherPanel);
			frame.setContentPane(prop);
			frame.remove(fatherPanel);
			frame.revalidate();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e){
		super.mouseEntered(e);
		this.fatherPanel.mouseIn();
		this.fatherPanel.playScroll();
	}
	
	public void mouseExited(MouseEvent e){
		super.mouseExited(e);
		this.fatherPanel.mouseOut();
		this.fatherPanel.closeScroll();
	}
	
}
