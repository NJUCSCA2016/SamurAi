/**
 * Date : Apr 3, 2016 6:06:09 PM
 */
package ui.panelmain;

import java.awt.Graphics;

import javax.swing.JPanel;

import main.Background;

/**
 * @author Alone
 * Written by YYM
 */
public class PanelMain extends JPanel{
	
	private Background bk = null;
	
	public PanelMain(){
		
		this.setLayout(null);
		
		//TODO : 添加主界面的背景
		bk = new Background(null);
		this.add(bk);
		
		this.add(new ButtonExample(this));
		this.add(new ButtonHelp(this));
		this.add(new ButtonMachineGame(this));
		this.add(new ButtonPeopleGame(this));
		this.add(new ButtonSet(this));
		this.add(new ButtonExit());
		
	}
	
	public void paintComponent(Graphics g){
		bk.creatBack(g);
		super.paintComponents(g);
	}
	
}
