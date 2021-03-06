package team.csca.view.filmProp;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;

public class JButtonNextPic extends DynamicButton {
	private JPanelStory situation;
		
	public JButtonNextPic(JPanelStory fatherPanel) {

		super(926, 576, 50, 50, ImgButton.NEXT_RIGHT_INIT, ImgButton.NEXT_RIGHT_ENTER, ImgButton.NEXT_RIGHT_CLICKED);
		this.situation = fatherPanel;

	}
	
	public void mouseClicked(MouseEvent e){
		if(this.situation.numOfPic < JPanelStory.maxPic){
			this.situation.numOfPic ++;
			this.situation.updateBackground();
			repaint();
		}else{
			this.frame.setContentPane(new JPanelHurdle());
			this.frame.remove(this.situation);
			this.frame.revalidate();
		}
	}
	
	

}
