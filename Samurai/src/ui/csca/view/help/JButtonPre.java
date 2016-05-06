package ui.csca.view.help;

import java.awt.event.ActionEvent;

import team.csca.view.extend.LayerBackground;

public class JButtonPre {
	
	private JPanelHelp fatherPanel;
	
	private LayerBackground inside;
	
	private JButtonNext buttonNext = null;
	
	public JButtonPre(JPanelHelp fatherPanel){
		
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
	protected void setJButtonNext(JButtonNext  buttonNext){
		this.buttonNext = buttonNext;
	}
	
}
