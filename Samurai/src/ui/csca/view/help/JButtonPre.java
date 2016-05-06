package ui.csca.view.help;

import java.awt.event.ActionEvent;

import com.sun.glass.events.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgButton;

public class JButtonPre extends DynamicButton{
	
	private JPanelHelp fatherPanel;
	
	private JButtonNext buttonNext = null;
	
	public JButtonPre(JPanelHelp fatherPanel){
		
		super(0, 0, 0, 0, ImgButton.NEXT_LEFT_INIT, ImgButton.NEXT_LEFT_ENTER , ImgButton.NEXT_LEFT_CLICKED);
		this.fatherPanel = fatherPanel;
		
	}
	
	public void mouseClicked(MouseEvent e){
		
			this.fatherPanel.numOfPic--;
			if(this.fatherPanel.numOfPic == this.fatherPanel.LAST_INDEX-1){
				this.buttonNext.setEnabled(true);
			}
			if(this.fatherPanel.numOfPic == 0){
				this.setButtonImg(ImgButton.NEXT_LEFT_ENABLE);
				this.setEnabled(false);
			}
			
			this.fatherPanel.updateBackground();
			
	}
	
	protected void activate(){
		this.setEnabled(true);
	}
	
//	protected ButtonPre getButtonPre(){
//		return this;
//	}
//	
	protected void setJButtonNext(JButtonNext  buttonNext){
		this.buttonNext = buttonNext;
	}
	
}
