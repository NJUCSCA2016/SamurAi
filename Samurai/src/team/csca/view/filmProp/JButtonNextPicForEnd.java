package team.csca.view.filmProp;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;
import team.csca.view.startgame.JPanelStartGame;

public class JButtonNextPicForEnd extends DynamicButton{
	private JPanelStory situation;
	
	public JButtonNextPicForEnd(JPanelStory fatherPanel) {

		super(926, 576, 50, 50, ImgButton.NEXT_RIGHT_INIT, ImgButton.NEXT_RIGHT_ENTER, ImgButton.NEXT_RIGHT_CLICKED);
		this.situation = fatherPanel;

	}
	
	public void mouseClicked(MouseEvent e){
		if(this.situation.numOfPic < JPanelStory.FINAL_PIC){
			this.situation.numOfPic ++;
			this.situation.updateBackground();
			repaint();
		}else{
			this.frame.setContentPane(new JPanelStartGame());
			this.frame.remove(this.situation);
			JPanelHurdle.resetHurdle();
			this.frame.revalidate();
		}
	}
}
