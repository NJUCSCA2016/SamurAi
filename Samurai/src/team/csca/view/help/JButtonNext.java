package team.csca.view.help;

import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.image.ImgButton;


public class JButtonNext extends DynamicButton{
	private JPanelHelp fatherPanel;
	
	private JButtonPre bubttonPre = null;
	
	public JButtonNext(JPanelHelp fatherPanel){
		
		super(926, 576, 50, 50, ImgButton.NEXT_RIGHT_INIT , ImgButton.NEXT_RIGHT_ENTER , ImgButton.NEXT_RIGHT_CLICKED);
		this.fatherPanel = fatherPanel;
	
	}
	


	public void mouseClicked(MouseEvent e){

			this.fatherPanel.numOfPic++;
			if(this.fatherPanel.numOfPic == 1){
				this.bubttonPre.activate();
			}
			if(this.fatherPanel.numOfPic == JPanelHelp.LAST_INDEX){
				this.setButtonImg(ImgButton.NEXT_RIGHT_ENABLE);
				this.setEnabled(false);
				this.removeMouseListener(this);
			}else{
				this.setButtonImg(ImgButton.NEXT_RIGHT_ENTER);
			}
			
			this.fatherPanel.updateBackground();

	}
	
 
	
	protected void activate(){
		this.setButtonImg(ImgButton.NEXT_RIGHT_INIT);
		this.addMouseListener(this);
		this.setEnabled(true);
	}
	
//	protected ButtonNext getButtonNext(){
//		return this;
//	}
//	
	protected void setJButtonPre(JButtonPre buttonPre){
		this.bubttonPre = buttonPre;
	}
}
