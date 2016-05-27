package team.csca.view.filmProp;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;

public class JButtonNextPic extends DynamicButton {
	private JPanelSituation situation;
	
	

	public JButtonNextPic(JPanelSituation fatherPanel) {

		super(926, 576, 50, 50, ImgButton.NEXT_RIGHT_INIT, ImgButton.NEXT_RIGHT_ENTER, ImgButton.NEXT_RIGHT_CLICKED);
		this.situation = fatherPanel;

	}
	
	public void mouseClicked(MouseEvent e){
		JPanelSituation.numOfPic ++;
//		System.out.println(this.situation.numOfPic);
		this.situation.updateBackground();
//		if(this.situation.numOfPic == 1){
//			this.bubttonPre.activate();
//		}
		repaint();

	}
	
	protected void activate(){
		this.setButtonImg(ImgButton.NEXT_RIGHT_INIT);
		this.addMouseListener(this);
		this.setEnabled(true);
	}
	
	

}
