package team.csca.view.filmProp;

import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgHurdle;

public class JButtonForHurdleChoose extends DynamicButton{

	private final static int[] XOfHurdle = new int[]{75, 50 , 424 , 676 , 872};
	private final static int[] YOfHurdle = new int[]{509 , 337 , 179 , 119 , 0};
	
	private final static int[] WidthOfHurdle = new int[]{206 , 276 , 274 , 208 , 126};
	private final static int[] HeightOfHurdle = new int[]{190 , 215 , 295 , 186 , 112};
	
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
		System.out.println(hurdle);
		JPanelPropPattern panel = null ;
		if(hurdle == 0){
			panel = new JPanelFilmProp1(fatherPanel);
		}else if(hurdle == 1){
			panel = new JPanelFilmProp2(fatherPanel);
		}else if (hurdle == 2) {
			panel = new JPanelFilmProp3(fatherPanel);
		}else if(hurdle == 3){
			panel = new JPanelFilmProp4(fatherPanel);
		}else if(hurdle == 4){
			panel = new JPanelFilmProp5(fatherPanel);
		}
		frame.setContentPane(panel);
		frame.remove(fatherPanel);
		frame.revalidate();
	}
	@Override
	public void mouseEntered(MouseEvent e){
		super.mouseEntered(e);
		repaint();
//		this.fatherPanel.mouseIn();
		this.fatherPanel.playScroll();
		repaint();
	}
	
	public void mouseExited(MouseEvent e){
		super.mouseExited(e);
		repaint();
//		this.fatherPanel.mouseOut();
		this.fatherPanel.closeScroll();
		repaint();
	}
	
}
