/**
 * Date : Apr 4, 2016 6:14:33 PM
 */
package ui.panelmain;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.SuperButton;
import ui.panelexample.PanelExample;

/**
 * @author Alone
 * Written by YYM
 */
public class ButtonExample extends SuperButton implements MouseListener{
	
	//TODO : 考虑设置一个总控制类，frame从其中获取而非作为参数传入
	private JFrame frame;
	
	/**
	 * fatherPanel为激活此Button的面板。
	 */
	private JPanel fatherPanel;
	/**
	 * 此对象本身的引用
	 */
	private JButton button;
	
	
	/**
	 *游戏示例Button
	 */
	public ButtonExample(JFrame frame , JPanel fatherPanel) {
		//TODO : 定义每个Butto的参数
		super(0, 0, 0, 0);
		this.frame = frame;
		this.fatherPanel = fatherPanel;
		this.button = this;
		this.setIcon(null);
		this.addMouseListener(this);
		
	}

	public void mouseClicked(MouseEvent e) {
		
		this.frame.setContentPane(new PanelExample());
		this.remove(this.fatherPanel);
		this.revalidate();
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO 改变Button的image
		
		this.button.setIcon(null);
		
	}
	public void mouseExited(MouseEvent e) {
		//TODO : 还原Button的image
		
		this.button.setIcon(null);
		
	}
	
	
	
	public void mousePressed(MouseEvent arg0) {	}
	public void mouseReleased(MouseEvent arg0) {}
	
	
}
