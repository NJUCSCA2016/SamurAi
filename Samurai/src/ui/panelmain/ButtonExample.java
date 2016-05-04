/**
 * Date : Apr 4, 2016 6:14:33 PM
 */
package ui.panelmain;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import main.DynamicButton;
import ui.panelexample.PanelExample;

/**
 * @author Alone
 * Written by YYM
 */
public class ButtonExample extends DynamicButton{
	
	//TODO : 考虑设置一个总控制类，frame从其中获取而非作为参数传入
	
	/**
	 * fatherPanel为激活此Button的面板。
	 */
	/**
	 * 此对象本身的引用
	 */
	
	public ButtonExample(JPanel fatherPanel) {
		//TODO : 定义每个Butto的参数
		super(0, 0, 0, 0,null,null,null);
		this.fatherPanel = fatherPanel;
	}

	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		
		this.frame.setContentPane(new PanelExample());
		this.remove(this.fatherPanel);
		this.revalidate();
		
	}

}
