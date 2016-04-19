package ui.panelhelp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import main.Background;
import main.SingletonClass;
import main.StaticButton;


public class ButtonNext extends StaticButton implements ActionListener{
	
	private int numOfPic = 0;
	
	private int lastIndex = 10;
	
	private PanelHelp fatherPanel;
	
	private Background inside;
	
	private JFrame frame;
	
	public ButtonNext(PanelHelp fatherPanel){
		
		super(0, 0, 0, 0, null);
		frame = SingletonClass.getFrameInstance();
		this.fatherPanel = fatherPanel;
		this.inside = fatherPanel.getInside();
		this.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae){
		if(numOfPic != lastIndex){
			numOfPic++;
//			TODO : 设置背景
//			this.inside.setBackground();
			if(numOfPic == lastIndex){
				this.setImg(null);
			}
		}else{
			this.setEnabled(false);
		}
	}
	
	public void preNumOfPic(){
		this.numOfPic--;
	}
	public int getNumOfPic(){
		return this.numOfPic;
	}
	public int getLastIndex(){
		return this.lastIndex;
	}
}
