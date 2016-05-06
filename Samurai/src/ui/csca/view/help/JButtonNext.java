package ui.csca.view.help;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.extend.LayerBackground;
import team.csca.view.image.ImgButton;
import ui.panelhelp.PanelHelp;


public class JButtonNext extends DynamicButton{
	private JPanelHelp fatherPanel;
	
	private JButtonPre bubttonPre = null;
	
	public JButtonNext(JPanelHelp fatherPanel){
		
		super(0, 0, 0, 0, ImgButton.NEXT_RIGHT_INIT , ImgButton.NEXT_RIGHT_ENTER , ImgButton.NEXT_RIGHT_CLICKED);
		this.fatherPanel = fatherPanel;
	
	}
	

	@Override
	public void mouseClicked(MouseEvent e){
//		if(this.fatherPanel.numOfPic != this.fatherPanel.LAST_INDEX){
			this.fatherPanel.numOfPic++;
			if(this.fatherPanel.numOfPic == 1){
				this.bubttonPre.activate();
			}
			if(this.fatherPanel.numOfPic == this.fatherPanel.LAST_INDEX){
				this.setButtonImg(ImgButton.NEXT_RIGHT_ENABLE);
				this.setEnabled(false);
			}
			
			this.fatherPanel.updateBackground();
//		}
	}
	
	protected void activate(){
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
