package team.csca.view.help;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;

public class JButtonPre extends DynamicButton{
	
	private JPanelHelp fatherPanel;
	
	private JButtonNext buttonNext = null;
	
	public JButtonPre(JPanelHelp fatherPanel){
		
		super(245, 585, 50, 50, ImgButton.NEXT_LEFT_INIT, ImgButton.NEXT_LEFT_ENTER , ImgButton.NEXT_LEFT_CLICKED);
		this.fatherPanel = fatherPanel;
		this.setButtonImg(ImgButton.NEXT_LEFT_ENABLE);
		this.setEnabled(false);
		this.removeMouseListener(this);
	}
	
	public void mouseClicked(MouseEvent e){
			
			this.fatherPanel.numOfPic--;
			if(this.fatherPanel.numOfPic == (JPanelHelp.LAST_INDEX-1)){
				this.buttonNext.activate();
			}
			if(this.fatherPanel.numOfPic == 0){
				this.setButtonImg(ImgButton.NEXT_LEFT_ENABLE);
				this.setEnabled(false);
				this.removeMouseListener(this);
			}else{
				
					this.setButtonImg(ImgButton.NEXT_LEFT_ENTER);

			}
			
			this.fatherPanel.updateBackground();
			
	}
	protected void activate(){
		this.setButtonImg(ImgButton.NEXT_LEFT_INIT);
		this.addMouseListener(this);
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
