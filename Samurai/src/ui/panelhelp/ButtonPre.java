package ui.panelhelp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Background;
import main.StaticButton;

public class ButtonPre extends StaticButton implements ActionListener{
	
	private PanelHelp fatherPanel;
	
	private Background inside;
	
	private ButtonNext buttonNext = null;
	
	public ButtonPre(PanelHelp fatherPanel){
		
		super(0, 0, 0, 0, null);
		this.fatherPanel = fatherPanel;
		this.inside = fatherPanel.getInside();
		this.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae){
		if(this.fatherPanel.numOfPic != 0){
			this.fatherPanel.numOfPic--;
			if(this.fatherPanel.numOfPic == this.fatherPanel.lastIndex-1){
				this.buttonNext.setEnabled(true);
			}
//			TODO : 设置背景
//			this.inside.setBackground();
		}else{
			this.setEnabled(false);
		}
	}
	
	protected void activate(){
		this.setEnabled(true);
	}
	
//	protected ButtonPre getButtonPre(){
//		return this;
//	}
//	
	protected void setButtonNext(ButtonNext  buttonNext){
		this.buttonNext = buttonNext;
	}
	
}
