package ui.csca.view.help;

import java.awt.event.ActionEvent;

import team.csca.view.extend.DynamicButton;
import team.csca.view.extend.LayerBackground;
import ui.panelhelp.PanelHelp;

public class JButtonNext extends DynamicButton{
	private JPanelHelp fatherPanel;
	
	private LayerBackground inside;
	
	private JButtonPre bubttonPre = null;
	
	public JButtonNext(PanelHelp fatherPanel){
		
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
	protected void setJButtonPre(JButtonPre buttonPre){
		this.bubttonPre = buttonPre;
	}
}
