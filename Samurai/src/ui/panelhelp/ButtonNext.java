package ui.panelhelp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Background;
import main.StaticButton;


public class ButtonNext extends StaticButton implements ActionListener{
	
	private PanelHelp fatherPanel;
	
	private Background inside;
	
	private ButtonPre bubttonPre = null;
	
	public ButtonNext(PanelHelp fatherPanel){
		
		super(0, 0, 0, 0, null);
		this.fatherPanel = fatherPanel;
		this.inside = fatherPanel.getInside();
		this.addActionListener(this);
	
	}
	

	@Override
	public void actionPerformed(ActionEvent ae){
		if(this.fatherPanel.numOfPic != this.fatherPanel.lastIndex){
			this.fatherPanel.numOfPic++;
			if(this.fatherPanel.numOfPic == 1){
				this.bubttonPre.setEnabled(true);
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
	
//	protected ButtonNext getButtonNext(){
//		return this;
//	}
//	
	protected void setButtonPre(ButtonPre buttonPre){
		this.bubttonPre = buttonPre;
	}
}
